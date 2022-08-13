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

    /*public String insertar() {
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            Statement consulta = null;
            conexion.conectar();
            String comando = "insert into productos values('" + objF.getCodigo()
                    + "','" + objF.getNombre()+ "'," + objF.getPrecio()+ "," + objF.getCantidad() +")";
            consulta = conexion.getConexion().createStatement();
            consulta.execute(comando);
            mensaje = "Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar...\n" + ex;
        }
        return mensaje;
    }

    public String insertar2() {
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into productos values(?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objF.getCodigo());
            consulta.setString(2, objF.getNombre());
            consulta.setDouble(3, objF.getPrecio());
            consulta.setInt(4, objF.getCantidad());
            consulta.execute();
            mensaje = "Registro exitoso...";
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            mensaje = "Error al intentar insertar...\n" + ex;
        }
        return mensaje;
    }*/

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
