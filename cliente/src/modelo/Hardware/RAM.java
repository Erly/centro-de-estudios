package modelo.Hardware;

@SuppressWarnings("serial")
public class RAM extends Hardware{

	int capacidad;
	int velocidad;
	
	public RAM() {
	}

	public RAM(String modelo, String marca, int capacidad, int velocidad) {
		super(modelo, marca);
		this.capacidad = capacidad;
		this.velocidad = velocidad;
	}
	
	public String toString(){
		return marca + " " + modelo + " \t" + capacidad + "GB " + velocidad + "Mhz";
	}
}
