package de.io.pharmacy_management_system.service;

import java.text.ParseException;
import java.util.List;

import de.io.pharmacy_management_system.exceptions.InvalidDateOfExpairy;
import de.io.pharmacy_management_system.exceptions.InvalidDateOfManufacture;
import de.io.pharmacy_management_system.exceptions.InvalidManufacturedCompanyName;
import de.io.pharmacy_management_system.exceptions.InvalidMedicineCost;
import de.io.pharmacy_management_system.exceptions.InvalidMedicineNameException;
import de.io.pharmacy_management_system.exceptions.ManufacturedCompanyNameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineAlreadyFoundException;
import de.io.pharmacy_management_system.exceptions.MedicineCostCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineExpiredException;
import de.io.pharmacy_management_system.exceptions.MedicineNameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineNameTakenException;
import de.io.pharmacy_management_system.exceptions.MedicineNotFoundException;
import de.io.pharmacy_management_system.exceptions.YearOfManufactureCannotBeNullExcpetion;
import de.io.pharmacy_management_system.model.Medicine;

public interface MedicineService {

	Medicine addMedicine(Medicine newMedicine) throws MedicineAlreadyFoundException, ParseException, InvalidMedicineNameException, MedicineNameCannotBeNullException, InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException, InvalidMedicineCost, MedicineCostCannotBeNullException, YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, MedicineExpiredException, InvalidDateOfManufacture;

	Medicine updateMedicine(int medicineId, Medicine modifiedMedicine) throws MedicineNotFoundException, ParseException, MedicineNameTakenException, InvalidMedicineNameException, MedicineNameCannotBeNullException, YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, MedicineExpiredException, InvalidDateOfManufacture, InvalidMedicineCost, MedicineCostCannotBeNullException, InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException;

	List<Medicine> getAllMedicines();

	List<Medicine> getAllByName(String medicinename);

	String deleteMedicineById(int medicineId) throws MedicineNotFoundException;

	Medicine getMedicineById(int medicineId) throws MedicineNotFoundException;

}
