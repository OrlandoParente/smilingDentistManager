package sdms.controller.api;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.zip.DataFormatException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.ExpenseDTO;
import sdms.model.Customer;
import sdms.model.DentalMaterial;
import sdms.model.Employee;
import sdms.model.Expense;
import sdms.service.CustomerServiceInterface;
import sdms.service.DentalMaterialServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class ExpenseRestController {
	
	@Autowired
	private ExpenseServiceInterface service;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private DentalMaterialServiceInterface dentalMaterialService;
	
	@Autowired
	private EmployeeServiceInterface employeeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DateAndTimeManager dateAndTimeManager;
	
	// READ ------------------------------------------------------
	@GetMapping("/getExpenseById/{id}")
	public ExpenseDTO getExpenseById( @PathVariable long id ) {
		
		Expense expense = service.getExpenseById(id);
		ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
		
		return expenseDTO;
	}
	
	@GetMapping("/getExpenses")
	public List<ExpenseDTO> getExpenses() {
		
		List<Expense> expenses = service.getExpenses();
		List<ExpenseDTO> expenseDTOs = expenses.stream().map( e -> modelMapper.map(e, ExpenseDTO.class) ).toList();
	
		return expenseDTOs;
	}
	
	// CREATE ----------------------------------------------------

	// Insert a "general" expense object
	@PostMapping("/postExpense")
	public void postExpense( @RequestBody ExpenseDTO expenseDTO ) {
		
		Expense expense = modelMapper.map(expenseDTO, Expense.class);
		service.postExpense(expense);
	}
	
	@PostMapping(value= {"/postExpense"}, params= {"date", "amount"})
	public ResponseEntity<?> putExpense( @RequestParam String date, @RequestParam double amount,
										 @RequestParam( defaultValue = "sdms_none-nessun-nothing" ) String description,
										 @RequestParam( defaultValue = "sdms_none-nessun-nothing" ) String tag,
										 @RequestParam( defaultValue = "-1" ) long idCustomer,
										 @RequestParam( defaultValue = "-1" ) long idEmployee,
										 @RequestParam( defaultValue = "-1" ) long idDentalMaterial,
										 @RequestParam( defaultValue = "-1000000" ) int dentalMaterialQuantityToAdd ){
		
		Expense expense = new Expense();
		
		Customer customer = null;
		Employee employee = null;
		DentalMaterial dentalMaterial = null;
		
		// can associate to expense only once between customer employee and dentalMaterial
		// if it find Customer doesn't search for employee and dentalMaterial, if it find employee doesn't search for dentalMaterial
		if( idCustomer != -1 ) {
			customer = customerService.getCustomerById(idCustomer);
			
			if( customer == null )
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with id " + idCustomer + "not found in the database");
		
			// link customer to expense
			expense.setCustomer(customer);
						
		} else if ( idEmployee != -1 ) {
			employee = employeeService.getEmployeeById(idEmployee);
			
			if( employee == null )
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with id " + idCustomer + "not found in the database");
		
			// link employee to expense
			expense.setCustomer(customer);
			
		} else if ( idDentalMaterial != -1 ) {
			dentalMaterial = dentalMaterialService.getDentalMaterialById(idDentalMaterial);
			
			if( dentalMaterial == null )
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with id " + idCustomer + "not found in the database");
			else 
				dentalMaterialService.increaseDentalMaterialQuantity(idDentalMaterial, dentalMaterialQuantityToAdd);
			
			// link dentalMaterial to expense
			expense.setDentalMaterial(dentalMaterial);
		}
		
		
		try {
			
			// mandatory fields
			expense.setDate( dateAndTimeManager.parseDate(date) );
			expense.setAmount(amount);
			
			// optional fields
			if( ! description.equals("sdms_none-nessun-nothing") ) expense.setDescription(description);
			if( ! tag.equals("sdms_none-nessun-nothing")) expense.setTag(tag);
			
			
			// update expense
			service.postExpense(expense);
		
		} catch ( DateTimeParseException dte ) {
		
			System.err.println( dte.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Error: date format not valid");

		} catch ( Exception e ) {

			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
	
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
	}
	

	// <------------- I need this ? 
	// Insert the refund to a customer
	@PostMapping("/postCustomerRefund")
	public ResponseEntity<?> postCustomerRefund( @RequestParam("idCustomer") long idCustomer,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
		
		Expense expense = new Expense();
		Customer customer = customerService.getCustomerById(idCustomer);
		if( customer == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Customer with id " + idCustomer + " not found in the database");
		
		expense.setCustomer(customer);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.postExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
	}
	
	// Insert the expense regards purchase of dental materials
	@PostMapping("/postDentalMaterialPurchase")
	public ResponseEntity<?> postDentalMaterialPurchase( @RequestParam("idDentalMaterial") long idDentalMaterial,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
		
		Expense expense = new Expense();
		DentalMaterial dentalMaterial = dentalMaterialService.getDentalMaterialById(idDentalMaterial);
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Dental Material with id " + idDentalMaterial + " not found in the database");
		
		expense.setDentalMaterial(dentalMaterial);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.postExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
		
	}
	
	// Insert the salary payment of an employee
	@PostMapping("/postSalaryPayment")
	public ResponseEntity<?> postSalaryPayment( @RequestParam("idEmployee") long idEmployee,
			@RequestParam("date") String date, @RequestParam("description") String description,
			@RequestParam("amount") double amount, @RequestParam("tag") String tag) {
		
		Expense expense = new Expense();
		Employee employee = employeeService.getEmployeeById(idEmployee);
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Customer with id " + idEmployee + " not found in the database");
		
		expense.setEmployee(employee);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.postExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
		
	}
	
	// UPDATE ----------------------------------------------------
	
	@PutMapping(value= {"/putExpense"}, params= {"id"})
	public ResponseEntity<?> putExpense( @RequestParam long id,
										 @RequestParam( defaultValue = "" ) String date,
										 @RequestParam( defaultValue = "sdms_none-nessun-nothing" ) String description,
										 @RequestParam( defaultValue = "sdms_none-nessun-nothing" ) String tag,
										 @RequestParam( defaultValue = "-1000000" ) double amount){
		
		Expense expense = service.getExpenseById(id);
		if( expense == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Expense  with id " + id + ", to update not found in the database");
		
		LocalDate ldDate = null;
		
		try {
			
			// Check that date has a valid format
			if( ! date.equals("") )
				ldDate = dateAndTimeManager.parseDate(date);
		
			// update fields value
			if( ldDate != null ) expense.setDate( ldDate );
			if( ! description.equals("sdms_none-nessun-nothing") ) expense.setDescription(description);
			if( ! tag.equals("sdms_none-nessun-nothing")) expense.setTag(tag);
			if( amount != -1000000 ) expense.setAmount(amount);
			
			// update expense
			service.putExpense(expense);
		
		} catch ( DateTimeParseException dte ) {
		
			System.err.println( dte.getMessage() );
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body("Error: date format not valid");

		} catch ( Exception e ) {

			System.err.println( e.getMessage() );
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).build();
	
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
	}
	
	// <<<<<<<<<<<<<<<<<<------------ I NEED THIS ?
	// Update the refund to a customer
	@PutMapping("/putCustomerRefund")
	public ResponseEntity<?> putCustomerRefund( @RequestParam("id") long id,
			@RequestParam("idCustomer") long idCustomer, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
	
		Expense expense = service.getExpenseById(id);
		if( expense == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Expense  with id " + id + ", to update not found in the database");
		
		Customer customer = customerService.getCustomerById(idCustomer);
		if( customer == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Customer with id " + idCustomer + " not found in the database");
		
		expense.setCustomer(customer);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.putExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
		
	}
	
	// <------------------------------ I NEED THIS?
	// Update the expense regards purchase of dental materials
	@PutMapping("/putDentalMaterialPurchase")
	public ResponseEntity<?> putDentalMaterialPurchase( @RequestParam("id") long id,
			@RequestParam("idDentalMaterial") long idDentalMaterial, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
		
		Expense expense = service.getExpenseById(id);
		if( expense == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Expense  with id " + id + ", to update not found in the database");
		
		DentalMaterial dentalMaterial = dentalMaterialService.getDentalMaterialById(idDentalMaterial);
		if( dentalMaterial == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Dental Material with id " + idDentalMaterial + " not found in the database");
		
		expense.setDentalMaterial(dentalMaterial);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.postExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
		
		
	}
	
	// <----------------- I NEED THIS ?
	// Insert the salary payment of an employee
	@PutMapping("/putSalaryPayment")
	public ResponseEntity<?> putSalaryPayment( @RequestParam("id") long id,
			@RequestParam("idEmployee") long idEmployee, @RequestParam("date") String date, 
			@RequestParam("description") String description, @RequestParam("amount") double amount, 
			@RequestParam("tag") String tag) {
		
		Expense expense = service.getExpenseById(id);
		if( expense == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Expense  with id " + id + ", to update not found in the database");
		
		Employee employee = employeeService.getEmployeeById(idEmployee);
		if( employee == null )
			return ResponseEntity.status( HttpStatus.NOT_FOUND )
					.body("404 NOT FOUND: Customer with id " + idEmployee + " not found in the database");
		
		expense.setEmployee(employee);
		expense.setDate( dateAndTimeManager.parseDate(date) );
		expense.setDescription(description);
		expense.setAmount(amount);
		expense.setTag(tag);
		
		service.postExpense(expense);
		
		return ResponseEntity.status(HttpStatus.OK).body(expense);
		
	}
	
	// Update a "general" expense object
	@PutMapping("/putExpense")
	public void putExpense( @RequestBody ExpenseDTO expenseDTO ) {
		
		Expense expense = modelMapper.map(expenseDTO, Expense.class);
		service.putExpense(expense);
	}
	
	// DELETE ----------------------------------------------------
	@DeleteMapping("/deleteExpense")
	public void deleteExpenseById( @RequestParam("id") long id ) {
		
		service.deleteExpense(id);
	}
	

}
