package sdmc.customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import sdmc.server_connection.HttpConnectionManager;
import sdmc.server_connection.RequestResponse;
import sdmc.settings.Setting;
import sdmc.utils.ButtonJsonKey;
import sdmc.utils.FrameTitleJsonKey;
import sdmc.utils.Utils;

public class SearchCustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelTopMenu;
	private JPanel panelShowDatas;
	
	private JButton btnMainMenu;
	private JButton btnSearch;
	private JButton btnResetSearch;
	
	private JTextField textFieldSearch;
	
	private JSONObject btnNames;
	private SearchCustomerActionListener listener;
	private DeleteCustomerActionListener deleteCustomerListener;
	private EditCustomerActionListener editCustomerListener;
	
	
	public SearchCustomerFrame() {
		
		// --------
		super( Utils.fileToJSONObject( Setting.getSettings().getFrameTitlesLanguageFile() ).getString( FrameTitleJsonKey.SEARCH_CUSTOMER ));
		
		this.setSize(1500, 800);
		this.setLocation(100, 150);
		
		
		
		btnNames = Utils.fileToJSONObject( Setting.getSettings().getBtnsLanguageFile()  );
		
		// Inizializazione listeners
		listener = new SearchCustomerActionListener( this );
		deleteCustomerListener = new DeleteCustomerActionListener( this );
		editCustomerListener = new EditCustomerActionListener( this );
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		// ----------- PANEL TOP MENU
		panelTopMenu = new JPanel( new FlowLayout( FlowLayout.LEFT ) );
		
		btnMainMenu = new JButton( btnNames.getString( ButtonJsonKey.BTN_MAIN_MENU ) );
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( SearchCustomerActionListener.MAIN_MENU );
		
		btnResetSearch = new JButton( btnNames.getString( ButtonJsonKey.BTN_RESET_SEARCH ) );
		btnResetSearch.addActionListener( listener );
		btnResetSearch.setActionCommand( SearchCustomerActionListener.RESET_SEARCH );
		
		
		btnSearch = new JButton( btnNames.getString( ButtonJsonKey.BTN_SEARCH ) );
		btnSearch.addActionListener( listener );
		btnSearch.setActionCommand( SearchCustomerActionListener.SEARCH );
		
		textFieldSearch = new JTextField(10);
		textFieldSearch.addActionListener( listener );								// <<< ####################### SERVE ????
		textFieldSearch.setActionCommand( SearchCustomerActionListener.SEARCH );	// <<< ####################### SERVE ????
		
		panelTopMenu.add( btnMainMenu );
		panelTopMenu.add( textFieldSearch );
		panelTopMenu.add( btnSearch );
		panelTopMenu.add( btnResetSearch );
		
		// ------------
		
		// ----------- PANEL SHOW DATAS
		
		panelShowDatas = new JPanel( );
		panelShowDatas.setLayout( new BoxLayout(panelShowDatas, BoxLayout.Y_AXIS ));
		
		showCustomers( getCustomers() );
		
		
		// -----------
		
		
		
		// aggiungo i pannel al container
		c.add( panelTopMenu , BorderLayout.NORTH );
		c.add( new JScrollPane( panelShowDatas ), BorderLayout.CENTER );
		
		this.setVisible( true );
		
	}
	
	public void showCustomers( JSONArray customers ) {
		
	
		panelShowDatas.removeAll();
		panelShowDatas.revalidate();
		panelShowDatas.repaint();
		
		customers.forEach( item -> { 
				System.out.println( item );
				JSONObject jo = ( JSONObject ) item;

				JPanel panelCustomer = new JPanel( new  FlowLayout( FlowLayout.LEFT ) );
				// evita che box layout lo sparpagli per tutto lo spazio disponibile
				panelCustomer.setMaximumSize( 
						new Dimension( (int) panelCustomer.getMaximumSize().getWidth(),  40 )  );
				panelCustomer.setBackground( Color.CYAN );
				
				// Bottoni Modifica ed Elimina --------------
				JButton btnEdit = new JButton( btnNames.getString( ButtonJsonKey.BTN_EDIT ) );
				btnEdit.addActionListener( editCustomerListener  ); //<<<< ##################
				btnEdit.setActionCommand( jo.getInt("id")+"" );
				
				JButton btnDelete = new JButton( btnNames.getString( ButtonJsonKey.BTN_DELETE ) );
				btnDelete.setBackground( Color.RED );
				btnDelete.addActionListener( deleteCustomerListener ); // <<<< #########################
				btnDelete.setActionCommand( jo.getInt("id")+"" );
				
				panelCustomer.add( btnEdit );
				panelCustomer.add( btnDelete );
				
				// --------------------------------------------
				
				// Inserisco in prima e seconda posizione il nome e il cognome del customer -----------
	
				JTextField textFieldName = new JTextField(20);
				textFieldName.setText( jo.getString( "name" ) );
				textFieldName.setToolTipText( "name"  );
				textFieldName.setEditable( false );
				
				panelCustomer.add( textFieldName );
				
				JTextField textFieldSurname = new JTextField(20);
				textFieldSurname.setText( jo.getString( "surname" ) );
				textFieldSurname.setToolTipText( "surname"  );
				textFieldSurname.setEditable( false );
				
				panelCustomer.add( textFieldSurname );
				
				// ------------------------------------------------------------------------------------
				
				
				for( String key:  jo.keySet() ) {
					// Salto l'id perchè si tratta di dettagli tecnici di implementazione che non interessano all'utente finale
					// Salto name e surname perchè già inseriti rispettivamente in prima e seconda posizione
					if( key.equals("id") || key.equals("name") || key.equals("surname") ) continue; 
					
					JTextField textField = new JTextField(20);
					
					// Restituisce una stringa e in caso il valore è null inserisce ""
					String value = jo.optString(key, "");
					textField.setText(value);
					
//					if( jo.get( key ) != JSONObject.NULL )
//						textField.setText( jo.getString( key ) );
//					else 
//						textField.setText( "" );
					
					textField.setToolTipText( key  );
					textField.setEditable( false );
					
					panelCustomer.add( textField );
					
					 //JLabel label = new JLabel( jo.getString(key) );
					 //panelCustomer.add( label );
					
				}
				
				
				panelShowDatas.add( panelCustomer );
		});
		
		
		// System.out.println( customers );
	}
	
	public JSONArray getCustomers() {
		JSONArray customers = new JSONArray();
		RequestResponse response = HttpConnectionManager.doGet("getCustomers");
		
		if( response.getResponseCode() ==  RequestResponse.CONNECTION_REFUSED ) {

			// Messaggio di Errore
			JOptionPane.showConfirmDialog( this , "Connessione con il server non riuscita", 
					"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
			

		} else {
		
			customers = new JSONArray( response.getResponseString() );
		}
		
		return customers;
		
	}
	
	public JSONObject getCustomer( String id ) {
		
		JSONObject customer = new JSONObject();
		RequestResponse response = HttpConnectionManager.doGet( HttpConnectionManager.GET_CUSTOMER_BY_ID + id );
		
		if( response.getResponseCode() ==  RequestResponse.CONNECTION_REFUSED ) {

			// Messaggio di Errore
			JOptionPane.showConfirmDialog( this , "Connessione con il server non riuscita", 
					"Message",JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE );
			

		} else {
		
			customer = new JSONObject( response.getResponseString() );
		}
		
		return customer;
		
	}
	
	public JSONArray getCustomers( String id ) {
		
		return new JSONArray().put( getCustomer(id) );
	}
	
	// GETTERS
	
	 public JTextField getTextFieldSearch() {
	
		 return this.textFieldSearch;
	 
	 }
	
}
