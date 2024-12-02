package sdms.service.old;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import sdms.model.*;

@Service
public interface ServicesInterface {

	// Generale
	int getMaxIdFromTable( String table ) throws SQLException;
	
	// Gestione clienti -------------------------------------
	ArrayList<Customer> getCustomers() throws SQLException;
	
	ArrayList<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word ) throws SQLException;
	
	Customer getCustomerById( Integer id ) throws SQLException;
	
	// inserimento dati essenziali del cliente
	boolean postCustomer( String name , String surname ,String phone_number ) throws SQLException;
	
	boolean postCustomer( String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , 
			String birth_city_province, String birth_date, String residence_street, String house_number, 
			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
			String phone_number_2, String e_mail )
		    throws SQLException;
	
	boolean putCustomerById( String id, String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , 
			String birth_city_province, String birth_date, String residence_street, String house_number, 
			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
			String phone_number_2, String e_mail )
		    throws SQLException;
	
	boolean deleteCustomerById( String id ) throws SQLException;
	
	// ------------------------------------------------------
	
	// Gestione Medical History (Anamnesi) -----------------
	ArrayList<MedicalHistory> getMedicalsHistoryByCustomer( String id_customer ) throws SQLException;
	
	ArrayList<MedicalHistory> getMedicalHistoryById( String id ) throws SQLException;
	
	// type = "generale" o "odontoiatrica" 
	boolean postMedicalHistory( String id_customer, String type , String name ) throws SQLException;
	boolean postMedicalHistory( String id_customer, String type , String name, String descriprion ) throws SQLException;

	boolean deleteMedicalHistoryById( String id ) throws SQLException;
	// ------------------------------------------------------

	// Gestione Appuntamenti --------------------------------
	ArrayList<Appointment> getAppointments() throws SQLException;
	
	ArrayList<Appointment> getAppointmentsByCustomerId( String id_customer ) throws SQLException;
	
	ArrayList<Appointment> getAppointmentsByDoctorId( String id_doctor ) throws SQLException;
	
	// per registrare un appuntamento ancora non svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
			int is_done, String bill_number, String note) throws SQLException;
	
	// set is_done = 1
	boolean putSetAppointmentDoneById( long id ) throws SQLException;
	
	// set is_done = 0
	boolean putUnsetAppointmentDoneById( long id ) throws SQLException;
	
	boolean putAppointmentBillNumberById( long id, String bill_number ) throws SQLException;
	
	boolean putAppointmentNoteById( long id , String note ) throws SQLException;
	
	boolean putAppointmentTreatmentById( long id, String id_treatment ) throws SQLException;
	
	boolean deleteAppointmentById( long id ) throws SQLException;
	// ------------------------------------------------------
	
	// Gestione trattamenti ---------------------------------
	Treatment getTreatmentById( String id ) throws SQLException;
	
	ArrayList<Treatment> getTreatmentsByCustomerId( String id_customer ) throws SQLException;
	
	// restituisce i trattamenti associati ad una fattura
	ArrayList<Treatment> getTreatmentsByBillNumber( String bill_number ) throws SQLException;
	
	boolean postTreatment( String name, String cost ) throws SQLException;
	boolean postTreatment( String name, String description, String cost ) throws SQLException;
	
	boolean deleteTreatmentById( String id ) throws SQLException;

	// ------------------------------------------------------
	
	
	// Gestione Dipendenti (Employee) -----------------------
	ArrayList<Employee> getEmployees() throws SQLException;
	
	ArrayList<Employee> getEmployeesByName( String name ) throws SQLException;
	
	ArrayList<Employee> getEmployeesBySurname( String surname ) throws SQLException;
	
	ArrayList<Employee> getEmployeesByProfessionalRoleName( String professiona_role_name ) throws SQLException;
	
	ArrayList<Employee> getEmployeesByPartialKeyWordOverAllFields( String key_word ) throws SQLException;
	
	Employee getEmployeeById( String id ) throws SQLException;
	
	// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
	boolean postEmployee( String name, String surname, String title, String phone_number ) throws SQLException;
	boolean postEmployee( String name, String surname, String title, String birth_date, String phone_number, String phone_number_2, String e_mail ) throws SQLException;
	
	boolean putEmployeeById( String id, String name, String surname, String title, String birth_date, 
							String phone_number, String phone_number_2, String e_mail ) throws SQLException;

	
	boolean deleteEmployeeById( String id ) throws SQLException;
	
	// ------------------------------------------------------
	
	// Gestione Professional Role ---------------------------
	ArrayList<ProfessionalRole> getProfessionalRoles() throws SQLException;
	
	ArrayList<ProfessionalRole> getProfessionalRolesAssociatedToIdEmployee( String id_employee ) throws SQLException;
	
	
	boolean postProfessionalRole( String name ) throws SQLException;
	boolean postProfessionalRole( String name, String description ) throws SQLException;
	
	boolean putProfessionalRoleById( String id, String name, String description ) throws SQLException;
	
	boolean deleteProfessionalRoleById( String id ) throws SQLException;	
	// ------------------------------------------------------
	
	// Gestione Has Professional Role -----------------------
	boolean postLinkEmployeeToProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	
	boolean deleteLinkEmployeeWithProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	// ------------------------------------------------------
		
	
}