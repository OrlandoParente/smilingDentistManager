package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Treatment;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

	// CREATE ---------------------------------------------------------------
//	boolean postTreatment( String name, String cost ) throws SQLException;
//	boolean postTreatment( String name, String description, String cost ) throws SQLException;
	
	// UPDATE ---------------------------------------------------------------
//	// restituisce i trattamenti associati ad una fattura
//	ResultSet getTreatmentsByBillNumber( String bill_number ) throws SQLException;
	
	// READ -----------------------------------------------------------------
//	ResultSet getTreatmentById( String id ) throws SQLException;
//	ResultSet getTreatmentsByCustomerId( String id_customer ) throws SQLException;
	
	
	// DELETE ---------------------------------------------------------------
//	boolean deleteTreatmentById( String id ) throws SQLException;
	
}
