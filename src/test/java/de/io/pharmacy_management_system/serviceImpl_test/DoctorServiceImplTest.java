package de.io.pharmacy_management_system.serviceImpl_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import de.io.pharmacy_management_system.model.Doctor;
import de.io.pharmacy_management_system.repository.DoctorRepository;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.serviceImpl.DoctorServicesImpl;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

	@InjectMocks
	DoctorServicesImpl doctorService;

	@Mock
	DoctorRepository doctorRepository;

	@Mock
	PatientRepository patientRepository;

	Doctor doctor;

	@BeforeEach
	public void setUp() {
		doctor = new Doctor();
		doctor.setDoctorIdT("id_1");
		doctor.setAddress("Hyderbad");
		doctor.setDesignation("Mbbs");
		doctor.setDoctorName("Mitra Vindha");
		doctor.setSpecialization("maternary");

	}

	@Test
	public void testFindDoctorById() throws Exception {
		Mockito.when(doctorRepository.findByDoctorIdT(doctor.getDoctorIdT())).thenReturn(doctor);

		assertEquals(doctor, doctorService.findDoctorById(doctor.getDoctorIdT()));
	}

	@Test
	public void testAddDoctor() throws Exception {
		Mockito.when(doctorRepository.findByDoctorIdT(doctor.getDoctorIdT())).thenReturn(null);
		Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
		assertEquals(doctor, doctorService.addDoctor(doctor));
	}

	@Test
	public void testUpdateDoctor() throws Exception {
		lenient().when(doctorRepository.findByDoctorIdT(doctor.getDoctorIdT())).thenReturn(doctor);
		System.out.println();
		System.out.println();
		Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
		assertEquals(doctor, doctorService.updateDoctor(doctor));

	}

	@Test
	public void testDeleteDoctor() throws Exception {

		Optional<Doctor> doctor_2 = Optional.of(doctor);
		Mockito.when(doctorRepository.findById(doctor.getDoctorId())).thenReturn(doctor_2);
		doctorService.deleteDoctor(doctor.getDoctorId());
		verify(doctorRepository, times(1)).deleteById(doctor.getDoctorId());

	}

	@Test
	public void testDeleteDoctorByIdt() throws Exception {

		Mockito.when(doctorRepository.findByDoctorIdT(doctor.getDoctorIdT())).thenReturn(doctor);
		doctorService.deleteDoctor(doctor.getDoctorIdT());
		verify(doctorRepository, times(1)).deleteById(doctor.getDoctorId());

	}
	 
}
