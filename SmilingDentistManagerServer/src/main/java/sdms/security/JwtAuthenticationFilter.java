package sdms.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.util.UserRoleManager;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final static Logger Logger = LoggerFactory.getLogger( JwtAuthenticationFilter.class );

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AntPathMatcher pathMatcher; // = new AntPathMatcher();
	
	@Autowired
	private EmployeeServiceInterface employeeService;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
			// Get Authorization from the header
			String authHeader = request.getHeader("Authorization");
			
			// Avoid to check permitted paths -------------------------------
			String requestPath = request.getServletPath();
			
			for( String path : SecurityPaths.getPermittedPaths() ) {
			    
				// Ignora gli endpoint che non richiedono autenticazione
			    if( pathMatcher.match(path, requestPath) ) {
			        filterChain.doFilter(request, response);
			        return;
			    }
			}
			// ------------------------------------------------------------
			
			// Get Authorization from the cookies
			Cookie[] cookies = request.getCookies();
			
			String username = null;
			String jwtToken = null;
			
			// Check if there are the Authorization in the header
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
			    jwtToken = authHeader.substring(7);
			    username = jwtUtil.extractUsername(jwtToken);
			    
			} 
			else if (cookies != null) { // Check if there are the Authorization in the cookies
	            
				for (Cookie cookie : cookies) {
					
	                if ("Authorization".equals(cookie.getName())) {
	                    jwtToken = cookie.getValue();
	                    username = jwtUtil.extractUsername(jwtToken);
	                    Logger.info(jwtToken);
	                    Logger.info("USERNAME " + username);
	                }
	            }
	        }
	        
			
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			    
				// Get roles (authorities) ------------------------------------------------------------------------
				
				String role;
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				// new SimpleGrantedAuthority("ROLE_" + role)
				
				Employee employee = employeeService.getEmployeeByEMail(username);
				
				// If the user is an employee ...
				if( employee != null ) {
					role = "ROLE_" + UserRoleManager.getRoleFromPermission( employee.getPermission() );
					authorities.add( new SimpleGrantedAuthority(role) );
					
				} else { // ... otherwise check if user is a customer
					
					Customer customer = customerService.getCustomerByEMail(username);
					if( customer != null ) {
						role = "ROLE_" + UserRoleManager.getRoleFromPermission( customer.getPermission() );
						authorities.add( new SimpleGrantedAuthority(role) );
					}	
				}
				
				// ------------------------------------------------------------------------------------------------
				
				if (jwtUtil.validateToken(jwtToken, username)) {
			        UsernamePasswordAuthenticationToken authToken =
			                new UsernamePasswordAuthenticationToken(username, null, authorities);
			        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			        SecurityContextHolder.getContext().setAuthentication(authToken);
			    } else Logger.info("LOG IN FAILED 1");
			    
			} else Logger.info("LOG IN FAILED 2");
			
			filterChain.doFilter(request, response);
		
	}

}
