package sdms.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.security.JwtUtil;
import sdms.service.CustomerService;
import sdms.service.EmployeeService;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;


@Controller
@RequestMapping("/login")
public class LoginController {

	private final Logger LOGGER = LoggerFactory.getLogger( LoginController.class );
	
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
    
	@Value("${jwt.expiration.minutes:120}") 
	private String strCookiesExpirationInMinutes; 
	
	private int cookieExpirationInMinutes; 
    
    @GetMapping
    public String login() {
    	
//    	PasswordEncoder encoder = new BCryptPasswordEncoder();
//    	String str = "1234";
//    	System.err.println( encoder.encode(str) );
    	
    	return "login";
    }
    
    @PostMapping
    public String login( HttpServletResponse response, 
    					 @RequestParam String username, @RequestParam String password, @RequestParam( defaultValue = "false") boolean rememberMe) {
        
    	
    	// better avoid write the clear password in the logs 
    	LOGGER.info("username : " + username + /* "; password : " + password + */"; rememberMe : " + rememberMe);

    	// get cookie expiration 
    	if( rememberMe )
    		cookieExpirationInMinutes = 525600; // 100 years
    	else
    		cookieExpirationInMinutes = Integer.parseInt( strCookiesExpirationInMinutes );
    	
    	LOGGER.info("cookieExpirationInMinutes: " + cookieExpirationInMinutes );
    	
    	// username = email
    	String dbPsw; // password saved in to the db
    	String role;
    	
    	// Data to save in the cookies ----
    	// String name;	// I can fetch from the idUser, but I need often so better save it in the cookies
    	Long idUser;	// It's useful for recovery any information about the user
    	// --------------------------------
    	
    	Employee employee = employeeService.getEmployeeByEMail(username);
    	Customer customer;
    	
    	if( employee != null ) {

    		dbPsw = employee.getPassword();
    		role = employee.getRole();
    		
    		// name = employee.getName();	// I can fetch from the idUser, but I need often so better save it in the cookies
    		idUser = employee.getId();
    	
    	} else { 
    		
    		customer = customerService.getCustomerByEMail(username);
    		if( customer != null ) {
    			
    			dbPsw = customer.getPassword();
    			role = customer.getRole();
    			
        		// name = customer.getName();	// I can fetch from the idUser, but I need often so better save it in the cookies 
        		idUser = customer.getId();
    		} else {
    			
    			return "redirect:/login?error=User not found in the db";
    		}
    	}

    	// check password and role
    	if( role.equals( UserRoleManager.ROLE_ACCESS_NOT_ALLOWED ) ) return "redirect:/login?error=User not allowed to access";
    	if( ! passwordEncoder.matches(password, dbPsw) ) return "redirect:/login?error";
    	
    	try {
    		Authentication authentication = authenticationManager.authenticate( 
					new UsernamePasswordAuthenticationToken(username, password)
			);
    		
    		String token = jwtUtil.generateToken(username);
    		response.setHeader("Authorization", "Bearer "+  token);
    		
    		
    		// save jwt in the cookies ----------------------------------------------------------
        	Cookie jwtCookie = new Cookie("Authorization", token);
        	jwtCookie.setHttpOnly(true); // Impedisce l'accesso al token tramite JavaScript
        	// jwtCookie.setSecure(true); // Imposta solo per HTTPS in ambienti di produzione
        	jwtCookie.setPath("/"); // Disponibile per tutto il dominio
        	jwtCookie.setMaxAge(cookieExpirationInMinutes * 60); // Imposta una scadenza (ad esempio 1 giorno)
        	response.addCookie(jwtCookie);
    		// ---------------------------------------------------------------------------------
        	

        	// Cookies value can't have space, so it's not a good idea save the name in the cookies (it can have a space)
//    		// save name of the user in the cookies -------------------------------------------
//        	Cookie nameCookie = new Cookie(WebClientCookieManager.NAME, name);
//        	// nameCookie.setHttpOnly(true); // Impedisce l'accesso al token tramite JavaScript
//        	// jwtCookie.setSecure(true); // Imposta solo per HTTPS in ambienti di produzione
//        	// nameCookie.setPath("/"); // Disponibile per tutto il dominio
//        	nameCookie.setMaxAge(cookieExpirationInMinutes * 60); // Imposta una scadenza (ad esempio 1 giorno)
//        	response.addCookie(nameCookie);
//    		// ---------------------------------------------------------------------------------
        	
    		// save id of the user in the cookies ----------------------------------------------
        	Cookie userIdCookie = new Cookie(WebClientCookieManager.ID_USER, idUser.toString());
        	// userIdCookie.setHttpOnly(true); // Impedisce l'accesso al token tramite JavaScript
        	// jwtCookie.setSecure(true); // Imposta solo per HTTPS in ambienti di produzione
        	userIdCookie.setPath("/"); // Disponibile per tutto il dominio
        	userIdCookie.setMaxAge(cookieExpirationInMinutes * 60); // Imposta una scadenza (ad esempio 1 giorno)
        	response.addCookie(userIdCookie);
    		// ---------------------------------------------------------------------------------
    	
    		// save authentication in the security context 
    		SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	} catch( Exception e ) {
    		
    		LOGGER.error( e.getMessage() );
    	}
    	
//    	return "redirect:/dashboard/employee";
    	return "redirect:/employee/calendar/day";
    	
    }
}
