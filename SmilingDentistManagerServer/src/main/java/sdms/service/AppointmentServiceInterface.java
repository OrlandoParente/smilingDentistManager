package sdms.service;

import java.util.List;

import sdms.model.Appointment;

public interface AppointmentServiceInterface {
	
	// Gestione Appuntamenti --------------------------------
	public Appointment getAppointmentById( long id );
	
	public List<Appointment> getAppointments();
	
	public List<Appointment> getAppointmentsByCustomerId( long customerId );
	
	public List<Appointment> getAppointmentsByDoctorId( long doctorId );

	public void postAppointment( Appointment appointment );
	// per registrare un appuntamento ancora non svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	//boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
	//		int is_done, String bill_number, String note);
	
	// set is_done = 1
	public void putSetAppointmentDoneById( long id );
	
	// set is_done = 0
	public void putUnsetAppointmentDoneById( long id );

	public void putAppointment( Appointment appointment );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	public void putAppointmentBillNumberById( long id, String invoiceNumber );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	public void putAppointmentNoteById( long id , String notes );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	public void putAppointmentTreatmentById( long id, long idTreatment );
	
	public void deleteAppointmentById( long id );
	// ------------------------------------------------------
	
}
