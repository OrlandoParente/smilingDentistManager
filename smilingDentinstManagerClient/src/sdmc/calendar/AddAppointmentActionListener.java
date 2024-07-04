package sdmc.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sdmc.combo_box_management.customer.Customer;
import sdmc.server_connection.HttpConnectionManager;

public class AddAppointmentActionListener implements ActionListener {

	private AddAppointmentFrame addAppointmentFrame;
	
	// Action Commands
	public static final String BACK_TO_CALENDAR_FRAME = "BACK_TO_CALENDAR_FRAME";
	public static final String ADD_APPOINTMENT = "ADD_APPOINTMENT";
	
	public static final String EDIT_APPOINTMENT = "EDIT_APPOINTMENT";
	public static final String DELETE_APPOINTMENT = "DELETE_APPOINTMENT";
	
	private long idAppointment;
	
	//
	private String hour;
	private String minutes;
	private String day;
	private String month;
	private String year;
	private String idCustomer;
	
	// costruttore per AddAppointmentFrame aperta da edit button
	private String oldHour;
	private String oldMinutes;
	private String oldDay;
	private String oldMonth;
	private String oldYear;
	private String oldIdCustomer;
	
	private String params;
	private int responseCode;
	
	public AddAppointmentActionListener(AddAppointmentFrame addAppointmentFrame, long idAppointment, String oldDay, String oldMonth, String oldYear,
										String oldHour, String oldMinutes,  String oldIdCustomer ) {
		
		this( addAppointmentFrame );
		
		this.idAppointment = idAppointment;
		this.oldHour = oldHour;
		this.oldMinutes = oldMinutes;
		this.oldDay = oldDay;
		this.oldMonth = oldMonth;
		this.oldYear = oldYear;
		this.oldIdCustomer = oldIdCustomer;
	}
	
	public AddAppointmentActionListener( AddAppointmentFrame addAppointmentFrame ) {
		this.addAppointmentFrame = addAppointmentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		
		switch( e.getActionCommand() ) {
		
		case BACK_TO_CALENDAR_FRAME:
			
			// Check message
			System.out.println("AddAppointmentActionListener ---> " + e.getActionCommand() );
			
			new CalendarFrame();
			addAppointmentFrame.dispose();
			
			break;
					
		case ADD_APPOINTMENT:
					
			// Check message
			System.out.println("AddAppointmentActionListener ---> " + e.getActionCommand() );
			
			addAppointment();
			
			// Ritorna al calendario
			new CalendarFrame();
			addAppointmentFrame.dispose();
			
			break;
					
		case EDIT_APPOINTMENT:
			
			// Check message
			System.out.println("AddAppointmentActionListener ---> " + e.getActionCommand() );
			
//			String parameters = "date=" + this.oldYear + "-" + this.oldMonth + "-" + this.oldDay 
//								+ "&time=" + this.oldHour + ":" + this.oldMinutes 
//								+ "&id_customer=" + this.oldIdCustomer;
//			
//			System.out.println("OLD PARAMETERS --> " + parameters );
//			
//			deleteAppointment( "id=" + this.idAppointment );
			deleteAppointment( );
			addAppointment();
			
			
			// Controllo responseCode == HTTP_OK
			
			// Ritorna al calendario
			new CalendarFrame();
			addAppointmentFrame.dispose();
			
			break;
			
			
		case DELETE_APPOINTMENT:
			
			// Check message
			System.out.println("AddAppointmentActionListener ---> " + e.getActionCommand() );
			
			deleteAppointment();
		
			// Ritorna al calendario
			new CalendarFrame();
			addAppointmentFrame.dispose();
			
			break;
		}
		
	}
	
	private void addAppointment() {
		
		// recupera i dati dal form
		this.fetchData();
		
		params = "date=" + this.year + "-" + this.month + "-" + this.day + "&time=" + this.hour + ":" + this.minutes 
				+ "&id_customer=" + this.idCustomer;
		
		System.out.println( "APOOINTMENT PARMAS -> " + params );
		
		responseCode = HttpConnectionManager.doPost( HttpConnectionManager.POST_APPOINTMENT, params ).getResponseCode() ;
		
		// Controllo responseCode == HTTP_OK

	}
	
	private void deleteAppointment() {
		
		// recupera i dati dal form
//		this.fetchData();
		
//		params = "date=" + this.year + "-" + this.month + "-" + this.day + "&time=" + this.hour + ":" + this.minutes 
//					+ "&id_customer=" + this.idCustomer;

		params = "id=" + this.idAppointment;
		
		responseCode = HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_APPOINTMENT_BY_ID, params);
		
		// Controllo responseCode == HTTP_OK

	}
	
	private void deleteAppointment( String parameters ) {
		

		responseCode = HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_APPOINTMENT_BY_ID, parameters );
		
		// Controllo responseCode == HTTP_OK

	}
	
	// recupera i dati dal form
	private void fetchData() {
		
		this.day = addAppointmentFrame.getTextFieldDay().getText();
		this.month = addAppointmentFrame.getTextFieldMonth().getText();
		this.year = addAppointmentFrame.getTextFieldYear().getText();
		
		this.hour = addAppointmentFrame.getTextFieldHours().getText();
		this.minutes = addAppointmentFrame.getTextFieldMinutes().getText();
		
		Customer customer = (Customer) addAppointmentFrame.getComboBoxCustomer().getSelectedItem() ;
		this.idCustomer = customer.getId() + "";
		
	}




}
