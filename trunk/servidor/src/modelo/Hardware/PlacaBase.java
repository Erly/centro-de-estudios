package modelo.Hardware;

@SuppressWarnings("serial")
public class PlacaBase extends Hardware{

	int pci;
	int agp;
	int pcie;
	int tipoDDR;
	int puertosDDR;

	public PlacaBase() {
	}

	public PlacaBase(String modelo, String marca, int pci, int agp, int pcie, int tipoDDR, int puertosDDR) {
		super(modelo, marca);
		this.pci = pci;
		this.agp = agp;
		this.pcie = pcie;
		this.tipoDDR = tipoDDR;
		this.puertosDDR = puertosDDR;
	}
	
	public String toString(){
		return marca + " " + modelo;
	}
}
