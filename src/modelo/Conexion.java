package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

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
    
    public void escribeDatos(String datos) throws IOException{
        flujoEscr= new FileWriter("camaras.txt", true);
        bufSalida= new PrintWriter(flujoEscr);
        
        bufSalida.println(datos);
        bufSalida.close();
    }
    
}
