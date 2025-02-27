package sdms.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sdms.model.Customer;
import sdms.model.Employee;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmailSenderServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.util.PasswordGenerator;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordController.class);
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	EmailSenderServiceInterface emailSenderService;
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	CustomerServiceInterface customerService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping
	public String resetPasswordPage( ) {
		
		LOGGER.info("GET: /reset-password ");
		return "reset-password";
	}
	
	@PostMapping 
	public String resetPasswordPost(RedirectAttributes redirectAttributes, @RequestParam( defaultValue = "" ) String username ) {
			
		LOGGER.info("POST: /reset-password  -> params={ username=" + username + " }");
		
		Employee employee = null;
		Customer customer = null;
		
		employee = employeeService.getEmployeeByEMail(username);
		// If the user is not an employee check in the customers 
		if( employee == null ) {
			customer = customerService.getCustomerByEMail(username);
		}
		
		if( employee == null && customer == null )
			return "redirect:/reset-password?invalidUsername";
		
		// Generate the new password
		String password = PasswordGenerator.generate();
		String encodedNewPassword = passwordEncoder.encode(password);
		
		try {
			// send the new password to the user
			LOGGER.info( "POST: /reset-password -> sender=" + sender + "; new password= " + password );
			emailSenderService.sendEmail( sender, username, "Recovery password", "Your new password is " + password);
			
		} catch ( Exception e ) {	// If it can't send the new password, do not update it
			
			e.printStackTrace();
			return "redirect:/reset-password?emailNotSent";
		}
		
		// Update the user password ------------------------
		if( customer != null ) {
			customer.setPassword(encodedNewPassword);
			customerService.putCustomer(customer);
		} else if ( employee != null ) {
			employee.setPassword(encodedNewPassword);
			employeeService.putEmployee(employee);
		}
		// -------------------------------------------------	
		
		// In order to do the "redirect" we need Flash Attributes instead of the classic Model 
		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("password", password);
			
		return "redirect:/reset-password?emailSent";
	}
	
	@PostMapping("/resend-password")
	public String resendEmail(RedirectAttributes redirectAttributes, @RequestParam String username, @RequestParam String password) {
		
		try {
			// send the new password to the user
			LOGGER.info( "POST: /reset-password/resend-password sender=" + sender + "; new password= " + password );
			emailSenderService.sendEmail( sender, username, "Recovery password", "Your new password is " + password);
			
		} catch ( Exception e ) {	// If it can't send the new password, do not update it
			
			e.printStackTrace();
			return "redirect:/reset-password?emailNotSent";
		}
		
		// In order to do the "redirect" we need Flash Attributes instead of the classic Model 
		redirectAttributes.addFlashAttribute("username", username);
		redirectAttributes.addFlashAttribute("password", password);
			
		return "redirect:/reset-password?emailSent";
	}
}
