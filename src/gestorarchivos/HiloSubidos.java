package gestorarchivos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HiloSubidos extends Thread{
    MainFrame frame;
    int borrarBD;
    ControlErrores cr = new ControlErrores();
    Funciones func = new Funciones();
    SQL sql = new SQL();
    Hora h = new Hora();
    double totalSubir = 0;
    public HiloSubidos(MainFrame mframe, int borrar){
        borrarBD = borrar;
        frame = mframe;
    }
    public void run(){
        frame.Estado.setText("Recuperando productos de la Base de Datos Web.");
        frame.barraTotal.setMinimum(0);
        frame.barraTotal.setMaximum(7);
        frame.barraTotal.setValue(0);

        if(borrarBD == 1){
            sql.actualizaBaseDatosWeb("update actualizando set actualizando = 1;");
            sql.actualizaBaseDatosWeb("truncate table productos;");
            sql.actualizaBaseDatosWeb("truncate table fichas;");
            sql.actualizaBaseDatosWeb("truncate table familia;");
            sql.actualizaBaseDatosWeb("truncate table proveedores;");
        }

        frame.Estado.setText("Recuperando productos de la Base de Datos Local.");
        /*
        ResultSet filasLocal = sql.leeBaseDatosBarbera("select A.Articulo,A.Nombre 'nomArticulo',A.Familia,A.Proveedor,F.Nombre 'nomFamilia',P.Nombre 'nomProveedor',D.Descripcion, D.Path, D.Paraweb "
                + "from Articulos A "
                + "inner join Proveedores P on A.Proveedor = P.Proveedor "
                + "left outer join Documentos D on A.Articulo = D.Articulo "
                + "left outer join Familias F on A.Familia = F.Familia "
                + "and (D.Descripcion LIKE '%seg%' or D.Descripcion LIKE '%tec%' or D.Descripcion LIKE '%téc%');");
         */

        ResultSet filasLocal = sql.leeBaseDatosBarbera("select A.Articulo,A.Nombre 'nomArticulo',A.Familia,A.Proveedor,D.Descripcion, D.Path, D.Paraweb "
                + "from Articulos A "
                + "inner join Documentos D on A.Articulo = D.Articulo "
                + "and (D.Descripcion LIKE '%seg%' or D.Descripcion LIKE '%tec%' or D.Descripcion LIKE '%téc%');");

        frame.barraTotal.setValue(1);

        frame.Estado.setText("Calculando Productos.");
        ResultSet nfilasLocal = sql.leeBaseDatosBarbera("select count(A.Articulo) 'nfilas' from Articulos A "
                + "inner join Documentos D on A.Articulo = D.Articulo "
                + "and (D.Descripcion LIKE '%seg%' or D.Descripcion LIKE '%tec%' or D.Descripcion LIKE '%téc%');");
        frame.barraTotal.setValue(2);

        frame.Estado.setText("Recuperando Proveedores de la Base de Datos Local.");
        ResultSet ProveedoresLocal = sql.leeBaseDatosBarbera("select distinct P.Proveedor, P.Nombre from Proveedores P, Articulos A, Documentos D "
                + "where P.Proveedor = A.Proveedor AND A.Articulo = D.Articulo");
        ResultSet ProveedoresWeb = sql.leeBaseDatosWeb("select id from proveedores");
        frame.barraTotal.setValue(3);

        frame.Estado.setText("Recuperando Familias de la Base de Datos Local.");
        ResultSet FamiliasLocal = sql.leeBaseDatosBarbera("select distinct F.Familia, F.Nombre from Familias F, Articulos A, Documentos D "
                + "where F.Familia = A.Familia AND A.Articulo = D.Articulo");
        ResultSet FamiliasWeb = sql.leeBaseDatosWeb("select id from familia");
        frame.barraTotal.setValue(4);

        frame.Estado.setText("Recuperando productos de la Web.");
        ResultSet ProductosWeb = sql.leeBaseDatosWeb("select codigo,familia,proveedor,nombre from productos");
        frame.barraTotal.setValue(5);

        frame.Estado.setText("Recuperando fichas de la Web.");
        ResultSet fichasWeb = sql.leeBaseDatosWeb("select id_producto, ruta_original from fichas");
        frame.barraTotal.setValue(6);

        frame.Estado.setText("Actualizando familia en la web");
        try{
            boolean existeFamilia = false;      
            while(FamiliasLocal.next()){
                existeFamilia = false;
                FamiliasWeb.beforeFirst();
                
                while(FamiliasWeb.next()){
                    if(FamiliasLocal.getString("Familia").equals(FamiliasWeb.getString("id"))){
                        existeFamilia = true;
                        break;
                    }
                }
                if(!existeFamilia)
                    sql.actualizaBaseDatosWeb("insert into familia values(\""+FamiliasLocal.getString("Familia")+"\",\""+FamiliasLocal.getString("Nombre")+"\")");
            }
        }catch(Exception e){
            cr.archivarErrorSQL("Fallo actualizando familia web: " + e.getMessage());
        }
        frame.barraTotal.setValue(7);

        frame.Estado.setText("Actualizando proveedores en la web");
        try{
            boolean existeProveedor = false;
            while(ProveedoresLocal.next()){
                existeProveedor = false;
                ProveedoresWeb.beforeFirst();

                while(ProveedoresWeb.next()){
                    if(ProveedoresLocal.getString("Proveedor").equals(ProveedoresWeb.getString("id"))){
                        existeProveedor = true;
                        break;
                    }
                }
                if(!existeProveedor)
                    sql.actualizaBaseDatosWeb("insert into proveedores values(\""+ProveedoresLocal.getString("Proveedor")+"\",\""+ProveedoresLocal.getString("Nombre")+"\")");
            }
        }catch(Exception e){
            cr.archivarErrorSQL("Fallo actualizando proveedor web: " + e.getMessage());
        }
        frame.barraTotal.setValue(7);

        //variables Bd Local
        String codArticuloLocal = "";
        String nomArticuloLocal = "";
        String proveedorLocal = "";
        //String nomProveedorLocal= "";
        String FamiliaLocal = "";
        //String nomFamiliaLocal = "";
        int tipoLocal = -1;

        try{
            int contFilas = 1;
            frame.barraTotal.setMinimum(1);
            int nFilas = 0;
            while(nfilasLocal.next())
                nFilas = nfilasLocal.getInt("nfilas");

            frame.barraTotal.setMaximum(nFilas);
            frame.barraTotal.setValue(1);
            //RECORRE LAS FILAS DE LA BD LOCAL
            while(filasLocal.next()){
                //INICIO FASE 1
                frame.lblTotal.setText("Progreso total: " + filasLocal.getRow() + " / " +nFilas);
                //Obtengo Código articulo
                codArticuloLocal = filasLocal.getString("Articulo");
                //Obtengo nombre Articulo
                nomArticuloLocal = filasLocal.getString("nomArticulo");
                String nombrex="'";
                char nombrechar=nombrex.charAt(0);
                if(nomArticuloLocal!=null && !nomArticuloLocal.equals(""))
                nomArticuloLocal = nomArticuloLocal.replace('"',nombrechar);
                //Obtengo Codigo proveedor
                proveedorLocal = filasLocal.getString("Proveedor");
                //nomProveedorLocal = filasLocal.getString("nomProveedor");
                if(proveedorLocal == null || proveedorLocal.indexOf("null") > 0){
                    proveedorLocal = "995";
                    //nomProveedorLocal = "Varios";
                }
                //Obtengo codigo familia
                FamiliaLocal = filasLocal.getString("Familia");
                //nomFamiliaLocal = filasLocal.getString("nomFamilia");
                if(FamiliaLocal == null || FamiliaLocal.equals("00")){
                   FamiliaLocal = "00";
                   //nomFamiliaLocal = "Varios";
                }
                //Obtengo tipo ficha
                tipoLocal = 0;
                String DescripcionLocal = filasLocal.getString("Descripcion");
                if(DescripcionLocal != null && (DescripcionLocal.indexOf("seg") > 0 || DescripcionLocal.indexOf("SEG") > 0 || DescripcionLocal.indexOf("Seg") > 0))
                    tipoLocal = 1;
                else if(DescripcionLocal != null && (DescripcionLocal.indexOf("tec") > 0 || DescripcionLocal.indexOf("TEC") > 0 || DescripcionLocal.indexOf("Tec") > 0 ||
                        DescripcionLocal.indexOf("téc") > 0 || DescripcionLocal.indexOf("TÉC") > 0 || DescripcionLocal.indexOf("Téc") > 0))
                    tipoLocal = 2;
                //Obtengo ruta ficha
                String Path = filasLocal.getString("Path");
                if(Path != null){
                    Path = Path.replace("\\","/");
                    //Path = Path.replace("F:/Datos Comunes/PRODUCTOS/FICHAS TECNICAS Y FICHAS DE SEGURIDAD", "/home/tote/Desktop/fichas");
                    //Path = Path.replace(" ","\\");
                }
                //Obtengo si subir o no la ficha
                int subir = filasLocal.getInt("Paraweb");

                //COMIENZA LA COMPARACION
                ProductosWeb.beforeFirst();
                boolean existeProducto = false;
                while(ProductosWeb.next()){
                    if(codArticuloLocal.equals(ProductosWeb.getString("codigo"))){
                        existeProducto = true;
                        if(!proveedorLocal.equals(ProductosWeb.getString("proveedor")))
                            sql.actualizaBaseDatosWeb("update productos set proveedor=\""+proveedorLocal+"\" "
                                    + "where codigo=\""+codArticuloLocal+"\"");
                        if(!FamiliaLocal.equals(ProductosWeb.getString("familia")))
                            sql.actualizaBaseDatosWeb("update productos set familia=\""+FamiliaLocal+"\" "
                                    + "where codigo=\""+codArticuloLocal+"\"");
                        if(nomArticuloLocal != null && !nomArticuloLocal.equals(ProductosWeb.getString("nombre")))
                            sql.actualizaBaseDatosWeb("update productos set nombre=\""+nomArticuloLocal+"\" "
                                    + "where codigo=\""+codArticuloLocal+"\"");
                        if(Path != null && !Path.equals("") && subir == 1 &&!func.existeFicha(fichasWeb, codArticuloLocal, Path)){
                            if(func.buscaArchivosLocal(Path)){
                                 sql.executeBinaryQuery("insert into fichas values(null,\""
                                        + codArticuloLocal+ "\","
                                        + "?" +",\""
                                        + func.getName(Path) +"\",\""
                                        + Path +"\",\""
                                        + tipoLocal +"\");", Path);
                                func.muestraSubidos(frame, codArticuloLocal,nomArticuloLocal,proveedorLocal,FamiliaLocal,tipoLocal,Path);
                            }
                        }else if(Path != null && !Path.equals("") && subir == 0 && func.existeFicha(fichasWeb, codArticuloLocal, Path)){
                            System.out.println("delete from fichas where ruta_original = \""+Path + "\"");
                            sql.actualizaBaseDatosWeb("delete from fichas where ruta_original = \""+Path + "\"");
                            func.muestraBorrados(frame, codArticuloLocal,nomArticuloLocal,proveedorLocal,FamiliaLocal,tipoLocal,Path);
                        }
                        func.muestraActualizados(frame, codArticuloLocal,nomArticuloLocal,proveedorLocal,FamiliaLocal,tipoLocal,Path);
                        break;
                    }
                }//Cierra While
                if(!existeProducto){
                    sql.actualizaBaseDatosWeb("insert into productos values(\""
                            +nomArticuloLocal+"\",\""
                            +codArticuloLocal+"\",\""
                            +FamiliaLocal+"\",\""
                            +proveedorLocal+"\",null);");
                    if(Path != null){
                        if(subir == 1 && !func.existeFicha(fichasWeb, codArticuloLocal, Path)){
                            if(func.buscaArchivosLocal(Path)){
                                sql.executeBinaryQuery("insert into fichas values(null,\""
                                        + codArticuloLocal+ "\","
                                        + "?" +",\""
                                        + func.getName(Path) +"\",\""
                                        + Path +"\",\""
                                        + tipoLocal +"\");", Path);
                            }
                        }
                    }
                    func.muestraSubidos(frame, codArticuloLocal,nomArticuloLocal,proveedorLocal,FamiliaLocal,tipoLocal,Path);
                }

                frame.barraTotal.setValue(contFilas++);
                frame.barraTotal.setStringPainted(true);
            }//cierra while principal

            sql.actualizaBaseDatosWeb("update actualizando set actualizando = 0;");
            frame.Estado.setText("Terminado");
            frame.boton.setEnabled(false);
            frame.botonBorrar.setEnabled(false);
            cr.archivarError("ESTO HA TERMINADO A LAS -> " + h.getDate());
        }catch(SQLException e){
            cr.archivarErrorSQL("Error de la base de datos: " + e.getMessage());
        }
    }
}