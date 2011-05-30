package interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;

import excepciones.ValorIncorrectoEx;

import modelo.Main;
import modelo.Solicitud;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class VAdminSolicitud extends JInternalFrame {
	private JTabbedPane tabbedPane;
	private JPanel solEquipos;
	private JPanel solAulas;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	
	Vector<Solicitud> vSolEquipos = new Vector<Solicitud>();
	Vector<Solicitud> vSolAulas = new Vector<Solicitud>();	
	Vector<Solicitud> vSolEquiposTodos = new Vector<Solicitud>();
	Vector<Solicitud> vSolAulasTodos = new Vector<Solicitud>();
	private JCheckBox chckbxMostrarLasSolicitudes;
	

	/**
	 * Create the frame.
	 */
	public VAdminSolicitud() {
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		solEquipos = new JPanel();
		tabbedPane.addTab("Equipos", null, solEquipos, null);
		solEquipos.setLayout(new BorderLayout(0, 0));
		
		solAulas = new JPanel();
		tabbedPane.addTab("Aulas", null, solAulas, null);
		solAulas.setLayout(new BorderLayout(0, 0));
		/*
		scrollPane = new JScrollPane();
		solEquipos.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		scrollPane2 = new JScrollPane();
		solAulas.add(scrollPane2, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane2.setViewportView(table_1);
		*/
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnRealizarSolicitud = new JButton("Realizar Solicitud");
		btnRealizarSolicitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxMostrarLasSolicitudes.isSelected()){
					lanzarError("Desmarca la casilla de ver todas para poder realizar una solicitud");
					return;
				}
				boolean exito;
				String observaciones;
				Solicitud sol;
				System.out.println("tabla: " + table.getSelectedRow() + "    tabla2: " + table_1.getSelectedRow());
				if(tabbedPane.getSelectedIndex() == 0){
					if(table.getSelectedRow() == -1){
						lanzarError("Tienes que seleccionar la solicitud a realizar");
						return;
					}
					sol = vSolEquipos.elementAt(table.getSelectedRow());
				} else {
					if(table_1.getSelectedRow() == -1){
						lanzarError("Tienes que seleccionar la solicitud a realizar");
						return;
					}
					sol = vSolAulas.elementAt(table_1.getSelectedRow());
				}
				int respuesta = JOptionPane.showConfirmDialog(null, "Ha sido posible realizar la solicitud?");
				if(respuesta == JOptionPane.YES_OPTION){
					exito = true;
				} else if(respuesta == JOptionPane.NO_OPTION){
					exito = false;
				} else {
					lanzarError("Acción cancelada");
					return;
				}
				observaciones = JOptionPane.showInputDialog("¿Ha habido algo que reseñar a la hora de realizar la solicitud?");
				sol.realizar(exito, observaciones);
				cargarTablas();
				int nSolicitudes = Main.centroEstudios.getSolicitudesPendientes();
				Main.lblUsuario.setText(Main.usuario.getNombre() + " (" + nSolicitudes + ")");
			}
		});
		
		chckbxMostrarLasSolicitudes = new JCheckBox("Mostrar las solicitudes realizadas");
		chckbxMostrarLasSolicitudes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTablas();
			}
		});
		panel.add(chckbxMostrarLasSolicitudes);
		panel.add(btnRealizarSolicitud);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b) cargarTablas();
	}

	private void cargarTablas(){
		vSolAulas = new Vector<Solicitud>(); vSolEquipos = new Vector<Solicitud>(); 
		vSolAulasTodos = new Vector<Solicitud>(); vSolEquiposTodos = new Vector<Solicitud>();
		Main.centroEstudios.cargarSolicitudes();
		Vector<Solicitud> vSolicitudes = Main.centroEstudios.getSolicitudes();
		for(int i = 0; i < vSolicitudes.size(); i++){
			Solicitud solicitud = vSolicitudes.elementAt(i);
			if(solicitud.aula == null){
				if(!solicitud.isRealizado()) vSolEquipos.addElement(solicitud);
				vSolEquiposTodos.addElement(solicitud);
			} else {
				if(!solicitud.isRealizado()) vSolAulas.addElement(solicitud);
				vSolAulasTodos.addElement(solicitud);
			}
		}
		
		Object columnas[] = {"Software", "Aula", "Equipo", "Realizado", "Exito", "Observaciones"};
		Object columnas2[] = {"Software", "Aula", "Realizado", "Exito", "Observaciones"};
		
		Object rowDataEq[][] = null;
		Object rowDataAu[][] = null;
		
		if(chckbxMostrarLasSolicitudes.isSelected()){
			rowDataEq = new Object[vSolEquiposTodos.size()][6];
			for(int i = 0; i < vSolEquiposTodos.size(); i++){
				Solicitud sol = vSolEquiposTodos.elementAt(i);
				rowDataEq[i][0] = sol.getSoftware().nombre + " " + sol.getSoftware().getVersion();
				rowDataEq[i][1] = sol.equipo.getCodAula();
				rowDataEq[i][2] = sol.equipo.getCodEquipo();
				rowDataEq[i][3] = sol.isRealizado();
				rowDataEq[i][4] = sol.isExito();
				rowDataEq[i][5] = sol.getObservaciones();
			}
			
			rowDataAu = new Object[vSolAulasTodos.size()][5];
			for(int i = 0; i < vSolAulasTodos.size(); i++){
				Solicitud sol = vSolAulasTodos.elementAt(i);
				rowDataAu[i][0] = sol.getSoftware().nombre + " " + sol.getSoftware().getVersion();
				rowDataAu[i][1] = sol.aula.getCodAula();
				rowDataAu[i][2] = sol.isRealizado();
				rowDataAu[i][3] = sol.isExito();
				rowDataAu[i][4] = sol.getObservaciones();
			}
		} else {
			rowDataEq = new Object[vSolEquipos.size()][6];
			for(int i = 0; i < vSolEquipos.size(); i++){
				Solicitud sol = vSolEquipos.elementAt(i);
				rowDataEq[i][0] = sol.getSoftware().nombre + " " + sol.getSoftware().getVersion();
				rowDataEq[i][1] = sol.equipo.getCodAula();
				rowDataEq[i][2] = sol.equipo.getCodEquipo();
				rowDataEq[i][3] = sol.isRealizado();
				rowDataEq[i][4] = sol.isExito();
				rowDataEq[i][5] = sol.getObservaciones();
			}
			
			rowDataAu = new Object[vSolAulas.size()][5];
			for(int i = 0; i < vSolAulas.size(); i++){
				Solicitud sol = vSolAulas.elementAt(i);
				rowDataAu[i][0] = sol.getSoftware().nombre + " " + sol.getSoftware().getVersion();
				rowDataAu[i][1] = sol.aula.getCodAula();
				rowDataAu[i][2] = sol.isRealizado();
				rowDataAu[i][3] = sol.isExito();
				rowDataAu[i][4] = sol.getObservaciones();
			}
		}
		
		table = new JTable(rowDataEq, columnas){
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(table);
		solEquipos.removeAll();
		solEquipos.add(scrollPane, BorderLayout.CENTER);
		
		
		table_1 = new JTable(rowDataAu, columnas2){
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane2 = new JScrollPane(table_1);
		solAulas.removeAll();
		solAulas.add(scrollPane2, BorderLayout.CENTER);
		
		tabbedPane.repaint();
	}
	
	private void lanzarError(String texto){
		try {
			Thread notif = new Thread(new BarraNotificadora(VAdminSolicitud.this, texto, 
					BarraNotificadora.ERROR_MESSAGE, 900));
			notif.start();
		} catch (ValorIncorrectoEx e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
