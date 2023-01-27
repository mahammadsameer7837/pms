package de.io.pharmacy_management_system.serviceImpl_test;

 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import de.io.pharmacy_management_system.model.Disease;
import de.io.pharmacy_management_system.model.Medicine;
import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.repository.DiseaseRepository;
import de.io.pharmacy_management_system.repository.MedicineRepository;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.service.MedicineService;
import de.io.pharmacy_management_system.serviceImpl.MedicineServiceImpl;
import de.io.pharmacy_management_system.serviceImpl.PatientServicesImpl;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceImplTest {

	@InjectMocks
	private MedicineServiceImpl medicineService;
	 
	@Mock
	private MedicineRepository medicineRepository;
	
	@Mock
	private PatientRepository patientRepository;
	
	@Mock
	private DiseaseRepository diseaseRepository;
	
	private Medicine medicine;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		medicine = new Medicine();
		medicine.setMedicineId(1);
		medicine.setMedicineName("Dolo");
		medicine.setCost(125.5);
		//medicine.setExpiryDate(new Date(2012,11,27));
		//medicine.setYearOfManufacture(new Date());
		medicine.setManufacturedCompanyName("Maxo Pharma Pvt limited");
		
	}
	
	
	 
	@Test
	public void testAddMedicine() throws Exception {
		 
		//Mockito.when(medicineRepository.findAllByMedicineName(medicine.getMedicineName()))
		//.thenReturn(new ArrayList<Medicine>());
		Mockito.when(medicineRepository.save(medicine)).thenReturn(medicine);
		
		assertEquals(medicine,medicineService.addMedicine(medicine));
		
	}
	
	@Test
	public void testUpdateMedicine() throws Exception {
		Optional<Medicine> medicineOp = Optional.of(medicine);
		lenient().when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(medicineOp);
		Mockito.when(medicineRepository.save(medicine)).thenReturn(medicine);
		assertEquals(medicine,medicineService.addMedicine(medicine));
	}
	
	@Test
	public void testGetAllMedicines() {
		List<Medicine> listOfMedicines = new ArrayList<>();
		Mockito.when(medicineRepository.findAll()).thenReturn(listOfMedicines );
		assertEquals(listOfMedicines,medicineService.getAllMedicines());
	}
	
	@Test
	public void testDeleteMedicineById() throws Exception {
		
		Optional<Medicine> medicineOp = Optional.of(medicine);
		
		Mockito.when(medicineRepository.findById(medicine.getMedicineId()))
		.thenReturn(medicineOp);
		
		List<Patient> listOfMockPatient = new ArrayList<>();
		
		Mockito.when(patientRepository.findAll()).thenReturn(listOfMockPatient );
		
		List<Disease> listOfMockingDiseases = new ArrayList<>();
		
		Mockito.when(diseaseRepository.findAll()).thenReturn(listOfMockingDiseases );
		
		medicineService.deleteMedicineById(medicine.getMedicineId());
		//Mockito.doNothing().when(medicineRepository).deleteById(medicine.getMedicineId());
		verify(medicineRepository, times(1)).deleteById(medicine.getMedicineId());
		 
	}
}
