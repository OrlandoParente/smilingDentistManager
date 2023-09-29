package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import sdmc.main.MainMenuFrame;
import sdmc.model.ProfessionalRole;
import sdmc.server_connection.HttpConnectionManager;

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
				break;
				
			} else if ( name.equals("") || surname.equals("") || phone_number.equals("") ) {
				
				missEssentialFieldErrorMessage();
				break;
			}
			
			// -----------------------------------------------------------------------------------
			
			params = "name=" + name + "&surname=" + surname + "&title=" + title + "&birth_date=" + birth_date
					+"&phone_number=" + phone_number + "&phone_number=" + phone_number_2 + "&e_mail="+ e_mail;
			
			HttpConnectionManager.doPost( HttpConnectionManager.POST_EMPLOYEE, params);
			
			int lastId = Integer.valueOf( HttpConnectionManager.doGet( HttpConnectionManager.GET_MAX_ID_EMPLOYEE)
											.getResponseString().trim() );
			
			System.out.println("=================> " + HttpConnectionManager.doGet( HttpConnectionManager.GET_MAX_ID_EMPLOYEE)
			.getResponseString());
			
			for( int i = 0; i < arrProfessionalRoleIds.length; i ++ ) {
				if( ! isDoubleInArray( i ) ) { // Se non ha duplicati nelle posizioni successive dell'array 
					
					params = "id_employee=" + lastId + "&id_professional_role=" + arrProfessionalRoleIds[i];
					HttpConnectionManager.doPost( HttpConnectionManager.POST_LINK_EMPLOYEE_TO_PROFESSIONAL_ROLE, params);
				}
			}
			
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
			
			break;
			
			
		case EDIT_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			break;
			
			
		case DELETE_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			// HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_EMPLOYEE_BY_ID, "id=" DA COMPLETARE );
			
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
		JOptionPane.showConfirmDialog( addEmployeeFrame , "Nome, cognome e numero di telefono sono campi obbligatori", 
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
	

}
