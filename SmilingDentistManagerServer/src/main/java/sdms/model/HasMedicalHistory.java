package sdms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "has_medical_history" )
public class HasMedicalHistory {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS ######################################################
	
	@ManyToOne
	@JoinColumn( name = "id_customer" )
	private Customer customer;
	
	@ManyToOne
	@JoinColumn( name = "id_medical_history" )
	private MedicalHistory medicalHistory;
	
	
	// ###################################################################
		
	private String notes;

	public HasMedicalHistory() {}
	

	@Override
	public String toString() {
		return "HasMedicalHistory [id=" + id + ", customer=" + customer + ", medicalHistory=" + medicalHistory
				+ ", notes=" + notes + "]";
	}


	// GETTERS AND SETTERS 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
