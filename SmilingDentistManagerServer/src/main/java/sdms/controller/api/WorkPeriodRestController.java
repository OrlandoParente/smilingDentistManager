package sdms.controller.api;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.dto.WorkPeriodDTO;
import sdms.model.Employee;
import sdms.model.WorkPeriod;
import sdms.service.EmployeeServiceInterface;
import sdms.service.WorkPeriodServiceInterface;
import sdms.util.DateAndTimeManager;

@RestController
public class WorkPeriodRestController {

	@Autowired
	private WorkPeriodServiceInterface service;
	
	@Autowired
	private EmployeeServiceInterface employeeService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private DateAndTimeManager dateAndTimeManager;
	
	private final Logger LOGGER = LoggerFactory.getLogger(  WorkPeriodRestController.class );
	
	// GET REQUEST -------------------------------------------------------------------------------------------------
	
	@GetMapping("/getWorkPeriodById/{id}")
	public ResponseEntity<?> getWorkPeriodById( @PathVariable ("id") long id ){
		
		WorkPeriod workPeriod = service.getWorkPeriodById(id);
		
		if( workPeriod == null ) return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		return ResponseEntity.status( HttpStatus.OK ).body( modelMapper.map(workPeriod, WorkPeriod.class) );
	}
	
	@GetMapping("/getWorkPeriodsByEmployeeId/{employeeId}")
	public ResponseEntity<?> getWorkPeriodsByEmployeeId( @PathVariable ("employeeId") long id ){
		
		Employee employee = employeeService.getEmployeeById(id);
		
		if( employee == null ) return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		List<WorkPeriodDTO> listWorkPeriodDTO = service.getWorkPeriodsByEmployee(employee).stream()
				.map( workPeriod -> modelMapper.map(workPeriod, WorkPeriodDTO.class ) ).toList();
		
		return ResponseEntity.status( HttpStatus.OK ).body( listWorkPeriodDTO );
	}
	
	@GetMapping("/getWorkPeriodsByEmployeeEMail/{eMail}")
	public ResponseEntity<?> getWorkPeriodsByEmployeeEMail( @PathVariable ("eMail") String eMail ){
		
		Employee employee = employeeService.getEmployeeByEMail(eMail);
		
		if( employee == null ) return ResponseEntity.status( HttpStatus.NOT_FOUND ).build();
		
		List<WorkPeriodDTO> listWorkPeriodDTO = service.getWorkPeriodsByEmployee(employee).stream()
				.map( workPeriod -> modelMapper.map(workPeriod, WorkPeriodDTO.class ) ).toList();
		
		return ResponseEntity.status( HttpStatus.OK ).body( listWorkPeriodDTO );
	}
	
	// -------------------------------------------------------------------------------------------------------------


	
	// POST REQUEST ------------------------------------------------------------------------------------------------
	
	@PostMapping( value = "/postWorkPeriod", params = {"idEmployee","startDate"} )
	public ResponseEntity<?> postWorkPeriod( @RequestParam long idEmployee, @RequestParam String startDate, 
												@RequestParam ( defaultValue = "" ) String endDate, 
												@RequestParam ( defaultValue = "" ) String workingAgreement, 
												@RequestParam ( defaultValue = "" ) String notes ) {
		
		// Searches for employee to associate the work period
		Employee employee = employeeService.getEmployeeById(idEmployee);
		
		if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
		
		
		LocalDate ldStartDate = null;
		LocalDate ldEndDate = null;
		
		try {
			// Convert date from string to LocalDate 
			ldStartDate = dateAndTimeManager.parseDate(startDate);
			
			if( ! endDate.equals("") ) ldEndDate = dateAndTimeManager.parseDate(endDate);
		} catch (/*DateTimeParseException */ Exception e ) {
			
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
		}
		
		
		// Creato entity to save
		WorkPeriod workPeriod = new WorkPeriod();
		
		workPeriod.setEmployee( employee );
		workPeriod.setStartDate( ldStartDate );
		
		if( ! endDate.equals("") )	workPeriod.setEndDate(ldEndDate);
		workPeriod.setWorkingAgreement(workingAgreement);
		workPeriod.setNotes(notes);
		
		service.postWorkPeriod(workPeriod);
		
		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
	}
	
//	
//	// Without end period date
//	@PostMapping( value = "/postWorkPeriod", params = {"idEmployee","startDate", "workingAgreement", "notes"} )
//	public ResponseEntity<?> postWorkPeriod( @RequestParam long idEmployee, @RequestParam String startDate, 
//											@RequestParam String workingAgreement, @RequestParam String notes ) {
//		
//		// Searches for employee to associate the work period
//		Employee employee = employeeService.getEmployeeById(idEmployee);
//		
//		if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
//		
//		
//		LocalDate ldStartDate = null;
//		
//		try {
//			// Convert date from string to LocalDate 
//			ldStartDate = dateAndTimeManager.parseDate(startDate);
//		} catch (/*DateTimeParseException */ Exception e ) {
//			
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
//		}
//		
//		
//		// Creato entity to save
//		WorkPeriod workPeriod = new WorkPeriod();
//		
//		workPeriod.setEmployee( employee );
//		workPeriod.setStartDate( ldStartDate );
//		workPeriod.setWorkingAgreement(workingAgreement);
//		workPeriod.setNotes(notes);
//		
//		service.postWorkPeriod(workPeriod);
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
//	}
//	
//	// with end period date
//	@PostMapping( value = "/postWorkPeriod", params = {"idEmployee","startDate", "endDate", "workingAgreement", "notes"} )
//	public ResponseEntity<?> postWorkPeriod( @RequestParam long idEmployee, @RequestParam String startDate, @RequestParam String endDate, 
//											@RequestParam String workingAgreement, @RequestParam String notes ) {
//		
//		// Searches for employee to associate the work period
//		Employee employee = employeeService.getEmployeeById(idEmployee);
//		
//		if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
//		
//		
//		LocalDate ldStartDate = null;
//		LocalDate ldEndDate = null;
//		
//		try {
//			// Convert date from string to LocalDate 
//			ldStartDate = dateAndTimeManager.parseDate(startDate);
//			ldEndDate = dateAndTimeManager.parseDate(endDate);
//		} catch (/*DateTimeParseException */ Exception e ) {
//			
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
//		}
//		
//		
//		// Creato entity to save
//		WorkPeriod workPeriod = new WorkPeriod();
//		
//		workPeriod.setEmployee( employee );
//		workPeriod.setStartDate( ldStartDate );
//		workPeriod.setEndDate(ldEndDate);
//		workPeriod.setWorkingAgreement(workingAgreement);
//		workPeriod.setNotes(notes);
//		
//		service.postWorkPeriod(workPeriod);
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
//	}
	
	// -------------------------------------------------------------------------------------------------------------


	// PUT REQUEST -------------------------------------------------------------------------------------------------

	@PutMapping( value = "/putWorkPeriod", params = {"id"} )
	public ResponseEntity<?> putWorkPeriod( @RequestParam long id, 
												@RequestParam( defaultValue = "-1" ) long idEmployee, 
												@RequestParam( defaultValue = "sdm-none-nessuna" ) String startDate, 
												@RequestParam( defaultValue = "sdm-none-nessuna" ) String endDate, 
												@RequestParam( defaultValue = "sdm-none-nessuna" ) String workingAgreement, 
												@RequestParam( defaultValue = "sdm-none-nessuna" ) String notes ) {
		
		// I use "sdm-none-nessuna" cause i need a string that the user don't use as imput 
		
		LOGGER.info("/putWorkPeriod -> params= id:" + id + ", idEmployee:" + idEmployee + ", startDate:" + startDate + ", endDate:" + endDate
						+ ", workingAgreement:" + workingAgreement + ", notes:" + notes);
		
		// Searches for workPeriod to update
		WorkPeriod workPeriod = service.getWorkPeriodById(id);
		if( workPeriod == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Work period to update not found");
		
		Employee employee = null;
		
		if( idEmployee != -1 ) {
			// Searches for employee to associate the work period
			employee = employeeService.getEmployeeById(idEmployee);
			if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
		}
		
		LocalDate ldStartDate = null;
		LocalDate ldEndDate = null;
		
		try {
			// Convert date from string to LocalDate 
			if( ! startDate.equals("del") &&  ! startDate.equals("") &&  ! startDate.equals("sdm-none-nessuna") ) 
				ldStartDate = dateAndTimeManager.parseDate(startDate);
			
			if( !endDate.equals("del") &&  !endDate.equals("")  && ! endDate.equals("sdm-none-nessuna") ) 
				ldEndDate = dateAndTimeManager.parseDate(endDate);
		} catch (/*DateTimeParseException */ Exception e ) {
			
			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
		}
		
		
		// Update entity field
		if( idEmployee != -1 )	workPeriod.setEmployee( employee );
		
		// if  string "del", set date to null <<------------ TO EDIT, in this case we should delete the whole work period ?
		if( startDate.equals("del") ) workPeriod.setStartDate(null);
		else if( ldStartDate != null )	workPeriod.setStartDate( ldStartDate );
		
		// if string "del", set date to null
		if( endDate.equals("del") )	workPeriod.setEndDate(null);
		else if( ldEndDate != null )	workPeriod.setEndDate(ldEndDate);
		
		if( ! workingAgreement.equals("sdm-none-nessuna") ) workPeriod.setWorkingAgreement(workingAgreement);
		if( ! notes.equals("sdm-none-nessuna") ) workPeriod.setNotes(notes);
		
		
		// check that startDate is before endDate ---------------------------
		
		if( workPeriod.getStartDate() != null && workPeriod.getEndDate() != null ) {
			if( workPeriod.getStartDate().isAfter( workPeriod.getEndDate() ) )
				return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Error: start date is after than end date" );
		}
		
		// ------------------------------------------------------------------
		
		// Put the work period
		service.putWorkPeriod(workPeriod);
		
		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
	}	
	
	
//	// Without end period date
//	@PutMapping( value = "/putWorkPeriod", params = {"id", "idEmployee","startDate", "workingAgreement", "notes"} )
//	public ResponseEntity<?> postWorkPeriod( @RequestParam long id, @RequestParam long idEmployee, @RequestParam String startDate, 
//											@RequestParam String workingAgreement, @RequestParam String notes ) {
//		
//		// Searches for workPeriod to update
//		WorkPeriod workPeriod = service.getWorkPeriodById(id);
//		if( workPeriod == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Work period to update not found");
//		
//		// Searches for employee to associate the work period
//		Employee employee = employeeService.getEmployeeById(idEmployee);
//		if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
//		
//		
//		LocalDate ldStartDate = null;
//		
//		try {
//			// Convert date from string to LocalDate 
//			ldStartDate = dateAndTimeManager.parseDate(startDate);
//		} catch (/*DateTimeParseException */ Exception e ) {
//			
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
//		}
//		
//		
//		// Update entity field
//		workPeriod.setEmployee( employee );
//		workPeriod.setStartDate( ldStartDate );
//		workPeriod.setWorkingAgreement(workingAgreement);
//		workPeriod.setNotes(notes);
//		
//		service.putWorkPeriod(workPeriod);
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
//	}
//	
//	// with end period date
//	@PutMapping( value = "/putWorkPeriod", params = {"id", "idEmployee","startDate", "endDate", "workingAgreement", "notes"} )
//	public ResponseEntity<?> postWorkPeriod( @RequestParam long id, @RequestParam long idEmployee, @RequestParam String startDate, 
//											 @RequestParam String endDate, @RequestParam String workingAgreement, @RequestParam String notes ) {
//		
//		// Searches for workPeriod to update
//		WorkPeriod workPeriod = service.getWorkPeriodById(id);
//		if( workPeriod == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Work period to update not found");
//		
//		// Searches for employee to associate the work period
//		Employee employee = employeeService.getEmployeeById(idEmployee);
//		if( employee == null )	return ResponseEntity.status( HttpStatus.NOT_FOUND ).body("Employee not found");
//		
//		
//		LocalDate ldStartDate = null;
//		LocalDate ldEndDate = null;
//		
//		try {
//			// Convert date from string to LocalDate 
//			ldStartDate = dateAndTimeManager.parseDate(startDate);
//			ldEndDate = dateAndTimeManager.parseDate(endDate);
//		} catch (/*DateTimeParseException */ Exception e ) {
//			
//			return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
//		}
//		
//		
//		// Update entity field
//		workPeriod.setEmployee( employee );
//		workPeriod.setStartDate( ldStartDate );
//		workPeriod.setEndDate(ldEndDate);
//		workPeriod.setWorkingAgreement(workingAgreement);
//		workPeriod.setNotes(notes);
//		
//		service.putWorkPeriod(workPeriod);
//		
//		return ResponseEntity.status( HttpStatus.OK ).body( workPeriod );
//	}	
	// -------------------------------------------------------------------------------------------------------------

	// DELETE REQUEST ----------------------------------------------------------------------------------------------
	
	@DeleteMapping( value = "/deleteWorkPeriodById", params = {"id"} )
	public ResponseEntity<?> deleteWorkPeriodById( @RequestParam long id ){
		
		try {
			service.deleteWorkPeriodById(id);
		} catch( Exception e ) {
			return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( e.getMessage() );
		}
		
		return ResponseEntity.status( HttpStatus.OK ).build();
	}
	
	// -------------------------------------------------------------------------------------------------------------

}
