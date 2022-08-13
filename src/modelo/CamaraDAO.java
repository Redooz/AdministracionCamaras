package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CamaraDAO {
    private Camara objC;

    public CamaraDAO(Camara objC) {
        this.objC = objC;
    }

    public CamaraDAO() {
        this.objC = null;
    }

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

    public Camara getObjC() {
        return objC;
    }

    public void setObjC(Camara objC) {
        this.objC = objC;
    }
    
    
    @Override
    public String toString() {
        return objC.toString();
    }

}
