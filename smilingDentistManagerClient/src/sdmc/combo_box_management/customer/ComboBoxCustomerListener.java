package sdmc.combo_box_management.customer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import sdmc.calendar.AddAppointmentFrame;

public class ComboBoxCustomerListener implements ItemListener{

	AddAppointmentFrame addAppointmentFrame;
	
	public ComboBoxCustomerListener( AddAppointmentFrame addAppointmentFrame ) {
		this.addAppointmentFrame = addAppointmentFrame;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

}
