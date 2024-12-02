package sdms.dto;

public class TreatmentDTO {

	private long id; 
	
	private String name;
	private String description;
	private float cost;
	
	public TreatmentDTO() {}
	
	public TreatmentDTO(int id, String name, String description, float cost) {
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
