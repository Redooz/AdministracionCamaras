package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Es una clase que se conecta a una base de datos y recupera datos de la tabla facturas.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class FacturaDAO {
    private Factura objF;

    /**
     * Constructor parametrico que realiza una instancia de FacturaDAO con el parametro:
     * @param objP
     * */
    public FacturaDAO(Factura objP) {
        this.objF = objP;
    }

    /**
     * Constructor basico que realiza una instancia de FacturaDAO con valores nulos
     * */
    public FacturaDAO() {
        this.objF = new Factura();
    }

    /**
     * Crea un modelo de tabla, se conecta a la base de datos, crea una declaración, ejecuta una consulta, obtiene los
     * metadatos del conjunto de resultados, agrega las columnas al modelo de tabla, agrega las filas al modelo de tabla,
     * cierra el conjunto de resultados y la conexión y devuelve el modelo de tabla
     *
     * @return Un modelo de tabla.
     */
    public DefaultTableModel consultar() {
        DefaultTableModel plantilla = new DefaultTableModel();
        ConexionBD con = new ConexionBD();
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();
            ResultSet datos = consulta.executeQuery("SELECT facturas.codigo,facturas.fecha,clientes.id,clientes.nombre,clientes.telefono,camaras.id,camaras.marca,camaras.lente,camaras.precio,camaras.rollo,camaras.visor,camaras.memoria,camaras.pantalla,facturas.precio_total FROM facturas NATURAL JOIN clientes,camaras \n" +
"WHERE facturas.id_cliente = clientes.id and facturas.id_camara = camaras.id;");
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
     * Metodo Insertar el cual esta encargado de insertar los datos de la clase factura a la base de datos 
     * y que retorna un String con la confirmacion de la insercion
     * @return String
     */
    public String insertar() {
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into facturas values(?,?,?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objF.getCodigo());
            consulta.setString(2, objF.getObjFCom().toString());
            consulta.setString(3, objF.getObjCli().getCedula());
            consulta.setString(4, objF.getObjCam().getId());
            consulta.setDouble(5, objF.precioTotal());
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
     * Metodo actualizar el cual esta encargado de la tarea de actualizar los datos de la base de datos, comparandolos con los datos de la tabla facturas
     * y que retorna un String con la confirmacion de la actualizacion
     * @return String
     */
    public String actualizar(){
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "update facturas set codigo=?,fecha=?,id_cliente=?,id_camara=?,precio_total=?"
                            + " where codigo='"+objF.getCodigo()+"'";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objF.getCodigo());
            consulta.setString(2, objF.getObjFCom().toString());
            consulta.setString(3, objF.getObjCli().getCedula());
            consulta.setString(4, objF.getObjCam().getId());
            consulta.setDouble(5, objF.precioTotal());
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
            String comando = "delete from facturas"
                            + " where codigo='"+objF.getCodigo()+"'";
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
     * Esta función devuelve el valor de la variable objF
     *
     * @return Se devuelve el objeto objF.
     */
    public Factura getObjF() {
        return objF;
    }

    /**
     * La función setObjF() es una función pública que toma un parámetro de tipo Factura y no devuelve nada
     *
     * @param objF El objeto que se utilizará para almacenar los datos de la base de datos.
     */
    public void setObjF(Factura objF) {
        this.objF = objF;
    }
    
    @Override
    public String toString() {
        return objF.toString();
    }

}
