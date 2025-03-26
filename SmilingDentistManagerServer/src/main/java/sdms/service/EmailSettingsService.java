package sdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.EmailSettings;
import sdms.repository.EmailSettingsRepository;

@Service
public class EmailSettingsService implements EmailSettingsServiceInterface {

	@Autowired
	private EmailSettingsRepository repository;
	
	@Override
	public EmailSettings getEmailSettings() {
		
		return repository.findById( EmailSettings.DEFAULT_UNIQUE_ID ).orElse(null);
	}
	
	@Override
	public void saveSettings(String host, int port, String username, String password, boolean enableAuth,
			boolean enableTLS, boolean enableSSL) {
		
		EmailSettings emailSettings = new EmailSettings();
		emailSettings.setHost(host);
		emailSettings.setPort(port);
		emailSettings.setUsername(username);
		emailSettings.setPassword(password);
		emailSettings.setEnableAuth(enableAuth);
		emailSettings.setEnableTLS(enableTLS);
		emailSettings.setEnableSSL(enableSSL);
		
		// We want always only ONE email account in the database
		emailSettings.setId( EmailSettings.DEFAULT_UNIQUE_ID );
		
		repository.save( emailSettings );
	}

	@Override
	public void saveSettings(EmailSettings emailSettings) {
		
		// We want always only ONE email account in the database
		emailSettings.setId( EmailSettings.DEFAULT_UNIQUE_ID );
		
		repository.save( emailSettings );		
	}

}
