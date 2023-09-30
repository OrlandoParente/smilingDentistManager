package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.main.MainMenuFrame;

public class SearchEmployeeActionListener implements ActionListener {

	// Action commands
	public static final String MAIN_MENU = "MAIN_MENU";
	public static final String SEARCH_EMPLOYEE = "SEARCH_EMPLOYEE";
	public static final String RESET_SEARCH = "RESET_SEARCH";
	
	private SearchEmployeeFrame searchEmployeeFrame;
	
	
	public SearchEmployeeActionListener( SearchEmployeeFrame searchEmployeeFrame ) {
		
		this.searchEmployeeFrame = searchEmployeeFrame;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
			
		case MAIN_MENU:
			
			// Check Message
			System.out.println("SearchEmployeeActionListener --> " + e.getActionCommand() );
			
			new MainMenuFrame();
			searchEmployeeFrame.dispose();
			
			break;
		
				
		case SEARCH_EMPLOYEE:
					
			// Check Message
			System.out.println("SearchEmployeeActionListener --> " + e.getActionCommand() );
					
			String keyWord = searchEmployeeFrame.getTextFieldSearchEmployee().getText();
			
			// Per evitare eventuali problemi con il risultato delle query sql
			if( keyWord == null ) keyWord = "";
			
			searchEmployeeFrame.reloadPanelEmployeeShowDatas( 
								searchEmployeeFrame.getEmployeesByPartialKeyWordOverAllFields( keyWord ) );
			
			break;
					
					
					
					
		case RESET_SEARCH:
			
			// Check Message
			System.out.println("SearchEmployeeActionListener --> " + e.getActionCommand() );
			
			searchEmployeeFrame.reloadPanelEmployeeShowDatas();
			
			searchEmployeeFrame.getComboBoxProfessionalRoles().setSelectedItem( null );
			
			searchEmployeeFrame.getTextFieldSearchEmployee().setText("");
			
			break;
			
		}
		
	}

}
