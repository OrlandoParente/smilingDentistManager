//package sdms.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import sdms.model.ProfessionalRole;
//import sdms.service.old.ServicesInterface;
//
//@RestController
//public class ProfessionalRoleRestController {
//
//	// Spring si occupa di associare la giusta implementazione dell'interfaccia service
//	@Autowired
//	@Qualifier("mainService")
//	ServicesInterface service;
//	
//	
//	@GetMapping("/getProfessionalRoles")
//	public ArrayList<ProfessionalRole> getProfessionalRoles(){
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> getProfessionalRoles ");
//		 
//		try {
//			
//			return service.getProfessionalRoles();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//		
//	}
//
//	@GetMapping("/getProfessionalRolesAssociatedToIdEmployee/{id_employee}")
//	public ArrayList<ProfessionalRole> getProfessionalRolesAssociatedToIdEmployee( @PathVariable String id_employee ){
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> getProfessionalRolesAssociatedToIdEmployee ");
//		 
//		try {
//			
//			return service.getProfessionalRolesAssociatedToIdEmployee( id_employee );
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//		
//	}
//
//	
//	
//	
//	@PostMapping( value="/postProfessionalRole" , params= {"name"} )
//	public boolean postProfessionalRole( @RequestParam("name") String name ) {
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> postProfessionalRoles -> params : " + name );
//		
//		try {
//			return service.postProfessionalRole(name);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//	@PostMapping( value="/postProfessionalRole" , params= {"name", "description"} )
//	public boolean postProfessionalRole( @RequestParam("name") String name, @RequestParam("description") String description ) {
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> postProfessionalRoles -> params : " + name + "," + description );
//		
//		try {
//			return service.postProfessionalRole(name, description);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//	
//	@PutMapping( value="/putProfessionalRoleById", params = {"id", "name", "description"} )
//	public boolean putProfessionalRoleById( @RequestParam("id") String id, @RequestParam("name") String name, 
//											@RequestParam("description") String description  ) {
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> putProfessionalRolesById -> params : " + id + "," + name + "," + description );
//		
//		try {
//			return service.putProfessionalRoleById( id, name,	description );
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return false;
//		
//	}
//	
//	@DeleteMapping( value="/deleteProfessionalRoleById", params = {"id"} )
//	public boolean deleteProfessionalRoleById( @RequestParam("id") String id  ) {
//		
//		// Stampa di controllo
//		System.out.println("ProfessionalRoleRestController --> deleteProfessionalRoleById --> id -> " + id );	
//		
//		try {
//			service.deleteProfessionalRoleById( id );
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
//
//}
