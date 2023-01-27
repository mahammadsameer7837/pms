package de.io.pharmacy_management_system.service;

import java.util.List;
import java.util.Set;

import de.io.pharmacy_management_system.exceptions.DiseaseAllreadyFoundException;
import de.io.pharmacy_management_system.exceptions.DiseaseNameNullException;
import de.io.pharmacy_management_system.exceptions.DiseaseNotFoundException;
import de.io.pharmacy_management_system.exceptions.EmptyMedicineSetException;
import de.io.pharmacy_management_system.exceptions.MedicineNotFoundException;
import de.io.pharmacy_management_system.model.Disease;
import de.io.pharmacy_management_system.model.Medicine;

public interface DiseaseService {

	Disease addDisease(Disease disease) throws DiseaseAllreadyFoundException;

	Disease updateDiseaseName(int diseaseId, String diseaseName)
			throws DiseaseAllreadyFoundException, DiseaseNotFoundException, DiseaseNameNullException;

	String updateMedicationToDisease(int diseaseId, Set<Medicine> medicationSet)
			throws DiseaseNotFoundException, EmptyMedicineSetException;

	String updateMedicationToDisease(int diseaseId, Medicine medicine)
			throws DiseaseNotFoundException, MedicineNotFoundException;

	String deleteDisease(int diseaseId) throws DiseaseNotFoundException;

	String deleteDisease(String diseaseName) throws DiseaseNotFoundException;

	List<Disease> getAllDiseases();

	Disease getADiseaseById(int diseaseId) throws DiseaseNotFoundException;

	Set<Medicine> getADiseaseMedicationById(int diseaseId) throws DiseaseNotFoundException;

	Set<Medicine> getADiseaseMedicationByDiseaseName(String diseaseName) throws DiseaseNotFoundException;

	List<Disease> getAllDiseasesByName(String name);

}
