package sdms.dto;

public class HasProfessionalRoleDTO {
	
	private Long id;
	
	// FOREIGN KEYS #######################################################
	 private long idEmployee; 
	 private long idProfessionalRole;
	 
	// ####################################################################
	
	public HasProfessionalRoleDTO() {}
	
	public HasProfessionalRoleDTO(long id, long idEmployee, long idProfessionalRole) {

		this.id = id;
		this.idEmployee = idEmployee;
		this.idProfessionalRole = idProfessionalRole;
	}
	
	// GETTERS AND SETTERS
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public long getIdProfessionalRole() {
		return idProfessionalRole;
	}

	public void setIdProfessionalRole(long idProfessionalRole) {
		this.idProfessionalRole = idProfessionalRole;
	}
		

}
