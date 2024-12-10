package sdms.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateManager {
	
	private List<DateTimeFormatter> dateTimeFormatters;
	
	public DateManager() {
		
		this.dateTimeFormatters = List.of(
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
		
	}
	
	public LocalDate parseDate( String date ) {
	
		for( DateTimeFormatter formatter : this.dateTimeFormatters ) {
			
			try {
				
				return LocalDate.parse(date, formatter);
				
			} catch ( DateTimeParseException e ) {
				// try next format
			}
			
		}
		
		throw new DateTimeParseException("Invalid format data : " + date , date, 0);
	}
}
