package sdms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sdms.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


	// CREATE ---------------------------------------------------------------
	
	// UPDATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	@Query("SELECT MAX(e.id) FROM Employee e")
	Long findMaxId();
	
	Optional<Employee> findByEMail(String eMail);
	
	List<Employee> findByName(String name);
	List<Employee> findByNameContaining( String name );
	
	List<Employee> findBySurname(String surname);
	List<Employee> findBySurnameContaining(String surname);
	
	List<Employee> findByNameOrSurnameOrBirthDateOrPhoneNumberOrPhoneNumber2OrEMail(String name, String surname, String date, String phoneNum, String phoneNum2, String email);
	List<Employee> findByNameContainingOrSurnameContainingOrBirthDateContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(String name, String surname, String date, String phoneNum, String phoneNum2, String email);
	
	// DELETE ---------------------------------------------------------------
		
	
}
