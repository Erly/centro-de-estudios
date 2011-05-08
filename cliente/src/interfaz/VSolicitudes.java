package interfaz;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import javax.swing.JTable;

import modelo.Main;
import modelo.Solicitud;

import java.awt.GridBagConstraints;
import java.util.Vector;

public class VSolicitudes extends JInternalFrame {

	/**
	 * Create the frame.
	 */
	public VSolicitudes() {
		setClosable(true);
		setBounds(100, 100, 600, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
	}

	public void setVisible(boolean b){
		super.setVisible(b);
		if(b){
			Main.centroEstudios.cargarSolicitudes();
			Vector<Solicitud> solicitudes = new Vector<Solicitud>();
			Vector<Solicitud> vSolicitudes = Main.centroEstudios.getSolicitudes();
			for(int i = 0; i < vSolicitudes.size(); i++){
				Solicitud solicitud = vSolicitudes.elementAt(i);
				if(solicitud.usuario.getNombre().equals(Main.usuario.getNombre())){
					solicitudes.addElement(solicitud);
				}
			}
			
			Object rowData[][] = new Object[solicitudes.size()][6];
			for(int i = 0; i < solicitudes.size(); i++){
				Solicitud sol = solicitudes.elementAt(i);
				rowData[i][0] = sol.getSoftware().nombre + " " + sol.getSoftware().getVersion();
				if(sol.aula == null){
					rowData[i][1] = sol.equipo.getCodAula();
					rowData[i][2] = sol.equipo.getCodEquipo();
				}else{
					rowData[i][1] = sol.aula.getCodAula();
					rowData[i][2] = "TODOS";
				}
				rowData[i][3] = sol.isRealizado();
				rowData[i][4] = sol.isExito();
				rowData[i][5] = sol.getObservaciones();
			}
			Object columnas[] = {"Software", "Aula", "Equipo", "Realizado", "Exito", "Observaciones"};
			
			JTable table = new JTable(rowData, columnas){
				public boolean isCellEditable(int rowIndex, int vColIndex){
					return false;
				}
			};
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scrollPane = new JScrollPane(table);
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 0;
			getContentPane().add(scrollPane, gbc_table);
		}
	}
}
