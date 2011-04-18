package modelo;

import excepciones.ValorIncorrectoEx;
import modelo.Usuarios.Usuario;

public class Solicitud {
	
	private int codigo;
	public String Descripcion;
	public Usuario usuario;
	public Aula aula;
	public Equipo equipo;
	private boolean realizado;
	public String observaciones;

	public Solicitud() {
		// TODO Auto-generated constructor stub
	}

	public Solicitud(int codigo, String descripcion, Usuario usuario,
			Aula aula, Equipo equipo) throws ValorIncorrectoEx {
		this.setCodigo(codigo);
		Descripcion = descripcion;
		this.usuario = usuario;
		this.aula = aula;
		this.equipo = equipo;
		realizado = false;
		observaciones = "";
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws ValorIncorrectoEx {
		if(codigo >= 1){
			this.codigo = codigo;
		} else {
			throw new ValorIncorrectoEx();
		}
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public boolean isRealizado() {
		return realizado;
	}

}
