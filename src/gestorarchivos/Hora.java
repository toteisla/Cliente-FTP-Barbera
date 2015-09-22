package gestorarchivos;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hora {
    public String getDate(){
        Calendar calendario = new GregorianCalendar();
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));
        if(Integer.valueOf(hora) < 9)
            hora = "0" + hora;
        if(Integer.valueOf(minutos) < 9)
            minutos = "0" + minutos;
        if(Integer.valueOf(segundos) < 9)
            segundos = "0" + segundos;

        return hora +":"+minutos+":"+segundos+":";
    }
}
