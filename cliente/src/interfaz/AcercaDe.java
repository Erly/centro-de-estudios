package interfaz;

import java.awt.EventQueue;

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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AcercaDe.class.getResource("/imagenes/Logo.png")));
		label.setBounds(10, 6, 128, 126);
		getContentPane().add(label);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AcercaDe.this.dispose();
			}
		});
		btnAceptar.setBounds(487, 140, 89, 23);
		getContentPane().add(btnAceptar);
		
		JLabel lblLiberadoBajoLicencia = new JLabel("<html>Liberado bajo licencia GPLv3 <br> Codigo fuente disponible en <a href='http://centro-de-estudios.googlecode.com'>centro-de-estudios.googlecode.com</a></html>");
		lblLiberadoBajoLicencia.setAutoscrolls(true);
		lblLiberadoBajoLicencia.setBounds(160, 87, 416, 45);
		getContentPane().add(lblLiberadoBajoLicencia);

	}
}
