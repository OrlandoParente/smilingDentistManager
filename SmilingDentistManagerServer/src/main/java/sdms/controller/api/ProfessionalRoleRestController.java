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

import sdms.dto.ProfessionalRoleDTO;
import sdms.model.ProfessionalRole;
import sdms.service.ProfessionalRoleServiceInterface;

@RestController
public class ProfessionalRoleRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger( ProfessionalRoleRestController.class );
	
	// Autowired: Spring si occupa di associare la giusta implementazione dell'interfaccia service

	@Autowired
	private ProfessionalRoleServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	// In this case ResponseEntity is not useful cause SpringBoot automatic manage 200 and 500 status codes 
	@GetMapping("/getProfessionalRoles")
	public List<ProfessionalRoleDTO> getProfessionalRoles(){
		
		// check message 
		LOGGER.info("ProfessionalRoleRestController --> getProfessionalRoles ");
		 		
		List<ProfessionalRole> professionalRoles = service.getProfessionalRoles();
		
		return professionalRoles.stream().map( pr -> modelMapper.map( pr, ProfessionalRoleDTO.class ) ).toList();
	}

	@GetMapping("/getProfessionalRolesAssociatedToIdEmployee/{idEmployee}")
	public List<ProfessionalRoleDTO> getProfessionalRolesAssociatedToIdEmployee( @PathVariable long idEmployee ){
		
		// Check message 
		LOGGER.info("ProfessionalRoleRestController --> getProfessionalRolesAssociatedToIdEmployee , pathVariable={idEmployee=" + idEmployee + "} ");
		 
		List<ProfessionalRole> professionalRoles = service.getProfessionalRolesAssociatedToIdEmployee(idEmployee);
		
		return professionalRoles.stream().map( pr -> modelMapper.map( pr, ProfessionalRoleDTO.class ) ).toList();
	}

	
	@PostMapping( value="/postProfessionalRole" , params= {"name"} )
	public ResponseEntity<?> postProfessionalRole( @RequestParam("name") String name, @RequestParam( defaultValue = "" ) String description ) {
		
		// Check message
		LOGGER.info("ProfessionalRoleRestController --> postProfessionalRoles , params ={name=" + name + ", description=" + description + "}" );
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setName(name);
		professionalRole.setDescription(description);
		
		try {
			service.postProfessionalRole( professionalRole );
		} catch( Exception e ) {
			// needs a best error code ?
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(professionalRole, ProfessionalRoleDTO.class) );
	}
	
	@PutMapping( value="/putProfessionalRole", params = {"id"} )
	public ResponseEntity<?> putProfessionalRoleById( @RequestParam("id") long id, 
										 @RequestParam( defaultValue = "" ) String name, 
										 // we need a string that the user don't want to use
										 @RequestParam( defaultValue = "sdm-server_nessuna_description_inserita-123" ) String description ) {
		
		// Check message 
		LOGGER.info("ProfessionalRoleRestController --> putProfessionalRolesById -> params={id=" + id + ",name=" + name + ",description=" + description + "}" );
		
		ProfessionalRole professionalRole = service.getProfessionalRoleById(id);
		
		if( professionalRole == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("NOT FOUND: Professional role with id " + id + " not found in the db");
		
//		professionalRole.setId(id);
		if( ! name.equals("") )	professionalRole.setName(name);
		if( ! description.equals("sdm-server_nessuna_description_inserita-123") ) professionalRole.setDescription(description);
		
		try {
			service.postProfessionalRole( professionalRole );
		} catch( Exception e) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(professionalRole, ProfessionalRoleDTO.class) );
	}
	
	@DeleteMapping( value="/deleteProfessionalRole", params = {"id"} )
	public ResponseEntity<?> deleteProfessionalRoleById( @RequestParam("id") long id  ) {
		
		// Check message 
		LOGGER.info("ProfessionalRoleRestController --> deleteProfessionalRoleById , params={id =" + id + "}" );	
		
		// This is for return the deleted object 
		ProfessionalRole professionalRole = service.getProfessionalRoleById(id);
		
		// If professional role not found return 404 not found
		if( professionalRole == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Error: expense with id=" + id + " not found in the database" );
		
		service.deleteProfessionalRoleById( id );

		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map( professionalRole , ProfessionalRoleDTO.class ) );
	}

}
