package sdms.controller.api;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.AppointmentDTO;
import sdms.model.Appointment;
import sdms.service.AppointmentServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class AppointmentRestController {

	@Autowired
	private AppointmentServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private DateAndTimeManager dateAndTimeManager;
	
	@GetMapping("/getAppointments")
	List<AppointmentDTO> getAppointments() {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointment ");
				
		return service.getAppointments().stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
	@GetMapping("/getAppointmentsByCustomerId/{idCustomer}")
	List<AppointmentDTO> getAppointmentsByCustomerId( @PathVariable long idCustomer ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointmentsByCustomerId ");
		
				
		return service.getAppointmentsByCustomerId(idCustomer).stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
	@GetMapping("/getAppointmentsByDoctorId/{idDoctor}")
	List<AppointmentDTO> getAppointmentsByDoctorId( @PathVariable long idDoctor ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointmentsByDoctorId ");
				
		return service.getAppointmentsByDoctorId(idDoctor).stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
	// per registrare un appuntamento ancora non svolto con i dati essenziali
	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer"} )
	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("idCustomer") long idCustomer ) {
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		Appointment appointment = new Appointment();
		appointment.setDate( dateAndTimeManager.parseDate(date) );
		appointment.setTime( dateAndTimeManager.parseTime(time) );
		appointment.setidCustomer(idCustomer);
		
		service.postAppointment(appointment);
		
	}
	
	
	// per registrare un appuntamento ancora non svolto
	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", "idTreatment", "note"} )
	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor")  long idDoctor, 
									@RequestParam("idTreatment") long idTreatment, @RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
				
		Appointment appointment = new Appointment();
		appointment.setDate( dateAndTimeManager.parseDate(date) );
		appointment.setTime( dateAndTimeManager.parseTime(time) );
		appointment.setidCustomer(idCustomer);
		appointment.setidDoctor(idDoctor);
		appointment.setidTreatment(idTreatment);
		appointment.setNote(note);
		
		service.postAppointment(appointment);
	}
	
	
	// per registrare un appuntamento già svolto
	// cioè registra anche il bill number (numero di fattura)
	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", 
														"idTreatment", "billNumber", "note"} )
	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor") long idDoctor, 
									@RequestParam("idTreatment") long idTreatment, @RequestParam("billNumber") String billNumber,
									@RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
				
		Appointment appointment = new Appointment();
		appointment.setDate( dateAndTimeManager.parseDate(date) );
		appointment.setTime( dateAndTimeManager.parseTime(time) );
		appointment.setidCustomer(idCustomer);
		appointment.setidDoctor(idDoctor);
		appointment.setidTreatment(idTreatment);
		appointment.setNote(note);
		appointment.setbillNumber(billNumber);
		
		service.postAppointment(appointment);
		
	}
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", "idTreatment",
														"isDone", "billNumber", "note"} )
	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor")  long idDoctor, 
									@RequestParam("idTreatment") long idTreatment, @RequestParam("isDone") int isDone,
									@RequestParam("billNumber") String billNumber, @RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		Appointment appointment = new Appointment();
		appointment.setDate( dateAndTimeManager.parseDate(date) );
		appointment.setTime( dateAndTimeManager.parseTime(time) );
		appointment.setidCustomer(idCustomer);
		appointment.setidDoctor(idDoctor);
		appointment.setidTreatment(idTreatment);
		appointment.setNote(note);
		appointment.setbillNumber(billNumber);
		appointment.setisDone(isDone);
		
		service.postAppointment(appointment);
		
	}
	
	
	// set is_done = 1
//	@PutMapping( value = "/putSetAppointmentDoneById", params = { "date", "time", "id_customer"} )
	@PutMapping( value = "/putSetAppointmentDoneById", params = { "id"} )
//	public boolean putSetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer ) {
	public void putSetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		System.out.println("AppointmentRestController -> putSetAppointmentDoneById ");
		
		service.putSetAppointmentDoneById(id);
	}
	
	// set is_done = 0
//	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "date", "time", "id_customer"} )
	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "id"} )
//	public boolean putUnsetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer ) {
	public void putUnsetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		System.out.println("AppointmentRestController -> putUnsetAppointmentDoneById ");
				
		service.putUnsetAppointmentDoneById(id);
		
	}
	
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
		@PutMapping( value = "/putAppointment", params = { "id", "date", "time", "idCustomer", "idDoctor", "idTreatment",
															"isDone", "billNumber", "note"} )
		public ResponseEntity<?> putAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
										@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor")  long idDoctor, 
										@RequestParam("idTreatment") long idTreatment, @RequestParam("isDone") int isDone,
										@RequestParam("billNumber") String billNumber, @RequestParam("note") String note) {
			
			// Check Message
			System.out.println("AppointmentRestController -> postAppointment ");

			
			Appointment appointment = service.getAppointmentById(idTreatment);
			
			if( appointment == null ) 
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Not Found: The entity to update was not found.");
			
			appointment.setDate( dateAndTimeManager.parseDate(date) );
			appointment.setTime( dateAndTimeManager.parseTime(time) );
			appointment.setidCustomer(idCustomer);
			appointment.setidDoctor(idDoctor);
			appointment.setidTreatment(idTreatment);
			appointment.setNote(note);
			appointment.setbillNumber(billNumber);
			appointment.setisDone(isDone);
			
			service.putAppointment(appointment);
			
			return ResponseEntity.status( HttpStatus.OK ).body(modelMapper.map(appointment, AppointmentDTO.class));
		}
	
	
//	@PutMapping( value = "/putAppointmentBillNumberById", params = { "date", "time", "id_customer", "bill_number"} )
	@PutMapping( value = "/putAppointmentBillNumberById", params = { "id" , "billNumber" } )
//	public boolean putAppointmentBillNumberById( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer, @RequestParam("bill_number") String bill_number ) {
	public void putAppointmentBillNumberById( @RequestParam("id") long id, @RequestParam("billNumber") String billNumber ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentBillNumberById ");
		
		service.putAppointmentBillNumberById(id, billNumber);	
	}
	
	
	
	
//	@PutMapping( value = "/putAppointmentNoteById", params = { "date", "time", "id_customer", "note"} )
	@PutMapping( value = "/putAppointmentNoteById", params = { "id", "note"} )
//	public boolean putAppointmentNoteById( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer, @RequestParam("note") String note ) {
	public void putAppointmentNoteById( @RequestParam("id") long id, @RequestParam("note") String note ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentNoteById ");
				
		service.putAppointmentNoteById(id, note);
	}
	
//	@PutMapping( value = "/putAppointmentTreatmentById", params = { "date", "time", "id_customer", "id_treatment"} )
	@PutMapping( value = "/putAppointmentTreatmentById", params = { "id", "idTreatment"} )
//	public boolean putAppointmentTreatmentById( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer, @RequestParam("id_treatment") String id_treatment  ) {
	public void putAppointmentTreatmentById( @RequestParam("id") long id, @RequestParam("idTreatment") long idTreatment  ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentTreatmentById ");
		
		service.putAppointmentTreatmentById(id, idTreatment);
	}
	
	
//	@DeleteMapping( value = "/deleteAppointmentById", params = { "date", "time", "id_customer"} )
	@DeleteMapping( value = "/deleteAppointmentById", params = { "id"} )
//	public boolean deleteAppointmentById( @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("id_customer") String id_customer ) {
	public void deleteAppointmentById( @RequestParam("id") long id ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> deleteAppointmentById ");

		service.deleteAppointmentById(id);
	}
	
}
