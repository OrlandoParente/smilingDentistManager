package sdms.service;

import java.util.List;

import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;

public interface HasProfessionalRoleServiceInterface {
	

	// CREATE
	public void postLinkEmployeeToProfessionalRole( HasProfessionalRole hasProfessionalRole );
	
	// READ
	public List<ProfessionalRole> getProfessionalRolesByEmployee( long idEmployee );
	public List<Employee> getEmployeesByProfessionalRole( long idProfessionalRole );
	public List<Employee> getEmployeesByProfessionalRoleName( String professionalRoleName );
	
	// UPDATE
	
	
	// DELETE
	public void deleteLinkEmployeeWithProfessionalRole( long idEmployee, long idProfessionalRole );
	public void deleteLinkEmployeeWithProfessionalRole( long id );

	
	
}
