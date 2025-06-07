package sdms.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sdms.model.WorkPeriod;
import sdms.repository.WorkPeriodRepository;


/*
 * 	// CREATE ---------------------------------------------------------------
	public void postWorkPeriod( WorkPeriod workPeriod );
	
	// READ -----------------------------------------------------------------
	WorkPeriod getWorkPeriodById( long id );
	List<WorkPeriod> getWorkPeriodsByEmployee(Employee employee);
	
	// UPDATE ---------------------------------------------------------------
	public void putWorkPeriod( WorkPeriod workPeriod );
		
	// DELETE ---------------------------------------------------------------
	public void deleteWorkPeriodById( long id );
 */

class WorkPeriodServiceTest {

	@Mock
	private WorkPeriodRepository workPeriodRepository;
	
	@InjectMocks
	private WorkPeriodService workPeriodService;
	
	// enable mockito annotations
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	// CREATE ---------------------------------------------------------------
	// public void postWorkPeriod( WorkPeriod workPeriod );
	
	@Test
	public void testPostWorkPeriod() {
		
		// simulate database ----------------------------------
		WorkPeriod workPeriod = new WorkPeriod();
		Long id = 1L;
		workPeriod.setId( id );
		
		// ----------------------------------------------------
		
		// test -----------------------------------------------
		workPeriodService.postWorkPeriod(workPeriod);
		// ----------------------------------------------------
		
		// check ----------------------------------------------
		verify( workPeriodRepository, times(1) ).save( workPeriod );
		// ----------------------------------------------------
		
	}
	
	// READ -----------------------------------------------------------------
	// WorkPeriod getWorkPeriodById( long id );
	// List<WorkPeriod> getWorkPeriodsByEmployee(Employee employee);
	
	public void testGetWorkPeriodsByEmployee() {
		
		// simulate database ----------------------------------
		WorkPeriod workPeriod = new WorkPeriod();
		Long id = 1L;
		workPeriod.setId( id );
		
		// ----------------------------------------------------
		
		// test -----------------------------------------------
		workPeriodService.postWorkPeriod(workPeriod);
		// ----------------------------------------------------
		
		// check ----------------------------------------------
		verify( workPeriodRepository, times(1) ).save( workPeriod );
		// ----------------------------------------------------
		
		
	}
	
	// UPDATE ---------------------------------------------------------------
	// public void putWorkPeriod( WorkPeriod workPeriod );
	
	@Test
	public void testPutWorkPeriod() {
		
		// simulate database ----------------------------------
		WorkPeriod workPeriod = new WorkPeriod();
		Long id = 1L;
		workPeriod.setId( id );
		
		// ----------------------------------------------------
		
		// test -----------------------------------------------
		workPeriodService.putWorkPeriod(workPeriod);
		// ----------------------------------------------------
		
		// check ----------------------------------------------
		verify( workPeriodRepository, times(1) ).save( workPeriod );
		// ----------------------------------------------------
	}
	
	// DELETE ---------------------------------------------------------------
	// public void deleteWorkPeriodById( long id );
	
	@Test
	public void testDeleteWorkPeriodById() {
		
		// simulate database ----------------------------------
		WorkPeriod workPeriod = new WorkPeriod();
		Long id = 1L;
		workPeriod.setId( id );
		
		when( workPeriodRepository.findById(id) ).thenReturn( Optional.of( workPeriod ) );
		// ----------------------------------------------------
		
		// test -----------------------------------------------
		workPeriodService.deleteWorkPeriodById( id );
		// ----------------------------------------------------
		
		// check ----------------------------------------------
		verify( workPeriodRepository, times(1) ).delete(workPeriod);
		// ----------------------------------------------------
	}

}
