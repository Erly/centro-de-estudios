package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import interfaz.Main;
import modelo.Hardware.*;
import modelo.Usuarios.*;

public class Peticion {
	
	private static final int CONSULTAR = 0;
	public static final int INSERTAR = 1;
	public static final int BORRAR = 2;
	private static final int MODIFICAR = 3;
	
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
	
	public Peticion(int tipo, Aula aula, Equipo equipo) throws Exception{
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
	
	/*
	public Peticion(int accion, String tabla, String[] filtros) throws Exception{
		if(accion != 0)
			throw new Exception();
		this.accion=accion;
		this.tabla = tabla;
		this.filtros = filtros;
	}
	
	public Peticion(int accion, String tabla, String[] filtros, String[] extras) throws Exception{
		this(accion, tabla, filtros);
		this.extras=extras;
	}*/
	
	public Respuesta tratar(){
		Respuesta respuesta = null;
		switch (accion) {
		case 0:
			/*String select;
			if(filtros != null){
				String filtro = filtros[0];
				for(int i=1;i<filtros.length;i++){
					filtro += " AND " + filtros[i];
				}
				if(extras != null){
					String extra = "";
					for(int i=0;i<extras.length;i++){
						extra += ", " + extras[i];
					}
					select = "SELECT * FROM " + tabla + " WHERE " + filtro + extra;
				}else{
					select = "SELECT * FROM " + tabla + " WHERE " + filtro;
				}
			}else{
				select = "SELECT * FROM " + tabla;
			}
			try {
				ResultSet rs = Main.db.lanzarSelect(select);
				while(rs.next()){
					
				}
				respuesta = new Respuesta(true, "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				switch(tipo){
				case 0:
					Hashtable<String, Usuario> usuarios = Main.db.obtenerUsuarios();
					respuesta = new Respuesta(true, "Usuarios obtenidos con exito", usuarios);
					break;
				case 1:
					Vector<Aula> aulas = Main.db.obtenerAulas();
					respuesta = new Respuesta(true, "Aulas obtenidas con exito.", aulas);
					break;
				case 2:
					Vector<Equipo> equipos = Main.db.obtenerEquipos(aula);
					respuesta = new Respuesta(true, "Equipos obtenidos con exito.", equipos);
					break;
				case 3:
					if (equipo==null){
						Vector<PlacaBase> placasBase = Main.db.obtenerPlacaBase();
						respuesta = new Respuesta(true, "Placas base obtenidas con exito.", placasBase);
					}else{
						PlacaBase placaBase = Main.db.obtenerPlacaBase(equipo, aula);
					}
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 7:
					
					break;
				case 8:
					
					break;
				case 9:
					
					break;
				case 10:
					
					break;
				case 11:
					
					break;
				case 12:
					
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		}
		return respuesta;
	}
}
