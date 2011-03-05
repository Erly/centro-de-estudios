package accionesMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Main;

@SuppressWarnings("serial")
public class Cerrar extends AbstractAction {

	JFrame jframe;
	
	public Cerrar(JFrame jframe) {
		this.jframe = jframe;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int opcion = JOptionPane.showOptionDialog(  
                jframe,  
                "Estas seguro de que quieres salir?",  
                "Salir", JOptionPane.YES_NO_OPTION,  
                JOptionPane.WARNING_MESSAGE, null, null,  
                null );  
        if( opcion == JOptionPane.YES_OPTION ) {
        	Main.db.cerrarConexion();
            System.exit(0);  
        } 
	}

}
