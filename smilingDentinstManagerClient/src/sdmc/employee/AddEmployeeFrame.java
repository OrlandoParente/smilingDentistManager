package sdmc.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.combo_box_management.ComboBoxRenderer;
import sdmc.combo_box_management.professional_role.ComboBoxProfessionalRoleListener;
import sdmc.combo_box_management.professional_role.ProfessionalRole;
import sdmc.main.MainMenuFrame;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.FrameTitleJsonKey;
import sdmc.utils.LabelJsonKey;
import sdmc.utils.Utils;

public class AddEmployeeFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JSONObject btnNames;
	private JSONObject labelNames;
	
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
	
	// private JComboBox<ProfessionalRole> comboBoxProfessionalRole;
	// private JLabel labelProfessionalRole;
	private JPanel panelProfessionalRolesContainer;
	
	private ComboBoxProfessionalRoleListener comboBoxProfessionalRoleListener;
	
	private int numProfessionalRole; 	// tiene il conto di quanti professionalRole panel inserire
	private int maxNumProfessionalRole; // conta quanti professional role sono presenti nel database 
										// e quindi quanti professional role possono essere associati al nuovo employee
	private boolean isInitializeProfessionalRoleLogic;
	private int [] arrProfessionalRoleIds ;
	// -------------------------------
	
	// BottomMenu panel Stuff --------
	private JButton btnAdd;
	private JButton btnMainMenu;
	private JButton btnAddNewProfessionalRole;
	
	// bottoni messi con il secondo costruttore
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnBackToSearchEmployee;
	// -------------------------------
	
	//
	private String idFromSearchEmployeeFrame;

			
	// COSTRUTTORE SECONDARIO, per accedervi da SearchEmployeeFrame		
	public AddEmployeeFrame(String id, 	String name, String surname, String title, String birthDate, 
							String phoneNumber, String phoneNumber2, String eMail, int [] arrProfessionalRoleIds) {
		
		this();
		
		this.idFromSearchEmployeeFrame = id;
		textFieldName.setText( name );
		textFieldSurname.setText( surname );
		textFieldTitle.setText( title );
		textFieldBirthDate.setText( birthDate ); 
		textFieldPhoneNumber.setText( phoneNumber );
		textFieldPhoneNumber2.setText( phoneNumber2 );
		textFieldEMail.setText(  eMail );
		this.arrProfessionalRoleIds = arrProfessionalRoleIds;
		
		this.insertProfessionalRolePanelsInProfessionalRolesContainer( arrProfessionalRoleIds );
		
		// PANEL BOTTM MENU -----------------------------------------------------------
		
		// già impostato dal costruttore principale
		// panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );

		// ripulisce il panel dai pulsanti messi dal costruttore principale
		panelBottomMenu.removeAll();
		
		btnBackToSearchEmployee = new JButton( btnNames.getString( ButtonJsonKey.BTN_BACK ) );
		btnBackToSearchEmployee.addActionListener( listener );
		btnBackToSearchEmployee.setActionCommand( AddEmployeeActionListener.BACK_TO_SEARCH_EMPLOYEE );
				
		btnEdit = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT ) );
		btnEdit.addActionListener( listener );
		btnEdit.setActionCommand( AddEmployeeActionListener.EDIT_EMPLOYEE );
		
		btnDelete= new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
		btnDelete.addActionListener( listener );
		btnDelete.setActionCommand( AddEmployeeActionListener.DELETE_EMPLOYEE );
		btnDelete.setBackground( Color.RED );
		
		
		btnAddNewProfessionalRole = new JButton( btnNames.getString( ButtonJsonKey.EMPLOYEE_BTN_ADD_NEW_PROFESSIONAL_ROLE ) );
		btnAddNewProfessionalRole.addActionListener( listener );
		btnAddNewProfessionalRole.setActionCommand( AddEmployeeActionListener.ADD_NEW_PROFESSIONAL_ROLE );
				
		panelBottomMenu.add( btnBackToSearchEmployee );
		panelBottomMenu.add( btnEdit );
		panelBottomMenu.add( btnDelete );
		panelBottomMenu.add( btnAddNewProfessionalRole );
			
		// Ricarica la grafica sul panel bottom menu
		panelBottomMenu.revalidate();
		panelBottomMenu.repaint();
		// c.add( panelBottomMenu , BorderLayout.SOUTH );
				
		// -----------------------------------------------------------------------------
				
		
		
	}
			
			
	// COSTRUTTORE PRINCIPALE
	public AddEmployeeFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super( Utils.fileToJSONObject( Setting.getSettings().getFrameTitlesLanguageFile() ).getString( FrameTitleJsonKey.ADD_EMPLOYEE ) );	
		this.setSize( 700, 500 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 200 );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile()  );
		labelNames = Utils.fileToJSONObject( Setting.getSettings().getLabelsLanguageFile() );
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
		
		btnAddNewProfessionalRole = new JButton( btnNames.getString( ButtonJsonKey.EMPLOYEE_BTN_ADD_NEW_PROFESSIONAL_ROLE ) );
		btnAddNewProfessionalRole.addActionListener( listener );
		btnAddNewProfessionalRole.setActionCommand( AddEmployeeActionListener.ADD_NEW_PROFESSIONAL_ROLE );
		
		panelBottomMenu.add( btnMainMenu );
		panelBottomMenu.add(btnAdd);
		panelBottomMenu.add( btnAddNewProfessionalRole );
		
		c.add( panelBottomMenu , BorderLayout.SOUTH );
		
		// -----------------------------------------------------------------------------
		
		

		// PANEL FORM  -----------------------------------------------------------------
		
		panelForm = new JPanel( );
		panelForm.setLayout( new BoxLayout( panelForm, BoxLayout.Y_AXIS ));
		
		
		// Panel Name -----
		
		textFieldName = new JTextField(20);
		labelName = new JLabel( labelNames.getString( LabelJsonKey.NAME ) );
		panelName = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelName.add( labelName );
		panelName.add( textFieldName );
		// ----------------
		
		// Panel Surname ------
		textFieldSurname = new JTextField(20);;
		labelSurname = new JLabel( labelNames.getString( LabelJsonKey.SURNAME ) );
		panelSurname = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelSurname.add( labelSurname );
		panelSurname.add( textFieldSurname );
		
		// ----------------
		
		// Panel Title ----
		textFieldTitle = new JTextField(20);;  
		textFieldTitle.setToolTipText("e.g. Sig., Sig.ra, Sig.na, Dott. , Dott.ssa");
		labelTitle = new JLabel( labelNames.getString( LabelJsonKey.TITLE ) );    
		panelTitle = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelTitle.add( labelTitle );
		panelTitle.add( textFieldTitle );
		// ----------------
		
		
		// Pale Birth Date ------
		textFieldBirthDate = new JTextField(20);;
		labelBirthDate= new JLabel( labelNames.getString( LabelJsonKey.BIRTH_DATE ) );
		panelBirthDate = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelBirthDate.add( labelBirthDate );
		panelBirthDate.add( textFieldBirthDate );
		
		// ----------------
		
		// Panel Phone Number -----
		textFieldPhoneNumber = new JTextField(20);;
		labelPhoneNumber = new JLabel( labelNames.getString( LabelJsonKey.PHONE_NUMBER ) );
		panelPhoneNumber = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelPhoneNumber.add( labelPhoneNumber );
		panelPhoneNumber.add( textFieldPhoneNumber );
		
		// ----------------
		
		// Panel Phone Number 2 -----
		textFieldPhoneNumber2 = new JTextField(20);;
		labelPhoneNumber2 = new JLabel( labelNames.getString( LabelJsonKey.PHONE_NUMBER_2 ) );
		panelPhoneNumber2 = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		

		panelPhoneNumber2.add( labelPhoneNumber2 );
		panelPhoneNumber2.add( textFieldPhoneNumber2 );
		// ----------------
		
		// Panel eMail ---
		textFieldEMail = new JTextField(20);;
		labelEMail = new JLabel( labelNames.getString( LabelJsonKey.E_MAIL ) );
		panelEMail = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		panelEMail.add( labelEMail );
		panelEMail.add( textFieldEMail );
		// ----------------
		
		
		// panelAddProfessionalRolesContainer -----------------
		
		panelProfessionalRolesContainer = new JPanel(  );
		
		// se lo metto direttamente nel costruttore del JPanel non funziona
		panelProfessionalRolesContainer.setLayout( new BoxLayout( panelProfessionalRolesContainer , BoxLayout.Y_AXIS ) );
		
		System.out.println( "AddEmployeeFrame -> isInitializeProfessionalRoleLogic -> " + isInitializeProfessionalRoleLogic );
		
		this.initializeProfessionalRoleLogic();
		// inizializzo il listener del professional role combo box
		comboBoxProfessionalRoleListener = new ComboBoxProfessionalRoleListener();
		
		this.insertProfessionalRolePanelsInProfessionalRolesContainer();
		
		// --------------------------------------------
		
		panelForm.add( panelName );
		panelForm.add( panelSurname );
		panelForm.add( panelTitle );
		panelForm.add( panelBirthDate );
		panelForm.add( panelPhoneNumber );
		panelForm.add( panelPhoneNumber2 );
		panelForm.add( panelEMail );
		
		panelForm.add( panelProfessionalRolesContainer );
		
		c.add(   new JScrollPane( panelForm ) , BorderLayout.CENTER );
		// -----------------------------------------------------------------------------
		
		
		
		// rende visibile il frame
		this.setVisible( true );
		
		
		// CONTROLLO e gestione ERRORE nessun professional role nel db
		this.checkProfessionalRoleEnoughtQuantityInDb();
		
		
	}
	
	// Resetta il panel dei combo box professional role con uno solo 1, come appena aperta il add Employee Frame
	public void resetProfessionalRolePanel() {
		
		this.isInitializeProfessionalRoleLogic = false;
		
		this.insertProfessionalRolePanelsInProfessionalRolesContainer();
	}
	
	// Popola il panel dei combo box professional role partento dall'array di partenza
	// Usato per popolare i combo box dalla chiamata dal SearchEmployeeFrame -> Edit Employee
	private void insertProfessionalRolePanelsInProfessionalRolesContainer( int [] arrProfessionalRoleIds ) {
		
		// reset Professional Roles Panel
		panelProfessionalRolesContainer.removeAll();
		
		numProfessionalRole = arrProfessionalRoleIds.length;
		
		for( int i = 0; i < numProfessionalRole ; i ++ ) {
			
			JLabel labelProfessionalRole = new JLabel( labelNames.getString( LabelJsonKey.SELECT_PROFESSIONAL_ROLE ) );
			ProfessionalRole arrProfessionalRoles [] = ProfessionalRole.getProfessionalRoleArray();
			
			JComboBox<ProfessionalRole> comboBoxSelectProfessionalRole = new JComboBox<ProfessionalRole>( arrProfessionalRoles  );
		
			// Serve a inserire nel combo Box solo il nome del Professional Role
			comboBoxSelectProfessionalRole.setRenderer( new ComboBoxRenderer() );
			comboBoxSelectProfessionalRole.addActionListener( listener );
			comboBoxSelectProfessionalRole.setActionCommand( i + "" );
			
			// ------ Cerca il Professional Role selezionato per impostarlo nell combo box ----------------
			
			ProfessionalRole selectedProfessionalRole = null;
			for( int j = 0; j < arrProfessionalRoles.length ; j ++ ) {
				if( arrProfessionalRoles[j].getId() == arrProfessionalRoleIds[i] ) {
					selectedProfessionalRole = arrProfessionalRoles[j];
					break;
				}
				
			}
			
			// --------------------------------------------------------------------------------------------

			comboBoxSelectProfessionalRole.setSelectedItem( selectedProfessionalRole );

			
			
			
			JButton btnDeleteProfessionalRolePanel = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
			btnDeleteProfessionalRolePanel.addActionListener( listener );
			btnDeleteProfessionalRolePanel.setActionCommand( AddEmployeeActionListener.DELETE_A_PROFESSIONAL_ROLE_PANEL );
			btnDeleteProfessionalRolePanel.setBackground( Color.RED );
			
			// Un dipendente deve avere almeno un professional role
			if( i == 0 )
				btnDeleteProfessionalRolePanel.setEnabled( false );
		
			JPanel panelProfessionalRole = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
			
			panelProfessionalRole.add( labelProfessionalRole );
			panelProfessionalRole.add( comboBoxSelectProfessionalRole );
			panelProfessionalRole.add( btnDeleteProfessionalRolePanel );
			
			panelProfessionalRolesContainer.add( panelProfessionalRole );
			
			
		}

		
		// "disegna" i componenti 
		panelProfessionalRolesContainer.revalidate();
		panelProfessionalRolesContainer.repaint();
		
		
	}
	
	// Popola il panel dei combo box professional roles con quanti richiesti, inizialmente 1
	private void insertProfessionalRolePanelsInProfessionalRolesContainer() {
		
		if( ! isInitializeProfessionalRoleLogic  )
			this.initializeProfessionalRoleLogic();

		// CONTROLLO e gestione ERRORE nessun professional role nel db
		this.checkProfessionalRoleEnoughtQuantityInDb();

		
		// Oss.: In questo modo l'utente perderà i ruoli selezionati fin'ora
		// Per conservare i valori selezionati fin'ora servirebbero molte più righe di codice, 
		// ma dato che realisticamente i ruoli associati ad un impiegato sono al massimo fino a 4, 
		// preferisco tenere, almeno per ora, il codice più leggero e leggibile
		arrProfessionalRoleIds = new int[ numProfessionalRole ];

		// Ripulisce il panel
		panelProfessionalRolesContainer.removeAll();
		
		for( int i = 0; i < numProfessionalRole ; i ++ ) {
			
			JLabel labelProfessionalRole = new JLabel( labelNames.getString( LabelJsonKey.SELECT_PROFESSIONAL_ROLE ) );
			ProfessionalRole arrProfessionalRoles [] = ProfessionalRole.getProfessionalRoleArray();
			
			JComboBox<ProfessionalRole> comboBoxSelectProfessionalRole = new JComboBox<ProfessionalRole>( arrProfessionalRoles  );
		
			// Inizializza
			arrProfessionalRoleIds[i] = arrProfessionalRoles[0].getId();
			
			// Serve a inserire nel combo Box solo il nome del Professional Role
			comboBoxSelectProfessionalRole.setRenderer( new ComboBoxRenderer() );
			
			comboBoxSelectProfessionalRole.addActionListener( listener );
			comboBoxSelectProfessionalRole.setActionCommand( i + "" );

			JButton btnDeleteProfessionalRolePanel = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
			btnDeleteProfessionalRolePanel.addActionListener( listener );
			btnDeleteProfessionalRolePanel.setActionCommand( AddEmployeeActionListener.DELETE_A_PROFESSIONAL_ROLE_PANEL );
			btnDeleteProfessionalRolePanel.setBackground( Color.RED );
			
			// Un dipendente deve avere almeno un professional role
			if( i == 0 )
				btnDeleteProfessionalRolePanel.setEnabled( false );
		
			JPanel panelProfessionalRole = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
			
			panelProfessionalRole.add( labelProfessionalRole );
			panelProfessionalRole.add( comboBoxSelectProfessionalRole );
			panelProfessionalRole.add( btnDeleteProfessionalRolePanel );
			
			panelProfessionalRolesContainer.add( panelProfessionalRole );
			
			
		}
		
		// "disegna" il panel con i nuovi elementi
		panelProfessionalRolesContainer.revalidate();
		panelProfessionalRolesContainer.repaint();
		
	}
	 
	
	private void initializeProfessionalRoleLogic() {
		
		this.isInitializeProfessionalRoleLogic = true;
		
		
		// Inizializzazione del maxNumProfessionalRole -----
		RequestResponse response = HttpConnectionManager.doGet( HttpConnectionManager.GET_PROFESSIONAL_ROLES );
		
		if( response.getResponseString() == null || response.getResponseCode() != HttpsURLConnection.HTTP_OK ) {
			
			this.maxNumProfessionalRole = 0;
			
		} else if ( response.getResponseString().equals("") ) {
			
			this.maxNumProfessionalRole = 0;
			
		} else {
			
			JSONArray jsonArr = new JSONArray( response.getResponseString() );
			this.maxNumProfessionalRole = jsonArr.length();
		}
		
	// -------------------------------------------------
		
		// inizializzazione numProfessionalRole che deve essere almeno uno all'inizio
		this.numProfessionalRole = 1;
		
	}
	
	public void incrementNumProfessionalRole() {
		
		if( this.numProfessionalRole < this.maxNumProfessionalRole ) {
			
			this.numProfessionalRole ++;
			// aggiorna la GUI
			this.insertProfessionalRolePanelsInProfessionalRolesContainer();
			
		} else { // ERRORE : Si vuole associare più professional role di quelli disponibili
			
			// Messaggio di Errore
			JOptionPane.showConfirmDialog( this, "Non ci sono altri ruoli professionali disponibili, puoi aggiungerli in MAIN MENU -> GESTIONE RUOLO PROFESSIONALE", 
					"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
			
		}
	}
	
	public void decrementNumProfessionalRole() {
		
		if( this.numProfessionalRole > 0 ) {
			
			this.numProfessionalRole --;
			// aggiorna la GUI
			this.insertProfessionalRolePanelsInProfessionalRolesContainer();
			
		} else { // ERRORE : Si vuole associare 0 professional role al dipendente			
			// Messaggio di Errore
			JOptionPane.showConfirmDialog( this, "Ogni dipendente deve avere almeno un ruolo professionale associato",
					"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
			
		}
	}


	/*
	 * Imposta ad un certo indice dell'array arrProfessionalRole, l'id di un certo ProfessionalRole
	 * DOVE l'indice dell'array sta ad indicare un certo comboBox a cui è associato tramite l'action Command
	 * MENTRE il ProfessionalRoleId serve a recuperare in seguito il Professional Role selezionato dal combo box in questione
	 */
	public void setSelectedProfessionalRoleInTheProfessionalRoleArray( int arrayIndex, int professionalRoleId ) {
		
		this.arrProfessionalRoleIds[ arrayIndex ] = professionalRoleId;
		
		
		String arr = "arrProfessionalRoleIds ---> [";
		for( int i = 0; i < this.arrProfessionalRoleIds.length ; i ++) {
			arr += i +" : " + this.arrProfessionalRoleIds[i] ;
			if( i != this.arrProfessionalRoleIds.length -1  )
				arr +=  " ;  ";
		}
		arr += "]";
		
		System.out.println( arr );
	
	}
	
	//  CONTROLLO  e gestione ERRORE nessun professional role nel db 
	private void checkProfessionalRoleEnoughtQuantityInDb() {
		
		if( this.maxNumProfessionalRole <= 0 ) {
			// Messaggio di Errore
			JOptionPane.showConfirmDialog( this, "Per inserire un dipendente, ci deve essere almeno un ruolo professionale nel database",
					"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
		
			this.dispose();
		
			new MainMenuFrame();
		}
	}
	
	
	// GETTERS
	
	public int[] getArrProfessionalRoleIds() {
		return arrProfessionalRoleIds;
	}


	public JTextField getTextFieldName() {
		return textFieldName;
	}


	public JTextField getTextFieldSurname() {
		return textFieldSurname;
	}


	public JTextField getTextFieldTitle() {
		return textFieldTitle;
	}


	public JTextField getTextFieldBirthDate() {
		return textFieldBirthDate;
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
	
	public String getIdFromSearchEmployeeFrame() {
		return idFromSearchEmployeeFrame;
	}	
}
