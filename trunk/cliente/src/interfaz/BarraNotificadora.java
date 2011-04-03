package interfaz;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import excepciones.ValorIncorrectoEx;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;

public class BarraNotificadora extends JPanel implements Runnable{

	/**
	 * Create the panel.
	 */
	
	public static final int ERROR_MESSAGE = -1;
	public static final int INFORMATION_MESSAGE = 0;
	public static final int OK_MESSAGE = 1;
	
	JLabel lblTexto;
	private int millis = -1;
	private int alto = 30;
	
	public BarraNotificadora(){
		setBackground(new Color(100, 149, 237));
		setLayout(null);
		this.setBounds(0, 0, 500, alto);
		lblTexto = new JLabel("texto");
		lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblTexto.setForeground(new Color(255, 255, 255));
		lblTexto.setBounds(5, 0, this.getWidth()-10, 30);
		add(lblTexto);
	}
	
	public BarraNotificadora(JFrame padre, String texto) {
		this();
		calcAltura(padre, texto);
		this.setBounds(0, 0, padre.getWidth(), alto);
		lblTexto.setText("<html><center>" + texto + "</center></html>");
		lblTexto.setBounds(5, 0, this.getWidth()-10, alto);
		padre.getContentPane().add(this, 0);
	}

	public BarraNotificadora(JDialog padre, String texto) {
		this();
		calcAltura(padre, texto);
		this.setBounds(0, 0, padre.getWidth(), alto);
		lblTexto.setText("<html><center>" + texto + "</center></html>");
		lblTexto.setBounds(5, 0, this.getWidth()-10, alto);
		padre.getContentPane().add(this, 0);
	}
	
	public BarraNotificadora(JFrame padre, String texto, int tipoMensaje) throws ValorIncorrectoEx{
		this(padre, texto);
		this.setColor(tipoMensaje);
	}
	
	public BarraNotificadora(JDialog padre, String texto, int tipoMensaje) throws ValorIncorrectoEx{
		this(padre, texto);
		this.setColor(tipoMensaje);
	}
	
	public BarraNotificadora(JFrame padre, String texto, int tipoMensaje, int millis) throws ValorIncorrectoEx{
		this(padre, texto, tipoMensaje);
		this.setMillis(millis);
	}
	
	public BarraNotificadora(JDialog padre, String texto, int tipoMensaje, int millis) throws ValorIncorrectoEx{
		this(padre, texto, tipoMensaje);
		this.setMillis(millis);
	}
	
	public void start(){
		Thread hilo = new Thread(this);
		hilo.run();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		setVisible(true);
		for(int i = 1; i <= alto; i++){
			setSize(getWidth(), i);
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(millis != -1){
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = alto; i >= 0; i--){
				setSize(getWidth(), i);
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.setVisible(false);
		}
	}

	private void setMillis(int millis) throws ValorIncorrectoEx {
		if(millis > 0)
			this.millis = millis;
		else
			throw new ValorIncorrectoEx();
	}
	
	private void setColor(int tipo) throws ValorIncorrectoEx{
		if(tipo < -1 || tipo > 1)
			throw new ValorIncorrectoEx();
		switch(tipo){
		case -1:
			this.setBackground(Color.RED);
			break;
		case 0:
			setBackground(new Color(100, 149, 237));
			break;
		case 1:
			this.setBackground(Color.GREEN);
			break;
		}
	}
	
	private void calcAltura(JFrame padre, String texto) {
		int ancho = padre.getWidth();
		int tamTexto = texto.length();
		alto = 20 * (1 + ancho / (tamTexto * 10));
	}
	
	private void calcAltura(JDialog padre, String texto) {
		int ancho = padre.getWidth();
		int tamTexto = texto.length();
		alto = 20 * (1 + (tamTexto * 8) / ancho);
		System.out.println("" + alto);
	}
}