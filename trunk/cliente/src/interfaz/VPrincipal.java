package interfaz;

import java.awt.Color;
import javax.swing.Action;
import javax.swing.ImageIcon;
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

import excepciones.ValorIncorrectoEx;

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
	VSoftware verSoftware;
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
				vp.setTitle("Centro de Estudios");
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
		
		lblUsuario = new JLabel(Main.usuario.getNombre());
		lblUsuario.setHorizontalTextPosition(SwingConstants.LEADING);
		lblUsuario.setIcon(new ImageIcon(VLogin.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
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
		Main.lblUsuario = lblUsuario;
		
		
		nuevaSolicitud = new VNuevaSolicitud();
		contentPane.add(nuevaSolicitud);
		
		verEquipo = new VerEquipo();
		verEquipo.setName("VerEquipo");
		verEquipo.setClosable(true);
		contentPane.add(verEquipo);
		
		verSolicitudes = new VSolicitudes();
		contentPane.add(verSolicitudes);
		
		verAulas = new VerAulas();
		verAulas.setName("VerAula");
		contentPane.add(verAulas);
		
		verSoftware = new VSoftware();
		verSoftware.setName("VerSoftware");
		contentPane.add(verSoftware);
		
		acercaDe = new AcercaDe();
		contentPane.add(acercaDe);
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		popupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAdministracion = new JMenu("Administración");
		menuBar.add(mnAdministracion);
		
		JMenu mnAulas = new JMenu("Aulas");
		menuBar.add(mnAulas);
		
		JMenu mnSoftware = new JMenu("Software");
		menuBar.add(mnSoftware);
		
		// Si el usuario es un técnico o administrador carga los menus y ventanas a los que un usuario normal no puede
		// acceder
		if(Main.usuario.getClass() == Tecnico.class || Main.usuario.getClass() == Administrador.class){
			
			int nSolicitudes = Main.centroEstudios.getSolicitudesPendientes();
			lblUsuario.setText(Main.usuario.getNombre() + " (" + nSolicitudes + ")");
			notificar(BarraNotificadora.INFORMATION_MESSAGE, "Tienes " + nSolicitudes + " solicitudes de instalacion pendientes");
			
			crearAula = new CrearAula();
			contentPane.add(crearAula);
			
			modificarAula = new ModificarAula();
			contentPane.add(modificarAula);
			
			nuevoEquipo = new NuevoEquipo();
			contentPane.add(nuevoEquipo);
			
			adminSolicitudes = new VAdminSolicitud();
			contentPane.add(adminSolicitudes);
			
			nuevoSoftware = new VNuevoSoft();
			contentPane.add(nuevoSoftware);
		
			MostrarVentana mvCrearAula = new MostrarVentana(crearAula);
			mvCrearAula.putValue(Action.NAME, "Crear Aula");
			JMenuItem mntmCrearAula = new JMenuItem(mvCrearAula);
			mnAulas.add(mntmCrearAula);
			
			MostrarVentana mvNuevoSoft = new MostrarVentana(nuevoSoftware);
			mvNuevoSoft.putValue(Action.NAME, "Agregar Software");
			JMenuItem mntmNuevoSoft = new JMenuItem(mvNuevoSoft);
			mnSoftware.add(mntmNuevoSoft);
			
			MostrarVentana mvAdminSol = new MostrarVentana(adminSolicitudes);
			mvAdminSol.putValue(Action.NAME, "Administrar Solicitudes");
			JMenuItem mntmAdminSolicitudes = new JMenuItem(mvAdminSol);
			popupMenu.add(mntmAdminSolicitudes);
			
			//Si el usuario es un administrador carga tambien los menus y ventanas a los que un técnico no podria acceder.
			if(Main.usuario.getClass() == Administrador.class){
				
				mnAdministracion.setVisible(true);
				
				nuevoUsuario = new VNuevoUsuario();
				contentPane.add(nuevoUsuario);
				
				verUsuarios = new VUsuarios();
				contentPane.add(verUsuarios);

				MostrarVentana mvCrearUsuario = new MostrarVentana(nuevoUsuario);
				mvCrearUsuario.putValue(Action.NAME, "Crear Usuario");
				JMenuItem mntmCrearUsuario = new JMenuItem(mvCrearUsuario);
				mnAdministracion.add(mntmCrearUsuario);

				MostrarVentana mvVerUsuarios = new MostrarVentana(verUsuarios);
				mvVerUsuarios.putValue(Action.NAME, "Ver Usuarios");
				JMenuItem mntmVerUsuarios = new JMenuItem(mvVerUsuarios);
				mnAdministracion.add(mntmVerUsuarios);
			}else{
				mnAdministracion.setVisible(false);
			}
		}
		
		//Objetos del menu de usuario
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
				
		//Objetos del menu principal
		MostrarVentana mvVerAula = new MostrarVentana(verAulas);
		mvVerAula.putValue(Action.NAME, "Ver Aulas");
		JMenuItem mntmVerAulas = new JMenuItem(mvVerAula);
		mnAulas.add(mntmVerAulas);
		
		MostrarVentana mvVerSoftware = new MostrarVentana(verSoftware);
		mvVerSoftware.putValue(Action.NAME, "Ver Software");
		JMenuItem mntmVerSoftware = new JMenuItem(mvVerSoftware);
		mnSoftware.add(mntmVerSoftware);
		
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
		
		cargarMenuUsuario();
		addWindowListener(new Salir());
		comprobarPermisos();
	}

	public void cargarMenuUsuario(){
		lblUsuario.setHorizontalAlignment(SwingConstants.LEADING);
		int ancho = (7 * lblUsuario.getText().length() + 16);
		lblUsuario.setBounds(this.getWidth() - ancho, this.getHeight() - 75, ancho, 15);
	}
	
	public void comprobarPermisos(){
		if(Main.usuario.getClass().toString().equals(Administrador.class.toString())){
			
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
	
	private void notificar(int tipo, String texto){
		try {
			Thread notif = new Thread(new BarraNotificadora(VPrincipal.this, texto, tipo, 1700));
			notif.start();
		} catch (ValorIncorrectoEx e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}