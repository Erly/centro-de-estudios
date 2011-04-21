package modelo.Usuarios;

import java.io.Serializable;


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


}
