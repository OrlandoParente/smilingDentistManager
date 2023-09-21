package sdms.model;

public class MedicalHistory {
	
	private int id;
	private int idCustomer;
	private String type;		   // [ anamnesi generale o odontoiatrica ]
	private String name;
	private String description;
	
	
	public MedicalHistory(int id, int idCustomer, String type, String name, String description) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.type = type;
		this.name = name;
		this.description = description;
	}


	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", idCustomer=" + idCustomer + ", type=" + type + ", name=" + name
				+ ", description=" + description + "]";
	}

	// GETTERS AND SETTERS 
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(int idCustomer) {
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
