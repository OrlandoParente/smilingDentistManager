package sdmc.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.main.MainMenuFrame;

public class SettingsActionListener implements ActionListener{

	//
	public final static String UPLOAD_DB = "UPLOAD_DB";
	public final static String DOWNLOAD_DB = "DOWNLOAD_DB";
	public final static String MAIN_MENU = "MAIN MENU";
	public final static String SAVE = "SAVE";
	
	//
	private SettingsFrame settingsFrame;
	
	public SettingsActionListener( SettingsFrame settingsFrame ) {
		
		this.settingsFrame = settingsFrame;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch ( e.getActionCommand() ) {
		
		case UPLOAD_DB : 
			
			System.out.println("SettingsActionListener -> " + e.getActionCommand() );
			
			// <<<<<<<<<<<<<<<------------------------- NOT IMPLEMENTED YET
			
			break;
			
		case DOWNLOAD_DB :
			
			System.out.println("SettingsActionListener -> " + e.getActionCommand() );
			
			// <<<<<<<<<<<<<<<------------------------- NOT IMPLEMENTED YET
			
			break;
		
		case MAIN_MENU :
			
			System.out.println("SettingsActionListener -> " + e.getActionCommand() );
			
			settingsFrame.dispose();
			
			new MainMenuFrame();
			
			break;
			
		case SAVE :
			
			System.out.println("SettingsActionListener -> " + e.getActionCommand() );
			
			Setting.getSettings().saveCurrentSettings();
			
			// reload frame
			new SettingsFrame();
			settingsFrame.dispose();
			
			break;
		}
		
	}

}
