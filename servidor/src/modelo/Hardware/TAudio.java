package modelo.Hardware;

@SuppressWarnings("serial")
public class TAudio extends Hardware{

	float canales;
	
	public TAudio() {
	}

	public TAudio(String modelo, String marca, float canales) {
		super(modelo, marca);
		this.canales = canales;
	}
	
	public String toString(){
		if (modelo.toUpperCase().contains("INTEGRADA"))
			return "Integrada \t" + canales;
		return marca + " " + modelo + " \t" + canales;
	}
}
