package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
import sdmc.main.MainMenuFrame;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;

public class AddCustomerActionListener implements ActionListener {

	private AddCustomerFrame addCustomerFrame;
	
	// Action commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String ADD_CUSTOMER = "ADD_CUSTOMER";
	
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
	
	
	
	public AddCustomerActionListener( AddCustomerFrame addCustomerFrame ) {
		this.addCustomerFrame = addCustomerFrame;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			System.out.println( "AddCustomerActionListener ----> " + e.getActionCommand() );
			
			new MainMenuFrame();
			this.addCustomerFrame.dispose();
			
			break;
			
		case ADD_CUSTOMER:
			
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
				JOptionPane.showConfirmDialog( addCustomerFrame, "NOME, COGNOME e NUMERO DI TELEFONO sono campi obblicatori", 
						"Message",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE );
				
				break; // esci 
			} else {
				
				// Generazione stringa parametri
				String parameters = "tax_id_code=" + taxIdCode + "&name=" + name + "&surname=" + surname + "&birth_city=" + birthCity
								+ "&birth_city_province=" + birthCityProvince + "&birth_date=" + birthDate 
								+ "&residence_street=" + residenceStreet + "&house_number=" + houseNumber 
								+ "&residence_city=" + residenceCity + "&residence_city_cap=" + residenceCityCap 
								+ "&residence_province=" + residenceProvince + "&phone_number=" + phoneNumber 
								+ "&phone_number_2=" + phoneNumber2 + "&e_mail=" + eMail ;
				
				// Generazione stringa parametri
				// String parameters = "name=" + name + "&surname=" + surname + "&phone_number=" + phoneNumber ;
				

				// System.out.println( " -------------------->>>>>>>>>>>>>>>>> " + HttpConnectionManager.doPost("postCustomer") );
				RequestResponse result = HttpConnectionManager.doPost("postCustomer", parameters );
				
				System.out.println("Inserimento customer completo ----> " + result.getResponseString() );
				
				if( result.getResponseCode() == HttpsURLConnection.HTTP_OK ) {
					
					// Messaggio di conferma per l'utente
					JOptionPane.showConfirmDialog( addCustomerFrame, "Nuovo cliente inserito", 
							"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE );
					
					// Svuota i campi dai dati immessi
					emptyTextFields();
					
				} else {
					
					// Messaggio di conferma per l'utente
					JOptionPane.showConfirmDialog( addCustomerFrame, "Connessione con il server non riuscita", 
							"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
					
				}
				
				

				
			 }
			
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
