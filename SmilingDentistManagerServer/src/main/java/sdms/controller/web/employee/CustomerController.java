package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.CustomerDTO;
import sdms.dto.join.CustomerMedicalHistoryExpenseAppointmentDTO;

import sdms.model.Customer;
import sdms.service.AppointmentServiceInterface;
import sdms.service.CustomerServiceInterface;
import sdms.service.ExpenseServiceInterface;
import sdms.service.HasMedicalHistoryServiceInterface;
import sdms.service.MedicalHistoryServiceInterface;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	@Autowired
	CustomerServiceInterface service;
	
	@Autowired
	MedicalHistoryServiceInterface medicalHistoryService;
	
	@Autowired
	HasMedicalHistoryServiceInterface hasMedicalHistoryService;
	
	@Autowired
	ExpenseServiceInterface expenseService;
	
	@Autowired
	AppointmentServiceInterface appointmentService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/customer","","/"})
	public String mainCustomerPage( HttpServletRequest request, Model model,
									@RequestParam( defaultValue = "" ) String keyword ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		// Get DTO customers list ----------------------------------------------------------------------
		List<Customer> customers = new ArrayList<Customer>();
		
		// if keyword is set up, search customers by keyword
		if( ! keyword.equals("") )
			customers =  service.getCustomersByPartialKeyWordOverAllFields(keyword);
		else
			customers = service.getCustomers();
		
		List<CustomerDTO> customersDTO = customers.stream().sorted( Comparator.comparing( c -> c.getSurname() ) )
										.map( c -> modelMapper.map(c, CustomerDTO.class ) )
										.toList();
		
		// ---------------------------------------------------------------------------------------------
		
		// Add stuff to the model
		model.addAttribute("customers", customersDTO);
		model.addAttribute("customerPermissions", UserRoleManager.getCustomerPermissions());
		
		return("employee/customers/customers");
	}
	
	@GetMapping({"/customer/details/{customerId}","/details/{customerId}"})
	public String customerDetailsPage( HttpServletRequest request, Model model,
									@PathVariable("customerId") Long customerId ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------

		
		// ---------------------------------------------------------------------------------------------
		
		Customer customer = service.getCustomerById(customerId);
		
		// <<<<<<<------------------------------ Si potrebbe reindirizzare ad una pagina di errore
		if( customer == null )
			return "404 NOT FOUND";
		
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		
		// ---------------------------------------------------------------------------------------------
		
		// ---------------------------------------------------------------------------------------------
		CustomerMedicalHistoryExpenseAppointmentDTO joinCustomer = new CustomerMedicalHistoryExpenseAppointmentDTO();
		
		joinCustomer.buildFromCustomerId(customerId, service, hasMedicalHistoryService, medicalHistoryService, expenseService, appointmentService, modelMapper);
		
		
		// ---------------------------------------------------------------------------------------------
				
		
		// ---------------------------------------------------------------------------------------------
		
//		List<MedicalHistoryDTO> medicalHistories = medicalHistoryService.getMedicalsHistoryByCustomerId( customerId ).stream()
//													.map( mh -> modelMapper.map(mh, MedicalHistoryDTO.class) )
//													.toList();
//		
		// ---------------------------------------------------------------------------------------------
		
		
		// Add stuff to the model
		model.addAttribute("customer", customerDTO);	// <<<<<<<<<<<<<--------------- NON SERVE
//		model.addAttribute("medicalHistories", medicalHistories);
		model.addAttribute("joinCustomer", joinCustomer);
		
		// serve?
		model.addAttribute("customerPermissions", UserRoleManager.getCustomerPermissions());
		
		return "employee/customers/customer-details";
	}
	
	
}
