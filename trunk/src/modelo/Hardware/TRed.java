package modelo.Hardware;

@SuppressWarnings("serial")
public class TRed extends Hardware {
	
	String puerto;
	int capacidad;

	public TRed() {
	}

	public TRed(String modelo, String marca, String puerto, int capacidad) {
		super(modelo, marca);
		this.puerto = puerto;
		this.capacidad = capacidad;
	}
	
	public String toString(){
		if(modelo.toUpperCase().contains("INTEGRADA"))
			return "Integrada \t" + capacidad + "Mbps";
		return marca + " " + modelo + " \t" + capacidad + "Mbps";
	}
}
