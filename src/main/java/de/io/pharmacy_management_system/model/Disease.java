package de.io.pharmacy_management_system.model;

import java.util.List;
import java.util.Set;

import de.io.pharmacy_management_system.exceptions.DiseaseNameNullException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Disease {
	
	/*3. Maintain a classification named Disease with properties: 
	 * diseaseName, diseaseId, medicationSuggested*/
	
	private String diseaseName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int diseaseId;
	
	@ManyToMany(targetEntity=Medicine.class)
	private Set<Medicine> medicationSuggested;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) throws DiseaseNameNullException {
		try {
		this.diseaseName = diseaseName.toLowerCase();
	}
		catch(Exception ex) {
			throw new DiseaseNameNullException();
		}
	}

	public int getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(int diseaseId) {
		this.diseaseId = diseaseId;
	}

	public Set<Medicine> getMedicationSuggested() {
		return medicationSuggested;
	}

	public void setMedicationSuggested(Set<Medicine> medicationSuggested) {
		this.medicationSuggested = medicationSuggested;
	}

	@Override
	public String toString() {
		return "Disease [diseaseName=" + diseaseName + ", diseaseId=" + diseaseId + ", medicationSuggested="
				+ medicationSuggested + "]";
	}
	
 
	

}
