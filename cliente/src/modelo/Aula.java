package modelo;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class Aula implements Serializable{

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
	
}
