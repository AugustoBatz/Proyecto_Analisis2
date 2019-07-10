/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasJFrame;

import gt.url.edu.factorybd.FactoryBaseDeDatos;
import gt.url.edu.inventariomaven.Producto;
import gt.url.edu.inventariomaven.Proveedor;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author sys515
 */
public class DetallesFactura implements InterfazLlenarDetalle{

    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();
    @Override
    public Producto getProducto(String id) {
        TypedQuery<Producto> query = conexion.getEntityManager().createNamedQuery("Producto.findById", Producto.class);
        query.setParameter("id", Integer.valueOf(id));
        return query.getSingleResult();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getListNit() {
        TypedQuery<Proveedor> query = conexion.getEntityManager().createNamedQuery("Proveedor.findAll", Proveedor.class);
        List<Proveedor> queryList=query.getResultList();
        return queryList;
    }

    @Override
    public Proveedor getProveedor(String id) {
        TypedQuery<Proveedor> query = conexion.getEntityManager().createNamedQuery("Proveedor.findById", Proveedor.class);
        query.setParameter("id", Integer.valueOf(id));
        return query.getSingleResult();
    }

   
    
}
