package sdmc.utils;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OnlyIntTextFieldListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// Check message
		System.out.println( "OnlyIntTextFielstListener --> " + e.getKeyChar() );
		
		if( ! ( e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) )	{
			
			// e.setKeyChar(' ');
			e.consume();
			
			System.out.println( "OnlyIntTextFielstListener --> event consumed "  );
			
			if(  ( e.getKeyChar() != KeyEvent.VK_BACK_SPACE ) && ( e.getKeyChar() != KeyEvent.VK_DELETE )  )
				Toolkit.getDefaultToolkit().beep();
			
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
