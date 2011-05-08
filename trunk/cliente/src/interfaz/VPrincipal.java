package interfaz;

import java.awt.Color;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accionesMenu.Cerrar;
import accionesMenu.CerrarSesion;
import accionesMenu.MostrarVentana;

import modelo.Main;
import modelo.Usuarios.Administrador;
import modelo.Usuarios.Tecnico;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.UIManager;
import java.awt.ComponentOrientation;

@SuppressWarnings("serial")
public class VPrincipal extends JFrame {

	private int x = 0;
	private int y = 0;
	
	private JPanel contentPane;

	VNuevoUsuario nuevoUsuario;
	VUsuarios verUsuarios;
	CrearAula crearAula;
	ModificarAula modificarAula;
	VerAulas verAulas;
	NuevoEquipo nuevoEquipo;
	VerEquipo verEquipo;
	VNuevoSoft nuevoSoftware;
	VNuevaSolicitud nuevaSolicitud;
	VSolicitudes verSolicitudes;
	VAdminSolicitud adminSolicitudes;
	
	AcercaDe acercaDe;
	
	private JMenu mnAdministracion;
	private JLabel lblUsuario;

	/**
	 * Create the frame.
	 */
	public VPrincipal() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				cargarMenuUsuario();
				VPrincipal vp = VPrincipal.this;
				if(vp.getExtendedState() == VPrincipal.MAXIMIZED_BOTH){
					x = vp.getX();
					y = vp.getY();
					vp.setLocation(0, 0);
				}else if(x != 0 || y != 0){
					vp.setLocation(x, y);
				}
			}
		});
		this.setExtendedState(VPrincipal.MAXIMIZED_BOTH);
		setName("VPrincipal");
		setBounds(100, 100, 800, 640);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		nuevoEquipo = new NuevoEquipo();
		contentPane.add(nuevoEquipo);
		
		nuevoUsuario = new VNuevoUsuario();
		contentPane.add(nuevoUsuario);
		
		nuevoSoftware = new VNuevoSoft();
		contentPane.add(nuevoSoftware);
		
		nuevaSolicitud = new VNuevaSolicitud();
		contentPane.add(nuevaSolicitud);
		
		verUsuarios = new VUsuarios();
		contentPane.add(verUsuarios);
		
		verEquipo = new VerEquipo();
		verEquipo.setName("VerEquipo");
		verEquipo.setClosable(true);
		verEquipo.setBounds(0, 0, 500, 400);
		contentPane.add(verEquipo);
		
		verSolicitudes = new VSolicitudes();
		contentPane.add(verSolicitudes);
		
		crearAula = new CrearAula();
		contentPane.add(crearAula);
		
		modificarAula = new ModificarAula();
		contentPane.add(modificarAula);
		
		verAulas = new VerAulas();
		verAulas.setName("VerAula");
		contentPane.add(verAulas);
		
		acercaDe = new AcercaDe();
		contentPane.add(acercaDe);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnAdministracion = new JMenu("Administración");
		menuBar.add(mnAdministracion);
		
		MostrarVentana mvCrearUsuario = new MostrarVentana(nuevoUsuario);
		mvCrearUsuario.putValue(Action.NAME, "Crear Usuario");
		JMenuItem mntmCrearUsuario = new JMenuItem(mvCrearUsuario);
		mnAdministracion.add(mntmCrearUsuario);

		MostrarVentana mvVerUsuarios = new MostrarVentana(verUsuarios);
		mvVerUsuarios.putValue(Action.NAME, "Ver Usuarios");
		JMenuItem mntmVerUsuarios = new JMenuItem(mvVerUsuarios);
		mnAdministracion.add(mntmVerUsuarios);
		
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
		
		lblUsuario = new JLabel(Main.usuario.getNombre() + " ^");
		lblUsuario.setOpaque(true);
		lblUsuario.setBackground(UIManager.getColor("Label.background"));
		lblUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//lblUsuario.setForeground(Color.GREEN);
				lblUsuario.setBackground(new Color(132, 164, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//lblUsuario.setForeground(Color.BLUE);
				lblUsuario.setBackground(UIManager.getColor("Label.background"));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblUsuario.setForeground(Color.BLUE);
		contentPane.add(lblUsuario);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		popupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		if(Main.usuario.getClass().toString().equals(Tecnico.class.toString()) || 
				Main.usuario.getClass().toString().equals(Administrador.class.toString())){
			adminSolicitudes = new VAdminSolicitud();
			contentPane.add(adminSolicitudes);
			
			MostrarVentana mvAdminSol = new MostrarVentana(adminSolicitudes);
			mvAdminSol.putValue(Action.NAME, "Administrar Solicitudes");
			JMenuItem mntmAdminSolicitudes = new JMenuItem(mvAdminSol);
			popupMenu.add(mntmAdminSolicitudes);
		}
		
		MostrarVentana mvSolicitarIns = new MostrarVentana(nuevaSolicitud);
		mvSolicitarIns.putValue(Action.NAME, "Solicitar Instalacion");
		JMenuItem mntmSolicitarIns = new JMenuItem(mvSolicitarIns);
		popupMenu.add(mntmSolicitarIns);
		
		MostrarVentana mvVerSolicitudes = new MostrarVentana(verSolicitudes);
		mvVerSolicitudes.putValue(Action.NAME, "Ver mis Solicitudes");
		JMenuItem mntmVerSolicitudes = new JMenuItem(mvVerSolicitudes);
		popupMenu.add(mntmVerSolicitudes);
		
		CerrarSesion cerrarSesion = new CerrarSesion(this);
		cerrarSesion.putValue(Action.NAME, "Cerrar Sesion");
		JMenuItem mntmCerrarSesin = new JMenuItem(cerrarSesion);
		popupMenu.add(mntmCerrarSesin);
		
		addPopup(lblUsuario, popupMenu);
		
		cargarMenuUsuario();
		addWindowListener(new Salir());
		comprobarPermisos();
	}

	public void cargarMenuUsuario(){
		lblUsuario.setHorizontalAlignment(SwingConstants.LEADING);
		int ancho = (7 * lblUsuario.getText().length() + 2);
		lblUsuario.setBounds(this.getWidth() - ancho, this.getHeight() - 75, ancho, 15);
	}
	
	public void comprobarPermisos(){
		if(Main.usuario.getClass().toString().equals(Administrador.class.toString())){
			mnAdministracion.setVisible(true);
		}else{
			mnAdministracion.setVisible(false);
		}
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showMenu(e);
			}
			private void showMenu(MouseEvent e) {
				if(popup.getWidth() == 0){
					//Se ejecutara solo la primera vez que se haga click para que el ancho y alto del menu no sean 0 
					//al hacer la llamada de abajo y asi el menu se muestre donde debe. 
					popup.show(null, 0, 0);
				}
				popup.show(e.getComponent(), e.getComponent().getWidth() -1 - popup.getWidth(), -1 - popup.getHeight());
			}
		});
	}
}