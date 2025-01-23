package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.ProfessionalRoleDTO;
import sdms.dto.join.EmployeeExpenseProfessionalRoleWorkPeriodDTO;
import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ProfessionalRoleServiceInterface;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceInterface employeeService;

	@Autowired
	ProfessionalRoleServiceInterface professionalRoleService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/employee","","/"})
	public String mainCalendarPage( HttpServletRequest request, Model model, 
									@RequestParam( defaultValue = "-1" ) Long idProfessionalRole,
									@RequestParam( defaultValue = "" ) String keyword ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		// Fetch professionalRoles ---------------------------------------------------------------------

		List<ProfessionalRoleDTO> professionalRoles = professionalRoleService.getProfessionalRoles().stream()
														.map( pr -> modelMapper.map(pr, ProfessionalRoleDTO.class) )
														.toList();
		// ---------------------------------------------------------------------------------------------
		
		// Fetch joinEmployees -------------------------------------------------------------------------
		List<Employee> employees = null;
		if( idProfessionalRole == -1 && keyword.equals("") ) {		// no filters
			employees = employeeService.getEmployees();
		} else if( keyword.equals("") ) {							// filter only by professional role
			employees = employeeService.getEmployeesByProfessionalRoleId(idProfessionalRole);
		} else {													// filter by keyword
			employees = employeeService.getEmployeesByPartialKeyWordOverAllFields(keyword);
			
			if( idProfessionalRole != -1 ) {						// filter by keyword and professional role
				employees = employees.stream().filter( e -> { 
					
					for(HasProfessionalRole hpr : e.getHasProfessionalRoles() ) {
						if( hpr.getProfessionalRole().getId() == idProfessionalRole )
							return true;
					} 
					return false;
					
				} ).toList();
			}
		} 
		
		// Remove user who is using the application ----------------------
		long idUser = Long.parseLong( WebClientCookieManager.getCookieValue(request, WebClientCookieManager.ID_USER) );
		int empIndex = -1;
		for( Employee emp : employees ) {
			if( emp.getId() == idUser )
				empIndex = employees.indexOf(emp);
		}
		if( empIndex != -1 ) employees.remove(empIndex);
		// ---------------------------------------------------------------
		
		List<EmployeeExpenseProfessionalRoleWorkPeriodDTO> joinEmployees = new ArrayList<EmployeeExpenseProfessionalRoleWorkPeriodDTO>();
		
		for( Employee employee : employees ) {
			
			joinEmployees.add(
						new EmployeeExpenseProfessionalRoleWorkPeriodDTO()
							.buildFromEmployeeId(employee.getId(), employeeService, modelMapper)
					);
			
		}
		// ---------------------------------------------------------------------------------------------
		
		
		model.addAttribute("joinEmployees", joinEmployees);
		model.addAttribute("professionalRoles", professionalRoles);
		model.addAttribute("permissions", UserRoleManager.getEmployeePermissions());
		
		return("employee/employees/employees");
	}
	
	
}
