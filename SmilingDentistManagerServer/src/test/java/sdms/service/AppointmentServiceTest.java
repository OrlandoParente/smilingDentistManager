package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.TableGenerator;
import sdms.model.Appointment;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;

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
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock 
	AppointmentRepository appointmentRepository;
	
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
	
	@Test
	public void testGetAppointmentsByDoctorId() {
		
		// simulate database --------------------------
		Employee doctor = new Employee();
		Long doctorId = 3L;
		doctor.setId(doctorId);
			
		List<Appointment> appointments = new ArrayList<>();
		
		Appointment app1 = new Appointment();
		Long id1 = 1L;
		app1.setId(id1);
		app1.setDoctor(doctor);
		
		
		Appointment app2 = new Appointment();
		Long id2 = 2L;
		app2.setId(id2);
		app2.setDoctor(doctor);
		
		Appointment app3 = new Appointment();
		Long id3 = 5L;
		app3.setId(id3);
		app3.setDoctor(doctor);
		
		appointments.add(app1);
		appointments.add(app2);
		appointments.add(app3);
		
		
		when( employeeRepository.findById( doctorId ) ).thenReturn( Optional.of( doctor ) );
		when( repository.findByDoctor(doctor) ).thenReturn(appointments);
		
		// --------------------------------------------
		
		// test
		List<Appointment> result = appointmentService.getAppointmentsByDoctorId( doctorId );
		
						
		// check ----
		assertNotNull(result);
		assertEquals( appointments.size(), result.size() );

		for( int i = 0; i < appointments.size(); i ++ ) {
			assertEquals(appointments.get(i).getId(), result.get(i).getId() );
		}
		// ----------
		
	}
	
	// ############# <<<<<<<<<<<<<<<<<<<<<<<<<<<<----------------------------------------------------------------------------------
	// ############# <<<<<<<<<<<<<<<<<<<<<<<<<<<<----------------------------------------------------------------------------------
	
//	public List<String> getInvoiceNumbersByCustomerId( long customerId );
	
//	@Test
//	public void testGetInvoiceNumbersByCustomerId() {
//		
//		// simulate database --------------------------
//		Customer customer = new Customer();
//		
//		Long customerId = 1L;
//		customer.setId(customerId);
//		
//		int numOfDifferentInvoices = 2;
//		
//		Appointment appointment1 = new Appointment();
//		Long appId1 = 2L;
//		appointment1.setId(appId1);
//		String invoiceNumber1 = "AAAAAAAAA";
//		appointment1.setInvoiceNumber(invoiceNumber1);
//		
//		Appointment appointment2 = new Appointment();
//		Long appId2 = 3L;
//		appointment2.setId(appId2);
//		String invoiceNumber2 = "BBBBBBBBBB";
//		appointment2.setInvoiceNumber(invoiceNumber2);
//		
//		List<Appointment> appointments = new ArrayList<Appointment>();
//		appointments.add(appointment1);
//		appointments.add(appointment2);
//		
//		when( customerRepository.findById(customerId) ).thenReturn( Optional.of(customer) ) ;
////		when( appointmentRepository.findByCustomer( eq( customer ) )).thenReturn( appointments );
//		when( appointmentRepository.findByCustomer( customer )).thenReturn( appointments );
//		
//		// --------------------------------------------
//		
//		// test
//		List<String> result = appointmentService.getInvoiceNumbersByCustomerId( customerId );
//		
//		verify( customerRepository, times(1) ).findById(customerId); 
//		verify(appointmentRepository).findByCustomer(any(Customer.class)); // This test fail 
//
//		
//		for( String str : result ) {
//			System.out.println( str );
//		}
//		
//		// check ----
//		assertNotNull(result);
//		
//		System.out.println( "--------->" + result.size() );
//		// assertEquals( numOfDifferentInvoices, result.size() );
//
////		for( int i = 0; i < appointments.size(); i ++ ) {
////			assertEquals(appointments.get(i).getInvoiceNumber(), result.get(i) );
////		}
//		// ----------
//	}

//	public void postAppointment( Appointment appointment );
	
	@Test
	public void testPostAppointment() {
		// simulate database --------------------------
		Employee doctor = new Employee();
		Long doctorId = 3L;
		doctor.setId(doctorId);
		
		Customer customer = new Customer();
		Long customerId = 10L;
		customer.setId(customerId);
				
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setDoctor(doctor);
		app.setCustomer(customer);
		
		// --------------------------------------------
		
		// test ---------------------------------------
		appointmentService.postAppointment(app);
		// --------------------------------------------
		
		// check ----
		verify( repository, times(1) ).save(app);
		// ----------
	}
	
	// set is_done = 1
//	public void putSetAppointmentDoneById( long id );
	
	@Test
	public void testPutSetAppointmentDoneById() {
		
		// simulate database --------------------------
		Employee doctor = new Employee();
		Long doctorId = 3L;
		doctor.setId(doctorId);
		
		Customer customer = new Customer();
		Long customerId = 10L;
		customer.setId(customerId);
				
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setDoctor(doctor);
		app.setCustomer(customer);
		
		when( repository.findById(idApp) ).thenReturn( Optional.of( app ) ); 
		
		// --------------------------------------------
		
		// test ---
		appointmentService.putSetAppointmentDoneById( idApp );
		// --------
		
		// check ---
		verify( repository , times(1) ).findById( anyLong() );
		
		assertEquals(1, app.getisDone() );
		// ---------
		
	}
	
	// set is_done = 0
//	public void putUnsetAppointmentDoneById( long id );
	
	@Test
	public void testPutUnsetAppointmentDoneById() {
		
		// simulate database --------------------------
		Employee doctor = new Employee();
		Long doctorId = 3L;
		doctor.setId(doctorId);
		
		Customer customer = new Customer();
		Long customerId = 10L;
		customer.setId(customerId);
				
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setDoctor(doctor);
		app.setCustomer(customer);
		
		when( repository.findById(idApp) ).thenReturn( Optional.of( app ) ); 
		
		// --------------------------------------------
		
		// test ---
		appointmentService.putUnsetAppointmentDoneById( idApp );
		// --------
		
		// check ---
		verify( repository , times(1) ).findById( anyLong() );
		
		assertEquals( 0, app.getisDone() );
		// ---------
		
	}

//	public void putAppointment( Appointment appointment );
	
	@Test
	public void testPutAppointment() {
	
		// simulate database --------------------------
		Employee doctor = new Employee();
		Long doctorId = 3L;
		doctor.setId(doctorId);
		
		Customer customer = new Customer();
		Long customerId = 10L;
		customer.setId(customerId);
				
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setDoctor(doctor);
		app.setCustomer(customer);
	
		// --------------------------------------------
		
		// test -----
		appointmentService.putAppointment(app);
		
		// ----------
		
		// check ----
		verify( repository, times(1) ).save(app);
		
		// ----------
	}
	
	// ################################## <<<<<<------------------------ DA TOGLIEREEEEEEEEEEEEEE ???????? ------------------------
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentBillNumberById( long id, String invoiceNumber );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentNoteById( long id , String notes );
	
	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
//	public void putAppointmentTreatmentById( long id, long idTreatment );
	
	// ############################################################################################################################
	
	// PATCH REQUESTS -----------------------------
//	public void updateTeethList( long id, String teeth );
	
	@Test
	public void testUpdateTeethList() {
		
		// simulate database --------------------------
		
		// The teeth string has to be like NUM_TOOTH1, NUM_TOOTH2, ...
		String teeth="11,21,12,14,16,28,";
		
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setTeeth("11,14,");
		
		when( repository.findById(idApp) ).thenReturn( Optional.of(app) );
		
		// --------------------------------------------
		
		// test ------
		appointmentService.updateTeethList(idApp, teeth);
		// -----------
		
		// check -----
		verify( repository , times(1) ).findById(idApp);
		
		assertEquals(teeth, app.getTeeth());
		// -----------
	}
	
	// --------------------------------------------
	
//	public void deleteAppointmentById( long id );
	public void testDeleteAppointmentById() {
	
		// simulate database --------------------------
		Appointment app = new Appointment();
		Long idApp = 1L;
		app.setId(idApp);
		app.setTeeth("11,14,");
		
		when( repository.findById(idApp) ).thenReturn( Optional.of(app) );
		
		// --------------------------------------------
		
		// test -------
		appointmentService.deleteAppointmentById(idApp);
		// ------------
		
		// check ------
		verify( repository , times(1) ).delete(app);
		// ------------
		
	}
	
	// ------------------------------------------------------
}
