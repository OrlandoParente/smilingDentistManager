package sdms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailTemplate {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	private String type; // Recall ( richiamo ), Marketing, ecc ...
	private String from;
	private String subject;
	private String text;
	
	
	public EmailTemplate() { }


	// To String method 
	@Override
	public String toString() {
		return "EmailTemplate [id=" + id + ", type=" + type + ", from=" + from + ", subject=" + subject + ", text="
				+ text + "]";
	}


	// GETTERS AND SETTERS
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
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
