package sdms.controller.web.employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	// private static final Logger Logger = LoggerFactory.getLogger( CalendarController.class );
	
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
	
	// CAN WE DELETE THIS? NOT USED
	@GetMapping({"/calendar","","/"})
	public void mainCalendarPage( Model model ) {
		
		
	}
	
	@GetMapping({"/calendar/day","/day","/calendar/day/{date}","/day/{date}"})
	public String dayCalendarPage( HttpServletRequest request, Model model, @PathVariable( required = false ) String date ) {
		
		// Set useful cookies --------------------------------------------------------------------------
		String name = WebClientCookieManager.getCookieValue(request, WebClientCookieManager.NAME);
		model.addAttribute(WebClientCookieManager.NAME, name);
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
		
		return("employee/calendar/view/day");
	}
}
