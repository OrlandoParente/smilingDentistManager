package sdms.service;

public interface EmailSenderServiceInterface {

	public void sendEmail(String from, String to, String subject, String text);
	public void sendHtmlEmail( String from, String to, String subject, String htmlBody );
	
//	public void sendResetPasswordEmail();
}
