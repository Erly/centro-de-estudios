package interfaz;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import excepciones.ValorIncorrectoEx;

import modelo.Main;
import modelo.Peticion;
import modelo.Respuesta;
import modelo.Usuarios.Administrador;
import modelo.Usuarios.Tecnico;
import modelo.Usuarios.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class VNuevoUsuario extends JInternalFrame {
	private JTextField txtNombre;
	private JPasswordField txtPass;
	private JComboBox cmbTipo;
	private JFormattedTextField txtEmail;

	/**
	 * Create the frame.
	 */
	public VNuevoUsuario() {
		setTitle("Nuevo Usuario");
		setBounds(100, 100, 330, 310);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 29, 77, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(50, 76, 77, 15);
		getContentPane().add(lblContrasea);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 124, 77, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(50, 174, 77, 15);
		getContentPane().add(lblTipo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(160, 23, 122, 27);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		cmbTipo = new JComboBox(new String[]{"Usuario", "Tecnico", "Administrador"});
		cmbTipo.setBounds(160, 169, 122, 25);
		getContentPane().add(cmbTipo);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario usuario = null;
				String tipo = "";
				switch(cmbTipo.getSelectedIndex()){
				case 0:
					usuario = new Usuario(txtNombre.getText(), String.valueOf(txtPass.getPassword()), txtEmail.getText());
					tipo = "Usuario";
					break;
				case 1:
					usuario = new Tecnico(txtNombre.getText(), String.valueOf(txtPass.getPassword()), txtEmail.getText());
					tipo = "Técnico";
					break;
				case 2:
					usuario = new Administrador(txtNombre.getText(), String.valueOf(txtPass.getPassword()), txtEmail.getText());
					tipo = "Administrador";
					break;
				}
				try {
					Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, usuario));
					if(res.exito){
						JOptionPane.showMessageDialog(null, "El usuario " + txtNombre.getText() + " de tipo " + tipo + 
								" ha sido creado con éxito.", "Usuario creado correctamente", JOptionPane.INFORMATION_MESSAGE);
						VNuevoUsuario.this.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear el usuario", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ValorIncorrectoEx e1) {
					JOptionPane.showMessageDialog(null, "Algun dato introducido es incorrecto.", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrear.setBounds(50, 227, 100, 27);
		getContentPane().add(btnCrear);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VNuevoUsuario.this.setVisible(false);
			}
		});
		btnCancelar.setBounds(182, 227, 100, 27);
		getContentPane().add(btnCancelar);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(160, 70, 122, 27);
		getContentPane().add(txtPass);
		
		txtEmail = new JFormattedTextField();
		txtEmail.setBounds(160, 118, 122, 27);
		getContentPane().add(txtEmail);

	}
}
