package sdms.controller.api;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import sdms.dto.EmailTemplateDTO;
import sdms.model.EmailTemplate;
import sdms.service.EmailTemplateServiceInterface;

@RestController
public class EmailTemplateRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateRestController.class);

	@Autowired
	private EmailTemplateServiceInterface emailTemplateService;
	
	@Autowired
	ModelMapper modelMapper;
	
	// SAVE WITH EDFAULT TYPE TAGS -----------------------------------------------------------------
	
	@PostMapping( value={"/saveEmailTemplateRecall"}, params= { "from", "subject", "text"})
	public ResponseEntity<?> saveEmailTemplateRecall( @RequestParam String type, @RequestParam String from, 
														@RequestParam String subject, @RequestParam String text){
		
		
		LOGGER.info("/saveEmailTemplateRecall PARAMS={  from=" + from +"; subject=" + subject + "; text=" + text + "  }");
		
		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setType( EmailTemplate.TYPE_RECALL );
		emailTemplate.setEmailFrom(from);
		emailTemplate.setSubject(subject);
		emailTemplate.setText(text);
		
		try {
			emailTemplateService.saveRecallEmailTemplate(emailTemplate);
		} catch( IllegalArgumentException e ) {
			
			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.badRequest().body( e.getMessage() );
		
		} catch( Exception e ) {

			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().body( modelMapper.map(emailTemplate, EmailTemplateDTO.class) );
	}
	// ---------------------------------------------------------------------------------------------
	
	
	// POST -----------------------------------------------------------------------------------------
	
	@PostMapping( value={"/postEmailTemplate"}, params= {"type", "from", "subject", "text"})
	public ResponseEntity<?> saveSettings( @RequestParam String type, @RequestParam String from, 
											@RequestParam String subject, @RequestParam String text){
		
		
		LOGGER.info("/postEmailTemplate PARAMS={ type=" +type+ "; from=" + from +"; subject=" + subject + "; text=" + text + "  }");
		
		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setType(type);
		emailTemplate.setEmailFrom(from);
		emailTemplate.setSubject(subject);
		emailTemplate.setText(text);
		
		try {
			emailTemplateService.postEmailTemplate(emailTemplate);
		} catch( IllegalArgumentException | EntityNotFoundException | NoSuchElementException e ) {
			
			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.badRequest().body( e.getMessage() );
		
		} catch( Exception e ) {

			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().body( modelMapper.map(emailTemplate, EmailTemplateDTO.class) );
	}
	
	// ---------------------------------------------------------------------------------------------
	
	// PUT -----------------------------------------------------------------------------------------
	
	@PostMapping( value={"/putEmailTemplate"}, params= {"type", "from", "subject", "text"})
	public ResponseEntity<?> putEmailTemplate( @RequestParam String type, @RequestParam String from, 
											@RequestParam String subject, @RequestParam String text){
		
		
		LOGGER.info("/putEmailTemplate PARAMS={ type=" + type + "; from=" + from + "; subject=" + subject + "; text=" + text + "  }");
		
		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setType(type);
		emailTemplate.setEmailFrom(from);
		emailTemplate.setSubject(subject);
		emailTemplate.setText(text);
		
		try {
			emailTemplateService.putEmailTemplate(emailTemplate);
		} catch( IllegalArgumentException | EntityNotFoundException | NoSuchElementException e) {
			
			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.badRequest().body( e.getMessage() );
		
		} catch( Exception e ) {

			System.err.println( e.getMessage() );
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().body( modelMapper.map(emailTemplate, EmailTemplateDTO.class) );
	}
	// ---------------------------------------------------------------------------------------------
}
