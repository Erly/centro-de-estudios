package interfaz;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;

import modelo.Main;
import modelo.Peticion;
import modelo.Respuesta;
import modelo.Usuarios.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings({"serial"})
public class VLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VLogin dialog = new VLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("ComboBox.disabledForeground"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Droid Serif", Font.BOLD, 40));
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(12, 12, 426, 71);
		contentPanel.add(lblLogin);
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String usuario = txtUsuario.getText();
					String pass = txtPassword.getText();
					try {
						Main.out.writeObject(new Peticion(new Usuario(usuario, pass, "")));
						Respuesta respuesta = (Respuesta)Main.in.readObject();
						JOptionPane.showMessageDialog(null, respuesta.mensaje, "Login", JOptionPane.DEFAULT_OPTION);
						if(respuesta.exito){
							
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
			});
			okButton.setBounds(287, 264, 54, 24);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(353, 264, 81, 24);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnSeleccionarServidor = new JButton("Seleccionar Servidor");
		btnSeleccionarServidor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ip = null;
				int puerto = 0;
				while(!comprobarip(ip)){
					ip = JOptionPane.showInputDialog("Introduce la ip del servidor: ");
					if(ip==null){
						
					}
				}
				while(puerto < 1 || puerto > 65535){
					puerto = Integer.parseInt(JOptionPane.showInputDialog("Introduce el puerto del servidor <1-65535>: "));
				}
				try {
					Main.socket = new Socket(ip, puerto);
					Main.out = new ObjectOutputStream(Main.socket.getOutputStream());
					Main.in = new ObjectInputStream(Main.socket.getInputStream());
				} catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(null, "No es posible encontrar un servidor en la dirección introducida.", "Host desconocido", JOptionPane.ERROR_MESSAGE);
				} catch (ConnectException ce){
					JOptionPane.showMessageDialog(null, "La conexión ha sido rechazada por el servidor. Asegurese de arrancar el servidor con los permisos adecuados.", "Host desconocido", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSeleccionarServidor.setBounds(12, 264, 182, 25);
		contentPanel.add(btnSeleccionarServidor);
		{
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(Color.WHITE);
			lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUsuario.setBounds(72, 119, 101, 14);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblPass = new JLabel("Contraseña");
			lblPass.setForeground(Color.WHITE);
			lblPass.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPass.setBounds(72, 165, 101, 14);
			contentPanel.add(lblPass);
		}
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(191, 113, 182, 25);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(191, 159, 182, 24);
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JCheckBox chkRecordarPass = new JCheckBox("Recordar Contraseña");
		chkRecordarPass.setBounds(72, 224, 202, 22);
		chkRecordarPass.setOpaque(false);
		contentPanel.add(chkRecordarPass);
		
		JLabel lblPassOlvidado = new JLabel("\u00BFHas olvidado tu contrase\u00F1a?");
		lblPassOlvidado.setForeground(new Color(128, 0, 128));
		lblPassOlvidado.setBounds(191, 195, 221, 14);
		contentPanel.add(lblPassOlvidado);
		
		JLabel usuarioIncorrecto = new JLabel("");
		usuarioIncorrecto.setIcon(new ImageIcon(VLogin.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		usuarioIncorrecto.setBounds(384, 113, 32, 32);
		usuarioIncorrecto.setVisible(false);
		contentPanel.add(usuarioIncorrecto);
		
		JLabel passwordIncorrecto = new JLabel("");
		passwordIncorrecto.setIcon(new ImageIcon(VLogin.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		passwordIncorrecto.setBounds(384, 157, 32, 32);
		passwordIncorrecto.setVisible(false);
		contentPanel.add(passwordIncorrecto);
		{
			JLabel Fondo = new JLabel("");
			Fondo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("imagenes/fondo_login.png")));//"/home/erlantz/Descargas/fondo_login.png"));
			Fondo.setBounds(0, 0, 450, 298);
			contentPanel.add(Fondo);
		}
	}
	
	public boolean comprobarip(String ip){
		if(ip == null){
			return false;
		}
		System.out.println(ip);
		String octetos[] = ip.split("\\.");
		System.out.println(octetos[0]);
		System.out.println(octetos[1]);
		System.out.println(octetos[2]);
		System.out.println(octetos[3]);
		if (octetos.length != 4){
			System.out.println("octetos");
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		int uno,dos,tres,cuatro;
		try{
			uno = Integer.parseInt(octetos[0]);
			dos = Integer.parseInt(octetos[1]);
			tres = Integer.parseInt(octetos[2]);
			cuatro = Integer.parseInt(octetos[3]);
			System.out.println("" + uno);
			System.out.println("" + dos);
			System.out.println("" + tres);
			System.out.println("" + cuatro);
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException");
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(uno < 1 || uno > 255){
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(dos < 0 || dos > 255){
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(tres < 0 || tres > 255){
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(cuatro < 0 || cuatro > 255){
			JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
