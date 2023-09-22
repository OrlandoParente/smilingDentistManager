package sdmc.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomerFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private AddCustomerActionListener listener;
	
	private JPanel panelBottomMenu;
	private JPanel panelFormMenu;

	private JButton btnMainMenu;
	private JButton btnAddCustomer;
	
	// ----------------------------
	private JLabel labelTaxIdCode;
	private JTextField textFieldTaxIdCode;
	private JPanel panelTaxIdCode;

	private JLabel labelName;
	private JTextField textFieldName;
	private JPanel panelName;
	
	private JLabel labelSurname;
	private JTextField textFieldSurname;
	private JPanel panelSurname;
	
	private JLabel labelBirthCity;
	private JTextField textFieldBirthCity;
	private JPanel panelBirthCity;
	
	private JLabel labelBirthCityProvince;
	private JTextField textFieldBirthCityProvince;	// preferibilmente la sigla provincia, ma lascio spazio per il nome completo
	private JPanel panelBirthCityProvince;
	
	private JLabel labelBirthDate;
	private JTextField textFieldBirthDate;
	private JPanel panelBirthDate;
	
	private JLabel labelResidenceStreet;
	private JTextField textFieldResidenceStreet;
	private JPanel panelResidenceStreet;
	private JLabel labelHouseNumber;
	private JTextField textFieldHouseNumber; 
	
	private JLabel labelResidenceCity;
	private JTextField textFieldResidenceCity;
	private JPanel panelResidenceCity;
	
	private JLabel labelResidenceCityCap;
	private JTextField textFieldResidenceCityCap;
	private JPanel panelResidenceCityCap;
	
	private JLabel labelResidenceProvince;
	private JTextField textFieldResidenceProvince;
	private JPanel panelResidenceProvince;
	
	private JLabel labelPhoneNumber;
	private JTextField textFieldPhoneNumber;
	private JPanel panelPhoneNumber;
	
	private JLabel labelPhoneNumber2;
	private JTextField textFieldPhoneNumber2;		// Generalmente telefono di casa
	private JPanel panelPhoneNumber2;
	
	
	private JLabel labelEMail;
	private JTextField textFieldEMail;
	private JPanel panelEMail;
	 
	// ----------------------------
	
	public AddCustomerFrame() {
		// inizializzazione del frame -----------------------------------------------
		super("ADD NEW CUSTOMER");	
		this.setSize( 400, 600 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 200 );
		
		//
		Container c = this.getContentPane();
		
		c.setLayout( new BorderLayout() );
		
		// inizializzazione listener
		listener = new AddCustomerActionListener( this );
		
		// ######################################################################
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( AddCustomerActionListener.MAIN_MENU );
		
		panelBottomMenu.add( btnMainMenu );
		
		
		btnAddCustomer = new JButton("AGGIUNGI");
		btnAddCustomer.addActionListener( listener );
		btnAddCustomer.setActionCommand( AddCustomerActionListener.ADD_CUSTOMER );
		
		panelBottomMenu.add( btnAddCustomer );
		// ######################################################################
		
		// ######################################################################
		
		panelFormMenu = new JPanel( new GridLayout(13,1) );
		
		// add panel tax code ---------------------------------------------------
		
		
		labelTaxIdCode = new JLabel("COD FISCALE : ");
		textFieldTaxIdCode = new JTextField(20);
		 
		// textFieldBirthCity.getV
		
		panelTaxIdCode = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelTaxIdCode.add( labelTaxIdCode );
		panelTaxIdCode.add(textFieldTaxIdCode);
		panelTaxIdCode.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelTaxIdCode  );
		// panelTaxIdCode.setVisible( true );
		
		// ----------------------------------------------------------------------
		
		// add panel name --- ---------------------------------------------------
		labelName = new JLabel("NOME* : ");
		textFieldName = new JTextField(20);
		
		panelName = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelName.add( labelName );
		panelName.add(textFieldName );
		panelName.setBackground( Color.LIGHT_GRAY );
	
		panelFormMenu.add( panelName );

		// ----------------------------------------------------------------------
		
		// add panel surname ----------------------------------------------------
		labelSurname= new JLabel("COGNOME* : ");
		textFieldSurname = new JTextField(20);
		
		panelSurname = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelSurname.add( labelSurname );
		panelSurname.add(textFieldSurname);
		panelSurname.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelSurname  );

		
		// ----------------------------------------------------------------------
		// Add Birth City panel -------------------------------------------------

		labelBirthCity= new JLabel("CITTÀ DI NASCITA : ");
		textFieldBirthCity= new JTextField(20);
				
		panelBirthCity = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelBirthCity.add( labelBirthCity);
		panelBirthCity.add(textFieldBirthCity);
		panelBirthCity.setBackground( Color.LIGHT_GRAY );
			
		panelFormMenu.add( panelBirthCity);

				
	    // ----------------------------------------------------------------------
		// Add Birth City Province panel ----------------------------------------

		labelBirthCityProvince = new JLabel("PROVINCIA : ");
		textFieldBirthCityProvince = new JTextField(20);
		
		panelBirthCityProvince = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelBirthCityProvince.add( labelBirthCityProvince );
		panelBirthCityProvince.add(textFieldBirthCityProvince);
		panelBirthCityProvince.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelBirthCityProvince );

		
		// ----------------------------------------------------------------------
		// add Birth Date panel -------------------------------------------------

		labelBirthDate= new JLabel("DATA DI NASCITA : ");
		textFieldBirthDate = new JTextField(20);
		
		panelBirthDate= new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelBirthDate.add( labelBirthDate );
		panelBirthDate.add(textFieldBirthDate );
		panelBirthDate.setBackground( Color.LIGHT_GRAY );
	
		panelFormMenu.add( panelBirthDate );

		
		
		// ----------------------------------------------------------------------
		// add Residence Street panel -------------------------------------------
		
		labelResidenceStreet = new JLabel("VIA : ");
		textFieldResidenceStreet = new JTextField(20);
		
		panelResidenceStreet = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelResidenceStreet.add( labelResidenceStreet );
		panelResidenceStreet.add(textFieldResidenceStreet);
		
	
		labelHouseNumber = new JLabel("N : ");
		textFieldHouseNumber = new JTextField(3);
				
		panelResidenceStreet.add( labelHouseNumber );
		panelResidenceStreet.add( textFieldHouseNumber );
		
		
		panelResidenceStreet.setBackground( Color.GREEN );
		panelFormMenu.add( panelResidenceStreet );

		
		// ----------------------------------------------------------------------
		// add ResidenceCity panel ----------------------------------------------

		labelResidenceCity= new JLabel("CITTÀ : ");
		textFieldResidenceCity = new JTextField(20);
		
		panelResidenceCity = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelResidenceCity.add( labelResidenceCity );
		panelResidenceCity.add(textFieldResidenceCity);
		panelResidenceCity.setBackground( Color.LIGHT_GRAY );
	
		panelFormMenu.add( panelResidenceCity );

		// ----------------------------------------------------------------------
		// add ResidenceCity CAP panel ------------------------------------------

		labelResidenceCityCap= new JLabel("CAP : ");
		textFieldResidenceCityCap = new JTextField(20);
		
		panelResidenceCityCap = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelResidenceCityCap.add( labelResidenceCityCap );
		panelResidenceCityCap.add(textFieldResidenceCityCap );
		panelResidenceCityCap.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelResidenceCityCap );

		// ----------------------------------------------------------------------
		// add ResidenceProvince panel -------------------------------------------

		labelResidenceProvince= new JLabel("PROVINCIA: ");
		textFieldResidenceProvince = new JTextField(20);
		
		panelResidenceProvince = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelResidenceProvince.add( labelResidenceProvince );
		panelResidenceProvince.add(textFieldResidenceProvince);
		panelResidenceProvince.setBackground( Color.LIGHT_GRAY );
	
		panelFormMenu.add( panelResidenceProvince );

		
		// ----------------------------------------------------------------------
		// add phone number panel -----------------------------------------------
		
		labelPhoneNumber= new JLabel("NUM. TELEFONO* : ");
		textFieldPhoneNumber = new JTextField(20);
		
		panelPhoneNumber = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelPhoneNumber.add( labelPhoneNumber );
		panelPhoneNumber.add(textFieldPhoneNumber);
		panelPhoneNumber.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelPhoneNumber );

		
		// ----------------------------------------------------------------------
		// Add phone number 2 panel ----------------------------------------------
		labelPhoneNumber2= new JLabel("NUM. TELEFONO 2: ");
		textFieldPhoneNumber2 = new JTextField(20);
		
		panelPhoneNumber2 = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelPhoneNumber2.add( labelPhoneNumber2 );
		panelPhoneNumber2.add(textFieldPhoneNumber2);
		panelPhoneNumber2.setBackground( Color.LIGHT_GRAY );
	
		panelFormMenu.add( panelPhoneNumber2 );

		
		// ----------------------------------------------------------------------
		// add email panel -------------------------------------------------------
		
		labelEMail= new JLabel("E-MAIL : ");
		textFieldEMail = new JTextField(20);
		
		panelEMail= new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		panelEMail.add( labelEMail );
		panelEMail.add(textFieldEMail);
		panelEMail.setBackground( Color.GREEN );
	
		panelFormMenu.add( panelEMail );

		
		// ----------------------------------------------------------------------
		
		
		// ######################################################################
		
		// Add panelFormMenu e panelBottomMenu al container c principale della finestra
		c.add( panelFormMenu , BorderLayout.CENTER );
		c.add( panelBottomMenu, BorderLayout.SOUTH );
		// ---------------------------------------------------------------------------
		
		// imposta finestra visibile
		this.setVisible( true );
	}
	
	
	
	public JTextField getTextFieldTaxIdCode() {
		return this.textFieldTaxIdCode;
	}



	public JTextField getTextFieldName() {
		return textFieldName;
	}



	public JTextField getTextFieldSurname() {
		return textFieldSurname;
	}



	public JTextField getTextFieldBirthCity() {
		return textFieldBirthCity;
	}



	public JTextField getTextFieldBirthCityProvince() {
		return textFieldBirthCityProvince;
	}



	public JTextField getTextFieldBirthDate() {
		return textFieldBirthDate;
	}



	public JTextField getTextFieldResidenceStreet() {
		return textFieldResidenceStreet;
	}
	
	public JTextField getTextFieldHouseNumber() {
		return textFieldHouseNumber;
	}



	public JTextField getTextFieldResidenceCity() {
		return textFieldResidenceCity;
	}

	public JTextField getTextFieldResidenceCityCap() {
		return textFieldResidenceCityCap;
	}


	public JTextField getTextFieldResidenceProvince() {
		return textFieldResidenceProvince;
	}



	public JTextField getTextFieldPhoneNumber() {
		return textFieldPhoneNumber;
	}



	public JTextField getTextFieldPhoneNumber2() {
		return textFieldPhoneNumber2;
	}



	public JTextField getTextFieldEMail() {
		return textFieldEMail;
	}
	
	
}
