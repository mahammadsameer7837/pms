package de.io.pharmacy_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.io.pharmacy_management_system.model.Disease;

@Repository 
public interface DiseaseRepository extends JpaRepository<Disease,Integer>{

	 public Disease findByDiseaseName(String diseaseName);

	public void deleteAllByDiseaseName(String diseaseName);

	public boolean existsByDiseaseName(String diseaseName);

	public List<Disease> findAllByDiseaseName(String diseaseName);
	

}
