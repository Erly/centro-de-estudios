package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import excepciones.ValorIncorrectoEx;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.swing.BoxLayout;

import modelo.Direcciones;
import modelo.Main;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class VConectar extends JDialog {

	private boolean conectado = false;
	private final JPanel contentPanel = new JPanel();
	JFormattedTextField txtIP;
	JFormattedTextField txtPuerto;
	JList lstDirecciones;
	Vector<Direcciones> conexiones = new Vector<Direcciones>();

	/**
	 * Create the dialog.
	 */
	public VConectar() {
		setTitle("Conexiones");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{30, 139, 0};
		gbl_contentPanel.rowHeights = new int[]{30, 0, 0, 29, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 4.9E-324};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblConectar = new JLabel("Conectar");
			GridBagConstraints gbc_lblConectar = new GridBagConstraints();
			gbc_lblConectar.insets = new Insets(0, 0, 5, 5);
			gbc_lblConectar.gridx = 0;
			gbc_lblConectar.gridy = 0;
			gbc_lblConectar.gridwidth = 2;
			contentPanel.add(lblConectar, gbc_lblConectar);
		}
		{
			JLabel lblDirecciones = new JLabel("Direcciones");
			lblDirecciones.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblDirecciones = new GridBagConstraints();
			gbc_lblDirecciones.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblDirecciones.insets = new Insets(0, 0, 5, 0);
			gbc_lblDirecciones.gridx = 2;
			gbc_lblDirecciones.gridy = 0;
			contentPanel.add(lblDirecciones, gbc_lblDirecciones);
		}
		{
			JLabel lblIp = new JLabel("IP");
			GridBagConstraints gbc_lblIp = new GridBagConstraints();
			gbc_lblIp.insets = new Insets(0, 0, 5, 5);
			gbc_lblIp.gridx = 0;
			gbc_lblIp.gridy = 2;
			contentPanel.add(lblIp, gbc_lblIp);
		}
		{
			txtIP = new JFormattedTextField();
			GridBagConstraints gbc_txtIP = new GridBagConstraints();
			gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtIP.insets = new Insets(0, 0, 5, 5);
			gbc_txtIP.gridx = 1;
			gbc_txtIP.gridy = 2;
			contentPanel.add(txtIP, gbc_txtIP);
		}
		{
			lstDirecciones = new JList();
			GridBagConstraints gbc_lstDirecciones = new GridBagConstraints();
			gbc_lstDirecciones.insets = new Insets(0, 0, 5, 0);
			gbc_lstDirecciones.fill = GridBagConstraints.BOTH;
			gbc_lstDirecciones.gridx = 2;
			gbc_lstDirecciones.gridy = 1;
			gbc_lstDirecciones.gridheight = 5;
			contentPanel.add(lstDirecciones, gbc_lstDirecciones);
		}
		{
			JLabel lblPuerto = new JLabel("Puerto");
			GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
			gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
			gbc_lblPuerto.gridx = 0;
			gbc_lblPuerto.gridy = 3;
			contentPanel.add(lblPuerto, gbc_lblPuerto);
		}
		{
			txtPuerto = new JFormattedTextField();
			GridBagConstraints gbc_txtPuerto = new GridBagConstraints();
			gbc_txtPuerto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPuerto.insets = new Insets(0, 0, 5, 5);
			gbc_txtPuerto.gridx = 1;
			gbc_txtPuerto.gridy = 3;
			contentPanel.add(txtPuerto, gbc_txtPuerto);
		}
		{
			JButton btnAnadir = new JButton("Añadir");
			btnAnadir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(comprobarip(txtIP.getText())){
						try{
							int puerto = Integer.parseInt(txtPuerto.getText());
							if(puerto < 1 || puerto > 65535)
								throw new NumberFormatException();
							String ip = txtIP.getText();
							Direcciones direccion = new Direcciones(ip, puerto);
							conexiones.addElement(direccion);
							lstDirecciones.setListData(conexiones);
							guardarDirecciones();
						} catch (NumberFormatException nfe){
							try {
								Thread notif = new Thread(new BarraNotificadora(VConectar.this, "El puerto introducido es incorrecto",
										BarraNotificadora.ERROR_MESSAGE, 3000));
								notif.start();
							} catch (ValorIncorrectoEx vie) {
								// TODO Auto-generated catch block
								vie.printStackTrace();
							}
						}
					}
				}
			});
			GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
			gbc_btnAnadir.insets = new Insets(0, 0, 5, 5);
			gbc_btnAnadir.gridx = 1;
			gbc_btnAnadir.gridy = 4;
			contentPanel.add(btnAnadir, gbc_btnAnadir);
		}
		{
			JButton btnEliminar = new JButton("Eliminar");
			GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
			gbc_btnEliminar.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnEliminar.gridx = 2;
			gbc_btnEliminar.gridy = 6;
			contentPanel.add(btnEliminar, gbc_btnEliminar);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnConectar = new JButton("Conectar");
				btnConectar.setActionCommand("Conectar");
				btnConectar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(lstDirecciones.getSelectedIndex() != -1){
							Direcciones direccion = conexiones.elementAt(lstDirecciones.getSelectedIndex());
							try {
								SocketAddress sockAddr = new InetSocketAddress(direccion.getIp(), direccion.getPuerto());
								Main.socket.connect(sockAddr, 2000);
								Main.out = new ObjectOutputStream(Main.socket.getOutputStream());
								Main.in = new ObjectInputStream(Main.socket.getInputStream());
								conectado = true;
							} catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								//e1.printStackTrace();
							} catch (ConnectException ce){
								// TODO Auto-generated catch block
								//ce.printStackTrace();								
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} finally {
								VConectar.this.setVisible(false);
							}
						}
					}
				});
				buttonPane.add(btnConectar);
				getRootPane().setDefaultButton(btnConectar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						VConectar.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			cargarDirecciones();
		}
	}
	
	public boolean comprobarip(String ip){
		if(ip == null){
			return false;
		}
		String octetos[] = ip.split("\\.");
		if (octetos.length != 4){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		int uno,dos,tres,cuatro;
		try{
			uno = Integer.parseInt(octetos[0]);
			dos = Integer.parseInt(octetos[1]);
			tres = Integer.parseInt(octetos[2]);
			cuatro = Integer.parseInt(octetos[3]);
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException");
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(uno < 1 || uno > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(dos < 0 || dos > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(tres < 0 || tres > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		if(cuatro < 0 || cuatro > 255){
			//JOptionPane.showMessageDialog(null, "La IP introducida es incorrecta.", "IP incorrecta", JOptionPane.ERROR_MESSAGE);
			try {
				Thread notif = new Thread(new BarraNotificadora(VConectar.this, "La IP introducida es incorrecta",
						BarraNotificadora.ERROR_MESSAGE, 3000));
				notif.start();
			} catch (ValorIncorrectoEx vie) {
				// TODO Auto-generated catch block
				vie.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	public void cargarDirecciones(){
		File archivo = new File("./hosts.txt");
		try {
			conexiones.removeAllElements();
			if(!archivo.createNewFile()){
				BufferedReader br = new BufferedReader(new FileReader(archivo));
				String s;
				while((s = br.readLine()) != null){
					int pos = s.indexOf(":");
					String ip = s.substring(0, pos);
					int puerto = Integer.parseInt(s.substring(pos+1, s.length()));
					Direcciones direccion = new Direcciones(ip, puerto);
					conexiones.addElement(direccion);
				}
				lstDirecciones.setListData(conexiones);
			}
		} catch (FileNotFoundException e) {
			//No hay direcciones guardadas
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardarDirecciones(){
		File archivo = new File("./hosts.txt");
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
			for(int i=0; i < conexiones.size(); i++){
				pw.println(conexiones.elementAt(i).toString());
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getConectado(){
		return conectado;
	}
}