package de.io.pharmacy_management_system.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Doctor {
	
	/*2. Maintain a classification named 
	Doctor with properties: doctorName, doctorId, 
	designation, address, specialization/expertise, 
	patientsAssigned, address*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int doctorId;
	private String doctorIdT;
	private String doctorName;
	
	private String designation;
	
	private String address;
	
	private String specialization;
	
	@ManyToMany(targetEntity=Patient.class)
	private Set<Patient> patientsAssigned=new HashSet<>();

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Set<Patient> getPatientsAssigned() {
		return patientsAssigned;
	}

	public void setPatientsAssigned(Set<Patient> patientsAssigned) {
		this.patientsAssigned = patientsAssigned;
	}

	public String getDoctorIdT() {
		return doctorIdT;
	}

	public void setDoctorIdT(String doctorIdT) {
		this.doctorIdT = doctorIdT;
	}
	
	

}
