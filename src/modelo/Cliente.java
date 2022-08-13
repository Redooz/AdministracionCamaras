/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author UD
 */
public class Cliente {
    private String cedula, nom,tel;

    public Cliente() {
        this.cedula = "";
        this.nom = "";
        this.tel = "";
    }
    public Cliente(String cedula, String nom, String tel) {
        this.cedula = cedula;
        this.nom = nom;
        this.tel = tel;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula + " Nombre: " + nom + " Telefono: " + tel;
    }

    public String datos() {
        return cedula + ";" + nom + ";" + tel;
    }
    
}
