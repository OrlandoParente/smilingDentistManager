package sdms.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import sdms.dto.TreatmentDTO;
import sdms.model.Treatment;
import sdms.service.TreatmentServiceInterface;

@RestController
public class TreatmentRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger( TreatmentRestController.class );
	
	@Autowired
	private TreatmentServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/getTreatmentById/{id}")
	public ResponseEntity<?> getTreatmentById( @PathVariable long id ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> getTreatmentById , pathVariable={id=" + id + "}");
		
		Treatment treatment = service.getTreatmentById(id);
		
		// Check if treatment is present in the database
		if( treatment == null ) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treatment with id=" + id + "not found in the database");
		
		TreatmentDTO treatmentDTO = modelMapper.map(treatment, TreatmentDTO.class);
		
		return ResponseEntity.status(HttpStatus.OK).body( treatmentDTO );
	}

	@GetMapping("/getTreatments")
	public List<TreatmentDTO> getTreatments( ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> getTreatments ");
		
		List<Treatment> treatments = service.getTreatments();
		
		List<TreatmentDTO> listTreatmentDTO = treatments.stream()
							.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
							.toList();
		
		return listTreatmentDTO ;
	}
	
	@GetMapping("/getTreatmentsById/{idCustomer}")
	public List<TreatmentDTO> getTreatmentsByCustomerId( @PathVariable long idCustomer ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> getTreatmentsByCustomerId , pathVariable={idCustomer=" + idCustomer + "}");
		
		List<Treatment> treatments = service.getTreatmentsByCustomerId(idCustomer);
		
		List<TreatmentDTO> listTreatmentDTO = treatments.stream()
				.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
				.toList();

		return listTreatmentDTO;
	}
	
	// <----------- TO EDIT : Should be invoiceNumber instead of billNumber
	@GetMapping("/getTreatmentsByBillNumber/{billNumber}")
	public List<TreatmentDTO> getTreatmentsByBillNumber( @PathVariable String billNumber ) {
		
		// check message
		LOGGER.info("TreatmentRestController -> getTreatmentsByBillNumber , pathVariable={billNumber=" + billNumber + "}");
		
		List<Treatment> treatments = service.getTreatmentsByBillNumber(billNumber);
		
		List<TreatmentDTO> listTreatmentDTO = treatments.stream()
				.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
				.toList();

		return listTreatmentDTO ;
	}
	
	// TO RE-WRITE IN ONCE ###########################################################################################################
	
	@PostMapping( value="/postTreatment", params = {"name","cost"} )
	public ResponseEntity<?> postTreatment( @RequestParam("name") String name, @RequestParam("cost") float cost ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> postTreatment, params={ name=" + name +", cost=" + cost + " }");
		
		Treatment treatment = new Treatment();
		treatment.setName(name);
		treatment.setCost(cost);
		
		service.postTreatment(treatment);
		
		return ResponseEntity.status(HttpStatus.OK).body(treatment);
	}
	
	@PostMapping( value="/postTreatment", params = {"name", "description", "cost"} )
	public void postTreatment( @RequestParam("name") String name, @RequestParam("description") String description,
								  @RequestParam("cost") float cost ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> postTreatment, params={ name=" + name +", description=" + description + ", cost=" + cost + " }");
				
		Treatment treatment = new Treatment();
		treatment.setName(name);
		treatment.setCost(cost);
		treatment.setDescription(description);
		
		service.postTreatment(treatment);
	}
	
	// ###############################################################################################################################
	
	// TO RE-WRITE WITH FACOLTATIVE PARAMS ###############################################################################################################################
	@PutMapping( value="/putTreatment", params = {"id", "name", "description", "cost"} )
	public ResponseEntity<?> putTreatment( @RequestParam("id") long id, @RequestParam("name") String name, 
								@RequestParam("description") String description,  @RequestParam("cost") float cost ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> putTreatment, params={id=" + id + ",name=" + name + ",description=" + description + ",cost=" + cost + "}");
		
		
		Treatment treatment = service.getTreatmentById(id);
		if( treatment == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Treatment to upload not found in the databse ");
		
		treatment.setName(name);
		treatment.setCost(cost);
		treatment.setDescription(description);
		
		service.putTreatment(treatment);
		
		return ResponseEntity.status( HttpStatus.OK ).body(treatment);
	}
	// ###############################################################################################################################
	
	
	@DeleteMapping( value="/deleteTreatment", params = {"id"})
	public ResponseEntity<?> deleteTreatmentById( @RequestParam("id") long id ) {

		// check message
		LOGGER.info("/deleteTreatment , params={id=" + id + "}");
		
		Treatment treatment = service.getTreatmentById(id);
		if( treatment == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Treatment with id=" + id + " not found in the database ");
		
		service.deleteTreatmentById(id);
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(treatment, TreatmentDTO.class) );
	}
			

}
