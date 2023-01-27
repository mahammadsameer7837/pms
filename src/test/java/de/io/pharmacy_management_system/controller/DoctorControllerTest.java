package de.io.pharmacy_management_system.controller;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.io.pharmacy_management_system.model.Doctor;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorControllerTest {
	
	private final static String BASE_URI = "http://localhost";

	private final ObjectMapper objectMapper = new ObjectMapper();

	ObjectWriter objectWriter = objectMapper.writer();

	@LocalServerPort
	private int port;
	
   Doctor doctor = new Doctor();
	
	@BeforeEach
	public void setUp() throws Exception{
		doctor.setDoctorName("Aravind Kg");
		doctor.setAddress("hyderabad");
		doctor.setDesignation("MBBS");
		doctor.setDoctorIdT("dct11");
		doctor.setSpecialization("General");
	}
	
	@BeforeEach
	public void configureRestAssured() {
		baseURI = BASE_URI;
		RestAssured.port = port;
	}
	
	@Test
	public void testAddDoctor() throws Exception
	{
		String content = objectWriter.writeValueAsString(doctor);

		given()
		.contentType(ContentType.JSON)
		.body(content).log().all()
		.when()
		.post("/doctor-services/add-doctor")
		.then().assertThat().statusCode(200).body("doctorName", equalTo("Aravind Kg"));
	}
	
	@Test
	public void testAddDoctorsList() throws Exception {
		List<Doctor> list = new ArrayList<>();
		doctor.setDoctorIdT("DCT12");
		list.add(doctor);
		
		String content = objectWriter.writeValueAsString(list);

		given()
		.contentType(ContentType.JSON)
		.body(content).log().all()
		.when()
		.post("/doctor-services/add-doctors-list")
		.then().assertThat().statusCode(200).body("[0].doctorName", equalTo("Aravind Kg"));
		
	}
	
	@Test
	public void testUpdateDoctorOne() throws Exception{
		doctor.setAddress("Pune");
    String content = objectWriter.writeValueAsString(doctor);
		
		with().contentType(ContentType.JSON).pathParam("doctorId",1).body(content).when()
		.put("/doctor-services/update-doctor/id/{doctorId}").then().assertThat().statusCode(200);
	}
	
	@Test
	public void testUpdateDoctorTwo() throws Exception{
		doctor.setAddress("Hyderabd");
    String content = objectWriter.writeValueAsString(doctor);
		
		with().contentType(ContentType.JSON).body(content).when()
		.put("/doctor-services/update-doctor").then().assertThat().statusCode(200);
	}
	
	@Test
	public void testGetDoctor() throws Exception{
		doctor.setAddress("Hyderabd");
    String content = objectWriter.writeValueAsString(doctor);
		
		given().contentType(ContentType.JSON).pathParam("doctorId", 1).body(content).when()
		.get("/doctor-services/get-doctor-details/id/{doctorId}").then().assertThat().statusCode(200).body("doctorName", equalTo("Aravind Kg"));
	}
	
	@Test
	public void testDeleteDoctor() throws Exception{
		//Successfully deleted
		
		String response = given().contentType(ContentType.JSON).pathParams("doctorId", 1).delete("/doctor-services/delete-doctor/id/{doctorId}")
				.then().assertThat().statusCode(200).extract().response().asString();
				
				Assertions.assertEquals("Successfully deleted", response);
		
	}
}
