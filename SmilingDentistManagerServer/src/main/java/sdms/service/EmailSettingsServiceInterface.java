package sdms.service;

import sdms.model.EmailSettings;

public interface EmailSettingsServiceInterface  {

	public EmailSettings getEmailSettings();
	
	public void saveSettings( EmailSettings emailSettings );
	public void saveSettings( String host, int port, String username, String password, boolean enableAuth, boolean enableTLS, boolean enableSSL);
}
