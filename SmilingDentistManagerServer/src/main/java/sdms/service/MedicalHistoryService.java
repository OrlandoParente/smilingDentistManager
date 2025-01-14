package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.model.MedicalHistory;
import sdms.repository.CustomerRepository;
import sdms.repository.HasMedicalHistoryRepository;
import sdms.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryService implements MedicalHistoryServiceInterface {

	@Autowired
	MedicalHistoryRepository repository;
	
	@Autowired
	HasMedicalHistoryRepository hasMedicalHistoryRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public List<MedicalHistory> getMedicalHistories() {
		
		return repository.findAll();
	}
	
	@Override
	public List<MedicalHistory> getMedicalsHistoryByCustomerId(long idCustomer) {
		
		Customer customer = customerRepository.findById(idCustomer).get();
		
		List<MedicalHistory> medicalHistories = hasMedicalHistoryRepository.findByCustomer(customer).stream()
																			.map( hasMH -> hasMH.getMedicalHistory() )
																			.toList();
		
		
		return medicalHistories;
	}

	@Override
	public MedicalHistory getMedicalHistoryById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public void postMedicalHistory(MedicalHistory medicalHistory) {
		
		repository.save(medicalHistory);
	}

	@Override
	public void putMedicalHistory(MedicalHistory medicalHistory) {
		
		repository.save(medicalHistory);
	}
	
	@Override
	public void deleteMedicalHistoryById(Long id) {
		
		// delete constraints first --------------------------------------------------------------
		
		MedicalHistory medicalHistory = repository.findById(id).get();
		
		hasMedicalHistoryRepository.findByMedicalHistory( medicalHistory ).forEach( hasMH -> {
			hasMedicalHistoryRepository.delete(hasMH);
		} );
		
		// ---------------------------------------------------------------------------------------
		
		repository.delete( repository.findById(id).get() );
	}

	@Override
	public List<String> getMedicalHistoryTypes() {
		
		return repository.findDistinctTypes();
	}

	@Override
	public List<String> getMedicalHistoryCategories() {
		
		return repository.findDistinctCategories();
	}

	@Override
	public List<MedicalHistory> getMedicalsHistoryByType( String type ) {
		
		return repository.findByType(type);
	}

	@Override
	public List<MedicalHistory> getMedicalsHistoryByCategory( String category ) {
		
		return repository.findByCategory(category);
	}

	@Override
	public List<MedicalHistory> getMedicalsHistoryByTypeAndCategory( String type, String category ) {
		
		return repository.findByTypeAndCategory(type, category);
	}	
	
}
