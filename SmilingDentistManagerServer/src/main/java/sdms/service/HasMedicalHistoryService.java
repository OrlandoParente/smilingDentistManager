package sdms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.model.HasMedicalHistory;
import sdms.model.MedicalHistory;
import sdms.repository.CustomerRepository;
import sdms.repository.HasMedicalHistoryRepository;
import sdms.repository.MedicalHistoryRepository;

@Service
public class HasMedicalHistoryService implements HasMedicalHistoryServiceInterface {

	@Autowired
	HasMedicalHistoryRepository repository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	MedicalHistoryRepository medicalHistoryRepository;

	@Override
	public void postHasMedicalHistory(HasMedicalHistory hasMedicalHistory) {
		
		repository.save( hasMedicalHistory );
	}

	@Override
	public HasMedicalHistory getHasMedicalHistoryById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<HasMedicalHistory> getHasMedicalHistories() {
		
		return repository.findAll();
	}

	@Override
	public List<HasMedicalHistory> getHasMedicalHistoriesByMedicalHistory(MedicalHistory medicalHistory) {
		
		return repository.findByMedicalHistory(medicalHistory);
	}

	@Override
	public List<HasMedicalHistory> getHasMedicalHistoriesByCustomer(Customer customer) {
		
		return repository.findByCustomer(customer);
	}

	@Override
	public void putHasMedicalHistory(HasMedicalHistory hasMedicalHistory) {
		
		repository.save( hasMedicalHistory );
	}

	@Override
	public void deleteHasMedicalHistoryById(long id) {
		
		repository.delete( repository.findById(id).get() );
	}
	
	@Override
	public void deleteHasMedicalHistoryByIdCustomerAndIdMedicalHistory( long idCustomer, long idMedicalHistory ) {
		
		Customer customer = customerRepository.findById(idCustomer).get();
		MedicalHistory medicalHistory = medicalHistoryRepository.findById( idMedicalHistory ).get();
		
		// Searches for hasMedicalHistory( idCustomer )
		List< HasMedicalHistory > listHasMedicalHistory = repository.findByCustomer( customer );
		
		// Searches for hasMedicalHistory( idCustomer, idMedicalHistory ) 
		HasMedicalHistory hasMedicalHistory = listHasMedicalHistory.stream()
												.filter( hasMH -> hasMH.getMedicalHistory().getId() == medicalHistory.getId() )
												.findFirst().orElse(null); // There should be only one hasMedicalHistory( idCustomer, idMedicalHistory )
		
		// It's a good idea manage the exception with idCustomer, idMedicalHistory wrong
		// if( hasMedicalHistory == null ) throw new Exception(" hasMedicalHistory not found in the db  ");
		
		repository.delete(hasMedicalHistory);
	}
}


















