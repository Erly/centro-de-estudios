package modelo.Hardware;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Hardware implements Serializable{

	String modelo;
	String marca;

	public Hardware() {
	}

	public Hardware(String modelo, String marca) {
		this.modelo = modelo;
		this.marca = marca;
	} 
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
