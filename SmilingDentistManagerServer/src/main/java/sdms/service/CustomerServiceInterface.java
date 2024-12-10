package sdms.service;

import java.util.List;

import sdms.model.Customer;

public interface CustomerServiceInterface {
	
	public long getLastCustomerId();
	
	public Customer getCustomerById( long id );
	public Customer getCustomerByEMail( String eMail );
	
	public List<Customer> getCustomers();
	public List<Customer> getCustomersByPartialKeyWordOverAllFields( String key_word );
	


//	// inserimento dati essenziali del cliente
//	boolean postCustomer( String name , String surname ,String phone_number ) throws SQLException;
//	
//	boolean postCustomer( String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , 
//			String birth_city_province, String birth_date, String residence_street, String house_number, 
//			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
//			String phone_number_2, String e_mail )
//		    throws SQLException;4+
	public void postCustomer( Customer customer );
//	
//	boolean putCustomerById( String id, String tax_id_code /* codice fiscale*/, String name , String surname , String birth_city , 
//			String birth_city_province, String birth_date, String residence_street, String house_number, 
//			String residence_city , String residence_city_cap , String residence_province, String phone_number ,
//			String phone_number_2, String e_mail )
//		    throws SQLException;
//	
	public void putCustomer( Customer customer );

	public void deleteCustomer( long id );
	
}
