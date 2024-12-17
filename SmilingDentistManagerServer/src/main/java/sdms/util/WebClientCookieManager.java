package sdms.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class WebClientCookieManager {

	// Cookie names
	// <---- TOKEN JWT
	public final static String NAME = "name";
	public final static String ID_USER = "idUser";
	
	public WebClientCookieManager() { }
	
	public static String getCookieValue( HttpServletRequest request , String key ) {
		
		for( Cookie cookie : request.getCookies() ) 
			if( key.equals( cookie.getName() ) )
				return cookie.getValue();
		
		return null;
	}
	
}
