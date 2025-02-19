package sdms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;

public class WebClientCookieManager {

	private final static Logger LOGGER = LoggerFactory.getLogger(WebClientCookieManager.class);
	
	// Cookie names
	// <---- TOKEN JWT (namely "Authorization" )
	// Cookies value can't have space, so it's not a good idea save the name in the cookies (it can have a space)
	//	public final static String NAME = "name";
	public final static String ID_USER = "idUser";
	
	public WebClientCookieManager() { }
	
	public static String getCookieValue( HttpServletRequest request , String key ) {
		
		for( Cookie cookie : request.getCookies() ) 
			if( key.equals( cookie.getName() ) )
				return cookie.getValue();
		
		return null;
	}
	
	public static void setUsefulGlobalCookiesInTheModel ( HttpServletRequest request, Model model, EmployeeServiceInterface service ) {
		
		// fetch useful cookies values
		String strId = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.ID_USER); 
		Long id = Long.parseLong(strId);
		String name = service.getEmployeeById(id).getName();
		
		// Add the useful cookies value to the model
		model.addAttribute("name", name);
		
	}
	
	public static void setUsefulGlobalCookiesInTheModel ( HttpServletRequest request, Model model, CustomerServiceInterface service ) {
		
		// fetch useful cookies values
		String strId = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.ID_USER); 
		Long id = Long.parseLong(strId);
		String name = service.getCustomerById(id).getName();
		
		// Add the useful cookies value to the model
		model.addAttribute("name", name);
		
	}
	
	// Useful for logout
	public static void destroyAllCookies(HttpServletRequest request, HttpServletResponse response) { 
		
		WebClientCookieManager.LOGGER.info( "in WebClientCookieManager.destroyAllCookies " );
		
		if (request.getCookies() != null) { 
			for (Cookie cookie : request.getCookies()) { 
				
				WebClientCookieManager.LOGGER.info( "deleting cookie -> " + cookie.getName() );
				cookie.setValue(null); 
				cookie.setMaxAge(0); 
				cookie.setPath("/"); 
				response.addCookie(cookie); 
			} 
		}
	}
	
}
