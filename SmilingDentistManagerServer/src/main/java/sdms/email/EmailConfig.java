package sdms.email;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import sdms.model.EmailSettings;
import sdms.service.EmailSettingsServiceInterface;

@Configuration
public class EmailConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger( EmailConfig.class );
	
	@Autowired
	EmailSettingsServiceInterface emailSettingsService;
	
	private JavaMailSenderImpl javaMailSender;
	
	@Bean
	JavaMailSender getJavaMailSender() {
	
		EmailSettings emailSettings = emailSettingsService.getEmailSettings();
			
		if( javaMailSender == null )
			javaMailSender = new JavaMailSenderImpl();
		
		updateJavaMailSender(emailSettings );
		
		return javaMailSender;
		
		
	}
	
	// Update javaMailSettings through events for avoid cycle dependencies 
    @EventListener
    public void handleEmailSettingsUpdatedEvent(EmailSettingsUpdatedEvent event) {
    	
        EmailSettings eventEmailSettings = event.getEmailSettings();

        updateJavaMailSender( eventEmailSettings );
        
        LOGGER.info("Email Settings updated");
    }
	
	// Call this every time the emailSettings entity is updated
	public void updateJavaMailSender( EmailSettings emailSettings ) {
		
		if( emailSettings == null ) {
			LOGGER.warn("Email setting are null");
			return;
		}
//		EmailSettings emailSettings = service.getEmailSettings();
//		EmailSettings emailSettings = emailSettingsService.getEmailSettings();
		
		javaMailSender.setHost( emailSettings.getHost() );
		javaMailSender.setPort( emailSettings.getPort() );
		javaMailSender.setUsername( emailSettings.getUsername() );
		javaMailSender.setPassword( emailSettings.getPassword() );
		
        Properties props = javaMailSender.getJavaMailProperties();
        
        props.put("mail.transport.protocol", "smtp");	// Should I save this in emailSettings object ? For let user able to change this
        props.put("mail.debug", "true");				// Should I save this in emailSettings object ? For let user able to change this
        
        props.put("mail.smtp.auth", emailSettings.isEnableAuth());
        props.put("mail.smtp.starttls.enable", emailSettings.isEnableTLS());
        props.put("mail.smtp.ssl.enable", emailSettings.isEnableSSL()); 
	}
	

}
