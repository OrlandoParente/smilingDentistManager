package sdms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import sdms.model.Appointment;
import sdms.model.Customer;
import sdms.model.Employee;
import sdms.model.MedicalHistory;
import sdms.model.ProfessionalRole;
import sdms.model.Treatment;
import sdms.service.DbManagerInterface;

@Repository("mainRepository")
public class RepositoryManager implements RepositoryInterface{

	@Autowired
	@Qualifier("mainDbManager")
	private DbManagerInterface dbManager;
	
	public RepositoryManager() {
		
	}
	
	// Generale --------------------------------------------------------------------------------------------------
	
	public int getMaxIdFromTable( String table ) throws SQLException{
		
		return dbManager.getMaxIdFromTable( table );
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	// Customer Management ----------------------------------------------------------------------------------------
	
	@Override
	public ArrayList<Customer> getCustomers() throws SQLException {
		
		ArrayList<Customer> customerList = new ArrayList<>();
		ResultSet rs = dbManager.getCustomers();
		
		while( rs.next() ) {
			customerList.add( new Customer(rs.getInt("id"), rs.getString("tax_id_code"), rs.getString("name"), rs.getString("surname"), 
					rs.getString("birth_city"), rs.getString("birth_city_province"), rs.getString("birth_date"),
					rs.getString("residence_street"), rs.getString("house_number"), rs.getString("residence_city"), 
					rs.getString("residence_city_cap"), rs.getString("residence_province"), rs.getString("phone_number"),   
					rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return customerList;
	}
	
	@Override
	public ArrayList<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word ) throws SQLException{
		
		ArrayList<Customer> customerList = new ArrayList<>();
		ResultSet rs = dbManager.getCustomersByPartialKeyWordOverAllFields( key_word );
		
		while( rs.next() ) {
			customerList.add( new Customer(rs.getInt("id"), rs.getString("tax_id_code"), rs.getString("name"), rs.getString("surname"), 
					rs.getString("birth_city"), rs.getString("birth_city_province"), rs.getString("birth_date"),
					rs.getString("residence_street"), rs.getString("house_number"), rs.getString("residence_city"), 
					rs.getString("residence_city_cap"), rs.getString("residence_province"), rs.getString("phone_number"),   
					rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return customerList;
	}

	@Override
	public Customer getCustomerById(Integer id) throws SQLException {
		
		ResultSet rs = dbManager.getCustomerById( id.toString() );
		Customer customer = new Customer(rs.getInt("id"), rs.getString("tax_id_code"), rs.getString("name"), rs.getString("surname"), 
				rs.getString("birth_city"), rs.getString("birth_city_province"), rs.getString("birth_date"),
				rs.getString("residence_street"), rs.getString("house_number"), rs.getString("residence_city"), 
				rs.getString("residence_city_cap"), rs.getString("residence_province"), rs.getString("phone_number"),   
				rs.getString("phone_number_2"), rs.getString("e_mail") );
		
		dbManager.closeConnection();
		
		return customer;
	}

	@Override
	public boolean postCustomer(String name, String surname, String phone_number) throws SQLException {
		
		boolean result = dbManager.postCustomer(name, surname, phone_number);
		 
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postCustomer(String tax_id_code, String name, String surname, String birth_city,
			String birth_city_province, String residence_city_cap , String birth_date, String residence_street,String house_number,
			String residence_city,String residence_province, String phone_number, String phone_number_2, String e_mail)
					throws SQLException {
		
		boolean result = dbManager.postCustomer(tax_id_code, name, surname, birth_city, birth_city_province, birth_date, 
												residence_street, house_number, residence_city, residence_city_cap , 
												residence_province, phone_number, phone_number_2, e_mail);
		 
		dbManager.closeConnection();
		
		return result;
	}
	
	@Override
	public boolean putCustomerById(String id, String tax_id_code, String name, String surname, String birth_city,
			String birth_city_province, String residence_city_cap , String birth_date, String residence_street,String house_number,
			String residence_city,String residence_province, String phone_number, String phone_number_2, String e_mail)
					throws SQLException {
		
		boolean result = dbManager.putCustomerById( id, tax_id_code, name, surname, birth_city, birth_city_province, birth_date, 
												residence_street, house_number, residence_city, residence_city_cap , 
												residence_province, phone_number, phone_number_2, e_mail);
		 
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean deleteCustomerById(String id) throws SQLException {
		
		boolean result = dbManager.deleteCustomerById(id);
		
		dbManager.closeConnection();
		
		return result;
	}
	// ------------------------------------------------------------------------------------------------------------
	// Medical Hitory Managment (Anamnesi) ------------------------------------------------------------------------
	
	@Override
	public ArrayList<MedicalHistory> getMedicalsHistoryByCustomer(String id_customer) throws SQLException {
		
		ArrayList<MedicalHistory> arrayListMedicalHistory = new ArrayList<MedicalHistory>();
		
		ResultSet rs = dbManager.getMedicalsHistoryByCustomer( id_customer );
		
		while( rs.next() ) {
			arrayListMedicalHistory.add( new MedicalHistory( rs.getInt("id"), rs.getInt("id_customer"), 
																rs.getString("type"),	rs.getString("name"), 
																rs.getString("description")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListMedicalHistory;
	}

	@Override
	public ArrayList<MedicalHistory> getMedicalHistoryById(String id) throws SQLException {
		
		ArrayList<MedicalHistory> arrayListMedicalHistory = new ArrayList<MedicalHistory>();
		
		ResultSet rs = dbManager.getMedicalHistoryById( id );
		
		while( rs.next() ) {
			arrayListMedicalHistory.add( new MedicalHistory( rs.getInt("id"), rs.getInt("id_customer"), 
																rs.getString("type"),	rs.getString("name"), 
																rs.getString("description")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListMedicalHistory;
		
	}
	
	
	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name) throws SQLException {
		
		boolean result = dbManager.postMedicalHistory(id_customer, type, name);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name, String descriprion)
			throws SQLException {
		
		boolean result = dbManager.postMedicalHistory(id_customer, type, name, descriprion);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean deleteMedicalHistoryById(String id) throws SQLException {
		
		boolean result = dbManager.deleteMedicalHistoryById( id ); 
		
		dbManager.closeConnection();
		
		return result;
		
		
	}

	// ------------------------------------------------------------------------------------------------------------
	// Appointment Management -------------------------------------------------------------------------------------

	
	@Override
	public ArrayList<Appointment> getAppointments() throws SQLException {
		
		ArrayList<Appointment> arrayListAppointments = new ArrayList<Appointment>();
		
		ResultSet rs = dbManager.getAppointments();
		
		while( rs.next() ) {
			arrayListAppointments.add( new Appointment(rs.getString("date"), rs.getString("time"), rs.getInt("id_customer"),
														rs.getInt("id_doctor"), rs.getInt("id_treatment"), rs.getInt("is_done"),
														rs.getString("bill_number"), rs.getString("note") ) );
		}
		
		dbManager.closeConnection();
		
		return arrayListAppointments;
	}

	@Override
	public ArrayList<Appointment> getAppointmentsByCustomerId(String id_customer) throws SQLException {
		
		ArrayList<Appointment> arrayListAppointments = new ArrayList<Appointment>();
		
		ResultSet rs = dbManager.getAppointmentsByCustomerId( id_customer );
		
		while( rs.next() ) {
		
			arrayListAppointments.add( new Appointment(rs.getString("date"), rs.getString("time"), rs.getInt("id_customer"),
														rs.getInt("id_doctor"), rs.getInt("id_treatment"), rs.getInt("is_done"),
														rs.getString("bill_number"), rs.getString("note") ) );
		}
		
		dbManager.closeConnection();
		
		return arrayListAppointments;
	}

	@Override
	public ArrayList<Appointment> getAppointmentsByDoctorId(String id_doctor) throws SQLException {
		
		ArrayList<Appointment> arrayListAppointments = new ArrayList<Appointment>();
		
		ResultSet rs = dbManager.getAppointmentsByDoctorId( id_doctor );
		
		while( rs.next() ) {
			arrayListAppointments.add( new Appointment(rs.getString("date"), rs.getString("time"), rs.getInt("id_customer"),
														rs.getInt("id_doctor"), rs.getInt("id_treatment"), rs.getInt("is_done"),
														rs.getString("bill_number"), rs.getString("note") ) );
		}
		
		dbManager.closeConnection();
		
		return arrayListAppointments;
		
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String note) throws SQLException {
		
		boolean result = dbManager.postAppointment(date, time, id_customer, id_doctor, id_treatment, note);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String bill_number, String note) throws SQLException {
		
		boolean result = dbManager.postAppointment(date, time, id_customer , id_doctor, id_treatment, bill_number, note);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			int is_done, String bill_number, String note) throws SQLException {
		
		boolean result = dbManager.postAppointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note);
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean putSetAppointmentDoneById(String date, String time, String id_customer) throws SQLException {
		
		boolean result = dbManager.putSetAppointmentDoneById(date, time, id_customer);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean putUnsetAppointmentDoneById(String date, String time, String id_customer) throws SQLException {
		
		boolean result = dbManager.putUnsetAppointmentDoneById(date, time, id_customer);
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean putAppointmentBillNumberById(String date, String time, String id_customer, String bill_number)
			throws SQLException {
		
		boolean result = dbManager.putAppointmentBillNumberById(date, time, id_customer, bill_number);
		
		dbManager.closeConnection();
		
		return result;
		
		
	}

	@Override
	public boolean putAppointmentNoteById(String date, String time, String id_customer, String note)
			throws SQLException {
		
		boolean result = dbManager.putAppointmentNoteById(date, time, id_customer, note);
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean putAppointmentTreatmentById(String date, String time, String id_customer, String id_treatment)
			throws SQLException {
		
		boolean result = dbManager.putAppointmentTreatmentById(date, time, id_customer, id_treatment);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean deleteAppointmentById(String date, String time, String id_customer) throws SQLException {
		
		boolean result = dbManager.deleteAppointmentById(date, time, id_customer);
		
		dbManager.closeConnection();
		
		return result;
		
	}

	
	
	// ------------------------------------------------------------------------------------------------------------
	
	// Treatment Management ---------------------------------------------------------------------------------------
	
	@Override
	public Treatment getTreatmentById(String id) throws SQLException {
		
		ResultSet rs = dbManager.getTreatmentById(id);
		
		Treatment treatment = new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("cost") );
		
		dbManager.closeConnection();
		
		return treatment;
	}

	@Override
	public ArrayList<Treatment> getTreatmentsByCustomerId(String id_customer) throws SQLException {
		
		ArrayList<Treatment> arrayListTreatment = new ArrayList<Treatment>();
		
		ResultSet rs = dbManager.getTreatmentsByCustomerId( id_customer );
		
		while( rs.next() ) {
			arrayListTreatment.add( new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("cost") ) );
		}
		
		dbManager.closeConnection();
		
		return arrayListTreatment;
	}

	@Override
	public ArrayList<Treatment> getTreatmentsByBillNumber(String bill_number) throws SQLException {
		
		ArrayList<Treatment> arrayListTreatment = new ArrayList<Treatment>();
		
		ResultSet rs = dbManager.getTreatmentsByBillNumber( bill_number );
		
		while( rs.next() ) {
			arrayListTreatment.add( new Treatment(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("cost") ) );
		}
		
		dbManager.closeConnection();
		
		return arrayListTreatment;
	}

	@Override
	public boolean postTreatment(String name, String cost) throws SQLException {
		
		boolean result = dbManager.postTreatment( name, cost );
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postTreatment(String name, String description, String cost) throws SQLException {
		
		boolean result = dbManager.postTreatment( name, description, cost );
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean deleteTreatmentById(String id) throws SQLException {
		
		boolean result = dbManager.deleteTreatmentById( id );
		
		dbManager.closeConnection();
		
		return result;
		
		
	}
	
	
	
	// ------------------------------------------------------------------------------------------------------------
	
	// Employee Management ---------------------------------------------------------------------------------------

	@Override
	public ArrayList<Employee> getEmployees() throws SQLException {
		
		ArrayList<Employee> arrayListEmployees = new ArrayList<Employee>();
		
		ResultSet rs = dbManager.getEmployees();
		
		while( rs.next() ) {
			arrayListEmployees.add( new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListEmployees;
	}

	@Override
	public ArrayList<Employee> getEmployeesByName(String name) throws SQLException {
		
		ArrayList<Employee> arrayListEmployees = new ArrayList<Employee>();
		
		ResultSet rs = dbManager.getEmployeesByName( name );
		
		while( rs.next() ) {
			arrayListEmployees.add( new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListEmployees;
		
		
	}

	@Override
	public ArrayList<Employee> getEmployeesBySurname(String surname) throws SQLException {
		
		ArrayList<Employee> arrayListEmployees = new ArrayList<Employee>();
		
		ResultSet rs = dbManager.getEmployeesBySurname( surname );
		
		while( rs.next() ) {
			arrayListEmployees.add( new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListEmployees;
	}

	@Override
	public ArrayList<Employee> getEmployeesByProfessionalRoleName(String professiona_role_name) throws SQLException {

		ArrayList<Employee> arrayListEmployees = new ArrayList<Employee>();
		
		ResultSet rs = dbManager.getEmployeesByProfessionalRoleName( professiona_role_name );
		
		while( rs.next() ) {
			arrayListEmployees.add( new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListEmployees;
	}

	@Override
	public ArrayList<Employee> getEmployeesByPartialKeyWordOverAllFields(String key_word) throws SQLException {
		
		ArrayList<Employee> arrayListEmployees = new ArrayList<Employee>();
		
		ResultSet rs = dbManager.getEmployeesByPartialKeyWordOverAllFields( key_word );
		
		while( rs.next() ) {
			arrayListEmployees.add( new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListEmployees;
		
		
	}

	@Override
	public Employee getEmployeeById(String id) throws SQLException {

		ResultSet rs = dbManager.getEmployeeById( id );
		
		
		Employee employee =  new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), 
										rs.getString("title"), rs.getString("birth_date"), rs.getString("phone_number"), 
										rs.getString("phone_number_2"), rs.getString("e_mail")) ;
		
		dbManager.closeConnection();
		
		return employee;
		
		
	}

	@Override
	public boolean postEmployee(String name, String surname, String title, String phone_number) throws SQLException {
		
		boolean result = dbManager.postEmployee(name, surname, title, phone_number);
		
		dbManager.closeConnection();
		
		return result;
	}

	@Override
	public boolean postEmployee(String name, String surname, String title, String birth_date, String phone_number,
			String phone_number_2, String e_mail) throws SQLException {

		boolean result = dbManager.postEmployee( name, surname, title, birth_date, phone_number, phone_number_2, e_mail );
		
		dbManager.closeConnection();
		
		return result;
		
		
	}

	@Override
	public boolean putEmployeeById(String id, String name, String surname, String title, String birth_date,
			String phone_number, String phone_number_2, String e_mail) throws SQLException {
		
		boolean result = dbManager.putEmployeeById( id, name, surname, title, birth_date, phone_number, phone_number_2, e_mail );
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean deleteEmployeeById(String id) throws SQLException {
		
		boolean result = dbManager.deleteEmployeeById( id );
		
		dbManager.closeConnection();
		
		return result;
		
		
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	// Professional Role Management -------------------------------------------------------------------------------


	@Override
	public ArrayList<ProfessionalRole> getProfessionalRoles() throws SQLException {
		
		ArrayList<ProfessionalRole> arrayListProfessionalRole = new ArrayList<ProfessionalRole>();
		
		ResultSet rs = dbManager.getProfessionalRoles();
		
		while( rs.next() ) {
			arrayListProfessionalRole.add( new ProfessionalRole(rs.getInt("id"), rs.getString("name"), rs.getString("description")) );
		}
		
		dbManager.closeConnection();
		
		return arrayListProfessionalRole;
	}

	@Override
	public boolean postProfessionalRole(String name) throws SQLException {
		
		boolean result = dbManager.postProfessionalRole( name );
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean postProfessionalRole(String name, String description) throws SQLException {
		
		boolean result = dbManager.postProfessionalRole( name, description );
		
		dbManager.closeConnection();
		
		return result;
		
	}

	@Override
	public boolean putProfessionalRoleById( String id, String name, String description ) throws SQLException{
	
		boolean result = dbManager.putProfessionalRoleById( id, name, description );
		
		dbManager.closeConnection();
		
		return result;
	}
	
	
	@Override
	public boolean deleteProfessionalRoleById(String id) throws SQLException {
		
		boolean result = dbManager.deleteProfessionalRoleById( id );
		
		dbManager.closeConnection();
		
		return result;
		
	}

	// ------------------------------------------------------------------------------------------------------------
	
	// Has Professional Role Management ----------------------------------------------------------------------------

	@Override
	public boolean postLinkEmployeeToProfessionalRole(String id_employee, String id_professional_role)
			throws SQLException {
		
		boolean result = dbManager.postLinkEmployeeToProfessionalRole( id_employee, id_professional_role );
		
		dbManager.closeConnection();
		
		return result;
		
		
		
	}

	@Override
	public boolean deleteLinkEmployeeWithProfessionalRole(String id_employee, String id_professional_role)
			throws SQLException {
		
		boolean result = dbManager.deleteLinkEmployeeWithProfessionalRole( id_employee, id_professional_role );
		
		dbManager.closeConnection();
		
		return result;
		
	}

	// ------------------------------------------------------------------------------------------------------------
	
	
}
