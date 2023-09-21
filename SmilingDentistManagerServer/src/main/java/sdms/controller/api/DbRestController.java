package sdms.controller.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sdms.model.Customer;
import sdms.repository.RepositoryInterface;
import sdms.service.DbManager;
import sdms.service.DbManagerInterface;

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
	
	 @RequestMapping("/getCustomers")
	 @ResponseBody
	public String getDoctors(){
		 
		 
		 ArrayList<Customer> customerList = null;
		 
		 try {
			 
			 customerList = repository.getCustomers();
		 
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return customerList.toString();
	}
	 
}
