package modelo.Hardware;

@SuppressWarnings("serial")
public class TGrafica extends Hardware{

	String puerto;
	int velocidad;
	int memoria;

	public TGrafica() {
	}

	public TGrafica(String modelo, String marca, String puerto, int velocidad, int memoria) {
		super(modelo, marca);
		this.puerto = puerto;
		this.velocidad = velocidad;
		this.memoria = memoria;
	}
	
	public String toString(){
		return marca + " " + modelo + " \t" + memoria + "MB";
	}
}
