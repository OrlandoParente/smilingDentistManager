package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import sdmc.main.MainMenuFrame;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;
import sdmc.settings.Setting;
import sdmc.utils.MessageJsonKey;
import sdmc.utils.Utils;

public class AddCustomerActionListener implements ActionListener {

	private JSONObject messageStrings;
	
	private AddCustomerFrame addCustomerFrame;
	
	// Action commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String ADD_CUSTOMER = "ADD_CUSTOMER";
	
	//
	public final static String BACK_TO_SEARCH_CUSTOMER = "BACK_TO_SEARCH_CUSTOMER";
	public final static String DELETE = "DELETE";
	public final static String EDIT = "EDIT";
	
	// Dati per l'inserimento del nuovo customer 
	
	private String taxIdCode;
	private String name;
	private String surname;
	private String birthCity;
	private String birthCityProvince;
	private String birthDate;
	private String residenceStreet;
	private String houseNumber;
	private String residenceCity;
	private String residenceCityCap;
	private String residenceProvince;
	private String phoneNumber;
	private String phoneNumber2;
	private String eMail;
	
	//
	private String param;
	private int resultCode;
	private String parameters;
	
	
	
	public AddCustomerActionListener( AddCustomerFrame addCustomerFrame ) {
		this.addCustomerFrame = addCustomerFrame;
		
		messageStrings = Utils.fileToJSONObject( Setting.getSettings().getMessageLanguageFile() );
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			// check message
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() );
			
			new MainMenuFrame();
			this.addCustomerFrame.dispose();
			
			break;
			
		case ADD_CUSTOMER:
			
			// check message
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() );
			
			// recupero contenuto textFields
			taxIdCode = addCustomerFrame.getTextFieldTaxIdCode().getText();
			name = addCustomerFrame.getTextFieldName().getText();
			surname = addCustomerFrame.getTextFieldSurname().getText();
			birthCity = addCustomerFrame.getTextFieldBirthCity().getText();
			birthCityProvince = addCustomerFrame.getTextFieldBirthCityProvince().getText();
			birthDate = addCustomerFrame.getTextFieldBirthDate().getText();
			residenceStreet = addCustomerFrame.getTextFieldResidenceStreet().getText();
			houseNumber = addCustomerFrame.getTextFieldHouseNumber().getText();
			residenceCity = addCustomerFrame.getTextFieldResidenceCity().getText();
			residenceCityCap = addCustomerFrame.getTextFieldResidenceCityCap().getText();
			residenceProvince = addCustomerFrame.getTextFieldResidenceProvince().getText();
			phoneNumber = addCustomerFrame.getTextFieldPhoneNumber().getText();
			phoneNumber2 = addCustomerFrame.getTextFieldPhoneNumber2().getText();
			eMail = addCustomerFrame.getTextFieldEMail().getText();
			
			// Controllo presenza delle informazioni essenziali 
			if( name.equals("") || surname.equals("") || phoneNumber.equals("") ) {
				JOptionPane.showConfirmDialog( addCustomerFrame,  messageStrings.getString( MessageJsonKey.ERROR_MISS_ESSENTIAL_FIELDS ), 
						"Message",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE );
				
				break; // esci 
			} else {
				
				// Generazione stringa parametri
				parameters = "tax_id_code=" + taxIdCode + "&name=" + name + "&surname=" + surname + "&birth_city=" + birthCity
								+ "&birth_city_province=" + birthCityProvince + "&birth_date=" + birthDate 
								+ "&residence_street=" + residenceStreet + "&house_number=" + houseNumber 
								+ "&residence_city=" + residenceCity + "&residence_city_cap=" + residenceCityCap 
								+ "&residence_province=" + residenceProvince + "&phone_number=" + phoneNumber 
								+ "&phone_number_2=" + phoneNumber2 + "&e_mail=" + eMail ;
				
				// Generazione stringa parametri
				// String parameters = "name=" + name + "&surname=" + surname + "&phone_number=" + phoneNumber ;
				

				// System.out.println( " -------------------->>>>>>>>>>>>>>>>> " + HttpConnectionManager.doPost("postCustomer") );
				RequestResponse result = HttpConnectionManager.doPost( HttpConnectionManager.POST_CUSTOMER, parameters );
				
				System.out.println("Inserimento customer completo ----> " + result.getResponseString() );
				
				if( result.getResponseCode() == HttpsURLConnection.HTTP_OK ) {
					
					// Messaggio di conferma per l'utente
					JOptionPane.showConfirmDialog( addCustomerFrame, messageStrings.getString( MessageJsonKey.NEW_CUSTOMER_ADDED ), 
							"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE );
					
					// Svuota i campi dai dati immessi
					emptyTextFields();
					
				} else {
					
					// Messaggio di Errore
					JOptionPane.showConfirmDialog( addCustomerFrame, messageStrings.getString( MessageJsonKey.SERVER_CONNECTION_FAILED ), 
							"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
					
				}
				
				

				
			 }
			
			break;
			
		case BACK_TO_SEARCH_CUSTOMER:
			
			// check message
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() );

			new SearchCustomerFrame();
			addCustomerFrame.dispose();
			
			break;
			
		case EDIT:
			
			// check message
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() +" -> id = " + addCustomerFrame.getIdCustomer() );
			
			// recupero contenuto textFields
			taxIdCode = addCustomerFrame.getTextFieldTaxIdCode().getText();
			name = addCustomerFrame.getTextFieldName().getText();
			surname = addCustomerFrame.getTextFieldSurname().getText();
			birthCity = addCustomerFrame.getTextFieldBirthCity().getText();
			birthCityProvince = addCustomerFrame.getTextFieldBirthCityProvince().getText();
			birthDate = addCustomerFrame.getTextFieldBirthDate().getText();
			residenceStreet = addCustomerFrame.getTextFieldResidenceStreet().getText();
			houseNumber = addCustomerFrame.getTextFieldHouseNumber().getText();
			residenceCity = addCustomerFrame.getTextFieldResidenceCity().getText();
			residenceCityCap = addCustomerFrame.getTextFieldResidenceCityCap().getText();
			residenceProvince = addCustomerFrame.getTextFieldResidenceProvince().getText();
			phoneNumber = addCustomerFrame.getTextFieldPhoneNumber().getText();
			phoneNumber2 = addCustomerFrame.getTextFieldPhoneNumber2().getText();
			eMail = addCustomerFrame.getTextFieldEMail().getText();
			
			
			// Generazione stringa parametri
			parameters = "id="+ addCustomerFrame.getIdCustomer() + "&tax_id_code=" + taxIdCode + "&name=" + name 
							+ "&surname=" + surname + "&birth_city=" + birthCity
							+ "&birth_city_province=" + birthCityProvince + "&birth_date=" + birthDate 
							+ "&residence_street=" + residenceStreet + "&house_number=" + houseNumber 
							+ "&residence_city=" + residenceCity + "&residence_city_cap=" + residenceCityCap 
							+ "&residence_province=" + residenceProvince + "&phone_number=" + phoneNumber 
							+ "&phone_number_2=" + phoneNumber2 + "&e_mail=" + eMail ;
		
			
			resultCode = HttpConnectionManager.doPut(HttpConnectionManager.PUT_CUSTOMER_BY_ID, parameters);
			
			if(  resultCode == HttpsURLConnection.HTTP_OK ) {
				
				// Messaggio di conferma per l'utente
				JOptionPane.showConfirmDialog( addCustomerFrame,  messageStrings.getString( MessageJsonKey.CUSTOMER_DATA_SUCCESSFUL_EDITED ),
						"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE );
				
				
			} else {
				
				// Messaggio di Errore
				JOptionPane.showConfirmDialog( addCustomerFrame, messageStrings.getString( MessageJsonKey.SERVER_CONNECTION_FAILED ) , 
						"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
				
			}
			
			new SearchCustomerFrame();
			addCustomerFrame.dispose();
			
			
			
			break;
			
		case DELETE:
			
			// check message
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() +" -> id = " + addCustomerFrame.getIdCustomer() );
			
			param = "id="+ addCustomerFrame.getIdCustomer();
			
			resultCode = HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_CUSTOMER, param);
			
			if(  resultCode == HttpsURLConnection.HTTP_OK ) {
				
				// Messaggio di conferma per l'utente
				JOptionPane.showConfirmDialog( addCustomerFrame, messageStrings.getString( MessageJsonKey.CUSTOMER_SUCCESSFUL_DELETED ) , 
						"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE );
				
				
			} else {
				
				// Messaggio di Errore
				JOptionPane.showConfirmDialog( addCustomerFrame, messageStrings.getString( MessageJsonKey.SERVER_CONNECTION_FAILED ) , 
						"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
				
			}
			
			new SearchCustomerFrame();
			addCustomerFrame.dispose();
			
			
			
			break;
		
		}
		
	}
	
	private void  emptyTextFields() {
		addCustomerFrame.getTextFieldTaxIdCode().setText("");
		addCustomerFrame.getTextFieldName().setText("");
		addCustomerFrame.getTextFieldSurname().setText("");
		addCustomerFrame.getTextFieldBirthCity().setText("");
		addCustomerFrame.getTextFieldBirthCityProvince().setText("");
		addCustomerFrame.getTextFieldBirthDate().setText("");
		addCustomerFrame.getTextFieldResidenceStreet().setText("");
		addCustomerFrame.getTextFieldHouseNumber().setText("");
		addCustomerFrame.getTextFieldResidenceCity().setText("");
		addCustomerFrame.getTextFieldResidenceCityCap().setText("");
		addCustomerFrame.getTextFieldResidenceProvince().setText("");
		addCustomerFrame.getTextFieldPhoneNumber().setText("");
		addCustomerFrame.getTextFieldPhoneNumber2().setText("");
		addCustomerFrame.getTextFieldEMail().setText("");
	}

}
