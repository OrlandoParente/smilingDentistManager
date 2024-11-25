package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Customer;
import sdms.repository.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface {

	@Autowired
	CustomerRepository repository;
	
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

	// <<<<<<<<<<<<<<<<<<<<<----------------------------------------------------------------------------------
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
		repository.delete( repository.findById(id).get() );
		
	}
	
}
