package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.server_connection.HttpConnectionManager;

public class DeleteCustomerActionListener implements ActionListener {

	private String customerId;
	
	private SearchCustomerFrame searchCustomerFrame;
	
	public DeleteCustomerActionListener( SearchCustomerFrame searchCustomerFrame ) {
		this.searchCustomerFrame = searchCustomerFrame;
	} 
	
	/*
	 * Prende l'id del customer dallìactionCommand e lancia una deleteCustomerById
	 * Quindi, ristampa la lista customer 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// check message
		System.out.println("DeleteCustomerActionListener ---> id = " + e.getActionCommand() );
		
		customerId = e.getActionCommand();
		
		String param = "id="+customerId;
		
		HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_CUSTOMER, param);
		
		searchCustomerFrame.showCustomers( searchCustomerFrame.getCustomers() );
		
	}

}
