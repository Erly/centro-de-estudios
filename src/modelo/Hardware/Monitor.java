package modelo.Hardware;

@SuppressWarnings("serial")
public class Monitor extends Hardware{

	float pulgadas;
	String resolucion;
	int herzios;

	public Monitor() {
	}

	public Monitor(String modelo, String marca, float pulgadas, String resolucion, int herzios) {
		super(modelo, marca);
		this.pulgadas = pulgadas;
		this.resolucion = resolucion;
		this.herzios = herzios;
	}
	
	public String toString(){
		return marca + " " + modelo + " \t" + pulgadas + '"';
	}
}
