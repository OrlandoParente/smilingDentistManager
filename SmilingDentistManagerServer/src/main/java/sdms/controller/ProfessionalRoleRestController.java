package sdms.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	// Spring si occupa di associare la giusta implementazione dell'interfaccia service

	@Autowired
	private ProfessionalRoleServiceInterface service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/getProfessionalRoles")
	public List<ProfessionalRoleDTO> getProfessionalRoles(){
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> getProfessionalRoles ");
		 		
		List<ProfessionalRole> professionalRoles = service.getProfessionalRoles();
		
		return professionalRoles.stream().map( pr -> modelMapper.map( pr, ProfessionalRoleDTO.class ) ).toList();
	}

	@GetMapping("/getProfessionalRolesAssociatedToIdEmployee/{idEmployee}")
	public List<ProfessionalRoleDTO> getProfessionalRolesAssociatedToIdEmployee( @PathVariable long idEmployee ){
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> getProfessionalRolesAssociatedToIdEmployee ");
		 
		List<ProfessionalRole> professionalRoles = service.getProfessionalRolesAssociatedToIdEmployee(idEmployee);
		
		return professionalRoles.stream().map( pr -> modelMapper.map( pr, ProfessionalRoleDTO.class ) ).toList();
	}

	
	
	
	@PostMapping( value="/postProfessionalRole" , params= {"name"} )
	public void postProfessionalRole( @RequestParam("name") String name ) {
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> postProfessionalRoles -> params : " + name );
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setName(name);
		
		service.postProfessionalRole( professionalRole );
	}
	
	@PostMapping( value="/postProfessionalRole" , params= {"name", "description"} )
	public void postProfessionalRole( @RequestParam("name") String name, @RequestParam("description") String description ) {
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> postProfessionalRoles -> params : " + name + "," + description );
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setName(name);
		professionalRole.setDescription(description);
		
		service.postProfessionalRole( professionalRole );
	}
	
	@PutMapping( value="/putProfessionalRoleById", params = {"id", "name", "description"} )
	public void putProfessionalRoleById( @RequestParam("id") long id, @RequestParam("name") String name, 
											@RequestParam("description") String description  ) {
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> putProfessionalRolesById -> params : " + id + "," + name + "," + description );
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setId(id);
		professionalRole.setName(name);
		professionalRole.setDescription(description);
		
		service.postProfessionalRole( professionalRole );
		
	}
	
	@DeleteMapping( value="/deleteProfessionalRoleById", params = {"id"} )
	public void deleteProfessionalRoleById( @RequestParam("id") long id  ) {
		
		// Stampa di controllo
		System.out.println("ProfessionalRoleRestController --> deleteProfessionalRoleById --> id -> " + id );	
		
		service.deleteProfessionalRoleById( id );
	}

}
