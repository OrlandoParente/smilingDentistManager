package sdms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Customer;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;

@Repository
public interface HasMedicalHistoryRepository extends JpaRepository<HasMedicalHistory, Long> {

	// CREATE
	
	// READ
	Optional <HasMedicalHistory> findById(Long id);
	
	List<HasMedicalHistory> findAll();
	List<HasMedicalHistory> findByMedicalHistory( MedicalHistory medicalHistory );
	List<HasMedicalHistory> findByCustomer(Customer customer);
	
	// UPDATE
	
	// DELETE
	
}
