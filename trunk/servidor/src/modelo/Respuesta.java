package modelo;

import java.util.Hashtable;
import java.util.Vector;

import modelo.Hardware.Hardware;

public class Respuesta {
	
	public Boolean exito;
	public String mensaje;
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
