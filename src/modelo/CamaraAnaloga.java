package modelo;

/**
 * Clase que representa una camara analoga, tiene como atributos 
 * id (String), marca (String), lente (String), precio (Double), rollo (String)
 * y visor (String)
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class CamaraAnaloga extends Camara{
    private String rollo, visor;

    /**
     * Constructor basico, crea una instancia de Camara analoga con sus atributos nulos
     */
    public CamaraAnaloga() {
        this.rollo = "";
        this.visor = "";
    }
    
    /**
     * Constructor parametrico, crea una instancia de Camara Analoga en base a los siguientes parametros
     * @param rollo
     * @param visor
     * @param id
     * @param marca
     * @param lente
     * @param precio
     */
    public CamaraAnaloga(String rollo, String visor, String id, String marca, String lente, double precio) {
        super(id, marca, lente, precio);
        this.rollo = rollo;
        this.visor = visor;
    }

    /**
     * Metodo que calcula el descuento que tiene la camara analoga, el cual es del 10%
     * @return
     */
    @Override
    public double descuento(){
        return precio*0.1;
    }
    
    /**
     * Getter que retorna el valor de rollo
     * @return String
     */
    public String getRollo() {
        return rollo;
    }

    /**
     * Setter que modifica el valor de rollo
     * @param rollo
     * @throws modelo.ExcepcionPersonalizada
     */
    public void setRollo(String rollo) throws ExcepcionPersonalizada{
        if (rollo.equals("")) {
            throw new ExcepcionPersonalizada(101);
        }else{
            this.rollo = rollo;
        }
    }

    /**
     * Getter que retorna el valor de visor
     * @return String
     */
    public String getVisor() {
        return visor;
    }

    /**
     * Setter que modifica el valor de visor
     * @param visor
     * @throws modelo.ExcepcionPersonalizada
     */
    public void setVisor(String visor) throws ExcepcionPersonalizada {
        if (visor.equals("")) {
            throw new ExcepcionPersonalizada(101);
        }else{
            this.visor = visor;
        }
    }

    @Override
    public String toString() {
        return "Camara Analoga "+ super.toString()+ " Rollo: " + rollo + " Visor: " + visor;
    }
    
    /**
     * Metodo que retorna los atributos de Camara Analoga en formato CSV
     * @return String
     */
    @Override
    public String datos(){
        return super.datos() + ";" + rollo + ";" + visor + ";--;--";
    }
    
}
