package sdms.controller.web.employee;

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

import jakarta.servlet.http.HttpServletRequest;
import sdms.service.ExpenseServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/expense")
public class ExpenseController {

	private final Logger LOGGER = LoggerFactory.getLogger( ExpenseController.class );
	
	@Autowired
	ExpenseServiceInterface service;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"","/","/expense"})
	public String getTreatmentPage( HttpServletRequest request, Model model ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		
		return ("employee/expenses/expenses");
	}
}
