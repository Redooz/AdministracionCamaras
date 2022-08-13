package modelo;

/**
 * Clase que representa una camara digital, tiene como atributos 
 * id (String), marca (String), lente (String), precio (Double), memoria (String)
 * y pantalla (String)
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class CamaraDigital extends Camara{
    private String memoria, pantalla;

    /**
     * Constructor basico, crea una instancia de Camara analoga con sus atributos nulos
     */
    public CamaraDigital() {
        this.memoria = "";
        this.pantalla = "";
    }

    /**
     * Un constructor que recibe los atributos de la clase y los atributos de la superclase.
     */
    public CamaraDigital(String memoria, String pantalla, String id, String marca, String lente, double precio) {
        super(id, marca, lente, precio);
        this.memoria = memoria;
        this.pantalla = pantalla;
    }

    /**
     * La función `descuento()` devuelve el valor del precio del producto multiplicado por 0,05
     *
     * @return El precio del producto con un 5% de descuento.
     */
    @Override
    public double descuento(){
        return precio*0.05;
    }
    
    /**
     * Esta función devuelve el valor de la variable memoria
     *
     * @return El valor de la variable memoria.
     */
    public String getMemoria() {
        return memoria;
    }

    /**
     * Esta función establece el valor de la variable memoria al valor del parámetro memoria
     *
     * @param memoria La memoria del dispositivo.
     */
    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    /**
     * Esta función devuelve el valor de la variable pantalla
     *
     * @return El valor de la variable pantalla.
     */
    public String getPantalla() {
        return pantalla;
    }

    /**
     * Esta función establece el valor de la variable pantalla al valor del parámetro pantalla
     *
     * @param pantalla El nombre de la pantalla que desea mostrar.
     */
    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    /**
     * El método toString() retorna una representación en formato String del objeto
     *
     * @return Se devuelve el método toString.
     */
    @Override
    public String toString() {
        return "Camara Digital " + super.toString()+ " Tamanio de memoria: " + memoria + " Tamanio de pantalla: " + pantalla;
    }

    /**
     * La función datos() retorna una representación en formato CSV del objeto
     *
     * @return String.
     */
    @Override
    public String datos(){
        return super.datos()+ ";--;--" + ";" + memoria + ";" + pantalla;
    }
    
}
