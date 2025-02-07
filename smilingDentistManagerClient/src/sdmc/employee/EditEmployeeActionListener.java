package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.server_connection.HttpConnectionManager;

public class EditEmployeeActionListener implements ActionListener {

	private SearchEmployeeFrame searchEmployeeFrame;
	
	public EditEmployeeActionListener( SearchEmployeeFrame searchEmployeeFrame ) {
		this.searchEmployeeFrame = searchEmployeeFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check Message
		System.out.println("EditEmployeeActionListener --> id -> " + e.getActionCommand() );
	
		String strEmployee = HttpConnectionManager
							.doGet( HttpConnectionManager.GET_EMPLOYEE_BY_ID + e.getActionCommand() )
							.getResponseString().trim();
		
		System.out.println( HttpConnectionManager.GET_EMPLOYEE_BY_ID +  e.getActionCommand() );
		// Evita errore nel creare il jsonObj con stringa nulla o vuota ----

		if( strEmployee == null ) {
			
			strEmployee = "{}";
		
		} else if( strEmployee.equals("") ) {
			
			strEmployee = "{}";
			
		} 
		// -------------------------------------------------------------------
		
		JSONObject joEmployee = new JSONObject( strEmployee );
		
		// Recupero professional roles associati all'employee -----------------------
		
		JSONArray jsonArrProfessionalRoles = searchEmployeeFrame.getProfessionalRoleByIdEmployee( joEmployee.getInt("id") + "" );
		
		int [] arrProfessionalRoles = new int [ jsonArrProfessionalRoles.length() ];
		
		for( int i = 0; i < jsonArrProfessionalRoles.length(); i ++ ) {
			arrProfessionalRoles[ i ] = jsonArrProfessionalRoles.getJSONObject(i).getInt("id");
		}
		
		// ----------------------------------------------------------------------------
		
		
		searchEmployeeFrame.dispose();
		
		new AddEmployeeFrame(  joEmployee.getInt("id") + "", joEmployee.getString("name"), joEmployee.getString("surname"), 
								joEmployee.getString("title"), joEmployee.getString("birthDate") ,
								joEmployee.optString("phoneNumber", ""), 	// joEmployee.getString("phoneNumber"), 
								joEmployee.optString("phoneNumber2", ""), 	// joEmployee.getString("phoneNumber2"),
								joEmployee.optString("eMail", ""), 	// joEmployee.getString("eMail") 
								arrProfessionalRoles );
	
	}

	
	
}
