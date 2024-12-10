package sdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Customer;
import sdms.model.DentalMaterial;
import sdms.model.Employee;
import sdms.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{


	// CREATE ---------------------------------------------------------------
	
	// UPDATE ---------------------------------------------------------------
	
	// READ -----------------------------------------------------------------
	List<Expense> findByEmployee(Employee employee);
	List<Expense> findByCustomer(Customer customer);
	List<Expense> findByDentalMaterial(DentalMaterial dentalMaterial);
	
	// DELETE ---------------------------------------------------------------

}
