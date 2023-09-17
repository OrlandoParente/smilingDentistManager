package smilingDentinstManagerClient;

import javax.swing.*;
import java.awt.*;

class MainMenuJFrame extends JFrame {
	
	public MainMenuJFrame() {
		super("MAIN MENU");
		// inizializzo frame
		
		this.setSize( 400, 700 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
		this.setLocation( 700, 200 );
		
		//
		Container c = this.getContentPane();
		
		
	}

}
