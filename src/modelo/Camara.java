package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase abstracta que representa camara, tiene como atributos 
 * id (String), marca (String), lente (String) y precio (Double)
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public abstract class Camara {

    /**
     * Representacion del id de una camara.
     */
    protected String id,

    /**
     * Representacion de la marca de una camara.
     */
    marca,

    /**
     * Representacion del lente de una camara.
     */
    lente;

    /**
     * Representacion del precio de una camara.
     */
    protected double precio;
    
    /**
     * Constructor basico, crea una instancia de Camara con sus atributos nulos
     */
    public Camara() {
        this.id = "";
        this.marca = "";
        this.lente = "";
        this.precio =0;
    }

    /**
     * Constructor parametrico, crea una instancia de Camara en base a los siguientes parametros
     * @param id
     * @param marca
     * @param lente
     * @param precio
     */
    public Camara(String id, String marca, String lente, double precio) {
        this.id = id;
        this.marca = marca;
        this.lente = lente;
        this.precio = precio;
    }
    
    /**
     * Metodo abstracto que calcula el descuento que tiene cada camara
     * @return
     */
    public abstract double descuento();
    
    /**
     * Getter que retorna el valor de ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter que modifica el valor de ID
     * @param id
     * @throws modelo.ExcepcionPersonalizada 101
     */
    public void setId(String id) throws ExcepcionPersonalizada {
        if (id.equals("") ) {
            throw new ExcepcionPersonalizada(101);
        }else{
            this.id = id;
        }
    }

    /**
     * Getter que retorna el valor de marca
     * @return
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Setter que modifica el valor de marca
     * @param marca
     * @throws modelo.ExcepcionPersonalizada 101 102
     */
    public void setMarca(String marca) throws ExcepcionPersonalizada{
        Pattern pat = Pattern.compile("[0-9]");
        Matcher mat = pat.matcher(marca);
        if (marca.equals("") ) {
            throw new ExcepcionPersonalizada(101);
        }else if(mat.find()){
            throw new ExcepcionPersonalizada(102,marca);
        }else{        
            this.marca = marca;
        }
    }

    /**
     * Getter que retorna el valor de lente
     * @return String
     */
    public String getLente() {
        return lente;
    }

    /**
     * Setter que modifica el valor de lente
     * @param lente
     * @throws modelo.ExcepcionPersonalizada
     */
    public void setLente(String lente) throws ExcepcionPersonalizada{
        if (lente.equals("") ) {
            throw new ExcepcionPersonalizada(101);
        }else{        
            this.lente = lente;
        }
    }

    /**
     * Getter que retorna el valor de precio
     * @return double
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Setter que modifica el valor de precio
     * @param precio
     */
    public void setPrecio(double precio){       
            this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Marca: " + marca + " Lente: " + lente + " Precio: "+precio;
    }

    /**
     * Metodo que retorna los atributos de Camara en formato CSVgit 
     * @return
     */
    public String datos(){
        return id + ";" + marca + ";" + lente + ";" + precio;
    }
}
