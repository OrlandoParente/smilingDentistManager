package sdms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sdms.model.Appointment;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.model.Treatment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	// CREATE --------------------------------------------------------------------
	
	// UPDATE --------------------------------------------------------------------
		
	// READ ----------------------------------------------------------------------

	List<Appointment> findByCustomer(Customer customer);
	List<Appointment> findByDoctor(Employee doctor);	
	List<Appointment> findByInvoiceNumber(String invoiceNumber);
	List<Appointment> findByTreatment(Treatment treatment);
	
	// DELETE --------------------------------------------------------------------
	
}
