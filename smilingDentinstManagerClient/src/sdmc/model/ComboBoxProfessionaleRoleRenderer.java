package sdmc.model;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/*
 * Serve a mettere nel combo box solo il nome del Professional role selezionato
 */
public class ComboBoxProfessionaleRoleRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if( value instanceof ProfessionalRole ) {

			ProfessionalRole professionalRole = (ProfessionalRole) value;
			setText( professionalRole.getName() );

		} else if ( value == null ) {
			setText("      ");
		}
		
		return this;
	}

	
}
