package sdmc.calendar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import sdmc.combo_box_management.ComboBoxRenderer;
import sdmc.combo_box_management.customer.ComboBoxCustomerListener;
import sdmc.combo_box_management.customer.Customer;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.OnlyIntTextFieldListener;
import sdmc.utils.Utils;

public class AddAppointmentFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	private JSONObject btnNames;
	
	private Customer customerSelectedByComboBox;
	
	private AddAppointmentActionListener listener;
	private OnlyIntTextFieldListener onlyIntTextFieldListener;
	private ComboBoxCustomerListener comboBoxCustomerListener;
	
	// form panel stuff ------------------------
	
	private JLabel labelDataSeparator1;
	private JLabel labelDataSeparator2;
	private JLabel labelTimeSeparator; 
	
	private JPanel panelData;
	private JLabel labelData;
	private JTextField textFieldDay;
	private JTextField textFieldMonth;
	private JTextField textFieldYear;

	private JPanel panelTime;
	private JLabel labelTime;
	private JTextField textFieldHours;
	private JTextField textFieldMinutes;
	
	private JPanel panelCustomer;
	private JComboBox<Customer> comboBoxCustomer;
	private JLabel labelCustomer;
	
	// -----------------------------------------
	
	// bottom menu stuff ----
	JButton btnBackToCalendarFrame;
	JButton btnAddAppointment;
	
	// per quando viene richiamato dal bottone edit
	JButton btnDeleteAppointment;
	JButton btnEditAppointment;
	// ----------------------
	
	private JPanel panelForm;
	private JPanel panelBottomMenu;
	
	public AddAppointmentFrame( long id, String day, String month, String year, String hours, String minutes, 
								Customer customerSelectedByComboBox ) {
		
		this();
		
		getTextFieldDay().setText( day );
		getTextFieldMonth().setText( month );
		getTextFieldYear().setText( year );
		
		getTextFieldHours().setText( hours );
		getTextFieldMinutes().setText( minutes );
		
		System.out.println( "==================>" + customerSelectedByComboBox.getName() );
		
		
		 getComboBoxCustomer().setSelectedItem( customerSelectedByComboBox ); 
		
		panelBottomMenu.remove( btnAddAppointment );

		btnEditAppointment = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT ) );
		btnEditAppointment.addActionListener( new AddAppointmentActionListener( this, id, day, month, year,
																				hours, minutes, customerSelectedByComboBox .getId() + "" ) );
		btnEditAppointment.setActionCommand( AddAppointmentActionListener.EDIT_APPOINTMENT );
		
		panelBottomMenu.add( btnEditAppointment );
		
		panelBottomMenu.revalidate();
		panelBottomMenu.repaint();
		
	}
	
	public AddAppointmentFrame() {
		
		super( "ADD APPOINTMENT FRAME" );
		
		setLocation(500, 300);
		setSize(500, 300);

		Container c = getContentPane();
		c.setLayout( new BorderLayout() );
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile() );
		listener = new AddAppointmentActionListener( this );
		comboBoxCustomerListener = new ComboBoxCustomerListener( this );
		onlyIntTextFieldListener = new OnlyIntTextFieldListener();
		
		//---------------------------------------
		
		// PANEL FORM ##########################################################################
		
		panelForm = new JPanel( );
		panelForm.setLayout( new BoxLayout( panelForm, BoxLayout.Y_AXIS ) );
		
		
		
		// Panel Data --------------------------------------------------------------------------
		panelData = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		// Evita che si espande a dismisura in altezza
		panelData.setMaximumSize( new Dimension( (int) panelData.getMaximumSize().getWidth() , 40 ) );
		
		labelData = new JLabel("DATA :");
		
		labelDataSeparator1 = new JLabel( " - " );
		labelDataSeparator2 = new JLabel( " - " );
		textFieldDay = new JTextField(3);
		textFieldDay.setToolTipText("DAY");
		textFieldDay.addKeyListener( onlyIntTextFieldListener );

		
		textFieldMonth = new JTextField(3);
		textFieldMonth.setToolTipText("MONTH");
		textFieldMonth.addKeyListener( onlyIntTextFieldListener );

		
		textFieldYear = new JTextField(4);
		textFieldYear.setToolTipText("YEAR");
		textFieldYear.addKeyListener( onlyIntTextFieldListener );
		
		
		panelData.add( labelData );
		panelData.add( textFieldDay );
		panelData.add( labelDataSeparator1 );
		panelData.add( textFieldMonth );
		panelData.add( labelDataSeparator2 );
		panelData.add( textFieldYear );
		
		panelForm.add( panelData );
		// -------------------------------------------------------------------------------------
		
		// Panel Time --------------------------------------------------------------------------
		panelTime = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		// Evita che si espande a dismisura in altezza
		panelTime.setMaximumSize( new Dimension( (int) panelTime.getMaximumSize().getWidth() , 40 ) );
	
		
		
		labelTimeSeparator = new JLabel(" : ");
		
		labelTime = new JLabel( "ORA : " );
		
		textFieldHours = new JTextField(3);
		textFieldHours.addKeyListener( onlyIntTextFieldListener );
		textFieldDay.setToolTipText("HOUR");
		
		textFieldMinutes = new JTextField(3);
		textFieldMinutes.addKeyListener( onlyIntTextFieldListener );
		textFieldMinutes.setToolTipText("MIUNTES");
		
		
		panelTime.add( labelTime );
		panelTime.add( textFieldHours );
		panelTime.add( labelTimeSeparator );
		panelTime.add( textFieldMinutes );
		
		
		panelForm.add( panelTime );
		// -------------------------------------------------------------------------------------
		
		// Customer panel -----------------------------------------------------------------------
		panelCustomer = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		labelCustomer = new JLabel( "CLIENTE : " );
		
		comboBoxCustomer = new JComboBox<Customer>( Customer.getCustomerArray() );
		comboBoxCustomer.setRenderer( new ComboBoxRenderer() );
		comboBoxCustomer.addItemListener( comboBoxCustomerListener );
		
		panelCustomer.add( labelCustomer );
		panelCustomer.add( comboBoxCustomer );
		
		panelForm.add( panelCustomer );
		
		// -------------------------------------------------------------------------------------
		
		
		// #####################################################################################
		
		// PANEL BOTTOM MENU ####################################################################
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		btnBackToCalendarFrame = new JButton( btnNames.getString( ButtonJsonKey.BTN_BACK ) );
		btnBackToCalendarFrame.addActionListener( listener );
		btnBackToCalendarFrame.setActionCommand( AddAppointmentActionListener.BACK_TO_CALENDAR_FRAME );
		
		
		btnAddAppointment =  new JButton( btnNames.getString( ButtonJsonKey.CALENDAR_BTN_ADD_APPOINTMENT ) );
		btnAddAppointment.addActionListener( listener );
		btnAddAppointment.setActionCommand( AddAppointmentActionListener.ADD_APPOINTMENT );
		
		

		
		panelBottomMenu.add( btnBackToCalendarFrame );
		panelBottomMenu.add( btnAddAppointment );
		
		// #####################################################################################
		
		
		// ---------------------
		c.add( panelForm , BorderLayout.CENTER );
		c.add( panelBottomMenu, BorderLayout.SOUTH  );
		
		// rendo la finestra visibile
		setVisible( true );
	}

	public JTextField getTextFieldDay() {
		return textFieldDay;
	}

	public JTextField getTextFieldMonth() {
		return textFieldMonth;
	}

	public JTextField getTextFieldYear() {
		return textFieldYear;
	}

	public JTextField getTextFieldHours() {
		return textFieldHours;
	}

	public JTextField getTextFieldMinutes() {
		return textFieldMinutes;
	}

	public JComboBox<Customer> getComboBoxCustomer() {
		return comboBoxCustomer;
	}
	
	
	
	
}
