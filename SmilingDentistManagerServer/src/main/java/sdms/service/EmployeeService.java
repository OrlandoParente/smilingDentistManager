package sdms.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;
import sdms.repository.AppointmentRepository;
import sdms.repository.EmployeeRepository;
import sdms.repository.ExpenseRepository;
import sdms.repository.HasProfessionalRoleRepository;
import sdms.repository.ProfessionalRoleRepository;
import sdms.repository.WorkPeriodRepository;
import sdms.util.DateAndTimeManager;


@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	ProfessionalRoleRepository professionalRoleRepository;
	
	@Autowired
	HasProfessionalRoleRepository hasProfessionalRoleRepository;
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Autowired
	WorkPeriodRepository workPeriodRepository;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;
	
	// We need this for keep the compatibility with the Swing Client
	@Override
	public long getLastCustomerId() {
		
		return repository.findMaxId().longValue();
	}
	
	@Override
	public List<Employee> getEmployees() {
		
		return repository.findAll();
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
		
		return repository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeesBySurname(String surname) {
		
		return repository.findBySurname(surname);
	}

	@Override
	public List<Employee> getEmployeesByProfessionalRoleName(String professionalRoleName) {
		
		// Even if Name is not a primary key (but it could be)
		// I guess there is only one professional role associated to each professional role name
		ProfessionalRole professionalRole = professionalRoleRepository.findByName(professionalRoleName).get(0);
		
		List<HasProfessionalRole> listHasProfessionalRole = hasProfessionalRoleRepository.findByProfessionalRole(professionalRole);
		
		return listHasProfessionalRole.stream().map( hpr -> hpr.getEmployee() ).toList();
	}

	@Override
	public List<Employee> getEmployeesByPartialKeyWordOverAllFields(String keyWord) {
			
		return repository.findByNameContainingOrSurnameContainingOrBirthDateStringContainingOrPhoneNumberContainingOrPhoneNumber2ContainingOrEMailContaining(
				keyWord, keyWord, keyWord, keyWord, keyWord, keyWord);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public void postEmployee(Employee employee) {
		
		repository.save(employee);
	}

	@Override
	public void putEmployee(Employee employee) {
		
		repository.save(employee);		
	}

	@Override
	public void deleteEmployeeById(Long id) {
		
		Employee employee = repository.findById(id).get();
		
		// Delete the constraints -------------------------------------------------------------------------
		
		hasProfessionalRoleRepository.findByEmployee( employee ).forEach( hasProfessionalRole ->  { 
			hasProfessionalRoleRepository.delete( hasProfessionalRole ); 
		});
		
		expenseRepository.findByEmployee(employee).forEach( expense -> {
			expenseRepository.delete(expense);
		});
		
		appointmentRepository.findByDoctor(employee).forEach( appointment -> {
			appointmentRepository.delete(appointment);
		});
		
		workPeriodRepository.findByEmployee(employee).forEach(  workPeriod -> {
			workPeriodRepository.delete(workPeriod);
		} );
		
		// ------------------------------------------------------------------------------------------------
		
		repository.delete( employee );
	}

	@Override
	public Employee getEmployeeByEMail(String eMail) {
		
		return repository.findByEMail( eMail ).get();
	}


	
}
