package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Conexion extends Thread {

	Socket cliente;
	
	
	public Conexion() {
		// TODO Auto-generated constructor stub
	}
	
	public Conexion(Socket cliente){
		cliente= this.cliente;
	}

	public void run(){
		ObjectInputStream in = null;
		PrintWriter out = null;
		try {
			in = new ObjectInputStream(cliente.getInputStream());
			out = new PrintWriter(cliente.getOutputStream());
			Peticion peticion;
			while((peticion = (Peticion)in.readObject()) != null){
				// Tratamiento de la peticion
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
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
