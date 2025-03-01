package sdms.controller.web.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import sdms.service.EmployeeServiceInterface;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("employee/email-settings")
public class EmailSettingsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSettingsController.class);
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@GetMapping( value={"", "/", "/email-settings", "/email-settings/"} )
	public String getEmailSettingsPage( HttpServletRequest request, Model model ) {
		
		// Check message
		LOGGER.info("GET: employee/email-settings");
		
		// Set useful cookies in the model
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model, employeeService);
		
		return "employee/email-settings/email-settings";
	}
}
