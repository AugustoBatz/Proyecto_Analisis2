/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.almacen;

import gt.url.edu.inventariomaven.Categoria;
import gt.url.edu.inventariomaven.CategoriaJpaController;
import gt.url.edu.inventariomaven.Marca;
import gt.url.edu.inventariomaven.MarcaJpaController;
import gt.url.edu.inventariomaven.Presentacion;
import gt.url.edu.inventariomaven.PresentacionJpaController;
import gt.url.edu.inventariomaven.Unidad;
import gt.url.edu.inventariomaven.UnidadJpaController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import gt.url.edu.main.Main;
import gt.url.edu.factorybd.FactoryBaseDeDatos;
import static gt.url.edu.factorybd.FactoryBaseDeDatos.getInstancia;

/**
 *
 * @author sys515
 */
public class AlmacenOperacionesBD {
    private List querylist=new ArrayList();
    private FactoryBaseDeDatos conexion= getInstancia();
    
    public List listasDelAlmacen(){
        
        TypedQuery<Categoria> query_cat = this.conexion.getEntityManager().createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        TypedQuery<Presentacion> query_pres = conexion.getEntityManager().createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = conexion.getEntityManager().createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        TypedQuery<Marca> query_marca = conexion.getEntityManager().createNamedQuery("Marca.findAll", Marca.class);
        List<Marca> listaMarca = query_marca.getResultList();//List es una interfaz
        //modelo_proxy_jl mc=new modelo_proxy_jl(listaCategoria);
        querylist.add(listaCategoria);
        querylist.add(listaPresentacion);
        querylist.add(listaUnidad);
        querylist.add(listaMarca);
        System.out.println("Query");
        //conexion.getEntityManager().close();
       
        return querylist;
    }
     public void create(int tipo,String descripcion){
    
        
        if(tipo == 1) {
            CategoriaJpaController categoriaController = new CategoriaJpaController(this.conexion.getEntityManager());
            Categoria c = new Categoria();
            
            c.setCategoria(descripcion);
            try {
                categoriaController.create(c);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
        if(tipo == 2) {
            PresentacionJpaController presetnacioncontroller=new PresentacionJpaController(conexion.getEntityManager());
            Presentacion p = new Presentacion();
            
            p.setPresentacion(descripcion);
            try {
                presetnacioncontroller.create(p);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(tipo == 3) {
            UnidadJpaController unidadcontroller=new UnidadJpaController(conexion.getEntityManager());
            Unidad u = new Unidad();
            u.setUnidad(descripcion);
            try {
                unidadcontroller.create(u);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           
            }
        }
        if(tipo == 4) {
            MarcaJpaController marcacontroller=new MarcaJpaController(conexion.getEntityManager());
            Marca m = new Marca();
            m.setMarca(descripcion);
            try {
                marcacontroller.create(m);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //em.close();
        
        
    
        
    }
}
