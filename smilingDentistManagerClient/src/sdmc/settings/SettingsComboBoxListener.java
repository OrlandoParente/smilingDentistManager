package sdmc.settings;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.sound.midi.SysexMessage;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.utils.Utils;

public class SettingsComboBoxListener implements ItemListener {

	private JSONArray jsonArrLangList = Utils.fileToJSONArray( Setting.getSettings().getLanguagesFile() );
	
	private SettingsFrame settingsFrame;
	
	public SettingsComboBoxListener() {
		
	}
	
	public SettingsComboBoxListener( SettingsFrame settingsFrame ) {
		
		this.settingsFrame = settingsFrame;
	
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if( e.getStateChange() == e.SELECTED ) {
			
			System.out.println( "########################>>>>>>>>>>>>>>" + e.getItem() );
			
			jsonArrLangList.forEach(  joArr -> {
				JSONObject jo = ( JSONObject ) joArr;
				
				if( jo.getString( Setting.JSON_KEY_LANGUAGE_NAME ).equals( e.getItem() ) ) {
					
					Setting.getSettings().setLanguageCode( jo.getString( Setting.JSON_KEY_LANGUAGE_CODE ) );
					
				}
				
				
			} );
			
			
			
		}
		
	}

}
