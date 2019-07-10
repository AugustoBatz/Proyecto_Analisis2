/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasClases;

import java.util.ArrayList;

/**
 *
 * @author chr
 */
public class Factura extends Component{
    private Proveedor proveedor;
    private String fecha;
    private ArrayList<Producto> productos;

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    @Override
    public float getCosto(){
        return 0;
        
    }
    
    
    
}
