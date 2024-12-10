package sdmc.settings;

import java.io.File;

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
	private String messageLanguageFile;		// Ancora non implementato
	private String monthsLanguageFile;
	private String languagesFile;
	
	private String server;
	private String languageCode;
	private String username;
	private String password;
	
	// possibili valori impostazioni
	
	//
	private static final String SETTINGS_FILE = "settings.json";
	private static final String LANGUAGES_FILE = "languages.json";
	
	// chiavi file json
	public static final String JSON_KEY_LANGUAGE_CODE = "languageCode";
	public static final String JSON_KEY_LANGUAGE_NAME = "languageName";
	
	public static final String JSON_KEY_BTNS_LANGUAGE_FILE = "btnsLanguageFile";
	public static final String JSON_KEY_LABELS_LANGUAGE_FILE = "labelsLanguageFile";
	public static final String JSON_KEY_FRAME_TITLES_LANGUAGE_FILE = "frameTitlesLanguageFile";
	public static final String JSON_KEY_J_OPTION_MESSAGE_LANGUAGE_FILE = "messageLanguageFile";
	public static final String JSON_KEY_MONTHS_LANGUAGE_FILE = "monthsLanguageFile";

	public static final String JSON_KEY_SERVER = "server";
	public static final String JSON_KEY_USERNAME = "username"; 
	public static final String JSON_KEY_PASSWORD = "password";
	
	
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
		
		
		
		languageCode = joSettings.getString( Setting.JSON_KEY_LANGUAGE_CODE );
		
		this.setServer( joSettings.getString( Setting.JSON_KEY_SERVER ));
		this.setUsername( joSettings.getString(JSON_KEY_USERNAME) );
		this.setPassword( joSettings.getString(JSON_KEY_PASSWORD) );
		this.setBtnsLanguageFile( "lang" + File.separator + languageCode + File.separator + "btn_strings_" + languageCode + ".json"  );
		this.setLabelsLanguageFile( "lang" + File.separator +  languageCode + File.separator + "label_strings_" + languageCode + ".json" );
		this.setFrameTitlesLanguageFile( "lang" + File.separator +  languageCode + File.separator + "frame_title_strings_" + languageCode + ".json"  );
		this.setMessageLanguageFile( "lang" + File.separator +  languageCode + File.separator + "message_strings_" + languageCode + ".json"  );
		this.setMonthsLanguageFile( "lang" + File.separator +  languageCode + File.separator + "month_strings_" + languageCode + ".json" );
		this.setLanguagesFile( "lang" + File.separator + LANGUAGES_FILE );
		
		
		
		
		// Check Message
		System.out.print("\n \n SETTINGS : \n BTNS_LANGUAGE_FILE = " + btnsLanguageFile 
				+ " \n LABELS_LANGUAGE_FILE = "+ labelsLanguageFile 
				+" \n TITLES_LANGUAGE_FILE = " + frameTitlesLanguageFile
				+ " \n J_OPTION_MESSAGE_LANGUAGE_FILE = " + messageLanguageFile
				+ " \n LANGUAGE_CODE = " + languageCode
				+ " \n USER = " + username
				+ " \n PASSWORD = " + password
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

		// Non servono in quanto i vari path se li ricava da solo, in modo da usare il giusto File.separator 
		// // sia su windows che su linux in modo automatico
//		strSettings += "\"" + JSON_KEY_BTNS_LANGUAGE_FILE + "\" : \"" + this.getBtnsLanguageFile() + "\",";
//		strSettings += "\"" + JSON_KEY_LABELS_LANGUAGE_FILE + "\" : \"" + this.getLabelsLanguageFile() + "\",";
//		strSettings += "\"" + JSON_KEY_FRAME_TITLES_LANGUAGE_FILE + "\" : \"" + this.getFrameTitlesLanguageFile() + "\",";
//		strSettings += "\"" + JSON_KEY_J_OPTION_MESSAGE_LANGUAGE_FILE + "\" : \"" + this.getMessageLanguageFile()  + "\",";
		
		
		strSettings += "\"" + JSON_KEY_SERVER + "\" : \"" + this.getServer() + "\" , ";
		strSettings += "\"" + JSON_KEY_USERNAME + "\" : \"" + this.getLanguageCode() + "\" , ";
		strSettings += "\"" + JSON_KEY_PASSWORD + "\" : \"" + this.getLanguageCode() + "\" , ";
		strSettings += "\"" + JSON_KEY_LANGUAGE_CODE + "\" : \"" + this.getLanguageCode() + "\" ";
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

	public String getMessageLanguageFile() {
		return this.messageLanguageFile;
	}

	public void setMessageLanguageFile(String messageLanguageFile) {
		this.messageLanguageFile = messageLanguageFile;
	}

	public String getMonthsLanguageFile() {
		return monthsLanguageFile;
	}

	public void setMonthsLanguageFile(String monthsLanguageFile) {
		this.monthsLanguageFile = monthsLanguageFile;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getLanguagesFile() {
		return languagesFile;
	}

	public void setLanguagesFile(String languagesFile) {
		this.languagesFile = languagesFile;
	}

	public String getLanguageCode() {
		return languageCode;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
		
		this.setBtnsLanguageFile( "lang" + File.separator + languageCode + File.separator + "btn_strings_" + languageCode + ".json"  );
		this.setLabelsLanguageFile( "lang" + File.separator +  languageCode + File.separator + "label_strings_" + languageCode + ".json" );
		this.setFrameTitlesLanguageFile( "lang" + File.separator +  languageCode + File.separator + "frame_title_strings_" + languageCode + ".json"  );
		this.setMessageLanguageFile( "lang" + File.separator +  languageCode + File.separator + "message_strings_" + languageCode + ".json"  );
		this.setMonthsLanguageFile( "lang" + File.separator +  languageCode + File.separator + "month_strings_" + languageCode + ".json" );
		
	}
	
	
	
	
}
