package sdmc.main;

import sdmc.calendar.*;
import sdmc.customer.AddCustomerFrame;
import sdmc.customer.SearchCustomerFrame;
import sdmc.employee.AddEmployeeFrame;
import sdmc.employee.SearchEmployeeFrame;
import sdmc.professional_role.ProfessionalRoleManagerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class MainMenuActionListener implements ActionListener {
	
	public final static String CALENDAR = "btnCalendar";
	public final static String ADD_CUSTOMER = "btnAddCustomer";
	public final static String SEARCH_CUSTOMER = "btnSearchCustomer";
	public final static String ADD_EMPLOYEE = "btnAddEmployee";
	public final static String SEARCH_EMPLOYEE = "btnSearchEmployee";
	public final static String PROFESSIONAL_ROLE_MANAGER = "btnProfessionalRoleManager";
	public final static String UPLOAD_DATABASE = "btnUploadDatabase";
	public final static String DOWNLOAD_DATABASE = "btnDownloadDatabase";
	public final static String SETTING = "btnSetttings";
	
	private JFrame mainMenuFrame;
	
	MainMenuActionListener( JFrame mainMenuFrame ) {
		
		// Prende in input la MainMenu frame per poterla chiudere all'occorrenza 
		this.mainMenuFrame = mainMenuFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch( e.getActionCommand() ) {
		
		case CALENDAR:
			
			System.out.println( "Main Menu Action Listener -> CALENDAR " );
			
			new CalendarFrame();
			mainMenuFrame.dispose();
			
			
			break;
			
		case ADD_CUSTOMER:
			
			System.out.println( "Main Menu Action Listener -> ADD_CUSTOMER " );
			
			new AddCustomerFrame();
			mainMenuFrame.dispose();
			
			break;
			
		case SEARCH_CUSTOMER:
			
			System.out.println( "Main Menu Action Listener -> SEARCH_CUSTOMER " );
			
			new SearchCustomerFrame();
			mainMenuFrame.dispose();
			
			break;
			
		case ADD_EMPLOYEE:
			
			System.out.println( "Main Menu Action Listener -> ADD_EMPLOYEE " );
			
			new AddEmployeeFrame();
			mainMenuFrame.dispose();
			
			break;
			
		case SEARCH_EMPLOYEE:
			
			System.out.println( "Main Menu Action Listener -> SEARCH_EMPLOYEE " );
			
			new SearchEmployeeFrame();
			mainMenuFrame.dispose();
			
			break;

		case PROFESSIONAL_ROLE_MANAGER:
			
			System.out.println( "Main Menu Action Listener ->  " + e.getActionCommand() );
			
			new ProfessionalRoleManagerFrame();
			mainMenuFrame.dispose();
			
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
