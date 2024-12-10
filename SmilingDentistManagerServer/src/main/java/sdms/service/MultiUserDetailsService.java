package sdms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;

@Service
public class MultiUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return employeeRepository.findByEMail(username)
				// searches for the user among the employees
				.map( employee -> User.builder()
						.username( employee.geteMail() )
						.password( employee.getPassword() )
						.roles( employee.getRole() )
						.build() )
				// If the user is not an employee, it search among the customers
				.orElseGet( () -> customerRepository.findByEMail(username)
							.map( customer -> User.builder()
									.username( customer.geteMail() )
									.password( customer.getPassword() )
									.roles( customer.getRole() )
									.build() ) 
							.orElseThrow( 
									() -> new UsernameNotFoundException("User not found with username: " + username) 
							 ) 
				);

	}

}
