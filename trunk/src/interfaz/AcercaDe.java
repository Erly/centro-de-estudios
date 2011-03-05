package interfaz;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AcercaDe extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcercaDe frame = new AcercaDe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AcercaDe() {
		setFrameIcon(new ImageIcon(AcercaDe.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		setTitle("Acerca de...");
		setClosable(true);
		setBounds(100, 100, 560, 160);
		getContentPane().setLayout(null);
		
		JLabel lblCentroDeEstudios = new JLabel("Centro de Estudios");
		lblCentroDeEstudios.setBounds(0, 0, 544, 29);
		lblCentroDeEstudios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentroDeEstudios.setFont(new Font("Tahoma", Font.BOLD, 24));
		getContentPane().add(lblCentroDeEstudios);
		
		JLabel lblVersionBeta = new JLabel("Version: 0.1 BETA");
		lblVersionBeta.setBounds(224, 40, 102, 14);
		getContentPane().add(lblVersionBeta);
		
		JLabel lblcCopyright = new JLabel("(c) Copyright 2011, Erlantz Oniga Ouro, todos los derechos reservados");
		lblcCopyright.setBounds(47, 107, 386, 14);
		getContentPane().add(lblcCopyright);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AcercaDe.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")));
		label.setBounds(257, 65, 26, 30);
		getContentPane().add(label);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AcercaDe.this.dispose();
			}
		});
		btnAceptar.setBounds(445, 102, 89, 23);
		getContentPane().add(btnAceptar);

	}
}
