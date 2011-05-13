package interfaz;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;

import excepciones.ValorIncorrectoEx;

import modelo.Aula;
import modelo.Equipo;
import modelo.Main;
import modelo.Software;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class VNuevaSolicitud extends JInternalFrame {

	private JTabbedPane tabbedPane;
	private JComboBox cmbAulas;
	private JComboBox cmbSoftware;
	private JList lstAulas;
	private JList lstAulasSel;
	private JList lstEquipos;
	private JList lstEquiposSel;
	private JButton btnCrearSolicitud;
	private JButton btnCancelar;
	private JEditorPane edtDescripcion;
	
	private Aula aula = new Aula();
	private Vector<Aula> vAulas = new Vector<Aula>();
	private Vector<Equipo> vEquipos = new Vector<Equipo>(); 
	
	
	/**
	 * Create the frame.
	 */
	public VNuevaSolicitud() {
		setClosable(true);
		setBounds(100, 100, 670, 370);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setAutoscrolls(true);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane.setBounds(223, 6, 429, 327);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Equipos", null, panel, null);
		panel.setLayout(null);
		
		cmbAulas = new JComboBox();
		cmbAulas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbAulas.getItemCount() != 0){
					aula = Main.centroEstudios.getAulas().elementAt(cmbAulas.getSelectedIndex());
					lstEquipos.setListData(aula.getEquipos());
				}
			}
		});
		cmbAulas.setBounds(16, 292, 187, 25);
		panel.add(cmbAulas);
		
		JLabel lblAula = new JLabel("Aula");
		lblAula.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblAula.setBounds(82, 268, 60, 15);
		panel.add(lblAula);
		
		JLabel lblEquipos = new JLabel("Equipos");
		lblEquipos.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblEquipos.setBounds(44, 6, 60, 15);
		panel.add(lblEquipos);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(16, 34, 126, 222);
		panel.add(scrollPane_2);
		
		lstEquipos = new JList();
		scrollPane_2.setViewportView(lstEquipos);
		
		JLabel lblEquiposSeleccionados = new JLabel("Equipos Seleccionados");
		lblEquiposSeleccionados.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblEquiposSeleccionados.setBounds(179, 6, 162, 15);
		panel.add(lblEquiposSeleccionados);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(215, 34, 126, 222);
		panel.add(scrollPane_3);
		
		lstEquiposSel = new JList();
		scrollPane_3.setViewportView(lstEquiposSel);
		
		JButton button = new JButton(">>");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lstEquipos.getSelectedIndex() != -1){
					Equipo equipo = aula.getEquipos().elementAt(lstEquipos.getSelectedIndex());
					for(int i = 0; i < vEquipos.size(); i++){
						if(equipo == vEquipos.elementAt(i)){
							lanzarError("Ese equipo ya está seleccionado");
							return;
						}
					}
					vEquipos.addElement(equipo);
					lstEquiposSel.setListData(vEquipos);
				}else{
					//TODO
				}
			}
		});
		button.setBounds(153, 114, 50, 27);
		panel.add(button);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lstEquiposSel.getSelectedIndex() != -1){
					vEquipos.removeElementAt(lstEquiposSel.getSelectedIndex());
					lstEquiposSel.setListData(vEquipos);
				}else{
					lanzarError("Debes seleccionar un equipo");
				}
			}
		});
		btnQuitar.setBounds(225, 262, 100, 27);
		panel.add(btnQuitar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Aula", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblAulas = new JLabel("Aulas");
		lblAulas.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblAulas.setBounds(53, 41, 42, 15);
		panel_1.add(lblAulas);
		
		JLabel lblAulasSeleccionadas = new JLabel("Aulas Seleccionadas");
		lblAulasSeleccionadas.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblAulasSeleccionadas.setBounds(185, 41, 145, 15);
		panel_1.add(lblAulasSeleccionadas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 72, 134, 193);
		panel_1.add(scrollPane);
		
		lstAulas = new JList();
		scrollPane.setViewportView(lstAulas);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(192, 72, 134, 193);
		panel_1.add(scrollPane_4);
		
		lstAulasSel = new JList();
		scrollPane_4.setViewportView(lstAulasSel);
		
		JButton button_1 = new JButton(">>");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lstAulas.getSelectedIndex() != -1){
					Aula aula = Main.centroEstudios.getAulas().elementAt(lstAulas.getSelectedIndex());
					for(int i = 0; i < vAulas.size(); i++){
						if(aula == vAulas.elementAt(i)){
							lanzarError("Esa aula ya está seleccionada");
							return;
						}
					}
					VNuevaSolicitud.this.vAulas.addElement(aula);
					lstAulasSel.setListData(vAulas);
				}else{
					lanzarError("Debes seleccionar un aula");
				}
			}
		});
		button_1.setBounds(140, 142, 50, 27);
		panel_1.add(button_1);
		
		JButton btnQuitar_1 = new JButton("Quitar");
		btnQuitar_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lstAulasSel.getSelectedIndex() != -1){
					vAulas.removeElementAt(lstAulasSel.getSelectedIndex());
					lstAulasSel.setListData(vAulas);
				}else{
					lanzarError("Debes seleccionar una aula");
				}
			}
		});
		btnQuitar_1.setBounds(210, 277, 100, 27);
		panel_1.add(btnQuitar_1);
		
		JLabel lblSoftware = new JLabel("Software");
		lblSoftware.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblSoftware.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoftware.setBounds(6, 6, 195, 15);
		getContentPane().add(lblSoftware);
		
		JLabel lblDescripcion = new JLabel("Descripcion* (max. 300 car.)");
		lblDescripcion.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(16, 70, 195, 15);
		getContentPane().add(lblDescripcion);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 97, 195, 101);
		getContentPane().add(scrollPane_1);
		
		edtDescripcion = new JEditorPane();
		edtDescripcion.setAutoscrolls(true);
		scrollPane_1.setViewportView(edtDescripcion);
		
		JLabel lblopcionalAportaDatos = new JLabel("<html><center>* = OPCIONAL. Aporta datos extra de la solicitud</center></html>");
		lblopcionalAportaDatos.setFont(new Font("DejaVu Sans", Font.ITALIC, 10));
		lblopcionalAportaDatos.setBounds(16, 202, 195, 32);
		getContentPane().add(lblopcionalAportaDatos);
		
		cmbSoftware = new JComboBox();
		cmbSoftware.setBounds(16, 33, 195, 25);
		getContentPane().add(cmbSoftware);
		
		btnCrearSolicitud = new JButton("Crear Solicitud");
		btnCrearSolicitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cmbSoftware.getSelectedIndex() == 0){
					lanzarError("Debes seleccionar un software para su instalacion");
					return;
				}
				Software software = Main.centroEstudios.getSoftware().elementAt(cmbSoftware.getSelectedIndex() - 1);
				String descripcion = edtDescripcion.getText();
				try{
					switch(tabbedPane.getSelectedIndex()){
					case 0:
						if(vEquipos.size() == 0){
							lanzarError("Debes seleccionar al menos un equipo en el que realizar la instalación");
							return;
						}
						Main.usuario.crearSolicitud(software, descripcion, vEquipos);
						break;
					case 1:
						if(vAulas.size() == 0){
							lanzarError("Debes seleccionar al menos una aula en la que realizar la instalación");
							return;
						}
						Main.usuario.crearSolicitud(software, descripcion, vAulas);
						break;
					}
					JOptionPane.showMessageDialog(null, "Solicitud de instalación creada con exito", "Solicitud creada!", JOptionPane.INFORMATION_MESSAGE);
					VNuevaSolicitud.this.dispose();
				}catch (ValorIncorrectoEx vie){
					vie.printStackTrace();
				}
			}
		});
		btnCrearSolicitud.setBounds(46, 246, 119, 27);
		getContentPane().add(btnCrearSolicitud);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VNuevaSolicitud.this.setVisible(false);
			}
		});
		btnCancelar.setBounds(56, 285, 100, 27);
		getContentPane().add(btnCancelar);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b){
			cargarDatos();
		}
	}
	
	private void cargarDatos(){
		vAulas = new Vector<Aula>();
		vEquipos = new Vector<Equipo>();
		cmbSoftware.removeAllItems();
		edtDescripcion.setText("");
		cmbAulas.removeAllItems();

		//lstEquiposSel.setListData(new Object[]{});
		//lstAulasSel.setListData(new Object[]{});
		lstEquiposSel.setListData(vEquipos);
		lstAulasSel.setListData(vAulas);
		Main.centroEstudios.cargarSoftware();
		cmbSoftware.addItem("---Seleccionar Software---");
		for(int i = 0; i < Main.centroEstudios.getSoftware().size(); i++){
			cmbSoftware.addItem(Main.centroEstudios.getSoftware().elementAt(i));
		}
		Main.centroEstudios.cargarAulas();
		lstAulas.setListData(Main.centroEstudios.getAulas());
		for(int i = 0; i < Main.centroEstudios.getAulas().size(); i++){
			cmbAulas.addItem(Main.centroEstudios.getAulas().elementAt(i));
		}
		aula = Main.centroEstudios.getAulas().elementAt(0);
	}
	
	private void lanzarError(String texto){
		try {
			Thread notif = new Thread(new BarraNotificadora(VNuevaSolicitud.this, texto, 
					BarraNotificadora.ERROR_MESSAGE, 500));
			notif.start();
		} catch (ValorIncorrectoEx e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
