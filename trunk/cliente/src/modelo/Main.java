package modelo;

import interfaz.VLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import datos.CentroDB;

public class Main {
	
	public static CentroDB db = new CentroDB();
	public static CentroEstudios centroEstudios = new CentroEstudios();
	public static Socket socket = new Socket();
	public static ObjectInputStream in;
	public static ObjectOutputStream out;

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		VLogin vlogin = new VLogin();
		vlogin.setVisible(true);
	}
	
	/**
	 * Borra los elementos del vector aulas del centro de estudios y los vuelve a cargar desde la base de datos. 
	 * Obteniendo la versión mas actualziada de estos.
	 */
	public static void recargarAulas(){
		Main.centroEstudios.getAulas().removeAllElements();
		try {
			Peticion pet = new Peticion(Peticion.AULAS);
			out.writeObject(pet);
			Respuesta res = (Respuesta)in.readObject();
			if (res.exito){
				centroEstudios.setAulas(res.resultado);
			}else{
				JOptionPane.showMessageDialog(null, res.mensaje, "Error al obtener las aulas", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
