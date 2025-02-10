package sdms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import sdms.model.Appointment;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.EmployeeRepository;
import sdms.repository.TreatmentRepository;

@Service
public class AppointmentService implements AppointmentServiceInterface{

	@Autowired
	AppointmentRepository repository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	TreatmentRepository treatmentRepository;
	
	private final static Logger LOGGER = LoggerFactory.getLogger( AppointmentService.class );
	
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
	public List<String> getInvoiceNumbersByCustomerId(long customerId) {
		
		List<Appointment> appointments = repository.findByCustomer( customerRepository.findById(customerId).get() );
		
		// Use a set for add one copy of each invoice number
		Set<String> setInvoiceNumber = new HashSet<String>();
		
		for( Appointment app : appointments ) {
			
			if( app.getInvoiceNumber() != null && ! app.getInvoiceNumber().trim().equals("") )	// avoid to insert empty invoice numbers 
				setInvoiceNumber.add( app.getInvoiceNumber() );
		}
		
		return new ArrayList<String>( setInvoiceNumber );
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
	public void putAppointmentBillNumberById(long id, String invoiceNumber) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setInvoiceNumber(invoiceNumber);
		repository.save(appointment);
		
	}

	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	@Override
	public void putAppointmentNoteById(long id, String notes) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setNotes(notes);
		repository.save(appointment);
		
	}

	// Volendo se ne può fare a meno, basta il putAppointment
	// però dato che questa funzionalità è prevista, così si alleggerisce il controller
	@Override
	public void putAppointmentTreatmentById(long id, long idTreatment) {
		
		Appointment appointment = repository.findById(id).get();
		appointment.setTreatment( treatmentRepository.findById( idTreatment ).get() );
		repository.save(appointment);
	}
	
	@Override
	@Transactional
	public void updateTeethList(long id, String teeth) {
		
		Appointment appointment = repository.findById(id)
				.orElseThrow( () -> new EntityNotFoundException( "Appointment not found for id: " + id ) );

		appointment.setTeeth(teeth);

		repository.save(appointment);
	}
	
	
	
	@Override
	public void deleteAppointmentById(long id) {
		
		repository.delete( repository.findById( id ).get() );
	}
	
}
