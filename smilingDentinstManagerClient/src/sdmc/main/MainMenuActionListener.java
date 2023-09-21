package sdmc.main;

import sdmc.calendar.*;
import sdmc.customer.AddCustomerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class MainMenuActionListener implements ActionListener {
	
	public final static String CALENDAR = "btnCalendar";
	public final static String ADD_CUSTOMER = "btnAddCustomer";
	public final static String SEARCH_CUSTOMER = "btnSearchCustomer";
	public final static String ADD_EMPLOYEE = "btnAddEmployee";
	public final static String SEARCH_EMPLOYEE = "btnSearchEmployee";
	public final static String UPLOAD_DATABASE = "btnUploadDatabase";
	public final static String DOWNLOAD_DATABASE = "btnDownloadDatabase";
	public final static String SETTING = "btnSetttings";
	
	private JFrame MainMenuFrame;
	
	MainMenuActionListener( JFrame MainMenuFrame ) {
		
		// Prende in input la MainMenu frame per poterla chiudere all'occorrenza 
		this.MainMenuFrame = MainMenuFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch( e.getActionCommand() ) {
		
		case CALENDAR:
			
			System.out.println( "Main Menu Action Listener -> CALENDAR " );
			
			new CalendarFrame();
			MainMenuFrame.dispose();
			
			
			break;
			
		case ADD_CUSTOMER:
			
			System.out.println( "Main Menu Action Listener -> ADD_CUSTOMER " );
			
			new AddCustomerFrame();
			MainMenuFrame.dispose();
			
			break;
			
		case SEARCH_CUSTOMER:
			
			System.out.println( "Main Menu Action Listener -> SEARCH_CUSTOMER " );
			
			break;
			
		case ADD_EMPLOYEE:
			
			System.out.println( "Main Menu Action Listener -> ADD_EMPLOYEE " );
			
			break;
			
		case SEARCH_EMPLOYEE:
			
			System.out.println( "Main Menu Action Listener -> SEARCH_EMPLOYEE " );
			
			break;
			
		case UPLOAD_DATABASE:
			
			System.out.println( "Main Menu Action Listener -> UPLOAD_DATABASE " );
			
			break;
			
		case DOWNLOAD_DATABASE:
			
			System.out.println( "Main Menu Action Listener -> DOWNLOAD_DATABASE " );
			
			break;
			
		case SETTING:
			
			System.out.println( "Main Menu Action Listener -> SETTING " );
			
			break;
		
		}
	}

}
