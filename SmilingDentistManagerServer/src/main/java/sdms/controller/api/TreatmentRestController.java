package sdms.controller.api;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Treatment;
import sdms.service.ServicesInterface;

@RestController
public class TreatmentRestController {
	
	@Autowired
	@Qualifier("mainService")
	ServicesInterface service;
	
	public TreatmentRestController() {
	}
	
	@GetMapping("/getTreatmentById/{id}")
	public Treatment getTreatmentById( @PathVariable String id ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentById ");
		
		Treatment treatment = null;
		
		try {
			service.getTreatmentById( id );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return treatment;
	}
	
	
	@GetMapping("/getTreatmentsById/{customer_id}")
	public Treatment getTreatmentsByCustomerId( @PathVariable String customer_id ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentsByCustomerId ");
		
		Treatment treatment = null;
		
		try {
			service.getTreatmentsByCustomerId( customer_id );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return treatment;
	}
	
	
	@GetMapping("/getTreatmentsByBillNumber/{bill_number}")
	public Treatment getTreatmentsByBillNumber( @PathVariable String bill_number ) {
		
		// check message
		System.out.println("TreatmentRestController -> getTreatmentsByBillNumber ");
		
		Treatment treatment = null;
		
		try {
			service.getTreatmentsByBillNumber( bill_number );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return treatment;
	}
	
	@PostMapping( value="/postTreatment", params = {"name","cost"} )
	public boolean postTreatment( @RequestParam("name") String name, @RequestParam("cost") String cost ) {
		
		// check message
		System.out.println("TreatmentRestController -> postTreatment");
		
		try {
			return service.postTreatment(name, cost);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	@PostMapping( value="/postTreatment", params = {"name", "description", "cost"} )
	public boolean postTreatment( @RequestParam("name") String name, @RequestParam("description") String description,
								  @RequestParam("cost") String cost ) {
		
		// check message
		System.out.println("TreatmentRestController -> postTreatment");
		
		try {
			return service.postTreatment(name, description, cost);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// boolean deleteTreatmentById( String id ) throws SQLException;
	@DeleteMapping( value="/deleteTreatmentById", params = {"id"})
	public boolean deleteTreatmentById( @RequestParam("id") String id ) {

		// check message
		System.out.println("TreatmentRestController -> deleteTreatmentById");
		
		try {
			return service.deleteTreatmentById( id );
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return false;
	}
			

}
