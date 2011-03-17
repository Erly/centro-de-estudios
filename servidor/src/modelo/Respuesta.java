package modelo;

import java.util.Hashtable;
import java.util.Vector;

public class Respuesta {
	
	public Boolean exito;
	public String mensaje;
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
	
	public Respuesta(Boolean exito, String mensaje, Vector resultado){
		this(exito,mensaje);
		this.resultado=resultado;
	}
	
	public Respuesta(Boolean exito, String mensaje, Hashtable resultado){
		this(exito,mensaje);
		this.resultado2=resultado;
	}
}
