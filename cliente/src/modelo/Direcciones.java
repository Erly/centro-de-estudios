package modelo;

public class Direcciones {
	private String ip;
	private int puerto;

	private Direcciones() {
		// TODO Auto-generated constructor stub
	}

	public Direcciones(String ip, int puerto) {
		this.setIp(ip);
		this.setPuerto(puerto);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public String toString(){
		return ip + ":" + puerto;
	}
}
