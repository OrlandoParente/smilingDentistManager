package sdms.controller.web.employee;

import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.EmployeeDTO;
import sdms.dto.ProfessionalRoleDTO;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ProfessionalRoleServiceInterface;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/personal-profile")
public class PersonalProfileController {

	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	ProfessionalRoleServiceInterface professionalRoleService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping( value= {"", "/", "/personal-profile"} )
	public String personalProfilePage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		// ---------------------------------------------------------------------------------------------
		
		// Fetch idUser from cookies -------------------------------------------------------------------
		String idUserStr = WebClientCookieManager.getCookieValue( request, WebClientCookieManager.ID_USER );
		long idUser = Long.parseLong(idUserStr);
		// ---------------------------------------------------------------------------------------------
		
		// Fetch employee
		EmployeeDTO employee = modelMapper.map( employeeService.getEmployeeById(idUser) , EmployeeDTO.class );
		
		// Fetch permission
		List<Integer> permissions = UserRoleManager.getEmployeePermissions();
		
		// Fetch professional roles liked to the employee (user)
		List<ProfessionalRoleDTO> employeeProfessionalRoles = employeeService.getEmployeeProfessionalRole(idUser).stream()
																.sorted( Comparator.comparing( pr -> pr.getName() ) )
																.map( pr -> modelMapper.map(pr, ProfessionalRoleDTO.class) )
																.toList();
		// Fetch all professional roles available
		List<ProfessionalRoleDTO> professionalRoles = professionalRoleService.getProfessionalRoles().stream()
																.sorted( Comparator.comparing( pr -> pr.getName() ) )
																.map( pr -> modelMapper.map(pr, ProfessionalRoleDTO.class) )
																.toList();
		
		// Add stuff to the model
		model.addAttribute("employee", employee);
		model.addAttribute("permissions", permissions);
		model.addAttribute("employeeProfessionalRoles", employeeProfessionalRoles);
		model.addAttribute("professionalRoles", professionalRoles);
		
		return "employee/personal-profile/personal-profile";
	}
}










