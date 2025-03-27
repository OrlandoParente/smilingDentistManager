package sdms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailTemplate {
	
	public static final String TYPE_RECALL = "Recall";

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	// We suppose to have only one email per type 
	@Column( unique = true )
	private String type; // Recall ( richiamo ), Marketing, ecc ...
	
	private String emailFrom;	// "from" is a reserved word for MySql 
	private String subject;
	private String text;
	
	
	public EmailTemplate() { }


	// To String method 
	@Override
	public String toString() {
		return "EmailTemplate [id=" + id + ", type=" + type + ", emailFrom=" + emailFrom + ", subject=" + subject + ", text="
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
