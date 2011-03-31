package modelo;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JOptionPane;

import excepciones.ValorIncorrectoEx;

import modelo.Usuarios.Usuario;

public class CentroEstudios {
	
	Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	Vector<Aula> aulas = new Vector<Aula>();

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

	public CentroEstudios() {}
	
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
	}
	
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
	
	public void eliminarAula(Aula aula){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.BORRAR, aula));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Aula eliminada con exito", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al borrar la aula", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
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

}
