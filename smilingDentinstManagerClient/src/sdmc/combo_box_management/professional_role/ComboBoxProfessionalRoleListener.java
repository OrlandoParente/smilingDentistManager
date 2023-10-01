package sdmc.combo_box_management.professional_role;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import sdmc.employee.SearchEmployeeFrame;
import sdmc.professional_role.ProfessionalRoleManagerFrame;
import sdmc.utils.Utils;

public class ComboBoxProfessionalRoleListener implements ItemListener {

	// private JTextField textFieldProfessionalRoleName;
	// private JTextArea textAreaProfessionalRoleDescription;
	
	ProfessionalRoleManagerFrame professionalRoleManagerFrame;
	SearchEmployeeFrame searchEmployeeFrame;
	
	public ComboBoxProfessionalRoleListener() {
		
		this.professionalRoleManagerFrame = null;
		this.searchEmployeeFrame = null;
	}
	
	public ComboBoxProfessionalRoleListener( ProfessionalRoleManagerFrame professionalRoleManagerFrame ) {
		
		this();
		
		this.professionalRoleManagerFrame = professionalRoleManagerFrame;
	
	}
	
	public ComboBoxProfessionalRoleListener( SearchEmployeeFrame searchEmployeeFrame ) {
		
		this();
		
		this.searchEmployeeFrame = searchEmployeeFrame;
	
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<ProfessionalRole> cb = ( JComboBox<ProfessionalRole> ) e.getSource();
		ProfessionalRole professionalRole = ( ProfessionalRole ) cb.getSelectedItem();

		System.out.println( "ComboBoxProfessionalRolesListener -> Selected ---> " +  professionalRole ); 
		// Gestione della "Grafica" ------------------------------------------------------------
		
		if( professionalRole != null ) {
			
			
			if( professionalRoleManagerFrame != null ) { // Se il listener fa riferimento al combo box del professionalRoleManagerFrame
			
				professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText( professionalRole.getName() );
				professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText( professionalRole.getDescription() );
			
				// Abilita i bottoni per eliminare e modificare il professional Role corrente
				// Disabilità il bottone per aggiungerlo ( perché già presente nel db )
				professionalRoleManagerFrame.getBtnDeleteProfessionalRole().setEnabled( true );
				professionalRoleManagerFrame.getBtnEditProfessionalRole().setEnabled( true );
				professionalRoleManagerFrame.getBtnAddProfessionalRole().setEnabled( false );
			
			} else if ( searchEmployeeFrame != null ) { // Se il listener fa riferimento al combo box del searchEmployeeFrame 
			
				System.out.println("ComboBoxProfessionalRoleListener -> searchEmployeeFrame NOT NULL -> professional name -> " + professionalRole.getName() );
			
				// toglie fli spazi e li sostituisce con "%20", che invece è riconosciuto dall'url encoding
				String professionalRoleName = Utils.adaptStrToUrlEncoding( professionalRole.getName() );
				
				searchEmployeeFrame.reloadPanelEmployeeShowDatas( 
						searchEmployeeFrame.getEmployeesByProfessionalRoleName(  professionalRoleName ));
			}
		
		} else {
			
			if( professionalRoleManagerFrame != null ) { // Se il listener fa riferimento al combo box del professionalRoleManagerFrame
				
				professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText( "" );
				professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText( "" );
				
				// Disabilita i bottoni per eliminare e modificare il professional Role corrente ( perchè è null )
				// Abilità il bottone per aggiungere un nuovo ProfessionalRole
				professionalRoleManagerFrame.getBtnDeleteProfessionalRole().setEnabled( false );
				professionalRoleManagerFrame.getBtnEditProfessionalRole().setEnabled( false );
				professionalRoleManagerFrame.getBtnAddProfessionalRole().setEnabled( true );
			
			} else if ( searchEmployeeFrame != null ) { // Se il listener fa riferimento al combo box del searchEmployeeFrame 
				
				System.out.println("ComboBoxProfessionalRoleListener -> searchEmployeeFrame NOT NULL");
				
				searchEmployeeFrame.reloadPanelEmployeeShowDatas();
				
			}
		}
		
		// -------------------------------------------------------------------------------------
	
	}

}
