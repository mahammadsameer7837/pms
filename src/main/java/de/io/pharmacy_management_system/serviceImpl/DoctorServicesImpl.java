package de.io.pharmacy_management_system.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.io.pharmacy_management_system.exceptions.AddressCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DesignationCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DoctorAlreadyExistWithIdTException;
import de.io.pharmacy_management_system.exceptions.DoctorIdMissingException;
import de.io.pharmacy_management_system.exceptions.DoctorIdTCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DoctorNotFoundException;
import de.io.pharmacy_management_system.exceptions.InvalidAddressException;
import de.io.pharmacy_management_system.exceptions.InvalidDesignationException;
import de.io.pharmacy_management_system.exceptions.InvalidDoctorId;
import de.io.pharmacy_management_system.exceptions.InvalidDoctorIdTException;
import de.io.pharmacy_management_system.exceptions.InvalidNameException;
import de.io.pharmacy_management_system.exceptions.InvalidSpecializationException;
import de.io.pharmacy_management_system.exceptions.NameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.SpecializationCannotBeNullException;
import de.io.pharmacy_management_system.model.Doctor;
import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.repository.DoctorRepository;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.service.DoctorService;

@Service
public class DoctorServicesImpl implements DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PatientRepository patientRepository;
	//CODE TO ADD A DOCTOR TO PATIENT
	@Override
	public void addPatienttoDoctor(Patient patient) throws DoctorNotFoundException {
		 
		Doctor nDoctor;
		try {
		 nDoctor = doctorRepository.findById(patient.getDoctorId()).get();
		}
		catch(Exception ex) {
		 
			throw new DoctorNotFoundException();
		}
		List<Doctor> doctorsList = doctorRepository.findAll();
		for(Doctor mDoctor: doctorsList) {
			boolean tf=false;
		if(!mDoctor.getPatientsAssigned().isEmpty()) {
			 
		for(Patient assignedPatient: mDoctor.getPatientsAssigned()) {
			 
			if(assignedPatient.getPatientId()==patient.getPatientId()) {
				 
				mDoctor.getPatientsAssigned().remove(assignedPatient);
				
				System.out.println("REMOVED ALREADY PRESENT PATIENT");
				
				doctorRepository.save(mDoctor);
				tf=true;
				break;
			}
		}
		if(tf) {
			break;
		}
		}
		}
		patientRepository.save(patient);
		nDoctor.getPatientsAssigned().add(patient);
		 
		doctorRepository.save(nDoctor);
		 
	}

	//CODE TO READ A DOCTOR DETAILS BY TAKING ID REQUIRMENT-5
	@Override
	public Doctor findDoctorById(int id) throws DoctorNotFoundException, InvalidDoctorId {
		 ValidationService.checkDoctorId(id);
		Doctor d;
		try {
		d = doctorRepository.findById(id).get();
		}
		catch(Exception ex) {
			throw new DoctorNotFoundException(); 
		}
		 
		return d;

	}

	//CODE TO READ A DOCTOR DETAILS BY TAKING iDT REQUIRMENT-5
	@Override
	public Doctor findDoctorById(String doctorIdT) throws DoctorNotFoundException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		ValidationService.checkDoctorIdT(doctorIdT);
		Doctor d = doctorRepository.findByDoctorIdT(doctorIdT);
		if (d == null) {
			throw new DoctorNotFoundException();
		}
		// System.out.println("here-12");
		return d;

	}

	//CODE TO ADD A NEW DOCTOR REQUIREMENT --2
	@Override
	public Doctor addDoctor(Doctor doctor) 
			throws DoctorAlreadyExistWithIdTException, 
			InvalidNameException, NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidSpecializationException, SpecializationCannotBeNullException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {

		if (doctorRepository.findByDoctorIdT(doctor.getDoctorIdT()) == null) {
			
			ValidationService.checkName(doctor.getDoctorName());
			ValidationService.checkAddress(doctor.getAddress());
			ValidationService.checkDesignation(doctor.getDesignation());
			ValidationService.checkSpecialization(doctor.getSpecialization());
			ValidationService.checkDoctorIdT(doctor.getDoctorIdT());
			return doctorRepository.save(doctor);

		}
		throw new DoctorAlreadyExistWithIdTException();

	}

	// CODE TO UPDATE DOCTOR DETAILS BY TAKING ID REQUIRMENT-5
	@Override
	public Doctor updateDoctor(int docotorId, Doctor doctor)
			throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, 
			InvalidDoctorId, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException {

		Doctor oldDoctor = findDoctorById(docotorId);

		return doctorUpdater(doctor, oldDoctor);

	}
//CODE TO UPDATE DOCTOR BY TAKING IDT REQUIRMENT-5
	@Override
	public Doctor updateDoctor(String docotorIdT, Doctor doctor)
			throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException {
		Doctor oldDoctor = findDoctorById(docotorIdT);
		return doctorUpdater(doctor, oldDoctor);

	}
//CODE TO UPDATE DOCTOR BY JUST TAKING DOCTOR REQUIRMENT-5
	@Override
	public Doctor updateDoctor(Doctor doctor)
			throws DoctorNotFoundException, 
			DoctorIdMissingException, DoctorAlreadyExistWithIdTException, 
			InvalidDoctorId, InvalidDoctorIdTException, DoctorIdTCannotBeNullException, 
			InvalidAddressException, AddressCannotBeNullException, 
			DesignationCannotBeNullException, InvalidDesignationException, 
			InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, 
			SpecializationCannotBeNullException {
		boolean go = true;
		if (doctor.getDoctorIdT() == null ) {
			go = false;
		} 
		if (go) {
			return doctorUpdater(doctor, findDoctorById(doctor.getDoctorIdT()));
		} 
		if (doctor.getDoctorId() == 0) {
			throw new DoctorIdMissingException();
		} else {
			return doctorUpdater(doctor, findDoctorById(doctor.getDoctorId()));
		}

	}
//CODE WHICH PERFORMS THE DOCTOR UPDATION DETAILS 
	private Doctor doctorUpdater(Doctor doctor, Doctor oldDoctor) 
			throws DoctorAlreadyExistWithIdTException, InvalidAddressException, 
			AddressCannotBeNullException, DesignationCannotBeNullException, 
			InvalidDesignationException, InvalidNameException, NameCannotBeNullException, 
			InvalidSpecializationException, SpecializationCannotBeNullException {

//		if (doctor.getDoctorIdT() != null) {
//			if (doctorRepository.findByDoctorIdT(doctor.getDoctorIdT()) == null) {
//				oldDoctor.setDoctorIdT(doctor.getDoctorIdT());
//			} else {
//				throw new DoctorAlreadyExistWithIdTException();
//			}
//		}
		if (doctor.getAddress() != null) {
			ValidationService.checkAddress(doctor.getAddress());
			oldDoctor.setAddress(doctor.getAddress());
		}
		if (doctor.getDesignation() != null) {
			ValidationService.checkDesignation(doctor.getDesignation());
			oldDoctor.setDesignation(doctor.getDesignation());
		}
		if (doctor.getDoctorName() != null) {
			ValidationService.checkName(doctor.getDoctorName());
			oldDoctor.setDoctorName(doctor.getDoctorName());
		}
		if (doctor.getSpecialization() != null) {
			ValidationService.checkSpecialization(doctor.getSpecialization());
			oldDoctor.setSpecialization(doctor.getSpecialization());
		}

		return doctorRepository.save(oldDoctor);
	}
//CODE TO DELETE A DOCTOR BY TAKING IDT  REQUIRMENT-5
	@Override
	public String deleteDoctor(int doctorId) throws DoctorNotFoundException, InvalidDoctorId {
		findDoctorById(doctorId);
		doctorRepository.deleteById(doctorId);
		return "Successfully deleted";

	}
//CODE TO DELETE A DOCTOR BY TAKING IDT----REQUIRMENT-5
	@Override
	public String deleteDoctor(String doctorIdT) throws DoctorNotFoundException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		 
		doctorRepository.deleteById(findDoctorById(doctorIdT).getDoctorId() );
		//System.out.println("here-4");
		return "Successfully deleted";

	}
//CODE TO ADD A LIST OF DOCTORS
	@Override
	public List<Doctor> addDoctorList(List<Doctor> doctorsList) 
			throws DoctorAlreadyExistWithIdTException, InvalidNameException, 
			NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, 
			DesignationCannotBeNullException, InvalidDesignationException, 
			InvalidSpecializationException, SpecializationCannotBeNullException, 
			InvalidDoctorIdTException, DoctorIdTCannotBeNullException {

		for (Doctor doctor : doctorsList) {
			addDoctor(doctor);
		}

		return doctorsList;
	}
//CODE TO REMOVE PATIENT FROM DOCTOR
	public void removePatientFromDoctor(Patient patient){
		Doctor doctor;
		try {
		 doctor = doctorRepository.findById(patient.getDoctorId()).get();
		}
		catch(Exception ex) {
			return;
		}
		doctor.getPatientsAssigned().remove(patient);
		doctorRepository.save(doctor);
		
	}

}
