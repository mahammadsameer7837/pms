package de.io.pharmacy_management_system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Patient {
	
	/*Maintain a classification named Patient having properties: 
		patientName, patientId, doctorId, dateOfAdmission, dateOfDischarge, 
		medicationsList, medicationCount, billAmountPaid, admittedReason, diseaseId, 
		address.*/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int patientId;

	private String patientName;
	
	private String admittedReason;
	
	@ManyToMany(targetEntity=Medicine.class)
	 
	private Set<Medicine> medicationsList = new HashSet<>();
	
	private String diseaseName;
	
	 
	private int doctorId;
	
	private Date dateOfAdmission = new Date();
	
	private Date dateOfDischarge;
	
	private int medicationCount;
	
	private Double billAmountPaid;
	
	private String address;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAdmittedReason() {
		return admittedReason;
	}

	public void setAdmittedReason(String admittedReason) {
		this.admittedReason = admittedReason;
	}

	public Set<Medicine> getMedicationsList() {
		return medicationsList;
	}

	public void setMedicationsList(Set<Medicine> medicationsList) {
		this.medicationsList = medicationsList;
	}
	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Date getDateOfDischarge() {
		return dateOfDischarge;
	}

	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}

	public int getMedicationCount() {
		return medicationCount;
	}

	public void setMedicationCount(int medicationCount) {
		this.medicationCount = medicationCount;
	}

	public Double getBillAmountPaid() {
		return billAmountPaid;
	}

	public void setBillAmountPaid(Double billAmountPaid) {
		this.billAmountPaid = billAmountPaid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	

}
