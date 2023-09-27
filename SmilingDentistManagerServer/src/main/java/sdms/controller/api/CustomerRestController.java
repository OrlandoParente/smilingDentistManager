package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Customer;
import sdms.repository.RepositoryInterface;

@RestController
public class CustomerRestController {
	
	// Spring si occupa di associare la giusta implementazione
	@Autowired
	@Qualifier("mainRepository")
	RepositoryInterface repository;
	//DbManagerInterface dbManager;
	
	public CustomerRestController() {
		
	}
	
	/*
	@RequestMapping("/getCustomers")
	public String getDoctors(){
		dbManager = DbManager.getDbManager();
		return null;
	}
	*/
	
	//
	 // @RequestMapping("/getCustomers")
	 // @ResponseBody
	@GetMapping("/getCustomers") 
	public ArrayList<Customer> getCustomers(){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomers ");
		 
		 ArrayList<Customer> customerList = null;
		 
		 try {
			 
			 customerList = repository.getCustomers();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return customerList;
	 }
	 
	 @RequestMapping("/getCustomersByPartialKeyWordOverAllFields/{key_word}")
	 // @ResponseBody
	 public ArrayList<Customer> getCustomersByPartialKeyWordOverAllFields( @PathVariable String key_word ){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomersByPartialKeyWordOverAllFields -> key_word = " + key_word );
		 
		 ArrayList<Customer> customerList = null;
		 
		 try {
			 
			 customerList = repository.getCustomersByPartialKeyWordOverAllFields( key_word );
		 
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return customerList;
	 }
	 
	 @GetMapping( value = "/getCustomerById/{id}" )
	 // @ResponseBody
	 public Customer getCustomerById( @PathVariable int id){
		 
		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomer -> id customer = " + id );
		 
		 Customer customer =  null;
		 
		 try {
			 
			 customer = repository.getCustomerById( id );
		 
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return customer;
	 }
	 
	 
	 // Inserimento dei soli parametri essenziali del cliente
	 @PostMapping( value = "/postCustomer" , params = {"name","surname","phone_number"}  )
	 public boolean postEssentialCustomer( @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("phone_number") String phoneNumber ) {

		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomers ");

		 try {
			 
			 return repository.postCustomer(name, surname, phoneNumber);
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 
		 return false;
	 }
	 
	 
	 //
	 @PostMapping( value = "/postCustomer" , params = {"tax_id_code", "name", "surname", "birth_city" , "birth_city_province", 
			 "birth_date","residence_street", "house_number", "residence_city", "residence_city_cap" ,"residence_province", 
			 "phone_number" , "phone_number_2", "e_mail" })
	 public boolean postCustomer(  @RequestParam("tax_id_code") String taxIdCode , @RequestParam("name") String name, 
			 @RequestParam("surname") String surname, @RequestParam("birth_city") String birthCity , @RequestParam("birth_city_province") String birthCityProvince,
			 @RequestParam("birth_date") String birthDate,@RequestParam("residence_street") String residenceStreet, 
			 @RequestParam("house_number") String houseNumber, @RequestParam("residence_city") String residenceCity, 
			 @RequestParam("residence_city_cap") String residenceCityCap,
			 @RequestParam("residence_province") String residenceProvince, @RequestParam("phone_number") String phoneNumber, 
			 @RequestParam("phone_number_2") String phoneNumber2,  @RequestParam("e_mail") String EMail) {

		 // Stampa di controllo
		 System.out.println("CustomerRestController --> getCustomers (con tutti i dati) ");

		 try {
			 
			 return repository.postCustomer(taxIdCode, name, surname, birthCity, birthCityProvince, birthDate, residenceStreet,
					 						houseNumber, residenceProvince, residenceCity, residenceCityCap, phoneNumber, 
					 						phoneNumber2, EMail);
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 
		 return false;
	 }
	 
	 // Aggiorna tutti i valori del Customer associato all'id passato
	 @PutMapping( value = "/putCustomerById" , params = {"id","tax_id_code", "name", "surname", "birth_city" , "birth_city_province", 
			 "birth_date","residence_street", "house_number", "residence_city", "residence_city_cap" ,"residence_province", 
			 "phone_number" , "phone_number_2", "e_mail" })
	 public boolean putCustomerById(  @RequestParam("id") String id, @RequestParam("tax_id_code") String taxIdCode , 
			 @RequestParam("name") String name, @RequestParam("surname") String surname, 
			 @RequestParam("birth_city") String birthCity , @RequestParam("birth_city_province") String birthCityProvince,
			 @RequestParam("birth_date") String birthDate,@RequestParam("residence_street") String residenceStreet, 
			 @RequestParam("house_number") String houseNumber, @RequestParam("residence_city") String residenceCity, 
			 @RequestParam("residence_city_cap") String residenceCityCap,
			 @RequestParam("residence_province") String residenceProvince, @RequestParam("phone_number") String phoneNumber, 
			 @RequestParam("phone_number_2") String phoneNumber2,  @RequestParam("e_mail") String EMail) {

		 // Stampa di controllo
		 System.out.println("CustomerRestController --> puCustomerById (con tutti i dati) ");

		 try {
			 
			 return repository.putCustomerById(id, taxIdCode, name, surname, birthCity, birthCityProvince, birthDate, 
					 					residenceStreet, houseNumber, residenceProvince, residenceCity, residenceCityCap, 
					 					phoneNumber,  phoneNumber2, EMail);
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 
		 return false;
	 }
	 
	 
	 // Eliminazione Customer by Id
	 @DeleteMapping( value="/deleteCustomer" , params = { "id" } )
	 public boolean deleteCustomerById( @RequestParam("id") String id ) {
		 
		 try {
			return repository.deleteCustomerById( id );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		 return false;
		 
	 }
}
