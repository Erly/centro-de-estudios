package modelo;

import java.io.Serializable;
import modelo.Usuarios.*;

public class Peticion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CONSULTAR = 0;
	public static final int INSERTAR = 1;
	public static final int BORRAR = 2;
	private static final int MODIFICAR = 3;
	private static final int LOGIN = 4;
	private static final int CARGARHARD = 5;
	
	public static final int USUARIOS = 0;
	public static final int AULAS = 1;
	public static final int EQUIPOS = 2;
	public static final int PLACABASE = 3;
	public static final int HDD = 4;
	public static final int CPU = 5;
	public static final int RAM = 6;
	public static final int TGRAFICA = 7;
	public static final int TAUDIO = 8;
	public static final int MONITOR = 9;
	public static final int TRED = 10;
	public static final int SOFTWARE = 11;
	public static final int SOLICITUDES = 12;
	
	
	private int accion;
	private int tipo;
	private Object objeto;
	private Object nuevoObjeto;
	private Aula aula;
	private Equipo equipo;
	private Usuario usuario;
	private boolean salir;
	/*String tabla;
	private String[] filtros;
	private String[] extras;*/
	
	public Peticion() {}
	
	public Peticion(int tipo) throws Exception{
		if(tipo < 0 || tipo > 12 || tipo == 2)
			throw new Exception();
		accion=CONSULTAR;
		this.tipo = tipo;
	}
	
	public Peticion(Aula aula){
		accion = CONSULTAR;
		tipo = EQUIPOS;
		this.aula=aula;
	}
	
	public Peticion(int tipo, Equipo equipo) throws Exception{
		if(tipo < 3 || tipo > 11)
			throw new Exception();
		accion = CONSULTAR;
		this.tipo = tipo;
		this.equipo = equipo;
	}
	
	public Peticion(int accion, Object objeto) throws Exception{
		if(accion != 1 && accion != 3)
			throw new Exception();
		this.accion=accion;
		this.objeto=objeto;
	}
	
	public Peticion(Object objeto, Object nuevoObjeto){
		accion=MODIFICAR;
		this.objeto=objeto;
	}
	
	public Peticion(Usuario usuario){
		accion=LOGIN;
		this.usuario = usuario;
	}
	
	public Peticion(Equipo equipo){
		accion=CARGARHARD;
		this.equipo=equipo;
	}
	
	public Peticion(boolean salir){
		this.salir=salir;
	}	
}
