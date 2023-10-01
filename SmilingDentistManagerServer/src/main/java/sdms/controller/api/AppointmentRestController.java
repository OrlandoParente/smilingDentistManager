package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Appointment;
import sdms.repository.RepositoryInterface;

@RestController
public class AppointmentRestController {

	@Autowired
	@Qualifier("mainRepository")
	RepositoryInterface repository;
	
	public AppointmentRestController() {
	}
	
	@GetMapping("/getAppointments")
	ArrayList<Appointment> getAppointments() {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointment ");
		
		
		try {
			
			return repository.getAppointments();
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return null;
	}
	
	@GetMapping("/getAppointmentsByCustomerId/{id_customer}")
	ArrayList<Appointment> getAppointmentsByCustomerId( @PathVariable String id_customer ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointmentsByCustomerId ");
		
		
		try {
			
			return repository.getAppointmentsByCustomerId( id_customer );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return null;
	}
	
	@GetMapping("/getAppointmentsByDoctorId/{id_doctor}")
	ArrayList<Appointment> getAppointmentsByDoctorId( @PathVariable String id_doctor ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> getAppointmentsByDoctorId ");
		
		
		try {
			
			return repository.getAppointmentsByDoctorId( id_doctor );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return null;
	}
	
	// per registrare un appuntamento ancora non svolto con i dati essenziali
	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer"} )
	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer ) {
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		try {
		
			return repository.postAppointment(date, time, id_customer, null, null, "");
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	// per registrare un appuntamento ancora non svolto
	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment", "note"} )
	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
									@RequestParam("id_treatment") String id_treatment, @RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		try {
		
			return repository.postAppointment(date, time, id_customer, id_doctor, id_treatment, note);
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	// per registrare un appuntamento già svolto
	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment", "bill_number", "note"} )
	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
									@RequestParam("id_treatment") String id_treatment, @RequestParam("bill_number") String bill_number,
									@RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		try {
		
			return repository.postAppointment(date, time, id_customer, id_doctor, id_treatment, bill_number, note);
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	// per registrare un appuntamento potendo scegliere i valori di tutti i campi
	@PostMapping( value = "/postAppointment", params = { "date", "time", "id_customer", "id_doctor", "id_treatment",
														"is_done", "bill_number", "note"} )
	public boolean postAppointment( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer,@RequestParam("id_doctor")  String id_doctor, 
									@RequestParam("id_treatment") String id_treatment, @RequestParam("is_done") int is_done,
									@RequestParam("bill_number") String bill_number, @RequestParam("note") String note) {
		
		// Check Message
		System.out.println("AppointmentRestController -> postAppointment ");
		
		try {
	
			return repository.postAppointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note);
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	// set is_done = 1
	@PutMapping( value = "/putSetAppointmentDoneById", params = { "date", "time", "id_customer"} )
	public boolean putSetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer ) {
	
	// Check Message
	System.out.println("AppointmentRestController -> putSetAppointmentDoneById ");
	
	try {
		
		return repository.putSetAppointmentDoneById( date, time, id_customer );
	
	} catch (SQLException e) {
	
		e.printStackTrace();
	
	}
	
	return false;
	}
	
	// set is_done = 0
	@PutMapping( value = "/putUnsetAppointmentDoneById", params = { "date", "time", "id_customer"} )
	public boolean putUnsetAppointmentDoneById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer ) {
	
		// Check Message
		System.out.println("AppointmentRestController -> putUnsetAppointmentDoneById ");
		
		try {
		
			return repository.putUnsetAppointmentDoneById( date, time, id_customer );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	@PutMapping( value = "/putAppointmentBillNumberById", params = { "date", "time", "id_customer", "bill_number"} )
	public boolean putAppointmentBillNumberById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer, 
									@RequestParam("bill_number") String bill_number ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentBillNumberById ");
		
		try {
		
			return repository.putAppointmentBillNumberById( date, time, id_customer, bill_number );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	
	
	@PutMapping( value = "/putAppointmentNoteById", params = { "date", "time", "id_customer", "note"} )
	public boolean putAppointmentNoteById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer, @RequestParam("note") String note ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentNoteById ");
		
		try {
		
			return repository.putAppointmentNoteById( date, time, id_customer, note );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	@PutMapping( value = "/putAppointmentTreatmentById", params = { "date", "time", "id_customer", "id_treatment"} )
	public boolean putAppointmentTreatmentById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer, 
									@RequestParam("id_treatment") String id_treatment  ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> putAppointmentTreatmentById ");
		
		try {
		
			return repository.putAppointmentTreatmentById( date, time, id_customer , id_treatment );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	@DeleteMapping( value = "/deleteAppointmentById", params = { "date", "time", "id_customer"} )
	public boolean deleteAppointmentById( @RequestParam("date") String date, @RequestParam("time") String time, 
									@RequestParam("id_customer") String id_customer ) {
		
		// Check Message
		System.out.println("AppointmentRestController -> deleteAppointmentById ");
		
		try {
	
			return repository.deleteAppointmentById( date, time, id_customer );
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return false;
	}
	
	
	
}
