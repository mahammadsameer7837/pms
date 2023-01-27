package de.io.pharmacy_management_system.controller;

import java.text.ParseException;
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
import de.io.pharmacy_management_system.serviceImpl.MedicineServiceImpl;

@RestController
@RequestMapping("/medicine-services")
public class MedicineController {
	
	@Autowired
	MedicineServiceImpl medicineservices;
	//Requirement-4: Add medicine
	@PostMapping("/add-medicine")
	public Medicine addM(@RequestBody Medicine med) throws MedicineAlreadyFoundException, 
	ParseException, InvalidMedicineNameException, MedicineNameCannotBeNullException, 
	InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException, 
	InvalidMedicineCost, MedicineCostCannotBeNullException, 
	YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, 
	MedicineExpiredException, InvalidDateOfManufacture {
		return medicineservices.addMedicine(med);
	}
	//Requirement-4: update medicine
	@PutMapping("/update-medicine/{id}")
	public String upMedicine(@PathVariable int id,@RequestBody Medicine medicine) 
			throws MedicineNotFoundException, ParseException, MedicineNameTakenException, 
			InvalidMedicineNameException, MedicineNameCannotBeNullException, 
			YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, 
			MedicineExpiredException, InvalidDateOfManufacture, InvalidMedicineCost, 
			MedicineCostCannotBeNullException, InvalidManufacturedCompanyName, 
			ManufacturedCompanyNameCannotBeNullException {
		medicineservices.updateMedicine(id, medicine);
		return "Update Successfull";
	}
	//Requirement-4: read medicine details
	@GetMapping("/get-all-medicines")
	public List<Medicine> getAllMedicines(){
		
		return medicineservices.getAllMedicines();
		
	}
	//Requirement-4: read medicine by name
	@GetMapping("/get-all-by-name/{medicinename}")
	public List<Medicine> getAllMedicines(@PathVariable String medicinename){
		
		return medicineservices.getAllByName(medicinename);
		
	}
	//Requirement-4: delete medicine
	@DeleteMapping("/remove-medicine/{medicineId}")
	public String deleteMedicine(@PathVariable int medicineId) throws MedicineNotFoundException {
		return medicineservices.deleteMedicineById(medicineId);
	}

}
