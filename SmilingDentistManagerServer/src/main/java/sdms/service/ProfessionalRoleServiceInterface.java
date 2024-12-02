package sdms.service;

import java.util.List;

import sdms.model.ProfessionalRole;

public interface ProfessionalRoleServiceInterface {

	
//	// Gestione Professional Role ---------------------------
	public ProfessionalRole getProfessionalRoleById( long id );
	
//	ArrayList<ProfessionalRole> getProfessionalRoles() throws SQLException;
	public List<ProfessionalRole> getProfessionalRoles();
	
//	ArrayList<ProfessionalRole> getProfessionalRolesAssociatedToIdEmployee( String id_employee ) throws SQLException;
	public List<ProfessionalRole> getProfessionalRolesAssociatedToIdEmployee( long idEmployee );
	
//	
//	boolean postProfessionalRole( String name ) throws SQLException;
//	boolean postProfessionalRole( String name, String description ) throws SQLException;
	public void postProfessionalRole( ProfessionalRole professionalRole );
	
//	boolean putProfessionalRoleById( String id, String name, String description ) throws SQLException;
	public void putProfessionalRole( ProfessionalRole professionalRole );
	
//	boolean deleteProfessionalRoleById( String id ) throws SQLException;	
	public void deleteProfessionalRoleById( Long id );
	
//	// ------------------------------------------------------
//	

}
