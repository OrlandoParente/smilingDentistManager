package sdmc.settings;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;


import sdmc.utils.ButtonJsonKey;
import sdmc.utils.FrameTitleJsonKey;
import sdmc.utils.LabelJsonKey;
import sdmc.utils.Utils;




public class SettingsFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	//
	private JSONObject btnNames;
	private JSONObject labelNames;
	
	// PANELS
	private JPanel panelMenu;
	private JPanel panelBottomMenu;
	
	private JPanel panelLanguage;
	private JPanel panelDatabase;
	
	// BUTTONS
	private JButton btnUploadDb;
	private JButton btnDownloadDb;
	private JButton btnMainMenu;
	private JButton btnSave;
	
	// LABELS
	private JLabel labelLanguage;
	
	// COMBO BOX
	private JComboBox<String> comboBoxLanguage;
	
	// listener
	private SettingsActionListener listener;
	private SettingsComboBoxListener comboBoxListener;
	
	//
	private int i;
	
	public SettingsFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super( Utils.fileToJSONObject( Setting.getSettings().getFrameTitlesLanguageFile() ).getString( FrameTitleJsonKey.SETTINGS ) );	
		this.setSize( 400, 600 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 150 );
		
		//
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile() );
		labelNames = Utils.fileToJSONObject( Setting.getSettings().getLabelsLanguageFile() );
		
		listener = new SettingsActionListener( this );
		comboBoxListener = new SettingsComboBoxListener( this );
		
		//
		Container c = this.getContentPane();

		c.setLayout( new BorderLayout());

		panelMenu = new JPanel();
		
		panelMenu.setLayout( new BoxLayout(panelMenu, BoxLayout.Y_AXIS ) );
		
		// ----- PANEL  LANGUAGE ------------
		
		panelLanguage = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		
		panelLanguage.setMaximumSize( new Dimension( (int)panelLanguage.getMaximumSize().getWidth(), 40));
		// panelLanguage.setBackground( Color.BLUE );
		
		labelLanguage = new JLabel( labelNames.getString( LabelJsonKey.LANGUAGE ) );
		
		// ---- JCOMBOBOX LANGUAGES
		JSONArray jsonArrLang = Utils.fileToJSONArray( Setting.getSettings().getLanguagesFile() );
		String [] languages = new String[ jsonArrLang.length() + 1 ];
		languages[0] = "";
		i = 1;
		
		jsonArrLang.forEach( joArr -> {
			JSONObject jo = (JSONObject) joArr;
			languages[i] = jo.getString( Setting.JSON_KEY_LANGUAGE_NAME );
			i ++;
		});
		
		comboBoxLanguage = new JComboBox<String>( languages );
		comboBoxLanguage.setSelectedIndex( 0 );
		comboBoxLanguage.addItemListener( comboBoxListener );
		
		// ------
		
		panelLanguage.add( labelLanguage );
		panelLanguage.add( comboBoxLanguage );
		
		// ----------------------------------------
		
		// ----- PANEL DATABASE -------------------
		panelDatabase  = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		

		panelDatabase.setMaximumSize( new Dimension( (int)panelDatabase.getMaximumSize().getWidth(), 40 ));
		
		System.out.println(  "SettingsFrame -> panelDatabase.setMaximumSize : " + panelDatabase.getHeight() );
		
		btnUploadDb = new JButton( btnNames.getString( ButtonJsonKey.BTN_UPLOAD_DB));
		btnUploadDb.addActionListener( listener );
		btnUploadDb.setActionCommand( SettingsActionListener.UPLOAD_DB );
		btnUploadDb.setEnabled(false);
		
		btnDownloadDb = new JButton(btnNames.getString( ButtonJsonKey.BTN_DOWNLOAD_DB));
		btnDownloadDb.addActionListener( listener );
		btnDownloadDb.setActionCommand( SettingsActionListener.DOWNLOAD_DB );
		btnDownloadDb.setEnabled(false);
		
		panelDatabase.add( btnUploadDb );
		panelDatabase.add( btnDownloadDb );
		
		// ----------------------------------------
		
		// PANEL BOTTOM MENU #################################################################
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING )  );
				
		btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( SettingsActionListener.MAIN_MENU );
				
				
		 btnSave = new JButton( btnNames.getString( ButtonJsonKey.BTN_SAVE ) );
		 btnSave.addActionListener( listener );
		 btnSave.setActionCommand( SettingsActionListener.SAVE );
				

		panelBottomMenu.add( btnMainMenu );
		panelBottomMenu.add( btnSave );
				
				
		// ###################################################################################
				
		
		//
		panelMenu.add( panelLanguage );
		panelMenu.add( panelDatabase );
		
		//
		c.add( panelMenu  , BorderLayout.CENTER );
		c.add( panelBottomMenu, BorderLayout.SOUTH );
		
		
		
		// 
		setVisible(true);

		
	}
	
	
}
