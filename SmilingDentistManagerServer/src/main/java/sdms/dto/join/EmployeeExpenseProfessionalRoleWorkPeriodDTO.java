package sdms.dto.join;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import sdms.dto.EmployeeDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.ProfessionalRoleDTO;
import sdms.dto.WorkPeriodDTO;
import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.service.EmployeeServiceInterface;

@Component
public class EmployeeExpenseProfessionalRoleWorkPeriodDTO {
	
	private static Logger LOGGER = LoggerFactory.getLogger(EmployeeExpenseProfessionalRoleWorkPeriodDTO.class);
	
	private EmployeeDTO employeeDTO;
	private List<ExpenseDTO> expensesDTO;
	private List<ProfessionalRoleDTO> professionalRolesDTO;
	private List<WorkPeriodDTO> workPeriodsDTO;
		
	// Empty constructor
	public EmployeeExpenseProfessionalRoleWorkPeriodDTO() {
		// for avoid error in thymeleaf
		this.employeeDTO = new EmployeeDTO();
		this.expensesDTO = new ArrayList<ExpenseDTO>();
		this.professionalRolesDTO = new ArrayList<ProfessionalRoleDTO>();
		this.workPeriodsDTO = new ArrayList<WorkPeriodDTO>();
	}


	
	public EmployeeExpenseProfessionalRoleWorkPeriodDTO buildFromEmployeeId( long idEmployee, EmployeeServiceInterface employeeService, ModelMapper modelMapper ) {
		
		LOGGER.info("buildFromEmployeeId");
		
		Employee employee = employeeService.getEmployeeById(idEmployee);
		
		this.employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		
		// Fetch Expenses
		if( employee.getExpenses() != null ) {
			this.expensesDTO = employee.getExpenses().stream().map( ex -> modelMapper.map(ex, ExpenseDTO.class) ).toList();	
		}
		
		// Fetch Professional Roles
		if( employee.getHasProfessionalRoles() != null ) {
			
			this.professionalRolesDTO = new ArrayList<ProfessionalRoleDTO>();
			
			for( HasProfessionalRole hpr : employee.getHasProfessionalRoles() ) {
				this.professionalRolesDTO.add( modelMapper.map( hpr.getProfessionalRole(), ProfessionalRoleDTO.class ) );
			} 			
		}
			
		// Fetch work Periods
		if( employee.getWorkPeriods() != null ) {
			this.workPeriodsDTO = employee.getWorkPeriods().stream().map( wp -> modelMapper.map( wp, WorkPeriodDTO.class ) ).toList();
		}
	
		return this;
	}


	// GETTERS AND SETTERS

	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}



	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}



	public List<ExpenseDTO> getExpensesDTO() {
		return expensesDTO;
	}



	public void setExpensesDTO(List<ExpenseDTO> expensesDTO) {
		this.expensesDTO = expensesDTO;
	}



	public List<ProfessionalRoleDTO> getProfessionalRolesDTO() {
		return professionalRolesDTO;
	}



	public void setProfessionalRolesDTO(List<ProfessionalRoleDTO> professionalRolesDTO) {
		this.professionalRolesDTO = professionalRolesDTO;
	}



	public List<WorkPeriodDTO> getWorkPeriodsDTO() {
		return workPeriodsDTO;
	}



	public void setWorkPeriodsDTO(List<WorkPeriodDTO> workPeriodsDTO) {
		this.workPeriodsDTO = workPeriodsDTO;
	}
	
		
}
