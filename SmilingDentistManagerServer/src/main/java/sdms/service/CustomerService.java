package sdms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import sdms.model.Customer;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.HasMedicalHistoryRepository;
import sdms.util.DateAndTimeManager;
import sdms.util.FolderManager;

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
	OrthopantomogramServiceInterface orthopantomogramService;
	
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
//		Hibernate.initialize(customer.getAppointments());
		
		LOGGER.info("postCustomer -> " + customer );
		
		// Create customer folder, if customerFolder is set in customer, this method just create the folder
		// otherwise it create the path folder and the folder itself
		customer.setCustomerFolder( FolderManager.getCustomerFolder(customer) );
		
		repository.save(customer);
		
	}

	@Override
	@Transactional
	public void putCustomer(Customer customer) {
		
		// for avoid exception: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: sdms.model.Customer.appointments: could not initialize proxy - no Session
//		Hibernate.initialize(customer.getAppointments());
		
		LOGGER.info("putCustomer -> " + customer );
		
		Customer original = repository.findById( customer.getId() )
				.orElseThrow( () -> new EntityNotFoundException("Customer with id " + customer.getId() + " not found in the database") );
		
		if( ! original.getCustomerFolder().equals( customer.getCustomerFolder() ) ) {
			
			// Delete old folder
			FolderManager.deleteFolder( original.getCustomerFolder() );
			
			// Create customer folder, if customerFolder is set in customer, this method just create the folder
			// otherwise it create the path folder and the folder itself
			customer.setCustomerFolder( FolderManager.getCustomerFolder(customer) );
		}
		
		repository.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(long id) {
		
		Customer customer = repository.findById(id)
				.orElseThrow( () -> new EntityNotFoundException("Customer with id " + id + " not found in the database") );
		
		// Delete customer folder
		try {
			FolderManager.deleteFolder( customer.getCustomerFolder() );
		} catch ( IllegalArgumentException e ) {
			// Customer folder doesn't exist (This is weird, but doesn't block customer deleting)
			LOGGER.warn( e.getMessage() );
		}
		
		// Delete the constraints -------------------------------------------------------------------------
		
		orthopantomogramService.getOrthopantomogramsByCustomer(id).forEach( ortho -> {
			orthopantomogramService.deleteOrthopantomogram(ortho.getId());
		} );
		
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
		
		return repository.findByEMail(eMail)
				.orElseThrow( () -> new EntityNotFoundException("Customer with email " + eMail + " not found in the database") );
	}
	
}
