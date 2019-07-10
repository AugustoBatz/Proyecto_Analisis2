/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.producto;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.Marca;
import com.mycompany.inventariomaven.Presentacion;
import com.mycompany.inventariomaven.Producto;
import com.mycompany.inventariomaven.ProductoJpaController;
import com.mycompany.inventariomaven.Unidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.mycompany.main.Main;
import factorybd.FactoryBaseDeDatos;
import static factorybd.FactoryBaseDeDatos.getInstancia;

/**
 *
 * @author sys515
 */
public class ProductoOperacionBD implements InterfazCrearProducto{
    private List querylist=new ArrayList();
 
    private FactoryBaseDeDatos conexion= getInstancia();
    
    
  
    @Override
    public List CaracteristicasDelProducto() {
     
        TypedQuery<Categoria> query_cat = conexion.getEntityManager().createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        TypedQuery<Presentacion> query_pres = conexion.getEntityManager().createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = conexion.getEntityManager().createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        TypedQuery<Marca> query_marca = conexion.getEntityManager().createNamedQuery("Marca.findAll", Marca.class);
        List<Marca> listaMarca = query_marca.getResultList();//List es una interfaz
        querylist.add(listaCategoria);
        querylist.add(listaPresentacion);
        querylist.add(listaUnidad);
        querylist.add(listaMarca);
        
        return querylist;
    }

    @Override
    public void CrearProducto(ArrayList data) {
        Producto p=new Producto();
        Contexto context;
        p.setNombre((String) data.get(0));
        p.setCodigo((String) data.get(1));
        p.setStockMinimo(Integer.valueOf((String) data.get(2)));
        
        context = new Contexto(new CategoriaEstrategia());
        p.setCategoriaid((Categoria) context.id((String) data.get(3)));
        
        context = new Contexto(new PresentacionEstrategia());
        p.setPresentacionid((Presentacion) context.id((String) data.get(4)));
        
        context = new Contexto(new UnidadEstrategia());
        p.setUnidadid((Unidad) context.id((String) data.get(5)));
        
        context = new Contexto( new MarcaEstrategia());
        p.setMarcaid((Marca) context.id((String) data.get(6)));
        
        
        ProductoJpaController productocontroller=new ProductoJpaController(conexion.getEntityManager());
        try {
             productocontroller.create(p);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductoOperacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
