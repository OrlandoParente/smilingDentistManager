package sdms.dto.join;


import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import sdms.dto.AppointmentDTO;
import sdms.dto.CustomerDTO;
import sdms.dto.EmployeeDTO;
import sdms.dto.TreatmentDTO;
import sdms.model.Appointment;
import sdms.service.AppointmentServiceInterface;

@Component
public class AppointmentCustomerDoctorTreatmentDTO {
	
	private AppointmentDTO appointmentDTO;
	private CustomerDTO customerDTO;
	private EmployeeDTO doctorDTO;
	private TreatmentDTO treatmentDTO;
	
	private static final Logger LOGGER = LoggerFactory.getLogger( AppointmentCustomerDoctorTreatmentDTO.class );
//	@Autowired
//	ModelMapper modelMapper;
//	
//	@Autowired
//	private AppointmentServiceInterface appointmentService;
	
	// Empty constructor
	public AppointmentCustomerDoctorTreatmentDTO() {
		// for avoid error in thymeleaf
		this.appointmentDTO = new AppointmentDTO();
		this.customerDTO = new CustomerDTO();
		this.doctorDTO = new EmployeeDTO();
		this.treatmentDTO = new TreatmentDTO();
	}

//	// build from idAppointment 
//	public AppointmentCustomerDoctorTreatmentDTO( long idAppointment ) {
//		this.buildFromAppointmentId(idAppointment);
//	}
	
	
	public AppointmentCustomerDoctorTreatmentDTO buildFromAppointmentId( long idAppointment, AppointmentServiceInterface appointmentService, ModelMapper modelMapper ) {
		
		LOGGER.info("build AppointmentCustomerDoctorTreatmentDTO from AppointmentId");
		
		Appointment appointment = appointmentService.getAppointmentById(idAppointment);
		
		this.setAppointmentDTO( modelMapper.map( appointment, AppointmentDTO.class ) );
		
		// force the call of setTeeth for let it set also the integer list of teeth
		this.appointmentDTO.setTeeth( appointment.getTeeth() );
		LOGGER.info( "Set Appointment -> " + appointment );
		
		if( appointment.getCustomer() != null )
			this.setCustomerDTO( modelMapper.map( appointment.getCustomer(), CustomerDTO.class ) );
		
		if( appointment.getDoctor() != null )
			this.setDoctorDTO( modelMapper.map( appointment.getDoctor(), EmployeeDTO.class ) );
		
		if( appointment.getTreatment() != null )
			this.setTreatmentDTO( modelMapper.map( appointment.getTreatment(), TreatmentDTO.class ) );
	
		return this;
	}
	
	public AppointmentCustomerDoctorTreatmentDTO buildWithEmptyAppointment( LocalDate date ) {
		
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setDate(date);
		
		this.setAppointmentDTO(appointmentDTO);
		
		return this;
	}
	
	// GETTERS AND SETTERS
	
	public AppointmentDTO getAppointmentDTO() {
		return appointmentDTO;
	}

	public void setAppointmentDTO(AppointmentDTO appointmentDTO) {
		this.appointmentDTO = appointmentDTO;
	}

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public EmployeeDTO getDoctorDTO() {
		return doctorDTO;
	}

	public void setDoctorDTO(EmployeeDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}

	public TreatmentDTO getTreatmentDTO() {
		return treatmentDTO;
	}

	public void setTreatmentDTO(TreatmentDTO treatmentDTO) {
		this.treatmentDTO = treatmentDTO;
	}
		
}
