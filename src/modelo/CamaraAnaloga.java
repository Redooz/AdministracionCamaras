/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UD
 */
public class CamaraAnaloga extends Camara{
    private String rollo, visor;

    public CamaraAnaloga() {
        this.rollo = "";
        this.visor = "";
    }

    public CamaraAnaloga(String rollo, String visor) {
        this.rollo = rollo;
        this.visor = visor;
    }

    public CamaraAnaloga(String rollo, String visor, String id, String marca, String lente, double precio) {
        super(id, marca, lente, precio);
        this.rollo = rollo;
        this.visor = visor;
    }

    @Override
    public double descuento(){
        return precio*0.1;
    }
    
    public String getRollo() {
        return rollo;
    }

    public void setRollo(String rollo) {
        this.rollo = rollo;
    }

    public String getVisor() {
        return visor;
    }

    public void setVisor(String visor) {
        this.visor = visor;
    }

    @Override
    public String toString() {
        return "Camara Analoga "+ super.toString()+ " Rollo: " + rollo + " Visor: " + visor;
    }
    @Override
    public String datos(){
        return super.datos() + ";" + rollo + ";" + visor + ";--;--";
    }
    
}
