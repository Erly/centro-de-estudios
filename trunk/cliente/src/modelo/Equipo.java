package modelo;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JOptionPane;

import modelo.Hardware.*;

public class Equipo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int codAula;
	private int codEquipo;
	private PlacaBase placaBase;
	private Vector<HDD> HDDs= new Vector<HDD>();
	private CPU cpu;
	private Vector<RAM> ram = new Vector<RAM>();
	private Vector<TGrafica> tGraficas = new Vector<TGrafica>();
	private TAudio tAudio;
	private Monitor monitor;
	private Vector<TRed> tRed = new Vector<TRed>();

	public Equipo() {
	}
	
	public Equipo(int codEquipo){
		this.codEquipo = codEquipo;
	}

	public Equipo(int codAula, int codEquipo, PlacaBase placaBase, Vector<HDD> HDDs,
			CPU cpu, Vector<RAM> ram, Vector<TGrafica> tGraficas,
			TAudio tAudio, Monitor monitor, Vector<TRed> tRed) {
		this.codAula = codAula;
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

	public int getCodAula(){
		return codAula;
	}
	
	public void setCodAula(int codAula){
		this.codAula = codAula;
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

	public void cargarHardware(){
		Respuesta res = Main.enviarPeticion(new Peticion(this));
		if(res.exito){
			Equipo eq = (Equipo)res.equipo;
			montarHardware(eq);
		}else{
			JOptionPane.showMessageDialog(null, res.mensaje, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void montarHardware(Equipo equipo){
		this.setPlacaBase(equipo.getPlacaBase());
		this.setHDDs(equipo.getHDDs());
		this.setCpu(equipo.getCpu());
		this.setRam(equipo.getRam());
		this.settGraficas(equipo.gettGraficas());
		this.settAudio(equipo.gettAudio());
		this.setMonitor(equipo.getMonitor());
		this.settRed(equipo.gettRed());
		
	}
}
