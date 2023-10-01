package sdmc.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.combo_box_management.customer.Customer;
import sdmc.employee.EditEmployeeActionListener;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.Utils;

public class CalendarFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private CalendarActionListener listener;
	
	private JSONObject btnNames;
	private CalendarManager calendarManager;
	
	private JPanel panelTopMenu;
	private JPanel panelBottomMenu;
	private JPanel panelBodyCalendar;
	
	
	// bottom Menu  stuff ------------
	
	private JButton btnMainMenu;
	private JButton btnAddAppointment;
	// -------------------------------
	
	// topMenu stuff ------------------
	
	private JButton btnPrevDay;
	private JButton btnNextDay;
	private JLabel labelCurrentDateDay;
	private JLabel labelCurrentDateMonth;
	private JLabel labelCurrentDateYear;
	
	// -------------------------------
	
	public CalendarFrame(  ){
		
		// inizializzazione finestra
		super("CALENDAR");

		this.setLocation(500, 100);
		this.setSize( 700, 700 );
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile() );
		calendarManager = CalendarManager.getCalendarManager();
		
		// inizializzazione listener
		listener = new CalendarActionListener( this );

		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		// PANEL TOP MENU ####################################################################
		
		panelTopMenu = new JPanel( new FlowLayout( FlowLayout.CENTER )  );
		
		btnPrevDay = new JButton( btnNames.getString( ButtonJsonKey.CALENDAR_BTN_PREV_DAY ) );
		btnPrevDay.addActionListener( listener );
		btnPrevDay.setActionCommand( CalendarActionListener.PREV_DAY );
		
		btnNextDay = new JButton( btnNames.getString( ButtonJsonKey.CALENDAR_BTN_NEXT_DAT ) );
		btnNextDay.addActionListener( listener );
		btnNextDay.setActionCommand( CalendarActionListener.NEXT_DAY );
		
		labelCurrentDateDay = new JLabel( calendarManager.getSelectedDayNumber() + "" );
		labelCurrentDateMonth = new JLabel( calendarManager.getSelectedStringMonth() );
		labelCurrentDateYear = new JLabel( calendarManager.getSelectedYear() + "" );
		
		panelTopMenu.add( btnPrevDay );
		panelTopMenu.add( labelCurrentDateDay );
		panelTopMenu.add( labelCurrentDateMonth );
		panelTopMenu.add( labelCurrentDateYear );
		panelTopMenu.add( btnNextDay );
		
		
		// ###################################################################################
		
		
		// PANEL BOTTOM MENU #################################################################
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING )  );
		
		btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( CalendarActionListener.MAIN_MENU );
		
		
		btnAddAppointment = new JButton( btnNames.getString( ButtonJsonKey.CALENDAR_BTN_ADD_APPOINTMENT ) );
		btnAddAppointment.addActionListener( listener );
		btnAddAppointment.setActionCommand( CalendarActionListener.ADD_APPOINTMENT );
		

		panelBottomMenu.add( btnMainMenu );
		panelBottomMenu.add( btnAddAppointment );
		
		
		// ###################################################################################
		
		
		// PANEL BODY CALENDAR ###############################################################
		panelBodyCalendar = new JPanel(  );
		panelBodyCalendar.setLayout( new BoxLayout( panelBodyCalendar , BoxLayout.Y_AXIS ) );
		// panelBodyCalendar.setBackground( Color.BLUE );
		
		// ###################################################################################

		// Add panels to conerainer
		c.add( panelTopMenu, BorderLayout.NORTH );
		c.add( panelBottomMenu, BorderLayout.SOUTH );
		c.add( panelBodyCalendar, BorderLayout.CENTER );
		
		reloadPanelBodyCalendar();
		
		// rende visibile la finestra
		this.setVisible( true );
	}
	
	
	public void reloadLabelsCurrentDate() {
		
		labelCurrentDateDay.setText( calendarManager.getSelectedDayNumber() + ""  ); 
		labelCurrentDateMonth.setText( calendarManager.getSelectedStringMonth() ); 
		labelCurrentDateYear.setText( calendarManager.getSelectedYear() + "" ); 
	
	}
	
	public void reloadPanelBodyCalendar() {
		insertAppointmentsPanelsInPanelBodyCalendar( this.getJsonArrAppointments(), 
													calendarManager.dataElementsToString( calendarManager.getSelectedDayNumber(),
																						   calendarManager.getSelectedMonthNum(),
																						   calendarManager.getSelectedYear() ) );
	}
	
	public void insertAppointmentsPanelsInPanelBodyCalendar( JSONArray jsonArrAppointment, String currentDate ) {
		
		// ripulisce eventualmente il panel container
		panelBodyCalendar.removeAll();
		
		
		jsonArrAppointment.forEach( item -> {
			
			// Recupero dati necessari --------------------------
			
			JSONObject joAppointment = (JSONObject) item;
			
			// System.out.println( joAppointment.getString("date") + " EQUALS " + currentDate );
			
			// Salta gli appuntamenti non fissati nella currentDate
			if(  joAppointment.getString("date").equals( currentDate ) ) {
				
				JSONObject joCustomer = this.getJObjCustomer( joAppointment.getInt("idCustomer") + "" );
				
				// --------------------------------------------------
				
				JPanel panelAppointment = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
				
				// evita che box layout lo sparpagli per tutto lo spazio disponibile
				panelAppointment.setMaximumSize( 
						new Dimension( (int) panelAppointment.getMaximumSize().getWidth(),  40 )  );
				panelAppointment.setBackground( Color.CYAN );
				
				// In futuro si può salvare nel db se un appuntamente è stato svolto ( appointment.is_dove )
				// JCheckBox checkBoxIsDone = new JCheckBox();
				
				JTextField textFieldCustomerName = new JTextField( 15 );
				textFieldCustomerName.setText( joCustomer.getString("name") );
				textFieldCustomerName.setEditable( false );
	
				JTextField textFieldCustomerSurname = new JTextField( 15 );
				textFieldCustomerSurname.setText( joCustomer.getString("surname") );
				textFieldCustomerSurname.setEditable( false );
				
				JTextField textFieldTime = new JTextField( 5 );
				textFieldTime.setText( joAppointment.getString("time") );
				textFieldTime.setEditable( false );
				
				JButton btnEditAppointment = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT  ) );
				btnEditAppointment.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						
						Customer selectedCustomer = new Customer( joCustomer.getInt("id"), joCustomer.getString("taxIdCode"), joCustomer.getString("name"),
																	joCustomer.getString("surname"), joCustomer.getString("birthCity"),
																	joCustomer.getString("birthCityProvince"), joCustomer.getString("birthDate"),
																	joCustomer.getString("residenceStreet"), joCustomer.getString("houseNumber"), 
																	joCustomer.getString("residenceCity"), joCustomer.getString("residenceCityCap"),
																	joCustomer.getString("residenceProvince"), joCustomer.getString("phoneNumber"),
																	joCustomer.getString("phoneNumber2"), joCustomer.getString("eMail"));
						
						System.out.println( "####################### >>>>>>>>>> " + selectedCustomer.getName() );
						char [] time = joAppointment.getString("time").toCharArray();
						String hour = "";
						String minutes = "";
						boolean isHour = true;
						
						for( int i = 0; i < time.length; i ++ ) {
							
							if( time[i] == ':' ) {
								isHour = false;
								continue;
							}
							
							if( isHour ) {
								
								hour += time [i];
								
							} else {
								
								minutes += time [i];
							
							}
							
						}
						
						System.out.println( "CalendarFrame MINUTES = " + minutes + " --- HOUR =  " + hour +" TIME -> " +  joAppointment.getString("time") );
						
						int selectedMonth  = calendarManager.getSelectedMonthNum() + 1 ;// perchè il calendario di java parte da 0
						
						new AddAppointmentFrame(  calendarManager.getSelectedDayNumber() + "", 
								selectedMonth + "", calendarManager.getSelectedYear() + "",
								hour, minutes,  selectedCustomer );
						
						dispose();
						
					}
				} );
				
				JButton btnDeleteAppointment = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
				
				/* Oss.: Utilizzo una classe anonima perchè avendo la primary key formata da 3 elementi
				 * è difficile passarla tramite actionCommand.
				 */
				btnDeleteAppointment.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						deleteAppointment(joAppointment.getString("date"), joAppointment.getString("time"), 
											joAppointment.getInt("idCustomer") + "" );
						
						reloadPanelBodyCalendar();
					}
				} );
				
				
				btnDeleteAppointment.setBackground( Color.RED );
				
				
				// panelAppointment.add( checkBoxIsDone );
				panelAppointment.add( textFieldTime );
				panelAppointment.add( textFieldCustomerName );
				panelAppointment.add( textFieldCustomerSurname );
				panelAppointment.add( btnEditAppointment );
				panelAppointment.add( btnDeleteAppointment );
				
				panelBodyCalendar.add( panelAppointment );
			}			
		});
		
		
		
		// disegna gli elementi inseriti
		panelBodyCalendar.revalidate();
		panelBodyCalendar.repaint();
		
	}
	
	public JSONArray getJsonArrAppointments() {
		
		
		
		String strAppointmenrs = HttpConnectionManager.doGet( HttpConnectionManager.GET_APPOINTMENTS ).getResponseString();
		
		// Evita errore nel creare il jsonArray con stringa nulla o vuota ----
		
		if( strAppointmenrs == null ) {
			
			strAppointmenrs = "[]";
		
		} else if( strAppointmenrs.equals("") ) {
			
			strAppointmenrs = "[]";
			
		} 
		
		// -------------------------------------------------------------------
		
		JSONArray jsonArrAppointments = new JSONArray( strAppointmenrs );
		
		// Qui si può inserire controllo response.responseCode == HTTP_OK
		
		return jsonArrAppointments;
	}
	
	public JSONObject getJObjCustomer( String id_customer ) {
		
		
		
		String strCustomer = HttpConnectionManager.doGet( HttpConnectionManager.GET_CUSTOMER_BY_ID + id_customer ).getResponseString();
		
		// Evita errore nel creare il jsonObj con stringa nulla o vuota ----
		
		if( strCustomer == null ) {
			
			strCustomer = "{}";
		
		} else if( strCustomer.equals("") ) {
			
			strCustomer = "{}";
			
		} 
		
		// -------------------------------------------------------------------
		
		
		// Qui si può inserire controllo response.responseCode == HTTP_OK
		
		return new JSONObject( strCustomer );
	}
	
	public JSONObject getJObjDoctor( String id_doctor) {
		
		
		
		String strDoctor = HttpConnectionManager.doGet( HttpConnectionManager.GET_EMPLOYEE_BY_ID + id_doctor ).getResponseString();
		
		// Evita errore nel creare il jsonObj con stringa nulla o vuota ----
		
		if( strDoctor == null ) {
			
			strDoctor = "{}";
		
		} else if( strDoctor.equals("") ) {
			
			strDoctor = "{}";
			
		} 
		
		// -------------------------------------------------------------------
		
		
		// Qui si può inserire controllo response.responseCode == HTTP_OK
		
		return new JSONObject( strDoctor );
	}
	
	public void deleteAppointment(  String date, String time, String id ) {
		
		
		String params = "date=" + date + "&time=" + time + "&id_customer=" +id ;
		
		int responseCode = HttpConnectionManager.doDelete( HttpConnectionManager.DELETE_APPOINTMENT_BY_ID, params );
		
		// Qua ci può stare un controllo responseCode == HTTP_OK
		
		// this.reloadPanelBodyCalendar();
		
		
	}
	
}
