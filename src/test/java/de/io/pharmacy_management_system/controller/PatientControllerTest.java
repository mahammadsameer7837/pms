package de.io.pharmacy_management_system.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.serviceImpl.PatientServicesImpl;


//@WebMvcTest(controllers = PatientController.class)
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
public class PatientControllerTest {

	//@Autowired
	private MockMvc mockMvc;
	
	//@Autowired
	//private WebApplicationContext webApplicationContext;
 
private final ObjectMapper objectMapper = new ObjectMapper();
	
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private PatientServicesImpl patientServices;
	
	@InjectMocks
	private PatientController patientController;
	
	Patient patient_1= new Patient();
	Patient patient_2= new Patient();
	
	 
	
	@BeforeEach
	public void setUp() {
		 
		this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
//		// mockMvc = webAppContextSetup(webApplicationContext)
//		            .build();
		patient_1.setPatientName("Sant");
		patient_1.setPatientId(1);
		patient_1.setAdmittedReason("Cough");
		patient_1.setAddress("Hyderabad");
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
	
	@Test
	public void testGetPatient() throws Exception {
		Mockito.when(patientServices.getPatient(Mockito.anyInt())).thenReturn(patient_1);
//		System.out.println(patientServices.getPatient(patient_1.getPatientId()).getPatientName());
		  mockMvc.perform(MockMvcRequestBuilders.get("/patient-services/get-patient/{id}", patient_1.getPatientId()))
		  .andExpect(status().isOk());
 	 // assertEquals(patient_1.getPatientName(),patientController.getPatient(patient_1.getPatientId()));
 	               // .andExpect(jsonPath("$.size()", is(records.size())))
 	               // .andExpect(jsonPath("$[0].name", is("Sans")));
 	}
 
}
