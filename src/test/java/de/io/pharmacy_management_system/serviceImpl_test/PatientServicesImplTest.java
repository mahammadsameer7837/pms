package de.io.pharmacy_management_system.serviceImpl_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import de.io.pharmacy_management_system.exceptions.AddressCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.AdmittedReasonCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DoctorNotFoundException;
import de.io.pharmacy_management_system.exceptions.InvalidAddressException;
import de.io.pharmacy_management_system.exceptions.InvalidAdmittedReason;
import de.io.pharmacy_management_system.exceptions.InvalidBillAmount;
import de.io.pharmacy_management_system.exceptions.InvalidDateOfDischarge;
import de.io.pharmacy_management_system.exceptions.InvalidDiseaseName;
import de.io.pharmacy_management_system.exceptions.InvalidDoctorId;
import de.io.pharmacy_management_system.exceptions.InvalidNameException;
import de.io.pharmacy_management_system.exceptions.MedicineNotFoundException;
import de.io.pharmacy_management_system.exceptions.NameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.NoMedicinesAssignedException;
import de.io.pharmacy_management_system.exceptions.PatientNotFoundException;
import de.io.pharmacy_management_system.exceptions.PatientsLimitReachedException;
import de.io.pharmacy_management_system.model.BillCart;
import de.io.pharmacy_management_system.model.Medicine;
import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.repository.DoctorRepository;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.serviceImpl.PatientServicesImpl;

@ExtendWith(MockitoExtension.class)
public class PatientServicesImplTest {

	@InjectMocks
	PatientServicesImpl psi;

	@Mock
	PatientRepository patientRepo;

	Patient patient;

	Optional<Patient> patient_2;

	@BeforeEach
	public void init() {
		patient = new Patient();
		patient.setPatientName("Karthik");
		patient.setAdmittedReason("Cough");
		patient.setAddress("Hyderabad");
		patient.setBillAmountPaid(105.0);
		patient.setDateOfAdmission(new Date());
		patient.setDiseaseName("Chripso coughlarysis");
		patient_2 = Optional.of(new Patient());
	}

	@Nested
	public class TestAddPatient {
		@Test
		public void testAddPatient() throws Exception {
			Mockito.when(patientRepo.save(patient)).thenReturn(patient);
			System.out.println(patient.getPatientName());
			assertEquals(patient, psi.addPatient(patient));
		}

		@Test
		public void testPatientLimitReachedException() {
			Mockito.when(patientRepo.count()).thenReturn((long) 15);
			assertThrows(PatientsLimitReachedException.class, () -> psi.addPatient(patient));
		}
	}

	@Test
	public void testGetPatient() throws Exception {
		Mockito.when(patientRepo.findById(patient_2.get().getPatientId())).thenReturn(patient_2);
		assertEquals(patient_2, Optional.of(psi.getPatient(patient_2.get().getPatientId())));
	}

	@Test
	@Disabled
	public void testDeletePatient() throws Exception {
//		Mockito.when(pr.deleteById(patient.getPatientId())).thenReturn("Successfully Deleted");
//		assertEquals(patient_2,Optional.of(psi.getPatient(patient_2.get().getPatientId())));	 
		int patientId = patient_2.get().getPatientId();
		// perform the call
		psi.deletePatient(patientId);
		// verify the mocks
		verify(patientRepo, times(1)).deleteById(eq(patientId));
	}

	@Test
	public void testUpdatePatient() throws Exception {
		Mockito.when(patientRepo.findById(patient_2.get().getPatientId())).thenReturn(patient_2);
		Mockito.when(patientRepo.save(patient_2.get())).thenReturn(patient);
		assertEquals(patient, psi.updatePatient(patient.getPatientId(), patient));
	}

	// creating a nested class to test produce bill method Along with exceptions
	// raised in it
	//
	@Nested
	public class TestProduceBill {

		private Patient patientTemp; // patient object will be used for test

		@BeforeEach
		public void setUp() {
			// creating medicine objects to add to set of medicines in patient
			Medicine dolo = new Medicine();
			dolo.setCost(456.88);
			Medicine doloo = new Medicine();
			doloo.setCost(456.88);
			Medicine dlo = new Medicine();
			dlo.setCost(456.88);
			Medicine doo = new Medicine();
			doo.setCost(456.88);
			Set<Medicine> medicinesList = new HashSet<>();
			medicinesList.add(doo);
			medicinesList.add(dlo);
			medicinesList.add(doloo);
			medicinesList.add(dolo);

			// creating patient object and assigning values
			patientTemp = new Patient();
			patientTemp.setAddress("Hyderabad");
			patientTemp.setAdmittedReason("Motions");
			patientTemp.setBillAmountPaid(1000.09);
			patientTemp.setDateOfAdmission(new Date());
			patientTemp.setDiseaseName("High pressured vascular hydro leakage");
			patientTemp.setDoctorId(1);
			patientTemp.setMedicationCount(3);
			patientTemp.setMedicationsList(medicinesList);
			patientTemp.setPatientName("Bontha Bondika");
		}

		// testing produce bill method for success case
		@Test
		public void testProduceBillSuccess() throws Exception {
			Optional<Patient> patientOp = Optional.of(patientTemp);
			Mockito.when(patientRepo.findById(patientTemp.getPatientId())).thenReturn(patientOp);
			assertEquals(1827.52, psi.produceBill(patientTemp.getPatientId()).getBill());

		}

		// testing produce bill for raising patient not found exception
		@Test
		public void testProduceBillPatientNotFoundException() {
			Optional<Patient> patientOp = java.util.Optional.empty();
			Mockito.when(patientRepo.findById(2)).thenReturn(patientOp);
			assertThrows(PatientNotFoundException.class, () -> psi.produceBill(2));

		}

		// testing produce bill for raising NoMedicinesAssignedException
		@Test
		public void testProduceBillNoMedicinesAssignedException() {
			Optional<Patient> patientOp = Optional.of(new Patient());
			Mockito.when(patientRepo.findById(patientOp.get().getPatientId())).thenReturn(patientOp);
			assertThrows(NoMedicinesAssignedException.class, () -> psi.produceBill(patientOp.get().getPatientId()));

		}
	}

	/*
	 * package booth2example;
	 * 
	 * 
	 * 
	 * import static org.hamcrest.CoreMatchers.is; import static
	 * org.junit.jupiter.api.Assertions.*; import static
	 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
	 * import static
	 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
	 * 
	 * 
	 * 
	 * import java.util.ArrayList; import java.util.List; import java.util.Optional;
	 * 
	 * 
	 * 
	 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
	 * import org.junit.jupiter.api.extension.ExtendWith; import
	 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
	 * import org.mockito.MockitoAnnotations; import
	 * org.springframework.beans.factory.annotation.Autowired; import
	 * org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import
	 * org.springframework.http.MediaType; import
	 * org.springframework.test.context.junit.jupiter.SpringExtension; import
	 * org.springframework.test.web.servlet.MockMvc; import
	 * org.springframework.test.web.servlet.ResultMatcher; import
	 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders; import
	 * org.springframework.test.web.servlet.setup.MockMvcBuilders;
	 * 
	 * 
	 * 
	 * import com.fasterxml.jackson.databind.ObjectMapper; import
	 * com.h2.test.controller.EmployeeController; import com.h2.test.model.Employee;
	 * import com.h2.test.service.EmployeeService;
	 * 
	 * 
	 * 
	 * import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import
	 * org.mockito.Mock; import
	 * org.springframework.beans.factory.annotation.Autowired; import
	 * org.springframework.test.web.servlet.MockMvc;
	 * 
	 * 
	 * 
	 * import com.h2.test.controller.EmployeeController; import
	 * com.h2.test.service.EmployeeService;
	 * 
	 * 
	 * 
	 * @ExtendWith(SpringExtension.class) class testController {
	 * 
	 * 
	 * 
	 * 
	 * private MockMvc mockMvc;
	 * 
	 * @Mock private EmployeeService employeeService;
	 * 
	 * 
	 * 
	 * @InjectMocks private EmployeeController employeeController;
	 * 
	 * 
	 * 
	 * private final ObjectMapper objectMapper = new ObjectMapper();
	 * 
	 * Employee emp1=new Employee(1,"Sans","HR",350000); Employee emp2=new
	 * Employee(2,"Raj","IT",30000); Employee emp3=new
	 * Employee(3,"Ravi","admin",650000); Employee emp4=new
	 * Employee(4,"Rahul","HR",500000);
	 * 
	 * @BeforeEach public void setUp() {
	 * this.mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build(); }
	 * 
	 * @Test public void getAllrecords_success() throws Exception { List<Employee>
	 * records=new ArrayList<>(); records.add(emp1); records.add(emp2);
	 * records.add(emp3); records.add(emp4);
	 * 
	 * Mockito.when(employeeService.getAllEmployee()).thenReturn(records);
	 * mockMvc.perform(MockMvcRequestBuilders .get("/employee")
	 * .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
	 * .andExpect(jsonPath("$.size()", is(records.size())));
	 * 
	 * }
	 * 
	 * @Test public void getEmployeeByID() throws Exception {
	 * Mockito.when(employeeService.findById(emp1.getId())).thenReturn(Optional.of(
	 * emp1)); mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}",
	 * emp1.getId())) .andExpect(status().isOk()) .andExpect(jsonPath("$.name",
	 * is("Sans")));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

}
