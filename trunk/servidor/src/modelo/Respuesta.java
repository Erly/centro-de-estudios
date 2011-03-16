package modelo;

import java.util.Vector;

public class Respuesta {
	
	private Boolean exito;
	public String mensaje;
	public Vector[] resultado;

	public Respuesta() {}
	
	public Respuesta(Boolean exito){
		this.exito=exito;
	}
	
	public Respuesta(Boolean exito, String mensaje){
		this(exito);
		this.mensaje=mensaje;
	}
	
	public Respuesta(Boolean exito, String mensaje, Vector[] resultado){
		this(exito,mensaje);
		this.resultado=resultado;
	}
}
