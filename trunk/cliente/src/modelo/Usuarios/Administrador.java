package modelo.Usuarios;

import java.io.Serializable;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Main;
import modelo.Peticion;
import modelo.Respuesta;


public class Administrador extends Tecnico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6041297726235740130L;

	public Administrador() {
		// TODO Auto-generated constructor stub
	}
	
	public Administrador(String nombre, String pass, String email){
		super(nombre, pass, email);
	}

	public void altaUsuario(Usuario usuario){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, usuario));
			if(res.exito){
				JOptionPane.showMessageDialog(null, "El usuario " + usuario.getNombre() + " de tipo " + 
						usuario.getClass().toString().substring(usuario.getClass().toString().lastIndexOf('.')) + 
						" ha sido creado con éxito.", "Usuario creado correctamente", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear el usuario", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e1) {
			JOptionPane.showMessageDialog(null, "Algun dato introducido es incorrecto.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

}
