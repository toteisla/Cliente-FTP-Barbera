package gestorarchivos;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class ControlErrores {
    Hora hora = new Hora();
    //File dir = new File(System.getProperty("user.home").replace("\\", "/") + "/aft/");
    File file = new File("log_erorr.txt");
    File sqlFile = new File("log_erorrSQL.txt");
    //File dir = new File("/home/tote/Desktop/");
    //File file = new File("/home/tote/Desktop/log_erorr.txt");
    //File sqlFile = new File("/home/tote/Desktop/log_erorrSQL.txt");
    public void archivarError(String cadena){
        System.out.println(System.getProperty("user.home").replace("\\", "/"));
        /*
        if(!dir.isDirectory()){
            this.crearCarpeta();
        }*/
        if(cadena.indexOf("Duplicate") <= 0 && cadena.indexOf("Too") <= 0){
            try{
                FileWriter fichero = new FileWriter(file,true);
                fichero.write(hora.getDate() + cadena + "\r\n");
                fichero.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error con el log de errores.");
                System.exit(0);
            }
        }
    }

    public void archivarErrorSQL(String cadena){
        /*if(!dir.isDirectory()){
            this.crearCarpeta();
        }*/
        try{
            FileWriter fichero = new FileWriter(sqlFile,true);
            fichero.write(""+hora.getDate() + cadena + "\r\n");
            fichero.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error con el log de errores.");
            System.exit(0);
        }
    }
    /*
    public void crearCarpeta(){
        try{
            dir.mkdir();
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }*/
}
