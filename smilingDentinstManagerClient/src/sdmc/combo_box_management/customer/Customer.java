package sdmc.combo_box_management.customer;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.combo_box_management.professional_role.ProfessionalRole;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;

public class Customer {

	private int id;
	private String taxIdCode;  // codice fiscale
	private String name;
	private String surname;
	private String birthCity;
	private String birthCityProvince;	// preferibilmente la sigla provincia, ma lascio spazio per il nome completo
	private String birthDate; 
	private String residenceStreet;
	private String houseNumber;
	private String residenceCity;
	private String residenceCityCap;
	private String residenceProvince; // sigla provincia preferibilmente (?)
	private String phoneNumber;
	private String phoneMumber2;// Generalmente telefono di casa
	private String eMail;
	
	
	// Costruttore con solo campi essenziali
	public Customer( int id, String name, String surname, String phoneNumber ) {
		this( id, null , name, surname, null, null, null, null, null, null, null, null, phoneNumber, null, null );
	}
	
	public Customer(int id, String taxIdCode, String name, String surname, String birthCity, String birthCityProvince,
			String birthDate, String residenceStreet, String houseNumber, String residenceCity, String residenceCityCap,
			String residenceProvince, String phoneNumber, String phoneMumber2, String eMail) {
		super();
		this.id = id;
		this.taxIdCode = taxIdCode;
		this.name = name;
		this.surname = surname;
		this.birthCity = birthCity;
		this.birthCityProvince = birthCityProvince;
		this.birthDate = birthDate;
		this.residenceStreet = residenceStreet;
		this.houseNumber = houseNumber;
		this.residenceCity = residenceCity;
		this.residenceCityCap = residenceCityCap;
		this.residenceProvince = residenceProvince;
		this.phoneNumber = phoneNumber;
		this.phoneMumber2 = phoneMumber2;
		this.eMail = eMail;
	}
	
	// Recupera la lista dei customers presenti nel db
	public static Customer []  getCustomerArray() {
		
		RequestResponse response = HttpConnectionManager.doGet( HttpConnectionManager.GET_CUSTOMERS );
		
		// Messaggio di controllo
		System.out.println( "sdmc.combo_box_management.customer.Customer -> getCustomerArray -> responseCode ---> " + response.getResponseCode() );
		
		// Evita errori nella creazione di JSON Array in caso di risposta vuota
		if( response.getResponseString() == null || response.getResponseCode() != HttpsURLConnection.HTTP_OK )
			response.setResponseString("[]");
		
		JSONArray jsonArrCustomers = new JSONArray( response.getResponseString() );
		
		// individuazione numero elementi restituiti
		int arrLength = jsonArrCustomers.length() ;
		
		Customer [] customerArr = new Customer[ arrLength ];
		
		//
		// professionalRoleArr[0] = null; 
		
		for( int i = 0; i < arrLength; i ++ ) {
			
			JSONObject jo = jsonArrCustomers.getJSONObject(i);
			customerArr [i] = new Customer( jo.getInt("id"), jo.getString("taxIdCode"), jo.getString("name"),
											jo.getString("surname"), jo.getString("birthCity"),
											jo.getString("birthCityProvince"), jo.getString("birthDate"),
											jo.getString("residenceStreet"), jo.getString("houseNumber"), 
											jo.getString("residenceCity"), jo.getString("residenceCityCap"),
											jo.getString("residenceProvince"), jo.getString("phoneNumber"),
											jo.getString("phoneNumber2"), jo.getString("eMail"));
		}
		
		return customerArr;
	}
	
	// Recupera la lista dei customers presenti nel db inserendo null come primo elemento
	// Utile da passare alla combo box
	public static Customer []  getCustomerArrayWithFirstNull() {
	
		Customer  [] customerArr = Customer.getCustomerArray();
		Customer [] newArr = new Customer[ customerArr.length + 1 ];
		
		newArr[0] = null;
		
		for( int i = 1; i < customerArr.length + 1 ; i ++ ) {
			
			newArr[i] = customerArr[i - 1];
			
		}
		
		return newArr;
	}
		
	
	@Override
	public boolean equals(Object obj) {
		
		if( obj instanceof Customer ) {
			
			Customer customer = (Customer) obj;
		
			if( this.getId() == customer.getId() )
				return true;
		}
		
		return false;
	}
	
	// GETTERS AND SETTERS
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaxIdCode() {
		return taxIdCode;
	}
	public void setTaxIdCode(String taxIdCode) {
		this.taxIdCode = taxIdCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getBirthCity() {
		return birthCity;
	}
	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}
	public String getBirthCityProvince() {
		return birthCityProvince;
	}
	public void setBirthCityProvince(String birthCityProvince) {
		this.birthCityProvince = birthCityProvince;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getResidenceStreet() {
		return residenceStreet;
	}
	public void setResidenceStreet(String residenceStreet) {
		this.residenceStreet = residenceStreet;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getResidenceCity() {
		return residenceCity;
	}
	public void setResidenceCity(String residenceCity) {
		this.residenceCity = residenceCity;
	}
	public String getResidenceCityCap() {
		return residenceCityCap;
	}
	public void setResidenceCityCap(String residenceCityCap) {
		this.residenceCityCap = residenceCityCap;
	}
	public String getResidenceProvince() {
		return residenceProvince;
	}
	public void setResidenceProvince(String residenceProvince) {
		this.residenceProvince = residenceProvince;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneMumber2() {
		return phoneMumber2;
	}
	public void setPhoneMumber2(String phoneMumber2) {
		this.phoneMumber2 = phoneMumber2;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
	
	
	
}
