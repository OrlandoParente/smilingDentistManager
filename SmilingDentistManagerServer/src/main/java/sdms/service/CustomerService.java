package sdms.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sdms.model.Customer;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.HasMedicalHistoryRepository;
import sdms.util.DateAndTimeManager;

@Service
public class CustomerService implements CustomerServiceInterface {

	private final static Logger LOGGER = LoggerFactory.getLogger( CustomerService.class );
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	HasMedicalHistoryRepository hasMedicalHistoryRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;
	
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
		return repository.findCustomerByTaxIdCodeContainingOrNameContainingOrSurnameContainingOrBirthCityContainingOrBirthCityProvinceContainingOrBirthDateStringContainingOrResidenceStreetContainingOrHouseNumberContainingOrResidenceCityContainingOrResidenceCityCapContainingOrResidenceProvinceContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(
							keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, 
							keyWord, keyWord, keyWord, keyWord, keyWord, keyWord, keyWord);
	}

	@Override
	@Transactional
	public void postCustomer(Customer customer) {
		
		// for avoid exception: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: sdms.model.Customer.appointments: could not initialize proxy - no Session
		Hibernate.initialize(customer.getAppointments());
		
		LOGGER.info("postCustomer -> " + customer );
		
		repository.save(customer);
		
	}

	@Override
	@Transactional
	public void putCustomer(Customer customer) {
		
		// for avoid exception: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: sdms.model.Customer.appointments: could not initialize proxy - no Session
//		Hibernate.initialize(customer.getAppointments());
		
		LOGGER.info("putCustomer -> " + customer );
		
		repository.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(long id) {
		
		Customer customer = repository.findById(id).get();
		
		// Delete the constraints -------------------------------------------------------------------------
		
		hasMedicalHistoryRepository.findByCustomer(customer).forEach( hasMedicalHistory -> {
			hasMedicalHistoryRepository.delete(hasMedicalHistory);
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
		
		return repository.findByEMail(eMail).orElse(null);
	}
	
}
