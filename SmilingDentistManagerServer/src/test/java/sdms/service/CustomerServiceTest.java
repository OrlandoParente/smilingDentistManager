package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.Customer;
import sdms.repository.CustomerRepository;

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
		private CustomerRepository repository;
	
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
			when( repository.findById( id ) ).thenReturn( Optional.of(customer) ) ;
			
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
			
			when( repository.findByEMail( eMail ) ).thenReturn( Optional.of(customer) );
			
			Customer result = customerService.getCustomerByEMail(eMail);
			
			assertEquals( id , result.getId() );			
		}
		
//		public List<Customer> getCustomers();
		
		@Test
		public void testGetCustomers(){
			
			// Build list of customer for simulate the database ----
			List<Customer> customers = new ArrayList<Customer>();
			
			Long id1 = 1L;
			Long id2 = 2L;
			
			Customer customer1 = new Customer();
			customer1.setId( id1 );
			
			Customer customer2 = new Customer();
			customer2.setId(id2);
			
			// 
			customers.add(customer1);
			customers.add(customer2);
			// -----------------------------------------------------
			
			when( repository.findAll() ).thenReturn(customers);
			
			List<Customer> result = customerService.getCustomers();
			
			// check not null
			assertNotNull(result);
			
			// check size
			assertEquals( customers.size(), result.size());
			
		    // check list
		    for (int i = 0; i < customers.size(); i++) {
		        assertEquals(customers.get(i).getId(), result.get(i).getId());
		    }
			
		}
		
//		public List<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word );
//		public void postCustomer( Customer customer );

//		public void putCustomer( Customer customer );

//		public void deleteCustomer( long id );
}
