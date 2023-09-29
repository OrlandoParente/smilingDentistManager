package sdmc.main;

import sdmc.settings.Setting;

public class Main {
	
	public static void main ( String [] args ) {
		System.out.println("Start the Main Menu of the smiling dentist manager client ");
		
		// Imposta i valori di default presi dal file settings.json 
		Setting.getSettings();
		
		new MainMenuFrame();
		
	}
	
}
