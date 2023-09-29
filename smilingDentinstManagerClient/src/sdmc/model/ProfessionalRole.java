package sdmc.model;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;

public class ProfessionalRole {
	
	private int id;
	private String name;
	private String description;
	
	public ProfessionalRole(int id, String name) {
		this(id, name, null);
	}
	
	public ProfessionalRole(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	// Recupera la lista dei professional roles presenti nel db
	public static ProfessionalRole []  getProfessionalRoleArray() {
		
		RequestResponse response = HttpConnectionManager.doGet("getProfessionalRoles");
		
		// Messaggio di controllo
		System.out.println( "ProfessionalRole -> getProfessionalRoleArray -> responseCode ---> " + response.getResponseCode() );
		
		// Evita errori nella creazione di JSON Array in caso di risposta vuota
		if( response.getResponseString() == null || response.getResponseCode() != HttpsURLConnection.HTTP_OK )
			response.setResponseString("[]");
		
		JSONArray jsonArrProfessionalRoles = new JSONArray( response.getResponseString() );
		
		// individuazione numero elementi restituiti
		int arrLength = jsonArrProfessionalRoles.length() ;
		
		ProfessionalRole [] professionalRoleArr = new ProfessionalRole[ arrLength ];
		
		//
		// professionalRoleArr[0] = null; 
		
		for( int i = 0; i < arrLength; i ++ ) {
			
			JSONObject jo = jsonArrProfessionalRoles.getJSONObject(i);
			professionalRoleArr[i] = new ProfessionalRole( jo.getInt("id"), jo.getString("name"), jo.getString("description") );
		}
		
		return professionalRoleArr;
	}
	
	// Recupera la lista dei professional roles presenti nel db inserendo null come primo elemento
	// Utile da passare alla combo box
	public static ProfessionalRole []  getProfessionalRoleArrayWithFirstNull() {
	
		ProfessionalRole [] arrProfessionalRole = ProfessionalRole.getProfessionalRoleArray();
		ProfessionalRole [] newArr = new ProfessionalRole[ arrProfessionalRole.length + 1 ];
		
		newArr[0] = null;
		
		for( int i = 1; i < arrProfessionalRole.length + 1 ; i ++ ) {
			
			newArr[i] = arrProfessionalRole[i - 1];
			
		}
		
		return newArr;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
