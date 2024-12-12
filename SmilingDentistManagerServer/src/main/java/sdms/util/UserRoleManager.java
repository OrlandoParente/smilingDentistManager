package sdms.util;

public class UserRoleManager {
	
	// PERMISSION_ = numeric form (as on the database) of what the user is allowed to read, write and add 
	// ROLE_ = string form (as on the Spring Security syntax) of what the user is allowed to read, write and add

	// Obs: ROLE_ADMIN cannot start with ROLE_ (it is automatically added)
	
	public final static int PERMISSION_ACCESS_NOT_ALLOWED = 0;
	public final static String ROLE_ACCESS_NOT_ALLOWED = "ACCESS_NOT_ALLOWED"; // "ROLE_ACCESS_NOT_ALLOWED";
	
	public final static int PERMISSION_BASE_CUSTOMER = 1;
	public final static String ROLE_BASE_CUSTOMER = "BASE_CUSTOMER"; // "ROLE_BASE_CUSTOMER";
	
	public final static int PERMISSION_ADMIN = 10;
	public final static String ROLE_ADMIN = "ADMIN"; // "ROLE_ADMIN";
	
	public static String getRoleFromPermission( int permission ) {
		
		switch( permission ) {
		
			case PERMISSION_ACCESS_NOT_ALLOWED:
				return ROLE_ACCESS_NOT_ALLOWED;
		
			case PERMISSION_BASE_CUSTOMER:
				return ROLE_BASE_CUSTOMER;
				
			case PERMISSION_ADMIN:
				return ROLE_ADMIN;
		
		}
		
		// errore
		return null;
	}
	
	public static int getPermissionFromRole( String role ) {
		
		switch( role ) {
		
			case ROLE_ACCESS_NOT_ALLOWED:
				return PERMISSION_ACCESS_NOT_ALLOWED;
		
			case ROLE_BASE_CUSTOMER:
				return PERMISSION_BASE_CUSTOMER;
				
			case ROLE_ADMIN:
				return PERMISSION_ADMIN;
		
		}
		
		// errore
		return -1;
	}
	
}