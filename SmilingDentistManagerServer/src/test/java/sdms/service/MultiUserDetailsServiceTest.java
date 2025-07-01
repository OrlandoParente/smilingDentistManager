package sdms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sdms.model.Customer;
import sdms.model.Employee;
import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;

// 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

class MultiUserDetailsServiceTest {

	@Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private MultiUserDetailsService userDetailsService;

	
	// enable mochito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

    @Test
    void testLoadUserByUsername_FoundInEmployees() {
        String email = "employee@email.com";
        Employee employee = new Employee();
        employee.seteMail(email);
        employee.setPassword("password");
        employee.setRole("EMPLOYEE");

        when(employeeRepository.findByEMail(email)).thenReturn(Optional.of(employee));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE")));
    }

    @Test
    void testLoadUserByUsername_FoundInCustomers() {
        String email = "customer@email.com";
        Customer customer = new Customer();
        customer.seteMail(email);
        customer.setPassword("pass123");
        customer.setRole("CUSTOMER");

        when(employeeRepository.findByEMail(email)).thenReturn(Optional.empty());
        when(customerRepository.findByEMail(email)).thenReturn(Optional.of(customer));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());
        assertEquals("pass123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_CUSTOMER")));
    }

    @Test
    void testLoadUserByUsername_NotFound() {
        String email = "unknown@email.com";

        when(employeeRepository.findByEMail(email)).thenReturn(Optional.empty());
        when(customerRepository.findByEMail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(email);
        });
    }
}
