package sdms.dto;

public class EmailTemplateDTO {
	
	private Long id;
	
	private String type; // Recall ( richiamo ), Marketing, ecc ...
	
	private String emailFrom;	// "from" is a reserved word for MySql 
	private String subject;
	private String text;
	
	
	public EmailTemplateDTO() { }


	// To String method 
	@Override
	public String toString() {
		return "EmailTemplatedto [id=" + id + ", type=" + type + ", emailFrom=" + emailFrom + ", subject=" + subject + ", text="
				+ text + "]";
	}


	// GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEmailFrom() {
		return emailFrom;
	}


	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
