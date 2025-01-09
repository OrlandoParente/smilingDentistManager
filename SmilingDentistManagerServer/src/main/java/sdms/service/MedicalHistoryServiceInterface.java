package sdms.service;

import java.util.List;

import sdms.model.MedicalHistory;

public interface MedicalHistoryServiceInterface {


	// CREATE
	public void postMedicalHistory( MedicalHistory medicalHistory );
	
	// READ
	public List<MedicalHistory> getMedicalsHistoryByCustomerId( long idCustomer );
	public MedicalHistory getMedicalHistoryById( Long id );
	
	public List<String> getMedicalHistoryTypes();
	public List<String> getMedicalHistoryCategories();
	
	public List<MedicalHistory> getMedicalsHistoryByType( String type );
	public List<MedicalHistory> getMedicalsHistoryByCategory( String category );
	public List<MedicalHistory> getMedicalsHistoryByTypeAndCategory( String type, String category );
	
	// UPDATE
	public void putMedicalHistory( MedicalHistory medicalHistory );
	
	// DELETE
	public void deleteMedicalHistoryById( Long id );
	
	
}
