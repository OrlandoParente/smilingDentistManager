package sdmc.customer;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class AddCustomerFrame extends JFrame{

	
	private static final long serialVersionUID = 1L;

	public AddCustomerFrame() {
		// inizializzazione del frame -----------------------------------------------
		super("ADD NEW CUSTOMER");	
		this.setSize( 400, 600 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setLocation( 700, 200 );
		
		//
		Container c = this.getContentPane();
		
		c.setLayout( new GridLayout( 8, 1) );
		// ---------------------------------------------------------------------------
		
		// imposta finestra visibile
		this.setVisible( true );
	}
}
