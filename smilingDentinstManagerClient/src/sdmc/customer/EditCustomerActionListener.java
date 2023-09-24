package sdmc.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONObject;

public class EditCustomerActionListener implements ActionListener {

	private SearchCustomerFrame searchCustomerFrame;
	
	public EditCustomerActionListener( SearchCustomerFrame searchCustomerFrame ) {
		this.searchCustomerFrame = searchCustomerFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// check message
		System.out.println("EditCustomerActionListener ---> id = " + e.getActionCommand() );

		
		JSONObject customer = searchCustomerFrame.getCustomer( e.getActionCommand() );
		
		new AddCustomerFrame( customer.getInt( "id" ), customer.getString( "taxIdCode" ), customer.getString( "name" ),
				customer.getString( "surname" ), customer.getString( "birthCity" ), customer.getString( "birthCityProvince" ),
				customer.getString( "birthDate" ), customer.getString( "residenceStreet" ), customer.getString( "houseNumber" ), 
				customer.getString( "residenceCity" ), customer.getString( "residenceCityCap" ), 
				customer.getString( "residenceProvince" ), customer.getString( "phoneNumber" ),
				customer.getString( "phoneNumber" ), customer.getString("eMail") );
		
		searchCustomerFrame.dispose();
	}
	
	
	
	

}
