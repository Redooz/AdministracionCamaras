
package modelo;

import java.util.Calendar;
import java.util.Date;


/**
 * La clase Fecha es una clase que representa una fecha. Tiene un constructor que toma tres enteros como parámetros y otro
 * constructor que toma cuatro enteros como parámetros. También tiene un constructor que no toma parámetros y establece la
 * fecha en la fecha actual. Tiene un método llamado antigüedad que devuelve el número de años desde la fecha representada
 * por el objeto. Tiene un método llamado diaSemanaDesdeAtributos que devuelve el día de la semana de la fecha representada
 * por el objeto. Tiene getters y setters para el día, mes, año y día de la semana. Tiene un método toString que devuelve
 * una representación de cadena de la fecha.
 * @author Nicolás Olmos
 * @author Daniel Garcia
 */
public class Fecha {
    private int dia ,mes,anio,diaSemana;
    
     public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public Fecha(int dia, int mes, int anio, int diaSemana) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.diaSemana = diaSemana;
    }
    
    public Fecha() {
        Calendar Fecha=Calendar.getInstance();
        this.dia = Fecha.get(Calendar.DAY_OF_MONTH);
        this.mes = Fecha.get(Calendar.MONTH+1);
        this.anio = Fecha.get(Calendar.YEAR);
        this.diaSemana = Fecha.get(Calendar.DAY_OF_WEEK); //Miercoles = 4
    }
    
    /**
     *  Esta función devuelve la cantidad de años transcurridos
     *
     * @return La diferencia entre el año actual y el año del objeto.
     */
    public int antiguedad(){
        Calendar Fecha = Calendar.getInstance();
        return Fecha.get(Calendar.YEAR)-this.anio;   
    }

    /**
     * Devuelve el día de la semana de la fecha representada por los atributos del objeto
     *
     * @return El día de la semana.
     */
    public int diaSemanaDesdeAtributos(){
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date(getAnio(),getMes(),getDia()));
        return fecha.get(Calendar.DAY_OF_WEEK); 
    }
    
    /**
     * Esta función devuelve el valor de la variable dia
     *
     * @return El valor de la variable dia.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Esta función establece el valor de la variable dia al valor del parámetro dia.
     *
     * @param dia El día del mes.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Esta función devuelve el mes del año
     *
     * @return El valor de la variable mes.
     */
    public int getMes() {
        return mes;
    }

    /**
     * Esta función establece el mes de la fecha.
     *
     * @param mes El mes del año.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * > Esta función devuelve el valor de la variable anio
     *
     * @return El año de la fecha.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Esta función establece el valor de la variable anio al valor del parámetro anio
     *
     * @param anio El año del coche.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Esta función devuelve el día de la semana.
     *
     * @return El día de la semana.
     */
    public int getDiaSemana() {
        return diaSemana;
    }

    /**
     * Esta función establece el día de la semana.
     *
     * @param diaSemana El día de la semana.
     */
    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    @Override
        public String toString() {
        return dia + "/" + mes + "/" + anio;
    }   
    
}
