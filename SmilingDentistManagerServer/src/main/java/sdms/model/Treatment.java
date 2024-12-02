package sdms.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "treatment" )
public class Treatment {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id; 
	
	@OneToMany( mappedBy = "treatment" )
	private List<Appointment> appointments;
	
	private String name;
	private String description;
	private float cost;
	
	public Treatment() {}
	
	public Treatment(int id, String name, String description, float cost) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
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
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	
	

}
