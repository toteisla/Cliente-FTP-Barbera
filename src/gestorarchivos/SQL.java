package gestorarchivos;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
public class SQL {
    Funciones func = new Funciones();
    ControlErrores cr = new ControlErrores();
    Hora hora = new Hora();
    public ResultSet leeBaseDatosWeb(String strQuery){
        String sqlStringConnection = "jdbc:mysql://pinturasbarbera.net/admin_Barbera";
        Connection sqlConnection=null;
        ResultSet sqlQuery = null;
        Statement sqlCommand;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(sqlStringConnection, "web", "20abril12");
            sqlCommand = sqlConnection.createStatement();
            sqlQuery = sqlCommand.executeQuery(strQuery);
        } catch (Exception ex){
            cr.archivarErrorSQL("Consulta fallida o no se pudo conectar: " + ex.getMessage() + "\n ---Consulta: " + strQuery);
            //JOptionPane.showMessageDialog(null, hora.getDate() + ":Error de conexion con la base de datos.");
            //System.exit(0);
        }
        return sqlQuery;
    }

    public boolean booleanQueryWeb(String strQuery){
        String sqlStringConnection = "jdbc:mysql://pinturasbarbera.net/admin_Barbera";
        Connection sqlConnection=null;
        ResultSet sqlQuery = null;
        Statement sqlCommand;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(sqlStringConnection, "web", "20abril12");
            sqlCommand = sqlConnection.createStatement();
            sqlQuery = sqlCommand.executeQuery(strQuery);
            while(sqlQuery.next()){
                if(sqlQuery.getRow() > 0)
                    return true;
            }
        } catch (Exception ex) {
            cr.archivarErrorSQL("Consulta fallida o no se pudo conectar: " + ex.getMessage());
            //JOptionPane.showMessageDialog(null, hora.getDate() + ":Error de conexion con la base de datos.");
            //System.exit(0);
        }/*finally{
            try{
              sqlConnection.close();
            }catch(SQLException e){
              System.out.println(e);
            }
        }*/
        return false;
    }

    public boolean actualizaBaseDatosWeb(String strQuery){
        String sqlStringConnection = "jdbc:mysql://pinturasbarbera.net/admin_Barbera";
        Connection sqlConnection=null;
        Statement sqlCommand=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(sqlStringConnection, "web", "20abril12");
            sqlCommand = sqlConnection.createStatement();
            sqlCommand.executeUpdate(strQuery);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            cr.archivarErrorSQL("Consulta fallida o no se pudo conectar: " + ex.getMessage());
            //cr.archivarErrorSQL(strQuery);
            //JOptionPane.showMessageDialog(null, hora.getDate() + ":Error de conexion con la base de datos.");
            //System.exit(0);

        }/*finally{
            try{
              sqlConnection.close();
            }catch(SQLException e){
              System.out.println(e);
            }
        }*/
        return true;
    }

    public ResultSet leeBaseDatosBarbera(String strQuery){
        String sqlStringConnection = "jdbc:sqlserver://80.35.55.160:1433;databaseName=Barbera;user=web;password=20webaft12";
        Connection sqlConnection=null;
        ResultSet sqlQuery = null;
        Statement sqlCommand;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(Exception ex){
            cr.archivarErrorSQL("Driver Mysql no encontrado: " + ex.getMessage());
            //System.exit(0);
        }
        try{
            sqlConnection = DriverManager.getConnection(sqlStringConnection);
            sqlCommand = sqlConnection.createStatement();
            sqlQuery = sqlCommand.executeQuery(strQuery);
            //sqlConnection.close();
        } catch (SQLException ex) {
            cr.archivarErrorSQL("Consulta fallida o no se pudo conectar: " + ex.getMessage() + "code: "+ ex.getErrorCode());
            //JOptionPane.showMessageDialog(null, hora.getDate() + ":Error de conexion con la base de datos.");
            //System.exit(0);
        }
        /*finally{
            try{
              sqlConnection.close();
            }catch(SQLException e){
              System.out.println(e);
            }
        }*/
        return sqlQuery;
    }

    public boolean executeBinaryQuery(String strQuery, String strBin){
        String sqlStringConnection = "jdbc:mysql://pinturasbarbera.net/admin_Barbera";
        Connection sqlConnection;
        PreparedStatement sqlQuery;
        Statement sqlCommand;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlConnection = DriverManager.getConnection(sqlStringConnection, "web", "20abril12");
            sqlCommand = sqlConnection.createStatement();
            sqlQuery = sqlConnection.prepareStatement(strQuery);
            File fileBin = new File(strBin);
            FileInputStream bin = new FileInputStream(fileBin);
            sqlQuery.setBinaryStream(1, (InputStream)bin, (int)fileBin.length());
            System.out.println("Ejecutando consulta.");
            int result = sqlQuery.executeUpdate();
            if(result > 0)
                return true;
            else
                return false;
        } catch (Exception ex) {
            System.out.print("Error de conexion con la base de datos: ");
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
