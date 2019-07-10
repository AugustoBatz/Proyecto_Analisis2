/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventario;

import gt.url.edu.inventariomaven.Categoria;
import gt.url.edu.inventariomaven.Marca;
import gt.url.edu.inventariomaven.Presentacion;
import gt.url.edu.inventariomaven.Producto;
import gt.url.edu.inventariomaven.Unidad;
import gt.url.edu.factorybd.FactoryBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sys515
 */
public class InventarioOperacionesBD {
    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();

   
    public List listProductos(){
        
        TypedQuery<Producto> queryprod = conexion.getEntityManager().createNamedQuery("Producto.findAll", Producto.class);
        List<Producto> listaProductos = queryprod.getResultList();//List es una interfaz
        
        for (Object obj:listaProductos) {
            System.out.println(obj);
        }
        return listaProductos;
      
    }
    public List nombreDeColumnas(){
         List<String> nombresDeLasColumnas = new ArrayList<String>();
        nombresDeLasColumnas.add("Codigo");
        nombresDeLasColumnas.add("Nombre");
        nombresDeLasColumnas.add("Marca");
        nombresDeLasColumnas.add("Presentacion");
        nombresDeLasColumnas.add("Unidad");
        nombresDeLasColumnas.add("Categoria");
        nombresDeLasColumnas.add("Existencia");
        nombresDeLasColumnas.add("StockMinimo");
        return nombresDeLasColumnas;
    }
    
}
