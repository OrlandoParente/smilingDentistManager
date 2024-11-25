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
		
		// Oss.:
		// Si potrebbero lanciare delle eccezioni in caso non trova oggetti
		// E far gestire al controller il NullPointException
		// Facendoli ritornare errore con il ResponseEntity
		
		Employee employee = employeeRepository.findById(idEmployee).get();
		ProfessionalRole professionalRole = professionalRoleRepository.findById(idProfessionalRole).get();
		
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


	
//	// Gestione Has Professional Role -----------------------
//	boolean postLinkEmployeeToProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
//	
//	boolean deleteLinkEmployeeWithProfessionalRole( String id_employee, String id_professional_role ) throws SQLException;
//	// ------------------------------------------------------
//	

}
