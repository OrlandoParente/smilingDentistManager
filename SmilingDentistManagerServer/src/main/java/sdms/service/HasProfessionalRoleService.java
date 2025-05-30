package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;
import sdms.repository.EmployeeRepository;
import sdms.repository.HasProfessionalRoleRepository;
import sdms.repository.ProfessionalRoleRepository;

@Service
public class HasProfessionalRoleService implements HasProfessionalRoleServiceInterface {

	@Autowired
	HasProfessionalRoleRepository repository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ProfessionalRoleRepository professionalRoleRepository;
	
	@Override
	public void postLinkEmployeeToProfessionalRole(HasProfessionalRole hasProfessionalRole) {
		
		repository.save(hasProfessionalRole);
	}

	@Override
	public void deleteLinkEmployeeWithProfessionalRole(long idEmployee, long idProfessionalRole) {
		
		Employee employee = employeeRepository.findById(idEmployee)
				.orElseThrow( () -> new EntityNotFoundException("Employee not found for id=" + idEmployee ) );

		ProfessionalRole professionalRole = professionalRoleRepository.findById(idProfessionalRole)
				.orElseThrow( () -> new EntityNotFoundException("Professional role not found for id=" + idEmployee ) );
		
		List<HasProfessionalRole> listHasProfessionalRole = repository.findByProfessionalRole(professionalRole);
		
		HasProfessionalRole hasProfessionalRole = listHasProfessionalRole.stream()
													.filter( hpr -> hpr.getEmployee().getId() == employee.getId() )
													.findFirst().orElse(null);
		
		repository.delete(hasProfessionalRole);
	}

	@Override
	public void deleteLinkEmployeeWithProfessionalRole(long id) {
		
		HasProfessionalRole hasProfessionalRole = repository.findById(id).get();
		repository.delete(hasProfessionalRole);
	}

	@Override
	public List<ProfessionalRole> getProfessionalRolesByEmployee(long idEmployee) {
		
		List<HasProfessionalRole> hasProfessionalRoles = repository.findByEmployee( employeeRepository.findById(idEmployee).get() );
		
		List<ProfessionalRole> professionalRoles = hasProfessionalRoles.stream().map( hpr ->  hpr.getProfessionalRole() ).toList();
		
		return professionalRoles;
	}

	@Override
	public List<Employee> getEmployeesByProfessionalRole(long idProfessionalRole) {
		
		List<HasProfessionalRole> hasProfessionalRoles = repository.findByProfessionalRole( professionalRoleRepository.findById( idProfessionalRole ).get() );
		
		List<Employee> employees = hasProfessionalRoles.stream().map( hpr ->  hpr.getEmployee()  ).toList(); 
		
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProfessionalRoleName(String professionalRoleName) {
		
		List<HasProfessionalRole> hasProfessionalRoles = repository.findByProfessionalRole( professionalRoleRepository.findByName( professionalRoleName ).get(0) );
		
		List<Employee> employees = hasProfessionalRoles.stream().map( hpr ->  hpr.getEmployee()  ).toList(); 
		
		return employees;

	}




}
