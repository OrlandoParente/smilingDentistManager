package sdmc.employee;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import sdmc.utils.ButtonJsonKey;
import sdmc.utils.Utils;

public class AddEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JSONObject btnNames;
	
	// actionListener
	private ActionListener listener;
	
	//
	private JPanel panelBottomMenu;
	private JPanel panelForm;
	
	// Form Panel Stuff --------------
	
	private JTextField textFieldName;
	private JLabel labelName;
	private JPanel panelName;
	
	private JTextField textFieldSurname;
	private JLabel labelSurname;
	private JPanel panelSurname;
	
	private JTextField textFieldTitle;   
	private JLabel labelTitle;   
	private JPanel panelTitle;
	
	private JTextField textFieldBirthDate;
	private JLabel labelBirthDate;
	private JPanel panelBirthDate;
	
	private JTextField textFieldPhoneNumber;
	private JLabel labelPhoneNumber;
	private JPanel panelPhoneNumber;
	
	private JTextField textFieldPhoneNumber2;
	private JLabel labelPhoneNumber2;
	private JPanel panelPhoneNumber2;
	
	private JTextField textFieldEMail;
	private JLabel labelEMail;
	private JPanel panelEMail;
	// -------------------------------
	
	// BottomMenu panel Stuff --------
	private JButton btnAdd;
	private JButton btnMainMenu;
	
	// bottoni messi con il secondo costruttore
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnBackToSearchEmployee;
	// -------------------------------
	
	//
	private String id;
	private String name;
	private String surname; 
	private String title;   // e.g. Dott. , Dott.ssa, Sig. , Sig.ra , Sig.na,
	private String birthDate;
	private String phoneNumber;
	private String phoneNumber2; // Generalmente telefono di casa
	private String eMail;
	
			
	// COSTRUTTORE SECONDARIO, per accedervi da SearchEmployeeFrame		
	public AddEmployeeFrame(String id, 	String name, String surname, String title, String birthDate, 
							String phoneNumber, String phoneNumber2, String eMail) {
		
		this();
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.phoneNumber2 = phoneNumber2;
		this.eMail = eMail;
		
	}
			
			
	// COSTRUTTORE PRINCIPALE
	public AddEmployeeFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super("ADD NEW EMPLOYEE");	
		this.setSize( 400, 400 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 200 );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		btnNames = Utils.fileToJSONObject( Utils.BTNS_ITALIAN_LANGUANGE );
		listener = new AddEmployeeActionListener( this );
		
		// ----------------------------------------------------------------------------
		
		// PANEL BOTTM MENU -----------------------------------------------------------
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );

		btnMainMenu = new JButton( btnNames.getString( ButtonJsonKey.BTN_MAIN_MENU ) );
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( AddEmployeeActionListener.MAIN_MENU );
		
		btnAdd = new JButton( btnNames.getString( ButtonJsonKey.BTN_ADD ) );
		btnAdd.addActionListener( listener );
		btnAdd.setActionCommand( AddEmployeeActionListener.ADD_EMPLOYEE );
		
		panelBottomMenu.add( btnMainMenu );
		panelBottomMenu.add(btnAdd);
		
		c.add( panelBottomMenu , BorderLayout.SOUTH );
		
		// -----------------------------------------------------------------------------
		
		

		// PANEL FORM  -----------------------------------------------------------------
		
		panelForm = new JPanel( new GridLayout( 7, 1 ) );
		
		
		// Panel Name -----
		
		textFieldName = new JTextField(20);
		labelName = new JLabel("NAME : ");
		panelName = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelName.add( labelName );
		panelName.add( textFieldName );
		// ----------------
		
		// Panel Surname ------
		textFieldSurname = new JTextField(20);;
		labelSurname = new JLabel("SURNAME . ");
		panelSurname = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelSurname.add( labelSurname );
		panelSurname.add( textFieldSurname );
		
		// ----------------
		
		// Panel Title ----
		textFieldTitle = new JTextField(20);;  
		textFieldTitle.setToolTipText("e.g. Sig., Sig.ra, Sig.na, Dott. , Dott.ssa");
		labelTitle = new JLabel("TITLE : ");    
		panelTitle = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelTitle.add( labelTitle );
		panelTitle.add( textFieldTitle );
		// ----------------
		
		
		// Pale Birth Date ------
		textFieldBirthDate = new JTextField(20);;
		labelBirthDate= new JLabel("BIRTH DATE : ");
		panelBirthDate = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelBirthDate.add( labelBirthDate );
		panelBirthDate.add( textFieldBirthDate );
		
		// ----------------
		
		// Panel Phone Number -----
		textFieldPhoneNumber = new JTextField(20);;
		labelPhoneNumber = new JLabel("PHONE NUMBER : ");
		panelPhoneNumber = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelPhoneNumber.add( labelPhoneNumber );
		panelPhoneNumber.add( textFieldPhoneNumber );
		
		// ----------------
		
		// Panel Phone Number 2 -----
		textFieldPhoneNumber2 = new JTextField(20);;
		labelPhoneNumber2 = new JLabel("PHONE NUMBER 2 : ");
		panelPhoneNumber2 = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		

		panelPhoneNumber2.add( labelPhoneNumber2 );
		panelPhoneNumber2.add( textFieldPhoneNumber2 );
		// ----------------
		
		// Panel eMail ---
		textFieldEMail = new JTextField(20);;
		labelEMail = new JLabel("E-MAIL : ");
		panelEMail = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelEMail.add( labelEMail );
		panelEMail.add( textFieldEMail );
		// ----------------
		
		
		panelForm.add( panelName );
		panelForm.add( panelSurname );
		panelForm.add( panelTitle );
		panelForm.add( panelBirthDate );
		panelForm.add( panelPhoneNumber );
		panelForm.add( panelPhoneNumber2 );
		panelForm.add( panelEMail );
		
		c.add( panelForm, BorderLayout.CENTER );
		// -----------------------------------------------------------------------------
		
		
		
		// rende visibile il frame
		this.setVisible( true );
		
	}
	
	
}
