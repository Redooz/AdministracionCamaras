package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private Connection conexion;
    private String bd, usuario, clave, mensaje;

    public ConexionBD(Connection conexion, String bd, String usuario, String clave, String mensaje) {
        this.conexion = conexion;
        this.bd = bd;
        this.usuario = usuario;
        this.clave = clave;
        this.mensaje = mensaje;
    }

    public ConexionBD() {
        this.conexion = null;
        this.bd = "tienda_camaras"; //Nombre de la base de datos
        this.usuario = "root"; //usuario
        this.clave = ""; 
        this.mensaje = "";
    }

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

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
