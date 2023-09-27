package sdms.controller.api;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentRestController {

	public AppointmentRestController() {
	}
	
	// Gestione Appuntamenti --------------------------------
	// ResultSet getAppointments() throws SQLException;
	
	// ResultSet getAppointmentsByCustomerId( String id_customer ) throws SQLException;
	
	// ResultSet getAppointmentsByDoctorId( String id_doctor ) throws SQLException;
	
	// per registrare un appuntamento ancora non svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
		//	int is_done, String bill_number, String note) throws SQLException;
	
	// set is_done = 1
	// boolean putSetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
	
	// set is_done = 0
	// boolean putUnsetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
	
	// boolean putAppointmentBillNumberById( String date, String time, String id_customer, String bill_number ) throws SQLException;
	
	// boolean putAppointmentNoteById( String date, String time, String id_customer, String note ) throws SQLException;
	
	// boolean putAppointmentTreatmentById( String date, String time, String id_customer, String id_treatment ) throws SQLException;
	
	// boolean deleteAppointmentById( String date, String time, String id_customer ) throws SQLException;
	// ------------------------------------------------------
}
