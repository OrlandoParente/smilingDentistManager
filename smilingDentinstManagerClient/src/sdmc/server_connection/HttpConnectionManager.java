package sdmc.server_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import sdmc.settings.Setting;

public class HttpConnectionManager {

	// Oss.: Le costanti request che terminano con / significa che prevedono una PathVariable
	
	//
	public static final String GET_MAX_ID_FROM_TABLE = "getMaxIdFromTable/";
	
	// Login
	public static final String DO_LOGIN = "api/login";
	
	// Requesto for Customer
	public static final String GET_CUSTOMERS = "getCustomers";
	public static final String GET_CUSTOMER_BY_ID = "getCustomerById/";
	public static final String GET_CUSTOMER_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS = "getCustomersByPartialKeyWordOverAllFields/";
	public static final String POST_CUSTOMER = "postCustomer";
	public static final String PUT_CUSTOMER_BY_ID = "putCustomer";
	public static final String DELETE_CUSTOMER = "deleteCustomer";
	public static final String GET_MAX_ID_CUSTOMER = "getMaxIdCustomer";
	
	// Request for Employee
	public static final String GET_EMPLOYEES = "getEmployees";
	public static final String GET_PROFESSIONAL_ROLES_ASSOCIATED_TO_ID_EMPLOYEE = "getProfessionalRolesAssociatedToIdEmployee/";
	public static final String GET_EMPLOYEES_BY_NAME = "getEmployeesByName/";
	public static final String GET_EMPLOYEES_BY_SURNAME = "getEmployeesBySurname/";
	public static final String GET_EMPLOYEES_BY_PROFESSIONAL_ROLE_NAME = "getEmployeesByProfessionalRoleName/";
	public static final String GET_EMPLOYEES_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS = "getEmployeesByPartialKeyWordOverAllFields/";
	public static final String GET_EMPLOYEE_BY_ID = "getEmployeeById/";
	public static final String POST_EMPLOYEE = "postEmployee";
	public static final String PUT_EMPLOYEE = "putEmployee";
	public static final String DELETE_EMPLOYEE_BY_ID = "deleteEmployeeById";
	public static final String GET_MAX_ID_EMPLOYEE = "getMaxIdEmployee";
	
	// Request for medicalHistory
	public static final String GET_MEDICALS_HISTORY_BY_CUSTOMER = "getMedicalsHistoryByCustomer/";
	public static final String GET_MEDICAL_HISTORY_BY_ID = "getMedicalHistoryById/";
	public static final String POST_MEDICAL_HISTORY = "postMedicalHistory";
	public static final String DELETE_MEDICAL_HISTORY_BY_ID = "deleteMedicalHistoryById";
	
	// Request for TreatmentRestController
	public static final String GET_TREATMENT_BY_ID = "getTreatmentById/";
	public static final String GET_TREATMENTS_BY_ID = "getTreatmentsById/";
	public static final String GET_TREATMENTS_BY_BILL_NUMBER = "getTreatmentsByBillNumber/";
	public static final String POST_TREATMENT = "postTreatment";
	public static final String DELETE_TREATMENT_BY_IS = "deleteTreatmentById";
	
	// Request for hasProfessionalRole
	public static final String POST_LINK_EMPLOYEE_TO_PROFESSIONAL_ROLE = "postLinkEmployeeToProfessionalRole";
	public static final String DELETE_LINK_EMPLOYEE_WITH_PROFESSIONAL_ROLE = "deleteLinkEmployeeWithProfessionalRole";
	
	// Request for Professional Role
	public static final String GET_PROFESSIONAL_ROLES = "getProfessionalRoles";
	public static final String POST_PROFESSIONAL_ROLE = "postProfessionalRole";
	public static final String PUT_PROFESSIONAL_ROLE_BY_ID = "putProfessionalRole";
	public static final String DELETE_PROFESSIONAL_ROLE_BY_ID = "deleteProfessionalRoleById";
	
	// Request for Appointment
	public static final String GET_APPOINTMENTS = "getAppointments";
	public static final String GET_APPOINTMENTS_BY_CUSTOMER_ID ="getAppointmentsByCustomerId/";
	public static final String GET_APPOINTMENTS_BY_DOCTOR_ID = "getAppointmentsByDoctorId/";
	public static final String POST_APPOINTMENT = "postAppointment";
	public static final String PUT_SET_APPOINTMENT_DONE_BY_ID =	"putSetAppointmentDoneById";
	public static final String PUT_UNSET_APPOINTMENT_DONE_BY_ID = "putUnsetAppointmentDoneById";
	public static final String PUT_APPOINTMENT_BILL_NUMBER_BY_ID = "putAppointmentBillNumberById";
	public static final String PUT_APPOINTMENT_NOTE_BY_ID = "putAppointmentNoteById";
	public static final String PUT_APPOINTMENT_TREATMENT_BY_ID = "putAppointmentTreatmentById";
	public static final String DELETE_APPOINTMENT_BY_ID = "deleteAppointmentById";
	
	// Prendo il server dai settings
	// private final static String URL_SERVER = "http://localhost:8080/";
	
	// JSON WEB TOKEN (si potrebbe salvare nei settings)
	private static String jwt;
	
	public HttpConnectionManager() {
		
	}
	
	private static String getJwt() {
		if( HttpConnectionManager.jwt == null ) {
			String username = Setting.getSettings().getUsername();
			String password = Setting.getSettings().getPassword();
			
			// <------ Da gestire se il login non va a buon fine 
			RequestResponse login = HttpConnectionManager.doLogin(username, password);
			HttpConnectionManager.jwt = login.getResponseString();
		}
		
		return HttpConnectionManager.jwt;
	}
	
	// DO LOGIN ###################################################################################################
	// get the JWT
	public static RequestResponse doLogin( String username, String password ) {
		String response = "";
		String parameters = "username=" + username + "&password=" + password;
		int responseCode = 0;
		
		try {
			
			// Check Message
			System.out.println("HttpConnectionManager -> DoLogin --->  URL --->" + Setting.getSettings().getServer() + HttpConnectionManager.DO_LOGIN );

			
			URL url = new URL( Setting.getSettings().getServer() + HttpConnectionManager.DO_LOGIN );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//conn.setDoInput(true);
					
			// Se ci sono dei parametri li scrive
			if( parameters != null && ! parameters.equals("") ) {
				
				// Check Message
				System.out.println("HttpConnectionManager -> DoLogin --->  IF parameters NOT NULL and NOT EMPTY --->" + parameters );
				
				/*
				// SECONDO METODO PER SCRIVERE I PARAMETRI
				BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() , "UTF-8" ));
				writer.write(parameters);
				writer.flush();
				writer.close();
				*/
				
				
				 OutputStream os = conn.getOutputStream();
				 os.write( parameters.getBytes() );
				 os.flush();
				 os.close();
				
				
			}

			
			responseCode = conn.getResponseCode();
			
			// Check Message
			System.out.println("HttpConnectionManager -> DoPost ---> " + responseCode );
			System.out.println("HttpConnectionManager -> DoPost -> parameters ---> " + parameters );
			
			if( responseCode == HttpsURLConnection.HTTP_OK ) {
				
				// Check Message
				System.out.println("HttpConnectionManaget -> DoPost ---> " + responseCode );
				
				BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
				String line = null;
				
				while( (line = in.readLine()) != null ) {
					response += line;
				}
			}
			
			
			// chiudo la connessione
			conn.disconnect();
			
		} catch ( ConnectException ce ) {
			
			response = ce.getMessage();
			responseCode = RequestResponse.CONNECTION_REFUSED;
			
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		return new RequestResponse(response, responseCode);

	}
	
	// ############################################################################################################
	
	
	// GET REQUEST ------------------------------------------------------------------------------------------------
	public static RequestResponse doGet( String getRequest ) {
		
		String response = "";
		int responseCode = 0;
		try {
			
			URL url = new URL(Setting.getSettings().getServer() + getRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + HttpConnectionManager.getJwt() );

			// set properties
			responseCode = conn.getResponseCode();
			
			// Check Message
			System.out.println("HttpConnectionManager -> DoGet ->" + Setting.getSettings().getServer() + getRequest );
			
			if( responseCode == HttpURLConnection.HTTP_OK ) {
				
				BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ));
				
				String line;
				
				while( ( line = in.readLine() ) != null )
					response+= line + " ";
			
				in.close();
			}
			
			System.out.println( response );
			
			// chiudo la connessione
			conn.disconnect();
			
		}  catch ( ConnectException ce ) {
			
			response = ce.getMessage();
			responseCode = RequestResponse.CONNECTION_REFUSED;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return new RequestResponse(response, responseCode);
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// POST REQUEST -----------------------------------------------------------------------------------------------
	public static RequestResponse doPost( String postRequest ) {
		return HttpConnectionManager.doPost(postRequest, null );
	}
	
	public static RequestResponse doPost( String postRequest , String parameters ) {
		String response = "";
		int responseCode = 0;
		
		try {
			
			// Check Message
			System.out.println("HttpConnectionManager -> DoPost --->  URL --->" + Setting.getSettings().getServer() + postRequest );

			
			URL url = new URL( Setting.getSettings().getServer() + postRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + HttpConnectionManager.getJwt() );
			conn.setDoOutput(true);
			//conn.setDoInput(true);
					
			// Se ci sono dei parametri li scrive
			if( parameters != null && ! parameters.equals("") ) {
				
				// Check Message
				System.out.println("HttpConnectionManager -> DoPost --->  IF parameters NOT NULL and NOT EMPTY --->" + parameters );
				
				/*
				// SECONDO METODO PER SCRIVERE I PARAMETRI
				BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( conn.getOutputStream() , "UTF-8" ));
				writer.write(parameters);
				writer.flush();
				writer.close();
				*/
				
				
				 OutputStream os = conn.getOutputStream();
				 os.write( parameters.getBytes() );
				 os.flush();
				 os.close();
				
				
			}

			
			responseCode = conn.getResponseCode();
			
			// Check Message
			System.out.println("HttpConnectionManager -> DoPost ---> " + responseCode );
			System.out.println("HttpConnectionManager -> DoPost -> parameters ---> " + parameters );
			
			if( responseCode == HttpsURLConnection.HTTP_OK ) {
				
				// Check Message
				System.out.println("HttpConnectionManaget -> DoPost ---> " + responseCode );
				
				BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
				String line = null;
				
				while( (line = in.readLine()) != null ) {
					response += line;
				}
			}
			
			
			// chiudo la connessione
			conn.disconnect();
			
		} catch ( ConnectException ce ) {
			
			response = ce.getMessage();
			responseCode = RequestResponse.CONNECTION_REFUSED;
			
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		return new RequestResponse(response, responseCode);
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	// PUT REQUEST ------------------------------------------------------------------------------------------------
	public static int doPut( String putRequest, String parameters ) {
		
		int responseCode = 0;
		
		
		try {
			
			URL url = new URL( Setting.getSettings().getServer() + putRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Authorization", "Bearer " + HttpConnectionManager.getJwt() );
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			// conn.setDoInput(true);
			conn.setDoOutput(true);
			
			// Inserisco i parametri ----
			OutputStream os = conn.getOutputStream();
			os.write( parameters.getBytes() );
			os.flush();
			os.close();
			// --------------------------
			
			responseCode = conn.getResponseCode();
			
			conn.disconnect();
			
		} catch ( ConnectException ce ) {
			ce.printStackTrace();
			responseCode = RequestResponse.CONNECTION_REFUSED;
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
		}
		
		return responseCode;
	}
	
	// ------------------------------------------------------------------------------------------------------------
	
	
	// DELTE REQUEST ----------------------------------------------------------------------------------------------
	public static int doDelete( String deleteRequest, String parameters ) {
		
		int responseCode = 0;
		
		try {
			
			URL url = new URL( Setting.getSettings().getServer() + deleteRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Authorization", "Bearer " + HttpConnectionManager.getJwt() );
			conn.setRequestMethod("DELETE");
			// conn.setDoInput(true);
			conn.setDoOutput(true);
			
			// Inserisco i parametri ----
			OutputStream os = conn.getOutputStream();
			os.write( parameters.getBytes() );
			os.flush();
			os.close();
			// --------------------------
			
			responseCode = conn.getResponseCode();
			
			conn.disconnect();
			
		} catch ( ConnectException ce ) {
			ce.printStackTrace();
			responseCode = RequestResponse.CONNECTION_REFUSED;
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
		}
		
		return responseCode;
	}
	// ------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
}
