/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedor;

import inventario_compras.ConexionDB.Conector;
import java.sql.SQLException;

/**
 *
 * @author sys515
 */
public class Proveedor {
    
    private String inserQuery;
    Conector conexion=Conector.getDbCon();
    private String query;
    public int setQuery(String insertQuery) throws SQLException{
        conexion.insert(insertQuery);
        return 1;
    }
    
}
