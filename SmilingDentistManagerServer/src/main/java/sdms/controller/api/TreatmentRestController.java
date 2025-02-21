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
	public TreatmentDTO getTreatmentById( @PathVariable long id ) {
		
		// check message
		LOGGER.info("TreatmentRestController -> getTreatmentById , pathVariable={id=" + id + "}");
		
		Treatment treatment = service.getTreatmentById(id);
		
		return modelMapper.map(treatment, TreatmentDTO.class);
	}

	@GetMapping("/getTreatments")
	public List<TreatmentDTO> getTreatments( ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> getTreatments ");
		
		List<Treatment> treatments = service.getTreatments();
		
		List<TreatmentDTO> listTreatmentDTO = treatments.stream()
							.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
							.toList();
		
		return listTreatmentDTO;
	}
	
	@GetMapping("/getTreatmentsById/{idCustomer}")
	public TreatmentDTO getTreatmentsByCustomerId( @PathVariable long idCustomer ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> getTreatmentsByCustomerId , pathVariable={idCustomer=" + idCustomer + "}");
		
		Treatment treatment = service.getTreatmentById(idCustomer);
		
		return modelMapper.map(treatment, TreatmentDTO.class);
	}
	
	// <----------- TO EDIT : Should be invoiceNumber instead of billNumber
	@GetMapping("/getTreatmentsByBillNumber/{billNumber}")
	public List<TreatmentDTO> getTreatmentsByBillNumber( @PathVariable String billNumber ) {
		
		// check message
		LOGGER.info("TreatmentRestController -> getTreatmentsByBillNumber , pathVariable={billNumber=" + billNumber + "}");
		
		List<Treatment> treatments = service.getTreatmentsByBillNumber(billNumber);
		
		return treatments.stream().map( t -> modelMapper.map( t, TreatmentDTO.class ) ).toList();
	}
	
	// TO RE-WRITE IN ONCE ###########################################################################################################
	
	@PostMapping( value="/postTreatment", params = {"name","cost"} )
	public void postTreatment( @RequestParam("name") String name, @RequestParam("cost") float cost ) {
		
		// Check message
		LOGGER.info("TreatmentRestController -> postTreatment, params={ name=" + name +", cost=" + cost + " }");
		
		Treatment treatment = new Treatment();
		treatment.setName(name);
		treatment.setCost(cost);
		
		service.postTreatment(treatment);
		
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
	
	
	// boolean deleteTreatmentById( String id ) throws SQLException;
	@DeleteMapping( value="/deleteTreatment", params = {"id"})
	public void deleteTreatmentById( @RequestParam("id") long id ) {

		// check message
		LOGGER.info("TreatmentRestController -> deleteTreatmentById, params={id=" + id + "}");
		
		service.deleteTreatmentById(id);
		
	}
			

}
