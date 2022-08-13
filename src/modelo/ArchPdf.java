package modelo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
//archivos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//librerias ajenas a itext
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ArchPDF es una clase usada para la generación de archivos en formato PDF, 
 * cuenta con dos atributos, ruta_destino (File) y mipdf (Document)
 * 
 * @author Multi
 */
public class ArchPdf {

    private File ruta_destino;

    /**
     * Constructor basico, crea una instancia ArchPdf con atributos en nulo
     */
    public ArchPdf(){
        ruta_destino = null;
    }

    /**
     * El Metodo crear_PDF permite crear un nuevo archivo PDF con los datos que se le suministren
     * @param t corresponde al titulo del documento
     * @param p corresponde al contenido del documento
     */
    public void crear_PDF(String t, String p){
        colocar_Destino();
        if(this.ruta_destino!=null){
            try {
                Document mipdf = new Document();
                PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));
                mipdf.open();// se abre el documento
                mipdf.addTitle(t); // se añade el titulo
                mipdf.add(new Paragraph(p+"\n"));
                mipdf.close(); //se cierra el PDF&
                JOptionPane.showMessageDialog(null,"Documento PDF creado");
                abrirPdf(this.ruta_destino.getPath()+".pdf");
            } catch (DocumentException | FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    /**
     * El metodo generar, genera un pdf y además lo guarda en un lugar especifico
     * @param nombre corresponde al nombre del archivo pdf
     * @param datos datos que van a estar en el archivo pdf
     */
    public void generar(String nombre,String datos){
        try {
            FileOutputStream archivo = new FileOutputStream(nombre+".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            
            Paragraph parrafo = new Paragraph("Datos de las camaras");
            parrafo.setAlignment(1);
            documento.add(parrafo);
            
            documento.add(new Paragraph(datos));
            documento.close();
            abrirPdf(nombre+".pdf");
        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    /**
     * Metodo que permite al usuario guardar el archivo en la ubicación deseada
     */
    public void colocar_Destino(){
       FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
        }
    }

    /**
     * El metodo abrir PDF permite abrir un documento mediante su ruta destino
     * @param path variable que contiene la ruta destino del archivo a abrir
     */
    public void abrirPdf(String path){
        try {
            System.out.println(path);
            File pdf = new File(path);
            Desktop.getDesktop().open(pdf);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    /**
     * Metodo getter que retorna la ruta del destino
     * @return ruta_destino (File)
     */
    public File getRuta_destino() {
        return ruta_destino;
    }

    /**
     * Metodo setter que modifica la ruta del destino
     * @param ruta_destino
     */
    public void setRuta_destino(File ruta_destino) {
        this.ruta_destino = ruta_destino;
    }
    
}