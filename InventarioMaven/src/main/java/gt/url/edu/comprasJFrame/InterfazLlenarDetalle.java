/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.inventariomaven.Producto;
import gt.url.edu.inventariomaven.Proveedor;
import java.util.List;

/**
 *
 * @author sys515
 */
public interface InterfazLlenarDetalle {
    public Producto getProducto(String id);
    public List getListNit();
    public Proveedor getProveedor(String id);
    
   
}
