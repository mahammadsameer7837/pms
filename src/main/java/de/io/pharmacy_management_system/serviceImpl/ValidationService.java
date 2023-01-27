package de.io.pharmacy_management_system.serviceImpl;

import java.util.Date;

import de.io.pharmacy_management_system.exceptions.AddressCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.AdmittedReasonCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DesignationCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.DoctorIdTCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.InvalidAddressException;
import de.io.pharmacy_management_system.exceptions.InvalidAdmittedReason;
import de.io.pharmacy_management_system.exceptions.InvalidBillAmount;
import de.io.pharmacy_management_system.exceptions.InvalidDateOfDischarge;
import de.io.pharmacy_management_system.exceptions.InvalidDateOfExpairy;
import de.io.pharmacy_management_system.exceptions.InvalidDateOfManufacture;
import de.io.pharmacy_management_system.exceptions.InvalidDesignationException;
import de.io.pharmacy_management_system.exceptions.InvalidDiseaseName;
import de.io.pharmacy_management_system.exceptions.InvalidDoctorId;
import de.io.pharmacy_management_system.exceptions.InvalidDoctorIdTException;
import de.io.pharmacy_management_system.exceptions.InvalidManufacturedCompanyName;
import de.io.pharmacy_management_system.exceptions.InvalidMedicineCost;
import de.io.pharmacy_management_system.exceptions.InvalidMedicineNameException;
import de.io.pharmacy_management_system.exceptions.InvalidNameException;
import de.io.pharmacy_management_system.exceptions.InvalidSpecializationException;
import de.io.pharmacy_management_system.exceptions.ManufacturedCompanyNameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineCostCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineExpiredException;
import de.io.pharmacy_management_system.exceptions.MedicineNameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.NameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.SpecializationCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.YearOfManufactureCannotBeNullExcpetion;
import de.io.pharmacy_management_system.model.Medicine;

public class ValidationService {

	public static void checkName(String name) throws InvalidNameException, NameCannotBeNullException {

		try {
			if (name.isBlank()) {

				throw new InvalidNameException();
			}
		} catch (NullPointerException npe) {
			throw new NameCannotBeNullException();
		}

		if (!Character.isLetter(name.charAt(0))) {

			throw new InvalidNameException();
		}

		int length = name.length();

		for (int index = 0; index < length; index++) {

			if (!(Character.isLetter(name.charAt(index)) || Character.isWhitespace(name.charAt(index)))) {

				throw new InvalidNameException();
			}
		}

	}

	public static void checkAdmittedReason(String admittedReason)
			throws InvalidAdmittedReason, AdmittedReasonCannotBeNullException {
		try {
			if (admittedReason.isBlank()) {
				throw new InvalidAdmittedReason();
			}
		} catch (NullPointerException npe) {
			throw new AdmittedReasonCannotBeNullException();
		}

		if (!(Character.isLetter(admittedReason.charAt(0)) || Character.isDigit(admittedReason.charAt(0)))) {
			throw new InvalidAdmittedReason();
		}
		int length = admittedReason.length();
		for (int index = 1; index < length; index++) {
			char ch = admittedReason.charAt(index);

			if (!(Character.isLetter(ch) || Character.isDigit(ch) || Character.isWhitespace(ch) || (ch == '.')
					|| (ch == ','))) {

				throw new InvalidAdmittedReason();

			}
		}

	}

	public static void compareDates(Date dateOfAdmission, Date dateOfDischarge) throws InvalidDateOfDischarge {

		if (dateOfDischarge == null) {
			return;
		}

		if (dateOfDischarge.compareTo(dateOfAdmission) < 0) {
			throw new InvalidDateOfDischarge();
		}

	}

	public static void checkAddress(String address) throws InvalidAddressException, AddressCannotBeNullException {

		try {
			if (address.isBlank()) {
				throw new InvalidAddressException();
			}
		} catch (NullPointerException npe) {
			throw new AddressCannotBeNullException();
		}
	}

	public static void checkDiseaseName(String diseaseName) throws InvalidDiseaseName {

		try {
			if (diseaseName.isBlank()) {
				throw new InvalidDiseaseName();
			}
		} catch (NullPointerException npe) {
			return;
		}

		if (!(Character.isLetter(diseaseName.charAt(0)))) {
			throw new InvalidDiseaseName();
		}
		int length = diseaseName.length();
		for (int index = 1; index < length; index++) {
			char ch = diseaseName.charAt(index);

			if (!(Character.isLetter(ch) || Character.isDigit(ch) || Character.isWhitespace(ch) || (ch == '-'))) {

				throw new InvalidDiseaseName();

			}
		}

	}

	public static void checkDoctorId(int doctorId) throws InvalidDoctorId {
		if (doctorId < 0) {
			throw new InvalidDoctorId();
		}

	}

	public static void checkBillAmount(Double billAmountPaid) throws InvalidBillAmount {

		try {
			if (billAmountPaid <= 0) {

				throw new InvalidBillAmount();
			}
		} catch (Exception ex) {
			throw new InvalidBillAmount();
		}

	}

	public static void checkDesignation(String designation)
			throws DesignationCannotBeNullException, InvalidDesignationException {

		try {
			if (designation.isBlank()) {

				throw new InvalidDesignationException();
			}
		} catch (NullPointerException npe) {
			throw new DesignationCannotBeNullException();
		}

		if (!Character.isLetter(designation.charAt(0))) {

			throw new InvalidDesignationException();
		}

		int length = designation.length();

		for (int index = 0; index < length; index++) {
			char ch = designation.charAt(index);
			if (!(Character.isLetter(ch) || Character.isWhitespace(ch) || ch == '-' || Character.isDigit(ch))) {

				throw new InvalidDesignationException();
			}
		}
	}

	public static void checkSpecialization(String specialization)
			throws InvalidSpecializationException, SpecializationCannotBeNullException {

		try {
			if (specialization.isBlank()) {

				throw new InvalidSpecializationException();
			}
		} catch (NullPointerException npe) {
			throw new SpecializationCannotBeNullException();
		}

		if (!Character.isLetter(specialization.charAt(0))) {

			throw new InvalidSpecializationException();
		}

		int length = specialization.length();

		for (int index = 0; index < length; index++) {
			char ch = specialization.charAt(index);
			if (!(Character.isLetter(ch) || Character.isWhitespace(ch) || ch == '-' || Character.isDigit(ch))) {

				throw new InvalidSpecializationException();
			}
		}
	}

	public static void checkDoctorIdT(String doctorIdT)
			throws InvalidDoctorIdTException, DoctorIdTCannotBeNullException {
		try {
			if (doctorIdT.isBlank()) {

				throw new InvalidDoctorIdTException();
			}
		} catch (NullPointerException npe) {
			throw new DoctorIdTCannotBeNullException();
		}
		if (!(Character.isLetter(doctorIdT.charAt(0))) || doctorIdT.charAt(0) == '_' ) {

			throw new InvalidDoctorIdTException();
		}
		int length = doctorIdT.length();
		for (int index = 0; index < length; index++) {
			char ch = doctorIdT.charAt(index);
			if (!(Character.isLetter(ch) || Character.isWhitespace(ch) || ch == '_' || Character.isDigit(ch))) {

				throw new InvalidDoctorIdTException();
			}
		}
	}

	public static void checkMedicineName(String medicinename)
			throws InvalidMedicineNameException, MedicineNameCannotBeNullException {
		try {
			if (medicinename.isBlank()) {

				throw new InvalidMedicineNameException();
			}
		} catch (NullPointerException npe) {
			throw new MedicineNameCannotBeNullException();
		}
		if (!Character.isLetter(medicinename.charAt(0))) {

			throw new InvalidMedicineNameException();
		}

		int length = medicinename.length();
		for (int index = 1; index < length; index++) {
			char ch = medicinename.charAt(index);

			if (!(Character.isLetter(ch) || Character.isDigit(ch) || Character.isWhitespace(ch) || (ch == '-'))) {

				throw new InvalidMedicineNameException();
			}
		}
	}

	public static void checkManufacturedCompanyName(String manufacturedCompanyName) throws InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException {
	 
			if (manufacturedCompanyName.isBlank()) {

				throw new InvalidManufacturedCompanyName();
			}
		
	}

	public static void checkMedicineCost(Double cost) throws InvalidMedicineCost, MedicineCostCannotBeNullException {
		try {
			if (cost <0) {

				throw new InvalidMedicineCost();
			}
		} catch (NullPointerException npe) {
			throw new MedicineCostCannotBeNullException();
		}
		
	}

	public static void compareDatesOfMedicines(Date yearOfManufacture, Date expiryDate) throws YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, MedicineExpiredException, InvalidDateOfManufacture {
		 if(yearOfManufacture == null) {
			 throw new YearOfManufactureCannotBeNullExcpetion();
		 }
		 if(expiryDate == null) {
			 throw new YearOfManufactureCannotBeNullExcpetion();
		 }
		 if(yearOfManufacture.compareTo(new Date()) > 0) {
			 throw new InvalidDateOfManufacture();
		 }

		 if(expiryDate.compareTo(new Date()) < 0) {
			 throw new MedicineExpiredException();
		 }
		 if (expiryDate.compareTo(yearOfManufacture) < 0) {
				throw new InvalidDateOfExpairy();
			}
		
		 
		
	}
	
}
