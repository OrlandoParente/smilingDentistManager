package sdmc.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// https://github.com/stleary/JSON-java
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;


public class Utils {
	
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
	
	/*
	 * Scrive una stringa su un file
	 */
	public static void writeOnFile( String string, String path, boolean append) {
		
		try {
			
			FileWriter fw = new FileWriter( new File( path ) ,append );
			fw.write( string );
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
