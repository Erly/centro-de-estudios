package modelo.Usuarios;

import java.io.Serializable;


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

}
