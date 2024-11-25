package sdms.service;

import sdms.model.HasProfessionalRole;

public interface HasProfessionalRoleServiceInterface {
	
//	// Gestione Has Professional Role -----------------------
//	boolean postLinkEmployeeToProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	public void postLinkEmployeeToProfessionalRole( HasProfessionalRole hasProfessionalRole );
	
//	
//	boolean deleteLinkEmployeeWithProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
	public void deleteLinkEmployeeWithProfessionalRole( long idEmployee, long idProfessionalRole );
	public void deleteLinkEmployeeWithProfessionalRole( long id );
//	// ------------------------------------------------------

	
	
}
