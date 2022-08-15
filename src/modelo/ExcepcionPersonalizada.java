/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Daniel Garcia
 */
public class ExcepcionPersonalizada extends Exception{
    private int nro;
    private String msj;
    
    public ExcepcionPersonalizada (int nro) {
        this.nro = nro;
        switch(nro){
            case 101:
                msj="No se admiten valores nulos...";
                break;
            case 102:
                msj="No se admiten valores numericos...";
                break;
            case 201:
                msj="En el campo de rollo debe ingresar una de estas opciones: B/N, sepia o color...";
                break;
            case 301:
                msj="En el campo de fecha se debe poner el siguiente formato 'año-mes-dia'...";
                break;
            default:
                msj = "Error no identificado...";
                break;
        }
    }
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

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    @Override
    public String toString() {
        return "ExcepcionPersonalizada: " + nro + ": "+ msj;
    }

    @Override
    public String getMessage() {
        return "Error:" + nro + ": "+ msj;
    }
}
