package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.main.MainMenuFrame;

public class SearchCustomerActionListener implements ActionListener{

	private SearchCustomerFrame searchCustomerFrame;
	
	// Action Commands
	public final static String MAIN_MENU = "MAIN_MENU";
	public final static String SEARCH = "SEARCH";
	
	
	public SearchCustomerActionListener( SearchCustomerFrame searchCustomerFrame ) {
		this.searchCustomerFrame = searchCustomerFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch( e.getActionCommand() ) {
		
		case MAIN_MENU:
			
			System.out.println("SearchCustomerListener --> " + e.getActionCommand() );
			
			new MainMenuFrame();
			searchCustomerFrame.dispose();
			
			break;
			
		case SEARCH:
			
			System.out.println("SearchCustomerListener --> " + e.getActionCommand() );
			
			break;
		
		}
	}
	
	
}
