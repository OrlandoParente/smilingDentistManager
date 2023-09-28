package sdmc.server_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpConnectionManager {

	// Oss.: Le costanti request che terminano con / significa che prevedono una PathVariable
	
	// Requesto for Customer
	public static final String GET_CUSTOMERS = "getCustomers";
	public static final String GET_CUSTOMER = "getCustomerById/";
	public static final String GET_CUSTOMER_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS = "getCustomersByPartialKeyWordOverAllFields/";
	public static final String POST_CUSTOMER = "postCustomer";
	public static final String PUT_CUSTOMER_BY_ID = "putCustomerById";
	public static final String DELETE_CUSTOMER = "deleteCustomer";
	
	// Request for Employee
	public static final String GET_EMPLOYEES = "getEmployees";
	public static final String GET_EMPLOYEES_BY_NAME = "getEmployeesByName/";
	public static final String GET_EMPLOYEES_BY_SURNAME = "getEmployeesBySurname/";
	public static final String GET_EMPLOYEES_BY_PROFESSIONAL_ROLE_NAME = "getEmployeesByProfessionalRoleName/";
	public static final String GET_EMPLOYEES_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS = "getEmployeesByPartialKeyWordOverAllFields/";
	public static final String GET_EMPLOYEE_BY_ID = "getEmployeeById/";
	public static final String POST_EMPLOYEE = "postEmployee";
	public static final String PUT_EMPLOYEE = "putEmployee";
	public static final String DELETE_EMPLOYEE_BY_ID = "deleteEmployeeById";
	
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
	public static final String PUT_PROFESSIONAL_ROLE_BY_ID = "putProfessionalRoleById";
	public static final String DELETE_PROFESSIONAL_ROLE_BY_ID = "deleteProfessionalRoleById";
	
	private final static String URL_SERVER = "http://localhost:8080/";
	
	public HttpConnectionManager() {

	}
	
	
	// GET REQUEST ------------------------------------------------------------------------------------------------
	public static RequestResponse doGet( String getRequest ) {
		
		String response = "";
		int responseCode = 0;
		try {
			
			URL url = new URL(URL_SERVER + getRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// set properties
			responseCode = conn.getResponseCode();
			
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
			System.out.println("HttpConnectionManaget -> DoPost --->  URL --->" + URL_SERVER + postRequest );

			
			URL url = new URL( URL_SERVER + postRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//conn.setDoInput(true);
					
			// Se ci sono dei parametri li scrive
			if( parameters != null && ! parameters.equals("") ) {
				
				// Check Message
				System.out.println("HttpConnectionManaget -> DoPost --->  IF parameters NOT NULL and NOT EMPTY --->" + parameters );
				
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
			System.out.println("HttpConnectionManaget -> DoPost ---> " + responseCode );
			System.out.println("HttpConnectionManaget -> DoPost -> parameters ---> " + parameters );
			
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
			
			URL url = new URL( URL_SERVER + putRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("PUT");
			
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
			
			URL url = new URL( URL_SERVER + deleteRequest );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
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
