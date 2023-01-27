package de.io.pharmacy_management_system.service;

import java.util.List;

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

public interface DoctorService {
	
	
	public void addPatienttoDoctor(Patient patient) throws DoctorNotFoundException ; // l1 

	public String deleteDoctor(String doctorIdT) throws DoctorNotFoundException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException;//l1

	public String deleteDoctor(int doctorId) throws DoctorNotFoundException, InvalidDoctorId;//l1

	public Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException, DoctorIdMissingException, DoctorAlreadyExistWithIdTException, InvalidDoctorId, InvalidDoctorIdTException, DoctorIdTCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException;//l1

	public Doctor updateDoctor(String docotorIdT, Doctor doctor) throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException,InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException;//l1

	public Doctor updateDoctor(int docotorId, Doctor doctor) throws DoctorNotFoundException, DoctorAlreadyExistWithIdTException, InvalidDoctorId, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidNameException, NameCannotBeNullException, InvalidSpecializationException, SpecializationCannotBeNullException;//l1

	public Doctor addDoctor(Doctor doctor) throws DoctorAlreadyExistWithIdTException, InvalidNameException, NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidSpecializationException, SpecializationCannotBeNullException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException;//l1

	public Doctor findDoctorById(String doctorIdT) throws DoctorNotFoundException,InvalidDoctorIdTException, DoctorIdTCannotBeNullException;// l1

	public Doctor findDoctorById(int id) throws DoctorNotFoundException, InvalidDoctorId;// l1

	List<Doctor> addDoctorList(List<Doctor> doctorsList) throws DoctorAlreadyExistWithIdTException, InvalidNameException, NameCannotBeNullException, InvalidAddressException, AddressCannotBeNullException, DesignationCannotBeNullException, InvalidDesignationException, InvalidSpecializationException, SpecializationCannotBeNullException, InvalidDoctorIdTException, DoctorIdTCannotBeNullException;

}
