package de.io.pharmacy_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import de.io.pharmacy_management_system.serviceImpl.DoctorServicesImpl;

@RestController
@RequestMapping("/doctor-services")
public class DoctorController {
	
	@Autowired
	DoctorServicesImpl doctorServices;
	
	@PostMapping("/add-doctors-list")
	public List<Doctor> addDoctorsList(@RequestBody List<Doctor> doctorsList) throws DoctorAlreadyExistWithIdTException, InvalidNameException, NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidSpecializationException, SpecializationCannotBeNullException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException{
		return doctorServices.addDoctorList(doctorsList);
	}
	//Requirement-2: Add new doctor into hospital
	@PostMapping("/add-doctor")
	public Doctor addDoctor(@RequestBody Doctor newDoctor) throws DoctorAlreadyExistWithIdTException, InvalidNameException, NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidSpecializationException, SpecializationCannotBeNullException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		return doctorServices.addDoctor(newDoctor);
	}
	//Requirement-5: Update doctor details
	@PutMapping("/update-doctor")
	public Doctor updateDoctor(@RequestBody Doctor updatedDoctor) throws DoctorNotFoundException, DoctorIdMissingException, DoctorAlreadyExistWithIdTException, InvalidDoctorId, InvalidDoctorIdTException, DoctorIdTCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException {
		return doctorServices.updateDoctor(updatedDoctor);
	}
	//Requirement-5: Update doctor details
	@PutMapping("/update-doctor/id/{doctorId}")
	public Doctor updateDoctor(@PathVariable int doctorId,@RequestBody Doctor updatedDoctor) throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, InvalidDoctorId, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException {
		return doctorServices.updateDoctor(doctorId, updatedDoctor);
	}
	//Requirement-5: Update doctor details
	@PutMapping("/update-doctor/idt/{doctorIdT}")
	public Doctor updateDoctor(@PathVariable String doctorIdT,@RequestBody Doctor updatedDoctor) throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException {
		return doctorServices.updateDoctor(doctorIdT, updatedDoctor);
	}
	//Requirement-5: Read doctor details
	@GetMapping("/get-doctor-details/id/{doctorId}")
	public Doctor getDoctorById(@PathVariable int doctorId) throws DoctorNotFoundException, InvalidDoctorId {
		
		return doctorServices.findDoctorById(doctorId);
		
	}
	//Requirement-5: Read doctor details
	@GetMapping("/get-doctor-details/idt/{doctorIdT}")
	public Doctor getDoctorByIdT(@PathVariable String doctorIdT) throws DoctorNotFoundException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		
		return doctorServices.findDoctorById(doctorIdT);
		
	}
	//Requirement-5: Delete doctor 
	@DeleteMapping("/delete-doctor/id/{doctorId}")
	public String deleteDoctor(@PathVariable int doctorId) throws DoctorNotFoundException, InvalidDoctorId {
		return doctorServices.deleteDoctor(doctorId);
	}
	//Requirement-5: Delete doctor 
	@DeleteMapping("/delete-doctor/idt/{doctorIdT}")
	public String deleteDoctor(@PathVariable String doctorIdT) throws DoctorNotFoundException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		//System.out.println("here-1");
		return doctorServices.deleteDoctor(doctorIdT);
	}

}
