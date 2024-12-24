package sdms.controller.web.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.servlet.http.HttpServletRequest;
import sdms.dto.CustomerDTO;
import sdms.dto.EmployeeDTO;
import sdms.dto.TreatmentDTO;
import sdms.dto.join.AppointmentCustomerDoctorTreatmentDTO;
import sdms.model.Appointment;
import sdms.service.AppointmentService;
import sdms.service.CustomerServiceInterface;
import sdms.service.EmployeeServiceInterface;
import sdms.service.TreatmentServiceInterface;
import sdms.util.DateAndTimeManager;
import sdms.util.WebClientCookieManager;

@Controller
@RequestMapping("/employee/calendar")
public class CalendarController {

	// For now I don't use this in this class 
	 private static final Logger Logger = LoggerFactory.getLogger( CalendarController.class );
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	EmployeeServiceInterface employeeService;
	
	@Autowired
	CustomerServiceInterface customerService;
	
	@Autowired
	TreatmentServiceInterface treatmentService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DateAndTimeManager dateAndTimeManager;
	
	// CAN WE DELETE THIS METHOD? NOT USED
	@GetMapping({"/calendar","","/"})
	public void mainCalendarPage( Model model ) {
		
		
	}
	
	// Controller for day view
	@GetMapping({"/calendar/day","/day",
				"/calendar/day/{date}","/day/{date}",
				"/calendar/day/{date}/{nextPrevDays}","/day/{date}/{nextPrevDays}"})
	public String dayCalendarPage( HttpServletRequest request, Model model, 
									@PathVariable( required = false ) String date,
									@PathVariable( required = false ) Integer nextPrevDays ) {
		
		// Set useful cookies --------------------------------------------------------------------------
//		String name = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.NAME);
//		model.addAttribute(WebClientCookieManager.NAME, name);
		
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		// Set data for filter the appointment list ----------------------------------------------------
		LocalDate filterDate = LocalDate.now();
		
		try {
			// if date is null, set today date
			filterDate =  dateAndTimeManager.parseDate(date);
		} catch(Exception e) {
			
			if( date != null ) { // that's mean date is in an invalid format
				System.err.println( e.getMessage() );
				// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<==========================================================================
				// throw httpStatus.BAD_REQUEST
			}
		}
		// ---------------------------------------------------------------------------------------------
		
		// change data ---------------------------------------------------------------------------------
		if( nextPrevDays != null ) {
			if( nextPrevDays > 0 ) {	// Add days
				filterDate = filterDate.plusDays( nextPrevDays );
			} else if ( nextPrevDays < 0 ) {  // Remove days
				filterDate = filterDate.minusDays( Math.abs(nextPrevDays) );
			}
		}
		
		// check print
		Logger.info( "Date: " + date + "  filteDate: " + filterDate + " nexPrevDays: " + nextPrevDays );
		// ---------------------------------------------------------------------------------------------
		
		List<Appointment> appointments = appointmentService.getAppointments();
		
		List<AppointmentCustomerDoctorTreatmentDTO> joinAppointments = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
		
		for( Appointment appointment : appointments ) {
		
			// add only appointments for the filterDate 
			if( appointment.getDate().isEqual(filterDate) )
				joinAppointments.add( 
						new AppointmentCustomerDoctorTreatmentDTO( )
						.buildFromAppointmentId(appointment.getId(), appointmentService, modelMapper) 
						);
			// Logger.info( "Appointment id: " + appointment.getId() + " Appointment notes: " + appointment.getNotes() );
		}
		
		// sort by time
		joinAppointments = joinAppointments.stream()
							// .filter( app ->  app.getAppointmentDTO().getDate().isEqual(filterDate) ) // ERROR
							.sorted( Comparator.comparing(  app -> app.getAppointmentDTO().getTime()  ) )
							.toList();
		
		// Set attributes to the model -------------------------------------------------------------------------
		
		List<CustomerDTO> customers = customerService.getCustomers().stream()
				.map( customer ->  modelMapper.map(customer, CustomerDTO.class) )
				.toList();
		
		// NEED EDIT: We need a filter to filter doctors from employees
		List<EmployeeDTO> doctors = employeeService.getEmployees().stream()
				.map( employee -> modelMapper.map(employee, EmployeeDTO.class) )
				.toList();
		
		List<TreatmentDTO> treatments = treatmentService.getTreatments().stream()
				.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
				.toList();
		
		// Pass to the model a list of appointments joined to Customer, Doctor and Treatment
		model.addAttribute("joinAppointments", joinAppointments );
		// Pass date to which we refer
		model.addAttribute("date", filterDate);
		// Pass customers to the model
		model.addAttribute("customers", customers);
		// Pass doctors to the model
		model.addAttribute("doctors", doctors);
		// Pass Treatments to the model
		model.addAttribute("treatments",treatments);
		
		// -----------------------------------------------------------------------------------------------------
		
		return "employee/calendar/view/day";
	}
	
	// Controller for week view
	@GetMapping({"/calendar/week","/week",
				"/calendar/week/{date}","/week/{date}",
				"/calendar/week/{date}/{nextPrevWeeks}","/week/{date}/{nextPrevWeeks}"})
	public String weekCalendarPage( HttpServletRequest request, Model model, 
									@PathVariable( required = false ) String date,
									@PathVariable( required = false ) Integer nextPrevWeeks ) {
		
		// Set useful cookies --------------------------------------------------------------------------
//		String name = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.NAME);
//		model.addAttribute(WebClientCookieManager.NAME, name);
		
		WebClientCookieManager.setUsefulGlobalCookiesInTheModel(request, model);
		// ---------------------------------------------------------------------------------------------
		
		// Set data for filter the appointment list ----------------------------------------------------
		LocalDate filterDate = LocalDate.now();
		
		
		
		try {
			// if date is null, set today date
			filterDate =  dateAndTimeManager.parseDate(date);
		} catch(Exception e) {
			
			if( date != null ) { // that's mean date is in an invalid format
				System.err.println( e.getMessage() );
				// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<==========================================================================
				// throw httpStatus.BAD_REQUEST
			}
		}
		// ---------------------------------------------------------------------------------------------
		
		// change data ---------------------------------------------------------------------------------
		if( nextPrevWeeks != null ) {
			if( nextPrevWeeks > 0 ) {	// Add days
				filterDate = filterDate.plusWeeks( nextPrevWeeks );
			} else if ( nextPrevWeeks < 0 ) {  // Remove days
				filterDate = filterDate.minusWeeks( Math.abs(nextPrevWeeks) );
			}
		}
		
		// check print
		Logger.info( "Date: " + date + "  filteDate: " + filterDate + " nexPrevWeeks: " + nextPrevWeeks );
		// ---------------------------------------------------------------------------------------------
		
		List<Appointment> appointments = appointmentService.getAppointments();
		
		List<AppointmentCustomerDoctorTreatmentDTO> joinAppointments = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
		
		// get first and last date to filter
		LocalDate monday = filterDate.with( TemporalAdjusters.previousOrSame( DayOfWeek.MONDAY ) ); 
		LocalDate sunday = filterDate.with( TemporalAdjusters.nextOrSame( DayOfWeek.SUNDAY ) );
		
		Logger.info("MONDAY: " + monday + " SUNDAY: " + sunday);
		
		for( Appointment appointment : appointments ) {
		
			// add only appointments that are in the week of the filterDate 
			if( ( appointment.getDate().isAfter(monday) || appointment.getDate().isEqual(monday) ) &&
			    ( appointment.getDate().isBefore(sunday) || appointment.getDate().isEqual(sunday) ) )
				
				joinAppointments.add( 
						new AppointmentCustomerDoctorTreatmentDTO( )
						.buildFromAppointmentId(appointment.getId(), appointmentService, modelMapper) 
						);
			// Logger.info( "Appointment id: " + appointment.getId() + " Appointment notes: " + appointment.getNotes() );
		}
		
		
		// sort by time
		joinAppointments = joinAppointments.stream()
							// .filter( app ->  app.getAppointmentDTO().getDate().isEqual(filterDate) ) // ERROR
							.sorted( Comparator.comparing(  app -> app.getAppointmentDTO().getTime()  ) )
							.toList();

		// Build a map <time, appointmens> for build the calendar in the week view -------------------------------------------------------------------------------------
		
		// I use treeMap instead of hashMap cause I need the keys are sorted by time 
		Map<LocalTime, List< AppointmentCustomerDoctorTreatmentDTO > > mapByTimeJoinAppointments = new TreeMap<LocalTime, List<AppointmentCustomerDoctorTreatmentDTO>>();

		
		if( joinAppointments.size() > 0 ) {
						
			// generate key/value of the first entry of the map
			List<AppointmentCustomerDoctorTreatmentDTO> tmpJoinAppointments = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
	//		tmpJoinAppointments.add( joinAppointments.get(0) );
			LocalTime time = joinAppointments.get(0).getAppointmentDTO().getTime();
			
			mapByTimeJoinAppointments.put(time, tmpJoinAppointments);
			
			Logger.info( "first time (key) in the mapByTimeJoinAppointments: " + time );
			
			for( AppointmentCustomerDoctorTreatmentDTO joinAppointment : joinAppointments ) {
				
				if( joinAppointment.getAppointmentDTO().getTime().equals( time ) ) {
					mapByTimeJoinAppointments.get(time).add(joinAppointment);
				} else {
					// generate the next key of the map
					time = joinAppointment.getAppointmentDTO().getTime();
					
					tmpJoinAppointments = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
					tmpJoinAppointments.add(joinAppointment);
					
					
					mapByTimeJoinAppointments.put(time, tmpJoinAppointments);
				}
				
			}
			Logger.info( "first time (key) in the mapByTimeJoinAppointments after the loop: " + time );
		}
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		// Set attributes to the model -------------------------------------------------------------------------
		
		List<CustomerDTO> customers = customerService.getCustomers().stream()
				.map( customer ->  modelMapper.map(customer, CustomerDTO.class) )
				.toList();
		
		// NEED EDIT: We need a filter to filter doctors from employees
		List<EmployeeDTO> doctors = employeeService.getEmployees().stream()
				.map( employee -> modelMapper.map(employee, EmployeeDTO.class) )
				.toList();
		
		List<TreatmentDTO> treatments = treatmentService.getTreatments().stream()
				.map( treatment -> modelMapper.map(treatment, TreatmentDTO.class) )
				.toList();
		
		LocalDate[] datesOfWeek = { monday, monday.plusDays(1), monday.plusDays(2), monday.plusDays(3), monday.plusDays(4), monday.plusDays(5), monday.plusDays(6) };
		
		// Pass to the model a list of appointments joined to Customer, Doctor and Treatment
		// model.addAttribute("joinAppointments", joinAppointments );	// Not useful in week view
		// Pass to the model the list of appointments joined to Customer, Doctor and Treatment mapped by time
		model.addAttribute("mapByTimeJoinAppointments", mapByTimeJoinAppointments);
		// Pass to dates of the week
		model.addAttribute("datesOfWeek", datesOfWeek);
		// Pass date to which we refer
		model.addAttribute("date", filterDate);
		// Pass customers to the model
		model.addAttribute("customers", customers);
		// Pass doctors to the model
		model.addAttribute("doctors", doctors);
		// Pass Treatments to the model
		model.addAttribute("treatments",treatments);
		
		// -----------------------------------------------------------------------------------------------------
		
		return "employee/calendar/view/week";
	}
}
