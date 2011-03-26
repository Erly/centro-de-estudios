package interfaz;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import modelo.Aula;
import modelo.Equipo;
import modelo.Main;
import modelo.Peticion;
import modelo.Respuesta;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class VerAulas extends JInternalFrame {

	Vector<Aula> aulas = new Vector<Aula>();
	JList lstAulas;
	JList lstEquipos;
	Aula aula;
	JCheckBox chkRecursivo;
	
	/**
	 * Create the frame.
	 */
	public VerAulas() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 650, 350);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblAulas = new JLabel("Aulas");
		lblAulas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAulas = new GridBagConstraints();
		gbc_lblAulas.anchor = GridBagConstraints.NORTH;
		gbc_lblAulas.insets = new Insets(0, 0, 5, 5);
		gbc_lblAulas.gridx = 0;
		gbc_lblAulas.gridy = 0;
		getContentPane().add(lblAulas, gbc_lblAulas);
		
		JLabel lblEquipos = new JLabel("Equipos");
		GridBagConstraints gbc_lblEquipos = new GridBagConstraints();
		gbc_lblEquipos.anchor = GridBagConstraints.NORTH;
		gbc_lblEquipos.insets = new Insets(0, 0, 5, 0);
		gbc_lblEquipos.gridx = 1;
		gbc_lblEquipos.gridy = 0;
		getContentPane().add(lblEquipos, gbc_lblEquipos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.weighty = 20.0;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.weighty = 20.0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		lstEquipos = new JList();
		scrollPane.setViewportView(lstEquipos);
		
		lstAulas = new JList();
		lstAulas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				aula = aulas.elementAt(lstAulas.getSelectedIndex());
				lstEquipos.setListData(aula.getEquipos());
			}
		});
		scrollPane_1.setViewportView(lstAulas);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		
		JButton btnBorrarAula = new JButton("Eliminar Aula");
		btnBorrarAula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(aula.getEquipos().isEmpty()){
					Main.centroEstudios.eliminarAula(aula);
					cargarAulas();
					aula = null;
				}else{
					if(chkRecursivo.isSelected()){
						Main.centroEstudios.eliminarAulaRecursivamente(aula);
						cargarAulas();
						aula = null;
					}else{
						JOptionPane.showMessageDialog(null, "Hay equipos en este aula por lo que no se puede eliminar sin eliminar los equipos.\n" +
								"Si deseas eliminar los equipos junto con el aula selecciona el borrado recursivo", "Imposible borrar", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JButton btnModificarAula = new JButton("Modificar Aula");
		btnModificarAula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Component[] componentes = VerAulas.this.getParent().getComponents();
				for(int i = 0; i < componentes.length; i++){
					if(componentes[i].getClass() == ModificarAula.class){
						ModificarAula ma = (ModificarAula) componentes[i];
						ma.cargarCodigo(aula);
						ma.setVisible(true);
					}
				}
			}
		});
		panel_1.add(btnModificarAula);
		panel_1.add(btnBorrarAula);
		btnBorrarAula.setHorizontalAlignment(SwingConstants.LEADING);
		
		chkRecursivo = new JCheckBox("Recursivo");
		panel_1.add(chkRecursivo);
		chkRecursivo.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.weightx = 1.0;
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		
		JButton btnNuevoEquipo = new JButton("Nuevo Equipo");
		btnNuevoEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component[] componentes = VerAulas.this.getParent().getComponents();
				for(int i = 0; i < componentes.length; i++){
					if(componentes[i].getClass() == NuevoEquipo.class){
						NuevoEquipo ne = (NuevoEquipo) componentes[i];
						if(!aula.getEquipos().isEmpty()){
							ne.cargarCampos(aula.getCodAula(), aula.getEquipos().lastElement().getCodEquipo() + 1);
						}else{
							ne.cargarCampos(aula.getCodAula(), 1);
						}
						ne.setVisible(true);
					}
				}
			}
		});
		panel.add(btnNuevoEquipo);
		
		JButton btnVerEquipo = new JButton("Ver Equipo");
		btnVerEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Component[] componentes = VerAulas.this.getParent().getComponents();
				for(int i = 0; i < componentes.length; i++){
					if(componentes[i].getClass() == VerEquipo.class){
						VerEquipo ve = (VerEquipo) componentes[i];
						Equipo e = aula.getEquipos().elementAt(lstEquipos.getSelectedIndex());
						
						e.cargarHardware();
						ve.setEquipo(e);
						ve.setVisible(true);
					}
				}
			}
		});
		panel.add(btnVerEquipo);
		
		JButton btnEliminarEquipo = new JButton("Eliminar Equipo");
		btnEliminarEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(lstEquipos.getSelectedIndex() != -1){
					Equipo eq = aula.getEquipos().elementAt(lstEquipos.getSelectedIndex());
					aula.borrarEquipo(eq);
				}
				cargarAulas();
			}
		});
		panel.add(btnEliminarEquipo);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b==true)
			cargarAulas();
	}
	
	public void cargarAulas(){
		Main.centroEstudios.cargarAulas();
		aulas = Main.centroEstudios.getAulas();
		lstAulas.setListData(aulas);
		lstEquipos.setListData(new Object[0]);
		if(lstAulas.getSelectedIndex() != -1){
			lstEquipos.setListData(aulas.elementAt(lstAulas.getSelectedIndex()).getEquipos());
		}
		chkRecursivo.setSelected(false);
	}
}
