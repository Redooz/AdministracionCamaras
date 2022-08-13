package modelo;

import java.util.ArrayList;

/**
 * Clase que representa una lista de facturas, tiene como atributos arrayFac (ArrayList)
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class ArrayListFactura {
    private ArrayList<Factura> arrayFac;

    /**
     * Constructor basico, crea una instancia de ArrayListFactura
     */
    public ArrayListFactura() {
        arrayFac = new ArrayList<>();
    }

    /**
     * Constructor parametrico, crea una instancia de ArrayListFactura 
     * a partir de los siguientes parametros
     * @param arrayFac
     * @param fecha
     */
    public ArrayListFactura(ArrayList<Factura> arrayFac, Fecha fecha) {
        this.arrayFac = arrayFac;
    }

    /**
     * Getter que retorna el contenido de ArrayFac
     * @return
     */
    public ArrayList<Factura> getArrayFac() {
        return arrayFac;
    }

    /**
     * Setter que modifica el contenido de ArrayFac
     * @param arrayFac
     */
    public void setArrayFac(ArrayList<Factura> arrayFac) {
        this.arrayFac = arrayFac;
    }
    
    /**
     * Metodo que calcula y retorna el total de los pagos, acumulando el precio total de cada factura
     * @return double
     */
    public double totalPagos(){
        double total = 0;
        
        for (int i = 0; i < arrayFac.size(); i++) {
            total += arrayFac.get(i).precioTotal();
        }
        
        return total;
    }
 
    /**
     * Metodo que retorna el contenido de la lista de facturas en formato (String)
     * @return String
     */
    public String datos(){
        String total = "";
        for (int i = 0; i < arrayFac.size(); i++) {
            String tipoC;
            if(arrayFac.get(i).getObjCam() instanceof CamaraDigital){
                tipoC = "Camara Digital";
            }else{
                tipoC = "Camara Analoga";
            }
            total += "\nCamara: "+ tipoC + arrayFac.get(i).toString() + "\n\n";
        }
        
        return total;
    }

    /**
     * Metodo que retorna la cantidad de camaras digitales dentro de todas las facturas
     * @return int
     */
    public int cantidadCamarasDigitales(){
        int cantidad = 0;
        
        for (int i = 0; i < arrayFac.size(); i++) {
            if(arrayFac.get(i).getObjCam() instanceof CamaraDigital){
                cantidad++;
            }
        }
        
        return cantidad;
    }
    
    @Override
    public String toString() {
        return "Lista Factura: " + this.arrayFac.toString();
    }
}
