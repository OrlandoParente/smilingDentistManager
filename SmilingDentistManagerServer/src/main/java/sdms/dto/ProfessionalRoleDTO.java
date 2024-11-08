package sdms.dto;

public class ProfessionalRoleDTO {
	
	private long id;
		
	private String name;
	private String description;
	
	public ProfessionalRoleDTO() {	}
	
	public ProfessionalRoleDTO(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	
	

}
