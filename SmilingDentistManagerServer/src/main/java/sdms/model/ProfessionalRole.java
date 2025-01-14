package sdms.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "professional_role" )
public class ProfessionalRole {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@OneToMany( mappedBy = "professionalRole" )
	private List<HasProfessionalRole> hasProfessionalRoles;
	
	private String name;
	private String description;
	
	// Empty Constructor 
	public ProfessionalRole() {	}
	
	// GETTERS AND SETTERS 	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	

}
