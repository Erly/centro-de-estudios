package interfaz;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JTable;

import modelo.Main;
import modelo.Software;

public class VSoftware extends JInternalFrame {
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VSoftware() {
		setClosable(true);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		

	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b){
			Main.centroEstudios.cargarSoftware();
			Vector<Software> vSoftware = Main.centroEstudios.getSoftware();
			
			Object rowData[][] = new Object[vSoftware.size()][6];
			for(int i = 0; i < vSoftware.size(); i++){
				Software soft = vSoftware.elementAt(i);
				rowData[i][0] = soft.getCodigo();
				rowData[i][1] = soft.nombre;
				rowData[i][2] = soft.getCategoria();
				rowData[i][3] = soft.getVersion();
				rowData[i][4] = soft.desarrollador;
				rowData[i][5] = soft.licencia;
			}
			Object columnas[] = {"Codigo", "Nombre", "Categoria", "Version", "Desarrollador", "Licencia"};
			
			table = new JTable(rowData, columnas){
				public boolean isCellEditable(int rowIndex, int vColIndex){
					return false;
				}
			};
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(table);
			
			GridBagConstraints gbc_table = new GridBagConstraints();
			gbc_table.gridwidth = 5;
			gbc_table.insets = new Insets(0, 0, 5, 5);
			gbc_table.fill = GridBagConstraints.BOTH;
			gbc_table.gridx = 0;
			gbc_table.gridy = 1;
			getContentPane().add(scrollPane, gbc_table);
			
			/*JButton btnModificarPrograma = new JButton("Modificar Programa");
			GridBagConstraints gbc_btnModificarPrograma = new GridBagConstraints();
			gbc_btnModificarPrograma.insets = new Insets(0, 0, 0, 5);
			gbc_btnModificarPrograma.gridx = 2;
			gbc_btnModificarPrograma.gridy = 2;
			getContentPane().add(btnModificarPrograma, gbc_btnModificarPrograma);
			
			JButton btnEliminarPrograma = new JButton("Eliminar programa");
			GridBagConstraints gbc_btnEliminarPrograma = new GridBagConstraints();
			gbc_btnEliminarPrograma.gridx = 4;
			gbc_btnEliminarPrograma.gridy = 2;
			getContentPane().add(btnEliminarPrograma, gbc_btnEliminarPrograma);
*/
		}
	}

}
