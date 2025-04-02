package sdms.email;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import sdms.model.Customer;
import sdms.model.EmailTemplate;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmailSenderServiceInterface;
import sdms.service.EmailTemplateServiceInterface;

@Component
public class SheduledEmailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger( SheduledEmailSender.class );
	
	@Autowired
	private EmailSenderServiceInterface emailSenderService;
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private EmailTemplateServiceInterface emailTemplateService;
	
	@Transactional
	@Scheduled( cron = "*/30 * * * * *" )
	public void sendPeriodicRecallEmails() {
		
		LOGGER.info("SCHEDULED E-MAIL SENDER -> Check for recall email candidates");
		
		List<Customer> customers = customerService.getCustomers();
		
		LocalDate today = LocalDate.now();
		
		EmailTemplate recallEmailTemplate = emailTemplateService.getEmailTemplateRecall();
		
		for( Customer customer : customers ) {
			if( customer.getDaysToNextRecallEmail() == AutomaticEmailUtil.AUTOMATIC_EMAIL_DISABLED )
				continue;
			
			if( customer.getNextRecallEmailDate().isEqual(today) ) {
				
				// Set new date for next recall email
				LocalDate nextRecallDate = today.plusDays( customer.getDaysToNextRecallEmail() );
				customer.setNextRecallEmailDate(nextRecallDate);
				
				// update new recall email date on the database
				customerService.putCustomer(customer);
				
				// Send the recall email
				emailSenderService.sendEmail( recallEmailTemplate.getEmailFrom(), customer.geteMail(), recallEmailTemplate.getSubject(), recallEmailTemplate.getText() );
				
				
			}
		}
		
		
	}
}

/* CRON EXPRESSION
 * secondi minuti ore giornoMese mese giornoSettimana [anno (opzionale)]
 * 
 * the symbol *: Qualsiasi valore
 * the symbol ,: Elenco di valori. Esempio: 1,15 
 * the symbol -: Intervallo. Esempio: 10-15
 * the symbol /: Incrementi. Esempio: 0/5 
 * the symbol ?: Nessun valore specificato (usato per evitare conflitti tra giorno del mese e giorno della settimana).
 * the symbol L: Ultimo valore possibile
 * the symbol W: Giorno feriale più vicino. Esempio 15W significa il giorno feriale più vicino al 15.
 * the symbol #: N-esimo giorno della settimana nel mese. Esempio: 2#1 indica il primo lunedì del mese.
 * 
 * ESEMPI
 * Ogni minuto: * * * * *
 * Ogni giorno alle 8:00: 0 0 8 * * ?  
 * Ogni ultimo giorno del mese alle 23:59: 59 59 23 L * ?
 * Ogni lunedì alle 9:30: 0 30 9 ? * MON
 * 
 */
// Ogni 5 minuti: 0 */5 * * * ?