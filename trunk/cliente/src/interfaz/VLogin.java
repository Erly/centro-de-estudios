package interfaz;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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
}
