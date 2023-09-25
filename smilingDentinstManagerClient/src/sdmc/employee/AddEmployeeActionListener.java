package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.main.MainMenuFrame;

public class AddEmployeeActionListener implements ActionListener {
	
	private AddEmployeeFrame addEmployeeFrame;
	
	// Action commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String ADD_EMPLOYEE = "ADD_EMPLOYEE";
	
	//
	public final static String BACK_TO_SEARCH_EMPLOYEE = "BACK_TO_SEARCH_EMPLOYEE";
	public final static String DELETE_EMPLOYEE = "DELETE_EMPLOYEE";
	public final static String EDIT_EMPLOYEE = "EDIT_EMPLOYEE";
	
	
	public AddEmployeeActionListener( AddEmployeeFrame addEmployeeFrame ) {
		
		this.addEmployeeFrame = addEmployeeFrame;
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			new MainMenuFrame();
			addEmployeeFrame.dispose();
			
			break;
			
		case ADD_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			break;
			
			
		case BACK_TO_SEARCH_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			break;
			
			
		case EDIT_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			break;
			
			
		case DELETE_EMPLOYEE:
			
			// check message
			System.out.println("AddEmployeeActionListener ---> " + e.getActionCommand() );
			
			break;
			
			
			
		}
				
	}
	
	

}
