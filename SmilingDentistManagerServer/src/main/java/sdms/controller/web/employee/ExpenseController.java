package sdms.controller.web.employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.AppointmentDTO;
import sdms.dto.CustomerDTO;
import sdms.dto.EmployeeDTO;
import sdms.dto.ExpenseDTO;
import sdms.dto.join.AppointmentCustomerDoctorTreatmentDTO;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.ExpenseServiceInterface;
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
	ModelMapper modelMapper;
	
	@GetMapping({"","/","/expense"})
	public String getTreatmentPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		
		// Fetch expenses
		List<ExpenseDTO> expenses = service.getExpenses().stream()
														 .sorted( Comparator.comparing(  ex -> ex.getDate() ) )		// sort by date 
														 .map( ex -> modelMapper.map(ex, ExpenseDTO.class) )
														 .toList();
		
		
		
		// Fetch revenue from appointments (namely appointments)
		List<AppointmentCustomerDoctorTreatmentDTO> joinAppointments = appointmentService.getAppointments().stream()
																		.sorted( Comparator.comparing(  a -> a.getDate() ) )
																		.map( a -> new AppointmentCustomerDoctorTreatmentDTO()
																				.buildFromAppointmentId( a.getId(), appointmentService, modelMapper))
																		.toList();
		
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
		
		// add stuff at the model
		model.addAttribute("expenses", expenses);
		model.addAttribute("joinAppointments", joinAppointments);
		model.addAttribute("customers", customers);
		model.addAttribute("employees", employees);
		
		model.addAttribute("totalRevenue", totalRevenue);
		model.addAttribute("totalExpenses", totalExpenses);
		model.addAttribute("gainLoss", gainLoss);
		
		model.addAttribute("expenseTags", expenseTags);
		
		return ("employee/expenses/expenses");
	}
}
