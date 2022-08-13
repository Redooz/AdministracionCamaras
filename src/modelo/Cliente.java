/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Es una clase que representa a un cliente, con atributos cedula, nom y tel.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class Cliente {
    private String cedula, nom,tel;

    /**
     * Un constructor basico, crea una instancia de Cliente con sus atributos nulos
     * */
    public Cliente() {
        this.cedula = "";
        this.nom = "";
        this.tel = "";
    }

    /**
     * Un constructor parametrico, crea una instancia de Cliente con los parametros:
     * @param cedula
     * @param nom
     * @param tel
     * */
    public Cliente(String cedula, String nom, String tel) {
        this.cedula = cedula;
        this.nom = nom;
        this.tel = tel;
    }

    /**
     * Esta funcion devuelve el valor de la variable cedula
     *
     * @return La variable cédula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Esta función establece el valor de la variable cedula al valor del parámetro cedula
     *
     * @param cedula El número de identificación de la persona.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Esta función devuelve el valor de la variable `nom`
     *
     * @return el nombre de la persona
     */
    public String getNom() {
        return nom;
    }

    /**
     * Esta función establece el valor de la variable nom al valor del parámetro nom
     *
     * @param nom El nombre del parámetro.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Esta función devuelve la variable tel
     *
     * @return Se devuelve la variable tel.
     */
    public String getTel() {
        return tel;
    }

    /**
     * > Esta función establece el atributo tel de la clase al valor del parámetro tel
     *
     * @param tel El número de teléfono del usuario.
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula + " Nombre: " + nom + " Telefono: " + tel;
    }

    /**
     * La función datos() devuelve un String que es la concatenación de los valores de los atributos cedula, nom y tel,
     * separados por punto y coma
     *
     * @return El método datos() devuelve un String con los valores de las variables cedula, nom y tel.
     */
    public String datos() {
        return cedula + ";" + nom + ";" + tel;
    }
    
}
