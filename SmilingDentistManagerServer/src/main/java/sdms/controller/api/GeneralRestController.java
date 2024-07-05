package sdms.controller.api;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sdms.service.ServicesInterface;

@RestController
public class GeneralRestController {
	
	@Autowired
	@Qualifier("mainService")
	ServicesInterface service;
	
	public GeneralRestController() {
		
	}
	
	
	@GetMapping("/getMaxIdFromTable/{table}")
	public int getLastInsertId( @PathVariable String table ) {
		
		try {
			
			int lastId = service.getMaxIdFromTable( table ); 
			
			System.out.println("GeneralRestConetroller -> Last Insert Id -> " + lastId );
			
			return lastId;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	
}

