package sdms.repository;

import java.sql.*;

public class DbManager {

	private static Connection conn;
	private static boolean hasData;
	
	// Singleton: voglio un solo oggetto di questa classe 
	private static DbManager dbManager;
	
	private DbManager() {
		try {
			
			
			this.getConnection();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			//
			e.printStackTrace();
		}
	}
	
	public static DbManager getDbManager() {
		
		if( dbManager == null )
			dbManager = new DbManager();
		
		return dbManager;
		
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
			
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		if( !hasData ) {
			hasData = true;
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("Select name FROM sqlite_master WHERE type='table' AND name = 'data'");
			
			if( !res.next() )
				System.out.println("Database assente");
			
			// creazione relazione card
			Statement createDatasTable = conn.createStatement();
			createDatasTable.execute("CREATE TABLE customer("
											+ "id int NOT NULL AUTO_INCREMENT,"
											+ "name varchar(100),"
											+ "surname varchar(100),"
											+ "phone_number varchar(15),"
											+ "phone_number_2 varchar(15),"
											+ "e_mail varchar(100),"
											+ "primary key(id)"
											+ ")");
		}
	
	
	}
	
}
