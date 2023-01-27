package de.io.pharmacy_management_system.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.io.pharmacy_management_system.exceptions.DiseaseAllreadyFoundException;
import de.io.pharmacy_management_system.exceptions.DiseaseNameNullException;
import de.io.pharmacy_management_system.exceptions.DiseaseNotFoundException;
import de.io.pharmacy_management_system.exceptions.EmptyMedicineSetException;
import de.io.pharmacy_management_system.exceptions.MedicineNotFoundException;
import de.io.pharmacy_management_system.model.Disease;
import de.io.pharmacy_management_system.model.Medicine;
import de.io.pharmacy_management_system.repository.DiseaseRepository;
import de.io.pharmacy_management_system.repository.MedicineRepository;
import de.io.pharmacy_management_system.service.DiseaseService;

@Service
public class DiseaseServicesImpl implements DiseaseService {

	@Autowired
	DiseaseRepository diseaseRepository;

	@Autowired
	MedicineRepository medicineRepository;

	@Autowired
	MedicineServiceImpl medicineServices;

	// ADD A NEW DISEASE
	@Override
	public Disease addDisease(Disease disease) throws DiseaseAllreadyFoundException {
		if (diseaseRepository.existsByDiseaseName(disease.getDiseaseName())) {
			throw new DiseaseAllreadyFoundException();
		}
		return diseaseRepository.save(disease);
	}

	// UPDATE THE DISEASE Name
	@Override
	public Disease updateDiseaseName(int diseaseId, String diseaseName)
			throws DiseaseAllreadyFoundException, DiseaseNotFoundException, DiseaseNameNullException {
		Disease disease;
		try {
			disease = diseaseRepository.findById(diseaseId).get();
		} catch (Exception ex) {
			throw new DiseaseNotFoundException();
		}
		if (diseaseRepository.existsByDiseaseName(diseaseName)) {
			throw new DiseaseAllreadyFoundException();
		}

		disease.setDiseaseName(diseaseName);
		return diseaseRepository.save(disease);
	}

	// UPDATE MEDICATIONS REQUIRED FOR THE DISEASE AS A SET
	@Override
	public String updateMedicationToDisease(int diseaseId, Set<Medicine> medicationSet)
			throws DiseaseNotFoundException, EmptyMedicineSetException {

		Disease disease;
		String message = "";

		if (medicationSet != null) {
			// checking whether Disease exists with given Id

			try {
				disease = diseaseRepository.findById(diseaseId).get();
			} catch (Exception ex) {
				throw new DiseaseNotFoundException(); // throws Medicine not found exception when get() method
														// Throws:NoSuchElementException - if no value is present
			}

			// Code to navigate through all medicines present and check
			// whether the given medicines in the set are present already then add to
			// Medication set of disease

			boolean noMedicines = false; // pointer to know whether medicines list is empty

			for (Medicine medicineCheck : medicineServices.getAllMedicines()) {
				noMedicines = true;
				boolean exits = true; // pointer to track whether medicine Id exits or not

				for (Medicine medicineGiven : medicationSet) {

					exits = false;
					if (medicineCheck.getMedicineId() == medicineGiven.getMedicineId()) {
						exits = true;
						disease.getMedicationSuggested().add(medicineGiven);
						medicationSet.remove(medicineGiven);
					}
				}
				// If any of medicines don't exits then this message
				// will be added to reply and all other medicines present will be added as usual

				if (!exits) {

					message = "Those Medicines Exists all are ";
				}
			}

			// Medicines don't even exists just return no medicines exists

			if (!noMedicines) {
				return "Currently no medicnes are available";
			}
			// saving the disease after making changes
			diseaseRepository.save(disease);
		} else {
			throw new EmptyMedicineSetException();
		}

		return message + "Successfully added";
	}

	// UPDATE MEDICATIONS REQUIRED FOR THE DISEASE A SINGLE MEDICINE
	@Override
	public String updateMedicationToDisease(int diseaseId, Medicine medicine)
			throws DiseaseNotFoundException, MedicineNotFoundException {

		Disease disease = null;

		if (medicine != null) {
			try {
				disease = diseaseRepository.findById(diseaseId).get();
			} catch (Exception ex) {
				throw new DiseaseNotFoundException(); // throws Medicine not found exception when get() method
														// Throws:NoSuchElementException - if no value is present
			}
			disease.getMedicationSuggested().add(medicineServices.getMedicineById(medicine.getMedicineId()));

		}
		diseaseRepository.save(disease);
		return "Successfully updated";
	}

	// DELETE A DISEASE by Id
	@Override
	public String deleteDisease(int diseaseId) throws DiseaseNotFoundException {
		if (diseaseRepository.existsById(diseaseId)) {
			diseaseRepository.deleteById(diseaseId);
			return "Disease with id: " + diseaseId + " is deleted Successfully";
		}
		throw new DiseaseNotFoundException();
	}

	// DELETE A DISEASE by Name
	@Override
	public String deleteDisease(String diseaseName) throws DiseaseNotFoundException {
		if (diseaseRepository.existsByDiseaseName(diseaseName.toLowerCase())) {
			diseaseRepository.deleteAllByDiseaseName(diseaseName.toLowerCase());
			return "Disease with name: " + diseaseName + " is deleted Successfully";
		}
		throw new DiseaseNotFoundException();
	}

	// GET ALL DISEASES
	@Override
	public List<Disease> getAllDiseases() {
		return diseaseRepository.findAll();
	}

	// GET A DISEASE BY NAME
	@Override
	public List<Disease> getAllDiseasesByName(String name) {
		return diseaseRepository.findAllByDiseaseName(name.toLowerCase());
	}

	// GET A DISEASE BY ID
	@Override
	public Disease getADiseaseById(int diseaseId) throws DiseaseNotFoundException {
		if (!diseaseRepository.existsById(diseaseId)) {
			throw new DiseaseNotFoundException();
		}
		return diseaseRepository.findById(diseaseId).get();
	}

	// GET A DISEASE MEDIACTION BY ID
	@Override
	public Set<Medicine> getADiseaseMedicationById(int diseaseId) throws DiseaseNotFoundException {
		if (!diseaseRepository.existsById(diseaseId)) {
			throw new DiseaseNotFoundException();
		}
		return diseaseRepository.findById(diseaseId).get().getMedicationSuggested();
	}

	// GET A DISEASE MEDIACTION BY NAME
	@Override
	public Set<Medicine> getADiseaseMedicationByDiseaseName(String diseaseName) throws DiseaseNotFoundException {

		if (!diseaseRepository.existsByDiseaseName(diseaseName.toLowerCase())) {
			throw new DiseaseNotFoundException();
		}
		return diseaseRepository.findByDiseaseName(diseaseName.toLowerCase()).getMedicationSuggested();
	}

}
