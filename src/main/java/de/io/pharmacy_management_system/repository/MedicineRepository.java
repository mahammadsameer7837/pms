package de.io.pharmacy_management_system.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.io.pharmacy_management_system.model.Medicine;

@Repository 
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
	
	Medicine findByMedicineName(String medicineName);
	Medicine findByYearOfManufacture(Date yearOfManufacture);
	Medicine findByExpiryDate(Date expiryDate);
	Medicine findByManufacturedCompanyName(String manufacturedCompanyName);
	List<Medicine> findAllByMedicineName(String medicineName);
	boolean existsByMedicineName(String medicineName);

}
