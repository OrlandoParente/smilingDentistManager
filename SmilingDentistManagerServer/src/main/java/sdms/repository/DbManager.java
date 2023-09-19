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
		conn = DriverManager.getConnection("jdbc:sqlite:sdm_db.db");
		
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
										+ "id integer ," // non uso il cod_fiscale come id perché non so se il cliente è tenuto a rilasciarlo
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
										+ "PRIMARY KEY(id AUTOINCREMENT)"
										+ ")");
		
		
		// creazione relazione Medical History CIOE' Anamnesi
		createDatasTable.execute("CREATE TABLE medical_history("
										+ "id integer ," //
										+ "id_customer integer," //
										+ "type varchar(100),"   // [ anamnesi generale o odontoiatrica ]
										+ "name varchar(100),"
										+ "description varchar(255),"
										+ "PRIMARY KEY(id AUTOINCREMENT),"
										+ "FOREIGN KEY(id_customer) REFERENCES customer(id)"
										+ ")");
		
		// creazione relazione Employee
		createDatasTable.execute("CREATE TABLE employee("
										+ "id integer,"  // non uso il cod_fiscale come id perché non so se il dipendente è tenuto a rilasciarlo
										+ "name varchar(50)," 
										+ "surname varchar(50)," 
										+ "title varchar (10),"  // e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
										+ "birth_date date,"
										+ "phone_number varchar(15),"
										+ "phone_number_2 varchar(15)," // Generalmente telefono di casa
										+ "e_mail varchar(100),"
										+ "PRIMARY KEY(id AUTOINCREMENT)"
										+ ")");
		
		// creazione relazione professional_role ( e.g. assistente alla poltrona, direttore sanitario, odontotecnico, igienista, etc ... )
		createDatasTable.execute("CREATE TABLE professional_role("
										+ "id integer,"
										+ "is_doctor bit,"  // cioè può mettere "le mani in bocca" al cliente
										+ "name varchar(50),"
										+ "description varchar(255),"
										+ "PRIMARY KEY(id AUTOINCREMENT)"
										+ ")");
		
		// creazione n-m tra ruolo professionale ed impiegato
		createDatasTable.execute("CREATE TABLE has_professional_role("
										+ "id_employee integer,"
										+ "id_professional_role integer,"
										+ "PRIMARY KEY(id_employee, id_professional_role),"
										+ "FOREIGN KEY(id_employee) REFERENCES employee(id),"
										+ "FOREIGN KEY(id_professional_role) REFERENCES professional_role(id)"
										+ ")");
		
		// creazione relazione Treatment  CIOE' Trattamento
		createDatasTable.execute("CREATE TABLE treatment("
										+ "id integer," //
										// + "bill_number varchar(50)," // numero fattura a cui appartiene
										+ "id_responsible_doctor integer,"
										+ "name varchar(100),"
										+ "description varchar(255),"
										+ "cost float,"
										+ "PRIMARY KEY(id AUTOINCREMENT),"
										// + "FOREIGN KEY(bill_number) REFERENCES bill(bill_number),"
										+ "FOREIGN KEY(id_responsible_doctor) REFERENCES employee(id)"
										+ ")");
		
		/* ######################################################################################################################### 
		// creazione relazione Bill CIOE' Fattura
		createDatasTable.execute("CREATE TABLE bill("
										+ "bill_number varchar(50)," // Numero fattura - generalmente ANNO + NUMERO intero
										+ "id_treatment integer,"
										+ "date date,"
										+ "PRIMARY KEY(bill_number),"
										+ "FOREIGN KET(id_treatment) REFERENCES treatment(id)"
										+ ")");
		
		*/
		// relazione appuntamento per il trattamento
		createDatasTable.execute("CREATE TABLE appointment("
										+ "date date,"
										+ "time time,"
										+ "id_doctor integer,"
										+ "is_done bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
										+ "bill_number varchar(50)," // Numero fattura - generalmente ANNO + NUMERO intero
																	 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
																	 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
																	 // si limita a segnare quali appuntamenti sono stati già fatturati
										+ "note varchar(100),"		 // eventualmente se serve specificare qualcosa
										+ "PRIMARY KEY(date, time),"
										+ "FOREIGN KEY(id_doctor) REFERENCES employee(id)"
										+ ")");
		
		/*
		// creazione n-m tra trattamento e appuntamento
		// idea: un trattamento può necessitare di essere diviso in più sedute
		// in una seduta, si può decidere (con molto coraggio del paziente) di fare più trattamenti
		createDatasTable.execute("CREATE TABLE treatment_appointment ("
										+ "id_treatment integer,"
										+ "date_,"
										+ "PRIMARY KEY(id_employee, id_professional_role),"
										+ "FOREIGN KEY(id_employee) REFERENCES employee(id),"
										+ "FOREIGN KEY(id_professional_role) REFERENCES professional_role(id),"
										+ ")");	
			*/
										
		
	}
	
}
