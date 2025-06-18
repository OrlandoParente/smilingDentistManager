package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	
	@Test
	public void testGetProfessionalRolesByEmployee () {
		
		// Simulate the database ----------------------------
		Employee employee = new Employee();
		Long idEmployee = 1L;
		employee.setId( idEmployee );
		
		ProfessionalRole professionalRole = new ProfessionalRole();
		professionalRole.setId(1L);
		
		HasProfessionalRole hasProfessionalRole = new HasProfessionalRole();
		Long profRoleId = 2L;
		hasProfessionalRole.setId( profRoleId );
		hasProfessionalRole.setEmployee(employee);
		hasProfessionalRole.setProfessionalRole(professionalRole);
		
		
		// Employee employee = new Employee();
		// employee.setId(1L);
		
		ProfessionalRole professionalRole1 = new ProfessionalRole();
		professionalRole.setId(2L);
		
		HasProfessionalRole hasProfessionalRole1 = new HasProfessionalRole();
		Long profRoleId1 = 3L;
		hasProfessionalRole1.setId( profRoleId1 );
		hasProfessionalRole1.setEmployee(employee);
		hasProfessionalRole1.setProfessionalRole(professionalRole1);
		
		
		List< HasProfessionalRole > hasProfessionalRoles = new ArrayList<>();
		hasProfessionalRoles.add( hasProfessionalRole );
		hasProfessionalRoles.add( hasProfessionalRole1 );
		
		List<ProfessionalRole> expectedProfRoles = hasProfessionalRoles.stream().map( hpr ->  hpr.getProfessionalRole() ).toList();
		
		when( employeeRepository.findById(idEmployee) ).thenReturn( Optional.of( employee ) );
		when( hasProfessionalRoleRepository.findByEmployee( any( Employee.class )) ).thenReturn( hasProfessionalRoles );
		// --------------------------------------------------
							
		// test ---------------------------------------------
		List< ProfessionalRole > result = hasProfessionalRoleService.getProfessionalRolesByEmployee( idEmployee );
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals( expectedProfRoles.size(), result.size() );
		
		// System.out.println( "expectedProfRoles size : " + expectedProfRoles.size() + " - result size :" + result.size() );
		
		for( int i = 0; i < expectedProfRoles.size(); i ++ ) {
		
			// System.out.println( i + " -> " + expectedProfRoles.get(i).getId() + " - " + i + " -> " + result.get(i).getId() );
			assertEquals( expectedProfRoles.get(i).getId() , result.get(i).getId() );
		}
		
		// --------------------------------------------------	
	}
	
	// public List<Employee> getEmployeesByProfessionalRole( long idProfessionalRole );
	// public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	
	
	// DELETE
	// public void deleteLinkEmployeeWithProfessionalRole( long idEmployee, long idProfessionalRole );
	// public void deleteLinkEmployeeWithProfessionalRole( long id );

}
