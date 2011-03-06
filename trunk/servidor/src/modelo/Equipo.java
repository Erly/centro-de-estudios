package modelo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Vector;

import modelo.Hardware.*;

@SuppressWarnings("serial")
public class Equipo implements Serializable{
	
	int codEquipo;
	PlacaBase placaBase;
	Vector<HDD> HDDs= new Vector<HDD>();
	CPU cpu;
	Vector<RAM> ram = new Vector<RAM>();
	Vector<TGrafica> tGraficas = new Vector<TGrafica>();
	TAudio tAudio;
	Monitor monitor;
	Vector<TRed> tRed = new Vector<TRed>();

	public Equipo() {
	}
	
	public Equipo(int codEquipo){
		this.codEquipo = codEquipo;
	}

	public Equipo(int codEquipo, PlacaBase placaBase, Vector<HDD> HDDs,
			CPU cpu, Vector<RAM> ram, Vector<TGrafica> tGraficas,
			TAudio tAudio, Monitor monitor, Vector<TRed> tRed) {
		this.codEquipo = codEquipo;
		this.placaBase = placaBase;
		this.HDDs = HDDs;
		this.cpu = cpu;
		this.ram = ram;
		this.tGraficas = tGraficas;
		this.tAudio = tAudio;
		this.monitor = monitor;
		this.tRed = tRed;
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public PlacaBase getPlacaBase() {
		return placaBase;
	}

	public void setPlacaBase(PlacaBase placaBase) {
		this.placaBase = placaBase;
	}

	public Vector<HDD> getHDDs() {
		return HDDs;
	}

	public void setHDDs(Vector<HDD> hDDs) {
		HDDs = hDDs;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public Vector<RAM> getRam() {
		return ram;
	}

	public void setRam(Vector<RAM> ram) {
		this.ram = ram;
	}

	public Vector<TGrafica> gettGraficas() {
		return tGraficas;
	}

	public void settGraficas(Vector<TGrafica> tGraficas) {
		this.tGraficas = tGraficas;
	}

	public TAudio gettAudio() {
		return tAudio;
	}

	public void settAudio(TAudio tAudio) {
		this.tAudio = tAudio;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public Vector<TRed> gettRed() {
		return tRed;
	}

	public void settRed(Vector<TRed> tRed) {
		this.tRed = tRed;
	}
	
	public String toString(){
		return "PC " + codEquipo;
	}

	/*public void cargarHardware(Aula aula) throws SQLException{
		this.setPlacaBase(Main.db.obtenerPlacaBase(this, aula));
		this.setHDDs(Main.db.obtenerHDDs(this, aula));
		this.setCpu(Main.db.obtenerCPU(this, aula));
		this.setRam(Main.db.obtenerRAM(this, aula));
		this.settGraficas(Main.db.obtenerTGrafica(this, aula));
		this.settAudio(Main.db.obtenerTAudio(this, aula));
		this.setMonitor(Main.db.obtenerMonitor(this, aula));
		this.settRed(Main.db.obtenerTRed(this, aula));
	}*/
}
