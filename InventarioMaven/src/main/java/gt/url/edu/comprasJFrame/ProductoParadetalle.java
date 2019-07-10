/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.factorybd.FactoryBaseDeDatos;
import gt.url.edu.inventariomaven.Producto;
import javax.persistence.TypedQuery;

/**
 *
 * @author sys515
 */
public class ProductoParadetalle implements InterfazLlenarDetalle{

    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();
    @Override
    public Producto getProducto(String id) {
        TypedQuery<Producto> query = conexion.getEntityManager().createNamedQuery("Producto.findById", Producto.class);
        query.setParameter("id", Integer.valueOf(id));
        return query.getSingleResult();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
