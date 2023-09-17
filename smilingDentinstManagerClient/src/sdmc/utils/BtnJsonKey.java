package sdmc.utils;

// Raccolata di costanti
public enum BtnJsonKey {
	
	MAIN_MENU_CALENDAR("btnCalendar"),
	MAIN_MENU_ADD_CUSTOMER("btnAddCustomer"),
	MAIN_MENU_SEARCH_CUSTOMER("btnSearchCustomer"),
	MAIN_MENU_ADD_EMPLOYEE("btnAddEmployee"),
	MAIN_MENU_SEARCH_EMPLOYEE("btnSearchEmployee"),
	MAIN_MENU_UPLOAD_DATABASE("btnUploadDatabase"),
	MAIN_MENU_DOWNLOAD_DATABASE("btnDownloadDatabase"),
	MAIN_MENU_SETTING("btnSetttings");
	
	private String key;
	
	BtnJsonKey( String key ) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		
		return this.key;
	}
	
	public String getKey() {
		
		return this.key;
	}
}
