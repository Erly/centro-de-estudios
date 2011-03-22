package modelo;

import interfaz.VLogin;
import interfaz.VPrincipal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import javax.swing.UIManager;
import datos.AccesoBD;
import datos.CentroDB;

public class Main {
	
	public static CentroDB db = new CentroDB();
	public static CentroEstudios centroEstudios = new CentroEstudios();
	public static Socket socket;
	public static ObjectInputStream in;
	public static ObjectOutputStream out;

	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			/*db.abrirConexionSinODBC("//localhost/centrodb", AccesoBD.MYSQL, "root", "administrador");
		}catch(SQLException e) {
			System.out.println("No se ha podido establecer la conexión.");*/
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		VLogin vlogin = new VLogin();
		vlogin.setVisible(true);
		//VPrincipal vprincipal = new VPrincipal();
		//vprincipal.setVisible(true);
	}
	
	/**
	 * Borra los elementos del vector aulas del centro de estudios y los vuelve a cargar desde la base de datos. 
	 * Obteniendo la versión mas actualziada de estos.
	 */
	public static void recargarAulas(){
		Main.centroEstudios.getAulas().removeAllElements();
		try {
			Main.centroEstudios.setAulas(Main.db.obtenerAulas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
