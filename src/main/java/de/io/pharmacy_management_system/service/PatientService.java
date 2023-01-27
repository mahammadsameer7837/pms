package de.io.pharmacy_management_system.service;

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
import de.io.pharmacy_management_system.model.Patient;

public interface PatientService {

	Patient addPatient(Patient newPatient)
			throws PatientsLimitReachedException, MedicineNotFoundException, DoctorNotFoundException, NameCannotBeNullException, InvalidNameException, InvalidAdmittedReason, AdmittedReasonCannotBeNullException, InvalidDateOfDischarge, InvalidAddressException, AddressCannotBeNullException, InvalidDiseaseName, InvalidDoctorId, InvalidBillAmount;

	Patient updatePatient(int id, Patient modifiedPatient)
			throws PatientNotFoundException, DoctorNotFoundException, MedicineNotFoundException, InvalidNameException, NameCannotBeNullException, InvalidDateOfDischarge, InvalidAddressException, AddressCannotBeNullException, InvalidAdmittedReason, AdmittedReasonCannotBeNullException, InvalidDoctorId, InvalidBillAmount;

	Patient getPatient(int id) throws PatientNotFoundException;

	String deletePatient(int id) throws PatientNotFoundException;

	BillCart produceBill(int patientId) throws PatientNotFoundException, NoMedicinesAssignedException;

}
