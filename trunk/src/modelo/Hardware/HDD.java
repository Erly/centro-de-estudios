package modelo.Hardware;

@SuppressWarnings("serial")
public class HDD extends Hardware{

	int capacidad;
	int rpm;

	public HDD() {
	}
	
	public HDD(String modelo, String marca, int capacidad, int rpm) {
		super(modelo, marca);
		this.capacidad = capacidad;
		this.rpm = rpm;
	}
	
	public String toString(){
		return marca + " " + modelo + " \t" + capacidad + "GB " + rpm + "rpm";
	}
}
