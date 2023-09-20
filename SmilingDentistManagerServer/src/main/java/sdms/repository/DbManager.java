package sdms.repository;

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.sql.*;

public class DbManager implements DbManagerInterface {

	private static Connection conn;
	
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


		// ---------------- CANCELLARE DOPO FASE TESTING -----------------------------------------------------------------------------------------
		// Inserisce i permessi anche agli altri gruppi di utenti per facilitare la popolazione del database al di fuori del programma java ------
		// ATTENZIONE levare questo codice quando si è finita la fase di testing e cancellare/ riportare il database ai giusti permessi ----------
		
			try {
				File file = new File("sdm_db.db");
				if( !file.exists() )
					file.createNewFile();
				file.setWritable(true, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// ---------------------------------------------------------------------------------------------------------------------------------------
		// ---------------------------------------------------------------------------------------------------------------------------------------
		
		// carica la classe JDBC
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:sdm_db.db");
		
		// new FilePermission("sdm_db.db", "read,write");

		// Controllo esistenza del db al quale vogliamo connettercia ----
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("Select name FROM sqlite_master WHERE type='table'" );
		//ResultSet res = state.executeQuery("Select name FROM sqlite_master WHERE type='table' AND name = 'customer'");
		//---------------------------------------------------------------
		
		// Se il database non esiste lo crea (vuoto)
		if( !res.next() ) {
			System.err.println("Database not foud ---> Creating new Database");
			//System.err.println("Database not foud ---> " + res.next() );
			makeNewDatabase();
			
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
										+ "birth_city_province varchar(100),"  // preferibilmente la sigla provincia, ma lascio spazio per il nome completo
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
										// + "is_doctor bit,"  // cioè può mettere "le mani in bocca" al cliente
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
										// + "id_responsible_doctor integer,"
										+ "name varchar(100),"
										+ "description varchar(255),"
										+ "cost float,"
										+ "PRIMARY KEY(id AUTOINCREMENT)"
										// + "FOREIGN KEY(bill_number) REFERENCES bill(bill_number),"
										// + "FOREIGN KEY(id_responsible_doctor) REFERENCES employee(id)"
										+ ")");
		

		// relazione appuntamento per il trattamento
		createDatasTable.execute("CREATE TABLE appointment("
										+ "date date,"
										+ "time time,"
										+ "id_customer integer,"
										+ "id_doctor integer,"
										+ "id_treatment integer,"
										+ "is_done bit default 0," 	 // di default l'appuntamento non è ancora avvenuto nel momento della registrazione
										+ "bill_number varchar(50)," // Numero fattura - generalmente ANNO + NUMERO intero
																	 // I db non vuole salvarsi le fatture perché sarebbero dati ritondanti 
																	 // ( cioè le fatture si possono costruire dai dati già presenti nel db )
																	 // si limita a segnare quali appuntamenti sono stati già fatturati
										+ "note varchar(255),"		 // eventualmente se serve specificare qualcosa
										+ "PRIMARY KEY(date, time, id_customer),"
										+ "FOREIGN KEY(id_doctor) REFERENCES employee(id),"
										+ "FOREIGN KEY(id_customer) REFERENCES customer(id)"
										+ "FOREIGN KEY(id_treatment) REFERENCES tretment(id)"
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
										
		
	} // <- END MAKE NEW DB FUNCTION
	
	
	// Chiude la connessione 
	@Override
	public void closeConnection() throws SQLException {
		
		if( DbManager.conn != null ) {
			
			DbManager.conn.close();
			DbManager.conn = null;
			DbManager.dbManager = null;
		}
		
	}

	@Override
	public ResultSet getCustomers() throws SQLException {
		
		
		Statement state = conn.createStatement();
		/*ResultSet rs = state.executeQuery("SELECT* FROM customer" );
		System.out.println( "########################################################################" );
		System.out.println( rs );
		while( rs.next() ) System.out.println( rs.getString("name") );
		*/
		
		// ResultSet rs = state.executeQuery("SELECT* FROM employee" );
		
		state.execute("INSERT INTO customer (tax_id_code, name, surname, birth_city, birth_city_province, birth_date, residence_street, residence_city, residence_province, phone_number, phone_number_2, e_mail )\n"
				+ "VALUES ('1AAAAAAAAAAAAAAA', 'Giggi', 'Rossi', 'Cosmo', 'RM', '1990-10-10', 'Leonardo Da Vinci', 'Cosenza', 'CS', '1234567890', '2345678901', 'mariorossi@email.it' )");
				
		return null;
		
	}

	@Override
	public ResultSet getCustomerById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postCustomer(String name, String surname, String phone_number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postCustomer(String tax_id_code, String name, String surname, String birth_city,
			String birth_city_province, String birth_date, String residence_street, String residence_city,
			String residence_province, String phone_number, String phone_number_2, String e_mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomerById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getMedicalsHistoryByCustomer(String id_customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getMedicalHistoryById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postMedicalHistory(String id_customer, String type, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postMedicalHistory(String id_customer, String type, String name, String descriprion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMedicalHistoryById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAppointmentByCustomer(String customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAppointmentByDoctor(String doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String bill_number, String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			int is_done, String bill_number, String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putSetAppointmentDoneById(String date, String time, String id_customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putUnsetAppointmentDoneById(String date, String time, String id_customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAppointmentBillNumberById(String date, String time, String id_customer, String billNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAppointmentNoteById(String date, String time, String id_customer, String note) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putAppointmentTreatmentById(String date, String time, String id_customer, String id_treatment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppointmentById(String date, String time, String id_customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getTreatmentById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getTreatmentByCustomer(String id_customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getTreatmentByBillNumber(String bill_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postTreatment(String name, String cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postTreatment(String name, String description, String cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTreatmentById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getEmployeesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getEmployeesBySurname(String surnname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getEmployeesByProfessionalRoleName(String professiona_role_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getEmployeeById(String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postEmployee(String name, String surname, String title, String phone_number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postEmployee(String name, String surname, String title, String birth_date, String phone_number,
			String phone_number_2, String e_mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployeeById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getProfessionalRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postProfessionalRole(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postProfessionalRole(String name, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProfessionalRoleById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postLinkEmployeeToProfessionalRole(String id_employee, String id_professional_rol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLinkEmployeeWithProfessionalRole(String id_employee, String id_professional_rol) {
		// TODO Auto-generated method stub
		
	}
	
}
