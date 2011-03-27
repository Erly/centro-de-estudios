package interfaz;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import datos.VERSION;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

@SuppressWarnings("serial")
public class AcercaDe extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public AcercaDe() {
		setFrameIcon(new ImageIcon(AcercaDe.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		setTitle("Acerca de...");
		setClosable(true);
		setBounds(100, 100, 600, 200);
		getContentPane().setLayout(null);
		
		JLabel lblCentroDeEstudios = new JLabel("Centro de Estudios");
		lblCentroDeEstudios.setBounds(150, 6, 439, 29);
		lblCentroDeEstudios.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblCentroDeEstudios);
		
		JLabel lblVersionBeta = new JLabel("Versión: " + VERSION.MAJOR + "." + VERSION.MINOR + "." + VERSION.BUILD);
		lblVersionBeta.setBounds(160, 47, 416, 15);
		getContentPane().add(lblVersionBeta);
		
		JLabel lblcCopyright = new JLabel("(c) Copyright 2011, Erlantz Oniga Ouro, todos los derechos reservados");
		lblcCopyright.setBounds(18, 144, 457, 14);
		getContentPane().add(lblcCopyright);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(10, 6, 128, 126);
		ImageIcon icono = new ImageIcon(AcercaDe.class.getResource("/imagenes/Logo.png"));
		Image img = icono.getImage();
		img = img.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imagen = new ImageIcon(img);
		lblLogo.setIcon(imagen);
		getContentPane().add(lblLogo);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AcercaDe.this.dispose();
			}
		});
		btnAceptar.setBounds(487, 140, 89, 23);
		getContentPane().add(btnAceptar);
		
		JEditorPane editorPane = new JEditorPane("text/html","<center>Liberado bajo licencia GPLv3 </center><br> " +
				"Codigo fuente disponible en <a href='http://centro-de-estudios.googlecode.com/'>centro-de-estudios.googlecode.com</a>");
		editorPane.setAutoscrolls(true);
		editorPane.setOpaque(false);
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())){
					try {
						Desktop.getDesktop().browse(new URI(e.getURL().toString()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		editorPane.setBounds(150, 74, 432, 58);
		getContentPane().add(editorPane);

	}
}
