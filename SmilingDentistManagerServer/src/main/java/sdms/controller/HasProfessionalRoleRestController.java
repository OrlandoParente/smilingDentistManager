//package sdms.controller;
//
//import java.sql.SQLException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import sdms.service.old.ServicesInterface;
//
//@RestController
//public class HasProfessionalRoleRestController {
//
//	@Autowired
//	@Qualifier("mainService")
//	private ServicesInterface service;
//	
//	public HasProfessionalRoleRestController() {
//		
//	}
//	
//	@PostMapping( value = "/postLinkEmployeeToProfessionalRole", params = {"id_employee", "id_professional_role"} )
//	public boolean postLinkEmployeeToProfessionalRole( @RequestParam("id_employee") String id_employee, 
//													    @RequestParam("id_professional_role")	String id_professional_role ) {
//		// check message
//		System.out.println( "HasProfessionalRoleRestController ->  postLinkEmployeeToProfessionalRole " );
//		
//		try {
//			return service.postLinkEmployeeToProfessionalRole( id_employee, id_professional_role );
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//	
//	
//	
//	@DeleteMapping( value = "/deleteLinkEmployeeWithProfessionalRole", params = {"id_employee", "id_professional_role"} )
//	public boolean deleteLinkEmployeeWithProfessionalRole( @RequestParam("id_employee") String id_employee, 
//													    @RequestParam("id_professional_role")	String id_professional_role ) {
//		// check message
//		System.out.println( "HasProfessionalRoleRestController ->  deleteLinkEmployeeWithProfessionalRole " );
//		
//		try {
//			return service.deleteLinkEmployeeWithProfessionalRole( id_employee, id_professional_role );
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//}
