package sdmc.customer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import sdmc.utils.ButtonJsonKey;
import sdmc.utils.Utils;

public class SearchCustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelTopMenu;
	private JPanel panelShowDatas;
	
	private JButton btnMainMenu;
	private JButton btnSearch;
	
	private JTextField textFieldSearch;
	
	private JSONObject btnNames;
	private SearchCustomerActionListener listener;
	
	
	public SearchCustomerFrame() {
		
		// --------
		super("SEARCH CUSTOMER ");
		
		this.setSize(1500, 800);
		this.setLocation(100, 150);
		
		btnNames = Utils.fileToJSONObject( Utils.BTNS_ITALIAN_LANGUANGE );
		
		listener = new SearchCustomerActionListener( this );
		
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		// ----------- PANEL TOP MENU
		panelTopMenu = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		
		btnMainMenu = new JButton( btnNames.getString( ButtonJsonKey.BTN_MAIN_MENU ) );
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( SearchCustomerActionListener.MAIN_MENU );
		
		btnSearch = new JButton( btnNames.getString( ButtonJsonKey.BTN_SEARCH ) );
		btnSearch.addActionListener( listener );
		btnSearch.setActionCommand( SearchCustomerActionListener.SEARCH );
		
		textFieldSearch = new JTextField(10);
		textFieldSearch.addActionListener( listener );								// <<< ####################### SERVE ????
		textFieldSearch.setActionCommand( SearchCustomerActionListener.SEARCH );	// <<< ####################### SERVE ????
		
		panelTopMenu.add( btnMainMenu );
		panelTopMenu.add( textFieldSearch );
		panelTopMenu.add( btnSearch );
		
		
		// ------------
		
		// ----------- PANEL SHOW DATAS
		
		panelShowDatas = new JPanel( );
		panelShowDatas.setLayout( new BoxLayout(panelShowDatas, BoxLayout.Y_AXIS ));
		
		
		JButton btn1 = new JButton("PROVA 1");
		JButton btn2 = new JButton("PROVA 2");
		
		panelShowDatas.add(btn1);
		panelShowDatas.add(btn2);
		
		// -----------
		
		
		
		// aggiungo i pannel al container
		c.add( panelTopMenu , BorderLayout.NORTH );
		c.add( panelShowDatas, BorderLayout.CENTER );
		
		this.setVisible( true );
		
	}
	
	private void showCustomer() {
		
	}
	
}
