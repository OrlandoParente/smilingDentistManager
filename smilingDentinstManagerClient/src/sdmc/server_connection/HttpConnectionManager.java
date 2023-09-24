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

	private final static String URL_SERVER = "http://localhost:8080/";
	
	public HttpConnectionManager() {
		// TODO Auto-generated constructor stub
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
