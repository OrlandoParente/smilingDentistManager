package sdms.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

// GET ottieni dati; DELETE elimina dati; POST inserisci dati; PUT modifica dati

@Service
public interface DbManagerInterface {
	
	void closeConnection() throws SQLException;
	
	// Gestione clienti -------------------------------------
	ResultSet getCustomers() throws SQLException;
	
	ResultSet getCustomerById( String id ) throws SQLException;
	
	ResultSet getCustomersByPartialKeyWordOverAllFields( String key_word ) throws SQLException;
	
	// inserimento dati essenziali del cliente
	boolean postCustomer( String name , String surname ,String phone_number ) throws SQLException;
	
	boolean postCustomer( String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , 
			String birth_city_province, String birth_date,String residence_street, String house_number,
			String residence_city , String residence_city_cap, String residence_province, String phone_number , 
			String phone_number_2, String e_mail ) throws SQLException;
	
	boolean putCustomerById( String id, String tax_id_code /* codice fiscale*/, String name , String surname ,
			String birth_city , String birth_city_province, String birth_date,String residence_street, String house_number,
			String residence_city , String residence_city_cap, String residence_province, String phone_number , 
			String phone_number_2, String e_mail ) throws SQLException;
	
	
	boolean deleteCustomerById( String id ) throws SQLException;
	
	// ------------------------------------------------------
	
	// Gestione Medical History (Anamnesi) -----------------
	ResultSet getMedicalsHistoryByCustomer( String id_customer ) throws SQLException;
	
	ResultSet getMedicalHistoryById( String id ) throws SQLException;
	
	// type = "generale" o "odontoiatrica" 
	boolean postMedicalHistory( String id_customer, String type , String name ) throws SQLException;
	boolean postMedicalHistory( String id_customer, String type , String name, String descriprion ) throws SQLException;

	boolean deleteMedicalHistoryById( String id ) throws SQLException;
	// ------------------------------------------------------
	
	// Gestione Appuntamenti --------------------------------
	ResultSet getAppointments() throws SQLException;
	
	ResultSet getAppointmentsByCustomerId( String id_customer ) throws SQLException;
	
	ResultSet getAppointmentsByDoctorId( String id_doctor ) throws SQLException;
	
	// per registrare un appuntamento ancora non svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
			int is_done, String bill_number, String note) throws SQLException;
	
	// set is_done = 1
	boolean putSetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
	
	// set is_done = 0
	boolean putUnsetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
	
	boolean putAppointmentBillNumberById( String date, String time, String id_customer, String bill_number ) throws SQLException;
	
	boolean putAppointmentNoteById( String date, String time, String id_customer, String note ) throws SQLException;
	
	boolean putAppointmentTreatmentById( String date, String time, String id_customer, String id_treatment ) throws SQLException;
	
	boolean deleteAppointmentById( String date, String time, String id_customer ) throws SQLException;
	// ------------------------------------------------------
	
	// Gestione trattamenti ---------------------------------
	ResultSet getTreatmentById( String id ) throws SQLException;
	
	ResultSet getTreatmentsByCustomer( String id_customer ) throws SQLException;
	
	// restituisce i trattamenti associati ad una fattura
	ResultSet getTreatmentsByBillNumber( String bill_number ) throws SQLException;
	
	boolean postTreatment( String name, String cost ) throws SQLException;
	boolean postTreatment( String name, String description, String cost ) throws SQLException;
	
	boolean deleteTreatmentById( String id ) throws SQLException;
	
	// ------------------------------------------------------
	
	
	// Gestione Dipendenti (Employee) -----------------------
	ResultSet getEmployees() throws SQLException;
	
	ResultSet getEmployeesByName( String name ) throws SQLException;
	
	ResultSet getEmployeesBySurname( String surname ) throws SQLException;
	
	ResultSet getEmployeesByProfessionalRoleName( String professiona_role_name ) throws SQLException;
	
	ResultSet getEmployeesByPartialKeyWordOverAllFields( String key_word ) throws SQLException;
	
	ResultSet getEmployeeById( String id ) throws SQLException;
	
	// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
	boolean postEmployee( String name, String surname, String title, String phone_number ) throws SQLException;
	boolean postEmployee( String name, String surname, String title, String birth_date, String phone_number, 
						String phone_number_2, String e_mail ) throws SQLException;
	
	boolean putEmployeeById( String id, String name, String surname, String title, String birth_date, String phone_number, 
						String phone_number_2, String e_mail ) throws SQLException;
	
	
	boolean deleteEmployeeById( String id ) throws SQLException;
	// ------------------------------------------------------
	
	// Gestione Professional Role ---------------------------
	ResultSet getProfessionalRoles() throws SQLException;
	
	boolean postProfessionalRole( String name ) throws SQLException;
	boolean postProfessionalRole( String name, String description ) throws SQLException;
	
	boolean putProfessionalRoleById(String id, String name, String description ) throws SQLException;
	
	boolean deleteProfessionalRoleById( String id ) throws SQLException;	
	// ------------------------------------------------------
	
	// Gestione Has Professional Role -----------------------
	boolean postLinkEmployeeToProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	
	boolean deleteLinkEmployeeWithProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	// ------------------------------------------------------
	

}
