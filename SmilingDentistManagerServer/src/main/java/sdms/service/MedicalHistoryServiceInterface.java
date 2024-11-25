package sdms.service;

import java.util.List;

import sdms.model.MedicalHistory;

public interface MedicalHistoryServiceInterface {

//	// Gestione Medical History (Anamnesi) -----------------
//	ArrayList<MedicalHistory> getMedicalsHistoryByCustomer( String id_customer ) throws SQLException;
	public List<MedicalHistory> getMedicalsHistoryByCustomerId( long idCustomer );
	
//	ArrayList<MedicalHistory> getMedicalHistoryById( String id ) throws SQLException;
	public MedicalHistory getMedicalHistoryById( Long id );
		
//	// type = "generale" o "odontoiatrica" 
//	boolean postMedicalHistory( String id_customer, String type , String name ) throws SQLException;
//	boolean postMedicalHistory( String id_customer, String type , String name, String descriprion ) throws SQLException;
	public void postMedicalHistory( MedicalHistory medicalHistory );

	public void putMedicalHistory( MedicalHistory medicalHistory );
	
//	boolean deleteMedicalHistoryById( String id ) throws SQLException;
	public void deleteMedicalHistoryById( Long id );
//	// ------------------------------------------------------

	
	
}
