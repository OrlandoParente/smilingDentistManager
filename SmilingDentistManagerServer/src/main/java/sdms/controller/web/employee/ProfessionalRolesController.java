package sdms.controller.web.employee;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.ProfessionalRoleDTO;
import sdms.service.ProfessionalRoleServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/professional-roles")
public class ProfessionalRolesController {
	
	private final Logger LOGGER = LoggerFactory.getLogger( ProfessionalRolesController.class );
	
	@Autowired
	ProfessionalRoleServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"", "/" ,"/professional-roles"})
	public String getProfessionalRolesPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		List<ProfessionalRoleDTO> listProfessionalRoleDTO = service.getProfessionalRoles().stream()
				.map( professionalRole -> modelMapper.map(professionalRole, ProfessionalRoleDTO.class ) )	// map to DTO class
				.sorted( Comparator.comparing( profRoleDTO -> profRoleDTO.getName() ) )						// sort by name
				.toList();
		
		// check print
		for( ProfessionalRoleDTO pr : listProfessionalRoleDTO )
			LOGGER.info( "Sorted by name: " + pr );
		
		
		model.addAttribute("professionalRoles", listProfessionalRoleDTO);
		
		return "employee/professional-roles/professional-roles";
	}

}
