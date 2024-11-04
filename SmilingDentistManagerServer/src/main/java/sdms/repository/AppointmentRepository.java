package sdms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Appointment;
import sdms.model.Customer;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	// Create
	
	// Update
	
	// Read
	
	// Delete
	
//	// Gestione Appuntamenti --------------------------------
//		ResultSet getAppointments() throws SQLException;
//		
//		List<Appointment> findByIdCustomer(long idCustomer);
//		
//		ResultSet getAppointmentsByDoctorId( String id_doctor ) throws SQLException;
//		
//		// per registrare un appuntamento ancora non svolto
//		boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
//		
//		// per registrare un appuntamento già svolto
//		boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
//		
//		// per registrare un appuntamento potendo scegliere i valori di tutti i campi
//		boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
//				int is_done, String bill_number, String note) throws SQLException;
//		
//		// set is_done = 1
////		boolean putSetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
//		boolean putSetAppointmentDoneById( long id ) throws SQLException;
//		
//		// set is_done = 0
////		boolean putUnsetAppointmentDoneById( String date, String time, String id_customer ) throws SQLException;
//		boolean putUnsetAppointmentDoneById( long id ) throws SQLException;
//		
////		boolean putAppointmentBillNumberById( String date, String time, String id_customer, String bill_number ) throws SQLException;
//		boolean putAppointmentBillNumberById( long id, String bill_number ) throws SQLException;
//		
////		boolean putAppointmentNoteById( String date, String time, String id_customer, String note ) throws SQLException;
//		boolean putAppointmentNoteById( long id, String note ) throws SQLException;
//		
////		boolean putAppointmentTreatmentById( String date, String time, String id_customer, String id_treatment ) throws SQLException;
//		boolean putAppointmentTreatmentById( long id, String id_treatment ) throws SQLException;
//		
////		boolean deleteAppointmentById( String date, String time, String id_customer ) throws SQLException;
//		boolean deleteAppointmentById( long id ) throws SQLException;
//		
//		// ------------------------------------------------------
//		
	
}
