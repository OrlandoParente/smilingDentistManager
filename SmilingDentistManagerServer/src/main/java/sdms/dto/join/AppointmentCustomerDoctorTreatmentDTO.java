package sdms.dto.join;

import org.modelmapper.ModelMapper;
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
		
		Appointment appointment = appointmentService.getAppointmentById(idAppointment);
		
		this.setAppointmentDTO( modelMapper.map( appointment, AppointmentDTO.class ) );
		
		if( appointment.getCustomer() != null )
			this.setCustomerDTO( modelMapper.map( appointment.getCustomer(), CustomerDTO.class ) );
		
		if( appointment.getDoctor() != null )
			this.setDoctorDTO( modelMapper.map( appointment.getDoctor(), EmployeeDTO.class ) );
		
		if( appointment.getTreatment() != null )
			this.setTreatmentDTO( modelMapper.map( appointment.getTreatment(), TreatmentDTO.class ) );
	
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
