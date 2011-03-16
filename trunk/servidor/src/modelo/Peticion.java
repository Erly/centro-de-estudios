package modelo;

import modelo.Hardware.*;
import modelo.Usuarios.*;

public class Peticion {
	
	public static final int CONSULTAR = 0;
	public static final int INSERTAR = 1;
	public static final int MODIFICAR = 2;
	public static final int BORRAR = 3;
	
	private int accion;
	private Object objeto;
	private Object nuevoObjeto;
	private String[] filtros;
	private String[] extras;
	
	public Peticion() {}
	
	public Peticion(int accion, Object objeto) throws Exception{
		if(accion != 1 && accion != 3)
			throw new Exception();
		this.accion=accion;
		this.objeto=objeto;
	}
	
	public Peticion(int accion, Object objeto, Object nuevoObjeto) throws Exception{
		if(accion != 2)
			throw new Exception();
		this.accion=accion;
		this.objeto=objeto;
	}
	
	public Peticion(int accion, String tabla, String[] filtros) throws Exception{
		if(accion != 0)
			throw new Exception();
		this.accion=accion;
		this.filtros = filtros;
	}
	
	public Peticion(int accion, String tabla, String[] filtros, String[] extras) throws Exception{
		this(accion, tabla, filtros);
		this.extras=extras;
	}
	
	public void tratar(){
		switch (accion) {
		case 0:
			
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		}
	}
}
