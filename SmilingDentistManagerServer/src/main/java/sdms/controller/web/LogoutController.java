package sdms.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.security.JwtUtil;
import sdms.service.CustomerService;
import sdms.service.EmployeeService;
import sdms.util.UserRoleManager;
import sdms.util.WebClientCookieManager;


@Controller
@RequestMapping("/logout")
public class LogoutController {

	private static final Logger LOGGER = LoggerFactory.getLogger( LogoutController.class );
	
    @GetMapping
    public String logout( HttpServletRequest request, HttpServletResponse response ) {
    	
    	LOGGER.info("Log out");
    	
    	// Destroy all cookie (including jwt)
    	WebClientCookieManager.destroyAllCookies(request, response);
 	
    	// Invalidate the session
    	request.getSession().invalidate();
    	
    	// Invalid security context
    	SecurityContextHolder.clearContext();
    	
    	return "redirect:/login?logout";
    }
    

}
