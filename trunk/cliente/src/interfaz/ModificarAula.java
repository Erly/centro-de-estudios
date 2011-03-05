package interfaz;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Aula;
import modelo.Main;

@SuppressWarnings("serial")
public class ModificarAula extends JInternalFrame {

	private JTextField txtCodigo;
	private JTextField txtCurso;
	
	/**
	 * Create the frame.
	 */
	public ModificarAula() {
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
				ModificarAula.this.dispose();
			}
		});
		btnCancelar.setBounds(338, 130, 90, 28);
		getContentPane().add(btnCancelar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!txtCurso.getText().isEmpty()){
					try {
						Main.db.actualizarAula(Integer.parseInt(txtCodigo.getText()), txtCurso.getText());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JOptionPane.showMessageDialog(null, "Aula Nº" + txtCodigo.getText() + " es ahora '" + txtCurso.getText() + "'.");
						Component[] componentes = ModificarAula.this.getParent().getComponents();
						for(int i = 0; i < componentes.length; i++){
							if(componentes[i].getClass() == VerAulas.class){
								VerAulas va = (VerAulas) componentes[i];
								va.cargarAulas();
							}
						}
						ModificarAula.this.dispose();
					}
				}
			}
		});
		btnModificar.setBounds(238, 130, 90, 28);
		getContentPane().add(btnModificar);
	}
	
	public void cargarCodigo(Aula aula){
		txtCodigo.setText("" + aula.getCodAula());
		txtCurso.setText(aula.getCurso());
		this.setTitle("Modificar " + txtCodigo.getText() + " - " + txtCurso.getText());
	}
}