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
	public final static String RESET_SEARCH = "RESET_SEARCH";
	
	
	
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
			
		case SEARCH: // <<<<<<<<< ########### mancano i controlli  CONNECTION REFUSED 
			
			// check message
			System.out.println("SearchCustomerListener --> " + e.getActionCommand() );
			
			String keyWord = searchCustomerFrame.getTextFieldSearch().getText();
			
			RequestResponse response = HttpConnectionManager.doGet(
											HttpConnectionManager.GET_CUSTOMER_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS + keyWord );
			
			System.out.println( "SearchCustomerListener --> " + e.getActionCommand() 
									+ "RESPONSE STRING -> " + response.getResponseString()
									+ "RESPONSE CODE -> " + response.getResponseCode()
									+  "key_word -> " + keyWord );
			
			// Gestisce caso di nessun elemento trovato per evitare a JSONException per la mancanza delle [ ]
			if( response.getResponseString() == null || response.getResponseString().equals("") ) {
					response.setResponseString("[]");
			}
			
			JSONArray customerList = new JSONArray( response.getResponseString() );
			
			searchCustomerFrame.showCustomers( customerList );
			
			break;
			
		case RESET_SEARCH: // <<<<<<<<< ########### mancano i controlli  CONNECTION REFUSED 
			
			// check message
			System.out.println("SearchCustomerListener --> " + e.getActionCommand() );
			
			
			searchCustomerFrame.showCustomers( searchCustomerFrame.getCustomers() );
			
			searchCustomerFrame.getTextFieldSearch().setText(""); 
			
			break;
		
		}
	}
	
	
}
