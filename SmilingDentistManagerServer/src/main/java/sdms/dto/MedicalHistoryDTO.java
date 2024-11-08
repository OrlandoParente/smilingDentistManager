package sdms.dto;

public class MedicalHistoryDTO {
	
	private long id;
	
	// FOREIGN KEYS ######################################################
	private long idCustomer;
	
	// ###################################################################
	
	
	private String type;		   // [ anamnesi generale o odontoiatrica ]
	private String name;
	private String description;
	
	
	public MedicalHistoryDTO() {}
	
	public MedicalHistoryDTO(int id, long idCustomer, String type, String name, String description) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.type = type;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "MedicalHistoryDTO [id=" + id + ", idCustomer=" + idCustomer + ", type=" + type + ", name=" + name
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
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
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
	
	
}
