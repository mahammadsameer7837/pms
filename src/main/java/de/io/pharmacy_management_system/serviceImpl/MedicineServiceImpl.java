package de.io.pharmacy_management_system.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import de.io.pharmacy_management_system.model.Disease;
import de.io.pharmacy_management_system.model.Medicine;
import de.io.pharmacy_management_system.model.Patient;
import de.io.pharmacy_management_system.repository.DiseaseRepository;
import de.io.pharmacy_management_system.repository.MedicineRepository;
import de.io.pharmacy_management_system.repository.PatientRepository;
import de.io.pharmacy_management_system.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	MedicineRepository medicineRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	//CODE TO ADD A NEW MEDICINE
	@Override
	public Medicine addMedicine(Medicine newMedicine) throws MedicineAlreadyFoundException, ParseException, InvalidMedicineNameException, MedicineNameCannotBeNullException, InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException, InvalidMedicineCost, MedicineCostCannotBeNullException, YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, MedicineExpiredException, InvalidDateOfManufacture {
		
		ValidationService.checkMedicineName(newMedicine.getMedicineName());
		ValidationService.checkManufacturedCompanyName(newMedicine.getManufacturedCompanyName());
		ValidationService.checkMedicineCost(newMedicine.getCost());
		//ValidationService.compareDatesOfMedicines(newMedicine.getYearOfManufacture(), newMedicine.getExpiryDate());
		
		List<Medicine> OldMedicines=medicineRepository.findAllByMedicineName(newMedicine.getMedicineName());
		
		for(Medicine foundMedicine: OldMedicines ) {
		//if(foundMedicine != null) {
			
		if(foundMedicine.getMedicineName().equalsIgnoreCase(newMedicine.getMedicineName())) {
				if(foundMedicine.getManufacturedCompanyName().equalsIgnoreCase(newMedicine.getManufacturedCompanyName())) {
					 
					if(foundMedicine.getCost().equals(newMedicine.getCost())) {
						 
						throw new MedicineAlreadyFoundException("medicine already found");
						
					}
				}
			}
		}
		//}
		
	
		return medicineRepository.save(newMedicine);
	}
	
	//CODE TO UPDATE THE EXISTING MEDICINE
	@Override
	public Medicine updateMedicine(int medicineId,Medicine modifiedMedicine) throws MedicineNotFoundException, ParseException, MedicineNameTakenException, InvalidMedicineNameException, MedicineNameCannotBeNullException, YearOfManufactureCannotBeNullExcpetion, InvalidDateOfExpairy, MedicineExpiredException, InvalidDateOfManufacture, InvalidMedicineCost, MedicineCostCannotBeNullException, InvalidManufacturedCompanyName, ManufacturedCompanyNameCannotBeNullException {
		Medicine oldMedicine;
	 
		 oldMedicine = medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException());
		 
		 
		 if(modifiedMedicine.getMedicineName() != null) { 
			 if(medicineRepository.existsByMedicineName(modifiedMedicine.getMedicineName())) {
					throw new MedicineNameTakenException();
				}
			 ValidationService.checkMedicineName(modifiedMedicine.getMedicineName());
			 oldMedicine.setMedicineName(modifiedMedicine.getMedicineName());
			 
			 }
		 if(modifiedMedicine.getExpiryDate() != null) { 
			 ValidationService.compareDatesOfMedicines(oldMedicine.getYearOfManufacture(), modifiedMedicine.getExpiryDate());
			 oldMedicine.setExpiryDate(modifiedMedicine.getExpiryDate());
			 
		 }
		 if(modifiedMedicine.getCost() != null)
		 {
			 ValidationService.checkMedicineCost(modifiedMedicine.getCost());
			 oldMedicine.setCost(modifiedMedicine.getCost());
			 
			 }
		 if(modifiedMedicine.getManufacturedCompanyName() != null) {
		 ValidationService.checkManufacturedCompanyName(modifiedMedicine.getManufacturedCompanyName());
			 oldMedicine.setManufacturedCompanyName(modifiedMedicine.getManufacturedCompanyName());
		 }
		 if(modifiedMedicine.getYearOfManufacture() != null){
			 ValidationService.compareDatesOfMedicines(modifiedMedicine.getYearOfManufacture(), oldMedicine.getExpiryDate());
			 oldMedicine.setYearOfManufacture(modifiedMedicine.getYearOfManufacture());
		 }
		return medicineRepository.save(oldMedicine);
	}
	

	//CODE TO GET ALL MEDICINES AS LIST
	@Override
	public List<Medicine> getAllMedicines(){
		return medicineRepository.findAll();
	}
	//CODE TO GET ALL MEDICINES HAVING SAME NAME
	@Override
	public List<Medicine> getAllByName(String medicinename) {
		 
		return medicineRepository.findAllByMedicineName(medicinename);
	}
	//CODE TO DELETE MEDICINE BY ID
	@Override
	public String deleteMedicineById(int medicineId) throws MedicineNotFoundException {
		Medicine medicine; 
		 
			medicine =medicineRepository.findById(medicineId).orElseThrow(()-> new MedicineNotFoundException());
 
		for(Patient patient:patientRepository.findAll()) {
			 if(patient.getMedicationsList().remove(medicine)) {
				 patient.setMedicationCount(patient.getMedicationCount()-1);
				 patientRepository.save(patient);
			 } 
		}
		
		for(Disease disease:diseaseRepository.findAll()) {
			 if(disease.getMedicationSuggested().remove(medicine)) {
				 diseaseRepository.save(disease);
			 } 
		}
		
		medicineRepository.deleteById(medicineId);
		return "Medicine with Id "+medicineId+" Deleted Successfully";
	}
	//Code to get medicine by Id
	@Override
	public Medicine getMedicineById(int medicineId) throws MedicineNotFoundException {
		
		 
			return medicineRepository.findById(medicineId).orElseThrow(() -> new MedicineNotFoundException());
	 
		
	}
	
}
