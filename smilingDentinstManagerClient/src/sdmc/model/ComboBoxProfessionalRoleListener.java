package sdmc.model;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sdmc.professional_role.ProfessionalRoleManagerFrame;

public class ComboBoxProfessionalRoleListener implements ItemListener {

	// private JTextField textFieldProfessionalRoleName;
	// private JTextArea textAreaProfessionalRoleDescription;
	
	ProfessionalRoleManagerFrame professionalRoleManagerFrame;
	
	public ComboBoxProfessionalRoleListener() {
		
		this.professionalRoleManagerFrame = null;
	}
	
	public ComboBoxProfessionalRoleListener( ProfessionalRoleManagerFrame professionalRoleManagerFrame ) {
		
		this();
		
		this.professionalRoleManagerFrame = professionalRoleManagerFrame;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		JComboBox<ProfessionalRole> cb = ( JComboBox<ProfessionalRole> ) e.getSource();
		ProfessionalRole professionalRole = ( ProfessionalRole ) cb.getSelectedItem();

		System.out.println( "ComboBoxProfessionalRolesListener -> Selected ---> " +  professionalRole ); 
		// Gestione della "Grafica" ------------------------------------------------------------
		
		if( professionalRole != null ) {
			
			if( professionalRoleManagerFrame != null ) {
			
				professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText( professionalRole.getName() );
				professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText( professionalRole.getDescription() );
			
				// Abilita i bottoni per eliminare e modificare il professional Role corrente
				// Disabilità il bottone per aggiungerlo ( perché già presente nel db )
				professionalRoleManagerFrame.getBtnDeleteProfessionalRole().setEnabled( true );
				professionalRoleManagerFrame.getBtnEditProfessionalRole().setEnabled( true );
				professionalRoleManagerFrame.getBtnAddProfessionalRole().setEnabled( false );
			
			}
		
		} else {
			
			if( professionalRoleManagerFrame != null ) {
				
				professionalRoleManagerFrame.getTextFieldProfessionalRoleName().setText( "" );
				professionalRoleManagerFrame.getTextAreaProfessionalDescription().setText( "" );
				
				// Disabilita i bottoni per eliminare e modificare il professional Role corrente ( perchè è null )
				// Abilità il bottone per aggiungere un nuovo ProfessionalRole
				professionalRoleManagerFrame.getBtnDeleteProfessionalRole().setEnabled( false );
				professionalRoleManagerFrame.getBtnEditProfessionalRole().setEnabled( false );
				professionalRoleManagerFrame.getBtnAddProfessionalRole().setEnabled( true );
			
			}
		}
		
		// -------------------------------------------------------------------------------------
	
	}

}
