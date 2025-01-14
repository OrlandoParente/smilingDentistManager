package sdms.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;
import sdms.security.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/*
 * Get (Set) the locale from (in) the database 
 */
@Component
public class CustomLocaleResolver implements LocaleResolver {
	
	private final Logger LOGGER = LoggerFactory.getLogger( CustomLocaleResolver.class ) ;
	
	public CustomLocaleResolver() {
		LOGGER.info("CustomResolver Instantiated");
	}
	
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
   	
    	
        String token = jwtUtil.extractJwtFromRequest(request);
        
        if (token != null ) {
            // Se il token è valido, estrai il nome utente dal token
            String username = jwtUtil.extractUsername(token);
            LOGGER.info("Resolving locale for user: " + username);
          
            Employee employee = employeeRepository.findByEMail(username).orElse(null);
            if (employee != null) {
            	LOGGER.info("Found employee locale: " + employee.getLanguage());
                return new Locale(employee.getLanguage());
            }
            
            Customer customer = customerRepository.findByEMail(username).orElse(null);
            if (customer != null) {
            	LOGGER.info("Found customer locale: " + customer.getLanguage());
                return new Locale(customer.getLanguage());
            }
            
        } else { // If user not logged in
        	
            String sessionLang = (String) request.getSession().getAttribute("lang");
            if (sessionLang != null) {
                LOGGER.info("Language found in session: " + sessionLang);
                return new Locale(sessionLang);
            }
        }
        // return Locale.getDefault();
        // Set english as default locale
        return Locale.ENGLISH;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        String token = jwtUtil.extractJwtFromRequest(request);
        
        if (token != null && jwtUtil.validateToken(token, jwtUtil.extractUsername(token))) {
            // Se il token è valido, estrai il nome utente dal token
            String username = jwtUtil.extractUsername(token);
          
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            String username = userDetails.getUsername();
            Employee employee = employeeRepository.findByEMail(username).orElse(null);
            
            LOGGER.info("Setting locale for user: " + username + " to " + locale.getLanguage());
            
            if (employee != null) {
                employee.setLanguage(locale.getLanguage());
                employeeRepository.save(employee);
                LOGGER.info("Saved employee locale: " + locale.getLanguage());
                return;
            }

            Customer customer = customerRepository.findByEMail(username).orElse(null);
            if (customer != null) {
                customer.setLanguage(locale.getLanguage());
                customerRepository.save(customer);
                LOGGER.info("Saved customer locale: " + locale.getLanguage());
                return;
            }
        } else {	// If user not logged in
        	
            LOGGER.info("Setting session language to: " + locale.getLanguage());
            request.getSession().setAttribute("lang", locale.getLanguage());
        	
        }
    }
}
