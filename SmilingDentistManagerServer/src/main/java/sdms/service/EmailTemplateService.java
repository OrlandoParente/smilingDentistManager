package sdms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sdms.model.EmailTemplate;
import sdms.repository.EmailTemplateRepository;

@Service
public class EmailTemplateService implements EmailTemplateServiceInterface {

	EmailTemplateRepository repository;

	// GET --------------------------------------------------------------
	
	@Override
	public EmailTemplate getEmailTemplateById( Long id ) {
		
		return repository.findById( id ).orElse(null);
	}

	@Override
	public EmailTemplate getEmailTemplateByType( String type ) {
		
		return repository.findByType(type).orElse(null);
	}
	
	// GET WITH DEFAULT TAGS -----------
	
	@Override
	public EmailTemplate getEmailTemplateRecall() {
		return repository.findByType( EmailTemplate.TYPE_RECALL).orElse(null);
	}
	// ---------------------------------
	
	
	@Override
	public List<EmailTemplate> getAllEmailTemplates() {
		
		return repository.findAll();
	}
	// ------------------------------------------------------------------
	
	// POST/PUT WITH DEFAULT TAGS ---------------------------------------
	@Override
	public void saveRecallEmailTemplate(EmailTemplate emailTemplate) {
		
		// check if already exist recall email
		EmailTemplate tmpEmailTemplate = repository.findByType( EmailTemplate.TYPE_RECALL ).get();
		
		if( tmpEmailTemplate == null ) {
			tmpEmailTemplate = new EmailTemplate();
		}
		
		// update emailTemaplate fields
		tmpEmailTemplate.setType( EmailTemplate.TYPE_RECALL );
		
		tmpEmailTemplate.setEmailFrom( emailTemplate.getEmailFrom() );
		tmpEmailTemplate.setSubject( emailTemplate.getSubject() );
		tmpEmailTemplate.setText( emailTemplate.getText() );
		
		repository.save( tmpEmailTemplate );
	}

	// ------------------------------------------------------------------
	
	// POST -------------------------------------------------------------
	
	@Override
	public void postEmailTemplate(EmailTemplate emailTemplate) {
		
		// Obs: We can set this constraint directly in the entity 
		// Check if email type is null
		if( emailTemplate.getType() == null ) 
		    throw new IllegalArgumentException("type field is null.");
		
		// Check if emailTemplate use an already saved email type
//		EmailTemplate tmpEmailTemplate = repository.findByType( emailTemplate.getType() ).get();
//		if( tmpEmailTemplate != null )
//			throw new ();
		// IF type is duplicated it should throw ConstraintViolationException
		
		repository.save( emailTemplate );
		
	}

	// ------------------------------------------------------------------
	
	
	// PUT --------------------------------------------------------------
	
	@Override
	public void putEmailTemplate(EmailTemplate emailTemplate) {
		
		EmailTemplate tmpEmailTemplate = repository.findByType( emailTemplate.getType() ).get();
		
		// check if emailTemplate with that type exist
		if( tmpEmailTemplate == null ) {
			
			// try to update emailTemplate by id
			if( emailTemplate.getId() != null )
				tmpEmailTemplate = repository.findById( emailTemplate.getId() ).get();
			
			if( tmpEmailTemplate == null )
				throw new EntityNotFoundException("Doesn'exist an email template to update in the database");
		}
		
		// update emailTemaplate fields
		tmpEmailTemplate.setEmailFrom( emailTemplate.getEmailFrom() );
		tmpEmailTemplate.setSubject( emailTemplate.getSubject() );
		tmpEmailTemplate.setType( emailTemplate.getType() );
		tmpEmailTemplate.setText( emailTemplate.getText() );
		
		repository.save( tmpEmailTemplate );
		
	}
	// ------------------------------------------------------------------

	// DELETE -----------------------------------------------------------
	@Override
	public void deleteEmailTemplateByType(String type) {
		
		if (type == null || type.isEmpty()) {
		    throw new IllegalArgumentException("type field can not be null or empty");
		}
		
		EmailTemplate emailTemplate = repository.findByType(type)
				.orElseThrow( () -> new EntityNotFoundException("No entity found in the database with type -> " + type ) );		
	
		repository.delete(emailTemplate);
	}


	@Override
	public void deleteEmailTemplateById(Long id) {
		
		EmailTemplate emailTemplate = repository.findById(id)
				.orElseThrow( () -> new EntityNotFoundException("No entity found in the database with id -> " + id ) );		
	
		repository.delete(emailTemplate);
		
	}
	
	// ------------------------------------------------------------------

	
}
