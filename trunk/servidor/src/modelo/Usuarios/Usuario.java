package modelo.Usuarios;

import java.io.Serializable;

public class Usuario implements Serializable{

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

}
