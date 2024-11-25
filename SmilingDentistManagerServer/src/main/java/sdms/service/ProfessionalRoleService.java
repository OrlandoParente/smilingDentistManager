package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Employee;
import sdms.model.HasProfessionalRole;
import sdms.model.ProfessionalRole;
import sdms.repository.EmployeeRepository;
import sdms.repository.HasProfessionalRoleRepository;
import sdms.repository.ProfessionalRoleRepository;

@Service
public class ProfessionalRoleService implements ProfessionalRoleServiceInterface {

	@Autowired
	ProfessionalRoleRepository repository;
	
	@Autowired
	HasProfessionalRoleRepository hasProfessionalRoleRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public ProfessionalRole getProfessionalRoleById(long id) {

		return repository.findById(id).get();
	}

	@Override
	public List<ProfessionalRole> getProfessionalRoles() {
		
		return repository.findAll();
	}

	@Override
	public List<ProfessionalRole> getProfessionalRolesAssociatedToIdEmployee(long idEmployee) {
		
		Employee employee = employeeRepository.findById(idEmployee).get();
		List<HasProfessionalRole> listHasProfessionalRole = hasProfessionalRoleRepository.findByEmployee(employee);
		
		return listHasProfessionalRole.stream().map( hpr -> hpr.getProfessionalRole() ).toList();
	}

	@Override
	public void postProfessionalRole(ProfessionalRole professionalRole) {
		
		repository.save(professionalRole);
	}

	@Override
	public void putProfessionalRole(ProfessionalRole professionalRole) {
		
		repository.save(professionalRole);
	}

	@Override
	public void deleteProfessionalRoleById(Long id) {
		
		repository.delete( repository.findById(id).get() );
	}
	
	

}
