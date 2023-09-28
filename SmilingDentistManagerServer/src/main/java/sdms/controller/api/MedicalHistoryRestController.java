package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.MedicalHistory;
import sdms.repository.RepositoryInterface;

@RestController
public class MedicalHistoryRestController {

	@Autowired
	@Qualifier("mainRepository")
	private RepositoryInterface repository;
	
	public MedicalHistoryRestController() {
	}
	
	// Gestione Medical History (Anamnesi)
	
	@GetMapping("/getMedicalsHistoryByCustomer/{id_customer}")
	ArrayList<MedicalHistory> getMedicalsHistoryByCustomer( @PathVariable String id_customer ){
		
		// check message
		System.out.println("MedicalHistoryRestController --> getMedicalsHistoryByCustomer ");
		
		ArrayList<MedicalHistory> arrListMedicalHistory = null;
		
		try {
			arrListMedicalHistory = repository.getMedicalsHistoryByCustomer( id_customer );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrListMedicalHistory;
	}
	

	@GetMapping("/getMedicalHistoryById/{id}")
	public MedicalHistory getMedicalHistoryById( @PathVariable String id ) {
		
		// check message
		System.out.println("MedicalHistoryRestController --> getMedicalsHistoryById ");
		
	
		
		MedicalHistory medicalHistory = null;
		
		try {
			repository.getMedicalHistoryById(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return medicalHistory;
	}
	
	
	// type = "generale" o "odontoiatrica" 
	@PostMapping( value="/postMedicalHistory", params= {"id_customer", "type", "name"} )
	public boolean postMedicalHistory(  @RequestParam("id_customer") String id_customer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		try {
			
			repository.postMedicalHistory(id_customer, type, name);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	

	@PostMapping( value="/postMedicalHistory", params= {"id_customer", "type", "name", "description"} )
	public boolean postMedicalHistory(  @RequestParam("id_customer") String id_customer, 
									 	@RequestParam("type") String type , @RequestParam("name") String name,
									 	@RequestParam("description") String description ) {
	
		// check message
		System.out.println("MedicalHistoryRestController --> postMedicalHistory ");
		
		try {
			
			repository.postMedicalHistory( id_customer, type, name, description );
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	
	@DeleteMapping( value="/deleteMedicalHistoryById", params = {"id"} )
	public boolean deleteMedicalHistoryById( @RequestParam("id") String id ) {

		// check message
		System.out.println("MedicalHistoryRestController --> deleteMedicalHistoryById ");
		
		try {
			
			repository.deleteMedicalHistoryById( id );
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

}
