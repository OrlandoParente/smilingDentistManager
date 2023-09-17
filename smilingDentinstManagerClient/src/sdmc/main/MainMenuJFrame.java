package sdmc.main;

import sdmc.utils.*;
import javax.swing.*;

import org.json.JSONObject;

import java.awt.*;

class MainMenuJFrame extends JFrame {
	
	private JSONObject btnNames;
	
	private JButton btnCalendar;
	private JButton btnAddCustomer;
	private JButton btnSearchCustomer;
	private JButton btnAddEmployee;
	private JButton btnSearchEmployee;
	private JButton btnUploadDatabase;
	private JButton btnDownloadDatabase;
	private JButton btnSetttings;
	
	public MainMenuJFrame() {
		
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
		
		// Creazione bottoni ----------------------------------------------------------
		
		JButton btnCalendar = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_CALENDAR ) );
		JButton btnAddCustomer = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_ADD_CUSTOMER ) );
		JButton btnSearchCustomer = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SEARCH_CUSTOMER ) );
		JButton btnAddEmployee = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_ADD_EMPLOYEE ) );
		JButton btnSearchEmployee = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SEARCH_EMPLOYEE ) );
		JButton btnUploadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_UPLOAD_DATABASE ) );
		JButton btnDownloadDatabase = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_DOWNLOAD_DATABASE ) );
		JButton btnSetttings = new JButton( btnNames.getString( ButtonJsonKey.MAIN_MENU_SETTING ) );
		
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
