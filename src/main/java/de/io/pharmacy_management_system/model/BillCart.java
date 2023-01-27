package de.io.pharmacy_management_system.model;

import java.util.HashSet;
import java.util.Set;

public class BillCart {
	
	private int patientId;
	private Set<Medicine> medicines = new HashSet<>();
	private double bill;
	
	
	public BillCart(Set<Medicine> medicines, int patientId, double bill) {
		super();
		this.medicines = medicines;
		this.patientId = patientId;
		this.bill = bill;
	}
	
	public BillCart() {
		// TODO Auto-generated constructor stub
	}

	public Set<Medicine> getMedicine() {
		return medicines;
	}
	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	
	

}
