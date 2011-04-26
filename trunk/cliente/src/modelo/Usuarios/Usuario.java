package modelo.Usuarios;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Aula;
import modelo.Equipo;
import modelo.Main;
import modelo.Peticion;
import modelo.Software;
import modelo.Solicitud;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 260023862352422499L;
	private String nombre;
	private String pass;
	private String email;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String pass, String email){
		this.setNombre(nombre);
		this.setPass(pass);
		this.setEmail(email);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void crearSolicitud(Software software, String descripcion, Vector vAulasOEquipos) throws ValorIncorrectoEx{
		int codigo = Solicitud.getMaxCod();
		if(codigo == -1){
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al obtener el codgo para las solicitudes", 
					"Error de codigo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(vAulasOEquipos.elementAt(0).getClass().toString().equals(Aula.class.toString())){
			Vector<Aula> vAulas = vAulasOEquipos;
			for(int i = 0; i < vAulas.size(); i++){
				Solicitud solicitud = new Solicitud(codigo + i, software, descripcion, this, vAulas.elementAt(i));
				Main.enviarPeticion(new Peticion(Peticion.INSERTAR, solicitud));
			}
		}else{
			Vector<Equipo> vEquipos = vAulasOEquipos;
			for(int i = 0; i < vEquipos.size(); i++){
				Solicitud solicitud = new Solicitud(codigo + i, software, descripcion, this, vEquipos.elementAt(i));
				Main.enviarPeticion(new Peticion(Peticion.INSERTAR, solicitud));
			}
		}
	}
}