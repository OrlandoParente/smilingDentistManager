package sdms.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateAndTimeManager {
	
	private List<DateTimeFormatter> dateFormatters;
	private List<DateTimeFormatter> timeFormatters;
	
	public DateAndTimeManager() {
		
		this.dateFormatters = List.of(
				// Italian formats
				DateTimeFormatter.ofPattern("d/M/yyyy"),
				DateTimeFormatter.ofPattern("dd/MM/yyyy"),
				DateTimeFormatter.ofPattern("d/M/yy"),
				DateTimeFormatter.ofPattern("dd/MM/yy"),
				// American formats
				DateTimeFormatter.ofPattern("yyyy/M/d"),
				DateTimeFormatter.ofPattern("yyyy/MM/dd"),
				DateTimeFormatter.ofPattern("yyyy/M/d"),
				DateTimeFormatter.ofPattern("yyyy/MM/dd")
		);
		
		this.timeFormatters = List.of(
				// H = 24 hours format
				DateTimeFormatter.ofPattern("HH:mm:ss"),	// In this application we don't really need seconds
				DateTimeFormatter.ofPattern("HH:mm"),
				
				// h 12 hours format and a = pm or am
				DateTimeFormatter.ofPattern("hh:mm a"),
				// A = PM or AM
				DateTimeFormatter.ofPattern("hh:mm A"),
				
				// For avoid errors but dangerous -------
				DateTimeFormatter.ofPattern("H:m"),
				DateTimeFormatter.ofPattern("HH:m"),
				DateTimeFormatter.ofPattern("H:mm"),
				
				DateTimeFormatter.ofPattern("h:m a"),
				DateTimeFormatter.ofPattern("hh:m a"),
				DateTimeFormatter.ofPattern("h:mm a")
				// --------------------------------------
	    );
		
	}
	
	public LocalDate parseDate( String date ) {
	
		for( DateTimeFormatter formatter : this.dateFormatters ) {
			
			try {
				
				return LocalDate.parse(date, formatter);
				
			} catch ( DateTimeParseException e ) {
				// try next format
			}
			
		}
		
		throw new DateTimeParseException("Invalid format data : " + date , date, 0);
	}
	
	// NOT USED, WE CAN DELETE THIS METHOD
	// ISO 8601 format = "yyyy-MM-dd"
	// if strDate is not a valid date return LocalDate.MIN = 0000-01-01 
	public String strDateToISO8601FormatStrDateOrMinDate( String strDate ) {
		
		// String strDateFromKeyword;
		LocalDate localDateFromStrDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			// if strDate is a valid date (do not throw exception)
			// convert in the "yyyy-MM-dd" format
			localDateFromStrDate = this.parseDate(strDate);
			strDate = localDateFromStrDate.format(formatter);
			
			
		} catch ( Exception e ) { 
			// if strDate is not a valid date
			// set date = 0000-01-01 
			localDateFromStrDate = LocalDate.MIN;
			strDate = localDateFromStrDate.format(formatter);
		}
		
		return strDate;
	}
	
	public LocalTime parseTime( String time ) {
		
		for( DateTimeFormatter formatter : this.timeFormatters ) {
			
			try {
				
				return LocalTime.parse(time, formatter);
				
			} catch ( DateTimeParseException e ) {
				// try next format
			}
			
		}
		
		throw new DateTimeParseException("Invalid format time : " + time , time, 0);
	}
}
