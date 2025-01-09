package sdms.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Customer;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;
import sdms.service.CustomerServiceInterface;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;

@RestController
public class HasMedicalHistoryRestController {

	private final Logger LOGGER = LoggerFactory.getLogger( HasMedicalHistoryRestController.class );
	
	@Autowired
	private HasMedicalHistoryServiceInterface service;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private MedicalHistoryServiceInterface medicalHistoryService;
	
	@PostMapping( value="/postHasMedicalHistory", params = { "idCustomer", "idMedicalHistory" } )
	public ResponseEntity<?> postHasMedicalHistory( @RequestParam long idCustomer, @RequestParam long idMedicalHistory,
													@RequestParam ( defaultValue = "" ) String notes ){
		
		LOGGER.info("/postHasMedicalHistory  PARAMS: idCustomer=" + idCustomer + "; idMedicalHistory=" + idMedicalHistory + "; notes= "+ notes +";");
		
		Customer customer = customerService.getCustomerById(idCustomer);
		// check if customer exists
		if( customer == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error: Customer with id = '" + idCustomer + "' not found in the database");
		
		MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(idMedicalHistory);		
		// check if medical history exists
		if( medicalHistory == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error: Medical History with id = '" + idMedicalHistory + "' not found in the database");
		
		HasMedicalHistory hasMedicalHistory = new HasMedicalHistory();
		
		try {
			
			hasMedicalHistory.setCustomer(customer);
			hasMedicalHistory.setMedicalHistory(medicalHistory);
			hasMedicalHistory.setNotes(notes);
			
			service.postHasMedicalHistory(hasMedicalHistory);
			
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( hasMedicalHistory );
	}
	
	@PostMapping( value="/putHasMedicalHistory", params = { "id" } )
	public ResponseEntity<?> putHasMedicalHistory( @RequestParam long id,
													@RequestParam ( defaultValue = "-1" ) long idCustomer, 
													@RequestParam ( defaultValue = "-1" ) long idMedicalHistory,
													@RequestParam ( defaultValue = "sdms-nothing_nessuna_valore_passato" ) String notes ){
		
		LOGGER.info("/putHasMedicalHistory  PARAMS: id=" + id + " idCustomer=" + idCustomer + "; idMedicalHistory=" + idMedicalHistory + "; notes= "+ notes +";");
		
		HasMedicalHistory hasMedicalHistory = service.getHasMedicalHistoryById(id);
		// check if the entity to edit exists
		if( hasMedicalHistory == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error: HasMedicalHistory with id = '" + id + "' not found in the database");
		
		
		try {
		
			if( idCustomer != - 1 ) {
				
				Customer customer = customerService.getCustomerById(idCustomer);
				
				// check if customer exists
				if( customer == null )
					return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error: Customer with id = '" + idCustomer + "' not found in the database");	
				
				hasMedicalHistory.setCustomer(customer);
			}
			
			if( idMedicalHistory != -1  ) {
			
				MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(idMedicalHistory);		
				// check if medical history exists
				if( medicalHistory == null )
					return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error: Medical History with id = '" + idMedicalHistory + "' not found in the database");
				
				hasMedicalHistory.setMedicalHistory(medicalHistory);
			}
			
			if( ! notes.equals("nothing_nessuna_valore_passato") )	hasMedicalHistory.setNotes(notes);
			
			service.putHasMedicalHistory(hasMedicalHistory);
			
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( hasMedicalHistory );
	}
	
	@DeleteMapping( value = "/deleteHasMedicalHistory", params = {"id"})
	public ResponseEntity<?> deleteHasMedicalHistoryById( @RequestParam long id ){
		
		try {
			service.deleteHasMedicalHistoryById(id);
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		}
		
		return ResponseEntity.status( HttpStatus.OK ).build();
	}
	
	@DeleteMapping( value = "/deleteHasMedicalHistory", params = {"idCustomer", "idMedicalHistory"})
	public ResponseEntity<?> deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory( @RequestParam long idCustomer, @RequestParam long idMedicalHistory ){
		
		try {
			service.deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory(idCustomer, idMedicalHistory);
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		}
		
		return ResponseEntity.status( HttpStatus.OK ).build();
	}
}
