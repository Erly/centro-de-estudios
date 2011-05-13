package interfaz;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

import datos.*;

import modelo.Conexion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main {

	public static final int OFF = 0;
	public static final int ON = 1;
	
	private JFrame frame;
	JFormattedTextField txtPuerto;
	JButton btnIniciarServidor;
	
	ServerSocket servidor = null;
	Socket cliente = null;
	int encendido = OFF;
	
	public static CentroDB db = new CentroDB();
	private JButton btnAcercaDe;
	private JLabel lblNombreDeUsuario;
	private JLabel lblContrasea;
	private JPasswordField txtPass;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 235);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		btnAcercaDe = new JButton("Acerca de...");
		btnAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AcercaDe acercade = new AcercaDe();
				acercade.setVisible(true);
			}
		});
		
		btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Thread hilo = new Thread(){
					private boolean arrancado=true;
					public void run(){
						while(arrancado){
							try {
								Socket cliente = servidor.accept();
								Conexion conexion = new Conexion(cliente);
								conexion.start();
							} catch (SocketException se){
								System.out.println("Socket cerrado");
								break;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				};
				if(encendido==OFF){
					try {
						String nombre = txtNombre.getText();
						String pass = String.valueOf(txtPass.getPassword());
						db.abrirConexionSinODBC("localhost/centrodb", AccesoBD.MYSQL, nombre, pass);
						
						servidor = new ServerSocket(Integer.parseInt(txtPuerto.getText()));
						
						hilo.start();
						btnIniciarServidor.setText("Detener Servidor");
						encendido=ON;
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {						
						servidor.close();
						db.cerrarConexion();
						btnIniciarServidor.setText("Iniciar Servidor");
						encendido=OFF;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		GridBagConstraints gbc_lblNombreDeUsuario = new GridBagConstraints();
		gbc_lblNombreDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeUsuario.gridx = 3;
		gbc_lblNombreDeUsuario.gridy = 1;
		frame.getContentPane().add(lblNombreDeUsuario, gbc_lblNombreDeUsuario);
		
		txtNombre = new JTextField();
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 4;
		gbc_txtNombre.gridy = 1;
		frame.getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);
		
		lblContrasea = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 3;
		gbc_lblContrasea.gridy = 2;
		frame.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		txtPass = new JPasswordField();
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.insets = new Insets(0, 0, 5, 5);
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.gridx = 4;
		gbc_txtPass.gridy = 2;
		frame.getContentPane().add(txtPass, gbc_txtPass);
		
		JLabel lblPuerto = new JLabel("Puerto <1-65535>");
		GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
		gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuerto.gridx = 3;
		gbc_lblPuerto.gridy = 3;
		frame.getContentPane().add(lblPuerto, gbc_lblPuerto);
		
		txtPuerto = new JFormattedTextField();
		txtPuerto.setText("4444");
		GridBagConstraints gbc_txtPuerto = new GridBagConstraints();
		gbc_txtPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_txtPuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPuerto.gridx = 4;
		gbc_txtPuerto.gridy = 3;
		frame.getContentPane().add(txtPuerto, gbc_txtPuerto);
		GridBagConstraints gbc_btnIniciarServidor = new GridBagConstraints();
		gbc_btnIniciarServidor.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciarServidor.gridx = 3;
		gbc_btnIniciarServidor.gridy = 5;
		frame.getContentPane().add(btnIniciarServidor, gbc_btnIniciarServidor);
		GridBagConstraints gbc_btnAcercaDe = new GridBagConstraints();
		gbc_btnAcercaDe.insets = new Insets(0, 0, 5, 5);
		gbc_btnAcercaDe.gridx = 4;
		gbc_btnAcercaDe.gridy = 5;
		frame.getContentPane().add(btnAcercaDe, gbc_btnAcercaDe);
	}

}
