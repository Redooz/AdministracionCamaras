package modelo;

/**
 * La clase ExcepcionPersonalizada, es la clase encargada de las exceciones creadas para el proyecto en particular
 * @author Daniel Garcia
 * @author Nicolas Olmos
 */
public class ExcepcionPersonalizada extends Exception{
    /**
     * Atributo nro hace referencia del codigo de error 
     */
    private int nro;
    /**
     * Atrubuto msj hace referencia del mensaje de error
     */
    private String msj;
    
    /**
     * Contructor parametrico el cual recibe un parametro nro (Codigo de error), identifica con el numero de error un mensaje personalizado, para referirse al error concreto 
     * @param nro
     */
    public ExcepcionPersonalizada (int nro) {
        this.nro = nro;
        switch(nro){
            case 101: // nro 100 para errores de valores ingresados
                msj="No se admiten valores nulos...";
                break;
            case 102: // nro 100 para errores de valores ingresados
                msj="No se admiten valores numericos...";
                break;
            case 201: // nro 200 para errorescon las opciones de camaras ingresadas
                msj="En el campo de rollo debe ingresar una de estas opciones: B/N, sepia o color...";
                break;
            case 301: // nro 300 para errores con las fechas
                msj="En el campo de fecha se debe poner el siguiente formato 'año-mes-dia'...";
                break;
            default:
                msj = "Error no identificado...";
                break;
        }
    }

    /**
     * Contructor parametrico encargado de asignar un mensaje teniendo encuenta el numero (codigo de error) ingresado y aladiendo datos de informacion, para ser mas preciso
     * @param nro 
     * @param info
     */
    public ExcepcionPersonalizada(int nro,String info) {
        this.nro = nro;
        switch(nro){
            case 101:
                msj="No se admiten valores nulos... : generado por: " + info;
                break;
            case 102:
                msj="No se admiten valores numericos... : generado por: " + info;
                break;
            case 201:
                msj="En el campo de rollo debe ingresar una de estas opciones: B/N, sepia o color... : generado por:" + info;
                break;
            case 202:
                msj="En el campo de Visor debe ingresar una de estas opciones: optico o reflex... : generado por:" + info;
                break;
            default:
                msj = "Error no detectado... : generado por: " + info;
                break;
        }
    }

    /**
     * Getter que retorna el valor de nro en formato int 
     * @return int
     */
    public int getNro() {
        return nro;
    }

    /**
     * Setter que modifica el valor de nro, teniendo en cuenta el parametro recibido de tipo int
     * @param nro
     */
    public void setNro(int nro) {
        this.nro = nro;
    }

    /**
     * Getter que retorna el valor de msj en formato String
     * @return String
     */
    public String getMsj() {
        return msj;
    }

    /**
     * Setter que modifica el valor de msj, teniendo en cuenta el parametro recibido de tipo String
     * @param msj
     */
    public void setMsj(String msj) {
        this.msj = msj;
    }

    /**
     * Metodo que retorna un texto con la concatenacion de los datos de nro (int) y msj (String) junto con el nombre de la clase, todo esto en formato String
     * @return String
     */
    @Override
    public String toString() {
        return "ExcepcionPersonalizada: " + nro + ": "+ msj;
    }

    /**
     *Metodo que retorna un texto con la concatenacion de los datos de nro (int) y msj (String) legible para el usuario, todo esto en formato String
     * @return String
     */
    @Override
    public String getMessage() {
        return "Error:" + nro + ": "+ msj;
    }
}
