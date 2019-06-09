/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario_compras;

import inventario_compras.ConexionDB.Conector;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author sys515
 */
public class Inventario_Compras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Conector conexion = Conector.getDbCon();
         ResultSet res=conexion.query("select * from Proveedor");
        while(res.next())
        {
            
            System.out.println(res.getString(1)+" - "+res.getString(2));
        }
        
        // TODO code application logic here
    }
    
}
