package modelo;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Usuarios.Usuario;

public class CentroEstudios {
	
	Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	private Vector<Aula> aulas = new Vector<Aula>();
	private Vector<Solicitud> solicitudes = new Vector<Solicitud>();

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

	public CentroEstudios() {}
	
	/**
	 * Inserta un nuevo aula en la base de datos y lo añade al vector.
	 * @param aula Aula que va a ser insertada.
	 */
	public void agregarAula(Aula aula){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, aula));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Aula creada correctamente", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear el aulas", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarAulas();
	}
	
	/**
	 * Modifica un aula existente con con los parametros de otro objeto Aula.
	 * @param aulaActual Aula que va a ser modificada.
	 * @param nuevaAula Aula cuyos valores se utilizaran para modificar el aula.
	 */
	public void modificarAula(Aula aulaActual, Aula nuevaAula){
		Respuesta res = Main.enviarPeticion(new Peticion(aulaActual, nuevaAula));
		if (res.exito){
			JOptionPane.showMessageDialog(null, res.mensaje, "Aula modificada correctamente", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, res.mensaje, "Error al modificar el aulas", JOptionPane.ERROR_MESSAGE);
		}
	}
	
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
	 * Elimina un aula vacía de la base de datos y despues recarga las aulas llamando al método cargarAulas().
	 * @param aula Aula que va a ser eliminada.
	 */
	public void eliminarAula(Aula aula){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.BORRAR, aula));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Aula eliminada con exito", JOptionPane.INFORMATION_MESSAGE);
				cargarAulas();
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al borrar la aula", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Elimina un aula y todos su equipos de la base de datos.
	 * @param aula Aula que va a ser eliminada junto con sus equipos.
	 */
	public void eliminarAulaRecursivamente(Aula aula){
		try {
			aula.borrarEquipos();
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.BORRAR, aula));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Aula eliminada satisfactoriamente", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Introduce una solicitud en la base de datos.
	 * @param solicitud Solicitud que va a ser introducida.
	 */
	public void crearSolicitud(Solicitud solicitud){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, solicitud));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Solicitud creada con exito", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear la solicitud", JOptionPane.ERROR_MESSAGE);
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
	
	public void cargarUsuario(){
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

}
