package sdms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.ProfessionalRole;

@Repository
public interface ProfessionalRoleRepository extends JpaRepository<ProfessionalRole, Long> {


	// CREATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	Optional<ProfessionalRole> findById(long id);
	List<ProfessionalRole> findByName( String name );
	
	// UPDATE ---------------------------------------------------------------
		
	// DELETE ---------------------------------------------------------------

}
