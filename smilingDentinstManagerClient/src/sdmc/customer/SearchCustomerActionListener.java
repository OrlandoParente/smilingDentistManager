package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONArray;

import sdmc.main.MainMenuFrame;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;

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
			
		case SEARCH: // <<<<<<<<< ########### mancano i controlli  CONNECTION REFUSED 6
			
			System.out.println("SearchCustomerListener --> " + e.getActionCommand() );
			
			String keyWord = searchCustomerFrame.getTextFieldSearch().getText();
			
			RequestResponse response = HttpConnectionManager.doGet("getCustomersByPartialKeyWordOverAllFields/" + keyWord );
			
			JSONArray customerList = new JSONArray( response.getResponseString() );
			
			searchCustomerFrame.showCustomers( customerList );
			
			break;
		
		}
	}
	
	
}
