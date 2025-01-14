package sdms.dto;

public class MedicalHistoryDTO {
	
	private long id;
	
	
	private String type;		   // [ anamnesi generale o odontoiatrica ]
	private String category;
	private String name;
	private String description;
	
	
	public MedicalHistoryDTO() {}
	
	
	@Override
	public String toString() {
		return "MedicalHistoryDTO [id=" + id + ", type=" + type + ", category=" + category + ", name=" + name
				+ ", description=" + description + "]";
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
	
	
	
	
}
