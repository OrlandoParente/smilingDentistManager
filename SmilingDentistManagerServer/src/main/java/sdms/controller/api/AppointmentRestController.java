package sdms.controller.api;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import sdms.dto.AppointmentDTO;
import sdms.model.Appointment;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.TreatmentServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class AppointmentRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger( AppointmentRestController.class );

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
		LOGGER.info("AppointmentRestController -> getAppointment ");
				
		return service.getAppointments().stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
	@GetMapping("/getAppointmentsByCustomerId/{idCustomer}")
	List<AppointmentDTO> getAppointmentsByCustomerId( @PathVariable long idCustomer ) {
	
		// Check Message
		LOGGER.info("AppointmentRestController -> getAppointmentsByCustomerId , pathVariables={idCustomer=" + idCustomer + "}");
				
		return service.getAppointmentsByCustomerId(idCustomer).stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
	@GetMapping("/getAppointmentsByDoctorId/{idDoctor}")
	List<AppointmentDTO> getAppointmentsByDoctorId( @PathVariable long idDoctor ) {
	
		// Check Message
		LOGGER.info("AppointmentRestController -> getAppointmentsByDoctorId , pathVariables={idDoctor=" + idDoctor + "}");
				
		return service.getAppointmentsByDoctorId(idDoctor).stream().map( app -> modelMapper.map(app, AppointmentDTO.class ) ).toList();
	}
	
//	// When the client sent you the obj as a jsonObj
//	@PostMapping( value = "/postAppointment" )
//	public ResponseEntity<?> postAppointment( @RequestBody AppointmentDTO appointmentDTO) {
//		
//		// Check Message
//		LOGGER.info("AppointmentRestController -> postAppointment (@RequestBody) ");
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
									@RequestParam( defaultValue = "" ) String paymentMethod,
									@RequestParam( defaultValue = "" ) String notes) {
									
		// Check Message 
		LOGGER.info("AppointmentRestController -> postAppointment "
				+ ", params={date=" + date + ", time=" + time + ", idCustomer=" + idCustomer + ", idDoctor=" + idDoctor + ", idTreatment=" + idTreatment + ""
				+ ", isDone=" + isDone + ", invoiceNumber=" + invoiceNumber + ", payment=" + payment + ", paymentMethod=" + paymentMethod + ", notes=" + notes + "}");
		
		Appointment appointment = new Appointment();
		
		try {
			// No need to check the default values for this fields
			appointment.setDate( dateAndTimeManager.parseDate(date) );
			appointment.setTime( dateAndTimeManager.parseTime(time) );
			appointment.setCustomer( customerService.getCustomerById(idCustomer) );
			appointment.setisDone(isDone);
			appointment.setNotes(notes);
			appointment.setPaymentMethod(paymentMethod);
			
			// TO EDIT: Return 404 Not found if they don't have default value and the service can't find them on the db // <<===================================
			// Not insert if they have the default value 
			if( idDoctor > -1 )	appointment.setDoctor( employeeService.getEmployeeById(idDoctor) );
			if( idTreatment > -1 )	appointment.setTreatment( treatmentService.getTreatmentById(idTreatment) );
			if( ! invoiceNumber.equals("none") )	appointment.setInvoiceNumber(invoiceNumber);
			if( payment != -1 ) appointment.setPayment(payment);
			
			service.postAppointment(appointment);
		
		} catch( DateTimeException e ) {
			System.err.println( "AppointmentRestconstroller -> PostAppointment, error: " + e.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Format date not valid");
		} catch( Exception e ) {
			System.err.println( "AppointmentRestconstroller -> PostAppointment, error: " + e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body("Post Appointment Failed");
		}
	
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(appointment, AppointmentDTO.class) );
	}
	
	// set is_done = 1
	@PutMapping( value = "/putSetAppointmentDoneById", params = { "id"} )
	public ResponseEntity<?> putSetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		LOGGER.info("AppointmentRestController -> putSetAppointmentDoneById , params={id=" + id + "} ");
		
		try {
			service.putSetAppointmentDoneById(id);
		} catch ( EntityNotFoundException e ) {
			
			e.printStackTrace();
			ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Appointment with id=" + id + " not found in the database ");
		} catch ( Exception e ) {
			
			e.printStackTrace();
			ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).build();
	}
	
	// set is_done = 0
	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "id"} )
	public ResponseEntity<?> putUnsetAppointmentDoneById( @RequestParam("id") long id ) {

		// Check Message
		LOGGER.info("AppointmentRestController -> putUnsetAppointmentDoneById , params={id=" + id + "} ");
		
		try {
			service.putUnsetAppointmentDoneById(id);
		} catch ( EntityNotFoundException e ) {
			
			e.printStackTrace();
			ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Appointment with id=" + id + " not found in the database ");
		} catch ( Exception e ) {
			
			e.printStackTrace();
			ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).build();
		
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
									@RequestParam( defaultValue = "sdms.nessuna-nota-inviata.ma-proprio-nessuna -1" ) String paymentMethod,
									// we need a default value which is surely different from a real note
									@RequestParam( defaultValue = "sdms.nessuna-nota-inviata.ma-proprio-nessuna -1" ) String notes) {
		
		// Check Message 
		LOGGER.info("AppointmentRestController -> putAppointment "
				+ ", params={id=" + id + ", date=" + date + ", time=" + time + ", idCustomer=" + idCustomer + ", idDoctor=" + idDoctor + ", idTreatment=" + idTreatment + ""
				+ ", isDone=" + isDone + ", invoiceNumber=" + invoiceNumber + ", payment=" + payment + ", paymentMethod=" + paymentMethod + ", notes=" + notes + "}");
		
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
			if( ! paymentMethod.equals("sdms.nessuna-nota-inviata.ma-proprio-nessuna -1") ) appointment.setPaymentMethod(paymentMethod);
			if( isDone != -1 )	appointment.setisDone(isDone);
			
			service.putAppointment(appointment);
				
		} catch ( DateTimeParseException e ) {
			
			System.err.println( "AppointmentRestconstroller -> PutAppointment, error: " + e.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Invalid format data");
			
		} catch( Exception e ) {
			System.err.println( "AppointmentRestconstroller -> PutAppointment, error: " + e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body("Post Appointment Failed");
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body(modelMapper.map(appointment, AppointmentDTO.class));
	}
	
	// Add or remove tooth to/from teeth list of an appointment
	@PatchMapping( value="/patchTeeth", params={"idAppointment", "teeth"} )
	public ResponseEntity<?> patchTeeth( @RequestParam long idAppointment, @RequestParam String teeth,	// Mandatory fields 
										 @RequestParam( defaultValue = "false" ) boolean delete ){
		
		LOGGER.info("/patchTeeth , params={idAppointment=" + idAppointment + ", teeth=" + teeth + ", delete=" + delete + "}");
		
		try {
			
			service.updateTeethList(idAppointment, teeth);
			
		} catch (EntityNotFoundException e) {
	        System.err.println("AppointmentRestController -> PatchTooth -> Error: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        
		} catch ( Exception e ) {
			System.err.println( "AppointmentRestController -> PatchTooth -> Error: " + e.getMessage() );
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		Appointment appointment = service.getAppointmentById(idAppointment);
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(appointment, AppointmentDTO.class) );
	}
	
	
	// <--------------------  Can I delete this?
	@PutMapping( value = "/putAppointmentBillNumberById", params = { "id" , "billNumber" } )
	public void putAppointmentBillNumberById( @RequestParam("id") long id, @RequestParam("billNumber") String billNumber ) {
		
		// Check Message
		LOGGER.info("AppointmentRestController -> putAppointmentBillNumberById ");
		
		service.putAppointmentBillNumberById(id, billNumber);	
	}
	
	// <-------------------- Can I delete this?
	@PutMapping( value = "/putAppointmentNoteById", params = { "id", "note"} )
	public void putAppointmentNoteById( @RequestParam("id") long id, @RequestParam("note") String note ) {
		
		// Check Message
		LOGGER.info("AppointmentRestController -> putAppointmentNoteById ");
				
		service.putAppointmentNoteById(id, note);
	}
	
	// <-------------------- Can I delete this?
	@PutMapping( value = "/putAppointmentTreatmentById", params = { "id", "idTreatment"} )
	public void putAppointmentTreatmentById( @RequestParam("id") long id, @RequestParam("idTreatment") long idTreatment  ) {
	
		// Check Message
		LOGGER.info("AppointmentRestController -> putAppointmentTreatmentById ");
		
		service.putAppointmentTreatmentById(id, idTreatment);
	}
	
	@DeleteMapping( value = "/deleteAppointment", params = { "id"} )
	public ResponseEntity<?> deleteAppointmentById( @RequestParam("id") long id ) {
	
		// Check Message
		LOGGER.info("AppointmentRestController -> deleteAppointmentById , params={id=" + id + "}");

		Appointment appointment = null;
		try {
			appointment = service.getAppointmentById(id);
			
		} catch( NoSuchElementException | EntityNotFoundException e ) {
			
			LOGGER.error("Appointment with id=" + id + " not found in the database");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment with id=" + id + " not found in the database");
		}
		service.deleteAppointmentById(id);
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(appointment, AppointmentDTO.class) );
	}
	
}
