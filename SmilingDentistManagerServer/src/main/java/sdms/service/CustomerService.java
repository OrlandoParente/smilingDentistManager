package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.MedicalHistoryRepository;

@Service
public class CustomerService implements CustomerServiceInterface {

	@Autowired
	CustomerRepository repository;
	
	@Autowired
	MedicalHistoryRepository medicalHistoryRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	// We need this for keep the compatibility with the Swing Client
	@Override
	public long getLastCustomerId() {
		
		return repository.findMaxId().longValue();
	}
	
	@Override
	public Customer getCustomerById(long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Customer> getCustomers() {
		return repository.findAll();
	}

	@Override
	public List<Customer> getCustomersByPartialKeyWordOverAllFields(String keyWord) {
				
		// this work as well, this one is done with @Query
		// return repository.findCustomersByPartialKeyWordOverAllFields( keyWord );
		
		// this one is done with JpaInteface 
		return repository.findCustomerByTaxIdCodeContainingOrNameContainingOrSurnameContainingOrBirthCityContainingOrBirthCityProvinceContainingOrBirthDateContainingOrResidenceStreetContainingOrHouseNumberContainingOrResidenceCityContainingOrResidenceCityCapContainingOrResidenceProvinceContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(
							keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord);
	}

	@Override
	public void postCustomer(Customer customer) {
		repository.save(customer);
		
	}

	@Override
	public void putCustomer(Customer customer) {
		repository.save(customer);
	}

	@Override
	public void deleteCustomer(long id) {
		
		Customer customer = repository.findById(id).get();
		
		// Delete the constraints -------------------------------------------------------------------------
		
		medicalHistoryRepository.findByCustomer(customer).forEach( medicalHistory -> {
			medicalHistoryRepository.delete(medicalHistory);
		});
		
		expenseRepository.findByCustomer(customer).forEach( expense -> {
			expenseRepository.delete(expense);
		});
		
		appointmentRepository.findByCustomer(customer).forEach( appointment -> {
			appointmentRepository.delete(appointment);
		});
		
		// ------------------------------------------------------------------------------------------------
		
		repository.delete( customer );
		
	}

	@Override
	public Customer getCustomerByEMail(String eMail) {
		
		return repository.findByEMail(eMail).get();
	}
	
}
