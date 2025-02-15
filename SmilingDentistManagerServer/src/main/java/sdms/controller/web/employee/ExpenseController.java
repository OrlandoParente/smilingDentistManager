package sdms.controller.web.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sdms.dto.CustomerDTO;
import sdms.dto.DentalMaterialDTO;
import sdms.dto.EmployeeDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.join.AppointmentCustomerDoctorTreatmentDTO;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.DentalMaterialServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.util.DateAndTimeManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/expense")
public class ExpenseController {

	private final Logger LOGGER = LoggerFactory.getLogger( ExpenseController.class );
	
	@Autowired
	ExpenseServiceInterface service;
	
	@Autowired
	AppointmentServiceInterface appointmentService;
	
	@Autowired
	CustomerServiceInterface customerService;
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	DentalMaterialServiceInterface dentalMaterialService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;
	
	@GetMapping({"","/","/expense"})
	public String getTreatmentPage( HttpServletRequest request, HttpServletResponse response, Model model,
									@RequestParam( defaultValue = "" ) String expenseTag,
									@RequestParam( defaultValue = "" ) String startDate,
									@RequestParam( defaultValue = "" ) String endDate,
									@RequestParam( defaultValue = "false" ) boolean csv ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		
		// Fetch expenses -------------------------------------------------------------------------------------------------------------
		
		List<ExpenseDTO> expenses = service.getExpenses().stream()
														 .sorted( Comparator.comparing(  ex -> ex.getDate() , Comparator.reverseOrder() ) )		// sort by date 
														 .map( ex -> modelMapper.map(ex, ExpenseDTO.class) )
														 .toList();
		
		// Filters
		if( ! expenseTag.equals("") )
			expenses = expenses.stream().filter( e -> e.getTag().equals( expenseTag ) ).toList();
		
		LocalDate ldStartDate = null;
		LocalDate ldEndDate = null;
		
		try {
			if( ! startDate.equals("") )
				ldStartDate = dateAndTimeManager.parseDate(startDate);
		} catch ( DateTimeParseException dte ) {
			System.err.println( dte.getMessage() );
			ldStartDate = null;			// for avoid error, but it needs a better way to manage this (return a message)
		}
		
		try {
			if( ! endDate.equals("") )
				ldEndDate = dateAndTimeManager.parseDate(endDate);
		} catch ( DateTimeParseException dte ) {
			System.err.println( dte.getMessage() );
			ldEndDate = null;			// for avoid error, but it needs a better way to manage this (return a message)
		}
		

		if( ldStartDate != null) {
			LocalDate finalLdStartDate = ldStartDate;
			expenses = expenses.stream() // .peek( ex -> { LOGGER.info( "Filter startDate -> " + ex.getDate() ); } )	// log dates 
										.filter( ex ->  ! ex.getDate().isBefore(finalLdStartDate) ).toList();
		}
		
		if( ldEndDate != null) {
			LocalDate finalLdEndDate = ldEndDate;
			expenses = expenses.stream().filter( ex ->  ! ex.getDate().isAfter(finalLdEndDate) ).toList();
		}
		
		// ----------------------------------------------------------------------------------------------------------------------------
		
		// Fetch revenue from appointments (namely appointments) ----------------------------------------------------------------------
		List<AppointmentCustomerDoctorTreatmentDTO> joinAppointments = appointmentService.getAppointments().stream()
																		.sorted( Comparator.comparing(  a -> a.getDate(), Comparator.reverseOrder() ) )
																		.map( a -> new AppointmentCustomerDoctorTreatmentDTO()
																				.buildFromAppointmentId( a.getId(), appointmentService, modelMapper))
																		.toList();
		
		// ldStartDate and ldEndDate found in expenses fetch
		if( ldStartDate != null) {
			LocalDate finalLdStartDate = ldStartDate;
			joinAppointments = joinAppointments.stream().filter( ja ->  ! ja.getAppointmentDTO().getDate().isBefore(finalLdStartDate) ).toList();
		}
		
		if( ldEndDate != null) {
			LocalDate finalLdEndDate = ldEndDate;
			joinAppointments = joinAppointments.stream().filter( ja ->   ! ja.getAppointmentDTO().getDate().isAfter(finalLdEndDate) ).toList();
		}
		// ----------------------------------------------------------------------------------------------------------------------------
		
		// Fetch customers
		List<CustomerDTO> customers = customerService.getCustomers().stream()
				.sorted( Comparator.comparing( c -> c.getSurname() ) )
				.map( customer ->  modelMapper.map(customer, CustomerDTO.class) )
				.toList();
		
		// Fetch expenses tags
		List<String> expenseTags = service.getExpenseTags();
		
		// Fetch employees
		List<EmployeeDTO> employees = employeeService.getEmployees().stream()
				.sorted( Comparator.comparing( emp -> emp.getSurname() ) )
				.map( employee -> modelMapper.map(employee, EmployeeDTO.class) )
				.toList();
		
		// Fetch dental materials
		List<DentalMaterialDTO> dentalMaterials= dentalMaterialService.getDentalMaterials().stream()
				.sorted( Comparator.comparing( dm-> dm.getName()) )
				.map( dm -> modelMapper.map(dm, DentalMaterialDTO.class) )
				.toList();
		
		// Calculation summary info -------------------------------------------------------------------
		
		double totalRevenue = 0;
		double totalExpenses = 0;
		double gainLoss = 0;
		
		for( AppointmentCustomerDoctorTreatmentDTO ja : joinAppointments ) {
		 	if( ja.getAppointmentDTO().getPayment() != null )	
				totalRevenue += ja.getAppointmentDTO().getPayment();
		}
		
		for( ExpenseDTO e : expenses ) {
			// if( e.getAmount() != null ) // this make sense if use Double instead of double for amount field
				totalExpenses += e.getAmount();
		}
		
		gainLoss = totalRevenue - totalExpenses;
		// ---------------------------------------------------------------------------------------------
		
		// add stuff to the model ----------------------------------------------------------------------
		
		model.addAttribute("expenses", expenses);
		model.addAttribute("joinAppointments", joinAppointments);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		model.addAttribute("dentalMaterials", dentalMaterials);
		
		model.addAttribute("totalRevenue", totalRevenue);
		model.addAttribute("totalExpenses", totalExpenses);
		model.addAttribute("gainLoss", gainLoss);
		
		model.addAttribute("expenseTags", expenseTags);
		
		// add filter selected fields
		model.addAttribute("expenseSelectedTag", expenseTag);
		model.addAttribute("selectedStartDate", ldStartDate);
		model.addAttribute("selectedEndDate", ldEndDate);
		
		// ---------------------------------------------------------------------------------------------
		
		// Download Expense CSV ------------------------------------------------------------------------
		if( csv ) {
			
			String csvFileName = "Income-Outcome.csv";
			
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + csvFileName + "\"");
			
			try {
				PrintWriter writer = response.getWriter();
				
				writer.println("Total incoming, Total outcoming, Gain/Loss");
				writer.println(totalRevenue + "," + totalExpenses + "," + gainLoss  );
				
				writer.println();
				writer.println("OUTCOME");
				writer.println("Date, Amount, Tag, Description");
				for( ExpenseDTO ex : expenses ) {
					writer.println( ex.getDate() + "," + ex.getAmount() + "," + ex.getTag() + "," + ex.getDescription() );
				}
				
				writer.println();
				writer.println("INCOME");
				writer.println("Date, Amount, Payment method, Customer, Invoice number");
				for( AppointmentCustomerDoctorTreatmentDTO ja : joinAppointments ) {
					writer.println( ja.getAppointmentDTO().getDate() + "," 
									 + ja.getAppointmentDTO().getPayment() + "," 
									 + ja.getAppointmentDTO().getPaymentMethod() + "," 
									 + ja.getCustomerDTO().getName() + " " + ja.getCustomerDTO().getSurname() + "," 
									 + ja.getAppointmentDTO().getInvoiceNumber() );
				}
				
			} catch ( IOException e ) {
				System.err.println( e.getMessage() );
				// TO EDIT: Redirect to a page error
				
			}
		
			// In this case it doesn't have to  return the view
			return null;
		}
		
		// ---------------------------------------------------------------------------------------------
		
		
		return ("employee/expenses/expenses");
	}
	
	@GetMapping({"csv","/csv","/expense/csv"})
	public void downloadCSVExpenses( HttpServletResponse response ){
		
	}
}
