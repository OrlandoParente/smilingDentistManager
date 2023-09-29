package sdms.controller.api;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sdms.repository.RepositoryInterface;

@RestController
public class GeneralRestController {
	
	@Autowired
	@Qualifier("mainRepository")
	RepositoryInterface repository;
	
	public GeneralRestController() {
		
	}
	
	
	@GetMapping("/getMaxIdFromTable/{table}")
	public int getLastInsertId( @PathVariable String table ) {
		
		try {
			
			int lastId = repository.getMaxIdFromTable( table ); 
			
			System.out.println("GeneralRestConetroller -> Last Insert Id -> " + lastId );
			
			return lastId;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return -1;
		
	}
	
	
}

