package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Employee;
import sdms.model.WorkPeriod;
import sdms.repository.WorkPeriodRepository;

@Service
public class WorkPeriodService implements WorkPeriodServiceInterface {

	@Autowired
	WorkPeriodRepository repository;
	
	// CREATE ---------------------------------------------------------------
	@Override
	public void postWorkPeriod(WorkPeriod workPeriod) {
		
		repository.save( workPeriod );
	}
	
	// READ -----------------------------------------------------------------
	@Override
	public WorkPeriod getWorkPeriodById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<WorkPeriod> getWorkPeriodsByEmployee(Employee employee) {
		
		return repository.findByEmployee(employee);
	}

	
	// UPDATE ---------------------------------------------------------------
	@Override
	public void putWorkPeriod(WorkPeriod workPeriod) {
		
		repository.save( workPeriod );
	}

		
	// DELETE ---------------------------------------------------------------
	@Override
	public void deleteWorkPeriodById(long id) {
		
		repository.delete( repository.findById(id).get() );
	}

}
