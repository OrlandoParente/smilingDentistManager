package sdms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "medical_history" )
public class MedicalHistory {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS ######################################################
	
	@ManyToOne
	@JoinColumn( name = "id_customer" )
	private Customer customer;
	
	// ###################################################################
	
	private String type;		   // [ anamnesi generale o odontoiatrica ]
	private String name;
	private String description;
	
	
	// Empty Constructor 
	public MedicalHistory() {}


	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", idCustomer=" + this.getIdCustomer() + ", type=" + type + ", name=" + name
				+ ", description=" + description + "]";
	}

	// GETTERS AND SETTERS 
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdCustomer() {
		
		if( this.customer != null )
			return this.customer.getId();
		
		// errore
		return -1;
	}

	public void setIdCustomer(long idCustomer) {
		
		if( this.customer == null )
			this.customer = new Customer();
		
		this.customer.setId(idCustomer);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		// It could be useful insert a check on the type we set up
		// type = "generale" o "odontoiatrica" 
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	
	
	
}
