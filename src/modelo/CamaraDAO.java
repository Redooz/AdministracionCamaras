package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase se utiliza para conectarse a la base de datos y realizar operaciones CRUD en la tabla Camaras.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class CamaraDAO {
    private Camara objC;

    /**
     * Un constructor que recibe un objeto Camara como parámetro.
     */
    public CamaraDAO(Camara objC) {
        this.objC = objC;
    }

    /**
     * Un constructor que inicializa el objeto `objC` a `null`.
     */
    public CamaraDAO() {
        this.objC = null;
    }

    /**
     * Se conecta a la base de datos, ejecuta una consulta y devuelve el resultado en un modelo de tabla
     *
     * @return Un modelo de tabla.
     */
    public DefaultTableModel consultar() {
        DefaultTableModel plantilla = new DefaultTableModel();
        ConexionBD con = new ConexionBD();
        try {
            con.conectar();
            Statement consulta = con.getConexion().createStatement();
            ResultSet datos = consulta.executeQuery("SELECT * from camaras;");
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
     * Se conecta a la base de datos, ejecuta un insert en camaras y devuelve el resultado en un String
     *
     * @return String
     */
    public String insertar() {
        String mensaje = "";
        try {
            ConexionBD conexion = new ConexionBD();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = "insert into camaras values(?,?,?,?,?,?,?,?)";
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objC.getId());
            consulta.setString(2, objC.getMarca());
            consulta.setString(3, objC.getLente());
            consulta.setDouble(4, objC.getPrecio());
            
            if(objC instanceof CamaraAnaloga){
                CamaraAnaloga objCA = (CamaraAnaloga) objC;
                consulta.setString(5, objCA.getRollo());
                consulta.setString(6, objCA.getVisor());
                consulta.setString(7, null);
                consulta.setString(8, null);
            } else if(objC instanceof CamaraDigital){
                CamaraDigital objCD = (CamaraDigital) objC;
                consulta.setString(5, null);
                consulta.setString(6, null);
                consulta.setString(7, objCD.getMemoria());
                consulta.setString(8, objCD.getPantalla());
            }
            
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
     * Esta función devuelve el objeto de la clase Camara
     *
     * @return El objeto objC.
     */
    public Camara getObjC() {
        return objC;
    }

    /**
     * La función setObjC() es una función pública que toma un objeto Camara como parámetro y establece la variable objC en
     * el parámetro
     *
     * @param objC Este es el objeto de la clase Camara.
     */
    public void setObjC(Camara objC) {
        this.objC = objC;
    }
    
    
    @Override
    public String toString() {
        return objC.toString();
    }

}
