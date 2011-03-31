package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accionesMenu.Cerrar;
import accionesMenu.MostrarVentana;

import modelo.Main;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class VPrincipal extends JFrame {

	private JPanel contentPane;

	CrearAula crearAula;
	ModificarAula modificarAula;
	VerAulas verAulas;
	NuevoEquipo nuevoEquipo;
	VerEquipo verEquipo;
	
	AcercaDe acercaDe;

	/**
	 * Create the frame.
	 */
	public VPrincipal() {
		this.setExtendedState(VPrincipal.MAXIMIZED_BOTH);
		setName("VPrincipal");
		setBounds(100, 100, 800, 640);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		nuevoEquipo = new NuevoEquipo();
		contentPane.add(nuevoEquipo);
		
		verEquipo = new VerEquipo();
		verEquipo.setName("VerEquipo");
		verEquipo.setClosable(true);
		verEquipo.setBounds(0, 0, 500, 400);
		contentPane.add(verEquipo);
		
		crearAula = new CrearAula();
		contentPane.add(crearAula);
		
		modificarAula = new ModificarAula();
		contentPane.add(modificarAula);
		
		verAulas = new VerAulas();
		verAulas.setName("VerAula");
		verAulas.setMinimumSize(new Dimension(650, 350));
		verAulas.setPreferredSize(new Dimension(650, 350));
		verAulas.setClosable(true);
		verAulas.setIconifiable(true);
		verAulas.setMaximizable(true);
		verAulas.setResizable(true);
		verAulas.setBounds(222, 6, 650, 350);
		contentPane.add(verAulas);
		
		acercaDe = new AcercaDe();
		contentPane.add(acercaDe);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAdministracin = new JMenu("Administración");
		mnAdministracin.setVisible(false);
		menuBar.add(mnAdministracin);
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Crear Usuario");
		mnAdministracin.add(mntmCrearUsuario);
		
		JMenuItem mntmModificarUsuarios = new JMenuItem("Modificar Usuario");
		mnAdministracin.add(mntmModificarUsuarios);
		
		JMenuItem mntmBorrarUsuario = new JMenuItem("Borrar Usuario");
		mnAdministracin.add(mntmBorrarUsuario);
		
		JMenu mnAulas = new JMenu("Aulas");
		menuBar.add(mnAulas);
		
		MostrarVentana mvCrearAula = new MostrarVentana(crearAula);
		mvCrearAula.putValue(Action.NAME, "Crear Aula");
		JMenuItem mntmCrearAula = new JMenuItem(mvCrearAula);
		mnAulas.add(mntmCrearAula);

		MostrarVentana mvVerAula = new MostrarVentana(verAulas);
		mvVerAula.putValue(Action.NAME, "Ver Aulas");
		JMenuItem mntmVerAulas = new JMenuItem(mvVerAula);
		mnAulas.add(mntmVerAulas);
		
		Cerrar cerrar = new Cerrar(this);
		cerrar.putValue(Action.NAME, "Salir");
		JMenuItem mntmBorrarAula = new JMenuItem(cerrar);
		mnAulas.add(mntmBorrarAula);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		MostrarVentana mvAcercaDe = new MostrarVentana(acercaDe);
		mvAcercaDe.putValue(Action.NAME, "Acerca de...");
		JMenuItem mntmAcercaDe = new JMenuItem(mvAcercaDe);
		mnAyuda.add(mntmAcercaDe);
		contentPane.setLayout(null);
		
		addWindowListener(new Salir());
	}
	
	public class Salir extends WindowAdapter{  
        public void windowClosing( WindowEvent e ) {  
            int opcion = JOptionPane.showOptionDialog(  
                    VPrincipal.this,  
                    "Estas seguro de que quieres salir?",  
                    "Salir", JOptionPane.YES_NO_OPTION,  
                    JOptionPane.WARNING_MESSAGE, null, null,  
                    null );  
            if( opcion == JOptionPane.YES_OPTION ) {
            	try {
            		Main.Salir();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.exit(0);
            }  
        }
    }  
}