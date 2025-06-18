package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.Employee;
import sdms.repository.EmployeeRepository;

/*
  	public long getLastCustomerId();
	
	public List<Employee> getEmployees();
	public List<Employee> getEmployeesByName( String name );
	public List<Employee> getEmployeesBySurname( String surname );
	public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	public List<Employee> getEmployeesByProfessionalRoleId( long professionalRoleId );
	public List<Employee> getEmployeesByPartialKeyWordOverAllFields( String keyWord );
	
	public List<ProfessionalRole> getEmployeeProfessionalRole( long idEmployee );
	
	public Employee getEmployeeById( Long id );
	public Employee getEmployeeByEMail( String eMail );
//	
//	// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
//	boolean postEmployee( String name, String surname, String title, String phone_number ) throws SQLException;
//	boolean postEmployee( String name, String surname, String title, String birth_date, String phone_number, String phone_number_2, String e_mail ) throws SQLException;
//	
	public void postEmployee( Employee employee );
//	boolean putEmployeeById( String id, String name, String surname, String title, String birth_date, 
//							String phone_number, String phone_number_2, String e_mail ) throws SQLException;
//
	public void putEmployee( Employee employee );

	public void deleteEmployeeById( Long id );
 */

class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;
	
	@InjectMocks
	EmployeeService employeeService;
	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	// This method is for keep compatibility with desktop client
	// public long getLastCustomerId();
	
	@Test
	public void testGetLastCustomerId() {
		
		// Simulate the database ----------------------------
		Long lastId = 1L;
		
		when( employeeRepository.findMaxId() ).thenReturn( lastId );
		// --------------------------------------------------
							
		// test ---------------------------------------------
		Long result = employeeService.getLastCustomerId();
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals(result, lastId);
		// --------------------------------------------------	
	}
	
	// public List<Employee> getEmployees();
	

	@Test
	public void  testGetEmployees() {
		
		// Simulate the database ----------------------------
		Employee employee1 = new Employee();
		Long id1 = 1L;
		employee1.setId(id1);
		
		Employee employee2 = new Employee();
		Long id2 = 2L;
		employee1.setId(id2);
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(employee1);
		employees.add(employee2);
		
		when( employeeRepository.findAll() ).thenReturn( employees );
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		List<Employee> result = employeeService.getEmployees();
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals( result.size(), employees.size() );
		
		for( int i = 0; i < result.size(); i ++ ) {
			assertEquals( result.get(i).getId(), employees.get(i).getId() );
		}
		
		// --------------------------------------------------	
	}
	
	// public List<Employee> getEmployeesByName( String name );
	// public List<Employee> getEmployeesBySurname( String surname );
	// public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	// public List<Employee> getEmployeesByProfessionalRoleId( long professionalRoleId );
	// public List<Employee> getEmployeesByPartialKeyWordOverAllFields( String keyWord );
	// public List<ProfessionalRole> getEmployeeProfessionalRole( long idEmployee );
	// public Employee getEmployeeById( Long id );
	// public Employee getEmployeeByEMail( String eMail );
	
	//		// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
	//		boolean postEmployee( String name, String surname, String title, String phone_number ) throws SQLException;
	//		boolean postEmployee( String name, String surname, String title, String birth_date, String phone_number, String phone_number_2, String e_mail ) throws SQLException;
	
	// public void postEmployee( Employee employee );
	//		boolean putEmployeeById( String id, String name, String surname, String title, String birth_date, 
	//								String phone_number, String phone_number_2, String e_mail ) throws SQLException;
	
	// public void putEmployee( Employee employee );
	
	// public void deleteEmployeeById( Long id );

}
