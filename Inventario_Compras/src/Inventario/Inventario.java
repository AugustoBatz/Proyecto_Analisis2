/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.util.ArrayList;

/**
 *
 * @author sys515
 */
public class Inventario {
    private ArrayList<Producto>  Producto;
    public int añadirProducto(Producto p){
        if(Producto==null){
            Producto=new ArrayList<Producto>();
        }

        Producto.add(p);

        return 1;
    }
    public void consultarProductos() {
        for (int i = 0; i < Producto.size(); i++) {
            System.out.println( Producto.get(i).getProducto());
           
        }
  }
    
}
