package datos;
import java.sql.*;

public class AccesoBD {

    public static final String DRIVER_GEN = "sun.jdbc.odbc.JdbcOdbcDriver";
    private Connection con = null;
    //Multihilo--> Colección de Statements para los usuarios conectados

    public static final int ACCESS =  1;
    public static final int MYSQL = 2;
    public static final int SQLSERVER = 3;
    public static final int ORACLE = 4;


    public AccesoBD() {
    }

    /**
     * Método que nos devuelve a conexión activa
     * @return Connection
     */
    public Connection getConexion(){
        return con;
    }

    /**
     * Método que permite establecer una conexión con una base de datos o SGBD sin
     * necesidad de configurar un ODBC en el equipo de forma manual.
     * @param ruta String: ruta hacia la base de datos publica o del sistema de archivos
     * @param code int: ACCESS = 1; MYSQL = 2; SQLSERVER = 3; ORACLE = 4;
     * @param user String: nombre de usuario a conectarse en caso de que exista
     * @param pass String: contraseña del usuario en caso de que exista
     * @throws SQLException: excepción lanzada por con poder establecer la conexión una vez cargado el driver
     * @throws ClassNotFoundException: excepción lanzada por no poder cargar el Driver
     */
    public void abrirConexionSinODBC(String ruta, int code, String user, String pass) throws
            SQLException, ClassNotFoundException {
        String bd_def = "";
        switch (code) {
            case 1: //Access
                Class.forName(DRIVER_GEN);
                bd_def ="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+ruta;
                con = DriverManager.getConnection(bd_def,user,pass);
                System.out.println("Conexión establecida con: " + ruta);
                break;
            case 2: //MySQL
                Class.forName("com.mysql.jdbc.Driver");
                // "jdbc:mysql://localhost/agenda", "root", "password"
                con = DriverManager.getConnection("jdbc:mysql:" + ruta, user, pass);
                System.out.println("Conexión establecida con: " + ruta);
                break;
            //Faltan los "cases" 3 (SQLServer) y 4 (ORACLE)
        }
    }//fin método abrirConexionSinODBC


    /**
     * Método que permite establecer una conexión con una base de datos a través de
     * un ODBC configurado en el equipo
     * @param ODBC String: nombre del Origen de Datos de 32 bits
     * @param user String: nombre de usuario a conectarse en caso de que exista
     * @param pass String: contraseña del usuario en caso de que exista
     * @throws SQLException: excepción lanzada por con poder establecer la conexión una vez cargado el driver
     * @throws ClassNotFoundException: excepción lanzada por no poder cargar el Driver
     */
    public void abrirConexionODBC(String ODBC, String user, String pass) throws
            ClassNotFoundException, SQLException {
    //se podría hacer como el anterior para todo tipo de SGBD
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        con = DriverManager.getConnection("jdbc:odbc:" + ODBC, user, pass);
        System.out.println("Conexión establecida con ODBC: " + ODBC);
    }

    /**
     * Método para cerrar la conexión con la Base de Datos de forma segura
     */
    public void cerrarConexion(){
        try {
        //En caso de que lleváramos un control sobre los usuarios y sus Statements
        //(flujos SQL) los cerraríamos 1 a 1 de forma segura antes de acabar con la
        //conexión.
            con.close();
            System.out.println("Base de datos cerrada con ÉXITO");
        } catch (SQLException ex) {
            System.out.println("ERROR de CIERRE: "+ex.getMessage());
        }
    }

    /**
     * Método que permite insertar un registro en una tabla.
     * @param tabla String : nombre de la tabla
     * @param campos String: lista de campos entre paréntesis y separados por comas
     * @param valores String: lista de valores entre paréntesis y separados por comas
     */
    public void lanzarInsert(String tabla, String campos, String valores) throws
            SQLException {
    //INSERT INTO ALUMNOS (USN,NOMBRE,APELLIDOS,AÑOINI) VALUES (103,'Iker','Lekue Arambarri',2004)
       String SQL = "INSERT INTO "+tabla+" ("+campos+") VALUES ("+valores+")";
       System.out.println("** "+SQL);
       //Debemos crear un flujo SQL para lanzar la sentencia
       if (con!=null){
           Statement st = con.createStatement();
           st.executeUpdate(SQL);
           System.out.println("Registro INSERTADO CON EXITO");
           st.close();
       }else{
           System.out.println("Imposible realizar la inserción; CONEXION BD NO ESTABLECIDA");
       }//fin conexion establecida
    }//fin método lanzarInsert


    /**
     * Método que permite borrar N-registros de una tabla de una BD que cumplan
     * una condición.
     * @param tabla String: nombre de la tabla
     * @param condicion String: condición del borrado
     * @throws SQLException
     */
    public void lanzarDelete(String tabla, String condicion) throws
                SQLException {
        //DELETE FROM ALUMNOS WHERE AÑOINI=2004;
        String SQL = "DELETE FROM "+tabla+" WHERE "+condicion;
           System.out.println("** "+SQL);
           //Debemos crear un flujo SQL para lanzar la sentencia
           if (con!=null){
               Statement st = con.createStatement();
               int u = st.executeUpdate(SQL);
               System.out.println(""+u+" Registro/s BORRADO/S CON EXITO");
               st.close();
           }else{
               System.out.println("Imposible realizar el borrado; CONEXION BD NO ESTABLECIDA");
           }//fin conexion establecida
    }//fin método lanzarDelete

    /**
     * Método que permite borrar N-registros de una tabla de una BD actualizando
     * N-campos de cada uno de ellos bajo una claúsula o condición.
     * @param tabla String: nombre e la tabla
     * @param campos String: conjunto de tuplas campo=valor
     * @param condicion String: condición de actualización
     * @throws SQLException
     */
    public void lanzarUpdate(String tabla, String campos, String condicion) throws
                SQLException {
        //UPDATE ALUMNOS SET AÑOINI=2005 WHERE AÑOINI=2003
        String SQL = "UPDATE "+tabla+" SET "+campos+" WHERE "+condicion;
           System.out.println("** "+SQL);
           //Debemos crear un flujo SQL para lanzar la sentencia
           if (con!=null){
               Statement st = con.createStatement();
               int u = st.executeUpdate(SQL);
               System.out.println(" "+u+" Registro/s MODIFICADOS/S CON EXITO");
               st.close();
           }else{
               System.out.println("Imposible realizar la actualización; CONEXION BD NO ESTABLECIDA");
           }//fin conexion establecida
    }//fin método lanzarDelete


    /**
     * Método que lanza una sentencia de administración al MOTOR de SQL
     * (GRANT USER, CREATE/DROP/ALTER TABLE ...)
     * @param SQLAdmin Strin: sentencia de administración SQL
     */
    public void lanzarSQLAdmin(String SQLAdmin) throws SQLException {
         System.out.println("** "+SQLAdmin);
         if (con!=null){
               Statement st = con.createStatement();
               st.execute(SQLAdmin);
               String instr = SQLAdmin.substring(0,SQLAdmin.indexOf(' '));
               System.out.println(instr.toUpperCase()+" REALIZADA CON EXITO");
               st.close();
           }else{
               System.out.println("Imposible realizar la SQL; CONEXIÓN BD NO ESTABLECIDA");
           }//fin conexion establecida
    }

    /**
     * Método que devuelve el set de resultados de una consulta realizda contra
     * una o varias tablas o consultas de una base de datos.
     * @param select String: consulta a realizar.
     * @return ResultSet: Conjunto de resultados cerrado.
     * @throws SQLException
     */
    public ResultSet lanzarSelect(String select) throws SQLException{
        System.out.println("** "+select);
        if (con!=null){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            System.out.println("CONSULTA REALIZADA CON EXITO");
            //st.close();  //OJO!!! NO CERRAREMOS EL STATEMENT
            return rs;
        }else{
            System.out.println("Imposible realizar la CONSULTA; CONEXIÓN BD NO ESTABLECIDA");
            return null;
       }//fin conexion establecida
    }

    /**
     * Método que permite visualizar por pantalla el conjunto de resultados devuelto
     * por una consulta previa
     * @param rs ResultSet: set de resultados a visualizar.
     */
    public void visualizarResultados(ResultSet rs) throws SQLException{
        ResultSetMetaData md = rs.getMetaData();
        for(int campo=1; campo<=md.getColumnCount(); campo++){
            System.out.print(md.getColumnName(campo)+"\t\t");
        }
        System.out.println("\r\n-----------------------------------------------------------------");
        while(rs.next()){
            for(int campo=1; campo<=md.getColumnCount(); campo++){
                System.out.print(rs.getObject(campo)+"\t\t");
            }//fin para cada campo
            System.out.println();
        }//fin mientras haya registros
    }

    /**
     * Método que permite introducir una sxentencia SQL de forma directa
     * @param SQL String: la sentencia SQL a gestionar por el Motor SQL
     * @return ResultSet: resultados en caso de ser una SELECT
     * @throws SQLException
     */
    public ResultSet lanzarSQL(String SQL) throws SQLException {
        ResultSet rs = null;
        if (SQL==null) return null;
        if (con==null){
            System.out.println("Imposible realizar la CONSULTA; CONEXIÓN BD NO ESTABLECIDA");
            return null;
        }
        Statement st = con.createStatement();
        String instruccion = SQL.substring(0,SQL.indexOf(' ')).toUpperCase();
        if(instruccion.equals("INSERT")||instruccion.equals("DELETE")||instruccion.equals("UPDATE")){
            int num = st.executeUpdate(SQL); //sentencia de actualización
            System.out.println(instruccion+" realizada con EXITO. "+num+ " registro modificado/s");
            return null;
        }else if(instruccion.equals("SELECT")){
            rs = st.executeQuery(SQL); //sentencia de consulta
            System.out.println(instruccion+" realizada con EXITO.");
            return rs;
        }else{
            st.execute(SQL); //sentencia de administración
            System.out.println(instruccion+" realizada con EXITO.");
            return null;
        }
    }
}
