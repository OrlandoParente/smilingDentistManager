package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import sdms.repository.AppointmentRepository;
import sdms.repository.EmployeeRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.HasProfessionalRoleRepository;
import sdms.repository.WorkPeriodRepository;

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
	
	@Mock
	HasProfessionalRoleRepository hasProfessionalRoleRepository;
	
	@Mock
	ExpenseRepository expenseRepository;
	
	@Mock
	AppointmentRepository appointmentRepository;
	
	@Mock
	WorkPeriodRepository workPeriodRepository;
	
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
	
	@Test
	public void testGetEmployeesByName() {
	
		String name = "Nome";
		
		// Simulate the database ----------------------------
		Employee employee1 = new Employee();
		Long id1 = 1L;
		String surname1 = "Cognome1";
		employee1.setId(id1);
		employee1.setName(name);
		employee1.setSurname(surname1);
		
		Employee employee2 = new Employee();
		Long id2 = 2L;
		String surname2 = "Cognome2";
		employee2.setId(id2);
		employee2.setName(name);
		employee2.setSurname(surname2);
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(employee1);
		employees.add(employee2);
		
		when( employeeRepository.findByName( name ) ).thenReturn( employees );
		// --------------------------------------------------
							
		// test ---------------------------------------------
		List<Employee> result = employeeService.getEmployeesByName( name );
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals( result.size(), employees.size() );
		
		for( int i = 0; i < result.size(); i ++ ) {
			assertEquals( result.get(i).getId(), employees.get(i).getId() );
			assertEquals( name , employees.get(i).getName() );
		}
		// --------------------------------------------------	
		
	}
	
	// public List<Employee> getEmployeesBySurname( String surname );
	
	@Test
	public void testGetEmployeesBySurname() {
		
		String surname = "Nome";
		
		// Simulate the database ----------------------------
		Employee employee1 = new Employee();
		Long id1 = 1L;
		String name1 = "Cognome1";
		employee1.setId(id1);
		employee1.setSurname( surname );
		employee1.setName(name1);
		
		Employee employee2 = new Employee();
		Long id2 = 2L;
		String name2 = "Cognome2";
		employee2.setId(id2);
		employee2.setSurname( surname );
		employee2.setName( name2 );
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(employee1);
		employees.add(employee2);
		
		when( employeeRepository.findBySurname( surname ) ).thenReturn( employees );
		// --------------------------------------------------
							
		// test ---------------------------------------------
		List<Employee> result = employeeService.getEmployeesBySurname( surname );
		// --------------------------------------------------
							
		// check --------------------------------------------
		assertEquals( result.size(), employees.size() );
		
		for( int i = 0; i < result.size(); i ++ ) {
			assertEquals( result.get(i).getId(), employees.get(i).getId() );
			assertEquals( surname , employees.get(i).getSurname() );
		}
		// --------------------------------------------------	
		
	}
	
	// public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	
	
	// public List<Employee> getEmployeesByProfessionalRoleId( long professionalRoleId );
	
	
	// public List<Employee> getEmployeesByPartialKeyWordOverAllFields( String keyWord );
	
	
	// public List<ProfessionalRole> getEmployeeProfessionalRole( long idEmployee );
	
	
	// public Employee getEmployeeById( Long id );
	@Test
	public void testGetEmployeeById() {
	    Long id = 10L;
	    Employee employee = new Employee();
	    employee.setId(id);

	    when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

	    Employee result = employeeService.getEmployeeById(id);

	    assertNotNull(result);
	    assertEquals(id, result.getId());
	}
	
	// public Employee getEmployeeByEMail( String eMail );
	@Test
	public void testGetEmployeeByEMail() {
	    String email = "test@email.com";
	    Employee employee = new Employee();
	    employee.seteMail(email);

	    when(employeeRepository.findByEMail(email)).thenReturn(Optional.of(employee));

	    Employee result = employeeService.getEmployeeByEMail(email);

	    assertNotNull(result);
	    assertEquals(email, result.geteMail());
	}

	
	//		// title e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na
	//		boolean postEmployee( String name, String surname, String title, String phone_number ) throws SQLException;
	//		boolean postEmployee( String name, String surname, String title, String birth_date, String phone_number, String phone_number_2, String e_mail ) throws SQLException;
	
	// public void postEmployee( Employee employee );
	//		boolean putEmployeeById( String id, String name, String surname, String title, String birth_date, 
	//								String phone_number, String phone_number_2, String e_mail ) throws SQLException;
	
	@Test
	public void testPostEmployee() {
	    Employee employee = new Employee();
	    employee.setName("Mario");

	    employeeService.postEmployee(employee);

	    verify(employeeRepository, times(1)).save(employee);
	}
	
	
	// public void putEmployee( Employee employee );
	@Test
	public void testPutEmployee() {
	    Employee employee = new Employee();
	    employee.setId(1L);
	    employee.setSurname("Rossi");

	    employeeService.putEmployee(employee);

	    verify(employeeRepository, times(1)).save(employee);
	}
	
	
	// public void deleteEmployeeById( Long id );
	@Test
	public void testDeleteEmployeeById() {
	    Long id = 42L;
	    Employee employee = new Employee();
	    employee.setId(id);

	    when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
	    when(hasProfessionalRoleRepository.findByEmployee(employee)).thenReturn(new ArrayList<>());
	    when(expenseRepository.findByEmployee(employee)).thenReturn(new ArrayList<>());
	    when(appointmentRepository.findByDoctor(employee)).thenReturn(new ArrayList<>());
	    when(workPeriodRepository.findByEmployee(employee)).thenReturn(new ArrayList<>());

	    employeeService.deleteEmployeeById(id);

	    verify(employeeRepository, times(1)).delete(employee);
	}

}
