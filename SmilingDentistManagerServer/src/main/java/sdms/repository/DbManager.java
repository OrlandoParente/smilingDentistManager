package sdms.repository;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


// Mettiamo questa etichetta sull'implementazione da usare
// e Spring la utilizzerà anche se l'interfaccia ha più di una implementazione
@Repository("mainRepository")
public class DbManager implements DbManagerInterface {

	private static Connection conn;
	
	// Singleton: voglio un solo oggetto di questa classe #########################
	// private static DbManagerInterface dbManager;
	
	public DbManager() {
		
		try {
			
			if( DbManager.conn == null )
				this.getConnection();
			// DbManager.dbManager = this;
			
		} catch (ClassNotFoundException | SQLException e) {
			//
			e.printStackTrace();
		}
	}
	
	/*
	// Utilizzo la factory per renderlo static
	public DbManagerInterface getDbManager() {
		
		//if( dbManager == null )
		//	dbManager = new DbManager();
		
		return dbManager;
		
		return null;
		
	}
	*/
	
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
										+ "house_number varchar(10),"
										+ "residence_city varchar(100),"
										+ "residence_city_cap varchar(5),"
										+ "residence_province varchar(100),"   // sigla provincia preferibilmente (?)
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
										+ "id integer,"
										+ "id_employee integer,"
										+ "id_professional_role integer,"
										+ "PRIMARY KEY(id AUTOINCREMENT),"
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
										+ "id integer,"
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
										+ "PRIMARY KEY(id AUTOINCREMENT),"
										+ "FOREIGN KEY(id_doctor) REFERENCES employee(id),"
										+ "FOREIGN KEY(id_customer) REFERENCES customer(id)"
										+ "FOREIGN KEY(id_treatment) REFERENCES treatment(id)"
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
			// DbManager.dbManager = null;
		}
		
	}
	
	private void connectIfClosed() {
		
		try {
			
			if( conn == null || conn.isClosed() ) 
				this.getConnection();
			
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public int getMaxIdFromTable( String table) throws SQLException{
		
		this.connectIfClosed();
		
		int lastId = -1;
		
		ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(id) FROM " + table  ); 
		
		if( rs.next() )
			lastId = rs.getInt(1);
		
		return lastId;
		
	}
	
	@Override
	public ResultSet getCustomers() throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM customer" );
		
	}

	@Override
	public ResultSet getCustomerById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM customer WHERE customer.id='" + id + "'");
	}
	
	@Override
	public ResultSet getCustomersByPartialKeyWordOverAllFields( String key_word ) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM customer WHERE tax_id_code LIKE '%"+ key_word + "%' "
				+ "OR name LIKE '%"+ key_word + "%' OR surname OR birth_city LIKE '%"+ key_word + "%' "
				+ "OR birth_city_province LIKE '%"+ key_word + "%' "
				+ "OR birth_date LIKE '%"+ key_word + "%' OR residence_street LIKE '%"+ key_word + "%'"
				+ "OR house_number LIKE '%"+ key_word + "%'"
				+ "OR residence_city LIKE '%"+ key_word + "%' OR residence_city_cap LIKE '%"+ key_word + "%'"
				+ "OR residence_province LIKE '%"+ key_word + "%' OR phone_number LIKE '%"+ key_word + "%'"
				+ "OR phone_number_2 LIKE '%"+ key_word + "%' OR e_mail LIKE '%"+ key_word + "%'");
	}

	@Override
	public boolean postCustomer(String name, String surname, String phone_number) throws SQLException {
		
		return this.postCustomer( "" , name, surname, "" , "" , "", "", "", "", "", "", phone_number, "", "" );
		
		
	}

	@Override
	public boolean postCustomer(String tax_id_code, String name, String surname, String birth_city,
			String birth_city_province, String birth_date, String residence_street, String house_number,
			String residence_city, String residence_city_cap, String residence_province, String phone_number, 
			String phone_number_2, String e_mail) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("INSERT INTO customer( tax_id_code, name, surname, birth_city, birth_city_province , "
					+ " birth_date, residence_street, house_number, residence_city, residence_city_cap, "
					+ "residence_province, phone_number, phone_number_2, e_mail ) "
					+ "VALUES (  '"+ tax_id_code +"','"+ name +"','"+ surname +"','"+ birth_city  +"','"+ birth_city_province +"','"
					+ birth_date +"','"+ residence_street +"','"+ house_number +"','"+ residence_city +"','"+ residence_city_cap 
					+"','"+ residence_province +"','"+ phone_number  +"','" + phone_number_2 +"','"+ e_mail +"')");
		
	}
	
	@Override
	public boolean putCustomerById(String id, String tax_id_code, String name, String surname, String birth_city,
			String birth_city_province, String birth_date, String residence_street, String house_number,
			String residence_city, String residence_city_cap, String residence_province, String phone_number,
			String phone_number_2, String e_mail) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE customer SET tax_id_code='" + tax_id_code + "'"
				+ ",name='" + name + "', surname='" + surname + "', birth_city='" + birth_city + "'"
				+ ",birth_city_province='" + birth_city_province + "', birth_date='" + birth_date + "'"
				+ ",residence_street='" + residence_street + "', house_number='" + house_number + "'"
				+ ",residence_city='" + residence_city + "', residence_city_cap='" + residence_city_cap + "'"
				+ ",residence_province='" + residence_province + "', phone_number='" + phone_number + "'"
				+ ",phone_number_2='" + phone_number_2 + "', e_mail='" + e_mail + "'"
				+ "WHERE id='"+ id +"'");
	}

	@Override
	public boolean deleteCustomerById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("DELETE FROM customer WHERE id='"+ id +"'");
		
	}

	@Override
	public ResultSet getMedicalsHistoryByCustomer(String id_customer) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM medical_history WHERE medical_history.id_customer = '"+ id_customer +"'");
	}

	@Override
	public ResultSet getMedicalHistoryById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM medical_history WHERE medical_history.id='" + id + "'");
	}

	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name) throws SQLException {
		
		return this.postMedicalHistory(id_customer, type, name, "");
	}

	@Override
	public boolean postMedicalHistory(String id_customer, String type, String name, String descriprion)
			throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("INSERT INTO medical_history(id_customer, type, name, description) "
				+ "VALUES ('"+ id_customer +"','" + type + "','" + name + "','" + descriprion + "')");
	}

	@Override
	public boolean deleteMedicalHistoryById(String id) throws SQLException {

		this.connectIfClosed();
		
		return conn.createStatement().execute("DELETE FROM medical_history WHERE medical_history.id='"+ id +"'");
	}

	@Override
	public ResultSet getAppointments() throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM appointment ORDER BY time");
	}

	@Override
	public ResultSet getAppointmentsByCustomerId(String id_customer) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM appointment WHERE appointment.id_customer='" + id_customer + "'");
	}

	@Override
	public ResultSet getAppointmentsByDoctorId(String id_doctor) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM appointment WHERE appointment.id_doctor='" + id_doctor + "'");
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String note) throws SQLException {
		
		return this.postAppointment(date, time, id_customer, id_doctor, id_treatment, 0, "", note);
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			String bill_number, String note) throws SQLException {
		
		return this.postAppointment(date, time, id_customer, id_doctor, id_treatment, 0, bill_number, note);
	}

	@Override
	public boolean postAppointment(String date, String time, String id_customer, String id_doctor, String id_treatment,
			int is_done, String bill_number, String note) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("INSERT INTO appointment(date, time, id_customer, id_doctor, id_treatment, is_done, bill_number, note) "
				+ "VALUES ('"+ date +"','" + time + "','" + id_customer + "','" + id_doctor + "','" + id_treatment + "',"
				+ "' " + is_done + " ',' " + bill_number + " ','" + note + "')");
	}

	@Override
	public boolean putSetAppointmentDoneById(long id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE appointment SET appointment.is_done='1' "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public boolean putUnsetAppointmentDoneById(long id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE appointment SET appointment.is_done='0' "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public boolean putAppointmentBillNumberById( long id , String bill_number)
			throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE appointment SET appointment.bill_number = '"+ bill_number +"' "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public boolean putAppointmentNoteById( long id, String note)
			throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE appointment SET appointment.note = '"+ note +"' "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public boolean putAppointmentTreatmentById( long id, String id_treatment)
			throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE appointment SET appointment.id_treatment = '"+ id_treatment +"' "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public boolean deleteAppointmentById(long id ) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("DELETE FROM appointment  "
									+" WHERE appointment.id = '"+id+"'");
	}

	@Override
	public ResultSet getTreatmentById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM treatment WHERE treatment.id='" + id + "'");
	}

	@Override
	public ResultSet getTreatmentsByCustomerId(String id_customer) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM treatment WHERE treatment.id_customer='" + id_customer + "'");
	}

	@Override
	public ResultSet getTreatmentsByBillNumber(String bill_number) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM treatment, appointment "
													+ "WHERE appointment.bill_number='" + bill_number + "'"
													+ "AND appointment.id_treatment=treatment.id");
	}

	@Override
	public boolean postTreatment(String name, String cost) throws SQLException {
		
		return this.postTreatment(name, "", cost);
	}

	@Override
	public boolean postTreatment(String name, String description, String cost) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("INSERT INTO treatment(name, description, cost) VALUES('"+ name +"','"+ description +"','" + cost + "')");
	}

	@Override
	public boolean deleteTreatmentById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("DELETE FROM treatment WHERE treatment.id = '" + id + "'");
	}

	@Override
	public ResultSet getEmployees() throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM employee");
	}

	@Override
	public ResultSet getEmployeesByName(String name) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM employee WHERE employee.name = '" + name + "'");
	}

	@Override
	public ResultSet getEmployeesBySurname(String surname) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM employee WHERE employee.surname = '" + surname + "'");
	}

	@Override
	public ResultSet getEmployeesByProfessionalRoleName(String professiona_role_name) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT employee.id, employee.name, employee.surname, employee.title,"
													+ "employee.birth_date, employee.phone_number, "
													+ "employee.phone_number_2, employee.e_mail "
													+ "FROM employee, has_professional_role, professional_role "
													+ "WHERE employee.id = has_professional_role.id_employee "
													+ "AND professional_role.id = has_professional_role.id_professional_role "
													+ "AND professional_role.name='" + professiona_role_name + "'" );
	}
	
	@Override
	public ResultSet getEmployeesByPartialKeyWordOverAllFields( String key_word ) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM employee WHERE "
				+ "name LIKE '%" + key_word + "%' OR surname LIKE '%"+ key_word + "%' "
				+ "OR title LIKE '%" + key_word + "%' OR birth_date LIKE '%" + key_word + "%' "
				+ "OR phone_number LIKE '%" + key_word + "%' "
				+ "OR phone_number_2 LIKE '%" + key_word + "%' OR e_mail LIKE '%" + key_word + "%'");
	}

	@Override
	public ResultSet getEmployeeById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM employee WHERE employee.id = '" + id + "'");
	}

	@Override
	public boolean postEmployee(String name, String surname, String title, String phone_number) throws SQLException {
		
		return this.postEmployee(name, surname, title, "", phone_number, "", "");
	}

	@Override
	public boolean putEmployeeById(String id, String name, String surname, String title, String birth_date,
			String phone_number, String phone_number_2, String e_mail) throws SQLException {

		this.connectIfClosed();
		
		return conn.createStatement().execute("UPDATE employee SET name='" + name + "'"
							+ ",surname='" + surname + "', title='" + title + "'"
							+ ",birth_date='" + birth_date + "', phone_number='" + phone_number + "'"
							+ ",phone_number_2='" + phone_number_2 + "', e_mail='" + e_mail + "'"
							+ "WHERE id='" + id + "'");
		
	}

	
	@Override
	public boolean postEmployee(String name, String surname, String title, String birth_date, String phone_number,
			String phone_number_2, String e_mail) throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().execute("INSERT INTO employee (name, surname, title, birth_date, phone_number, phone_number_2, e_mail)"
												+ "VALUES ('" + name + "','" + surname + "','" + title + "',"
														+ "'" + birth_date + "','" + phone_number + "','" + phone_number_2 + "','" + e_mail + "')");
	}

	@Override
	public boolean deleteEmployeeById(String id) throws SQLException {
		
		this.connectIfClosed();

		Statement state = conn.createStatement();
		
		// Prima elimina tutti i link dell'employee con i professional_role
		state.execute("DELETE FROM has_professional_role WHERE has_professional_role.id_employee = '" + id + "'");
		
		return state.execute("DELETE FROM employee WHERE employee.id = '" + id +  "'");
	}

	@Override
	public ResultSet getProfessionalRoles() throws SQLException {
		
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT* FROM professional_role");
	}
	
	@Override
	public ResultSet getProfessionalRolesAssociatedToIdEmployee( String id_employee ) throws SQLException{
	
		this.connectIfClosed();
		
		return conn.createStatement().executeQuery("SELECT professional_role.id, professional_role.name ,"
													+ " professional_role.description, employee.id AS id_employee"
													+ " FROM employee, has_professional_role, professional_role "
													+ "WHERE professional_role.id = has_professional_role.id_professional_role "
													+ "AND has_professional_role.id_employee = employee.id "
													+ "AND employee.id='" + id_employee + "'");
	
		
	}
	

	@Override
	public boolean postProfessionalRole(String name) throws SQLException {
		
		this.connectIfClosed();
		
		return this.postProfessionalRole(name, "");
	}

	@Override
	public boolean postProfessionalRole(String name, String description) throws SQLException {
		
		this.connectIfClosed();
		
		conn.createStatement();
		
		return conn.createStatement().execute("INSERT INTO professional_role(name, description) VALUES ('" + name + "','" + description + "')");
	}
	
	@Override
	public boolean putProfessionalRoleById(String id, String name, String description) throws SQLException {
		
		this.connectIfClosed();
		
		conn.createStatement();
		
		return conn.createStatement().execute("UPDATE professional_role "
				+ "SET name='" + name + "', description='" + description + "' "
				+ "WHERE id='" + id + "'");
	}
	

	@Override
	public boolean deleteProfessionalRoleById(String id) throws SQLException {
		
		this.connectIfClosed();
		
		Statement state = conn.createStatement();
		
		// Elimina anche tutte le associazione con i rispettivi employees con un certo professional role
		state.execute("DELETE FROM has_professional_role WHERE has_professional_role.id_professional_role = '" + id + "'");
		
		return state.execute("DELETE FROM professional_role WHERE professional_role.id = '" +id+ "'");
	}

	@Override
	public boolean postLinkEmployeeToProfessionalRole(String id_employee, String id_professional_role)
			throws SQLException {
		
		
		this.connectIfClosed();
		
		conn.createStatement();
		
		return conn.createStatement().execute("INSERT INTO has_professional_role( id_employee, id_professional_role)"
												+ "VALUES ('" + id_employee + "','" + id_professional_role + "')");
	}

	@Override
	public boolean deleteLinkEmployeeWithProfessionalRole(String id_employee, String id_professional_role)
			throws SQLException {
		
		this.connectIfClosed();
		
		conn.createStatement();
		
		return conn.createStatement().execute("DELETE FROM has_professional_role WHERE has_professional_role.id_employee = '" + id_employee + "'"
												+ "has_professional_role.id_professional_role = '" + id_professional_role + "'");
	}




}

	