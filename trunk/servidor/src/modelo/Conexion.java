package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Conexion extends Thread {

	Socket cliente = null;
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	
	public Conexion() {}
	
	public Conexion(Socket cliente){
		this.cliente = cliente;
	}

	public void run(){
		try {
			System.out.println("RUN");
			in = new ObjectInputStream(cliente.getInputStream());
			out = new ObjectOutputStream(cliente.getOutputStream());
			Peticion peticion;
			while(true){
				System.out.println("WHILE");
				// Tratamiento de la peticion
				peticion = (Peticion)in.readObject();
				Respuesta respuesta = peticion.tratar();
				if (respuesta.mensaje.equals("SALIR")){
					break;
				}
				out.writeObject(respuesta);
				System.out.println("FIN WHILE");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Finally");
				in.close();
				out.close();
				cliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
