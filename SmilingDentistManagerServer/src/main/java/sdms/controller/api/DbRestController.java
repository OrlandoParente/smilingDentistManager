package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Customer;
import sdms.repository.RepositoryInterface;

@RestController
public class DbRestController {
	
	// Spring si occupa di associare la giusta implementazione
	@Autowired
	RepositoryInterface repository;
	//DbManagerInterface dbManager;
	
	public DbRestController() {
		
	}
	
	/*
	@RequestMapping("/getCustomers")
	public String getDoctors(){
		dbManager = DbManager.getDbManager();
		return null;
	}
	*/
	
	//
	 @RequestMapping("/getCustomers")
	 // @ResponseBody
	 public ArrayList<Customer> getDoctors(){
		 
		 // Stampa di controllo
		 System.out.println("DBRestController --> getCustomers ");
		 
		 ArrayList<Customer> customerList = null;
		 
		 try {
			 
			 customerList = repository.getCustomers();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return customerList;
	 }
	 
	 
	 // Inserimento dei soli parametri essenziali del cliente
	 @PostMapping( value = "/postCustomer" , params = {"name","surname","phone_number"}  )
	 public boolean postEssentialCustomer( @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("phone_number") String phoneNumber ) {

		 // Stampa di controllo
		 System.out.println("DBRestController --> getCustomers ");

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
		 System.out.println("DBRestController --> getCustomers (con tutti i dati) ");

		 try {
			 
			 return repository.postCustomer(taxIdCode, name, surname, birthCity, birthCityProvince, birthDate, residenceStreet,
					 						houseNumber, residenceProvince, residenceCity, residenceCityCap, phoneNumber, 
					 						phoneNumber2, EMail);
		
		 } catch (SQLException e) {
			e.printStackTrace();
		 }
		 
		 return false;
	 }
	 
}
