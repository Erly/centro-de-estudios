package modelo;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

import datos.Seriales;

import excepciones.ValorIncorrectoEx;

public class Aula implements Serializable{

	private static final long serialVersionUID = Seriales.AULA;
	private int codAula;
	private String curso;
	private Vector<Equipo> equipos;
	
	public Aula() {
	}
	
	public Aula(int codAula, String curso){
		this.setCodAula(codAula);
		this.setCurso(curso);
	}

	public void setEquipos(Vector<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Vector<Equipo> getEquipos() {
		return equipos;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCodAula(int codAula) {
		this.codAula = codAula;
	}

	public int getCodAula() {
		return codAula;
	}
	public String toString(){
		return codAula + " - " + curso;
	}
	
	/**
	 * Inserta un nuevo equipo en la base de datos y lo añade en el vector Equipos;
	 * @param equipo Equipo que va a ser insertado
	 */
	public void agregarEquipo(Equipo equipo){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.INSERTAR, (Object)equipo));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Equipo creado con exito", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al crear el equipo", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Elimina un equipo de la base de datos.
	 * @param equipo Equipo que va a ser borrado.
	 */
	public void borrarEquipo(Equipo equipo){
		try {
			Respuesta res = Main.enviarPeticion(new Peticion(Peticion.BORRAR, (Object)equipo));
			if (res.exito){
				JOptionPane.showMessageDialog(null, res.mensaje, "Equipo borrado con exito", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ValorIncorrectoEx e) {
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Borra todos los equipos de este aula de la base de datos.
	 */
	public void borrarEquipos(){
		try{
			for(int i = 0; i < equipos.size(); i++){
				Respuesta res = Main.enviarPeticion(new Peticion(Peticion.BORRAR, equipos.elementAt(i)));
				if (!res.exito){
					JOptionPane.showMessageDialog(null, res.mensaje, "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (ValorIncorrectoEx e){
			JOptionPane.showMessageDialog(null, "La accion solicitada no coincide con el constructor empleado", "Error al borrar el equipo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
