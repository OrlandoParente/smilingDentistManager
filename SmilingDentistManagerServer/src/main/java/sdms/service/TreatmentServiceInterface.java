package sdms.service;

import java.util.List;

import sdms.model.Treatment;

public interface TreatmentServiceInterface {
	
	
//	// Treatment manage ----------------------------------------
//	Treatment getTreatmentById( String id ) throws SQLException;
	public Treatment getTreatmentById( long id );
//	
//	ArrayList<Treatment> getTreatmentsByCustomerId( String id_customer ) throws SQLException;
	public List<Treatment> getTreatmentsByCustomerId( long idCustomer );
//	
//	// return the treatments associated with an invoice (bill)
//	ArrayList<Treatment> getTreatmentsByBillNumber( String bill_number ) throws SQLException;
	public List<Treatment> getTreatmentsByBillNumber( String billNumber );
	
//	boolean postTreatment( String name, String cost ) throws SQLException;
//	boolean postTreatment( String name, String description, String cost ) throws SQLException;
	public void postTreatment( Treatment treatment );
	
	public void putTreatment(Treatment treatment);
	
//	boolean deleteTreatmentById( String id ) throws SQLException;
	public void deleteTreatmentById( long id );
	
//	// ------------------------------------------------------
	
	

}
