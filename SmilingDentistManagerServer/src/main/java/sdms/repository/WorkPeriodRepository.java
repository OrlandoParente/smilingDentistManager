package sdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sdms.model.Employee;
import sdms.model.WorkPeriod;

public interface WorkPeriodRepository extends JpaRepository<WorkPeriod, Long> {

	// CREATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	List<WorkPeriod> findByEmployee(Employee employee);
	
	// UPDATE ---------------------------------------------------------------

		
	// DELETE ---------------------------------------------------------------

}
