package modelo.Usuarios;

import java.io.Serializable;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Aula;
import modelo.Main;
import modelo.Peticion;
import modelo.Respuesta;
import modelo.Software;


public class Tecnico extends Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5109449268774445409L;

	public Tecnico() {
		// TODO Auto-generated constructor stub
	}
	
	public Tecnico(String nombre, String pass, String email){
		super(nombre, pass, email);
	}
	
	/**
	 * Inserta un nuevo aula en la base de datos y lo añade al vector.
	 * @param aula Aula que va a ser insertada.
	 */
	public void agregarAula(Aula aula){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, aula));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Aula creada correctamente", JOptionPane.INFORMATION_MESSAGE);
				Main.centroEstudios.cargarAulas();
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear el aulas", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Main.centroEstudios.cargarAulas();
		}else{
			JOptionPane.showMessageDialog(null, res.mensaje, "Error al modificar el aulas", JOptionPane.ERROR_MESSAGE);
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
				Main.centroEstudios.cargarAulas();
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
				Main.centroEstudios.cargarAulas();
			}
		} catch (ValorIncorrectoEx e) {
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void agregarSoftware(Software software){
		try{
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, software));
			if(res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Sofware agregado con exito", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al agregar el software", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
