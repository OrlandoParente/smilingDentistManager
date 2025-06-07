package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.Customer;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.HasMedicalHistoryRepository;

/*
 * 	public long getLastCustomerId();
	
	public Customer getCustomerById( long id );
	public Customer getCustomerByEMail( String eMail );
	
	public List<Customer> getCustomers();
	public List<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word );
	


//	// inserimento dati essenziali del cliente
//	boolean postCustomer( String name , String surname ,String phone_number ) throws SQLException;
//	
//	boolean postCustomer( String tax_id_code , String name , String surname , String birth_city , 
//			String birth_city_province, String birth_date, String residence_street, String house_number, 
//			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
//			String phone_number_2, String e_mail )
//		    throws SQLException;4+
	public void postCustomer( Customer customer );
//	
//	boolean putCustomerById( String id, String tax_id_code , String name , String surname , String birth_city , 
//			String birth_city_province, String birth_date, String residence_street, String house_number, 
//			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
//			String phone_number_2, String e_mail )
//		    throws SQLException;
//	
	public void putCustomer( Customer customer );

	public void deleteCustomer( long id );
	
 */

class CustomerServiceTest {
	
		@Mock
		private CustomerRepository customerRepository;
		
		@Mock
		private OrthopantomogramServiceInterface orthopantomogramService;
	
		@Mock
		private HasMedicalHistoryRepository hasMedicalHistoryRepository;
		
		@Mock
		private ExpenseRepository expenseRepository;
		
		@Mock
		private AppointmentRepository appointmentRepository;
		
		@InjectMocks
		private CustomerService customerService;
	
		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
		}
	
//		public long getLastCustomerId();
		
		@Test
	  	public void testGetLastCustomerId() {
			
			Customer customer = new Customer();
			Long id = 1L;
			customer.setId(id);
			
			// Create a MOCK without annotations ( but doesn't works )
//			CustomerRepository repository = mock(CustomerRepository.class);
			
			// anyLong()
			when( customerRepository.findById( id ) ).thenReturn( Optional.of(customer) ) ;
			
			Customer result = customerService.getCustomerById( id );
			
			assertEquals( id , result.getId() );
			
	  	}
		
//		public Customer getCustomerByEMail( String eMail );
		
		@Test
		public void testGetCustomerByEMail( ) {
			
			Customer customer = new Customer();
			Long id = 1L;
			String eMail = "test@email.com";
			
			customer.setId(id);
			customer.seteMail(eMail);
			
			when( customerRepository.findByEMail( eMail ) ).thenReturn( Optional.of(customer) );
			
			Customer result = customerService.getCustomerByEMail(eMail);
			
			assertEquals( id , result.getId() );			
		}
		
		// ######################################################################################################################### 
//		public List<Customer> getCustomers();
		
//		@Test
//		public void testGetCustomers(){
//			
//			// Build list of customer for simulate the database ----
//			List<Customer> customers = new ArrayList<Customer>();
//			
//			Long id1 = 1L;
//			Long id2 = 2L;
//			
//			Customer customer1 = new Customer();
//			customer1.setId( id1 );
//			
//			Customer customer2 = new Customer();
//			customer2.setId(id2);
//			
//			// 
//			customers.add(customer1);
//			customers.add(customer2);
//			// -----------------------------------------------------
//			
//			when( repository.findAll() ).thenReturn(customers);
//			
//			List<Customer> result = customerService.getCustomers();
//			
//			// check not null
//			assertNotNull(result);
//			
//			// check size
//			assertEquals( customers.size(), result.size());
//			
//		    // check list
//		    for (int i = 0; i < customers.size(); i++) {
//		        assertEquals(customers.get(i).getId(), result.get(i).getId());
//		    }
//			
//		}
		
		// ######################################################################################################################### 
		
//		public List<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word );
		
		@Test
		public void testGetCustomersByPartialKeyWordOverAllFields() {
			
			// Simulate the database ----------------------------
			
			
			Customer customer1 = new Customer();
			Long id1 = 1L;
			String name1 = "Customer";
			customer1.setName(name1);
			customer1.setId(id1);
			
			Customer customer2 = new Customer();
			Long id2 = 2L;
			String name2 = "Customer2";
			customer2.setId(id2);
			customer2.setName(name2);
			
			
			String partialWord = "Cust";
			
			List<Customer> customers = new ArrayList<>();
			customers.add(customer1);
			customers.add(customer2);
			
			// We have 2 method for do the same thing (the service can choose the one who prefer)
			when( customerRepository.findCustomersByPartialKeyWordOverAllFields( partialWord ) ).thenReturn( customers );
			
			// We have 2 method for do the same thing (the service can choose the one who prefer)
			when( customerRepository.findCustomerByTaxIdCodeContainingOrNameContainingOrSurnameContainingOrBirthCityContainingOrBirthCityProvinceContainingOrBirthDateStringContainingOrResidenceStreetContainingOrHouseNumberContainingOrResidenceCityContainingOrResidenceCityCapContainingOrResidenceProvinceContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord, partialWord) )
						.thenReturn( customers );
			
			// --------------------------------------------------
			
			// test ---------------------------------------------
			List<Customer> result = customerService.getCustomersByPartialKeyWordOverAllFields(partialWord);
			// --------------------------------------------------
			
			// check --------------------------------------------
			assertNotNull(result);
			
			assertEquals(customers.size(), result.size());
			
			for( int i = 0; i < customers.size(); i ++ )
				assertEquals(customers.get(i).getId(), result.get(i).getId() );
			// --------------------------------------------------
			
			
		}
		
//		public void postCustomer( Customer customer );

		@Test
		public void testPostCustomer() {
			
			// Simulate the database ----------------------------
			Customer customer = new Customer();
			Long id = 1L;
			String name = "Customer";
			customer.setName(name);
			customer.setId(id);
			// --------------------------------------------------
			
			// test ---------------------------------------------
			customerService.postCustomer(customer);
			// --------------------------------------------------
			
			// check --------------------------------------------
			verify( customerRepository, times(1) ).save( customer );
			// --------------------------------------------------
			
		}
		
//		public void putCustomer( Customer customer );
		
		@Test
		public void testPutCustomer() {
			
			
			// Simulate the database ----------------------------
			
			// common values old and new customer object --
			Long id = 1L;
			String customerFolderPath = "/path/customer";
			// --------------------------------------------
			
			Customer customer = new Customer();
			String name = "Customer";
			customer.setName(name);
			customer.setId(id);
			customer.setCustomerFolder( customerFolderPath );
			
			
			Customer oldCustomer = new Customer();
			// Long id = 1L;
			String nameOldCustomer = "CustomerOld";
			oldCustomer.setName(nameOldCustomer);
			oldCustomer.setId(id);
			oldCustomer.setCustomerFolder( customerFolderPath );
			
			when( customerRepository.findById(id) ).thenReturn( Optional.of( oldCustomer ) );
			// --------------------------------------------------
			
			// test ---------------------------------------------
			customerService.putCustomer(customer);
			// --------------------------------------------------
			
			// check --------------------------------------------
			verify( customerRepository, times(1) ).findById(id);
			verify( customerRepository, times(1) ).save( customer );
			
			// Oss.: verify can be used only on Mocks, customer is not a mock in this case 
			// verify( customer, times(1) ).setCustomerFolder( anyString() );
			// --------------------------------------------------
			
		}

//		public void deleteCustomer( long id );
		
		@Test
		public void testDeleteCustome() {
		
			// Simulate the database ----------------------------
			Customer customer = new Customer();
			Long id = 1L;
			String name = "Customer";
			String customerFolderPath = "/path/customer";
			customer.setName(name);
			customer.setId(id);
			customer.setCustomerFolder(customerFolderPath);
			
			when( customerRepository.findById(id) ).thenReturn( Optional.of( customer ) );
			when( orthopantomogramService.getOrthopantomogramsByCustomer(id)).thenReturn(Collections.emptyList() );
			when( hasMedicalHistoryRepository.findByCustomer(customer) ).thenReturn(Collections.emptyList() );
			when( expenseRepository.findByCustomer(customer) ).thenReturn(Collections.emptyList() );
			when( appointmentRepository.findByCustomer(customer) ).thenReturn(Collections.emptyList() );
			// --------------------------------------------------
			
			// test ---------------------------------------------
			customerService.deleteCustomer(id);
			// --------------------------------------------------
			
			// check --------------------------------------------
			verify( customerRepository, times(1) ).delete( customer );
			// --------------------------------------------------
			
		}
}
