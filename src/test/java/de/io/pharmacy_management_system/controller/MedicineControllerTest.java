package de.io.pharmacy_management_system.controller;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.io.pharmacy_management_system.model.Medicine;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicineControllerTest {
	
	
	private final static String BASE_URI = "http://localhost";

	private final ObjectMapper objectMapper = new ObjectMapper();

	ObjectWriter objectWriter = objectMapper.writer();

	@LocalServerPort
	private int port;
	
	Medicine medicine = new Medicine();
	
	@BeforeEach
	public void setUp() throws Exception{
		medicine.setMedicineId(1);
		medicine.setMedicineName("Para");
		medicine.setManufacturedCompanyName("Para Limited");
		medicine.setCost(200.05);
	}
	
	@BeforeEach
	public void configureRestAssured() {
		baseURI = BASE_URI;
		RestAssured.port = port;
	}

	// test will to check medicines with the given name
	@Test
	public void testGetAllMedicinesByNAme() {
		
		List list =(List) given().pathParam("medicinename", "Dolo").get("/medicine-services/get-all-by-name/{medicinename}").then().assertThat().statusCode(200).extract().as(List.class);;
		
		assertEquals(true,list.isEmpty());
	}
	// test to check whether an empty list is added or not
	@Test
	public void testGetAllMedicines() {
		List list =(List) given().get("/medicine-services/get-all-medicines").then().assertThat().statusCode(200).extract().as(List.class);
		
		assertEquals(true,list.isEmpty());
		
	}

	@Test
	public void testAddM() throws Exception{
		String content = objectWriter.writeValueAsString(medicine);

		given()
		.contentType(ContentType.JSON)
		.body(content).log().all()
		.when()
		.post("/medicine-services/add-medicine")
		.then().assertThat().statusCode(200).body("medicineName", equalTo("para"));
	}
	
	@Test
	public void testUpMedicine() throws Exception {
		
		Medicine medicine2 = new Medicine();
		medicine2.setMedicineId(1);
		medicine2.setManufacturedCompanyName("Dolo Limited");
		medicine2.setCost(200.05);
		
		String content = objectWriter.writeValueAsString(medicine2);
		
		with().contentType(ContentType.JSON).pathParam("id",1).body(content).when()
		.put("/medicine-services/update-medicine/{id}").then().assertThat().statusCode(200);
	}
	
	@Test
	public void testDeleteMedicine() throws Exception {
		
		String response = given().contentType(ContentType.JSON).pathParams("medicineId", 1).delete("/medicine-services/remove-medicine/{medicineId}")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				Assertions.assertEquals("Medicine with Id 1 Deleted Successfully", response);
	}

}
