package sdms.controller.api;

import java.time.DateTimeException;
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
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.TreatmentServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class AppointmentRestController {
	

	@Autowired
	private AppointmentServiceInterface service;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private TreatmentServiceInterface treatmentService;
	
	@Autowired
	private EmployeeServiceInterface employeeService;
	
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
	
//	// When the client sent you the obj as a jsonObj
//	@PostMapping( value = "/postAppointment" )
//	public ResponseEntity<?> postAppointment( @RequestBody AppointmentDTO appointmentDTO) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment (@RequestBody) ");
//			
//		try {
//			
//			Appointment appointment  = modelMapper.map(appointmentDTO, Appointment.class);
//			service.postAppointment(appointment);
//			
//		} catch( Exception e ) {
//			// TO EDIT: Return better error response
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Post Appointment Failed");
//		}
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( appointmentDTO  );
//	}
	
//	 params = { "date", "time", "idCustomer", "idDoctor", "idTreatment", "isDone", "billNumber", "notes"}
	
	// Post using @RequestParam
	@PostMapping( value = "/postAppointment", params = {"date", "time", "idCustomer"} )
	public ResponseEntity<?> postAppointment( 
								@RequestParam String date, @RequestParam String time, @RequestParam long idCustomer, // Mandatory parameters
									@RequestParam( defaultValue = "-1" )  long idDoctor, 
									@RequestParam( defaultValue = "-1" ) long idTreatment, 
									@RequestParam( defaultValue = "0" ) int isDone,
									@RequestParam( defaultValue = "none" ) String invoiceNumber,
									@RequestParam( defaultValue = "-1" ) double payment,
									@RequestParam( defaultValue = "" ) String notes) {
									
		// Check Message (Use the Logger)
		System.out.println("AppointmentRestController -> postAppointment ((date, time, idCustomer, idDoctor, idTreatment, isDone, billNumber, notes) ) ");
		
		Appointment appointment = new Appointment();
		
		try {
			// No need to check the default values for this fields
			appointment.setDate( dateAndTimeManager.parseDate(date) );
			appointment.setTime( dateAndTimeManager.parseTime(time) );
			appointment.setCustomer( customerService.getCustomerById(idCustomer) );
			appointment.setisDone(isDone);
			appointment.setNotes(notes);
			
			// TO EDIT: Return 404 Not found if they don't have default value and the service can't find them on the db // <<===================================
			// Not insert if they have the default value 
			if( idDoctor != -1 )	appointment.setDoctor( employeeService.getEmployeeById(idDoctor) );
			if( idTreatment != -1 )	appointment.setTreatment( treatmentService.getTreatmentById(idTreatment) );
			if( ! invoiceNumber.equals("none") )	appointment.setInvoiceNumber(invoiceNumber);
			if( payment != -1 ) appointment.setPayment(payment);
			
			service.postAppointment(appointment);
		
		} catch( DateTimeException e ) {
			System.err.println( "AppointmentRestconstroller -> PostAppointment, error: " + e.getMessage() );
			// TO EDIT: Return better error response // <<=====================================================================================================
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Format date not valid");
		}catch( Exception e ) {
			System.err.println( "AppointmentRestconstroller -> PostAppointment, error: " + e.getMessage() );
			// TO EDIT: Return better error response // <<=====================================================================================================
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Post Appointment Failed");
		}
	
		
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(appointment, AppointmentDTO.class) );

	}
	
	// WE CAN DELETE ALL THIS #############################################################################################################################
	
//	// per registrare un appuntamento ancora non svolto con i dati essenziali
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "notes"} )
//	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("idCustomer") long idCustomer, @RequestParam( defaultValue = "" ) String notes) {
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment (date, time, idCustomer) ");
//		
//		Appointment appointment = new Appointment();
//		appointment.setDate( dateAndTimeManager.parseDate(date) );
//		appointment.setTime( dateAndTimeManager.parseTime(time) );
//		appointment.setidCustomer(idCustomer);
//		appointment.setNotes(notes);
//		
//		service.postAppointment(appointment);
//		
//	}
//	
//	
//	// per registrare un appuntamento ancora non svolto
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", "idTreatment", "notes"} )
//	public ResponseEntity<?> postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor")  long idDoctor, 
//									@RequestParam("idTreatment") long idTreatment, @RequestParam("notes") String notes) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment (date, time, idCustomer, idDoctor, idTreatment, notes) ");
//			
//		Appointment appointment = new Appointment();
//		
//		try {
//			
//			appointment.setDate( dateAndTimeManager.parseDate(date) );
//			appointment.setTime( dateAndTimeManager.parseTime(time) );
//			appointment.setidCustomer(idCustomer);
//			appointment.setidDoctor(idDoctor);
//			appointment.setidTreatment(idTreatment);
//			appointment.setNotes(notes);
//			
//			service.postAppointment(appointment);
//			
//		} catch( Exception e ) {
//			// TO EDIT: Return better error response
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Post Appointment Failed");
//		}
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(appointment, AppointmentDTO.class) );
//	}
//	
//	
//	// per registrare un appuntamento già svolto
//	// cioè registra anche il bill number (numero di fattura)
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", 
//														"idTreatment", "billNumber", "notes"} )
//	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor") long idDoctor, 
//									@RequestParam("idTreatment") long idTreatment, @RequestParam("billNumber") String billNumber,
//									@RequestParam("notes") String notes) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment (date, time, idCustomer, idDoctor, idTreatment, billNumber, notes)  ");
//				
//		Appointment appointment = new Appointment();
//		appointment.setDate( dateAndTimeManager.parseDate(date) );
//		appointment.setTime( dateAndTimeManager.parseTime(time) );
//		appointment.setidCustomer(idCustomer);
//		appointment.setidDoctor(idDoctor);
//		appointment.setidTreatment(idTreatment);
//		appointment.setNotes(notes);
//		appointment.setbillNumber(billNumber);
//		
//		service.postAppointment(appointment);
//		
//	}
//	
//	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "idCustomer", "idDoctor", "idTreatment",
//														"isDone", "billNumber", "notes"} )
//	public void postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("idCustomer") long idCustomer,@RequestParam("idDoctor")  long idDoctor, 
//									@RequestParam("idTreatment") long idTreatment, @RequestParam("isDone") int isDone,
//									@RequestParam("billNumber") String billNumber, @RequestParam("notes") String notes) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment ((date, time, idCustomer, idDoctor, idTreatment, isDone, billNumber, notes) ) ");
//		
//		Appointment appointment = new Appointment();
//		appointment.setDate( dateAndTimeManager.parseDate(date) );
//		appointment.setTime( dateAndTimeManager.parseTime(time) );
//		appointment.setidCustomer(idCustomer);
//		appointment.setidDoctor(idDoctor);
//		appointment.setidTreatment(idTreatment);
//		appointment.setNotes(notes);
//		appointment.setbillNumber(billNumber);
//		appointment.setisDone(isDone);
//		
//		service.postAppointment(appointment);
//		
//	}
//	
	// ####################################################################################################################################################
	
	// set is_done = 1
	@PutMapping( value = "/putSetAppointmentDoneById", params = { "id"} )
	public void putSetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		System.out.println("AppointmentRestController -> putSetAppointmentDoneById ");
		
		service.putSetAppointmentDoneById(id);
	}
	
	// set is_done = 0
	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "id"} )
	public void putUnsetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		System.out.println("AppointmentRestController -> putUnsetAppointmentDoneById ");
				
		service.putUnsetAppointmentDoneById(id);
		
	}
	
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
//	@PutMapping( value = "/putAppointment", params = { "id", "date", "time", "idCustomer", "idDoctor", "idTreatment", "isDone", "billNumber", "notes"} )
	@PutMapping( value = "/putAppointment", params = { "id" } )
	public ResponseEntity<?> putAppointment( @RequestParam long id,	// Mandatory parameters
									@RequestParam( defaultValue = "" ) String date, 
									@RequestParam( defaultValue = "" ) String time, 
									@RequestParam( defaultValue = "-1" ) long idCustomer, 
									@RequestParam( defaultValue = "-1" )  long idDoctor, 
									@RequestParam( defaultValue = "-1" ) long idTreatment, 
									@RequestParam( defaultValue = "-1" ) int isDone,
									@RequestParam( defaultValue = "none" ) String invoiceNumber, 
									@RequestParam( defaultValue = "-1" ) double payment,
									// we need a default value which is surely different from a real note
									@RequestParam( defaultValue = "sdms.nessuna-nota-inviata.ma-proprio-nessuna -1" ) String notes) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");

		
		Appointment appointment = service.getAppointmentById(id);
		
		if( appointment == null ) 
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Not Found: The entity to update was not found.");
		
		try {
			if( ! date.equals("") )	appointment.setDate( dateAndTimeManager.parseDate(date) );
			if( ! time.equals("") )	appointment.setTime( dateAndTimeManager.parseTime(time) );
			
			if( idCustomer == -2 )	// If the user wants delete the link to customer without replace with some else customer
				appointment.setCustomer( null );
			else if( idCustomer != -1 )
				appointment.setCustomer( customerService.getCustomerById(idCustomer) );
			
			if( idDoctor == -2 )	// If the user wants delete the link to doctor without replace with some else doctor
				appointment.setDoctor( null );
			else if( idDoctor != -1 )	
				appointment.setDoctor( employeeService.getEmployeeById(idDoctor) );
			
			if( idTreatment == -2 )	// If the user wants delete the link to treatment without replace with some else treatment
				appointment.setTreatment( null );
			else if( idTreatment != -1 )
				appointment.setTreatment( treatmentService.getTreatmentById(idTreatment) );
			
			if( ! notes.equals("sdms.nessuna-nota-inviata.ma-proprio-nessuna -1") ) appointment.setNotes(notes);
			if( ! invoiceNumber.equals("none") )	appointment.setInvoiceNumber(invoiceNumber);
			if( payment != -1 )	appointment.setPayment(payment);
			if( isDone != -1 )	appointment.setisDone(isDone);
			
			service.putAppointment(appointment);
				
		} catch( Exception e ) {
			System.err.println( "AppointmentRestconstroller -> PutAppointment, error: " + e.getMessage() );
			// TO EDIT: Return better error response // <<=====================================================================================================
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Post Appointment Failed");
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body(modelMapper.map(appointment, AppointmentDTO.class));
	}
	
	@PutMapping( value = "/putAppointmentBillNumberById", params = { "id" , "billNumber" } )
	public void putAppointmentBillNumberById( @RequestParam("id") long id, @RequestParam("billNumber") String billNumber ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentBillNumberById ");
		
		service.putAppointmentBillNumberById(id, billNumber);	
	}
	
	
	@PutMapping( value = "/putAppointmentNoteById", params = { "id", "note"} )
	public void putAppointmentNoteById( @RequestParam("id") long id, @RequestParam("note") String note ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentNoteById ");
				
		service.putAppointmentNoteById(id, note);
	}
	
	@PutMapping( value = "/putAppointmentTreatmentById", params = { "id", "idTreatment"} )
	public void putAppointmentTreatmentById( @RequestParam("id") long id, @RequestParam("idTreatment") long idTreatment  ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentTreatmentById ");
		
		service.putAppointmentTreatmentById(id, idTreatment);
	}
	
	@DeleteMapping( value = "/deleteAppointment", params = { "id"} )
	public void deleteAppointmentById( @RequestParam("id") long id ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> deleteAppointmentById ");

		service.deleteAppointmentById(id);
	}
	
}
