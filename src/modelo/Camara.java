/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UD
 */
public abstract class Camara {
    protected String id, marca, lente;
    protected double precio;
    
    public Camara() {
        this.id = "";
        this.marca = "";
        this.lente = "";
        this.precio =0;
    }
    public Camara(String id, String marca, String lente, double precio) {
        this.id = id;
        this.marca = marca;
        this.lente = lente;
        this.precio = precio;
    }
    
    public abstract double descuento();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLente() {
        return lente;
    }

    public void setLente(String lente) {
        this.lente = lente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Marca: " + marca + " Lente: " + lente + " Precio: "+precio;
    }
    public String datos(){
        return id + ";" + marca + ";" + lente + ";" + precio;
    }
}
