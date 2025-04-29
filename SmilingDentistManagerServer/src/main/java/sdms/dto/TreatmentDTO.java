package sdms.dto;

public class TreatmentDTO {

	private Long id; 
	
	private String name;
	private String description;
	private float cost;
	
	public TreatmentDTO() {}
	
	// <================================== Is this needed ?? Can I delete this? 
	public TreatmentDTO(Long id, String name, String description, float cost) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.cost = cost;
	}
	
	// ToString 
	@Override
	public String toString() {
		return "TreatmentDTO [id=" + id + ", name=" + name + ", description=" + description + ", cost=" + cost + "]";
	}
	
	// GETTERS AND SETTERS
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
