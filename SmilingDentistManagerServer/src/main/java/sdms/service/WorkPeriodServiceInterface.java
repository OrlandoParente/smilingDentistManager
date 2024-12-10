package sdms.service;

import java.util.List;

import sdms.model.Employee;
import sdms.model.WorkPeriod;

public interface WorkPeriodServiceInterface {

	// CREATE ---------------------------------------------------------------
	public void postWorkPeriod( WorkPeriod workPeriod );
	
	// READ -----------------------------------------------------------------
	WorkPeriod getWorkPeriodById( long id );
	List<WorkPeriod> getWorkPeriodsByEmployee(Employee employee);
	
	// UPDATE ---------------------------------------------------------------
	public void putWorkPeriod( WorkPeriod workPeriod );
		
	// DELETE ---------------------------------------------------------------
	public void deleteWorkPeriodById( long id );
	
}
