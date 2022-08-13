
package modelo;

import java.util.Calendar;
import java.util.Date;


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
    
    public int antiguedad(){
        Calendar Fecha = Calendar.getInstance();
        return Fecha.get(Calendar.YEAR)-this.anio;   
    }
    
    //Retorna el dia de la semana con los atributos de la clase
    public int diaSemanaDesdeAtributos(){
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(new Date(getAnio(),getMes(),getDia()));
        return fecha.get(Calendar.DAY_OF_WEEK); 
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    @Override
        public String toString() {
        return dia + "/" + mes + "/" + anio;
    }   
    
}
