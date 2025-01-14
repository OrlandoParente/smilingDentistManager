package sdms.service;

import java.util.List;

import sdms.model.Treatment;

public interface TreatmentServiceInterface {
	
	public Treatment getTreatmentById( long id );
	public List<Treatment> getTreatments();
	public List<Treatment> getTreatmentsByCustomerId( long idCustomer );
	public List<Treatment> getTreatmentsByBillNumber( String billNumber );
	
	public void postTreatment( Treatment treatment );
	
	public void putTreatment(Treatment treatment);
	
	public void deleteTreatmentById( long id );
	
	
	

}
