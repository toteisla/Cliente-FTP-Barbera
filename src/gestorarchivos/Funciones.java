package gestorarchivos;

import java.io.File;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Funciones{
    private int uploadedFiles = 0;
    private int updatedFiles = 0;
    private int removedFiles = 0;

    public boolean buscaArchivosLocal(String ruta){
        File archivo = new File(ruta);
        if(!archivo.isFile()){
            return false;
        }
        return true;
    }

    public String getName(String ruta){
        String name = "";
        for(int i=ruta.length() ; i>0 ; i--){
            if(ruta.substring(i-1,i).equals("/")){
                name=ruta.substring(i);
                break;
            }
        }
        return name;
    }

    public void muestraSubidos(MainFrame frame,String cod ,String nom, String prov, String fam, int tipo, String ruta){
        String filaTabla[] = new String[6];
        filaTabla[0] = cod;
        filaTabla[1] = nom;
        filaTabla[2] = prov;
        filaTabla[3] = fam;
        if(tipo == 1)
            filaTabla[4] = "Seguridad";
        if(tipo == 2)
            filaTabla[4] = "Técnica";
        filaTabla[5] = ruta;
        DefaultTableModel modelo = (DefaultTableModel)frame.tablaSubidos.getModel();
        modelo.addRow(filaTabla);
        frame.scrollSubidos.getVerticalScrollBar().setValue(frame.scrollSubidos.getVerticalScrollBar().getMaximum());
        frame.jTabbedPane1.setTitleAt(0, "Nuevos("+ ++uploadedFiles +")");
    }

    public void muestraActualizados(MainFrame frame,String cod ,String nom, String prov, String fam, int tipo, String ruta){
        String filaTabla[] = new String[6];
        filaTabla[0] = cod;
        filaTabla[1] = nom;
        filaTabla[2] = prov;
        filaTabla[3] = fam;
        if(tipo == 1)
            filaTabla[4] = "Seguridad";
        if(tipo == 2)
            filaTabla[4] = "Técnica";
        if(tipo == 0)
            filaTabla[4] = "";
        filaTabla[5] = ruta;
        DefaultTableModel modelo = (DefaultTableModel)frame.tablaActualizados.getModel();
        modelo.addRow(filaTabla);
        frame.scrollActualizados.getVerticalScrollBar().setValue(frame.scrollActualizados.getVerticalScrollBar().getMaximum());
        frame.jTabbedPane1.setTitleAt(1, "Actualizados("+ ++updatedFiles +")");
    }

    public void muestraBorrados(MainFrame frame,String cod ,String nom, String prov, String fam, int tipo, String ruta){
        String filaTabla[] = new String[6];
        filaTabla[0] = cod;
        filaTabla[1] = nom;
        filaTabla[2] = prov;
        filaTabla[3] = fam;
        if(tipo == 1)
            filaTabla[4] = "Seguridad";
        if(tipo == 2)
            filaTabla[4] = "Técnica";
        if(tipo == 0)
            filaTabla[4] = "";
        filaTabla[5] = ruta;
        DefaultTableModel modelo = (DefaultTableModel)frame.tablaBorrados.getModel();
        modelo.addRow(filaTabla);
        frame.scrollBorrados.getVerticalScrollBar().setValue(frame.scrollBorrados.getVerticalScrollBar().getMaximum());
        frame.jTabbedPane1.setTitleAt(2, "Borrados("+ ++removedFiles +")");
    }

    public boolean existeFicha(ResultSet fichasWeb, String codArticuloLocal, String Path){
        try{
            fichasWeb.last();
            String arrFichas[] = new String[fichasWeb.getRow()];
            int contFichas = 0;
            fichasWeb.beforeFirst();
            while(fichasWeb.next()){
                if(codArticuloLocal.equals(fichasWeb.getString("id_producto"))){
                    arrFichas[contFichas] = fichasWeb.getString("ruta_original");
                }
                contFichas++;
            }
            for(int i=0 ; i<arrFichas.length ; i++){
                if(arrFichas[i] != null){
                    if(arrFichas[i].equals(Path))
                        return true;
                }
            }
        }catch(Exception e){
            
        }
        return false;
    }
}
