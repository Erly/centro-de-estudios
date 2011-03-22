package datos;

import modelo.Hardware.*;
import modelo.Usuarios.*;
import modelo.Aula;
import modelo.Equipo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

public class CentroDB extends AccesoBD {

	public CentroDB() {
	}

	public String login(Usuario usuario) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return "Imposible realizar consulta; CONEXION BD NO ESTABLECIDA";
		}
		ResultSet rs_login = lanzarSelect("Select * from usuarios where NomUsu='" + usuario.getNombre() + "'");
		while(rs_login.next()){
			//Usuario usu = new Usuario(rs_login.getString("NomUsu") ,rs_login.getString("Pass"), rs_login.getString("Email"), 
			//		rs_login.getString("Tipo");
			if(usuario.getPass().equals(rs_login.getString("Pass"))){
				return rs_login.getString("Tipo");
			}else{
				return "La contraseña introducida es incorrecta.";
			}
		}
		return "El usuario introducido no existe";
	}
	
	public void actualizarAula(Aula aula) throws SQLException{
		actualizarAula(aula.getCodAula(), aula.getCurso());
	}
	
	public void actualizarAula(int codAula, String curso) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("Aulas", "Curso='" + curso + "'", "CodAula =" + codAula);
	}
	
	public void actualizarEquipo(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarUpdate("Equipos", "CodEquipo='" + equipo.getCodEquipo() + "'", "CodAula =" + equipo.getCodAula());
	}
	
	public void borrarAula(Aula aula) throws SQLException{
		borrarAula(aula.getCodAula());
	}
	
	public void borrarAula(int codAula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarDelete("Aulas", "CodAula=" + codAula);
	}
	
	public void borrarEquipo(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarDelete("eqTR", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqTG", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqRAM", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		this.lanzarDelete("eqHDD", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
		
		this.lanzarDelete("Equipos", "CodEquipo=" + equipo.getCodEquipo() + " AND CodAula=" + equipo.getCodAula());
	}
	
	public void insertarAula(Aula aula) throws SQLException{
		insertarAula(aula.getCodAula(), aula.getCurso());
	}
	
	public void insertarAula(int codAula, String curso) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("Aulas", "CodAula, Curso", codAula + ", '" + curso + "'");
	}
	
	public void insertarEquipo(Equipo equipo) throws SQLException{
		insertarEquipo(equipo.getCodAula(), equipo.getCodEquipo(), equipo.getPlacaBase(), equipo.getHDDs(), equipo.getCpu(), equipo.getRam(), 
				equipo.gettGraficas(), equipo.gettAudio(), equipo.getMonitor(), equipo.gettRed());
	}
	
	public void insertarEquipo(int codAula, int codEquipo, PlacaBase pb, Vector<HDD> vhdd, CPU cpu, Vector<RAM> vram, Vector<TGrafica> vtgraf, 
			TAudio tso, Monitor mon, Vector<TRed> vtred) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return;
		}
		this.lanzarInsert("Equipos", "CodEquipo, CodAula, ModeloPB, ModeloCPU, ModeloTA, ModeloMonitor", codEquipo + ", " + codAula + ", '" + pb.getModelo() + "', '" + 
				cpu.getModelo() + "', '" + tso.getModelo() + "', '" + mon.getModelo() + "'");
		for(int i = 0; i < vhdd.size(); i++){
			this.lanzarInsert("eqHDD", "Codigo, codEquipo, codAula, ModeloHDD", this.obtenerMaxCod("eqHDD") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vhdd.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vram.size(); i++){
			this.lanzarInsert("eqRAM", "Codigo, codEquipo, codAula, ModeloRAM", this.obtenerMaxCod("eqRAM") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vram.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vtgraf.size(); i++){
			this.lanzarInsert("eqTG", "Codigo, codEquipo, codAula, ModeloTG", this.obtenerMaxCod("eqTG") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vtgraf.elementAt(i).getModelo() + "'");
		}
		for(int i = 0; i < vtred.size(); i++){
			this.lanzarInsert("eqTR", "Codigo, codEquipo, codAula, ModeloTR", this.obtenerMaxCod("eqTR") + 1 + ", " + codEquipo + ", " + codAula + ", '" +
					vtred.elementAt(i).getModelo() + "'");
		}
	}
	
	public Vector<Aula> obtenerAulas() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Aula> aulas = new Vector<Aula>();
		ResultSet rs_aulas = this.lanzarSelect("select * from Aulas ORDER BY CodAula");
		while(rs_aulas.next()){
			try{
				Aula a = new Aula(rs_aulas.getInt("CodAula"), rs_aulas.getString("Curso"));
				a.setEquipos(obtenerEquipos(a));
				aulas.addElement(a);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return aulas;
	}
	
	public Vector<CPU> obtenerCPU() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<CPU> CPUs = new Vector<CPU>();
		ResultSet rs_cpu = this.lanzarSelect("select * from CPU");
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
	
	public CPU obtenerCPU(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		CPU cpu = null;
		ResultSet rs_cpu = this.lanzarSelect("select * from CPU where ModeloCPU = (select ModeloCPU from Equipos where CodEquipo = " 
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
	
	public Vector<Equipo> obtenerEquipos(Aula aula) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Equipo> equipos = new Vector<Equipo>();
		ResultSet rs_eq = this.lanzarSelect("select * from Equipos where CodAula = " + aula.getCodAula());
		while(rs_eq.next()){
			try{
				Equipo e = new Equipo(rs_eq.getInt("CodEquipo"));
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
	
	public Vector<HDD> obtenerHDDs() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<HDD> HDDs = new Vector<HDD>();
		ResultSet rs_hdd = this.lanzarSelect("select * from HDD");
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
	
	public Vector<HDD> obtenerHDDs(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<HDD> HDDs = new Vector<HDD>();
		ResultSet rs_eqhdd = this.lanzarSelect("Select ModeloHDD from EqHDD where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqhdd.next()){
			try{
				ResultSet rs_hdd = this.lanzarSelect("select * from HDD where ModeloHDD = ('" + rs_eqhdd.getString(1) + "')");
				rs_hdd.next();
				HDD hdd = new HDD(rs_hdd.getString("ModeloHDD"), rs_hdd.getString("Marca"), rs_hdd.getInt("Capacidad"), rs_hdd.getInt("RPM"));
				HDDs.addElement(hdd);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return HDDs;
	}
	
	private int obtenerMaxCod(String tabla) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return 0;
		}
		ResultSet rs_mc = this.lanzarSelect("select MAX(Codigo) from " + tabla);
		rs_mc.next();
		return rs_mc.getInt(1);
	}
	
	public Vector<Monitor> obtenerMonitor() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<Monitor> monitores = new Vector<Monitor>();
		ResultSet rs_mon = this.lanzarSelect("select * from Monitor");
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
	
	public Monitor obtenerMonitor(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Monitor monitor = null;
		ResultSet rs_mon = this.lanzarSelect("select * from Monitor where ModeloMonitor = (select ModeloMonitor from Equipos where CodEquipo = " 
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
	
	public Vector<PlacaBase> obtenerPlacaBase() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<PlacaBase> vpb = new Vector<PlacaBase>();
		ResultSet rs_pb = this.lanzarSelect("select * from `Placas Base`");
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
	
	public PlacaBase obtenerPlacaBase(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		PlacaBase pb = null;
		ResultSet rs_pb = this.lanzarSelect("select * from `Placas Base` where ModeloPB = (select ModeloPB from Equipos where CodEquipo = " 
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
	
	public Vector<RAM> obtenerRAM() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<RAM> ram = new Vector<RAM>();
		ResultSet rs_ram = this.lanzarSelect("select * from RAM");
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
	
	public Vector<RAM> obtenerRAM(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<RAM> ram = new Vector<RAM>();
		ResultSet rs_eqram = this.lanzarSelect("Select ModeloRAM from EqRAM where CodEquipo = "	+ equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqram.next()){
			try{
				ResultSet rs_ram = this.lanzarSelect("select * from RAM where ModeloRAM = ('" + rs_eqram.getString(1) + "')");
				rs_ram.next();
				RAM r = new RAM(rs_ram.getString("ModeloRAM"), rs_ram.getString("Marca"), rs_ram.getInt("Capacidad"), rs_ram.getInt("Velocidad"));
				ram.addElement(r);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return ram;
	}
	
	public Vector<TAudio> obtenerTAudio() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TAudio> vta = new Vector<TAudio>();
		ResultSet rs_ta = this.lanzarSelect("select * from `TAudio`");
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

	public TAudio obtenerTAudio(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		TAudio ta = null;
		ResultSet rs_ta = this.lanzarSelect("select * from `TAudio` where ModeloTA = (select ModeloTA from Equipos where CodEquipo = " 
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
	
	public Vector<TGrafica> obtenerTGrafica() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TGrafica> tGraficas = new Vector<TGrafica>();
		ResultSet rs_tg = this.lanzarSelect("select * from `TGrafica`");
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
	
	public Vector<TGrafica> obtenerTGrafica(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TGrafica> tGraficas = new Vector<TGrafica>();
		ResultSet rs_eqtg = this.lanzarSelect("Select ModeloTG from EqTG where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqtg.next()){
			try{
				ResultSet rs_tg = this.lanzarSelect("select * from `TGrafica` where ModeloTG = ('" + rs_eqtg.getString(1) + "')");
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
	
	public Vector<TRed> obtenerTRed() throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TRed> tRed = new Vector<TRed>();
		ResultSet rs_tr = this.lanzarSelect("select * from `TRed`");
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
	
	public Vector<TRed> obtenerTRed(Equipo equipo) throws SQLException{
		if(this.getConexion()==null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
			return null;
		}
		Vector<TRed> tRed = new Vector<TRed>();
		ResultSet rs_eqtr = this.lanzarSelect("Select ModeloTR from EqTR where CodEquipo = " + equipo.getCodEquipo() + " AND CodAula = " + equipo.getCodAula());
		while(rs_eqtr.next()){
			try{
				ResultSet rs_tr = this.lanzarSelect("select * from `TRed` where ModeloTR = ('" + rs_eqtr.getString(1) + "')");
				rs_tr.next();
				TRed tr = new TRed(rs_tr.getString("ModeloTR"), rs_tr.getString("Marca"), rs_tr.getString("Puerto"), rs_tr.getInt("Capacidad"));
				tRed.addElement(tr);
			}catch(Exception ex1){
				System.out.println(ex1.getMessage());
			}
		}
		return tRed;
	}
	
	public Hashtable<String, Usuario> obtenerUsuarios() throws SQLException{
		if (this.getConexion() == null){
			System.out.println("Imposible realizar consulta; CONEXION BD NO ESTABLECIDA");
	          return null;
	       }
	       Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	        ResultSet rs_usu = this.lanzarSelect("select * from usuarios");
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
}
