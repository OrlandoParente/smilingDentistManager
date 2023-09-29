package sdmc.professional_role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sdmc.main.MainMenuFrame;
import sdmc.model.ProfessionalRole;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;

public class ProfessionalRoleManagerActionListener implements ActionListener {

	private ProfessionalRoleManagerFrame professionalRoleManagerFrame;

	public static final String MAIN_MENU = "MAIN_MENU";
	public static final String DELETE_PROFESSIONAL_ROLE = "DELETE_PROFESSIONAL_ROLE";
	public static final String EDIT_PROFESSIONAL_ROLE = "EDIT_PROFESSIONAL_ROLE";
	public static final String ADD_PROFESSIONAL_ROLE = "ADD_PROFESSIONAL_ROLE";
	
	
	private String params;
	private String name;
	private String description;
	private int responseCode;
	private RequestResponse response;
	private int id;
	private ProfessionalRole selectedProfessionalRole;
	private int indexSelectedProfessionalRoleInComboBox;
	
	public ProfessionalRoleManagerActionListener(  ProfessionalRoleManagerFrame professionalRoleManagerFrame ) {
		this.professionalRoleManagerFrame = professionalRoleManagerFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
		
			// Check message
			System.out.println("ProfessionalRoleManagerActionListener --> " + e.getActionCommand() );
			
			new MainMenuFrame();
			professionalRoleManagerFrame.dispose();
			
			break;
			
		case DELETE_PROFESSIONAL_ROLE:
			
			// Check message
			System.out.println("ProfessionalRoleManagerActionListener --> " + e.getActionCommand() );
			
			selectedProfessionalRole = (ProfessionalRole) professionalRoleManagerFrame.getComboBoxSelectProfessionalRole().getSelectedItem();
			
			id = -1;
			if( selectedProfessionalRole != null ) id = selectedProfessionalRole.getId();
			params = "id=" + id;
			
				
			responseCode = HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_PROFESSIONAL_ROLE_BY_ID, params );
			
			// Qua ci può stare il controllo if( responseCode == 200 )
			
			// 
			professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText("");
			professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText("");
			professionalRoleManagerFrame.reloadComboBoxProfessionalRoles();
			

					
			break;
			
		case EDIT_PROFESSIONAL_ROLE:
			
			// Check message
			System.out.println("ProfessionalRoleManagerActionListener --> " + e.getActionCommand() );
			
			// Recupera l'index per selezionarlo di nuovo dopo la modifica
			indexSelectedProfessionalRoleInComboBox = professionalRoleManagerFrame.getComboBoxSelectProfessionalRole().getSelectedIndex();
			
			// Controllo se Name è vuoto e nel caso lancia un JOptionPanel
			if( this.isTextFieldNameEmpty() )
				break;
			
			name = professionalRoleManagerFrame.getTextFieldProfessionalRoleName().getText();
			
			if( professionalRoleManagerFrame.getTextAreaProfessionalDescription().getText() == null)
				description = "";
			else
				description = professionalRoleManagerFrame.getTextAreaProfessionalDescription().getText();
			
			// Recupero selected Professional Id --------
			selectedProfessionalRole = (ProfessionalRole) professionalRoleManagerFrame.getComboBoxSelectProfessionalRole().getSelectedItem();
			id = selectedProfessionalRole.getId();
			// -----------------------------------------
			
			params = "id=" + id + "&name=" + name + "&description=" + description ; 
			
			responseCode = HttpConnectionManager.doPut( HttpConnectionManager.PUT_PROFESSIONAL_ROLE_BY_ID, params );
			
			// Qua ci può stare il controllo if( response.responseCode == 200 )
			
			// 
			// professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText("");
			// professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText("");
			professionalRoleManagerFrame.reloadComboBoxProfessionalRoles();
			professionalRoleManagerFrame.getComboBoxSelectProfessionalRole().setSelectedIndex( indexSelectedProfessionalRoleInComboBox )  ;
			
			
			break;
			
		case ADD_PROFESSIONAL_ROLE:
			
			// Check message
			System.out.println("ProfessionalRoleManagerActionListener --> " + e.getActionCommand() );
			
			params = "";
			
			// Controllo se Name è vuoto e nel caso lancia un JOptionPanel
			if( this.isTextFieldNameEmpty() )
				break;
			
			name = professionalRoleManagerFrame.getTextFieldProfessionalRoleName().getText();
			
			params += "name=" + name ;
			
			if( professionalRoleManagerFrame.getTextAreaProfessionalDescription().getText() != null
				|| ! professionalRoleManagerFrame.getTextAreaProfessionalDescription().getText().equals("") ) {
				
				description = professionalRoleManagerFrame.getTextAreaProfessionalDescription().getText();
				
				params += "&description=" + description ; 
			}
			
			response = HttpConnectionManager.doPost( HttpConnectionManager.POST_PROFESSIONAL_ROLE , params );
			
			// Qua ci può stare il controllo if( response.responseCode == 200 )
			
			// 
			professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText("");
			professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText("");
			professionalRoleManagerFrame.reloadComboBoxProfessionalRoles();
			
			
			break;
		}
			
	}
	
	private boolean isTextFieldNameEmpty() {
		
		JTextField textFieldProfessionalRoleName = professionalRoleManagerFrame.getTextFieldProfessionalRoleName();
		
		if( textFieldProfessionalRoleName == null || textFieldProfessionalRoleName.getText().equals("") ) {
			
			System.err.println( "professionalRoleManagerFrameActionListener --> NOME non può essere vuoto " );
			JOptionPane.showConfirmDialog( professionalRoleManagerFrame, "NOME non può essere vuoto", 
					"Message",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE );
			
			return true;
		}
		
		return false;
	}
}
