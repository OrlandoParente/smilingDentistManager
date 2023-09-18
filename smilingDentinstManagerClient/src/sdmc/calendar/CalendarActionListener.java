package sdmc.calendar;

import sdmc.main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class CalendarActionListener implements ActionListener {

	//
	public final static String MAIN_MENU = "MAIN_MENU";
	
	
	//
	private JFrame calendarFrame;
	
	public CalendarActionListener( JFrame calendarFrame ) {
		this.calendarFrame = calendarFrame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			new MainMenuFrame();
			calendarFrame.dispose();
			break;
		}
		
	}

	
}
