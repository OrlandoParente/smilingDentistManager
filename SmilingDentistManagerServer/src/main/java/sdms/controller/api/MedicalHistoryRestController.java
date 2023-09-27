package sdms.controller.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// ResultSet getMedicalHistoryById( String id ) throws SQLException;
	
	// type = "generale" o "odontoiatrica" 
	// boolean postMedicalHistory( String id_customer, String type , String name ) throws SQLException;
	// boolean postMedicalHistory( String id_customer, String type , String name, String descriprion ) throws SQLException;

	// boolean deleteMedicalHistoryById( String id ) throws SQLException;

}
