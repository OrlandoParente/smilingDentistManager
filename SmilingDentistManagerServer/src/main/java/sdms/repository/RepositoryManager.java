package sdms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sdms.model.Customer;
import sdms.model.MedicalHistory;
import sdms.service.DbManagerInterface;

@Repository("mainRepository")
public class RepositoryManager implements RepositoryInterface{

	@Autowired
	private DbManagerInterface dbManager;
	
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
	public boolean deleteCustomerById(String id) throws SQLException {
		
		boolean result = dbManager.deleteCustomerById(id);
		
		return result;
	}

	@Override
	public ArrayList<MedicalHistory> getMedicalsHistoryByCustomer(String id_customer) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MedicalHistory> AgetMedicalHistoryById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name, String descriprion)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMedicalHistoryById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
