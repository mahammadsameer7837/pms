package de.io.pharmacy_management_system.controller;

import java.util.Date;

 
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.io.pharmacy_management_system.exceptions.PatientsLimitReachedException;
import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.serviceImpl.PatientServicesImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTestTwo {

	private final static String BASE_URI = "http://localhost";

	private final ObjectMapper objectMapper = new ObjectMapper();

	ObjectWriter objectWriter = objectMapper.writer();

	@LocalServerPort
	private int port;
	
	/*
	 * @Mock private PatientServicesImpl patientServices;
	 * 
	 * @InjectMocks private PatientController patientController;
	 */

	Patient patient_1 = new Patient();
	Patient patient_2 = new Patient();

	
	  @BeforeEach 
	  public void setUp() {
	 
	  patient_1.setPatientName("Kadberi"); 
	  patient_1.setAdmittedReason("Cough"); 
	  patient_1.setAddress("Kurnool");
	  patient_1.setBillAmountPaid(105.0);
	  patient_1.setDateOfAdmission(new Date());
	  patient_1.setDiseaseName("Chripso coughlarysis");
	  
	  patient_2.setPatientName("Sandep");
	  patient_2.setAdmittedReason("Cold");
	  patient_2.setAddress("Hyderabad"); 
	  patient_2.setBillAmountPaid(105.0);
	  patient_2.setDateOfAdmission(new Date());
	  patient_2.setDiseaseName("Chripso coldlarysis");
	  
	  }
	 

	@BeforeEach
	public void configureRestAssured() {
		baseURI = BASE_URI;
		RestAssured.port = port;
	}

	@Test
	public void testGetPatient() throws Exception {

		// baseURI = "http://localhost:8080/patient-services";

		//Mockito.when(patientServices.getPatient(1)).thenReturn(patient_2);

		given().pathParam("id", 2).get("/patient-services/get-patient/{id}").then().assertThat().statusCode(200)
				.body("patientId", Matchers.equalTo(2));

	}
	@Test
	public void testAddPatient() throws Exception {
		//Mockito.when(patientServices.addPatient(patient_1)).thenReturn(patient_1);
		/*
		 * with() .contentType(ContentType.JSON)
		 * .accept(ContentType.JSON).body(patient_1)
		 * .when().post("/patient-services/add-patient")
		 * .then().assertThat().statusCode(204);
		 */

		String content = objectWriter.writeValueAsString(patient_1);

		given()
		.contentType(ContentType.JSON)
		.body(content).log().all()
		.when()
		.post("/patient-services/add-patient")
		.then().assertThat().statusCode(200).body("patientName", equalTo("Santhosh"));
		
	}
	
	@Test
	public void testAddPatientThrowsPatientsLimitReachedException() throws Exception {
		//Mockito.when(patientServices.addPatient(Mockito.any(Patient.class))).thenThrow(PatientsLimitReachedException.class);
		
		String content = objectWriter.writeValueAsString(patient_1);
		
		  given().contentType(ContentType.JSON)
		  .body(content).when()
		  .post("/patient-services/add-patient").then().assertThat().statusCode(509); 
	}
	
	@Test
	public void testUpdatePatient() throws Exception{
		String content = objectWriter.writeValueAsString(patient_1);
		with().contentType(ContentType.JSON).pathParam("id",2).body(content).when()
		.put("/patient-services/update-patient/{id}").then().assertThat().statusCode(200);
	}
	
	@Test
	public void testDeletePatient() {
		
		String response = given().contentType(ContentType.JSON).pathParams("id", 2).delete("/patient-services/delete-patient/{id}")
		.then().assertThat().statusCode(404).extract().response().asString();
		
		Assertions.assertEquals("Patient not found", response);
	
		
	}

}
