package interfaz;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;

import excepciones.ValorIncorrectoEx;

import modelo.Main;
import modelo.Usuarios.Usuario;

import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial"})
public class VLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	JLabel lblConectado;
	JButton btnSeleccionarServidor;
	JButton okButton;
	JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public VLogin() {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		setTitle("Login");
		setUndecorated(true);
		setBounds(100, 100, 400, 250);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("ComboBox.disabledForeground"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Droid Serif", Font.BOLD, 40));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(12, 12, 378, 71);
		contentPanel.add(lblLogin);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("OK")){
					//okButton.doClick();
					if(!okButton.isEnabled()){
						return;
					}
					String usuario = txtUsuario.getText();
					char[] pass = txtPassword.getPassword();
					String password = String.valueOf(pass);
					pass = new char[0];
					Usuario usu = new Usuario(usuario, password, "");
					if(Main.Login(usu)){
						VPrincipal vprincipal = new VPrincipal();
						vprincipal.setVisible(true);
						VLogin.this.dispose();
					}
				}
			}
		});
		okButton.setEnabled(false);
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!okButton.isEnabled()){
					return;
				}
				String usuario = txtUsuario.getText();
				char[] pass = txtPassword.getPassword();
				String password = String.valueOf(pass);
				pass = new char[0];
				Usuario usu = new Usuario(usuario, password, "");
				if(Main.Login(usu)){
					VPrincipal vprincipal = new VPrincipal();
					vprincipal.setVisible(true);
					VLogin.this.dispose();
				}
			}
		});
		okButton.setBounds(243, 221, 54, 24);
		contentPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Main.socket.isConnected()){
					try {
						Main.Salir();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
		cancelButton.setBounds(309, 221, 81, 24);
		contentPanel.add(cancelButton);
		cancelButton.setActionCommand("Cancel");
		
		btnSeleccionarServidor = new JButton("Seleccionar Servidor");
		btnSeleccionarServidor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				VConectar vconectar = new VConectar();
				vconectar.setVisible(true);
				if(vconectar.getConectado()){
					lblConectado.setText("Conectado");
					lblConectado.setForeground(Color.GREEN);
					okButton.setEnabled(true);
					try {
						Thread notif = new Thread(new BarraNotificadora(VLogin.this, "Conexión establecida con el servidor " +
								Main.socket.getInetAddress().toString()+ ":" + Main.socket.getPort(), BarraNotificadora.OK_MESSAGE, 3000));
						notif.start();
					} catch (ValorIncorrectoEx e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						Thread notif = new Thread(new BarraNotificadora(VLogin.this, "Error al establecer la conexión con el " +
								"servidor", BarraNotificadora.ERROR_MESSAGE, 3000));
						notif.start();
					} catch (ValorIncorrectoEx e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSeleccionarServidor.setBounds(12, 221, 182, 25);
		contentPanel.add(btnSeleccionarServidor);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(Color.BLACK);
			lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUsuario.setBounds(28, 119, 101, 14);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblPass = new JLabel("Contraseña");
			lblPass.setForeground(Color.BLACK);
			lblPass.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPass.setBounds(28, 165, 101, 14);
			contentPanel.add(lblPass);
		}
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(147, 113, 182, 25);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(147, 159, 182, 24);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		lblConectado = new JLabel("NO Conectado");
		lblConectado.setForeground(Color.RED);
		lblConectado.setBounds(12, 12, 127, 15);
		contentPanel.add(lblConectado);
		{
			JLabel Fondo = new JLabel("");
			Fondo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("imagenes/fondo_login.png")));//"/home/erlantz/Descargas/fondo_login.png"));
			Fondo.setBounds(0, 0, 400, 250);
			contentPanel.add(Fondo);
		}
		
		if(Main.socket.isConnected()){
			lblConectado.setText("Conectado");
			lblConectado.setForeground(Color.GREEN);
			okButton.setEnabled(true);
		}
	}
	
	public boolean comprobarip(String ip){
		if(ip == null){
			return false;
		}
		String octetos[] = ip.split("\\.");
		if (octetos.length != 4){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		int uno,dos,tres,cuatro;
		try{
			uno = Integer.parseInt(octetos[0]);
			dos = Integer.parseInt(octetos[1]);
			tres = Integer.parseInt(octetos[2]);
			cuatro = Integer.parseInt(octetos[3]);
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException");
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(uno < 1 || uno > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(dos < 0 || dos > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(tres < 0 || tres > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(cuatro < 0 || cuatro > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VLogin.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		return true;
	}
}