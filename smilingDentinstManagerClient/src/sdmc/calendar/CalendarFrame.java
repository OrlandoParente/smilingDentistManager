package sdmc.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalendarFrame extends JFrame {

	private CalendarActionListener listener;
	
	private JPanel panelTopMenu;
	private JPanel panelBodyCalendar;
	
	private JButton btnMainMenu;
	private JButton btnSearch;
	
	public CalendarFrame(  ){
		
		// inizializzazione finestra
		super("CALENDAR");

		this.setLocation(200, 100);
		this.setSize( 1200, 700 );
		
		// inizializzazione listener
		listener = new CalendarActionListener( this );
		
		// Inizializzazione buttons ----------------------------------------------------------
		
		btnMainMenu = new JButton("MAIN MENU");
		btnMainMenu.addActionListener( listener );
		btnMainMenu.setActionCommand( CalendarActionListener.MAIN_MENU );
		
		btnSearch = new JButton("SEARCH");
		
		// -----------------------------------------------------------------------------------
		
		
		Container c = this.getContentPane();
		c.setLayout( new BorderLayout() );
		
		// PANEL TOP MENU ####################################################################
		
		panelTopMenu = new JPanel( new FlowLayout( FlowLayout.LEADING )  );
		
		panelTopMenu.add( btnMainMenu );
		panelTopMenu.add( btnSearch );
		
		
		// ###################################################################################
		
		
		// PANEL BODY CALENDAR ###############################################################
		panelBodyCalendar = new JPanel( new GridLayout( 9, 3 ) );
		panelBodyCalendar.setBackground( Color.BLUE );
		
		// ###################################################################################
		
		// ################################
		c.add( panelTopMenu, BorderLayout.NORTH );
		c.add( panelBodyCalendar, BorderLayout.CENTER );
		
		
		
		// rende visibile la finestra
		this.setVisible( true );
	}
	
}
