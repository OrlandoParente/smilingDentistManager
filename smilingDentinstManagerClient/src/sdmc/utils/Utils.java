package sdmc.utils;

import java.io.FileReader;
import java.io.IOException;

// https://github.com/stleary/JSON-java
import org.json.JSONObject;

import java.io.BufferedReader;


public class Utils {
	
	// Definisco come costanti i path dei file contenenti informazioni di impostazioni del sistema
	public final static String BTNS_ITALIAN_LANGUANGE = "btn_strings_it.json";
	
	public Utils() {}
	
	/*
	 * Ritorna il contenuto di un file sottoforma di String
	 */
	public static String fileToString( String path ) {
		
		String text = "";
		
		try {
			
			BufferedReader reader = new BufferedReader( new FileReader( path ) );
			String line = reader.readLine();
			while( line != null ) {
				text += line;
				line = reader.readLine();
			}
			
			reader.close();
			
			// stampa di controllo 
			System.out.println("LETTURA LINE " + path + " --> " + text );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
		} 
		
		
		return text; 
	}

	
	/*
	 * Ritorna il contenuto di un file in un JSON Object 
	 */
	public static JSONObject fileToJSONObject( String path ) {
		
		
		String jsonString = "";
		jsonString += Utils.fileToString( path );
		
		JSONObject jsonObj = new JSONObject( jsonString );
		
		
		return jsonObj;
	}
	
	
	
	
}