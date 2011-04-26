package modelo;

import java.io.Serializable;
import java.util.Vector;

import datos.Seriales;

import excepciones.ValorIncorrectoEx;
import modelo.Usuarios.Usuario;

public class Solicitud implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = Seriales.SOLICITUD;
	private int codigo;
	private Software software;
	public String descripcion;
	public Usuario usuario;
	public Aula aula;
	public Equipo equipo;
	private boolean exito;
	private boolean realizado;
	private String observaciones;

	public Solicitud() {
		// TODO Auto-generated constructor stub
	}

	public Solicitud(int codigo, Software software, String descripcion, Usuario usuario,
			Equipo equipo) throws ValorIncorrectoEx {
		this.setCodigo(codigo);
		setSoftware(software);
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.aula = null;
		this.equipo = equipo;
		setExito(false);
		setRealizado(false);
		setObservaciones("");
	}

	public Solicitud(int codigo, Software software, String descripcion, Usuario usuario,
			Aula aula) throws ValorIncorrectoEx {
		this.setCodigo(codigo);
		setSoftware(software);
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.aula = aula;
		this.equipo = null;
		setExito(false);
		setRealizado(false);
		setObservaciones("");
	}
	
	public Solicitud(int codigo, Software software, String descripcion, Usuario usuario,
			Equipo equipo, boolean exito, boolean realizado, String observacione) throws ValorIncorrectoEx {
		this(codigo, software, descripcion, usuario, equipo);
		if(realizado){
			realizar(realizado, observacione);
		}
	}
	
	public Solicitud(int codigo, Software software, String descripcion, Usuario usuario,
			Aula aula, boolean exito, boolean realizado, String observacione) throws ValorIncorrectoEx {
		this(codigo, software, descripcion, usuario, aula);
		if(realizado){
			realizar(realizado, observacione);
		}
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) throws ValorIncorrectoEx {
		if(codigo >= 1){
			this.codigo = codigo;
		} else {
			throw new ValorIncorrectoEx();
		}
	}

	private void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public boolean isRealizado() {
		return realizado;
	}

	private void setExito(boolean exito) {
		this.exito = exito;
	}

	public boolean isExito() {
		return exito;
	}

	private void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public Software getSoftware() {
		return software;
	}
	
	public void realizar(boolean exito, String observaciones){
		setRealizado(true);
		setExito(exito);
		setObservaciones(observaciones);
	}
	
	public static int getMaxCod(){
		Respuesta res;
		try {
			res = Main.enviarPeticion(new Peticion(Peticion.SOLICITUDES));
			Vector<Solicitud> vSolicitudes = res.resultado;
			if(vSolicitudes.isEmpty()){
				return 1;
			}else{
				return vSolicitudes.lastElement().getCodigo() + 1;
			}
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}