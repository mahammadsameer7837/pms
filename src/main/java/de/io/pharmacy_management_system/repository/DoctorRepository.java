package de.io.pharmacy_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.io.pharmacy_management_system.model.Doctor;

@Repository 
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	
	public Doctor findByDoctorIdT(String doctorIdT);

//	@Modifying
//	@Query(value="delete from doctor where doctor_idt= ?1",nativeQuery=true)
//	public void deleteByDoctorIdT(String doctorIdT);

}
