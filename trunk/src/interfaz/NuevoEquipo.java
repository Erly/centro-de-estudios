package interfaz;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import modelo.Main;
import modelo.Hardware.*;

import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class NuevoEquipo extends JInternalFrame {
	private JTextField txtAula;
	private JTextField txtCodEquipo;
	private JComboBox cmbPlacaBase;
	private JComboBox cmbCPU;
	private JComboBox cmbTSonido;
	private JComboBox cmbMonitor;
	private JList lstHDD;
	private JList lstHDD2;
	private JList lstRAM;
	private JList lstRAM2;
	private JList lstTGrafica;
	private JList lstTGrafica2;
	private JList lstTRed;
	private JList lstTRed2;
	Vector<PlacaBase> vpb = new Vector<PlacaBase>();
	Vector<CPU> vcpu = new Vector<CPU>();
	Vector<TAudio> vtso = new Vector<TAudio>();
	Vector<Monitor> vmo = new Vector<Monitor>();
	Vector<HDD> vhdd = new Vector<HDD>();
	Vector<RAM> vram = new Vector<RAM>();
	Vector<TGrafica> vtgraf = new Vector<TGrafica>();
	Vector<TRed> vtred = new Vector<TRed>();
	Vector<HDD> vhdd2 = new Vector<HDD>();
	Vector<RAM> vram2 = new Vector<RAM>();
	Vector<TGrafica> vtgraf2 = new Vector<TGrafica>();
	Vector<TRed> vtred2 = new Vector<TRed>();
	
	/**
	 * Create the frame.
	 */
	public NuevoEquipo() {
		setClosable(true);
		setTitle("Nuevo Equipo");
		setBounds(100, 100, 800, 450);
		getContentPane().setLayout(null);
		
		JLabel lblAula = new JLabel("Aula");
		lblAula.setBounds(258, 28, 25, 14);
		getContentPane().add(lblAula);
		
		JLabel lblCodequipo = new JLabel("Codigo Equipo");
		lblCodequipo.setBounds(379, 28, 82, 14);
		getContentPane().add(lblCodequipo);
		
		JLabel lblCpu = new JLabel("CPU");
		lblCpu.setBounds(444, 62, 26, 14);
		getContentPane().add(lblCpu);
		
		JLabel lblTSonido = new JLabel("T. Sonido");
		lblTSonido.setBounds(36, 102, 58, 14);
		getContentPane().add(lblTSonido);
		
		JLabel lblMonitor = new JLabel("Monitor");
		lblMonitor.setBounds(425, 102, 45, 14);
		getContentPane().add(lblMonitor);
		
		JLabel lblHdds = new JLabel("HDDs");
		lblHdds.setBounds(190, 131, 65, 14);
		getContentPane().add(lblHdds);
		
		txtAula = new JTextField();
		txtAula.setHorizontalAlignment(SwingConstants.TRAILING);
		txtAula.setEditable(false);
		txtAula.setEnabled(false);
		txtAula.setBounds(293, 24, 76, 22);
		getContentPane().add(txtAula);
		txtAula.setColumns(10);
		
		txtCodEquipo = new JTextField();
		txtCodEquipo.setHorizontalAlignment(SwingConstants.TRAILING);
		txtCodEquipo.setEnabled(false);
		txtCodEquipo.setEditable(false);
		txtCodEquipo.setColumns(10);
		txtCodEquipo.setBounds(473, 24, 76, 22);
		getContentPane().add(txtCodEquipo);
		
		cmbCPU = new JComboBox();
		cmbCPU.setBounds(480, 58, 268, 22);
		getContentPane().add(cmbCPU);
		
		cmbTSonido = new JComboBox();
		cmbTSonido.setBounds(104, 98, 269, 22);
		getContentPane().add(cmbTSonido);
		
		cmbMonitor = new JComboBox();
		cmbMonitor.setBounds(480, 98, 268, 22);
		getContentPane().add(cmbMonitor);
		
		JLabel lblRam = new JLabel("RAM");
		lblRam.setBounds(584, 131, 65, 14);
		getContentPane().add(lblRam);
		
		JLabel lblTGrafica = new JLabel("T. Grafica");
		lblTGrafica.setBounds(178, 255, 65, 14);
		getContentPane().add(lblTGrafica);
		
		JLabel lblTRed = new JLabel("T. Red");
		lblTRed.setBounds(584, 255, 65, 14);
		getContentPane().add(lblTRed);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 152, 159, 90);
		getContentPane().add(scrollPane);
		
		lstHDD = new JList();
		lstHDD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()){
					e.consume();
					vhdd2.addElement(vhdd.elementAt(lstHDD.getSelectedIndex()));
					lstHDD2.setListData(vhdd2);
				}
			}
		});
		scrollPane.setViewportView(lstHDD);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 152, 159, 90);
		getContentPane().add(scrollPane_1);
		
		lstHDD2 = new JList();
		scrollPane_1.setViewportView(lstHDD2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(434, 152, 159, 90);
		getContentPane().add(scrollPane_2);
		
		lstRAM = new JList();
		lstRAM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()){
					e.consume();
					vram2.addElement(vram.elementAt(lstRAM.getSelectedIndex()));
					lstRAM2.setListData(vram2);
				}
			}
		});
		scrollPane_2.setViewportView(lstRAM);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(603, 152, 159, 90);
		getContentPane().add(scrollPane_3);
		
		lstRAM2 = new JList();
		scrollPane_3.setViewportView(lstRAM2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(45, 280, 159, 90);
		getContentPane().add(scrollPane_4);
		
		lstTGrafica = new JList();
		lstTGrafica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()){
					e.consume();
					vtgraf2.addElement(vtgraf.elementAt(lstTGrafica.getSelectedIndex()));
					lstTGrafica2.setListData(vtgraf2);
				}
			}
		});
		scrollPane_4.setViewportView(lstTGrafica);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(214, 280, 159, 90);
		getContentPane().add(scrollPane_5);
		
		lstTGrafica2 = new JList();
		scrollPane_5.setViewportView(lstTGrafica2);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(434, 280, 159, 90);
		getContentPane().add(scrollPane_6);
		
		lstTRed = new JList();
		lstTRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()){
					e.consume();
					vtred2.addElement(vtred.elementAt(lstTRed.getSelectedIndex()));
					lstTRed2.setListData(vtred2);
				}
			}
		});
		scrollPane_6.setViewportView(lstTRed);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(603, 280, 159, 90);
		getContentPane().add(scrollPane_7);
		
		lstTRed2 = new JList();
		scrollPane_7.setViewportView(lstTRed2);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NuevoEquipo.this.dispose();
			}
		});
		btnCancelar.setBounds(408, 380, 91, 30);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cmbPlacaBase.getSelectedIndex() != 0 && cmbCPU.getSelectedIndex() != 0 && cmbTSonido.getSelectedIndex() != 0 && cmbMonitor.getSelectedIndex() != 0 
						&& lstHDD2.getLastVisibleIndex() != -1 && lstRAM2.getLastVisibleIndex() != -1 && lstTGrafica2.getLastVisibleIndex() != -1
						&& lstTRed2.getLastVisibleIndex() != -1){
					try {
						Main.db.insertarEquipo(Integer.parseInt(txtAula.getText()), Integer.parseInt(txtCodEquipo.getText()), vpb.elementAt(cmbPlacaBase.getSelectedIndex()-1), 
								vhdd2, vcpu.elementAt(cmbCPU.getSelectedIndex()-1), vram2, vtgraf2, vtso.elementAt(cmbTSonido.getSelectedIndex()-1), 
								vmo.elementAt(cmbMonitor.getSelectedIndex()-1), vtred2);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						JOptionPane.showMessageDialog(null, "Equipo creado con exito.", "Equipo creado", JOptionPane.INFORMATION_MESSAGE);
						Component[] componentes = NuevoEquipo.this.getParent().getComponents();
						for(int i = 0; i < componentes.length; i++){
							if(componentes[i].getClass() == VerAulas.class){
								VerAulas va = (VerAulas) componentes[i];
								va.cargarAulas();
							}
						}
						NuevoEquipo.this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Tienes que seleccionar al menos un componente de cada tipo");
				}
			}
		});
		btnAceptar.setBounds(279, 380, 91, 30);
		getContentPane().add(btnAceptar);
		
		JLabel lblPlacaBase = new JLabel("Placa Base");
		lblPlacaBase.setBounds(24, 62, 71, 14);
		getContentPane().add(lblPlacaBase);
		
		cmbPlacaBase = new JComboBox();
		cmbPlacaBase.setBounds(105, 58, 268, 22);
		getContentPane().add(cmbPlacaBase);

	}
	
	public void cargarCampos(int codClase, int codEquipo){
		try {
			resetearCombos();
			resetearListas();
			txtAula.setText("" + codClase);
			txtCodEquipo.setText("" + codEquipo);
			vpb = Main.db.obtenerPlacaBase();
			vcpu = Main.db.obtenerCPU();
			vtso = Main.db.obtenerTAudio();
			vmo = Main.db.obtenerMonitor();
			vhdd = Main.db.obtenerHDDs();
			vram = Main.db.obtenerRAM();
			vtgraf = Main.db.obtenerTGrafica();
			vtred = Main.db.obtenerTRed();
			
			cmbPlacaBase.addItem("Seleccione Placa Base");
			for(int i = 0; i < vpb.size(); i++){
				cmbPlacaBase.addItem(vpb.elementAt(i));
			}

			cmbCPU.addItem("Seleccione CPU");
			for(int i = 0; i < vcpu.size(); i++){
				cmbCPU.addItem(vcpu.elementAt(i));
			}

			cmbTSonido.addItem("Seleccione T. Sonido");
			for(int i = 0; i < vtso.size(); i++){
				cmbTSonido.addItem(vtso.elementAt(i));
			}

			cmbMonitor.addItem("Seleccione Monitor");
			for(int i = 0; i < vmo.size(); i++){
				cmbMonitor.addItem(vmo.elementAt(i));
			}
			lstHDD.setListData(vhdd);
			lstRAM.setListData(vram);
			lstTGrafica.setListData(vtgraf);
			lstTRed.setListData(vtred);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resetearCombos(){
		cmbPlacaBase.removeAllItems();
		cmbCPU.removeAllItems();
		cmbTSonido.removeAllItems();
		cmbMonitor.removeAllItems();
	}
	
	public void resetearListas(){
		lstHDD2.setListData(new Object[0]);
		lstRAM2.setListData(new Object[0]);
		lstTGrafica2.setListData(new Object[0]);
		lstTRed2.setListData(new Object[0]);
	}
}
