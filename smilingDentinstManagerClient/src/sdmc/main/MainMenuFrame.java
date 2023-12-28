package sdmc.main;

import sdmc.settings.Setting;
import sdmc.utils.*;
import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;

public class MainMenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JSONObject btnNames;
	private MainMenuActionListener listener;
	
	private JButton btnCalendar;
	private JButton btnAddCustomer;
	private JButton btnSearchCustomer;
	private JButton btnAddEmployee;
	private JButton btnSearchEmployee;
	private JButton btnProfessionalRoleManager;
	// private JButton btnUploadDatabase;		// In futuro si può pensare di aggiungere un db locale per maggiore sicurezza
												// nel non perdere i dati
	// private JButton btnDownloadDatabase;		// In futuro si può pensare di aggiungere un db locale per maggiore sicurezza
												// nel non perdere i dati	
	private JButton btnSetttings;
	
	public MainMenuFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super( Utils.fileToJSONObject( Setting.getSettings().getFrameTitlesLanguageFile() ).getString( FrameTitleJsonKey.MAIN_MENU ) );	
		this.setSize( 400, 600 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 150 );
		//
		Container c = this.getContentPane();
		
		c.setLayout( new GridLayout(7, 1) );
		// ---------------------------------------------------------------------------
		
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile()  );
		listener = new MainMenuActionListener( this );
		
		
		// Creazione bottoni ----------------------------------------------------------
		
		btnCalendar = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_CALENDAR ) );
		btnCalendar.addActionListener( listener );
		btnCalendar.setActionCommand( MainMenuActionListener.CALENDAR );
		
		btnAddCustomer = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_ADD_CUSTOMER ) );
		btnAddCustomer.addActionListener( listener );
		btnAddCustomer.setActionCommand( MainMenuActionListener.ADD_CUSTOMER);
		
		btnSearchCustomer = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SEARCH_CUSTOMER ) );
		btnSearchCustomer.addActionListener( listener );
		btnSearchCustomer.setActionCommand( MainMenuActionListener.SEARCH_CUSTOMER );
		
		btnAddEmployee = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_ADD_EMPLOYEE ) );
		btnAddEmployee.addActionListener( listener );
		btnAddEmployee.setActionCommand( MainMenuActionListener.ADD_EMPLOYEE );
		
		btnSearchEmployee = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SEARCH_EMPLOYEE ) );
		btnSearchEmployee.addActionListener( listener );
		btnSearchEmployee.setActionCommand( MainMenuActionListener.SEARCH_EMPLOYEE );
		
		/*
		// Bottoni per un futuro miglioramento del software
		btnUploadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_UPLOAD_DATABASE ) );
		btnUploadDatabase.addActionListener( listener );
		btnUploadDatabase.setActionCommand( MainMenuActionListener.UPLOAD_DATABASE );
		
		btnDownloadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_DOWNLOAD_DATABASE ) );
		btnDownloadDatabase.addActionListener( listener );
		btnDownloadDatabase.setActionCommand( MainMenuActionListener.DOWNLOAD_DATABASE );
		*/
		
		btnProfessionalRoleManager = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_PROFESSIONAL_ROLE_MANAGER ) );
		btnProfessionalRoleManager.addActionListener( listener );
		btnProfessionalRoleManager.setActionCommand( MainMenuActionListener.PROFESSIONAL_ROLE_MANAGER );
	
		
		btnSetttings = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SETTING ) );
		btnSetttings.addActionListener( listener );
		btnSetttings.setActionCommand( MainMenuActionListener.SETTING );
		
		
		// ----------------------------------------------------------------------------
		
		// Creazione bottoni ----------------------------------------------------------
		
		c.add( btnCalendar );
		c.add( btnAddCustomer );
		c.add( btnSearchCustomer );
		c.add( btnAddEmployee );
		c.add( btnSearchEmployee );
		c.add( btnProfessionalRoleManager );
		// c.add( btnUploadDatabase  );		// implementabile in futuro
		// c.add( btnDownloadDatabase );	// implementabile in futuro
		c.add( btnSetttings );			// implementabile in futuro
		
		// ----------------------------------------------------------------------------
		
		
		// Imposto come visibile la finestra
		this.setVisible( true );
	}

}
