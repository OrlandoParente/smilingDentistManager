package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdms.model.Employee;
import sdms.service.ServicesInterface;

@RestController
public class EmployeeRestController {
	
	@Autowired
	@Qualifier("mainService")
	private ServicesInterface service;
	
	public EmployeeRestController() {
	}
	
	@GetMapping("/getMaxIdEmployee")
	public int getMaxIdEmployee( ) {
		
		try {
			return service.getMaxIdFromTable("employee");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return -1;
	}
	
	@GetMapping("/getEmployees")
	public ArrayList<Employee> getEmployees(){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployees ");
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = service.getEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}
		
	@GetMapping("/getEmployeesByName/{name}")
	public ArrayList<Employee> getEmployeesByName( @PathVariable String name ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByName ");
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = service.getEmployeesByName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}
	
	
	@GetMapping("/getEmployeesBySurname/{surname}")
	public ArrayList<Employee> getEmployeesBySurname( @PathVariable String surname ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesBySurname ");
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = service.getEmployeesByName(surname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}
	
	// :[a-zA-Z &+-]* serve per accettare lo spazio
	@RequestMapping("/getEmployeesByProfessionalRoleName/{professional_role_name}")
	
	public ArrayList<Employee> getEmployeesByProfessionalRoleName( @PathVariable("professional_role_name") String professional_role_name){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByProfessionalRoleName -> " + professional_role_name );
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = service.getEmployeesByProfessionalRoleName(professional_role_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}


	
	
	@GetMapping("/getEmployeesByPartialKeyWordOverAllFields/{key_word}")
	public ArrayList<Employee> getEmployeesByPartialKeyWordOverAllFields( @PathVariable String key_word ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByPartialKeyWordOverAllFields -> key_word -> " + key_word );
		
		ArrayList<Employee> employees = null;
		
		try {
			employees = service.getEmployeesByPartialKeyWordOverAllFields( key_word );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
		
	}
	
	
	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmployeeById( @PathVariable String id ) {
		
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeeById ");
				
		
		Employee employee = null;
		
		try {
			employee = service.getEmployeeById( id );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return employee;
	}
	
	@PostMapping( value="/postEmployee", params = {"name","surname","title", "phone_number"} )
	public boolean postEmployee( @RequestParam("name") String name, @RequestParam("surname") String surname ,
								@RequestParam("title") String title, @RequestParam("phone_number") String phone_number) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> postEmployee ");
				
		try {
			
			return service.postEmployee(name, surname, title, phone_number);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
		return false;
	}
	

	@PostMapping( value="/postEmployee", params = {"name","surname","title", "birth_date","phone_number", "phone_number_2","e_mail"} )
	public boolean postEmployee( @RequestParam("name") String name, @RequestParam("surname") String surname ,
							@RequestParam("title") String title, @RequestParam("birth_date") String birth_date,
							@RequestParam("phone_number") String phone_number, @RequestParam("phone_number_2") String phone_number_2,
							@RequestParam("e_mail") String e_mail ) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> postEmployee ");
		
		try {
			
			return service.postEmployee(name, surname, title, birth_date, phone_number, phone_number, e_mail);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
		return false;
	}
	
	
	@PutMapping( value="/putEmployee", params = {"id", "name","surname","title", "birth_date","phone_number", "phone_number_2","e_mail"} )
	public boolean postEmployee( @RequestParam("id") String id, @RequestParam("name") String name, 
							@RequestParam("surname") String surname , @RequestParam("title") String title, 
							@RequestParam("birth_date") String birth_date, @RequestParam("phone_number") String phone_number, 
							@RequestParam("phone_number_2") String phone_number_2, @RequestParam("e_mail") String e_mail ) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> putEmployee ");
		
		try {
			
			return service.putEmployeeById(id, name, surname, title, birth_date, phone_number, phone_number_2, e_mail);
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
		return false;
	}
	

	@DeleteMapping( value="/deleteEmployeeById", params = {"id"} )
	public boolean deleteEmployeeById( @RequestParam ("id") String id ) {
		
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> deleteEmployee ");
		
		try {
			service.deleteEmployeeById( id );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
