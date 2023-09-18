package sdmc.main;

import sdmc.utils.*;
import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;

public class MainMenuFrame extends JFrame {
	
	private JSONObject btnNames;
	private MainMenuActionListener listener;
	
	private JButton btnCalendar;
	private JButton btnAddCustomer;
	private JButton btnSearchCustomer;
	private JButton btnAddEmployee;
	private JButton btnSearchEmployee;
	private JButton btnUploadDatabase;
	private JButton btnDownloadDatabase;
	private JButton btnSetttings;
	
	public MainMenuFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super("MAIN MENU");	
		this.setSize( 400, 600 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 200 );
		
		//
		Container c = this.getContentPane();
		
		c.setLayout( new GridLayout( 8, 1) );
		// ---------------------------------------------------------------------------
		
		
		btnNames = Utils.fileToJSONObject( Utils.BTNS_ITALIAN_LANGUANGE );
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
		
		btnUploadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_UPLOAD_DATABASE ) );
		btnUploadDatabase.addActionListener( listener );
		btnUploadDatabase.setActionCommand( MainMenuActionListener.UPLOAD_DATABASE );
		
		btnDownloadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_DOWNLOAD_DATABASE ) );
		btnDownloadDatabase.addActionListener( listener );
		btnDownloadDatabase.setActionCommand( MainMenuActionListener.DOWNLOAD_DATABASE );
		
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
		c.add( btnUploadDatabase  );
		c.add( btnDownloadDatabase );
		c.add( btnSetttings );
		
		// ----------------------------------------------------------------------------
		
		
		// Imposto come visibile la finestra
		this.setVisible( true );
	}

}
