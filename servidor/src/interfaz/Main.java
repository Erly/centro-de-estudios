package interfaz;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

public class Main {

	public static final int OFF = 0;
	public static final int ON = 1;
	
	private JFrame frame;
	JFormattedTextField txtPuerto;
	JButton btnIniciarServidor;
	
	ServerSocket servidor = null;
	Socket cliente = null;
	int encendido = OFF;
	
	public static CentroDB db;

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
			db.abrirConexionSinODBC("//localhost/centrodb", AccesoBD.MYSQL, "root", "administrador");
		}catch(SQLException e) {
			System.out.println("No se ha podido establecer la conexión.");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPuerto = new JLabel("Puerto <1-65535>");
		GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
		gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuerto.gridx = 3;
		gbc_lblPuerto.gridy = 2;
		frame.getContentPane().add(lblPuerto, gbc_lblPuerto);
		
		txtPuerto = new JFormattedTextField();
		txtPuerto.setText("4444");
		GridBagConstraints gbc_txtPuerto = new GridBagConstraints();
		gbc_txtPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_txtPuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPuerto.gridx = 4;
		gbc_txtPuerto.gridy = 2;
		frame.getContentPane().add(txtPuerto, gbc_txtPuerto);
		
		btnIniciarServidor = new JButton("Iniciar Servidor");
		btnIniciarServidor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(encendido==OFF){
					try {
						servidor = new ServerSocket(Integer.parseInt(txtPuerto.getText()));
						Thread hilo = new Thread(){
							public void run(){
								while(true){
									try {
										Socket cliente = servidor.accept();
										Conexion conexion = new Conexion(cliente);
										conexion.run();
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						};
						hilo.run();
						btnIniciarServidor.setText("Detener Servidor");
						encendido=ON;
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						servidor.close();
						btnIniciarServidor.setText("Iniciar Servidor");
						encendido=OFF;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnIniciarServidor = new GridBagConstraints();
		gbc_btnIniciarServidor.insets = new Insets(0, 0, 0, 5);
		gbc_btnIniciarServidor.gridx = 3;
		gbc_btnIniciarServidor.gridy = 7;
		frame.getContentPane().add(btnIniciarServidor, gbc_btnIniciarServidor);
		
		JButton btnNewButton = new JButton("Finalizar Servidor");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 7;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

}
