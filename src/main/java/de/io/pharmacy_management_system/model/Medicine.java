package de.io.pharmacy_management_system.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.io.pharmacy_management_system.exceptions.ManufacturedCompanyNameCannotBeNullException;
import de.io.pharmacy_management_system.exceptions.MedicineNameCannotBeNullException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medicine {
	
	 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer medicineId;
	
	private String medicineName;
	private Date yearOfManufacture;
	private Date expiryDate;
	private Double cost;
	private String manufacturedCompanyName;
	
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) throws MedicineNameCannotBeNullException{
		try{
			this.medicineName = medicineName.toLowerCase();
		}
		catch(Exception ex) {
			throw new MedicineNameCannotBeNullException();
		}
	}
	public Date getYearOfManufacture() {
		return yearOfManufacture;
	}
	public void setYearOfManufacture(Date yearOfManufacture) throws ParseException {
		//SimpleDateFormat sdformat = new SimpleDateFormat("yyy-MM-dd");
		//this.yearOfManufacture = sdformat.parse(yearOfManufacture);
		this.yearOfManufacture = yearOfManufacture;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) throws ParseException {
		//SimpleDateFormat sdformat = new SimpleDateFormat("yyy-MM-dd");
		this.expiryDate = expiryDate;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getManufacturedCompanyName() {
		return manufacturedCompanyName;
	}
	public void setManufacturedCompanyName(String manufacturedCompanyName) throws ManufacturedCompanyNameCannotBeNullException {
		try{
			this.manufacturedCompanyName = manufacturedCompanyName.toLowerCase();
	}
	catch(Exception ex) {
		throw new ManufacturedCompanyNameCannotBeNullException();
	}
	}
	
	

}
