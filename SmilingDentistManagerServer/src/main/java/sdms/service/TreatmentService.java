package sdms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Appointment;
import sdms.model.Customer;
import sdms.model.Treatment;
import sdms.repository.AppointmentRepository;
import sdms.repository.CustomerRepository;
import sdms.repository.TreatmentRepository;

@Service
public class TreatmentService implements TreatmentServiceInterface {

	@Autowired
	private TreatmentRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Treatment getTreatmentById(long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Treatment> getTreatmentsByCustomerId(long idCustomer) {
		
		Customer customer = customerRepository.findById(idCustomer).get();
		List<Appointment> appointments = appointmentRepository.findByCustomer(customer);
		List<Treatment> treatments = appointments.stream().map( a -> a.getTreatment() ).toList();
		
		return treatments;
	}

	@Override
	public List<Treatment> getTreatments() {
		
		return repository.findAll();
	}
	
	@Override
	public List<Treatment> getTreatmentsByBillNumber(String billNumber) {

		List<Appointment> appointments = appointmentRepository.findByBillNumber(billNumber);
		List<Treatment> treatments = appointments.stream().map( a -> a.getTreatment() ).toList();
		
		return treatments;
	}

	@Override
	public void postTreatment(Treatment treatment) {
		
		repository.save(treatment);
	}
	
	@Override
	public void putTreatment(Treatment treatment) {
		
		repository.save(treatment);
	}

	@Override
	public void deleteTreatmentById(long id) {
		
		Treatment treatment = repository.findById(id).get();
		
		// first delete the constraints --------------------
		List<Appointment> appointments = appointmentRepository.findByTreatment( treatment );
		
		for( Appointment appointment : appointments ) {
			appointment.setTreatment( null );
			appointmentRepository.save( appointment );
		}
		
		// -------------------------------------------------
		
		repository.delete( treatment );
	}

}
