package sdms.util;

import java.security.SecureRandom;

public class PasswordGenerator {

	private final static int DEFAULT_PASSWORD_LENGTH  = 8;

	// Generate a password with default length
	public static String generate() {
		return PasswordGenerator.generate( PasswordGenerator.DEFAULT_PASSWORD_LENGTH );
	}
	
	// Generate a password with length passed as input
	public static String generate( int pswLength ) {
		
		SecureRandom random = new SecureRandom();
		String characters="ABCDEFGJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&_-*&()+";
		
		StringBuilder password = new StringBuilder( pswLength );
		
		for( int i = 0; i < pswLength; i ++ ) {
			int index = random.nextInt( characters.length() );
			password.append( characters.charAt(index) );
		}
		
		return password.toString();
	}
	
	public static int getDefaultPasswordLength() {
		return PasswordGenerator.DEFAULT_PASSWORD_LENGTH;
	}
}
