package sdms.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.EmployeeDTO;
import sdms.model.Employee;
import sdms.service.EmployeeServiceInterface;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/getMaxIdEmployee")
	public long getMaxIdEmployee() {
		
		long maxId = service.getLastCustomerId();
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getMaxIdEmployee -> " + maxId);

		return maxId;
	}
	
	@GetMapping("/getEmployees")
	public List<EmployeeDTO> getEmployees(){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployees ");
		
		List<Employee> employees = service.getEmployees();
		
		return employees.stream().map( e -> modelMapper.map(e, EmployeeDTO.class) ).toList();
		
	}
		
	@GetMapping("/getEmployeesByName/{name}")
	public List<EmployeeDTO> getEmployeesByName( @PathVariable String name ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByName ");
		
		List<Employee> employees = service.getEmployeesByName(name);
		
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
		
	}
	
	
	@GetMapping("/getEmployeesBySurname/{surname}")
	public List<EmployeeDTO> getEmployeesBySurname( @PathVariable String surname ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesBySurname ");
		
		List<Employee> employees = service.getEmployeesBySurname(surname);
		
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
		
	}
	
	// :[a-zA-Z &+-]* serve per accettare lo spazio
	@GetMapping("/getEmployeesByProfessionalRoleName/{professionalRoleName}")
	public List<EmployeeDTO> getEmployeesByProfessionalRoleName( @PathVariable("professionalRoleName") String professionalRoleName){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByProfessionalRoleName -> " + professionalRoleName );
		
		List<Employee> employees = service.getEmployeesByProfessionalRoleName(professionalRoleName);
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class) ).toList();
		
	}


	
	
	@GetMapping("/getEmployeesByPartialKeyWordOverAllFields/{keyWord}")
	public List<EmployeeDTO> getEmployeesByPartialKeyWordOverAllFields( @PathVariable String keyWord ){
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeesByPartialKeyWordOverAllFields -> key_word -> " + keyWord );
		
		List<Employee> employees = service.getEmployeesByPartialKeyWordOverAllFields(keyWord);
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
	}
	
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<?> getEmployeeById( @PathVariable long id ) {
		
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> getEmployeeById ");
		
		Employee employee = service.getEmployeeById(id);
		
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		
		return ResponseEntity.status( HttpStatus.OK ).body( employeeDTO );
	}
	
	@PostMapping( value="/postEmployee", params = {"name","surname","title", "phoneNumber"} )
	public void postEmployee( @RequestParam("name") String name, @RequestParam("surname") String surname ,
								@RequestParam("title") String title, @RequestParam("phoneNumber") String phoneNumber) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> postEmployee ");
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSurname(surname);
		employee.setTitle(title);
		employee.setPhoneNumber(phoneNumber);
		
		service.postEmployee(employee);
		
	}
	

	@PostMapping( value="/postEmployee", params = {"name","surname","title", "birthDate","phoneNumber", "phoneNumber2","eMail"} )
	public void postEmployee( @RequestParam("name") String name, @RequestParam("surname") String surname ,
							@RequestParam("title") String title, @RequestParam("birthDate") String birthDate,
							@RequestParam("phoneNumber") String phoneNumber, @RequestParam("phoneNumber2") String phoneNumber2,
							@RequestParam("eMail") String eMail ) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> postEmployee ");
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setSurname(surname);
		employee.setTitle(title);
		employee.setPhoneNumber(phoneNumber);
		employee.setPhoneNumber2(phoneNumber2);
		employee.seteMail(eMail);
		employee.setBirthDate(birthDate);
		
		service.postEmployee(employee);
		
	}
	
	
	@PutMapping( value="/putEmployee", params = {"id", "name","surname","title", "birthDate","phoneNumber", "phoneNumber2","eMail"} )
	public ResponseEntity<?> postEmployee( @RequestParam("id") long id, @RequestParam("name") String name, 
							@RequestParam("surname") String surname , @RequestParam("title") String title, 
							@RequestParam("birthDate") String birthDate, @RequestParam("phoneNumber") String phoneNumber, 
							@RequestParam("phoneNumber2") String phoneNumber2, @RequestParam("eMail") String eMail ) {

		// Messaggio di controllo
		System.out.println("EmployeeRestController --> putEmployee ");
		
		Employee employee = service.getEmployeeById(id);
		
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		employee.setName(name);
		employee.setSurname(surname);
		employee.setTitle(title);
		employee.setPhoneNumber(phoneNumber);
		employee.setPhoneNumber2(phoneNumber2);
		employee.seteMail(eMail);
		employee.setBirthDate(birthDate);
		
		service.putEmployee(employee);
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(employee, EmployeeDTO.class) );
		
		
	}
	

	@DeleteMapping( value="/deleteEmployeeById", params = {"id"} )
	public void deleteEmployeeById( @RequestParam ("id") long id ) {
		
		
		// Messaggio di controllo
		System.out.println("EmployeeRestController --> deleteEmployee ");
		
		service.deleteEmployeeById(id);
	}

}
