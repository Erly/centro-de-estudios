package interfaz;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import excepciones.ValorIncorrectoEx;

import modelo.Main;
import modelo.Software;
import modelo.Usuarios.Tecnico;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VNuevoSoft extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtVersion;
	private JTextField txtCategoria;
	private JTextField txtLicencia;
	private JTextField txtDesarrollador;

	/**
	 * Create the frame.
	 */
	public VNuevoSoft() {
		setClosable(true);
		setBounds(100, 100, 470, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(147, 23, 60, 15);
		getContentPane().add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(219, 17, 70, 27);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(88, 62, 60, 15);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(160, 56, 201, 27);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(16, 112, 70, 15);
		getContentPane().add(lblCategoria);
		
		txtVersion = new JTextField();
		txtVersion.setBounds(88, 157, 122, 27);
		getContentPane().add(txtVersion);
		txtVersion.setColumns(10);
		
		JLabel lblVersion = new JLabel("Version");
		lblVersion.setBounds(16, 163, 60, 15);
		getContentPane().add(lblVersion);
		
		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setBounds(254, 112, 60, 15);
		getContentPane().add(lblLicencia);
		
		JLabel lblDesarrollador = new JLabel("Desarrollador");
		lblDesarrollador.setBounds(219, 163, 95, 15);
		getContentPane().add(lblDesarrollador);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(88, 106, 122, 27);
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);
		
		txtLicencia = new JTextField();
		txtLicencia.setBounds(326, 106, 122, 27);
		getContentPane().add(txtLicencia);
		txtLicencia.setColumns(10);
		
		txtDesarrollador = new JTextField();
		txtDesarrollador.setBounds(326, 157, 122, 27);
		getContentPane().add(txtDesarrollador);
		txtDesarrollador.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VNuevoSoft.this.dispose();
			}
		});
		btnCancelar.setBounds(232, 225, 100, 27);
		getContentPane().add(btnCancelar);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("OK")){
					anadirSoft();
				}
			}
		});
		btnAadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				anadirSoft();
			}
		});
		btnAadir.setBounds(107, 225, 100, 27);
		btnAadir.setActionCommand("OK");
		getRootPane().setDefaultButton(btnAadir);
		getContentPane().add(btnAadir);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b==true){
			resetearCampos();
		}
	}
	
	private void resetearCampos(){
		cargarCodigo();
		txtNombre.setText("");
		txtCategoria.setText("");
		txtLicencia.setText("");
		txtVersion.setText("");
		txtDesarrollador.setText("");
	}
	
	private void cargarCodigo(){
		Main.centroEstudios.cargarSoftware();
		if(Main.centroEstudios.getSoftware().isEmpty()){
			txtCodigo.setText("1");
		}else{
			txtCodigo.setText("" + (Main.centroEstudios.getSoftware().lastElement().getCodigo() + 1));
		}
	}
	
	private void anadirSoft(){
		try{
			int codigo = Integer.parseInt(txtCodigo.getText());
			String nombre = txtNombre.getText();
			String categoria = txtCategoria.getText();
			String licencia = txtLicencia.getText();
			float version = Float.parseFloat(txtVersion.getText());
			String desarrollador = txtDesarrollador.getText();
			
			if(!nombre.trim().isEmpty() && !categoria.trim().isEmpty() && !licencia.trim().isEmpty() && 
					!desarrollador.trim().isEmpty()){
				Software software = new Software(codigo, nombre, version, categoria, licencia, desarrollador);
				((Tecnico)Main.usuario).agregarSoftware(software);
				VNuevoSoft.this.dispose();
			}
		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El codigo o la version introducidos no son validos", "Error de codigo/version", 
					JOptionPane.ERROR_MESSAGE);
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
