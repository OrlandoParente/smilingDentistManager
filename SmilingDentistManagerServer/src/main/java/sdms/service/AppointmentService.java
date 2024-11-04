package sdms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdms.model.Appointment;
import sdms.repository.AppointmentRepository;

@Service
public class AppointmentService implements AppointmentServiceInterface{

	@Autowired
	AppointmentRepository repository;
	
	@Override
	public List<Appointment> getAppointments() {
		
		return repository.findAll();
	}

	@Override
	public List<Appointment> getAppointmentsByCustomerId(long customerId) {
		
//		return repository.findByIdCustomer(customerId);
		return null;
	}

	@Override
	public ArrayList<Appointment> getAppointmentsByDoctorId(long doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String note) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String bill_number, String note) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			int is_done, String bill_number, String note) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putSetAppointmentDoneById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putUnsetAppointmentDoneById(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putAppointmentBillNumberById(long id, String bill_number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putAppointmentNoteById(long id, String note) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean putAppointmentTreatmentById(long id, String id_treatment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAppointmentById(long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
