package sdmc.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.combo_box_management.ComboBoxRenderer;
import sdmc.combo_box_management.professional_role.ComboBoxProfessionalRoleListener;
import sdmc.combo_box_management.professional_role.ProfessionalRole;
import sdmc.server_connection.HttpConnectionManager;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.FrameTitleJsonKey;
import sdmc.utils.Utils;

public class SearchEmployeeFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private ActionListener listener;
	private ComboBoxProfessionalRoleListener comboBoxProfessionalRoleListener;
	private DeleteEmployeeActionListener deleteEmployeeActionListener;
	private EditEmployeeActionListener editEmployeeActionListener;
	
	private JSONObject btnNames;
	
	private JPanel panelTopMenu;
	private JPanel panelShowDatas;
	
	private JButton btnMainMenu;
	private JButton btnSearchEmployee;
	private JButton btnResetSearch;
	
	private JTextField textFieldSearchEmployee;
	
	private JComboBox<ProfessionalRole> comboBoxProfessionalRoles;
	
	
	
	
	public SearchEmployeeFrame() {
		
		// inizializzazione finestra ----
		super( Utils.fileToJSONObject( Setting.getSettings().getFrameTitlesLanguageFile() ).getString( FrameTitleJsonKey.SEARCH_EMPLOYEE ) );
		
		this.setSize(1500, 800);
		this.setLocation(100, 150);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile()  );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		listener = new SearchEmployeeActionListener( this );
		deleteEmployeeActionListener = new DeleteEmployeeActionListener( this );
		editEmployeeActionListener = new EditEmployeeActionListener( this );
		comboBoxProfessionalRoleListener = new ComboBoxProfessionalRoleListener( this );
		// ---------
		
		// Panel Top Menu --------------------------------------------
		panelTopMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		btnMainMenu = new JButton( btnNames.getString( ButtonJsonKey.BTN_MAIN_MENU ) );
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( SearchEmployeeActionListener.MAIN_MENU );
		
		btnSearchEmployee = new JButton( btnNames.getString( ButtonJsonKey.BTN_SEARCH ) );
		btnSearchEmployee.addActionListener( listener );
		btnSearchEmployee.setActionCommand( SearchEmployeeActionListener.SEARCH_EMPLOYEE );
		
		btnResetSearch= new JButton( btnNames.getString( ButtonJsonKey.BTN_RESET_SEARCH ) );
		btnResetSearch.addActionListener( listener );
		btnResetSearch.setActionCommand( SearchEmployeeActionListener.RESET_SEARCH );
		
		textFieldSearchEmployee = new JTextField(15);
		textFieldSearchEmployee.setToolTipText(" Doesn't work for professional roles, use the combo box ");
		
		comboBoxProfessionalRoles = new JComboBox<ProfessionalRole>( ProfessionalRole.getProfessionalRoleArrayWithFirstNull() ); 
		// Per mostrare solo il nome del Professional Role
		comboBoxProfessionalRoles.setRenderer( new ComboBoxRenderer() );
		comboBoxProfessionalRoles.addItemListener( comboBoxProfessionalRoleListener );
		
		panelTopMenu.add( btnMainMenu );
		panelTopMenu.add( comboBoxProfessionalRoles );
		panelTopMenu.add( textFieldSearchEmployee );
		panelTopMenu.add( btnSearchEmployee );
		panelTopMenu.add( btnResetSearch );
		
		c.add( panelTopMenu , BorderLayout.NORTH );
		
		// -----------------------------------------------------------
		
		// Panel Show Datas ------------------------------------------
		panelShowDatas = new JPanel();
		panelShowDatas.setLayout( new BoxLayout( panelShowDatas , BoxLayout.Y_AXIS ) );
		
		this.insertEmployeePanelsInShowDatasPanel( this.getEmployees() );
		
		c.add( new JScrollPane( panelShowDatas ) );
		// -----------------------------------------------------------
				
		// Rende visibile la finestra
		this.setVisible(true);
		
	}
	
	//
	public void reloadPanelEmployeeShowDatas() {
		
		panelShowDatas.removeAll();
		
		insertEmployeePanelsInShowDatasPanel( this.getEmployees() );
		
		panelShowDatas.revalidate();
		panelShowDatas.repaint();
		
	}
	
	//
	public void reloadPanelEmployeeShowDatas( JSONArray jsonArrEmployees ) {
		
		panelShowDatas.removeAll();
		
		insertEmployeePanelsInShowDatasPanel( jsonArrEmployees );
		
		panelShowDatas.revalidate();
		panelShowDatas.repaint();
		
	}
	
	
	// Inserisce employee in showDatas panel
	private void insertEmployeePanelsInShowDatasPanel ( JSONArray jsonArrEmployees ) {
		
		// Se l'arrey è vuoro esci ( non ci sono employee da inserire nel panelShowDatas )
		if ( jsonArrEmployees == null ) return;
		
		
		jsonArrEmployees.forEach( item -> {
			
			// Recupero dati necessari --------------------
			
			JSONObject joEmployee = ( JSONObject ) item;
			
			JSONArray jsonArrProfessionalRoles = this.getProfessionalRoleByIdEmployee( joEmployee.getInt("id") + "" );

			// --------------------------------------------
			
			
			// Creazione panelEmployee  -------------------
			
			JPanel panelEmployee = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
			
			// evita che box layout lo sparpagli per tutto lo spazio disponibile
			panelEmployee.setMaximumSize( 
					new Dimension( (int) panelEmployee.getMaximumSize().getWidth(),  40 )  );
			panelEmployee.setBackground( Color.CYAN );
			
			
			JButton btnEdit = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT ) );
			btnEdit.addActionListener( editEmployeeActionListener );
			btnEdit.setActionCommand( joEmployee.getInt("id") + "" );
			
			JButton btnDelete = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
			btnDelete.addActionListener( deleteEmployeeActionListener );
			btnDelete.setActionCommand( joEmployee.getInt("id") + "" );
			btnDelete.setBackground( Color.RED );
			
			// Inserisco JTextField Name e Surname all'inizio -------------
			
			JTextField textFieldEmployeeName = new JTextField(20);
			textFieldEmployeeName.setText( joEmployee.getString("name") );
			textFieldEmployeeName.setToolTipText("name");
			textFieldEmployeeName.setEditable( false );
			
			JTextField textFieldEmployeeSurname = new JTextField(20);
			textFieldEmployeeSurname.setText( joEmployee.getString("surname") );
			textFieldEmployeeSurname.setToolTipText("surname");
			textFieldEmployeeSurname.setEditable( false );
			
			// ------------------------------------------------------------
			// ---
			panelEmployee.add( btnEdit );
			panelEmployee.add( btnDelete );
			panelEmployee.add( textFieldEmployeeName );
			panelEmployee.add( textFieldEmployeeSurname );
			
			// Stampa dati Employee
			for( String key : joEmployee.keySet() ) {
				
				// Salto id perché è un'informazione tecnica non utile all'utente finale
				// Salto name e surname perché già inseriti prima per assicurarsi di inserirli in prima e seconda posizione
				if( key.equals("id") || key.equals("name") || key.equals("surname") )
					continue;
				
				JTextField textField = new JTextField(20);
				textField.setText( ( String ) joEmployee.getString( key ) );
				textField.setToolTipText( key );
				textField.setEditable( false );
				
				panelEmployee.add( textField );
				
			}
			
			// Stampa professional Role associati all'employee
			jsonArrProfessionalRoles.forEach( professionalRole -> {
				
				JSONObject joProfessionalRole = ( JSONObject ) professionalRole;
				
				JTextField textFieldNameProfessionalRole = new JTextField(20);
				textFieldNameProfessionalRole.setText( ( String ) joProfessionalRole.getString( "name" ) );
				textFieldNameProfessionalRole.setToolTipText( "professional_role" );
				textFieldNameProfessionalRole.setEditable( false );
				
				panelEmployee.add( textFieldNameProfessionalRole );
				
			} );
			
			
			
			this.panelShowDatas.add( panelEmployee );
			// --------------------------------------------
			
		});
		
	}
	
	// Recupera la lista degli employee
	public JSONArray getEmployees() {
		
		JSONArray jsonArrEmployees = null;
		
		String strEmployees = HttpConnectionManager.doGet( HttpConnectionManager.GET_EMPLOYEES).getResponseString();
		
		// Evita errore nel creare il jsonArray con stringa nulla o vuota ----
		
		if( strEmployees == null ) {
			
			strEmployees = "[]";
		
		} else if( strEmployees.equals("") ) {
			
			strEmployees = "[]";
			
		} 
		// -------------------------------------------------------------------
		
		jsonArrEmployees = new JSONArray( strEmployees );
			
		
		return jsonArrEmployees;
	}
	
	// Recupera la lista degli employee
	public JSONArray getEmployeesByPartialKeyWordOverAllFields( String keyWord ) {
		
		JSONArray jsonArrEmployees = null;
		
		String strEmployees = HttpConnectionManager
								.doGet( HttpConnectionManager.GET_EMPLOYEES_BY_PARTIAL_KEY_WORD_OVER_ALL_FIELDS + keyWord )
								.getResponseString();
		
		// Evita errore nel creare il jsonArray con stringa nulla o vuota ----
		
		if( strEmployees == null ) {
			
			strEmployees = "[]";
		
		} else if( strEmployees.equals("") ) {
			
			strEmployees = "[]";
			
		} 
		// -------------------------------------------------------------------
		
		jsonArrEmployees = new JSONArray( strEmployees );
			
		
		return jsonArrEmployees;
	}
	
	
	// Ritorna i gli employee associati ad un professional role name
	public JSONArray getEmployeesByProfessionalRoleName( String professionalRoleName ) {
		
		JSONArray jsonArrEmployee = null;
		
		String strEmployee = HttpConnectionManager.doGet( HttpConnectionManager.GET_EMPLOYEES_BY_PROFESSIONAL_ROLE_NAME  + professionalRoleName ).getResponseString();
		
		System.out.println( " -----> " + HttpConnectionManager.GET_EMPLOYEES_BY_PROFESSIONAL_ROLE_NAME  + professionalRoleName );
		
		// Evita errore nel creare il jsonArray con stringa nulla o vuota ----
		// Oss.: In realtà per come è costruito il sw non è possibile che entri in questa situazione 
		if( strEmployee == null ) {
			
			strEmployee = "[]";
		
		} else if( strEmployee.equals("") ) {
			
			strEmployee = "[]";
			
		} 
		// -------------------------------------------------------------------
		
		jsonArrEmployee = new JSONArray( strEmployee );
			
		
		return jsonArrEmployee;
		
	}
	
	
	// Ritorna i ruoli professionali associati ad un employee dal suo Id
	public JSONArray getProfessionalRoleByIdEmployee( String idEmployee ) {
		
		JSONArray jsonArrProfessionalRoles = null;
		
		String strProfessionalRoles = HttpConnectionManager.doGet( 
				HttpConnectionManager.GET_PROFESSIONAL_ROLES_ASSOCIATED_TO_ID_EMPLOYEE + idEmployee ).getResponseString();
		
		
		// Evita errore nel creare il jsonArray con stringa nulla o vuota ----
		// Oss.: In realtà per come è costruito il sw non è possibile che entri in questa situazione 
		if( strProfessionalRoles == null ) {
			
			strProfessionalRoles = "[]";
		
		} else if( strProfessionalRoles.equals("") ) {
			
			strProfessionalRoles = "[]";
			
		} 
		// -------------------------------------------------------------------
		
		jsonArrProfessionalRoles = new JSONArray( strProfessionalRoles );
			
		
		return jsonArrProfessionalRoles;
		
	}
	
	// GETTERS
	
	public JTextField getTextFieldSearchEmployee() {
		
		return this.textFieldSearchEmployee;
		
	}
	
	public JComboBox<ProfessionalRole> getComboBoxProfessionalRoles() {
		
		return this.comboBoxProfessionalRoles;
		
	}


}






