package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.model.MedicalHistory;
import sdms.repository.CustomerRepository;
import sdms.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryService implements MedicalHistoryServiceInterface {

	@Autowired
	MedicalHistoryRepository repository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	@Override
	public List<MedicalHistory> getMedicalsHistoryByCustomerId(long idCustomer) {
		
		Customer customer = customerRepository.findById(idCustomer).get();
		
		return repository.findByCustomer(customer);
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
		
		repository.delete( repository.findById(id).get() );
	}	
	
}
