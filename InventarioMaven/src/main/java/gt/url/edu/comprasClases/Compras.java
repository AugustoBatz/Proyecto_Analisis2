/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.comprasClases;

import gt.url.edu.comprasJFrame.DetallesFactura;
import gt.url.edu.comprasJFrame.InterfazLlenarDetalle;
import gt.url.edu.factorybd.FactoryBaseDeDatos;
import gt.url.edu.inventariomaven.FacturaCompra;
import gt.url.edu.inventariomaven.FacturaCompraJpaController;
import gt.url.edu.inventariomaven.Lote;
import gt.url.edu.inventariomaven.LoteJpaController;
import gt.url.edu.inventariomaven.Producto;
import gt.url.edu.inventariomaven.ProductoJpaController;
import gt.url.edu.inventariomaven.ProveedorJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;

/**
 *
 * @author sys515
 */
public class Compras implements InterfazCompras{

    private int idFactura;
    private FacturaCompra fc;
    private final FactoryBaseDeDatos conexion = FactoryBaseDeDatos.getInstancia();
    private final InterfazLlenarDetalle interfaz = new DetallesFactura();
    private FacturaCompraJpaController facturacontroller;

    public Compras() {

    }

    @Override
    public void crearFacturaCompra(FacturaCompra fc) {
        facturacontroller = new FacturaCompraJpaController(conexion.getEntityManager());

        
        facturacontroller.create(fc);
        System.out.println(fc);
        idFactura=fc.getId();
        System.out.println("IdF "+idFactura);
        this.fc=fc;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void crearLotes(List data) {
        LoteJpaController loteController = new LoteJpaController(conexion.getEntityManager());
        ProductoJpaController pc=new ProductoJpaController(conexion.getEntityManager());
        
        for (Object object : data) {
            
            Lote lote = new Lote();
            
            List data2 = (List) object;
            lote.setNoLote(1);
            String id_2 = String.valueOf(data2.get(0));
            lote.setGanancia(12.5f);
            lote.setPrecioTotal(12.2f);
            lote.setPrecioUnitario(12.f);
            
            lote.setCantidad(Integer.valueOf(String.valueOf(data2.get(6))));
            System.out.println(Integer.valueOf(String.valueOf(data2.get(6))));
            lote.setDisponible(true);
            lote.setFacturaCompraid(fc);
            System.out.println(fc);
            lote.setCostoUnitario(Float.valueOf(String.valueOf(data2.get(7))));
            System.out.println(Float.valueOf(String.valueOf(data2.get(7))));
            lote.setCostoTotal(Float.valueOf(String.valueOf(data2.get(8))));
            System.out.println(Float.valueOf(String.valueOf(data2.get(8))));
            lote.setProductoid(interfaz.getProducto(id_2));
          
            TypedQuery<Producto> query = conexion.getEntityManager().createNamedQuery("Producto.findById", Producto.class);
            query.setParameter("id",Integer.valueOf(id_2));
            try {
                pc.edit(query.getSingleResult(), Integer.valueOf(String.valueOf(data2.get(6))));
            } catch (Exception ex) {
                Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
            }
            loteController.create(lote);
            
            System.out.println("entraa controllers");
            
        }
    }
  
    
    
}
