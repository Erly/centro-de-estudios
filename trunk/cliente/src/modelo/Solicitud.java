package modelo;

import excepciones.ValorIncorrectoEx;
import modelo.Usuarios.Usuario;

public class Solicitud {
	
	private int codigo;
	public String Descripcion;
	public Usuario usuario;
	public Aula aula;
	public Equipo equipo;
	private boolean exito;

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
		exito = false;
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

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

}
