package sdms.email;

import org.springframework.context.ApplicationEvent;

import sdms.model.EmailSettings;

// Event for notify EmailConfig when update the bean javaMailSender
public class EmailSettingsUpdatedEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    
	private final EmailSettings emailSettings;

    public EmailSettingsUpdatedEvent(Object source, EmailSettings emailSettings) {
        super(source);
        this.emailSettings = emailSettings;
    }

    public EmailSettings getEmailSettings() {
        return emailSettings;
    }
    
}
