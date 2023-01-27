package de.io.pharmacy_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.io.pharmacy_management_system.model.Patient;

@Repository 
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	

}
