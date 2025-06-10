package sdms.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.EmailSettings;
import sdms.repository.EmailSettingsRepository;

/*
 * 	public EmailSettings getEmailSettings();
	
	public void saveSettings( EmailSettings emailSettings );
	public void saveSettings( String host, int port, String username, String password, boolean enableAuth, boolean enableTLS, boolean enableSSL);
 */

class EmailSettingsServiceTest {

	@Mock
	private EmailSettingsService emailSettingsService;
	
	@InjectMocks
	private EmailSettingsRepository emailSettingsRepository;
	
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
	
	@Test
	public void testGetEmailSettting() {
		
		// Simulate the database ----------------------------
		EmailSettings emailSettings;
		emailSettings = new EmailSettings();
		
		Long id = 1L;
		emailSettings.setId(id);
		
		when( emailSettingsRepository.findById(id) ).thenReturn( Optional.of( emailSettings ) );
		// --------------------------------------------------
					
		// test ---------------------------------------------
		EmailSettings result = emailSettingsService.getEmailSettings();
		// --------------------------------------------------
					
		// check --------------------------------------------
		assertEquals( emailSettings.getId() ,  result.getId() );
		// --------------------------------------------------
	}
	
	// public void saveSettings( EmailSettings emailSettings );
	
	@Test
	public void testSaveSattings() {
		// Simulate the database ----------------------------
		EmailSettings emailSettings;
		emailSettings = new EmailSettings();
		
		Long id = 1L;
		emailSettings.setId(id);
		
		// when( emailSettingsRepository.findById(id) ).thenReturn( Optional.of( emailSettings ) );
		
		// --------------------------------------------------
							
		// test ---------------------------------------------
		// EmailSettings result = emailSettingsService.getEmailSettings();
		emailSettingsService.saveSettings(emailSettings);
		// --------------------------------------------------
							
		// check --------------------------------------------
		verify( emailSettingsRepository , times(1) ).save( emailSettings );
		// --------------------------------------------------
	}
	
	// public void saveSettings( String host, int port, String username, String password, boolean enableAuth, boolean enableTLS, boolean enableSSL);
	
	public void testSaveSettings() {
		
	}
	
}
