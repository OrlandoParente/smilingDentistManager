package sdmc.main;

import sdmc.server_connection.HttpConnectionManager;

public class Main {
	
	public static void main ( String [] args ) {
		System.out.println("Start the Main Menu of the smiling dentist manager client ");
		
		new MainMenuFrame();
		
		new HttpConnectionManager().getCustomers();
	}
	
}
