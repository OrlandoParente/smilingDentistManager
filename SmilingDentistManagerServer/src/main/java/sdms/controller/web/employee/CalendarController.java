package sdms.controller.web.employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sdms.dto.join.AppointmentCustomerDoctorTreatmentDTO;
import sdms.model.Appointment;
import sdms.service.AppointmentService;

@Controller
@RequestMapping("/employee/calendar")
public class CalendarController {

	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping({"/calendar","","/"})
	public String mainCalendarPage( Model model ) {
		
		List<Appointment> appointments = appointmentService.getAppointments();
		
		List<AppointmentCustomerDoctorTreatmentDTO> joinAppointments = new ArrayList<AppointmentCustomerDoctorTreatmentDTO>();
		
		for( Appointment appointment : appointments ) {
			joinAppointments.add( 
					new AppointmentCustomerDoctorTreatmentDTO( )
					.buildFromAppointmentId(appointment.getId(), appointmentService, modelMapper) 
					);
		}
		
		// sort for time
		joinAppointments = joinAppointments.stream()
							.sorted( Comparator.comparing(  app -> app.getAppointmentDTO().getTime()  ) )
							.toList();
		
		// Pass to the model a list of appointments joined to Customer, Doctor and Treatment
		model.addAttribute("joinAppointment", joinAppointments );
		
		return("employee/calendar/view/day");
	}
	
	@GetMapping({"/calendar/day","/day"})
	public String dayCalendarPage( Model model ) {
		
//		model.addAllAttributes( "joinAppointments", );
		
		return("employee/calendar/view/day");
	}
}
