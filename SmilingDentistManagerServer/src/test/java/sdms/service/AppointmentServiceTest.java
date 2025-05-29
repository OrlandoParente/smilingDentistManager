package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.Appointment;
import sdms.model.Customer;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;

/*
 * 	// Gestione Appuntamenti --------------------------------
	public Appointment getAppointmentById( long id );
	
	public List<Appointment> getAppointments();
	
	public List<Appointment> getAppointmentsByCustomerId( long customerId );
	
	public List<Appointment> getAppointmentsByDoctorId( long doctorId );
	
	public List<String> getInvoiceNumbersByCustomerId( long customerId );

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
	
	// PATCH REQUESTS -----------------------------
	public void updateTeethList( long id, String teeth );
	// --------------------------------------------
	
	public void deleteAppointmentById( long id );
	// ------------------------------------------------------
 */

class AppointmentServiceTest {

	@Mock
	AppointmentRepository repository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	AppointmentService appointmentService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
//	public Appointment getAppointmentById( long id );
	
	@Test
	public void testGetAppointmentById() {
		
		// simulate database --------------------------
		Appointment appointment = new Appointment();
		
		Long id = 1L;
		appointment.setId(id);
		
		when( repository.findById( id ) ).thenReturn(  Optional.of(appointment) );
		// --------------------------------------------
		
		
		// test
		Appointment result = appointmentService.getAppointmentById(id);
		
		// check result ----
		assertNotNull(result);
		assertEquals(id, result.getId());
		// -----------------
	}
	
	// public List<Appointment> getAppointments();
	
	@Test
	public void testGetAppoitment() {
		
		// simulate database --------------------------
		List<Appointment> appointments = new ArrayList<>();
		
		Appointment app1 = new Appointment();
		Long id1 = 1L;
		app1.setId(id1);
		
		Appointment app2 = new Appointment();
		Long id2 = 2L;
		app1.setId(id2);
		
		appointments.add(app1);
		appointments.add(app2);
		
		when( repository.findAll() ).thenReturn(appointments);
		// --------------------------------------------
		
		// test
		List<Appointment> result = appointmentService.getAppointments();
		
		// check ----
		assertNotNull(result);
		assertEquals( appointments.size(), result.size() );

		for( int i = 0; i < appointments.size(); i ++ ) {
			assertEquals(appointments.get(i).getId(), result.get(i).getId() );
		}
		// ----------
	}
	
//	public List<Appointment> getAppointmentsByCustomerId( long customerId );
	
	@Test
	public void testGetAppointmentsByCustomerId() {
		
		// simulate database --------------------------
		Customer customer = new Customer();
		Long customerId = 3L;
		customer.setId(customerId);
			
		List<Appointment> appointments = new ArrayList<>();
		
		Appointment app1 = new Appointment();
		Long id1 = 1L;
		app1.setId(id1);
		app1.setCustomer(customer);
		
		
		Appointment app2 = new Appointment();
		Long id2 = 2L;
		app2.setId(id2);
		app2.setCustomer(customer);
		
		Appointment app3 = new Appointment();
		Long id3 = 5L;
		app3.setId(id3);
		app3.setCustomer( customer );
		
		appointments.add(app1);
		appointments.add(app2);
		appointments.add(app3);
		
		
		when( customerRepository.findById(customerId) ).thenReturn( Optional.of(customer) );
		when( repository.findByCustomer(customer) ).thenReturn(appointments);
		
		// --------------------------------------------
		
		// test
		List<Appointment> result = appointmentService.getAppointmentsByCustomerId( customerId );
		
						
		// check ----
		assertNotNull(result);
		assertEquals( appointments.size(), result.size() );

		for( int i = 0; i < appointments.size(); i ++ ) {
			assertEquals(appointments.get(i).getId(), result.get(i).getId() );
		}
		// ----------
				
	}
	
//	public List<Appointment> getAppointmentsByDoctorId( long doctorId );
	
//	public List<String> getInvoiceNumbersByCustomerId( long customerId );

//	public void postAppointment( Appointment appointment );
	// per registrare un appuntamento ancora non svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String note) throws SQLException;
	
	// per registrare un appuntamento già svolto
	// boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, String bill_number, String note) throws SQLException;
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	//boolean postAppointment( String date, String time, String id_customer, String id_doctor, String id_treatment, 
	//		int is_done, String bill_number, String note);
	
	// set is_done = 1
//	public void putSetAppointmentDoneById( long id );
	
	// set is_done = 0
//	public void putUnsetAppointmentDoneById( long id );

//	public void putAppointment( Appointment appointment );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentBillNumberById( long id, String invoiceNumber );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentNoteById( long id , String notes );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentTreatmentById( long id, long idTreatment );
	
	// PATCH REQUESTS -----------------------------
//	public void updateTeethList( long id, String teeth );
	// --------------------------------------------
	
//	public void deleteAppointmentById( long id );
	// ------------------------------------------------------
}
