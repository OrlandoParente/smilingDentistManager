package sdms.security;

import java.util.List;

/*
 * Since JwtFilter apply the filter over all path, even if defined as ".permitAll" in the SecurityFilterChain,
 * I use this class for storage once all the permitted paths.
 * 
 * This class can be useful also for other path storage stuff
 */
public class SecurityPaths {

	private final static List<String> PERMITTED_PATHS = List.of("/",
																// "/fragments/**",
																"/bootstrap/**", 
																"/css/**",
																"/js/**",
																"/login", 
																"/api/login",
																"/reset-password");
	
	public SecurityPaths() { }
	
	public static List<String> getPermittedPaths() {
		return PERMITTED_PATHS;
	}
}
