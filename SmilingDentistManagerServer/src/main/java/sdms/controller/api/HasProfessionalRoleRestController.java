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

import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;
import sdms.service.EmployeeServiceInterface;
import sdms.service.HasProfessionalRoleServiceInterface;
import sdms.service.ProfessionalRoleServiceInterface;

@RestController
public class HasProfessionalRoleRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger( HasProfessionalRoleRestController.class );
	
	@Autowired
	private HasProfessionalRoleServiceInterface service;
	
	@Autowired
	private EmployeeServiceInterface employeeService;
	
	@Autowired
	private ProfessionalRoleServiceInterface professionalRoleService;
		
	@PostMapping( value = "/postLinkEmployeeToProfessionalRole", params = {"idEmployee", "idProfessionalRole"} )
	public ResponseEntity<?> postLinkEmployeeToProfessionalRole( @RequestParam long idEmployee, 
													    		 @RequestParam( defaultValue = "-1")	long idProfessionalRole ) {
		// check message
		LOGGER.info( "HasProfessionalRoleRestController ->  postLinkEmployeeToProfessionalRole "
					+ ", params={idEmployee=" + idEmployee + ", idProfessionalRole=" + idProfessionalRole + " }" );
		
		// if idProfessionalRole == -1 Do Nothing 
		if( idProfessionalRole == -1 )
			return ResponseEntity.status(HttpStatus.OK).build();
		
		
		HasProfessionalRole hasProfessionalRole = new HasProfessionalRole();
		
		// Find and check his existence of Employee
		Employee employee = employeeService.getEmployeeById(idEmployee);
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found in the database");
		
		// Find and check his existence of Professional Role
		ProfessionalRole professionalRole = professionalRoleService.getProfessionalRoleById(idProfessionalRole);
		if( professionalRole == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Professional Role not found in the database");
		
		// Create the link between Employee and Professional Role
		hasProfessionalRole.setEmployee(employee);
		hasProfessionalRole.setProfessionalRole(professionalRole);
		
		// save the link on the database
		service.postLinkEmployeeToProfessionalRole( hasProfessionalRole );
		
		return ResponseEntity.status( HttpStatus.OK ).body( hasProfessionalRole );
		
	}
	
	
	
	
	@DeleteMapping( value = "/deleteLinkEmployeeWithProfessionalRole", params = {"idEmployee", "idProfessionalRole"} )
	public void deleteLinkEmployeeWithProfessionalRole( @RequestParam("idEmployee") long idEmployee, 
													    @RequestParam("idProfessionalRole")	long idProfessionalRole ) {
		// check message
		LOGGER.info( "HasProfessionalRoleRestController ->  deleteLinkEmployeeWithProfessionalRole " 
				+ ", params={idEmployee=" + idEmployee + ", idProfessionalRole=" + idProfessionalRole + " }" );
		
		service.deleteLinkEmployeeWithProfessionalRole( idEmployee, idProfessionalRole );
	
	}
	
}
