package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase se utiliza para conectarse a la base de datos y realizar operaciones CRUD en la tabla Clientes.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class ClienteDAO {
    private Cliente objC;

    /**
     * Un constructor que recibe un objeto Cliente como parámetro.
     */
    public ClienteDAO(Cliente objC) {
        this.objC = objC;
    }

    /**
     * Un constructor que inicializa el objeto `objC`.
     */
    public ClienteDAO() {
        this.objC = new Cliente();
    }

    /**
     * Se conecta a la base de datos, ejecuta una consulta, obtiene los metadatos del conjunto de resultados, crea un
     * modelo de tabla, agrega las columnas al modelo de tabla, agrega las filas al modelo de tabla, cierra el conjunto de
     * resultados y la conexión, y devuelve el modelo de la tabla
     *
     * @return Un modelo de tabla.
     */
    public DefaultTableModel consultar() {
        DefaultTableModel plantilla = new DefaultTableModel();
        ConexionBD con = new ConexionBD();
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();
            ResultSet datos = consulta.executeQuery("SELECT * from clientes;");
            ResultSetMetaData campos = datos.getMetaData();
            
            for (int i = 1; i <= campos.getColumnCount(); i++) {
                plantilla.addColumn(campos.getColumnName(i));
            }
            
            while (datos.next()) {
                Object fila[] = new Object[campos.getColumnCount()];
                for (int i = 0; i < campos.getColumnCount(); i++) {
                    fila[i] = datos.getObject(i + 1);
                }
                plantilla.addRow(fila);
            }
            
            datos.close();
            con.getConexion().close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        return plantilla;
    }
    
    /**
     * Metodo Insertar el cual esta encargado de insertar los datos de la clase clientes a la base de datos 
     * y que retorna un String con la confirmacion de la insercion
     * @return String
     */
    public String insertar() {
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into clientes values(?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objC.getCedula());
            consulta.setString(2, objC.getNom());
            consulta.setString(3, objC.getTel());
            consulta.execute();
            mensaje = "Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar...\n" + ex;
        }
        return mensaje;
    }

    /**
     * Metodo actualizar el cual esta encargado de la tarea de actualizar los datos de la base de datos, comparandolos con los datos de la tabla clientes
     * y que retorna un String con la confirmacion de la actualizacion
     * @return String
     */
    public String actualizar(){
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "update clientes set id=?,nombre=?,telefono=?"
                            + " where id='"+objC.getCedula()+"'";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objC.getCedula());
            consulta.setString(2, objC.getNom());
            consulta.setString(3, objC.getTel());
            consulta.execute();
            mensaje = "Actualizacion exitosa...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar actualizar...\n" + ex;
        }
        return mensaje;
    }
    
    /**
     * Metodo eliminar el cual esta encargado de la tarea de eliminar los datos de la base de datos, comprandolos con la fila seleccionada
     * y que retorna un String con la confirmacion de la eliminacion
     * @return String
     */
    public String eliminar(){
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "delete from clientes"
                            + " where id='"+objC.getCedula()+"'";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.execute();
            
            if(consulta != null){
                mensaje = "Eliminación exitosa...";
            }
            
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar...\n" + ex;
        }
        return mensaje;
    }
    
    /**
     * Esta función devuelve el objeto de la clase Cliente
     *
     * @return El objeto objC.
     */
    public Cliente getObjC() {
        return objC;
    }

    /**
     * La función setObjC() es una función pública que toma un objeto Cliente como parámetro y establece la variable objC
     * en el parámetro
     *
     * @param objC Este es el objeto que se utilizará para almacenar los datos que se recuperarán de la base de datos.
     */
    public void setObjC(Cliente objC) {
        this.objC = objC;
    }
    
    @Override
    public String toString() {
        return objC.toString();
    }

}
