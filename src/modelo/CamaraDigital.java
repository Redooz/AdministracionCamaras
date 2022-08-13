/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UD
 */
public class CamaraDigital extends Camara{
    private String memoria, pantalla;

    public CamaraDigital() {
        this.memoria = "";
        this.pantalla = "";
    }

    public CamaraDigital(String memoria, String pantalla, String id, String marca, String lente, double precio) {
        super(id, marca, lente, precio);
        this.memoria = memoria;
        this.pantalla = pantalla;
    }

    @Override
    public double descuento(){
        return precio*0.05;
    }
    
    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getPantalla() {
        return pantalla;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public String toString() {
        return "Camara Digital " + super.toString()+ " Tamanio de memoria: " + memoria + " Tamanio de pantalla: " + pantalla;
    }
    @Override
    public String datos(){
        return super.datos()+ ";--;--" + ";" + memoria + ";" + pantalla;
    }
    
}
