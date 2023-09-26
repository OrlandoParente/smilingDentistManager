package sdmc.employee;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import sdmc.utils.ButtonJsonKey;
import sdmc.utils.Utils;

public class SearchEmployeeFrame extends JFrame{

	private ActionListener listener;
	
	private JSONObject btnNames;
	
	private JPanel panelTopMenu;
	private JPanel panelShowDatas;
	
	private JButton btnMainMenu;
	private JButton btnSearchEmployee;
	private JButton btnResetSearch;
	
	private JTextField textFieldSearchEmployee;
	
	private JComboBox<String> comboBoxProfessionalRoles;
	
	
	public SearchEmployeeFrame() {
		
		// inizializzazione finestra ----
		super("SEARCH EMPLOYEE ");
		
		this.setSize(1500, 800);
		this.setLocation(100, 150);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		btnNames = Utils.fileToJSONObject( Utils.BTNS_ITALIAN_LANGUANGE );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		listener = new SearchEmployeeActionListener( this );
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
		
		String [] arr = {null, "primo", "secondo"};
		comboBoxProfessionalRoles = new JComboBox<String>( arr ); // <<<<<<<<<<<<<################# 
		
		panelTopMenu.add( btnMainMenu );
		panelTopMenu.add( comboBoxProfessionalRoles );
		panelTopMenu.add( textFieldSearchEmployee );
		panelTopMenu.add( btnSearchEmployee );
		panelTopMenu.add( btnResetSearch );
		
		c.add( panelTopMenu , BorderLayout.NORTH );
		
		// -----------------------------------------------------------
		
		// Panel Show Datas ------------------------------------------
		// -----------------------------------------------------------
				
		// Rende visibile la finestra
		this.setVisible(true);
		
	}
}
