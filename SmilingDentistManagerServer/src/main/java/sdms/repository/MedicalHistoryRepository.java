package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Customer;
import sdms.model.MedicalHistory;
import java.util.List;


@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {


	// CREATE ---------------------------------------------------------------
//	// type = "generale" o "odontoiatrica" 
//	boolean postMedicalHistory( String id_customer, String type , String name ) throws SQLException;
//	boolean postMedicalHistory( String id_customer, String type , String name, String descriprion ) throws SQLException;
	
	// UPDATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
//	ResultSet getMedicalsHistoryByCustomer( String id_customer ) throws SQLException;
	public List<MedicalHistory> findByCustomer(Customer customer);
//	ResultSet getMedicalHistoryById( String id ) throws SQLException;

	
	// DELETE ---------------------------------------------------------------
//	boolean deleteMedicalHistoryById( String id ) throws SQLException;

}
