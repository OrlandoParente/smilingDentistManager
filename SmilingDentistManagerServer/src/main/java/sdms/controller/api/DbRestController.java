package sdms.controller.api;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sdms.repository.DbManager;

@RestController
public class DbRestController {

	DbManager dbManager;
	
	@RequestMapping("/getDoctors")
	public String getDoctors(){
		dbManager = DbManager.getDbManager();
		return null;
	}
}
