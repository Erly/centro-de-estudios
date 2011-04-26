package accionesMenu;

import interfaz.VLogin;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CerrarSesion extends AbstractAction {

	VLogin vlogin = new VLogin();
	JFrame principal;
	
	public CerrarSesion(JFrame principal) {
		 this.principal = principal; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcion = JOptionPane.showOptionDialog(principal, "Estas seguro de querer cerrar sesion?", "Salir", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);  
        if( opcion == JOptionPane.YES_OPTION ) {
        	principal.dispose();
        	vlogin.setVisible(true);
        }
	}
}
