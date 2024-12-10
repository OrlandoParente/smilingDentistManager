package sdmc.calendar;

import sdmc.main.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalendarActionListener implements ActionListener {

	// Action commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String ADD_APPOINTMENT = "ADD_APPOINTMENT"; 
	public final static String PREV_DAY = "PREV_DAY"; 
	public final static String NEXT_DAY = "NEXT_DAY"; 
	
	private CalendarManager calendarManager;
	
	//
	private CalendarFrame calendarFrame;
	
	public CalendarActionListener( CalendarFrame calendarFrame ) {
		
		this.calendarFrame = calendarFrame;
	
		calendarManager = CalendarManager.getCalendarManager();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			// Check message
			System.out.println("CalendarActionListener -> " + e.getActionCommand() );
			
			new MainMenuFrame();
			calendarFrame.dispose();
			
			break;
			
		case ADD_APPOINTMENT:
			
			// Check message
			System.out.println("CalendarActionListener -> " + e.getActionCommand() );
			
			new AddAppointmentFrame();
			calendarFrame.dispose();
			
			break;
			
		
		case PREV_DAY:
			
			// Check message
			System.out.println("CalendarActionListener -> " + e.getActionCommand() );
			
			calendarManager.decrementSelectDayDate();
			
			calendarFrame.reloadLabelsCurrentDate();
			
			calendarFrame.reloadPanelBodyCalendar();
			
			break;
			
			
		case NEXT_DAY:
			
			// Check message
			System.out.println("CalendarActionListener -> " + e.getActionCommand() );
			
			
			calendarManager.incrementSelectNexDayDate();
			
			calendarFrame.reloadLabelsCurrentDate();
			
			calendarFrame.reloadPanelBodyCalendar();
			
			break;
		
		
		}
		
	}

	
}
