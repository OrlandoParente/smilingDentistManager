package sdms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

// GET ottieni dati; DELETE elimina dati; POST inserisci dati; PUT modifica dati


public interface DbManagerInterface {
	
	// void getDbManager();
	
	void closeConnection() throws SQLException;
	
	// Gestione clienti -------------------------------------
	ResultSet getCustomers() throws SQLException;
	
	ResultSet getCustomerById( String id );
	
	// inserimento dati essenziali del cliente
	void postCustomer( String name , String surname ,String phone_number );
	
	void postCustomer( String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , String birth_city_province, String birth_date,
			String residence_street, String residence_city , String residence_province, String phone_number , String phone_number_2, String e_mail );
	
	void deleteCustomerById( String id );
	
	// ------------------------------------------------------
	
	// Gestione Medical History (Annamnesi) -----------------
	ResultSet getMedicalsHistoryByCustomer( String id_customer );
	
	ResultSet getMedicalHistoryById( String id );
	
	// type = "generale" o "odontoiatrica" 
	void postMedicalHistory( String id_customer, String type , String name );
	void postMedicalHistory( String id_customer, String type , String name, String descriprion );

	void deleteMedicalHistoryById( String id );
	// ------------------------------------------------------
	
	// Gestione Appuntamenti --------------------------------
	ResultSet getAppointments();
	
	ResultSet getAppointmentByCustomer( String customer );
	
	ResultSet getAppointmentByDoctor( String doctor );
	
	// per registrare un appuntamento ancora non svolto
	void postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note);
	
	// per registrare un appuntamento già svolto
	void postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note);
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	void postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, int is_done, String bill_number, String note);
	
	// set is_done = 1
	void putSetAppointmentDoneById( String date, String time, String id_customer );
	
	// set is_done = 0
	void putUnsetAppointmentDoneById( String date, String time, String id_customer );
	
	void putAppointmentBillNumberById( String date, String time, String id_customer, String billNumber );
	
	void putAppointmentNoteById( String date, String time, String id_customer, String note );
	
	void putAppointmentTreatmentById( String date, String time, String id_customer, String id_treatment );
	
	void deleteAppointmentById( String date, String time, String id_customer );
	// ------------------------------------------------------
	
	// Gestione trattamenti ---------------------------------
	ResultSet getTreatmentById( String id );
	
	ResultSet getTreatmentByCustomer( String id_customer );
	
	// restituisce i trattamenti associati ad una fattura
	ResultSet getTreatmentByBillNumber( String bill_number );
	
	void postTreatment( String name, String cost );
	void postTreatment( String name, String description, String cost );
	
	void deleteTreatmentById( String id );
	
	// ------------------------------------------------------
	
	
	// Gestione Dipendenti (Employee) -----------------------
	ResultSet getEmployees();
	
	ResultSet getEmployeesByName( String name );
	
	ResultSet getEmployeesBySurname( String surnname );
	
	ResultSet getEmployeesByProfessionalRoleName( String professiona_role_name );
	
	ResultSet getEmployeeById( String Id );
	
	// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
	void postEmployee( String name, String surname, String title, String phone_number );
	void postEmployee( String name, String surname, String title, String birth_date, String phone_number, String phone_number_2, String e_mail );
	
	void deleteEmployeeById( String id );
	// ------------------------------------------------------
	
	// Gestione Professional Role ---------------------------
	ResultSet getProfessionalRoles();
	
	void postProfessionalRole( String name );
	void postProfessionalRole( String name, String description );
	
	void deleteProfessionalRoleById( String id );	
	// ------------------------------------------------------
	
	// Gestione Has Professional Role -----------------------
	void postLinkEmployeeToProfessionalRole( String id_employee, String id_professional_rol );
	
	void deleteLinkEmployeeWithProfessionalRole( String id_employee, String id_professional_rol );
	// ------------------------------------------------------
	
	
	
	

}
