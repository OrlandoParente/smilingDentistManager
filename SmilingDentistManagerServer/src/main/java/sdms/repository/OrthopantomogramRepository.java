package sdms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sdms.model.Customer;
import sdms.model.Orthopantomogram;
import java.time.LocalDate;


public interface OrthopantomogramRepository extends JpaRepository<Orthopantomogram, Long> {

	Optional<Orthopantomogram> findByFilename(String filename);
	
	List<Orthopantomogram> findByCustomer(Customer customer);
	List<Orthopantomogram> findByDate(LocalDate date);
	List<Orthopantomogram> findByFormat(String format);
	
}
