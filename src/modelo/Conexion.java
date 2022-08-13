package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * La clase conexion permite la lectura y escritura en un archivo.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class Conexion {
    private BufferedReader bufEntrada;
    private FileReader flujoLee;
    private FileWriter flujoEscr;
    private PrintWriter bufSalida;

    public Conexion(BufferedReader bufEntrada, FileReader flujoLee, FileWriter flujoEscr, PrintWriter bufSalida) {
        this.bufEntrada = bufEntrada;
        this.flujoLee = flujoLee;
        this.flujoEscr = flujoEscr;
        this.bufSalida = bufSalida;
    }
    
    public Conexion() {
        this.bufEntrada = null;
        this.flujoLee = null;
        this.flujoEscr = null;
        this.bufSalida = null;
    }
    
    /**
     * La función lee el archivo y devuelve los datos en un (String)
     *
     * @return El método está devolviendo una cadena.
     * @throws IOException
     */
    public String leerDatos() throws IOException{
        this.flujoLee= new FileReader("camaras.txt");
        bufEntrada= new BufferedReader(flujoLee);
        String datos = "";
        String linea = this.bufEntrada.readLine();
        
        while (linea != null){
            datos+=linea+"\n";
            linea=this.bufEntrada.readLine();
        }
        
        JOptionPane.showMessageDialog(null, "Se ha leido el archivo con exito","Lectura",JOptionPane.INFORMATION_MESSAGE);
        bufEntrada.close();
        return datos;
    }
    
    /**
     * La función recibe un String, crea un nuevo FileWrite, crea un nuevo PrintWriter, imprime el String
     * en el archivo y cierra el archivo.
     *
     * @param datos Los datos que se escribirán en el archivo.
     * @throws IOException
     */
    public void escribeDatos(String datos) throws IOException{
        flujoEscr= new FileWriter("camaras.txt", true);
        bufSalida= new PrintWriter(flujoEscr);
        
        bufSalida.println(datos);
        bufSalida.close();
    }
    
}
