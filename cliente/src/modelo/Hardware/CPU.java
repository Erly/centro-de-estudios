package modelo.Hardware;

@SuppressWarnings("serial")
public class CPU extends Hardware{

	int nucleos;
	float velocidad;
	int cache;
	
	public CPU() {
	}

	public CPU(String modelo, String marca, int nucleos, float velocidad, int cache) {
		super(modelo, marca);
		this.nucleos = nucleos;
		this.velocidad = velocidad;
		this.cache = cache;
	}
	
	public String toString(){
		return marca + " " + modelo + " \t" + nucleos + "x " + velocidad + "Ghz";
	}
}
