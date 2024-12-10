package sdms.service;

import java.util.List;

import sdms.model.Employee;

public interface EmployeeServiceInterface {

	public long getLastCustomerId();
	
	public List<Employee> getEmployees();
	public List<Employee> getEmployeesByName( String name );
	public List<Employee> getEmployeesBySurname( String surname );
	public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	public List<Employee> getEmployeesByPartialKeyWordOverAllFields( String keyWord );
	
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
	
}
