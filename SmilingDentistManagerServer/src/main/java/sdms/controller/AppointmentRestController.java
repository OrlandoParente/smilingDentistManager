//package sdms.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import netscape.javascript.JSObject;
//import sdms.model.Appointment;
//import sdms.service.old.ServicesInterface;
//
//@RestController
//public class AppointmentRestController {
//
//	@Autowired
//	@Qualifier("mainService")
//	ServicesInterface service;
//	
//	public AppointmentRestController() {
//	}
//	
//	@GetMapping("/getAppointments")
//	ArrayList<Appointment> getAppointments() {
//	
//		// Check Message
//		System.out.println("AppointmentRestController -> getAppointment ");
//		
//		
//		try {
//			
//			for( Appointment ap : service.getAppointments() ) System.out.println( " - " + ap.getId() + ap.getbillNumber() + " - " );
//
//			return service.getAppointments();
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return null;
//	}
//	
//	@GetMapping("/getAppointmentsByCustomerId/{id_customer}")
//	ArrayList<Appointment> getAppointmentsByCustomerId( @PathVariable String id_customer ) {
//	
//		// Check Message
//		System.out.println("AppointmentRestController -> getAppointmentsByCustomerId ");
//		
//		
//		try {
//			
//			return service.getAppointmentsByCustomerId( id_customer );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return null;
//	}
//	
//	@GetMapping("/getAppointmentsByDoctorId/{id_doctor}")
//	ArrayList<Appointment> getAppointmentsByDoctorId( @PathVariable String id_doctor ) {
//	
//		// Check Message
//		System.out.println("AppointmentRestController -> getAppointmentsByDoctorId ");
//		
//		
//		try {
//			
//			return service.getAppointmentsByDoctorId( id_doctor );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return null;
//	}
//	
//	// per registrare un appuntamento ancora non svolto con i dati essenziali
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer"} )
//	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer ) {
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment ");
//		
//		try {
//		
//			return service.postAppointment(date, time, id_customer, null, null, "");
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
//	// per registrare un appuntamento ancora non svolto
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment", "note"} )
//	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
//									@RequestParam("id_treatment") String id_treatment, @RequestParam("note") String note) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment ");
//		
//		try {
//		
//			return service.postAppointment(date, time, id_customer, id_doctor, id_treatment, note);
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
//	// per registrare un appuntamento già svolto
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment", "bill_number", "note"} )
//	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
//									@RequestParam("id_treatment") String id_treatment, @RequestParam("bill_number") String bill_number,
//									@RequestParam("note") String note) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment ");
//		
//		try {
//		
//			return service.postAppointment(date, time, id_customer, id_doctor, id_treatment, bill_number, note);
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
//	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment",
//														"is_done", "bill_number", "note"} )
//	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
//									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
//									@RequestParam("id_treatment") String id_treatment, @RequestParam("is_done") int is_done,
//									@RequestParam("bill_number") String bill_number, @RequestParam("note") String note) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> postAppointment ");
//		
//		try {
//	
//			return service.postAppointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note);
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
//	// set is_done = 1
////	@PutMapping( value = "/putSetAppointmentDoneById", params = { "date", "time", "id_customer"} )
//	@PutMapping( value = "/putSetAppointmentDoneById", params = { "id"} )
////	public boolean putSetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
////									@RequestParam("id_customer") String id_customer ) {
//	public boolean putSetAppointmentDoneById( @RequestParam("id") long id ) {
//
//	
//	// Check Message
//	System.out.println("AppointmentRestController -> putSetAppointmentDoneById ");
//	
//	try {
//		
////		return service.putSetAppointmentDoneById( date, time, id_customer );
//		return service.putSetAppointmentDoneById( id );
//		
//	} catch (SQLException e) {
//	
//		e.printStackTrace();
//	
//	}
//	
//	return false;
//	}
//	
//	// set is_done = 0
////	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "date", "time", "id_customer"} )
//	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "id"} )
////	public boolean putUnsetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
////									@RequestParam("id_customer") String id_customer ) {
//	public boolean putUnsetAppointmentDoneById( @RequestParam("id") long id ) {
//
//		// Check Message
//		System.out.println("AppointmentRestController -> putUnsetAppointmentDoneById ");
//		
//		try {
//		
////			return service.putUnsetAppointmentDoneById( date, time, id_customer );
//			return service.putUnsetAppointmentDoneById( id );
//			
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
////	@PutMapping( value = "/putAppointmentBillNumberById", params = { "date", "time", "id_customer", "bill_number"} )
//	@PutMapping( value = "/putAppointmentBillNumberById", params = { "id" , "bill_number" } )
////	public boolean putAppointmentBillNumberById( @RequestParam("date") String date, @RequestParam("time") String time, 
////									@RequestParam("id_customer") String id_customer, @RequestParam("bill_number") String bill_number ) {
//	public boolean putAppointmentBillNumberById( @RequestParam("id") long id, @RequestParam("bill_number") String bill_number ) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> putAppointmentBillNumberById ");
//		
//		try {
//		
////			return service.putAppointmentBillNumberById( date, time, id_customer, bill_number );
//			return service.putAppointmentBillNumberById( id, bill_number );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
//	
//	
////	@PutMapping( value = "/putAppointmentNoteById", params = { "date", "time", "id_customer", "note"} )
//	@PutMapping( value = "/putAppointmentNoteById", params = { "id", "note"} )
////	public boolean putAppointmentNoteById( @RequestParam("date") String date, @RequestParam("time") String time, 
////									@RequestParam("id_customer") String id_customer, @RequestParam("note") String note ) {
//	public boolean putAppointmentNoteById( @RequestParam("id") long id, @RequestParam("note") String note ) {
//		
//		// Check Message
//		System.out.println("AppointmentRestController -> putAppointmentNoteById ");
//		
//		try {
//		
////			return service.putAppointmentNoteById( date, time, id_customer, note );
//			return service.putAppointmentNoteById( id, note );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
////	@PutMapping( value = "/putAppointmentTreatmentById", params = { "date", "time", "id_customer", "id_treatment"} )
//	@PutMapping( value = "/putAppointmentTreatmentById", params = { "id", "id_treatment"} )
////	public boolean putAppointmentTreatmentById( @RequestParam("date") String date, @RequestParam("time") String time, 
////									@RequestParam("id_customer") String id_customer, @RequestParam("id_treatment") String id_treatment  ) {
//	public boolean putAppointmentTreatmentById( @RequestParam("id") long id, @RequestParam("id_treatment") String id_treatment  ) {
//	
//		// Check Message
//		System.out.println("AppointmentRestController -> putAppointmentTreatmentById ");
//		
//		try {
//		
////			return service.putAppointmentTreatmentById( date, time, id_customer , id_treatment );
//			return service.putAppointmentTreatmentById( id , id_treatment );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
////	@DeleteMapping( value = "/deleteAppointmentById", params = { "date", "time", "id_customer"} )
//	@DeleteMapping( value = "/deleteAppointmentById", params = { "id"} )
////	public boolean deleteAppointmentById( @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("id_customer") String id_customer ) {
//	public boolean deleteAppointmentById( @RequestParam("id") long id ) {
//	
//		// Check Message
//		System.out.println("AppointmentRestController -> deleteAppointmentById ");
//		
//		try {
//	
//			return service.deleteAppointmentById( id );
//		
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		
//		}
//		
//		return false;
//	}
//	
//	
//	
//}
