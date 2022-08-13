package modelo;

import java.util.Calendar;

/**
 * La clase Hora representa la hora local del sistema
 */
public class Hora {
    private int hh,mm,ss;

    /**
     * Constructor basico, crea una instancia de Hora a partir de la hora local
     */
    public Hora() {
        Calendar hora = Calendar.getInstance();
        hh = hora.get(Calendar.HOUR);
        mm = hora.get(Calendar.MINUTE);
        ss = hora.get(Calendar.SECOND);
   }

    /**
     * Constructor parametrico, crea una instancia de Hora a partir de los 
     * siguientes parametros
     * @param hh
     * @param mm
     * @param ss
     */
    public Hora(int hh, int mm, int ss) {
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
    }
    
    /**
     * Metodo el cual incrementa los segundos de la hora
     */
    public void incrementoSS(){
        if(ss<59){
            ss++;
        } else {
            ss = 0;
        }
    }
    
    /**
     * Metodo el cual incrementa los minutos de la hora
     */
    public void incrementoMM(){
        if(ss == 0){
            mm++;
        } else if(mm > 59){
            mm = 0;
        }
    }

    /**
     * Metodo el cual incrementa las horas de la hora
     */
    public void incrementoHH(){
        if(mm == 0){
            hh++;
        } else if(hh >= 23){
            hh = 0;
        }
    }
    
    /**
     * Getter que retorna el valor del atributo hh 
     * @return int
     */
    public int getHh() {
        return hh;
    }

    /**
     * Setter que modifica el valor del atributo hh
     * @param hh
     */
    public void setHh(int hh) {
        this.hh = hh;
    }

    /**
     * Getter que retorna el valor del atributo mm 
     * @return int
     */
    public int getMm() {
        return mm;
    }

    /**
     * Setter que modifica el valor del atributo mm
     * @param mm
     */
    public void setMm(int mm) {
        this.mm = mm;
    }

    /**
     * Getter que retorna el valor del atributo ss 
     * @return int
     */
    public int getSs() {
        return ss;
    }

    /**
     * Setter que modifica el valor del atributo ss
     * @param ss
     */
    public void setSs(int ss) {
        this.ss = ss;
    }
    
    @Override
    public String toString() {
        return hh + ":" + mm + ":" + ss;
    }
    
}
