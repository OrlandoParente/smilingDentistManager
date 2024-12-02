package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Appointment;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;

@Service
public class AppointmentService implements AppointmentServiceInterface{

	@Autowired
	AppointmentRepository repository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Appointment getAppointmentById(long id) {
		
		return repository.findById(id).get();
		
	}
	
	@Override
	public List<Appointment> getAppointments() {
		
		return repository.findAll();
	}

	@Override
	public List<Appointment> getAppointmentsByCustomerId(long customerId) {
		
		return repository.findByCustomer( customerRepository.findById( customerId ).get() );
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorId(long doctorId) {
		
		return repository.findByDoctor(  employeeRepository.findById( doctorId ).get() );
	}

	@Override
	public void postAppointment(Appointment appointment) {
	
		repository.save( appointment );
	}

	@Override
	// set is_done = 1
	public void putSetAppointmentDoneById(long id) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setisDone( Appointment.IS_DONE );
		repository.save(appointment);
		
	}

	@Override
	// set is_done = 0
	public void putUnsetAppointmentDoneById(long id) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setisDone( Appointment.IS_NOT_DONE );
		repository.save(appointment);
		
	}
	
	@Override
	public void putAppointment(Appointment appointment) {
		
		repository.save(appointment);
	}

	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	@Override
	public void putAppointmentBillNumberById(long id, String billNumber) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setbillNumber(billNumber);
		repository.save(appointment);
		
	}

	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	@Override
	public void putAppointmentNoteById(long id, String note) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setNote(note);
		repository.save(appointment);
		
	}

	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	@Override
	public void putAppointmentTreatmentById(long id, long idTreatment) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setidTreatment(idTreatment);
		repository.save(appointment);
	}

	@Override
	public void deleteAppointmentById(long id) {
		
		repository.delete( repository.findById( id ).get() );
	}

	
}
