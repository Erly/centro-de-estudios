package accionesMenu;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
public class MostrarVentana extends AbstractAction{
	
	JInternalFrame jframe;

	public MostrarVentana(JInternalFrame jframe) {
		this.jframe = jframe;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		jframe.setVisible(true);
	}
}
