package sdms.model;

public class HasProfessionalRole {
	
	private int idEmployee; 
	private int idProfessionalRole;
	
	public HasProfessionalRole(int idEmployee, int idProfessionalRole) {
		super();
		this.idEmployee = idEmployee;
		this.idProfessionalRole = idProfessionalRole;
	}
	
	// GETTERS AND SETTERS
	
	public int getidEmployee() {
		return idEmployee;
	}
	public void setidEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public int getidProfessionalRole() {
		return idProfessionalRole;
	}
	public void setidProfessionalRole(int idProfessionalRole) {
		this.idProfessionalRole = idProfessionalRole;
	}
	
	

}
