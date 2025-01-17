package sdms.controller.api;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

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

import sdms.dto.DentalMaterialDTO;
import sdms.model.DentalMaterial;
import sdms.service.DentalMaterialServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class DentalMaterialRestController {

	private Logger LOGGER = LoggerFactory.getLogger( DentalMaterialRestController.class );
	
	@Autowired
	DentalMaterialServiceInterface service;
	
	@Autowired
	ExpenseServiceInterface expenseService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;
	
	// READ ------------------------------------------------------
	@GetMapping("/getDentalMaterialById/{id}")
	public DentalMaterialDTO getDentalMaterialById( @PathVariable Long id ) {
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		DentalMaterialDTO dentalMaterialDTO = modelMapper.map( dentalMaterial, DentalMaterialDTO.class);	
		
		return dentalMaterialDTO;
	}
	
	@GetMapping("/getDentalMaterials")
	public List<DentalMaterialDTO> getDentalMaterials() {
		
		List<DentalMaterial> dentalMaterials = service.getDentalMaterials();
		List<DentalMaterialDTO> dentalMaterialDTOs = dentalMaterials.stream()
														.map( dm -> modelMapper.map( dm, DentalMaterialDTO.class ) )
														.toList();
		
		return dentalMaterialDTOs;
	}
	
	
	// CREATE ----------------------------------------------------
	@PostMapping( value= {"/postDentalMaterial"} , params= {"name"} )
	public ResponseEntity<?> postDentalMaterial(  @RequestParam String name,		// Mandatory parameter 
									 		      @RequestParam( defaultValue = "0") int quantity, 
									 		      @RequestParam( defaultValue = "" ) String description, 
									 		      @RequestParam( defaultValue = "0.0" )  double cost ) {
		
		DentalMaterial dentalMaterial = new DentalMaterial();
		
		try {
			
			dentalMaterial.setName(name);
			dentalMaterial.setQuantity(quantity);
			dentalMaterial.setDescription(description);
			dentalMaterial.setCost(cost);
			
			service.postDentalMaterial(dentalMaterial);
		
		} catch( Exception e ) {
			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(dentalMaterial, DentalMaterialDTO.class) );
	}
	
//	@PostMapping("/postDentalMaterial")
//	public void postDentalMaterial(  @RequestParam("name") String name, @RequestParam("quantity") double quantity, 
//										@RequestParam("description") String description, @RequestParam("cost")  double cost ) {
//		
//		DentalMaterial dentalMaterial = new DentalMaterial();
//		dentalMaterial.setName(name);
//		dentalMaterial.setQuantity(quantity);
//		dentalMaterial.setDescription(description);
//		dentalMaterial.setCost(cost);
//		
//		service.postDentalMaterial(dentalMaterial);
//	}
	

	
	// UPDATE ----------------------------------------------------

	@PutMapping( value= {"/putDentalMaterial"}, params= {"id"})
	public ResponseEntity<?> putDentalMaterial(   @RequestParam long id,
												  @RequestParam( defaultValue = "" ) String name,		
											      @RequestParam( defaultValue = "-1000000") int quantity, 
											      @RequestParam( defaultValue = "sdms-none_nessuna_descrizione_nothing" ) String description, 
											      @RequestParam( defaultValue = "-1000000" )  double cost ) {
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("NOT FOUND: Dental Material to update not found in the database");
			
		try {
			
			if( ! name.equals("") ) dentalMaterial.setName(name);
			if( quantity != -1000000 ) dentalMaterial.setQuantity(quantity);
			if( ! description.equals("sdms-none_nessuna_descrizione_nothing") ) dentalMaterial.setDescription(description);
			if( cost != -1000000 ) dentalMaterial.setCost(cost);
			
			service.putDentalMaterial(dentalMaterial);
		
		} catch( Exception e ) {
			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body( modelMapper.map(dentalMaterial, DentalMaterialDTO.class) );
	}
	
//	@PutMapping("/putDentalMaterial")
//	public ResponseEntity<?> putDentalMaterial(  @RequestParam("id") long id, @RequestParam("name") String name, 
//									@RequestParam("quantity") double quantity, @RequestParam("description") String description, 
//									@RequestParam("cost")  double cost ) {
//		
//		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
//		
//		if( dentalMaterial == null )
//			return ResponseEntity.status( HttpStatus.NOT_FOUND )
//					.body("NOT FOUND: Dental Material to update not found in the database");
//					
//		dentalMaterial.setName(name);
//		dentalMaterial.setQuantity(quantity);
//		dentalMaterial.setDescription(description);
//		dentalMaterial.setCost(cost);
//		
//		service.putDentalMaterial(dentalMaterial);
//		
//		return ResponseEntity.status(HttpStatus.OK).body(dentalMaterial);
//	}
	
	// PATCH
	@PatchMapping( value= {"/decreaseDentalMaterialQuantity"}, params= {"id", "quantity"})
	public ResponseEntity<?> decreaseDentalMaterialQuantity( @RequestParam long id, @RequestParam int quantity ){
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("NOT FOUND: Dental Material to update not found in the database");

		try {
			
			service.decreaseDentalMaterialQuantity(id, quantity);
		
		} catch( Exception e ) {
			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body( modelMapper.map(dentalMaterial, DentalMaterialDTO.class) );
	}
	
	@PatchMapping( value= {"/increaseDentalMaterialQuantity"}, params= {"id", "quantity"})
	public ResponseEntity<?> increaseDentalMaterialQuantity( @RequestParam long id, @RequestParam int quantity ){
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("NOT FOUND: Dental Material to update not found in the database");

		try {
			
			service.increaseDentalMaterialQuantity(id, quantity);
		
		} catch( Exception e ) {
			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body( modelMapper.map(dentalMaterial, DentalMaterialDTO.class) );
	}
	
	// Increase dental Material and save that in expenses 
	@PatchMapping( value= {"/increaseDentalMaterialQuantity"}, params= {"id", "quantity","amount","date"})
	public ResponseEntity<?> increaseDentalMaterialQuantity( @RequestParam long id, @RequestParam int quantity,
															  @RequestParam double amount, @RequestParam String date,
															  @RequestParam( defaultValue = "" ) String tag ){
		
		DentalMaterial dentalMaterial = service.getDentalMaterialById(id);
		
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("NOT FOUND: Dental Material to update not found in the database");

		try {
			service.increaseDentalMaterialQuantity(id, quantity);
		
		} catch( Exception e ) {
			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		// Post expense
		
		try {
			LocalDate ldDate = dateAndTimeManager.parseDate(date);
			expenseService.postDentalMaterialPurchase(id, amount, quantity, ldDate, tag);
		
		} catch( DateTimeParseException e ) {
			
			System.err.println( e.getMessage() );
			
			// If it can't upload the purchase in the expense, delete the change
			service.decreaseDentalMaterialQuantity(id, quantity);
			
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
			
		} catch( Exception e ) {
			
			System.err.println( e.getMessage() );
			
			// If it can't upload the purchase in the expense, delete the change
			service.decreaseDentalMaterialQuantity(id, quantity);
			
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body( modelMapper.map(dentalMaterial, DentalMaterialDTO.class) );
	}
	
	// DELETE ----------------------------------------------------
	@DeleteMapping("/deleteDentalMaterial")
	public void deleteDentalMaterial( @RequestParam("id") long id ) {
		
		service.deleteDentalMaterial(id);
	}
}
