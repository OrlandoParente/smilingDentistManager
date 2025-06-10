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
import sdms.repository.EmployeeRepository;
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
	
	@InjectMocks
	ProfessionalRoleService professionalRoleService;
	
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
	
//	@Test
	public void testPostLinkEmployeeToProfessionalRole() {
		
		// Simulate the database ----------------------------
		
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		
		// --------------------------------------------------
							
		// check --------------------------------------------
		
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
