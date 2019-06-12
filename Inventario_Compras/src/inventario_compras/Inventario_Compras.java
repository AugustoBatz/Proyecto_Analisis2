/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario_compras;

import Inventario.Inventario;
import Inventario.Producto;
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
        
        
      //construir();
        Producto Lapiz=new Producto();
        Lapiz.crearProducto("01", "Lapiz", "HB", "Unidad", "X", "Libreria",25, 0);
        Producto Marcador=new Producto();
        Marcador.crearProducto("02", "Marcador", "Bic", "Unidad", "X", "Libreria",50, 0);
        Producto Pegamento=new Producto();
        Pegamento.crearProducto("03", "Pegamento", "Fast", "Frasco","12 onz", "Libreria", 23,0);
        Inventario inventario=new Inventario();
        inventario.añadirProducto(Lapiz);
        inventario.añadirProducto(Pegamento);
        inventario.añadirProducto(Marcador);
        inventario.consultarProductos();
        
        
  

        
        // TODO code application logic here
    }
    public static void construir(){
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
    }
}
