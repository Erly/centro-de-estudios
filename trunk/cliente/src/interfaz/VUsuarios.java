package interfaz;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Main;
import modelo.Usuarios.Usuario;

import java.awt.GridBagConstraints;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.ListSelectionModel;

public class VUsuarios extends JInternalFrame {
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VUsuarios() {
		setBounds(100, 100, 450, 300);
		setClosable(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Main.centroEstudios.cargarUsuarios();
		Hashtable<String, Usuario> usuarios = Main.centroEstudios.getUsuarios();
		
		Enumeration<Usuario> eUsuarios = usuarios.elements();
		Object rowData[][] = new Object[usuarios.size()][3];
		for(int i = 0; i < usuarios.size(); i++){
			Usuario usu = eUsuarios.nextElement();
			rowData[i][0] = usu.getNombre();
			rowData[i][1] = usu.getEmail();
			String tipo = usu.getClass().toString().substring(usu.getClass().toString().lastIndexOf(".") + 1);
			rowData[i][2] = tipo;
		}
		Object columnas[] = {"Nombre", "Email", "Tipo"};

		table = new JTable(rowData, columnas){
			public boolean isCellEditable(int rowIndex, int vColIndex){
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		getContentPane().add(scrollPane, gbc_table);

	}

}
