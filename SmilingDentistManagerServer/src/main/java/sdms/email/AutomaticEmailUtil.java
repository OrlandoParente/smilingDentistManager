package sdms.email;

import java.util.ArrayList;
import java.util.List;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import sdms.model.EmailSettings;

public class AutomaticEmailUtil {

	// default values ################
	public final static Integer AUTOMATIC_EMAIL_DISABLED = -1;
	public final static Integer AUTOMATIC_EMAIL_ONE_WEEK = 7;
	public final static Integer AUTOMATIC_EMAIL_THREE_MONTHS = 84;
	public final static Integer AUTOMATIC_EMAIL_FOUR_MONTHS = 112;
	public final static Integer AUTOMATIC_EMAIL_SIX_MONTHS = 168;
	public final static Integer AUTOMATIC_EMAIL_ONE_YEAR = 365;
	// ###############################

	
	public static List<Integer> getAutomaticEmailIntervals(){
		
		List<Integer> intervals = new ArrayList<>();
		
		intervals.add(AUTOMATIC_EMAIL_DISABLED);
		intervals.add(AUTOMATIC_EMAIL_ONE_WEEK);
		intervals.add(AUTOMATIC_EMAIL_THREE_MONTHS);
		intervals.add(AUTOMATIC_EMAIL_FOUR_MONTHS);
		intervals.add(AUTOMATIC_EMAIL_SIX_MONTHS);
		intervals.add(AUTOMATIC_EMAIL_ONE_YEAR);
		
		return intervals;
	}
	
	
}
