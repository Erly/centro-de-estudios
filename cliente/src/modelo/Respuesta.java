package modelo;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Vector;

import datos.Seriales;

import modelo.Hardware.Hardware;

public class Respuesta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = Seriales.RESPUESTA;
	public Boolean exito;
	public String mensaje;
	public Equipo equipo;
	public Hardware hardware;
	public Vector resultado;
	public Hashtable resultado2;

	public Respuesta() {}
	
	public Respuesta(Boolean exito){
		this.exito=exito;
	}
	
	public Respuesta(Boolean exito, String mensaje){
		this(exito);
		this.mensaje=mensaje;
	}
	
	public Respuesta(Boolean exito, String mensaje, Equipo equipo){
		this(exito, mensaje);
		this.equipo=equipo;
	}
	
	public Respuesta(Boolean exito, String mensaje, Hardware resultado){
		this(exito,mensaje);
		this.hardware=resultado;
	}
	
	public Respuesta(Boolean exito, String mensaje, Vector resultado){
		this(exito,mensaje);
		this.resultado=resultado;
	}
	
	public Respuesta(Boolean exito, String mensaje, Hashtable resultado){
		this(exito,mensaje);
		this.resultado2=resultado;
	}
}
