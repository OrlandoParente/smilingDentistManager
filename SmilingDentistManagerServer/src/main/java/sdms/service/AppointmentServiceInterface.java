package sdms.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sdms.model.Appointment;

public interface AppointmentServiceInterface {
	
	// Gestione Appuntamenti --------------------------------
	List<Appointment> getAppointments();
	
	List<Appointment> getAppointmentsByCustomerId( long customerId );
	
	List<Appointment> getAppointmentsByDoctorId( long doctorId );

	// per registrare un appuntamento ancora non svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
			int is_done, String bill_number, String note);
	
	// set is_done = 1
	boolean putSetAppointmentDoneById( long id );
	
	// set is_done = 0
	boolean putUnsetAppointmentDoneById( long id );

	boolean putAppointmentBillNumberById( long id, String bill_number );
	
	boolean putAppointmentNoteById( long id , String note );
	
	boolean putAppointmentTreatmentById( long id, String id_treatment );
	
	boolean deleteAppointmentById( long id );
	// ------------------------------------------------------
	
}
