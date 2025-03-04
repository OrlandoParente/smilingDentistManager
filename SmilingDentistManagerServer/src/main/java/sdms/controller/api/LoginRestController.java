package sdms.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Customer;
import sdms.model.Employee;
import sdms.security.JwtUtil;
import sdms.service.CustomerService;
import sdms.service.EmployeeService;
import sdms.util.UserRoleManager;


@RestController
@RequestMapping("/api/login")
public class LoginRestController {

	private static final Logger Logger = LoggerFactory.getLogger( LoginRestController.class );
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CustomerService customerService;
	
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        
    	Logger.info("username : " + username + " -- password : " + password);

    	// username = email
    	String dbPsw; // password saved in to the db
    	String role;
    	
    	Employee employee = employeeService.getEmployeeByEMail(username);
    	Customer customer;
    	
    	if( employee != null ) {

    		dbPsw = employee.getPassword();
    		role = employee.getRole();
    	
    	} else { 
    		
    		customer = customerService.getCustomerByEMail(username);
    		if( customer != null ) {
    			
    			dbPsw = customer.getPassword();
    			role = customer.getRole();
    			
    		} else {
    			
    			return "User not found on the db";
    		}
    	}

    	// check password and role
    	if( role.equals( UserRoleManager.ROLE_ACCESS_NOT_ALLOWED ) ) return "User not allowed to access";
    	if( ! passwordEncoder.matches(password, dbPsw) ) return "Wrong Password";
    	
    	try {
	    	Authentication authentication = authenticationManager.authenticate( 
						new UsernamePasswordAuthenticationToken(username, password)
					);
	    	
	    	// save authentication in the security context 
	    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	} catch( Exception e ) {
    		System.err.println( e.getMessage() );
    	}
    	
    	return jwtUtil.generateToken(username);
    	
    }
}
