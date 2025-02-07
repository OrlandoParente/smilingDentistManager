package sdmc.calendar;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONObject;

import sdmc.settings.Setting;
import sdmc.utils.MonthJsonKey;
import sdmc.utils.Utils;

public class CalendarManager {
	
	
	private Calendar javaCalendar;
	
	private JSONObject monthNames;
	
	private int selectedDayNum;
	private int selectedMonthNum;
	private int selectedYear;
	
	
	// SIGLETON #########################################################################################
	
	private static CalendarManager calendarManager;
	
	private CalendarManager() {
		
		monthNames = Utils.fileToJSONObject( Setting.getSettings().getMonthsLanguageFile() );
		
		javaCalendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault() );
		// Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY );
		
		// Seleziona la data di oggi -----------------------
		
		this.selectedDayNum = this.getTodayDayNumber();
		this.selectedMonthNum = this.getTodayMonthNum();;
		this.selectedYear = this.getTodayYear();
		
		// -------------------------------------------------
		
	}
	
	public static CalendarManager getCalendarManager() {
		
		if( calendarManager == null )
			calendarManager = new CalendarManager();
	
		return calendarManager;
	}

	// ################################################################################################## 
	
	public String dataElementsToString ( int day, int month, int year ) {
		month ++; // perchè usiamo le costanti di java.util.Calendar che partono da 0 Gen = 0 ... Dic = 11
		return dataElementsToString (  day + "", month + "", year + "" );
	}
	
	public String dataElementsToString ( String day, String month, String year ) {
		return year + "-" + month + "-" + day;
	}
	
	public void decrementSelectDayDate() {
		
		selectedDayNum --;
		
		if( selectedDayNum == 0 ) {
			
			selectedMonthNum --;
			
			if( selectedMonthNum ==  Calendar.JANUARY - 1 ) {
				
				selectedYear --;
				selectedMonthNum = Calendar.DECEMBER;
				selectedDayNum = 31;
			} else {
				
				selectedDayNum = monthLenght( selectedYear, selectedMonthNum );
			}
			
		}
		
	}
	
	public void incrementSelectNexDayDate() {
	
		selectedDayNum ++;
		
		if( selectedDayNum > monthLenght(selectedYear, selectedMonthNum) ) {
			
			selectedDayNum = 1;
			selectedMonthNum ++;
			
			if( selectedMonthNum == Calendar.DECEMBER + 1 ) {

				selectedYear ++;
				selectedMonthNum = Calendar.JANUARY;
				
			}
		}
	}
	
	
	// Restituisce il numero di giorni di cui è composto il mese passato in base all'anno passato
	public int monthLenght ( int year, int month ) {
		
		if( month == Calendar.FEBRUARY ) {
			
			if( year % 4 == 0 ) return 29;
			else return 28;
			
		} if( month == Calendar.JANUARY || month == Calendar.MARCH || month == Calendar.MAY || month == Calendar.JULY 
				||  month == Calendar.AUGUST ||  month == Calendar.OCTOBER || month == Calendar.DECEMBER  ) {
			
			return 31;
		
		} 
		
		return 30;
		
		
	}
	
	private String convertMonthNumberToString ( int monthNumber ) {
		
		String month = "";
		
		switch( monthNumber ) {
		
		// Jenuary = 0 ... Dicember = 11
		
		case Calendar.JANUARY:
			
			month = monthNames.getString( MonthJsonKey.JENUARY );
			
			break;
			
		case Calendar.FEBRUARY:
			
			month = monthNames.getString( MonthJsonKey.FEBRUARY );
			
			break;
			
		case Calendar.MARCH:
			
			month = monthNames.getString( MonthJsonKey.MARCH );
			
			break;
			
		case Calendar.APRIL:
			
			month = monthNames.getString( MonthJsonKey.APRIL );
			
			break;
			
		case Calendar.MAY:
			
			month = monthNames.getString( MonthJsonKey.MAY );
			
			break;
			
		case Calendar.JUNE:
			
			month = monthNames.getString( MonthJsonKey.JUNE );
			
			break;
			
		case Calendar.JULY:
			
			month = monthNames.getString( MonthJsonKey.JULY );
			
			break;
			
		case Calendar.AUGUST:
			
			month = monthNames.getString( MonthJsonKey.AUGUST );
			
			break;
			
		case Calendar.SEPTEMBER:
			
			month = monthNames.getString( MonthJsonKey.SEPTEMBER );
			
			break;
			
		case Calendar.OCTOBER:
			
			month = monthNames.getString( MonthJsonKey.OCTOBER );
			
			break;
			
		case Calendar.NOVEMBER:
			
			month = monthNames.getString( MonthJsonKey.NOVEMBER );
			
			break;
			
		case Calendar.DECEMBER:
			
			month = monthNames.getString( MonthJsonKey.DECEMBER );
			
			break;
			
		
		
		}
		
		
		return month;
		
	}
	
	// GETTERS 
	
	// Selected date -----------------------------------------------
	public int getSelectedDayNumber() {
		
		return this.selectedDayNum;
	}
	
	public int getSelectedMonthNum () {
		return this.selectedMonthNum;
	}
	
	public String getSelectedStringMonth () {
		return convertMonthNumberToString( getSelectedMonthNum() );
	}
	
	public int getSelectedYear() {
		return this.selectedYear;
	}
	// -------------------------------------------------------------
	
	
	// Today date --------------------------------------------------
	
	public int getTodayDayNumber() {
		
		return javaCalendar.get( Calendar.DAY_OF_MONTH );
	}
	
	public int getTodayMonthNum () {
		return javaCalendar.get( Calendar.MONTH );
	}
	
	public String getTodayStringMonth () {
		return convertMonthNumberToString( getTodayMonthNum() );
	}
	
	public int getTodayYear() {
		return javaCalendar.get( Calendar.YEAR );
	}
	
	// --------------------------------------------------------------
}
