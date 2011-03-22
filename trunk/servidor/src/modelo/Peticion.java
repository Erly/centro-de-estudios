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
	
	public Peticion(boolean salir){
		this.salir=salir;
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
		if(salir)
			return new Respuesta(true, "SALIR");
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
						PlacaBase placaBase = Main.db.obtenerPlacaBase(equipo);
						respuesta = new Respuesta(true, "Placa base obtenida con exito.", placaBase);
					}
					break;
				case 4:
					Vector<HDD> hdds;
					if (equipo==null){
						hdds = Main.db.obtenerHDDs();
						respuesta = new Respuesta(true, "HDDs obtenidos con exito.", hdds);
					}else{
						hdds = Main.db.obtenerHDDs(equipo);
						respuesta = new Respuesta(true, "HDDs obtenidos con exito.", hdds);
					}
					break;
				case 5:
					if (equipo==null){
						Vector<CPU> cpus = Main.db.obtenerCPU();
						respuesta = new Respuesta(true, "CPUs obtenidas con exito.", cpus);
					}else{
						CPU cpu = Main.db.obtenerCPU(equipo);
						respuesta = new Respuesta(true, "CPU obtenida con exito.", cpu);
					}
					break;
				case 6:
					Vector<RAM> ram;
					if (equipo==null){
						ram= Main.db.obtenerRAM();
						respuesta = new Respuesta(true, "RAM obtenida con exito.", ram);
					}else{
						ram = Main.db.obtenerRAM(equipo);
						respuesta = new Respuesta(true, "RAM obtenida con exito.", ram);
					}
					break;
				case 7:
					Vector<TGrafica> graficas;
					if (equipo==null){
						graficas = Main.db.obtenerTGrafica();
						respuesta = new Respuesta(true, "Tarjetas Graficas obtenidas con exito.", graficas);
					}else{
						graficas = Main.db.obtenerTGrafica(equipo);
						respuesta = new Respuesta(true, "Tarjetas Graficas obtenidas con exito.", graficas);
					}
					break;
				case 8:
					if (equipo==null){
						Vector<TAudio> taudio = Main.db.obtenerTAudio();
						respuesta = new Respuesta(true, "Tarjetas de Audio obtenidas con exito.", taudio);
					}else{
						TAudio taudio = Main.db.obtenerTAudio(equipo);
						respuesta = new Respuesta(true, "Tarjeta de Audio obtenida con exito.", taudio);
					}
					break;
				case 9:
					if (equipo==null){
						Vector<Monitor> monitores = Main.db.obtenerMonitor();
						respuesta = new Respuesta(true, "CPUs obtenidas con exito.", monitores);
					}else{
						Monitor monitor = Main.db.obtenerMonitor(equipo);
						respuesta = new Respuesta(true, "CPU obtenida con exito.", monitor);
					}
					break;
				case 10:
					Vector<TRed> redes;
					if (equipo==null){
						redes = Main.db.obtenerTRed();
						respuesta = new Respuesta(true, "Tarjetas de Red obtenidas con exito.", redes);
					}else{
						redes = Main.db.obtenerTRed(equipo);
						respuesta = new Respuesta(true, "Tarjetas de Red obtenidas con exito.", redes);
					}
					break;
				case 11:
					
					break;
				case 12:
					
					break;
				}
			} catch (SQLException e) {
				respuesta = new Respuesta(false, "Sentencia SQL incorrecta");
			}
			break;
		case 1:
			if(objeto.getClass().toString().equals(Usuario.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Tecnico.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Administrador.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Aula.class.toString())){
				try {
					Main.db.insertarAula((Aula)objeto);
					respuesta= new Respuesta(true, "La aula ha sido creada con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "La aula no ha podido ser creada debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(Equipo.class.toString())){
				try {
					Main.db.insertarEquipo((Equipo)objeto);
					respuesta= new Respuesta(true, "El equipo ha sido creado con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "El equipo no ha podido ser creada debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(PlacaBase.class.toString())){
				
			}else if(objeto.getClass().toString().equals(HDD.class.toString())){
				
			}else if(objeto.getClass().toString().equals(CPU.class.toString())){
				
			}else if(objeto.getClass().toString().equals(RAM.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TGrafica.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TAudio.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Monitor.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TRed.class.toString())){
				
			}/*else if(objeto.getClass().toString().equals(Software.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Solicitud.class.toString())){
				
			}*/
			break;
		case 2:
			if(objeto.getClass().toString().equals(Usuario.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Tecnico.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Administrador.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Aula.class.toString())){
				try {
					Main.db.actualizarAula((Aula)nuevoObjeto);
					respuesta= new Respuesta(true, "La aula ha sido creada con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "La aula no ha podido ser creada debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(Equipo.class.toString())){
				try {
					Main.db.actualizarEquipo((Equipo)nuevoObjeto);
					respuesta= new Respuesta(true, "El equipo ha sido creado con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "El equipo no ha podido ser creada debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(PlacaBase.class.toString())){
				
			}else if(objeto.getClass().toString().equals(HDD.class.toString())){
				
			}else if(objeto.getClass().toString().equals(CPU.class.toString())){
				
			}else if(objeto.getClass().toString().equals(RAM.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TGrafica.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TAudio.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Monitor.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TRed.class.toString())){
				
			}/*else if(objeto.getClass().toString().equals(Software.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Solicitud.class.toString())){
				
			}*/
			break;
		case 3:
			if(objeto.getClass().toString().equals(Usuario.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Tecnico.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Administrador.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Aula.class.toString())){
				try {
					Main.db.borrarAula((Aula)objeto);
					respuesta= new Respuesta(true, "La aula ha sido eliminada con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "La aula no ha podido ser eliminada debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(Equipo.class.toString())){
				try {
					Main.db.borrarEquipo((Equipo)objeto);
					respuesta= new Respuesta(true, "El equipo ha sido eliminado con exito");
				} catch (SQLException e) {
					respuesta = new Respuesta(false, "El equipo no ha podido ser eliminado debido a un error en los datos introducidos. El error devuelto es: \r\n"
							+ e.getMessage().toString());
				}
			}else if(objeto.getClass().toString().equals(PlacaBase.class.toString())){
				
			}else if(objeto.getClass().toString().equals(HDD.class.toString())){
				
			}else if(objeto.getClass().toString().equals(CPU.class.toString())){
				
			}else if(objeto.getClass().toString().equals(RAM.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TGrafica.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TAudio.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Monitor.class.toString())){
				
			}else if(objeto.getClass().toString().equals(TRed.class.toString())){
				
			}/*else if(objeto.getClass().toString().equals(Software.class.toString())){
				
			}else if(objeto.getClass().toString().equals(Solicitud.class.toString())){
				
			}*/
			break;
		}
		return respuesta;
	}
}
