package interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import modelo.Main;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CrearAula extends JInternalFrame {
	private JTextField txtCodigo;
	private JTextField txtCurso;

	/**
	 * Create the frame.
	 */
	public CrearAula() {
		setClosable(true);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(134, 38, 43, 14);
		getContentPane().add(lblCodigo);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(10, 85, 43, 14);
		getContentPane().add(lblCurso);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setBounds(187, 31, 68, 28);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtCurso = new JTextField();
		txtCurso.setBounds(63, 78, 365, 28);
		getContentPane().add(txtCurso);
		txtCurso.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CrearAula.this.dispose();
			}
		});
		btnCancelar.setBounds(338, 130, 90, 28);
		getContentPane().add(btnCancelar);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!txtCurso.getText().isEmpty()){
					try {
						Main.db.insertarAula(Integer.parseInt(txtCodigo.getText()), txtCurso.getText());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JOptionPane.showMessageDialog(null, "Aula Nº" + txtCodigo.getText() + " '" + txtCurso.getText() + "' creada satisfactoriamente.");
						CrearAula.this.dispose();
					}
				}
			}
		});
		btnCrear.setBounds(238, 130, 90, 28);
		getContentPane().add(btnCrear);
	}
	
	public void setVisible(boolean b){
		super.setVisible(b);
		if(b==true){
			cargarCodigo();
		}
	}
	
	public void cargarCodigo(){
		Main.recargarAulas();
		if(Main.centroEstudios.getAulas().isEmpty()){
			txtCodigo.setText("1");
		}else{
			txtCodigo.setText("" + (Main.centroEstudios.getAulas().lastElement().getCodAula() + 1));
		}
		txtCurso.setText("");
	}
}
