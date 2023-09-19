package sdms.repository;

import java.sql.*;

public class DbManager {

	private static Connection conn;
	private static boolean isConnectionOpen;
	
	// Singleton: voglio un solo oggetto di questa classe #########################
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
	// ############################################################################

	private void getConnection() throws ClassNotFoundException, SQLException {
			
		// carica la classe JDBC
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		
		//
		if( !isConnectionOpen ) {
			
			isConnectionOpen = true;
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("Select name FROM sqlite_master WHERE type='table' AND name = 'data'");
			
			// Se il database non esiste lo crea (vuoto)
			if( !res.next() ) {
				System.err.println("Database not foud ---> Creating new Database");
				makeNewDatabase();
			}
			
		}
	}
	
	private void makeNewDatabase() throws SQLException {
		
		Statement createDatasTable = conn.createStatement();
		
		// creazione relazione customer
		createDatasTable.execute("CREATE TABLE customer("
										+ "id integer AUTO_INCREMENT," // non uso il cod_fiscale come id perché non so se il cliente è tenuto a rilasciarlo
										+ "tax_id_code varchar(16)," // codice fiscale
										+ "name varchar(100),"
										+ "surname varchar(100),"
										+ "birth_city varchar(100),"
										+ "birth_city_province varchar(5),"  // sigla provincia
										+ "birth_date date,"
										+ "residence_street varchar(100),"
										+ "residence_city varchar(100),"
										+ "residence_province varchar(5),"   // sigla provincia
										+ "phone_number varchar(15),"
										+ "phone_number_2 varchar(15)," // Generalmente telefono di casa
										+ "e_mail varchar(100),"
										+ "PRIMARY KEY(id)"
										+ ")");
		
		
		// creazione relazione Medical History CIOE' Anamnesi
		createDatasTable.execute("CREATE TABLE medical_history("
										+ "id integer ," //
										+ "id_customer integer," //
										+ "type varchar(100),"   // [ anamnesi generale o odontoiatrica ]
										+ "name varchar(100),"
										+ "description varchar(100),"
										+ "PRIMARY KEY(id AUTOINCREMENT),"
										+ "FOREIGN KEY(id_customer) REFERENCES customer(id)"
										+ ")");
										
		
	}
	
}
