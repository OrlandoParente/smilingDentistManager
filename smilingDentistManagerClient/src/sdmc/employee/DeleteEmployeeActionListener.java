package sdmc.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.server_connection.HttpConnectionManager;

public class DeleteEmployeeActionListener implements ActionListener{

	private SearchEmployeeFrame searchEmployeeFrame;
	
	public DeleteEmployeeActionListener( SearchEmployeeFrame searchEmployeeFrame ) {
		
		this.searchEmployeeFrame = searchEmployeeFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Check Message
		System.out.println( "DeleteEmployeeActionListenere --> id -> " + e.getActionCommand()  );
		
		String param = "id=" + e.getActionCommand();
		
		HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_EMPLOYEE_BY_ID, param);
		
		// Qui ci potrebbe andare un controllo del tipo responseCode == HTTP_OK
		
		searchEmployeeFrame.reloadPanelEmployeeShowDatas();
		
	}


}
