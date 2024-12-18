package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import sdmc.combo_box_management.professional_role.ProfessionalRole;
import sdmc.main.MainMenuFrame;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.settings.Setting;
import sdmc.utils.MessageJsonKey;
import sdmc.utils.Utils;

public class AddEmployeeActionListener implements ActionListener {
	
	private AddEmployeeFrame addEmployeeFrame;
	
	// Action commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String ADD_EMPLOYEE = "ADD_EMPLOYEE";
	public final static String ADD_NEW_PROFESSIONAL_ROLE= "ADD_NEW_PROFESSIONAL_ROLE";
	public final static String DELETE_A_PROFESSIONAL_ROLE_PANEL = "DELETE_A_PROFESSIONAL_ROLE_PANEL";
	
	//
	public final static String BACK_TO_SEARCH_EMPLOYEE = "BACK_TO_SEARCH_EMPLOYEE";
	public final static String DELETE_EMPLOYEE = "DELETE_EMPLOYEE";
	public final static String EDIT_EMPLOYEE = "EDIT_EMPLOYEE";
	
	//
	private JSONObject messageStrings;
	
	private String params;
	
	String name;
	String surname;
	String title;
	String birth_date;
	String phone_number;
	String phone_number_2;
	String e_mail;
	
	private int [] arrProfessionalRoleIds;
	
	public AddEmployeeActionListener( AddEmployeeFrame addEmployeeFrame ) {
		
		this.addEmployeeFrame = addEmployeeFrame;
	
		messageStrings = Utils.fileToJSONObject( Setting.getSettings().getMessageLanguageFile() );
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			new MainMenuFrame();
			addEmployeeFrame.dispose();
			
			break;
			
		case ADD_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			this.addEmployee();
			
			// Svuota i fields
			addEmployeeFrame.getTextFieldName().setText("");
			addEmployeeFrame.getTextFieldSurname().setText("");
			addEmployeeFrame.getTextFieldTitle().setText("");
			addEmployeeFrame.getTextFieldBirthDate().setText("");
			addEmployeeFrame.getTextFieldPhoneNumber().setText("");
			addEmployeeFrame.getTextFieldPhoneNumber2().setText("");
			addEmployeeFrame.getTextFieldEMail().setText("");
			
			addEmployeeFrame.resetProfessionalRolePanel();
			
			break;
			
		case ADD_NEW_PROFESSIONAL_ROLE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
						
			addEmployeeFrame.incrementNumProfessionalRole();
			
			break;
			
		case DELETE_A_PROFESSIONAL_ROLE_PANEL:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
						
			addEmployeeFrame.decrementNumProfessionalRole();
			
			
			break;
			
		case BACK_TO_SEARCH_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			addEmployeeFrame.dispose();
			
			new SearchEmployeeFrame();
			
			break;
			
			
		case EDIT_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			// Oss.: In questo modo l'employee cambia id, ma dato che è un dato tecnico che non viene mai mostrato all'utente
			// e a questo non interessa, allora posso dedurre che non è importante
			// facendo in questo modo ( elimina, ri aggiungi ) si semplifica e snellisce notevolemente il codice
			// perchè ci permette di non dover gestire l'update dei professional role che, non essendo un numero fisso
			// si sarebbero dovuti gestire comunque con più di una query sql
			
			this.deleteEmployee();
			
			this.addEmployee();
			
			addEmployeeFrame.dispose();
			
			new SearchEmployeeFrame();
			
			break;
			
			
		case DELETE_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			this.deleteEmployee();
			
			addEmployeeFrame.dispose();
			
			new SearchEmployeeFrame();
			
			
			break;
			
		// Prende in input l'action command del comboBox selctProfessionalRole
		// il quale corrisponde all'indice i dell'array int []  arrProfessionalRoleIds
		default:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			int i = Integer.parseInt( e.getActionCommand() );
			
			ProfessionalRole selectedProfessionalRole = (ProfessionalRole)(( JComboBox<ProfessionalRole> ) e.getSource()).getSelectedItem();
			
			addEmployeeFrame.setSelectedProfessionalRoleInTheProfessionalRoleArray( i, selectedProfessionalRole.getId() );
			
			break;	
			
			
			
		}
				
	}
	
	
	private void missEssentialFieldErrorMessage() {
		
		// Messaggio di Errore
		JOptionPane.showConfirmDialog( addEmployeeFrame , messageStrings.getString( MessageJsonKey.ERROR_MISS_ESSENTIAL_FIELDS ), 
				"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
		
	}
	
	// Controlla se l'elemento i-esimo dell'arrey dei ProfessionalRole è presente anche in una posizione j > i
	// in questo modo ogni professional role viene inserito una sola volta
	private boolean isDoubleInArray( int i ) {
		
		if( i == this.arrProfessionalRoleIds.length )
			return false;
		
		for( int j = i + 1 ; j < this.arrProfessionalRoleIds.length ; j ++)
			if( arrProfessionalRoleIds[j] == arrProfessionalRoleIds[i] )
				return true;
		
		return false;
	}
	
	
	private void deleteEmployee() {
		
		String param = "id=" + addEmployeeFrame.getIdFromSearchEmployeeFrame();
		
		HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_EMPLOYEE_BY_ID, param);
		
	}
	
	private void addEmployee() {
		
		name = addEmployeeFrame.getTextFieldName().getText();
		surname = addEmployeeFrame.getTextFieldSurname().getText();
		title = addEmployeeFrame.getTextFieldTitle().getText();
		birth_date = addEmployeeFrame.getTextFieldBirthDate().getText();
		phone_number = addEmployeeFrame.getTextFieldPhoneNumber().getText();
		phone_number_2 = addEmployeeFrame.getTextFieldPhoneNumber2().getText();
		e_mail = addEmployeeFrame.getTextFieldEMail().getText();
		
		arrProfessionalRoleIds = addEmployeeFrame.getArrProfessionalRoleIds();
		
		// Controllo che i campi obbligatori siano riempiti ----------------------------------
		

		if( name == null || surname == null || phone_number == null ) {
			
			missEssentialFieldErrorMessage();
			
			
		} else if ( name.equals("") || surname.equals("") || phone_number.equals("") ) {
			
			missEssentialFieldErrorMessage();

		} else {
			
			// -----------------------------------------------------------------------------------
			
			params = "name=" + name + "&surname=" + surname + "&title=" + title + "&birthDate=" + birth_date
					+"&phoneNumber=" + phone_number + "&phoneNumber2=" + phone_number_2 + "&eMail="+ e_mail;
			
			HttpConnectionManager.doPost( HttpConnectionManager.POST_EMPLOYEE, params);
			
			long lastId = Long.valueOf( HttpConnectionManager.doGet( HttpConnectionManager.GET_MAX_ID_EMPLOYEE)
			 								.getResponseString().trim() );
			
			 System.out.println("=================> " + HttpConnectionManager.doGet( HttpConnectionManager.GET_MAX_ID_EMPLOYEE)
			 					.getResponseString());
			
			
			for( int i = 0; i < arrProfessionalRoleIds.length; i ++ ) {
				if( ! isDoubleInArray( i ) ) { // Se non ha duplicati nelle posizioni successive dell'array 
					
					params = "idEmployee=" + lastId + "&idProfessionalRole=" + arrProfessionalRoleIds[i];
					HttpConnectionManager.doPost( HttpConnectionManager.POST_LINK_EMPLOYEE_TO_PROFESSIONAL_ROLE, params);
				}
			}
		
		}		
	}

}
