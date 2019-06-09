/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario_compras.ConexionDB;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
/**
 *
 * @author sys515
 */
public final class Conector {
  public Connection conn;
    private Statement statement;
    public static Conector conexion;
    private Conector() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "InventarioPEPS/UEPS?useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "1InventarioPEPS$";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    public static synchronized Conector getDbCon() {
        if ( conexion == null ) {
            conexion = new Conector();
        }
        return conexion;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = conexion.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    public int insert(String insertQuery) throws SQLException {
        statement = conexion.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
    
}
