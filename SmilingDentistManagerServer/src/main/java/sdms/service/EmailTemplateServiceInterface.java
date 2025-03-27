package sdms.service;

import java.util.List;

import sdms.model.EmailTemplate;

public interface EmailTemplateServiceInterface {

	public EmailTemplate getEmailTemplateById( Long id );
	public EmailTemplate getEmailTemplateByType( String type );
	
	// Use default tags
	public EmailTemplate getEmailTemplateRecall();
	
	
	public List<EmailTemplate> getAllEmailTemplates();
	
	// Use default tags
	public void saveRecallEmailTemplate( EmailTemplate emailTemplate );
	
	// Generic post/put Email Template
	public void postEmailTemplate( EmailTemplate emailTemplate );
	public void putEmailTemplate( EmailTemplate emailTemplate );
	
	public void deleteEmailTemplateByType( String type );
	public void deleteEmailTemplateById( Long id );
}
