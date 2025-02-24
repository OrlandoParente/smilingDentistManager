package sdms.controller.api;

import java.time.format.DateTimeParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.EmployeeDTO;
import sdms.model.Employee;
import sdms.model.WorkPeriod;
import sdms.service.EmployeeServiceInterface;
import sdms.service.WorkPeriodServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class EmployeeRestController {
	
	private final Logger LOGGER = LoggerFactory.getLogger( EmployeeRestController.class );
	
	@Autowired
	private EmployeeServiceInterface service;
	
	@Autowired
	private WorkPeriodServiceInterface workPeriodService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DateAndTimeManager dateAndTimeManager;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    // GET
    
	@GetMapping("/getMaxIdEmployee")
	public long getMaxIdEmployee() {
		
		long maxId = service.getLastCustomerId();
		
		// Check message
		LOGGER.info("EmployeeRestController --> getMaxIdEmployee -> maxId=" + maxId);

		return maxId;
	}
	
	// Response Entity is not needed cause for list "not found" it has to return an empty list ( with 200 ok code)
	// so I need only 200 and 500 codes that are automatic managed by Spring Boot
	@GetMapping("/getEmployees")
	public List<EmployeeDTO> getEmployees(){
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployees ");
		
		List<Employee> employees = service.getEmployees();
		
		return employees.stream().map( e -> modelMapper.map(e, EmployeeDTO.class) ).toList();
		
	}
	
	// Response Entity is not needed cause for list "not found" it has to return an empty list ( with 200 ok code, so 404 code is not needed )
	// so I need only 200 and 500 codes that are automatic managed by Spring Boot
	@GetMapping("/getEmployeesByName/{name}")
	public List<EmployeeDTO> getEmployeesByName( @PathVariable String name ){
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployeesByName, pathVariable={name=" + name + "}");
		
		List<Employee> employees = service.getEmployeesByName(name);
		
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
		
	}
	
	
	@GetMapping("/getEmployeesBySurname/{surname}")
	public List<EmployeeDTO> getEmployeesBySurname( @PathVariable String surname ){
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployeesBySurname -> pathVariable={surname=" + surname + "}");
		
		List<Employee> employees = service.getEmployeesBySurname(surname);
		
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
		
	}
	
	// :[a-zA-Z &+-]* serve per accettare lo spazio
	@GetMapping("/getEmployeesByProfessionalRoleName/{professionalRoleName}")
	public List<EmployeeDTO> getEmployeesByProfessionalRoleName( @PathVariable("professionalRoleName") String professionalRoleName){
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployeesByProfessionalRoleName -> pathVariable={ professionalRoleName="+ professionalRoleName + " }" );
		
		List<Employee> employees = service.getEmployeesByProfessionalRoleName(professionalRoleName);
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class) ).toList();
		
	}

	
	@GetMapping("/getEmployeesByPartialKeyWordOverAllFields/{keyWord}")
	public List<EmployeeDTO> getEmployeesByPartialKeyWordOverAllFields( @PathVariable String keyWord ){
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployeesByPartialKeyWordOverAllFields -> pathVariables={ keyWord=" + keyWord + " }");
		
		List<Employee> employees = service.getEmployeesByPartialKeyWordOverAllFields(keyWord);
		return employees.stream().map( e -> modelMapper.map( e, EmployeeDTO.class ) ).toList();
	}
	
	
	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<?> getEmployeeById( @PathVariable long id ) {
		
		// Check message
		LOGGER.info("EmployeeRestController --> getEmployeeById -> pathVariables={ id=" + id + "}");
		
		Employee employee = service.getEmployeeById(id);
		
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		
		return ResponseEntity.status( HttpStatus.OK ).body( employeeDTO );
	}
	
	// POST
	
	@PostMapping( value="/postEmployee", params = {"name","surname","eMail"} )
	public ResponseEntity<?>  postEmployee( @RequestParam String name, @RequestParam String surname ,@RequestParam String eMail,
								@RequestParam(defaultValue = "") String title, 
								@RequestParam(defaultValue = "") String birthDate,
								@RequestParam(defaultValue = "") String phoneNumber, 
								@RequestParam(defaultValue = "") String phoneNumber2,
								@RequestParam(defaultValue = "-1") Double salary,
								@RequestParam(defaultValue = "-1") Integer permission,
								@RequestParam(defaultValue = "") String password,
								@RequestParam(defaultValue = "en") String language,
								@RequestParam(defaultValue = "") String startWorkDate
	 ) {
		
		// Check message 
		LOGGER.info("EmployeeRestController --> postEmployee -> params={ name=" + name + ", surname=" + surname + " ,eMail=" + eMail 
					+ ", title=" + title + ", birthDate=" + birthDate + ",phoneNumber=" + phoneNumber + ", phoneNumber2=" + phoneNumber2 
					+ ", salary=" + salary + ", permission=" + permission + ",password=*Better not print this*,language=" + language 
					+ ",startWorkDate=" + startWorkDate + " }");
		
		
		Employee employee = new Employee();
		
		try {
			
			employee.setName(name);
			employee.setSurname(surname);
			employee.seteMail(eMail);
			// Should I do a control on correct syntax locale language here ?
			employee.setLanguage(language);
			
			if( ! title.equals(""))	employee.setTitle(title);
			if( ! phoneNumber.equals(""))	employee.setPhoneNumber(phoneNumber);
			if( ! phoneNumber2.equals(""))	employee.setPhoneNumber2(phoneNumber2);
			if( ! birthDate.equals("") )	employee.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
			
			if( salary != -1 ) employee.setSalary(salary);
			if( permission != -1 ) employee.setPermission(permission);
			if( ! password.equals("") ) employee.setPassword( passwordEncoder.encode(password) );
			
			
			service.postEmployee(employee);
			
			
			if( ! startWorkDate.equals("") ) {
				
				WorkPeriod workPeriod = new WorkPeriod();
				workPeriod.setStartDate( dateAndTimeManager.parseDate(startWorkDate) );
				workPeriod.setEmployee(employee);
				
				workPeriodService.postWorkPeriod(workPeriod);
			}
			
			
			
		
		} catch ( DateTimeParseException e ) {
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Error: invalid format data");
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( employee );		
	}
	
	// PUT
	
	@PutMapping( value="/putEmployee", params = {"id"} )
	public ResponseEntity<?>  putEmployee( @RequestParam Long id,
			@RequestParam(defaultValue = "") String name, 
			@RequestParam(defaultValue = "") String surname ,
			@RequestParam(defaultValue = "") String eMail,
			@RequestParam(defaultValue = "") String title, 
			@RequestParam(defaultValue = "") String birthDate,
			@RequestParam(defaultValue = "") String phoneNumber, 
			@RequestParam(defaultValue = "") String phoneNumber2,
			@RequestParam(defaultValue = "-1") Double salary,
			@RequestParam(defaultValue = "-1" ) Integer permission,
			@RequestParam(defaultValue = "") String password,
			@RequestParam(defaultValue = "") String startWorkDate
	) {

		// Check message
		LOGGER.info("EmployeeRestController --> putEmployee -> params={ id=" + id + ",name=" + name + ", surname=" + surname + " ,eMail=" + eMail 
					+ ", title=" + title + ", birthDate=" + birthDate + ",phoneNumber=" + phoneNumber + ", phoneNumber2=" + phoneNumber2 
					+ ", salary=" + salary + ", permission=" + permission + ",password=*Better not print this*, startWorkDate=" + startWorkDate + " }");
		
		
		Employee employee = service.getEmployeeById(id);
		
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee with id " + id + " not found in the database" );
		
		try {
		
			if( ! name.equals(""))	employee.setName(name);
			if( ! surname.equals(""))	employee.setSurname(surname);
			if( ! eMail.equals(""))	employee.seteMail(eMail);
			
			if( ! title.equals(""))	employee.setTitle(title);
			if( ! phoneNumber.equals(""))	employee.setPhoneNumber(phoneNumber);
			if( ! phoneNumber2.equals(""))	employee.setPhoneNumber2(phoneNumber2);
			if( ! birthDate.equals("") )	employee.setBirthDate( dateAndTimeManager.parseDate(birthDate) );
			
			if( salary != -1 ) employee.setSalary(salary);
			if( permission != -1 ) employee.setPermission(permission);
			if( ! password.equals("") ) employee.setPassword( passwordEncoder.encode(password) );
			
			service.postEmployee(employee);
			
		} catch ( DateTimeParseException e ) {
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Error: invalid format data");
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(employee, EmployeeDTO.class) );		
	}
	
	// PATCH
	
	@PatchMapping(value= {"/employeeChangePassword"}, params= {"id","currentPassword", "newPassword"})
	public ResponseEntity<?> patchPassword( @RequestParam long id, @RequestParam String currentPassword, @RequestParam String newPassword ) {
		
		// Check messages
		LOGGER.info("/employeeChangePassword -> params={id=" + id + ", currentPassword=" + currentPassword + ", newPassword=" + newPassword + "}");
		
		Employee employee = service.getEmployeeById(id);
		
		// Check employee exists
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Error, employee not found in the database");
		
		// Check current password 
		if( ! passwordEncoder.matches(currentPassword, employee.getPassword()) )
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Error, wrong password");	// 400 
		
		String encodedNewPassword = passwordEncoder.encode(newPassword);		
		LOGGER.info( "encodedNewPassword: " + encodedNewPassword );
		
		try {
			// Update password
			employee.setPassword(encodedNewPassword);
			service.postEmployee(employee);
		} catch ( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body("Error, password update failed");
		}
		
		return ResponseEntity.status( HttpStatus.OK ).body(employee);
	}
	
	// DELETE
	
	@DeleteMapping( value="/deleteEmployee", params = {"id"} )
	public ResponseEntity<?> deleteEmployeeById( @RequestParam ("id") long id ) {
		
		LOGGER.info("/deleteEmployee -> params={id=" + id + "}");
		
		Employee employee = service.getEmployeeById(id);
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee with id=" + id + " not found in the database");
		
		service.deleteEmployeeById(id);
		
		return ResponseEntity.status( HttpStatus.OK ).build();
	}

}
