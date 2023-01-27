package de.io.pharmacy_management_system.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.io.pharmacy_management_system.exceptions.DiseaseAllreadyFoundException;
import de.io.pharmacy_management_system.exceptions.DiseaseNameNullException;
import de.io.pharmacy_management_system.exceptions.DiseaseNotFoundException;
import de.io.pharmacy_management_system.exceptions.EmptyMedicineSetException;
import de.io.pharmacy_management_system.exceptions.MedicineNotFoundException;
import de.io.pharmacy_management_system.model.Disease;
import de.io.pharmacy_management_system.model.Medicine;
import de.io.pharmacy_management_system.serviceImpl.DiseaseServicesImpl;

@RestController
@RequestMapping("/disease-services")
public class DiseaseController {
	
	@Autowired
	DiseaseServicesImpl diseaseServices;
	
	@GetMapping("/get-all-disesases")
	public List<Disease> getAllDisease(){
		System.out.println("Here-1");
		return diseaseServices.getAllDiseases();
	}
	@GetMapping("/get-disease-by-name/{diseaseName}")
	public List<Disease> getAllByName(@PathVariable String diseaseName){
		return diseaseServices.getAllDiseasesByName(diseaseName);
	}
	@GetMapping("/get-disease-by-id/{diseaseId}")
	public Disease getDiseaseById(@PathVariable int diseaseId) throws DiseaseNotFoundException
	{
		return diseaseServices.getADiseaseById(diseaseId);
	}
	
	@PostMapping("/add-disease")
	public Disease addDisease(@RequestBody Disease disease) throws DiseaseAllreadyFoundException {
		return diseaseServices.addDisease(disease);
	}
	@PutMapping("/update-disease-name/id/{id}")
	public Disease updateDiseaseName(@PathVariable int id,@RequestParam String name ) throws DiseaseNotFoundException, DiseaseAllreadyFoundException, DiseaseNameNullException {
		 
		return diseaseServices.updateDiseaseName(id, name);
				
	}
	@PutMapping("/update-disease-medication/set/id/{id}")
	public String updateDiseaseMedication(@PathVariable int id,@RequestBody Set<Medicine> medicines) throws DiseaseNotFoundException, EmptyMedicineSetException {
		return diseaseServices.updateMedicationToDisease(id, medicines);
				
	}
	@PutMapping("/update-disease-medication/id/{id}")
	public String updateDiseaseMedication(@PathVariable int id,@RequestBody Medicine medicine) throws DiseaseNotFoundException, DiseaseAllreadyFoundException, MedicineNotFoundException {
		return diseaseServices.updateMedicationToDisease(id, medicine);
				
	}

}
