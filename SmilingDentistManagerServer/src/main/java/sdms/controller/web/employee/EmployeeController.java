package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.ProfessionalRoleDTO;
import sdms.dto.join.EmployeeExpenseProfessionalRoleWorkPeriodDTO;
import sdms.model.Employee;
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
	public String mainCalendarPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		// Fetch professionalRoles ---------------------------------------------------------------------

		List<ProfessionalRoleDTO> professionalRoles = professionalRoleService.getProfessionalRoles().stream()
														.map( pr -> modelMapper.map(pr, ProfessionalRoleDTO.class) )
														.toList();
		// ---------------------------------------------------------------------------------------------
		
		// Fetch joinEmployees -------------------------------------------------------------------------
		List<Employee> employees = employeeService.getEmployees();
		
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
