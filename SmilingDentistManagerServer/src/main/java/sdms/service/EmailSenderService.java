package sdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService implements EmailSenderServiceInterface {

	@Autowired
	private JavaMailSender mailSender;
	
	
	public EmailSenderService( JavaMailSender mailSender ) {
	
	}

	@Override
	public void sendEmail(String from, String to, String subject, String text) {
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		
		mailSender.send(simpleMailMessage);
	}

	@Override
	public void sendHtmlEmail(String from, String to, String subject, String htmlBody) {

	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    MimeMessageHelper helper;
	    
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(htmlBody, true); // the second parameter means that's a html content
	        mailSender.send(mimeMessage);
	
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
	}
}
