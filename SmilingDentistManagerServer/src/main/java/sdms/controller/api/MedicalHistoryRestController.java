package sdms.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.MedicalHistoryDTO;
import sdms.model.MedicalHistory;
import sdms.service.MedicalHistoryServiceInterface;

//Management of Medical History ("Anamnesi" in Italian)
@RestController
public class MedicalHistoryRestController {

	private final Logger LOGGER = LoggerFactory.getLogger( MedicalHistoryRestController.class );
	
	@Autowired
	private MedicalHistoryServiceInterface service;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	// GET ----------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/getMedicalsHistoryByCustomer/{idCustomer}")
	List<MedicalHistoryDTO> getMedicalsHistoryByCustomer( @PathVariable long idCustomer ) {
		
		// check message
		LOGGER.info("MedicalHistoryRestController --> getMedicalsHistoryByCustomer , pathVariable={ idCustomer=" + idCustomer + " } ");
		
		List<MedicalHistory> listMedicalHistory = service.getMedicalsHistoryByCustomerId(idCustomer);
		
		return listMedicalHistory.stream().map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) ).toList();
	}
	
	@GetMapping("/getMedicalsHistoryByType/{type}")
	List<MedicalHistoryDTO> getMedicalsHistoryByCustomer( @PathVariable String type ) {
		
		LOGGER.info("/getMedicalsHistoryByType/{type} ,  pathVariable={ type = " + type + " }" );
		
		List<MedicalHistory> listMedicalHistory = service.getMedicalsHistoryByType(type);
		
		return listMedicalHistory.stream().map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) ).toList();	
	}
	
	@GetMapping("/getMedicalsHistoryByCategory/{category}")
	List<MedicalHistoryDTO> getMedicalsHistoryByCategory( @PathVariable String category ) {
		
		LOGGER.info("/getMedicalsHistoryByCategory/{category} , pathVariable={ category = " + category + " } " );
		
		List<MedicalHistory> listMedicalHistory = service.getMedicalsHistoryByType(category);
		
		return listMedicalHistory.stream().map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) ).toList();	
	}
	
	@GetMapping("/getMedicalsHistoryByTypeAndCategory/{type}/{category}")
	List<MedicalHistoryDTO> getMedicalsHistoryByTypeAndCategory( @PathVariable String type, @PathVariable String category ) {
		
		LOGGER.info("/getMedicalsHistoryByType/{type} , pathVariable={ type = " + type + ", category=" + category + " }");
		
		List<MedicalHistory> listMedicalHistory = service.getMedicalsHistoryByType(category);
		
		return listMedicalHistory.stream().map( mh -> modelMapper.map( mh, MedicalHistoryDTO.class) ).toList();	
	}

	@GetMapping("/getMedicalHistoryById/{id}")
	public ResponseEntity<?> getMedicalHistoryById( @PathVariable long id ) {
		
		// check message
		LOGGER.info("/getMedicalHistoryById , pathVariable={id=" + id + "}" );
		
		MedicalHistory medicalHistory = service.getMedicalHistoryById( id );
		if( medicalHistory == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Medical History with id=" + id + " not found in the database ");
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map( medicalHistory, MedicalHistoryDTO.class ) );
		
	}
	
	@GetMapping("/getMedicalHistoryTypes")
	public List<String> getMedicalHistoryTypes() {
		
		return service.getMedicalHistoryTypes();
	}
	
	@GetMapping("/getMedicalHistoryCategories")
	public List<String> getMedicalHistoryCategories() {
		
		return service.getMedicalHistoryCategories();
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	
	// POST ---------------------------------------------------------------------------------------------------------------

	@PostMapping( value="/postMedicalHistory", params= { "name" } )
	public ResponseEntity<?> postMedicalHistory(  @RequestParam("name") String name, 
									 	@RequestParam( defaultValue = "" ) String type , 
									 	@RequestParam( defaultValue = "" ) String category,
									 	@RequestParam( defaultValue = "" ) String description ) {
	
		// check message
		LOGGER.info("MedicalHistoryRestController --> postMedicalHistory "
					+ ", params={name=" + name + ",type=" + type + ", category=" + category + ", description=" + description + "} ");
		
		// NOTE: Can be a good idea check on UNIQUE Medical History Name? For avoid 2 or more medical histories with the same name?
		// NOTE: Can be a good idea set type and/ or category as mandatory ?
		
		MedicalHistory medicalHistory = new MedicalHistory();
		
		try {
		
			medicalHistory.setName(name);
			
			if( ! type.equals("") ) medicalHistory.setType(type);
			if( ! category.equals("") ) medicalHistory.setCategory(category);
			medicalHistory.setDescription(description);
			
			service.postMedicalHistory(medicalHistory);
			
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( medicalHistory );
	}
		
	// PUT ----------------------------------------------------------------------------------------------------------------

	@PutMapping( value="/putMedicalHistory", params= {"id"} )
	public ResponseEntity<?> putMedicalHistory(  @RequestParam("id") long id, 
									 	@RequestParam( defaultValue =  "sdms_nothing-nessun-valore-passato") String name,
									 	@RequestParam( defaultValue =  "sdms_nothing-nessun-valore-passato") String type,
									 	@RequestParam( defaultValue =  "sdms_nothing-nessun-valore-passato") String category,
									 	@RequestParam( defaultValue =  "sdms_nothing-nessun-valore-passato") String description ) {
	
		// check message
		LOGGER.info("MedicalHistoryRestController --> putMedicalHistory "
					+ ", params={id=" + id + ",name=" + name + ",type=" + type + ", category=" + category + ", description=" + description + "} ");
		
		MedicalHistory medicalHistory = service.getMedicalHistoryById(id);
		// check medicalHistory 
		if( medicalHistory == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Error: Medical History with id =" + id + " not found in the database" );
		
		try {
		
			if( ! name.equals("sdms_nothing-nessun-valore-passato") ) medicalHistory.setName(name);
			if( ! type.equals("sdms_nothing-nessun-valore-passato") ) medicalHistory.setType(type);
			if( ! category.equals("sdms_nothing-nessun-valore-passato") ) medicalHistory.setCategory(category);
			if( ! description.equals("sdms_nothing-nessun-valore-passato") ) medicalHistory.setDescription(description);
			
			service.putMedicalHistory(medicalHistory);
			
			
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( medicalHistory );
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	
	// DELETE --------------------------------------------------------------------------------------------------------------
	
	@DeleteMapping( value="/deleteMedicalHistoryById", params = {"id"} )
	public ResponseEntity<?> deleteMedicalHistoryById( @RequestParam("id") long id ) {

		// check message
		LOGGER.info("MedicalHistoryRestController --> deleteMedicalHistoryById , params={id=" + id + "} ");
		
		MedicalHistory medicalHistory = service.getMedicalHistoryById( id );
		if( medicalHistory == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Medical History with id=" + id + " not found in the database ");
		
		MedicalHistoryDTO medicalHistoryDTO = modelMapper.map(medicalHistory, MedicalHistoryDTO.class);
		
		service.deleteMedicalHistoryById(id);
		
		return ResponseEntity.status( HttpStatus.OK ).body( medicalHistoryDTO );
		
	}
	
	// --------------------------------------------------------------------------------------------------------------------

}
