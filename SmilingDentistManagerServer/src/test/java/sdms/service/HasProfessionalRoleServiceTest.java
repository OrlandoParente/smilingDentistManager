package sdms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.EmailSettings;
import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;
import sdms.repository.EmployeeRepository;
import sdms.repository.HasProfessionalRoleRepository;
import sdms.repository.ProfessionalRoleRepository;

/*
 * 	// CREATE
	public void postLinkEmployeeToProfessionalRole( HasProfessionalRole hasProfessionalRole );
	
	// READ
	public List<ProfessionalRole> getProfessionalRolesByEmployee( long idEmployee );
	public List<Employee> getEmployeesByProfessionalRole( long idProfessionalRole );
	public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	
	// UPDATE
	
	
	// DELETE
	public void deleteLinkEmployeeWithProfessionalRole( long idEmployee, long idProfessionalRole );
	public void deleteLinkEmployeeWithProfessionalRole( long id );

 */

class HasProfessionalRoleServiceTest {

	@Mock
	EmployeeRepository employeeRepository;	
	
	@Mock
	ProfessionalRoleRepository professionalRoleRepository;  
	
	@Mock
	HasProfessionalRoleRepository hasProfessionalRoleRepository;
	
	@InjectMocks
	HasProfessionalRoleService hasProfessionalRoleService;
	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
  	// CREATE
	// public void postLinkEmployeeToProfessionalRole( HasProfessionalRole hasProfessionalRole );
	
	@Test
	public void testPostLinkEmployeeToProfessionalRole() {
		
		// Simulate the database ----------------------------
		Employee employee = new Employee();
		employee.setId(1L);
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setId(1L);
		
		HasProfessionalRole hasProfessionalRole = new HasProfessionalRole();
		hasProfessionalRole.setEmployee(employee);
		hasProfessionalRole.setProfessionalRole(professionalRole);
		// --------------------------------------------------
							
		// test ---------------------------------------------
		hasProfessionalRoleService.postLinkEmployeeToProfessionalRole( hasProfessionalRole );
		// --------------------------------------------------
							
		// check --------------------------------------------
		verify( hasProfessionalRoleRepository , times(1) ).save( hasProfessionalRole );
		// --------------------------------------------------
		
	}
	
	// READ
	// public List<ProfessionalRole> getProfessionalRolesByEmployee( long idEmployee );
	// public List<Employee> getEmployeesByProfessionalRole( long idProfessionalRole );
	// public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	
	
	// DELETE
	// public void deleteLinkEmployeeWithProfessionalRole( long idEmployee, long idProfessionalRole );
	// public void deleteLinkEmployeeWithProfessionalRole( long id );

}
