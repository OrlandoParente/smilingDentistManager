package sdmc.settings;

import org.json.JSONObject;

import sdmc.utils.Utils;

/*
 * In futuro è possibile creare un SettingFrame che permette di cambiare questi valori da GUI 
 */
public class Setting {

	// Files path in cui si trovano le stringe in una certa lingua
	private String btnsLanguageFile;
	private String labelsLanguageFile;				// Ancora non implementato
	private String frameTitlesLanguageFile;			// Ancora non implementato
	private String jOptionMessageLanguageFile;		// Ancora non implementato
	
	private String server;
	
	// possibili valori impostazioni
	
	//
	private static final String SETTINGS_FILE = "settings.json";
	
	// chiavi file json
	private static final String JSON_KEY_BTNS_LANGUAGE_FILE = "btnsLanguageFile";
	private static final String JSON_KEY_LABELS_LANGUAGE_FILE = "labelsLanguageFile";
	private static final String JSON_KEY_FRAME_TITLES_LANGUAGE_FILE = "frameTitlesLanguageFile";
	private static final String JSON_KEY_J_OPTION_MESSAGE_LANGUAGE_FILE = "jOptionMessageLanguageFile";
	
	private static final String JSON_KEY_SERVER = "server";
	
	// SIGLETON ---------------------------------
	
	private static boolean isInit;
	private static Setting settings;
	
	private Setting() {
		
		Setting.isInit = true;
		this.setDefaults();
	}
	
	public static Setting getSettings() {
		
		if( ! isInit ) {
			Setting.settings = new Setting();
		}
		
		return settings;
	}
	
	// ------------------------------------------
	
	// Inizializza le impostazioni con i valori nel file settings.json
	private void setDefaults() {
		
		JSONObject joSettings =  Utils.fileToJSONObject( Setting.SETTINGS_FILE );
		
		this.setBtnsLanguageFile( joSettings.getString( Setting.JSON_KEY_BTNS_LANGUAGE_FILE ) );
		this.setLabelsLanguageFile( joSettings.getString( Setting.JSON_KEY_LABELS_LANGUAGE_FILE ) );
		this.setFrameTitlesLanguageFile( joSettings.getString( Setting.JSON_KEY_FRAME_TITLES_LANGUAGE_FILE) );
		this.setjOptionMessageLanguageFile( joSettings.getString( Setting.JSON_KEY_J_OPTION_MESSAGE_LANGUAGE_FILE) );
		this.setServer( joSettings.getString( JSON_KEY_SERVER ));
		
		// Check Message
		System.out.print("\n \n SETTINGS : \n BTNS_LANGUAGE_FILE = " + btnsLanguageFile 
				+ " \n LABELS_LANGUAGE_FILE = "+ labelsLanguageFile 
				+" \n TITLES_LANGUAGE_FILE = " + frameTitlesLanguageFile
				+ " \n J_OPTION_MESSAGE_LANGUAGE_FILE = " + jOptionMessageLanguageFile 
				+ " \n SERVER = " + server + "\n \n \n" );
		
	}
	
	
	/*
	 * Sovrascrive le impostazioni su file settings.json
	 */
	public void saveCurrentSettings() {
		Utils.writeOnFile( this.getJsonObjStringCurrentSetting(), Setting.SETTINGS_FILE, false);
	}
	
	private String getJsonObjStringCurrentSetting (){
		
		String strSettings = "{";
		
		strSettings += "\"" + JSON_KEY_BTNS_LANGUAGE_FILE + "\" : \"" + this.getBtnsLanguageFile() + "\",";
		strSettings += "\"" + JSON_KEY_LABELS_LANGUAGE_FILE + "\" : \"" + this.getLabelsLanguageFile() + "\",";
		strSettings += "\"" + JSON_KEY_FRAME_TITLES_LANGUAGE_FILE + "\" : \"" + this.getFrameTitlesLanguageFile() + "\",";
		strSettings += "\"" + JSON_KEY_J_OPTION_MESSAGE_LANGUAGE_FILE + "\" : \"" + this.getjOptionMessageLanguageFile()  + "\",";
		strSettings += "\"" + JSON_KEY_SERVER + "\" : \"" + this.getServer() + "\" ";
		strSettings += "}";
		return strSettings;
	}
	
	// GETTERS AND SETTERS

	public String getBtnsLanguageFile() {
		return this.btnsLanguageFile;
	}

	public void setBtnsLanguageFile(String btnsLanguageFile) {
		this.btnsLanguageFile = btnsLanguageFile;
	}

	public  String getLabelsLanguageFile() {
		return this.labelsLanguageFile;
	}

	public void setLabelsLanguageFile(String labelsLanguageFile) {
		this.labelsLanguageFile = labelsLanguageFile;
	}

	public  String getFrameTitlesLanguageFile() {
		return this.frameTitlesLanguageFile;
	}

	public void setFrameTitlesLanguageFile(String frameTitlesLanguageFile) {
		this.frameTitlesLanguageFile = frameTitlesLanguageFile;
	}

	public String getjOptionMessageLanguageFile() {
		return this.jOptionMessageLanguageFile;
	}

	public void setjOptionMessageLanguageFile(String jOptionMessageLanguageFile) {
		this.jOptionMessageLanguageFile = jOptionMessageLanguageFile;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	
}
