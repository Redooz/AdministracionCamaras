package modelo;

/**
 * Un objeto `Factura` tiene un `codigo`, un objeto `Cliente`, un objeto `Camara` y un objeto `Fecha`
 */
public class Factura {
    private String codigo;
    private Cliente objCli;
    private Camara objCam;
    private Fecha objFCom;
    /**
     * Constructor basico que realiza una instancia de Factura con valores nulos
     * */
    public Factura() {
        this.codigo = "";
        this.objCli = new Cliente();
        this.objCam = null;
        this.objFCom = new Fecha();
    }

    /**
     * Constructor parametrico que realiza una instancia de Factura haciendo uso de
     * los siguientes parametros:
     * @param codigo
     * @param objCli
     * @param objCam
     * @param objFCom
     * */
    public Factura(String codigo, Cliente objCli, Camara objCam, Fecha objFCom) {
        this.codigo = codigo;
        this.objCli = objCli;
        this.objCam = objCam;
        this.objFCom = objFCom;
    }

    /**
     * Si el día de la semana es miércoles, devuelva el precio de la cámara menos el descuento. De lo contrario, devolver
     * el precio de la cámara.
     *
     * @return El precio del billete.
     */
    public double precioTotal(){
        if (objFCom.diaSemanaDesdeAtributos() == 1) { //Miercoles = 1
            return objCam.getPrecio()-objCam.descuento();
        } else {
            return objCam.getPrecio();
        }
    }
    
    /**
     * Esta función devuelve el valor de la variable codigo
     *
     * @return El valor de la variable código.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Esta función establece el valor de la variable de código al valor del parámetro de código
     *
     * @param codigo El código del producto.
     */
    /**
     * Esta función establece el valor de la variable de código al valor del parámetro de código
     *
     * @param codigo El código del producto.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Esta función devuelve el objeto de la clase Cliente
     *
     * @return El objeto objCli.
     */
    public Cliente getObjCli() {
        return objCli;
    }

    /**
     * Esta función establece el valor de la variable privada objCli al valor del parámetro objCli
     *
     * @param objCli Este es el objeto que se utilizará para almacenar los datos que se enviarán al servidor.
     */
    public void setObjCli(Cliente objCli) {
        this.objCli = objCli;
    }

    /**
     * Esta función devuelve el objeto de la clase Camara
     *
     * @return Se devuelve el objeto objCam.
     */
    public Camara getObjCam() {
        return objCam;
    }

    /**
     * Esta función establece el valor de la variable privada objCam al valor del parámetro objCam
     *
     * @param objCam El objeto de cámara que se usará para renderizar la escena.
     */
    public void setObjCam(Camara objCam) {
        this.objCam = objCam;
    }

    /**
     * Esta función devuelve el valor de la variable privada objFCom
     *
     * @return Se devuelve el objeto objFCom.
     */
    public Fecha getObjFCom() {
        return objFCom;
    }

    /**
     * La función setObjFCom() establece el valor de la variable privada objFCom al valor del parámetro objFCom
     *
     * @param objFCom El objeto que contiene la fecha.
     */
    public void setObjFCom(Fecha objFCom) {
        this.objFCom = objFCom;
    }

    @Override
    public String toString() {
        return "\nCodigo: " + codigo + "\nCliente:" + objCli.toString() + "\nCamara: " + objCam.toString() + "\nFecha: " + objFCom.toString();
    }
    
    /**
     * La función datos() devuelve un string con el código, los datos del cliente, los datos de la cámara y el precio total
     * de la venta en formato CSV
     *
     * @return El método datos() está devolviendo el código, los datos del cliente, los datos de la cámara y el precio
     * total.
     */
    public String datos(){
        return codigo + ";" + objCli.datos() + ";" + objCam.datos() + ";" + precioTotal();
    }
}
