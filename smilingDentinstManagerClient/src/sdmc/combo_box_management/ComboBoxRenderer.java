package sdmc.combo_box_management;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import sdmc.combo_box_management.customer.Customer;
import sdmc.combo_box_management.professional_role.ProfessionalRole;

/*
 * Serve a mettere nel combo box solo il nome del Professional role selezionato
 * o nome e cognome del Customer selezionato 
 */
public class ComboBoxRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if( value instanceof ProfessionalRole ) {

			ProfessionalRole professionalRole = (ProfessionalRole) value;
			setText( professionalRole.getName() );

		} else if( value instanceof Customer ) {
			
			Customer customer = (Customer) value;
			setText( customer.getName() + " " + customer.getSurname() );
			
		} else if ( value == null ) {
			setText("      ");
		}
		
		return this;
	}

	
}
