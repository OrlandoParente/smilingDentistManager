package sdmc.professional_role;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONObject;

import sdmc.professional_role_management.ComboBoxProfessionalRoleListener;
import sdmc.professional_role_management.ComboBoxProfessionaleRoleRenderer;
import sdmc.professional_role_management.ProfessionalRole;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.Utils;



public class ProfessionalRoleManagerFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JSONObject btnNames;
	private ActionListener listener;
	private ItemListener comboBoxProfessionalRoleListener;

	
	private JComboBox<ProfessionalRole> comboBoxSelectProfessionalRole;
	
	private JButton btnMainMenu;
	private JButton btnAddProfessionalRole;
	private JButton btnDeleteProfessionalRole;
	private JButton btnEditProfessionalRole;
	
	private JTextField textFieldProfessionalRoleName;
	private JTextArea textAreaProfessionalDescription;
	
	private JLabel labelSelectProfessionalRole;
	private JLabel labelProfessionalRoleName;
	private JLabel labelProfessionalRoleDescrioption;
	
	private JPanel panelSelectProfessionalRole;
	private JPanel panelProfessionalRoleName;
	private JPanel panelProfessionalRoleDescription;
	private JPanel panelBottomMenu;
	
	public ProfessionalRoleManagerFrame() {
		
		// inizializzazione del frame -----------------------------------------------
		super("PROFESSIONAL ROLE MANAGER");	
		this.setSize( 500, 300 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
					
		this.setLocation( 700, 300 );
					
		//
		Container c = this.getContentPane();
					
		c.setLayout( new BoxLayout( c, BoxLayout.Y_AXIS ) );
		
		listener = new ProfessionalRoleManagerActionListener( this );
		// Listener combo box 
		comboBoxProfessionalRoleListener = new ComboBoxProfessionalRoleListener( this );
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile()  );
		
		// ---------------------------------------------------------------------------

		
		// Panel Name of Professional Role -------------------------------------------
		panelProfessionalRoleName = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		labelProfessionalRoleName = new JLabel("NOME : ");
		textFieldProfessionalRoleName = new JTextField(20);
		
		panelProfessionalRoleName.add( labelProfessionalRoleName );
		panelProfessionalRoleName.add( textFieldProfessionalRoleName );
		
		
		
		// ---------------------------------------------------------------------------
		
				
		// Panel Description of Professional Role ------------------------------------

		panelProfessionalRoleDescription = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		labelProfessionalRoleDescrioption = new JLabel("DESCRIPTION : ");
		textAreaProfessionalDescription = new JTextArea( 7, 30 );
		
		panelProfessionalRoleDescription.add( labelProfessionalRoleDescrioption );
		panelProfessionalRoleDescription.add(  new JScrollPane( textAreaProfessionalDescription,
												JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
												JScrollPane.HORIZONTAL_SCROLLBAR_NEVER ) );
		
		
		
		// ---------------------------------------------------------------------------	
		
		// Panel Select Professional Role --------------------------------------------
		panelSelectProfessionalRole = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		labelSelectProfessionalRole = new JLabel("RUOLO PROFESSIONALE : ");
		
		comboBoxSelectProfessionalRole = new JComboBox<ProfessionalRole>(  ProfessionalRole.getProfessionalRoleArrayWithFirstNull() );
		// Serve a inserire nel combo Box solo il nome del Professional Role
		comboBoxSelectProfessionalRole.setRenderer( new ComboBoxProfessionaleRoleRenderer() );
		comboBoxSelectProfessionalRole.addItemListener( comboBoxProfessionalRoleListener );
		
		panelSelectProfessionalRole.add( labelSelectProfessionalRole );
		panelSelectProfessionalRole.add( comboBoxSelectProfessionalRole );
		
		
		// ---------------------------------------------------------------------------
		
		
		// Panel Bottom Menu ---------------------------------------------------------
		
		panelBottomMenu = new JPanel( new FlowLayout( FlowLayout.LEADING ) );
		
		btnMainMenu = new JButton( btnNames.getString( ButtonJsonKey.BTN_MAIN_MENU ) );
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( ProfessionalRoleManagerActionListener.MAIN_MENU );
		
		btnDeleteProfessionalRole = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
		btnDeleteProfessionalRole.addActionListener( listener );
		btnDeleteProfessionalRole.setActionCommand( ProfessionalRoleManagerActionListener.DELETE_PROFESSIONAL_ROLE );
		btnDeleteProfessionalRole.setBackground( Color.RED );
		btnDeleteProfessionalRole.setEnabled( false );
		
		btnEditProfessionalRole = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT ) );
		btnEditProfessionalRole.addActionListener( listener );
		btnEditProfessionalRole.setActionCommand( ProfessionalRoleManagerActionListener.EDIT_PROFESSIONAL_ROLE );
		btnEditProfessionalRole.setEnabled( false );
		
		btnAddProfessionalRole = new JButton( btnNames.getString( ButtonJsonKey.BTN_ADD ) );
		btnAddProfessionalRole.addActionListener( listener );
		btnAddProfessionalRole.setActionCommand( ProfessionalRoleManagerActionListener.ADD_PROFESSIONAL_ROLE );
		
		panelBottomMenu.add( btnMainMenu );
		panelBottomMenu.add( btnAddProfessionalRole );
		panelBottomMenu.add( btnEditProfessionalRole );
		panelBottomMenu.add( btnDeleteProfessionalRole );
		
		
		// ---------------------------------------------------------------------------
		
		// Aggiungo tutti i panel al container c ----
		c.add( panelSelectProfessionalRole );
		c.add( panelProfessionalRoleName );
		c.add( panelProfessionalRoleDescription );
		
		c.add( panelBottomMenu );
		// -------------------------------------------
					
	
		// Rende visibile la pagina 
		this.setVisible( true );
		
	}

	// Aggiorna la lista dei professional roles selezionabili dal combo box
	public void reloadComboBoxProfessionalRoles() {
		
		this.comboBoxSelectProfessionalRole.setModel( 
				new DefaultComboBoxModel<ProfessionalRole>( ProfessionalRole.getProfessionalRoleArrayWithFirstNull() ));
	
	}
	
	// GETTERS
	public JComboBox<ProfessionalRole> getComboBoxSelectProfessionalRole(){
		return this.comboBoxSelectProfessionalRole;
	}
	
	public JButton getBtnAddProfessionalRole() {
		return btnAddProfessionalRole;
	}

	public JButton getBtnDeleteProfessionalRole() {
		return btnDeleteProfessionalRole;
	}

	public JButton getBtnEditProfessionalRole() {
		return btnEditProfessionalRole;
	}

	public JTextArea getTextAreaProfessionalDescription() {
		return textAreaProfessionalDescription;
	}

	public JTextField getTextFieldProfessionalRoleName() {
		return textFieldProfessionalRoleName;
	}
	
	
}
