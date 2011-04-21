package modelo;

import interfaz.VLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import modelo.Usuarios.Usuario;

public class Main {
	
	public static CentroEstudios centroEstudios = new CentroEstudios();
	public static Socket socket = new Socket();
	public static ObjectInputStream in;
	public static ObjectOutputStream out;
	public static Usuario usuario = null;

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
	
	public static Respuesta enviarPeticion(Peticion peticion) {
		try {
			Main.out.writeObject(peticion);
			Respuesta res = (Respuesta)Main.in.readObject();
			return res;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Imposible enviar o recivir información del servidor, asegurese de estar " +
					"correctamente conectado", "Error al conectar", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "El cliente esperaba un objeto de tipo Respuesta, en su lugar" +
					"ha recivido un objeto de otro tipo", "Error en la recepción", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	public static boolean Login(Usuario usuario){
		Respuesta res = enviarPeticion(new Peticion(usuario));
		if(res.exito){
			Main.usuario = res.usuario;
			JOptionPane.showMessageDialog(null, res.mensaje, "Login", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, res.mensaje, "Login", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static void Salir() throws IOException{
		Peticion pet = new Peticion(true);
		out.writeObject(pet);
		Main.out.close();
		Main.in.close();
		Main.socket.close();
	}
}