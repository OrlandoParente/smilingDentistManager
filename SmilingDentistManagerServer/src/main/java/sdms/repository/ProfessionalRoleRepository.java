package sdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.ProfessionalRole;

@Repository
public interface ProfessionalRoleRepository extends JpaRepository<ProfessionalRole, Long> {


	// CREATE ---------------------------------------------------------------
//	boolean postProfessionalRole( String name ) throws SQLException;
//	boolean postProfessionalRole( String name, String description ) throws SQLException;
	
	// READ -----------------------------------------------------------------
//	ResultSet getProfessionalRoles() throws SQLException;
//	ResultSet getProfessionalRolesAssociatedToIdEmployee( String id_employee ) throws SQLException;	
	List<ProfessionalRole> findByName(String name);
	
	// UPDATE ---------------------------------------------------------------
//	boolean putProfessionalRoleById(String id, String name, String description ) throws SQLException;
		
	// DELETE ---------------------------------------------------------------
//	boolean deleteProfessionalRoleById( String id ) throws SQLException;	

}
