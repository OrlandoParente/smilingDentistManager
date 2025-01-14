package sdms.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "medical_history" )
public class MedicalHistory {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	// FOREIGN KEYS ######################################################
	
	@OneToMany( mappedBy = "medicalHistory" )
	private List<HasMedicalHistory> hasMedicalHistories;
	
	// ###################################################################
	
	private String type;		   // [ anamnesi generale o odontoiatrica ]
	private String category;
	private String name;
	private String description;
	
	
	// Empty Constructor 
	public MedicalHistory() {}


	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", hasMedicalHistories= [ has medical histories ... ] " + /* hasMedicalHistories + */ ", type=" + type
				+ ", category=" + category + ", name=" + name + ", description=" + description + "]";
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

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public List<HasMedicalHistory> getHasMedicalHistories() {
		return hasMedicalHistories;
	}


	public void setHasMedicalHistories(List<HasMedicalHistory> hasMedicalHistories) {
		this.hasMedicalHistories = hasMedicalHistories;
	}


	
	
	
}
