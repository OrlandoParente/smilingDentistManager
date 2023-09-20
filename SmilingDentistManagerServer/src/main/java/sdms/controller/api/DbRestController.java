package sdms.controller.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sdms.repository.DbManager;

@RestController
public class DbRestController {

	DbManager dbManager;
	
	/*
	@RequestMapping("/getCustomers")
	public String getDoctors(){
		dbManager = DbManager.getDbManager();
		return null;
	}
	*/
	
	 @RequestMapping("/getCustomers")
	public String getDoctors(){
		 String out = "";
		dbManager = DbManager.getDbManager();
		ResultSet rs = null;
		try {
			rs = dbManager.getCustomers();
			// System.out.println( rs.getString("name") );
			if(  rs != null && rs.next()  )
			 out += " " + rs.getString("name");
			else out="Query empty!";
			// dbManager.closeConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return out;
	}
	 
}
