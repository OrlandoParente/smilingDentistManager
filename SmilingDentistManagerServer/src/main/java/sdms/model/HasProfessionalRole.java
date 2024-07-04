package sdms.model;

public class HasProfessionalRole {
	
	private long id;
	private int idEmployee; 
	private int idProfessionalRole;
	
	public HasProfessionalRole(long id, int idEmployee, int idProfessionalRole) {
		super();
		this.id = id;
		this.idEmployee = idEmployee;
		this.idProfessionalRole = idProfessionalRole;
	}
	
	// GETTERS AND SETTERS
	public long getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public int getIdProfessionalRole() {
		return idProfessionalRole;
	}
	public void setIdProfessionalRole(int idProfessionalRole) {
		this.idProfessionalRole = idProfessionalRole;
	}
	
	

}
