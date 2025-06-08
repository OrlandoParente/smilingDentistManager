package sdms.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import sdms.model.EmailSettings;

/*
 * 	public EmailSettings getEmailSettings();
	
	public void saveSettings( EmailSettings emailSettings );
	public void saveSettings( String host, int port, String username, String password, boolean enableAuth, boolean enableTLS, boolean enableSSL);
 */

class EmailSettingsServiceTest {

	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	// public EmailSettings getEmailSettings();
	public void testGetEmailSettting() {
		
		EmailSettings emailSettings;
		emailSettings = new EmailSettings();
		
		
	}
	
	// public void saveSettings( EmailSettings emailSettings );
	
	// public void saveSettings( String host, int port, String username, String password, boolean enableAuth, boolean enableTLS, boolean enableSSL);


}
