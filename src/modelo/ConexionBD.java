package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConexionBD es una clase que se conecta a una base de datos MySQL.
 */
public class ConexionBD {

    private Connection conexion;
    private String bd, usuario, clave, mensaje;

    /**
     * Es un constructor parametrico que instancia a Conexion con los siguientes parametros.
     * @param conexion
     * @param bd
     * @param usuario
     * @param clave
     * @param mensaje
     */
    public ConexionBD(Connection conexion, String bd, String usuario, String clave, String mensaje) {
        this.conexion = conexion;
        this.bd = bd;
        this.usuario = usuario;
        this.clave = clave;
        this.mensaje = mensaje;
    }
    /**
     * Es un constructor basico que instancia a ConexionBD con valores especificos para la
     * base de datos tienda_camaras.
     */
    public ConexionBD() {
        this.conexion = null;
        this.bd = "tienda_camaras"; //Nombre de la base de datos
        this.usuario = "root"; //usuario
        this.clave = ""; 
        this.mensaje = "";
    }

    /**
     * Se conecta a la base de datos usando el driver de MySQL.
     */
    public void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Nombre del driver
            String ruta = "jdbc:mysql://localhost/" + bd;
            System.out.println(ruta);
            conexion = DriverManager.getConnection(ruta, usuario, clave);
            mensaje = "Conexión exitosa...";
        } catch (ClassNotFoundException ex) {
            mensaje = "No se pudo establecer conexion...";
        } catch (SQLException ex) {
            mensaje = "No se puede conectar con MySQL...";
        }
    }

    @Override
    public String toString() {
        return "Conexion{" + "conexion=" + conexion + ", bd=" + bd + ", usuario=" + usuario + ", clave=" + clave + ", mensaje=" + mensaje + '}';
    }

    /**
     * Esta función devuelve el objeto de conexión.
     *
     * @return El objeto de conexión.
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Esta función establece la conexión a la base de datos.
     *
     * @param conexion Objeto de conexión
     */
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Esta función devuelve el valor de la variable bd
     *
     * @return El valor de la variable bd.
     */
    public String getBd() {
        return bd;
    }

    /**
     * Esta función establece el valor de la variable bd al valor del parámetro bd.
     *
     * @param bd El nombre de la base de datos a la que conectarse.
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * Esta función devuelve el valor de la variable usuario
     *
     * @return El valor de la variable de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Esta función establece el valor de la variable usuario al valor del parámetro usuario
     *
     * @param usuario El nombre de usuario del usuario al que desea enviar el mensaje.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * > Esta función devuelve el valor de la variable clave
     *
     * @return El valor de la variable clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Esta función establece el valor de la variable clave al valor del parámetro clave
     *
     * @param clave La contraseña para el usuario.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Esta función devuelve el valor de la variable mensaje
     *
     * @return El método getMensaje() está devolviendo el valor de la variable mensaje.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Esta función establece el valor de la variable mensaje al valor de la variable mensaje que se pasa como parámetro
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
