package modelo;

import modelo.Hardware.*;
import modelo.Usuarios.*;

public class Peticion {

	private int accion;
	
	public static final int CONSULTAR = 0;
	public static final int INSERTAR = 1;
	public static final int MODIFICAR = 2;
	public static final int BORRAR = 3;
	
	private Object objeto;
	
	public Peticion() {
		// TODO Auto-generated constructor stub
	}

	public Peticion(int accion, Aula aula){
		accion = this.accion;
		objeto = aula;
	}

	public Peticion(int accion, Equipo equipo){
		accion = this.accion;
		objeto = equipo;
	}

	public Peticion(int accion, CPU cpu){
		accion = this.accion;
		objeto = cpu;
	}

	public Peticion(int accion, HDD hdd){
		accion = this.accion;
		objeto = hdd;
	}

	public Peticion(int accion, Monitor monitor){
		accion = this.accion;
		objeto = monitor;
	}

	public Peticion(int accion, PlacaBase placabase){
		accion = this.accion;
		objeto = placabase;
	}

	public Peticion(int accion, RAM ram){
		accion = this.accion;
		objeto = ram;
	}

	public Peticion(int accion, TAudio taudio){
		accion = this.accion;
		objeto = taudio;
	}

	public Peticion(int accion, TGrafica grafica){
		accion = this.accion;
		objeto = grafica;
	}

	public Peticion(int accion, TRed tred){
		accion = this.accion;
		objeto = tred;
	}

	public Peticion(int accion, Usuario usuario){
		accion = this.accion;
		objeto = usuario;
	}

	public Peticion(int accion, Tecnico tecnico){
		accion = this.accion;
		objeto = tecnico;
	}

	public Peticion(int accion, Administrador administrador){
		accion = this.accion;
		objeto = administrador;
	}

	public void tratar(){
		
	}
}
