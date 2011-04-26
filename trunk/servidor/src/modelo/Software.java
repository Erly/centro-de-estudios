package modelo;

import java.io.Serializable;

import excepciones.ValorIncorrectoEx;

public class Software implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 515682319167300184L;
	private int codigo;
	public String nombre;
	private float version;
	private String categoria;
	public String licencia;
	public String desarrollador;

	public Software() {
		// TODO Auto-generated constructor stub
	}

	public Software(int codigo, String nombre, float version, String categoria,
			String licencia, String desarrollador) throws ValorIncorrectoEx {
		this.setCodigo(codigo);
		this.nombre = nombre;
		this.setVersion(version);
		this.setCategoria(categoria);
		this.licencia = licencia;
		this.desarrollador = desarrollador;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) throws ValorIncorrectoEx {
		if(version > 0){
			this.version = version;
		} else {
			throw new ValorIncorrectoEx();
		}
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public String toString(){
		return nombre + " " + version;
	}
}