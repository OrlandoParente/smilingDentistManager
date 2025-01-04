package sdms.repository;

import java.time.LocalDate;
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
	
	List<Employee> findByTitleOrNameOrSurnameOrBirthDateOrPhoneNumberOrPhoneNumber2OrEMailOrSalary(String title, String name, String surname, LocalDate date, String phoneNum, String phoneNum2, String email, Double salary);
	List<Employee> findByTitleContainingOrNameContainingOrSurnameContainingOrBirthDateStringContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContainingOrSalaryStringContaining(String title, String name, String surname, String date, String phoneNum, String phoneNum2, String email, String salary);
	
	// DELETE ---------------------------------------------------------------
		
	
}
