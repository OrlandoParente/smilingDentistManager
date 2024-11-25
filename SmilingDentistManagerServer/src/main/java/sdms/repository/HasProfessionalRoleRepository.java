package sdms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;

import java.util.List;


@Repository
public interface HasProfessionalRoleRepository extends JpaRepository<HasProfessionalRole, Long>{

	// CREATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	List<HasProfessionalRole> findByEmployee(Employee employee);
	List<HasProfessionalRole> findByProfessionalRole(ProfessionalRole professionalRole);
	
	// UPDATE ---------------------------------------------------------------
	
	// DELETE ---------------------------------------------------------------
		
}
