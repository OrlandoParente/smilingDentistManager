package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.CustomerDTO;
import sdms.model.Customer;
import sdms.service.CustomerServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	@Autowired
	CustomerServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/customer","","/"})
	public String mainCalendarPage( HttpServletRequest request, Model model,
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
		
		return("employee/customers/customers");
	}
}
