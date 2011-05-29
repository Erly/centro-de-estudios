package modelo;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Usuarios.Usuario;

public class CentroEstudios {
	
	private Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	private Vector<Aula> aulas = new Vector<Aula>();
	private Vector<Solicitud> solicitudes = new Vector<Solicitud>();
	private Vector<Software> software = new Vector<Software>();

	public Hashtable<String, Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Hashtable<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Vector<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(Vector<Aula> aulas) {
		this.aulas = aulas;
	}

	public void setSolicitudes(Vector<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Vector<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSoftware(Vector<Software> software) {
		this.software = software;
	}

	public Vector<Software> getSoftware() {
		return software;
	}

	public CentroEstudios() {}
	
	
	
	/**
	 * Borra los elementos del vector aulas del centro de estudios y los vuelve a cargar desde la base de datos. 
	 * Obteniendo la versión mas actualziada de estos.
	 */
	public void cargarAulas(){
		this.getAulas().removeAllElements();
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.AULAS));
			if (res.exito){
				this.setAulas(res.resultado);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al obtener las aulas", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga todas las solicitudes de la base de datos en el vector Solicitudes.
	 */
	public void cargarSolicitudes(){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.SOLICITUDES));
			if(res.exito){
				setSolicitudes(res.resultado);
			} else {
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al obtener las peticiones", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarUsuarios(){
		try{
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.USUARIOS));
			if(res.exito){
				setUsuarios(res.resultado2);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al obtener los usuarios", JOptionPane.ERROR_MESSAGE);
			}
		}catch (ValorIncorrectoEx e) {
			
		}
	}

	public void cargarSoftware(){
		try{
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.SOFTWARE));
			if(res.exito){
				setSoftware(res.resultado);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al obtener el software", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			
		}
	}
	
	public int getSolicitudesPendientes(){
		cargarSolicitudes();
		int cont = 0;
		for(int i = 0; i < solicitudes.size(); i++){
			Solicitud sol = solicitudes.elementAt(i);
			if(!sol.isRealizado()){
				cont++;
			}
		}
		return cont;
	}
}
