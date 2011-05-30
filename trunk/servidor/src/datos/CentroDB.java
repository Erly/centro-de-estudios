package datos;

import modelo.Hardware.*;
import modelo.Usuarios.*;
import modelo.Aula;
import modelo.Equipo;
import modelo.Software;
import modelo.Solicitud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import excepciones.ValorIncorrectoEx;

// TODO: Auto-generated Javadoc
/**
 * The Class CentroDB.
 */
public class CentroDB extends AccesoBD {

	/**
	 * Instantiates a new centro db.
	 */
	public CentroDB() {
	}

	/**
	 * Login.
	 *
	 * @param usuario the usuario
	 * @return the string
	 * @throws SQLException the sQL exception
	 */
	public String login(Usuario usuario) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return "Imposible realizar consulta; CONEXION BD NO ESTABLECIDA";
		}
		ResultSet rs_login = lanzarSelect("Select * from usuarios where NomUsu='" + usuario.getNombre() + "'");
		while(rs_login.next()){
			//Usuario usu = new Usuario(rs_login.getString("NomUsu") ,rs_login.getString("Pass"), rs_login.getString("Email"), 
			//		rs_login.getString("Tipo");
			String tipo = rs_login.getString("Tipo");
			if(usuario.getPass().equals(rs_login.getString("Pass"))){
				/*if(tipo.equals("Administrador")){
					usuario = new Administrador(rs_login.getString("NomUsu"), rs_login.getString("Pass"), rs_login.getString("Email"));
				}else if(tipo.equals("Tecnico")){
					usuario = new Tecnico(rs_login.getString("NomUsu"), rs_login.getString("Pass"), rs_login.getString("Email"));
				}else{
					usuario = new Usuario(rs_login.getString("NomUsu"), rs_login.getString("Pass"), rs_login.getString("Email"));
				}*/
				usuario.setEmail(rs_login.getString("Email"));
				return tipo;
			}else{
				return "La contraseña introducida es incorrecta.";
			}
		}
		return "El usuario introducido no existe";
	}
	
	/**
	 * Actualizar aula.
	 *
	 * @param aula the aula
	 * @throws SQLException the sQL exception
	 */
	public void actualizarAula(Aula aula) throws SQLException{
		actualizarAula(aula.getCodAula(), aula.getCurso());
	}
	
	/**
	 * Actualizar aula.
	 *
	 * @param codAula the cod aula
	 * @param curso the curso
	 * @throws SQLException the sQL exception
	 */
	public void actualizarAula(int codAula, String curso) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("aulas", "Curso='" + curso + "'", "CodAula=" + codAula);
	}
	
	/**
	 * Actualizar equipo.
	 *
	 * @param equipo the equipo
	 * @throws SQLException the sQL exception
	 */
	public void actualizarEquipo(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("equipos", "CodEquipo=" + equipo.getCodEquipo(), "CodAula=" + equipo.getCodAula());
	}
	
	public void actualizarUsuario(Usuario usuario) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("usuarios", "Pass='" + usuario.getPass() + "', Email='" + usuario.getEmail() + "'", 
				"NomUsu='" + usuario.getNombre() + "'");
	}
	
	public void actualizarSoftware(Software software) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("software", "Categoria='" + software.getCategoria() + "', Desarrollador='" + software.desarrollador + 
				"', Version='" + software.getVersion() + "'", "CodSoft=" + software.getCodigo());
	}
	
	public void actualizarSolicitudes(Solicitud solicitud) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("solicitudes", "Realizado=" + solicitud.isRealizado() + ", Exito=" + solicitud.isExito() 
				+ ", Observaciones='" + solicitud.getObservaciones() + "'",	"CodSolicitud=" + solicitud.getCodigo());
	}
	
	/**
	 * Borrar aula.
	 *
	 * @param aula the aula
	 * @throws SQLException the sQL exception
	 */
	public void borrarAula(Aula aula) throws SQLException{
		borrarAula(aula.getCodAula());
	}
	
	/**
	 * Borrar aula.
	 *
	 * @param codAula the cod aula
	 * @throws SQLException the sQL exception
	 */
	public void borrarAula(int codAula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarDelete("aulas", "CodAula=" + codAula);
	}
	
	public void borrarSolicitud(Solicitud solicitud) throws SQLException{
		borrarSolicitud(solicitud.getCodigo());
	}
	
	public void borrarSolicitud(int codSolicitud) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarDelete("solicitudes", "CodSolicitud=" + codSolicitud);
	}
	
	/**
	 * Borrar equipo.
	 *
	 * @param equipo the equipo
	 * @throws SQLException the sQL exception
	 */
	public void borrarEquipo(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarDelete("eqtr", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqtg", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqram", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqhdd", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		
		this.lanzarDelete("equipos", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
	}
	
	public void borrarUsuario(Usuario usuario) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("usuarios", "Activo=0", "NomUsu='" + usuario.getNombre() + "'");
	}
	
	/**
	 * Insertar aula.
	 *
	 * @param aula the aula
	 * @throws SQLException the sQL exception
	 */
	public void insertarAula(Aula aula) throws SQLException{
		insertarAula(aula.getCodAula(), aula.getCurso());
	}
	
	/**
	 * Insertar aula.
	 *
	 * @param codAula the cod aula
	 * @param curso the curso
	 * @throws SQLException the sQL exception
	 */
	public void insertarAula(int codAula, String curso) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("aulas", "CodAula, Curso", codAula + ", '" + curso + "'");
	}
	
	public void insertarUsuario(Usuario usuario) throws SQLException{
		String tipo;
		if(usuario.getClass().toString().equals(Administrador.class.toString())){
			tipo = "Administrador";
		}else if(usuario.getClass().toString().equals(Tecnico.class.toString())){
			tipo = "Tecnico";
		}else{
			tipo = "Usuario";
		}
		insertarUsuario(usuario.getNombre(), usuario.getPass(), usuario.getEmail(), tipo);
	}
	
	public void insertarUsuario(String nombre, String pass, String email, String tipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("usuarios", "NomUsu, Pass, Email, Tipo", "'" + nombre + "', '" + pass + "', '" + email + "', '" + 
				tipo + "'");
	}
	
	public void insertarSolicitud(Solicitud solicitud) throws SQLException{
		if(solicitud.aula == null){
			insertarSolicitud(solicitud.getCodigo(), solicitud.getSoftware(), solicitud.descripcion, solicitud.usuario, solicitud.equipo);
		}else{
			insertarSolicitud(solicitud.getCodigo(), solicitud.getSoftware(), solicitud.descripcion, solicitud.usuario, solicitud.aula);
		}
	}

	public void insertarSolicitud(int codSolicitud, Software software, String descripcion, Usuario usuario, Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("solicitudes", "CodSolicitud, CodSoft, Descripcion, NomUsu, CodAula, CodEquipo", codSolicitud + ", " 
				+ software.getCodigo() + ", '"+ descripcion + "', '" + usuario.getNombre() + "', " + equipo.getCodAula() + ", " + equipo.getCodEquipo());
	}

	public void insertarSolicitud(int codSolicitud, Software software, String descripcion, Usuario usuario, Aula aula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("solicitudes", "CodSolicitud, CodSoft, Descripcion, NomUsu, CodAula, CodEquipo", codSolicitud + ", " 
				+ software.getCodigo() + ", '"+ descripcion + "', '" + usuario.getNombre() + "', " + aula.getCodAula() + ", 0");
	}
	
	public void insertarSoftware(Software soft) throws SQLException{
		insertarSoftware(soft.getCodigo(), soft.nombre, soft.getCategoria(), soft.licencia, soft.desarrollador, soft.getVersion());
	}
	
	public void insertarSoftware(int codSoft, String nombre, String categoria, String licencia, String desarrollador, float version) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("software", "CodSoft, Nombre, Categoria, Licencia, Desarrollador, Version", codSoft + ", '" 
				+ nombre + "', '" + categoria + "', '" + licencia + "', '" + desarrollador + "', " + version);
	}
	
	/**
	 * Insertar equipo.
	 *
	 * @param equipo the equipo
	 * @throws SQLException the sQL exception
	 */
	public void insertarEquipo(Equipo equipo) throws SQLException{
		insertarEquipo(equipo.getCodAula(), equipo.getCodEquipo(), equipo.getPlacaBase(), equipo.getHDDs(), equipo.getCpu(), equipo.getRam(), 
				equipo.gettGraficas(), equipo.gettAudio(), equipo.getMonitor(), equipo.gettRed());
	}
	
	/**
	 * Insertar equipo.
	 *
	 * @param codAula the cod aula
	 * @param codEquipo the cod equipo
	 * @param pb the pb
	 * @param vhdd the vhdd
	 * @param cpu the cpu
	 * @param vram the vram
	 * @param vtgraf the vtgraf
	 * @param tso the tso
	 * @param mon the mon
	 * @param vtred the vtred
	 * @throws SQLException the sQL exception
	 */
	public void insertarEquipo(int codAula, int codEquipo, PlacaBase pb, Vector<HDD> vhdd, CPU cpu, Vector<RAM> vram, Vector<TGrafica> vtgraf, 
			TAudio tso, Monitor mon, Vector<TRed> vtred) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("equipos", "CodEquipo, CodAula, ModeloPB, ModeloCPU, ModeloTA, ModeloMonitor", codEquipo + ", " + codAula + ", '" + pb.getModelo() + "', '" + 
				cpu.getModelo() + "', '" + tso.getModelo() + "', '" + mon.getModelo() + "'");
		for(int i = 0; i < vhdd.size(); i++){
			this.lanzarInsert("eqhdd", "Codigo, codEquipo, codAula, ModeloHDD", this.obtenerMaxCod("eqhdd") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vhdd.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vram.size(); i++){
			this.lanzarInsert("eqram", "Codigo, codEquipo, codAula, ModeloRAM", this.obtenerMaxCod("eqram") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vram.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vtgraf.size(); i++){
			this.lanzarInsert("eqtg", "Codigo, codEquipo, codAula, ModeloTG", this.obtenerMaxCod("eqtg") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vtgraf.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vtred.size(); i++){
			this.lanzarInsert("eqtr", "Codigo, codEquipo, codAula, ModeloTR", this.obtenerMaxCod("eqtr") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vtred.elementAt(i).getModelo() + "'");
		}
	}
	
	/**
	 * Obtener aulas.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<Aula> obtenerAulas() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Aula> aulas = new Vector<Aula>();
		ResultSet rs_aulas = this.lanzarSelect("select * from aulas ORDER BY CodAula");
		while(rs_aulas.next()){
			try{
				Aula a = new Aula(rs_aulas.getInt("CodAula"), rs_aulas.getString("Curso"));
				a.setEquipos(obtenerEquipos(a));
				aulas.addElement(a);
			}catch(Exception ex1){
				System.out.println("Ha ocurrido un error al recuperar algun equipo del aula " + rs_aulas.getInt("CodAula"));
			}
		}
		return aulas;
	}
	
	public Aula obtenerAula(int codAula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		ResultSet rs_aula = this.lanzarSelect("SELECT * FROM aulas WHERE CodAula=" + codAula);
		while(rs_aula.next()){
			Aula aula = new Aula(rs_aula.getInt("CodAula"), rs_aula.getString("Curso"));
			aula.setEquipos(obtenerEquipos(aula));
			return aula;
		}
		return null;
	}
	
	/**
	 * Obtener cpu.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<CPU> obtenerCPU() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<CPU> CPUs = new Vector<CPU>();
		ResultSet rs_cpu = this.lanzarSelect("select * from cpu");
		while(rs_cpu.next()){
			try{
				CPU cpu = new CPU(rs_cpu.getString("ModeloCPU") ,rs_cpu.getString("Marca"), rs_cpu.getInt("Nucleos"), rs_cpu.getFloat("Velocidad"), 
						rs_cpu.getInt("Cache"));
				CPUs.addElement(cpu);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return CPUs;
	}
	
	/**
	 * Obtener cpu.
	 *
	 * @param equipo the equipo
	 * @return the cPU
	 * @throws SQLException the sQL exception
	 */
	public CPU obtenerCPU(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		CPU cpu = null;
		ResultSet rs_cpu = this.lanzarSelect("select * from cpu where ModeloCPU = (select ModeloCPU from equipos where CodEquipo = " 
				+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula() + ")");
		while(rs_cpu.next()){
			try{
				cpu = new CPU(rs_cpu.getString("ModeloCPU") ,rs_cpu.getString("Marca"), rs_cpu.getInt("Nucleos"), rs_cpu.getFloat("Velocidad"), 
						rs_cpu.getInt("Cache"));
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return cpu;
	}
	
	/**
	 * Obtener equipos.
	 *
	 * @param aula the aula
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<Equipo> obtenerEquipos(Aula aula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Equipo> equipos = new Vector<Equipo>();
		ResultSet rs_eq = this.lanzarSelect("select CodEquipo from equipos where CodAula = " + aula.getCodAula());
		while(rs_eq.next()){
			try{
				Equipo e = new Equipo(aula.getCodAula(), rs_eq.getInt(1));
				e.cargarHardware();
				/*e.setPlacaBase(obtenerPlacaBase(e, aula));
				e.setHDDs(obtenerHDDs(e, aula));
				e.setCpu(obtenerCPU(e, aula));
				e.setRam(obtenerRAM(e, aula));
				e.settGraficas(obtenerTGrafica(e, aula));
				e.settAudio(obtenerTAudio(e, aula));
				e.setMonitor(obtenerMonitor(e, aula));
				e.settRed(obtenerTRed(e, aula));*/
				equipos.addElement(e);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return equipos;
	}
	
	/**
	 * Obtener hd ds.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<HDD> obtenerHDDs() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<HDD> HDDs = new Vector<HDD>();
		ResultSet rs_hdd = this.lanzarSelect("select * from hdd");
		while(rs_hdd.next()){
			try{
				HDD hdd = new HDD(rs_hdd.getString("ModeloHDD"), rs_hdd.getString("Marca"), rs_hdd.getInt("Capacidad"), rs_hdd.getInt("RPM"));
				HDDs.addElement(hdd);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return HDDs;
	}
	
	/**
	 * Obtener hd ds.
	 *
	 * @param equipo the equipo
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<HDD> obtenerHDDs(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<HDD> HDDs = new Vector<HDD>();
		ResultSet rs_eqhdd = this.lanzarSelect("Select ModeloHDD from eqhdd where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqhdd.next()){
			try{
				ResultSet rs_hdd = this.lanzarSelect("select * from hdd where ModeloHDD = ('" + rs_eqhdd.getString(1) + "')");
				rs_hdd.next();
				HDD hdd = new HDD(rs_hdd.getString("ModeloHDD"), rs_hdd.getString("Marca"), rs_hdd.getInt("Capacidad"), rs_hdd.getInt("RPM"));
				HDDs.addElement(hdd);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return HDDs;
	}
	
	/**
	 * Obtener max cod.
	 *
	 * @param tabla the tabla
	 * @return the int
	 * @throws SQLException the sQL exception
	 */
	private int obtenerMaxCod(String tabla) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return 0;
		}
		ResultSet rs_mc = this.lanzarSelect("select MAX(Codigo) from " + tabla);
		rs_mc.next();
		return rs_mc.getInt(1);
	}
	
	/**
	 * Obtener monitor.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<Monitor> obtenerMonitor() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Monitor> monitores = new Vector<Monitor>();
		ResultSet rs_mon = this.lanzarSelect("select * from monitor");
		while(rs_mon.next()){
			try{
				Monitor monitor = new Monitor(rs_mon.getString("ModeloMonitor") ,rs_mon.getString("Marca"), rs_mon.getFloat("Pulgadas"), rs_mon.getString("Resolucion"), 
						rs_mon.getInt("Herzios"));
				monitores.addElement(monitor);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return monitores;
	}
	
	/**
	 * Obtener monitor.
	 *
	 * @param equipo the equipo
	 * @return the monitor
	 * @throws SQLException the sQL exception
	 */
	public Monitor obtenerMonitor(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Monitor monitor = null;
		ResultSet rs_mon = this.lanzarSelect("select * from monitor where ModeloMonitor = (select ModeloMonitor from equipos where CodEquipo = " 
				+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula() + ")");
		while(rs_mon.next()){
			try{
				monitor = new Monitor(rs_mon.getString("ModeloMonitor") ,rs_mon.getString("Marca"), rs_mon.getFloat("Pulgadas"), rs_mon.getString("Resolucion"), 
						rs_mon.getInt("Herzios"));
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return monitor;
	}
	
	/**
	 * Obtener placa base.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<PlacaBase> obtenerPlacaBase() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<PlacaBase> vpb = new Vector<PlacaBase>();
		ResultSet rs_pb = this.lanzarSelect("select * from `placas base`");
		while(rs_pb.next()){
			try{
				PlacaBase pb = new PlacaBase(rs_pb.getString("ModeloPB") ,rs_pb.getString("Marca"), rs_pb.getInt("PCI"), rs_pb.getInt("AGP"), rs_pb.getInt("PCIE"), 
						rs_pb.getInt("TipoDDR"), rs_pb.getInt("PuertosDDR"));
				vpb.addElement(pb);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return vpb;
	}
	
	/**
	 * Obtener placa base.
	 *
	 * @param equipo the equipo
	 * @return the placa base
	 * @throws SQLException the sQL exception
	 */
	public PlacaBase obtenerPlacaBase(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		PlacaBase pb = null;
		ResultSet rs_pb = this.lanzarSelect("select * from `placas base` where ModeloPB = (select ModeloPB from equipos where CodEquipo = " 
				+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula() + ")");
		while(rs_pb.next()){
			try{
				pb = new PlacaBase(rs_pb.getString("ModeloPB") ,rs_pb.getString("Marca"), rs_pb.getInt("PCI"), rs_pb.getInt("AGP"), rs_pb.getInt("PCIE"), 
						rs_pb.getInt("TipoDDR"), rs_pb.getInt("PuertosDDR"));
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return pb;
	}
	
	/**
	 * Obtener ram.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<RAM> obtenerRAM() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<RAM> ram = new Vector<RAM>();
		ResultSet rs_ram = this.lanzarSelect("select * from ram");
		while(rs_ram.next()){
			try{
				RAM r = new RAM(rs_ram.getString("ModeloRAM"), rs_ram.getString("Marca"), rs_ram.getInt("Capacidad"), rs_ram.getInt("Velocidad"));
				ram.addElement(r);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return ram;
	}
	
	/**
	 * Obtener ram.
	 *
	 * @param equipo the equipo
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<RAM> obtenerRAM(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<RAM> ram = new Vector<RAM>();
		ResultSet rs_eqram = this.lanzarSelect("Select ModeloRAM from eqram where CodEquipo = "	+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqram.next()){
			try{
				ResultSet rs_ram = this.lanzarSelect("select * from ram where ModeloRAM = ('" + rs_eqram.getString(1) + "')");
				rs_ram.next();
				RAM r = new RAM(rs_ram.getString("ModeloRAM"), rs_ram.getString("Marca"), rs_ram.getInt("Capacidad"), rs_ram.getInt("Velocidad"));
				ram.addElement(r);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return ram;
	}
	
	public Vector<Software> obtenerSoftware() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Software> vsoft = new Vector<Software>();
		ResultSet rs_so = this.lanzarSelect("select * from software");
		while(rs_so.next()){
			try{
				Software soft = new Software(rs_so.getInt("CodSoft"), rs_so.getString("Nombre"), rs_so.getFloat("Version"), 
						rs_so.getString("Categoria"), rs_so.getString("Licencia"), rs_so.getString("Desarrollador"));
				vsoft.addElement(soft);
			}catch (Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return vsoft;
	}
	
	public Vector<Software> obtenerSoftware(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Software> vsoft = new Vector<Software>();
		ResultSet rs_eqso = this.lanzarSelect("SELECT CodSoft FROM eqso WHERE CodAula=" + equipo.getCodAula() + " AND CodEquipo=" + equipo.getCodEquipo());
		while(rs_eqso.next()){
			try{
				ResultSet rs_so = this.lanzarSelect("select * from software where CodSoft = ('" + rs_eqso.getInt("CodSoft") + "')");
				rs_so.next();
				Software s = new Software(rs_so.getInt("CodSoft"), rs_so.getString("Nombre"), rs_so.getFloat("Version"), 
						rs_so.getString("Categoria"), rs_so.getString("Licencia"), rs_so.getString("Desarrollador"));
				vsoft.addElement(s);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return vsoft;
	}

	public Software obtenerSoftware(int codigo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Software software = null;
		ResultSet rs_soft = this.lanzarSelect("SELECT * FROM software WHERE CodSoft=" + codigo);
		rs_soft.next();
		try {
			software = new Software(rs_soft.getInt("CodSoft"), rs_soft.getString("Nombre"), rs_soft.getFloat("Version"), 
					rs_soft.getString("Categoria"), rs_soft.getString("Licencia"), rs_soft.getString("Desarrollador"));
		} catch (ValorIncorrectoEx e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return software;
	}
	
	public Vector<Solicitud> obtenerSolicitudes() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Solicitud> vsol = new Vector<Solicitud>();
		ResultSet rs_sol = this.lanzarSelect("select * from solicitudes");
		while(rs_sol.next()){
			try{
				Software software = obtenerSoftware(rs_sol.getInt("CodSoft"));
				Usuario usuario = obtenerUsuario(rs_sol.getString("NomUsu"));
				int codEquipo = rs_sol.getInt("CodEquipo");
				Solicitud solicitud = null;
				if(codEquipo == 0){
					Aula aula = obtenerAula(rs_sol.getInt("CodAula"));
					solicitud = new Solicitud(rs_sol.getInt("CodSolicitud"), software, rs_sol.getString("Descripcion"), 
							usuario, aula, rs_sol.getBoolean("Exito"), rs_sol.getBoolean("Realizado"), rs_sol.getString("Observaciones"));
				}else{
					Equipo equipo = new Equipo(rs_sol.getInt("CodAula"), rs_sol.getInt("CodEquipo"));
					equipo.cargarHardware();
					solicitud = new Solicitud(rs_sol.getInt("CodSolicitud"), software, rs_sol.getString("Descripcion"), 
							usuario, equipo, rs_sol.getBoolean("Exito"), rs_sol.getBoolean("Realizado"), rs_sol.getString("Observaciones"));
				}
				vsol.addElement(solicitud);
			}catch (Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return vsol;
	}
	
	public Solicitud obtenerSolicitud(int codSolicitud) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		ResultSet rs_sol = lanzarSelect("SELECT * FROM solicitudes WHERE CodSolicitud=" + codSolicitud);
		Solicitud solicitud = null;
		while(rs_sol.next()){
			Software software = obtenerSoftware(rs_sol.getInt("CodSoft"));
			Usuario usuario = obtenerUsuario(rs_sol.getString("NomUsu"));
			int codEquipo = rs_sol.getInt("CodEquipo");
			try{
				if(codEquipo == 0){
					Aula aula = obtenerAula(rs_sol.getInt("CodAula"));
					solicitud = new Solicitud(rs_sol.getInt("CodSolicitud"), software, rs_sol.getString("Descripcion"), 
							usuario, aula, rs_sol.getBoolean("Exito"), rs_sol.getBoolean("Realizado"), rs_sol.getString("Observaciones"));
				}else{
					Equipo equipo = new Equipo(rs_sol.getInt("CodAula"), rs_sol.getInt("CodEquipo"));
					equipo.cargarHardware();
					solicitud = new Solicitud(rs_sol.getInt("CodSolicitud"), software, rs_sol.getString("Descripcion"), 
							usuario, equipo, rs_sol.getBoolean("Exito"), rs_sol.getBoolean("Realizado"), rs_sol.getString("Observaciones"));
				}
			}catch (ValorIncorrectoEx e){
				System.out.println(e.getMessage().toString());
			}
		}
		return solicitud;
	}
	
	/**
	 * Obtener t audio.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<TAudio> obtenerTAudio() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TAudio> vta = new Vector<TAudio>();
		ResultSet rs_ta = this.lanzarSelect("select * from `taudio`");
		while(rs_ta.next()){
			try{
				TAudio ta = new TAudio(rs_ta.getString("ModeloTA") ,rs_ta.getString("Marca"), rs_ta.getFloat("Canales"));
				vta.addElement(ta);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return vta;
	}

	/**
	 * Obtener t audio.
	 *
	 * @param equipo the equipo
	 * @return the t audio
	 * @throws SQLException the sQL exception
	 */
	public TAudio obtenerTAudio(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		TAudio ta = null;
		ResultSet rs_ta = this.lanzarSelect("select * from `taudio` where ModeloTA = (select ModeloTA from equipos where CodEquipo = " 
				+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula() + ")");
		while(rs_ta.next()){
			try{
				ta = new TAudio(rs_ta.getString("ModeloTA") ,rs_ta.getString("Marca"), rs_ta.getFloat("Canales"));
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return ta;
	}
	
	/**
	 * Obtener t grafica.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<TGrafica> obtenerTGrafica() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TGrafica> tGraficas = new Vector<TGrafica>();
		ResultSet rs_tg = this.lanzarSelect("select * from `tgrafica`");
		while(rs_tg.next()){
			try{
				TGrafica tg = new TGrafica(rs_tg.getString("ModeloTG"), rs_tg.getString("Marca"), rs_tg.getString("Puerto"), rs_tg.getInt("Velocidad"),
						rs_tg.getInt("Memoria"));
				tGraficas.addElement(tg);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return tGraficas;
	}
	
	/**
	 * Obtener t grafica.
	 *
	 * @param equipo the equipo
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<TGrafica> obtenerTGrafica(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TGrafica> tGraficas = new Vector<TGrafica>();
		ResultSet rs_eqtg = this.lanzarSelect("Select ModeloTG from eqtg where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqtg.next()){
			try{
				ResultSet rs_tg = this.lanzarSelect("select * from `tgrafica` where ModeloTG = ('" + rs_eqtg.getString(1) + "')");
				rs_tg.next();
				TGrafica tg = new TGrafica(rs_tg.getString("ModeloTG"), rs_tg.getString("Marca"), rs_tg.getString("Puerto"), rs_tg.getInt("Velocidad"),
						rs_tg.getInt("Memoria"));
				tGraficas.addElement(tg);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return tGraficas;
	}
	
	/**
	 * Obtener t red.
	 *
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<TRed> obtenerTRed() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TRed> tRed = new Vector<TRed>();
		ResultSet rs_tr = this.lanzarSelect("select * from `tred`");
		while(rs_tr.next()){
			try{
				TRed tr = new TRed(rs_tr.getString("ModeloTR"), rs_tr.getString("Marca"), rs_tr.getString("Puerto"), rs_tr.getInt("Capacidad"));
				tRed.addElement(tr);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return tRed;
	}
	
	/**
	 * Obtener t red.
	 *
	 * @param equipo the equipo
	 * @return the vector
	 * @throws SQLException the sQL exception
	 */
	public Vector<TRed> obtenerTRed(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TRed> tRed = new Vector<TRed>();
		ResultSet rs_eqtr = this.lanzarSelect("Select ModeloTR from eqtr where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqtr.next()){
			try{
				ResultSet rs_tr = this.lanzarSelect("select * from `tred` where ModeloTR = ('" + rs_eqtr.getString(1) + "')");
				rs_tr.next();
				TRed tr = new TRed(rs_tr.getString("ModeloTR"), rs_tr.getString("Marca"), rs_tr.getString("Puerto"), rs_tr.getInt("Capacidad"));
				tRed.addElement(tr);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return tRed;
	}
	
	/**
	 * Obtener usuarios.
	 *
	 * @return the hashtable
	 * @throws SQLException the sQL exception
	 */
	public Hashtable<String, Usuario> obtenerUsuarios() throws SQLException{
		if (this.getConexion() == null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
	    Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	    ResultSet rs_usu = this.lanzarSelect("select * from usuarios where Activo=1");
	    while (rs_usu.next()) {
	    	try {
	    		Usuario u = null;
	    		if(rs_usu.getString("Tipo").equals("Usuario")){
	    			u = new Usuario(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
	    		}else if(rs_usu.getString("Tipo").equals("Tecnico")){
	    			u = new Tecnico(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
	    		}else if(rs_usu.getString("Tipo").equals("Administrador")){
	    			u = new Administrador(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
	    		}
	    		usuarios.put(u.getNombre(), u);
	    	} catch (Exception ex1) {
	    		System.out.println(ex1.getMessage());
	    		return null;
	    	}
	    }
	    return usuarios;
	}
	
	public Usuario obtenerUsuario(String nombre) throws SQLException{
		if (this.getConexion() == null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		ResultSet rs_usu = this.lanzarSelect("SELECT * FROM usuarios WHERE NomUsu='" + nombre + "'");
		Usuario u = null;
		while(rs_usu.next()){
			if(rs_usu.getString("Tipo").equals("Usuario")){
    			u = new Usuario(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
    		}else if(rs_usu.getString("Tipo").equals("Tecnico")){
    			u = new Tecnico(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
    		}else if(rs_usu.getString("Tipo").equals("Administrador")){
    			u = new Administrador(rs_usu.getString("NomUsu"), rs_usu.getString("Pass"), rs_usu.getString("Email"));
    		}
		}
		return u;
	}
	
	public void instalarSoftware(Equipo e, Software s) throws SQLException{
		if (this.getConexion() == null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("eqso", "CodAula, CodEquipo, CodSoft", e.getCodAula() + ", " + e.getCodEquipo() + ", " +
				s.getCodigo());
	}

	public void instalarSoftware(Aula a, Software s) throws SQLException{
		if (this.getConexion() == null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		Vector<Equipo> vEquipos = obtenerEquipos(a);
		for(int i = 0; i < vEquipos.size(); i++){
			try{
				instalarSoftware(vEquipos.elementAt(i), s);
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
	}
}
	