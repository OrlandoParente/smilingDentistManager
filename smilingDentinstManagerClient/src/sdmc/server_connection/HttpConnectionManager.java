package sdmc.server_connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectionManager {

	private final String URL_SERVER = "http://localhost:8080/";
	public HttpConnectionManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void getCustomers() {
		
		String response = "";
		try {
			
			URL url = new URL(URL_SERVER + "getCustomers" );
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// set properties
			int responseCode = conn.getResponseCode();
			
			if( responseCode == HttpURLConnection.HTTP_OK ) {
				
				BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ));
				
				String line;
				
				while( ( line = in.readLine() ) != null )
					response+= line + " ";
				
			}
			
			System.out.println( response );
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
