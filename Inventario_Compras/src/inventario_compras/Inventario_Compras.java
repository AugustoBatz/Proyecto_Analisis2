/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario_compras;

import Proveedor.Interfaz_Crear_Proveedor_Controller;
import Proveedor.Interfaz_Crear_Proveedor_View;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




/**
 *
 * @author sys515
 */
public class Inventario_Compras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Interfaz_Crear_Proveedor_View interfaz = new Interfaz_Crear_Proveedor_View();
        new Interfaz_Crear_Proveedor_Controller(interfaz);
        interfaz.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        interfaz.pack();
        interfaz.setLocationRelativeTo(null);
        interfaz.setVisible(true);

        
        // TODO code application logic here
    }
    
}
