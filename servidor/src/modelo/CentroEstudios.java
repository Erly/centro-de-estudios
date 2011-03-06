package modelo;

import java.util.Hashtable;
import java.util.Vector;

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

}
