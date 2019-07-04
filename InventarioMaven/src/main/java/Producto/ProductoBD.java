/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Producto;

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
import main.Main;

/**
 *
 * @author sys515
 */
public class ProductoBD {
    private List querylist=new ArrayList();
    private int id_marca,id_prese,id_uni,id_cata;
    public List consultaOpciones(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");        
        EntityManager em = emf.createEntityManager();//
        TypedQuery<Categoria> query_cat = em.createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        TypedQuery<Presentacion> query_pres = em.createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = em.createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        TypedQuery<Marca> query_marca = em.createNamedQuery("Marca.findAll", Marca.class);
        List<Marca> listaMarca = query_marca.getResultList();//List es una interfaz
        querylist.add(listaCategoria);
        querylist.add(listaPresentacion);
        querylist.add(listaUnidad);
        querylist.add(listaMarca);
        //modelo_proxy_jl mc=new modelo_proxy_jl(listaCategoria);
        
        em.close();
        emf.close();
        return querylist;
    }
    
    public void crearProducto(ArrayList data){
        Producto p=new Producto();
        Contexto context;
        p.setNombre((String) data.get(0));
        p.setCodigo((String) data.get(1));
        p.setStockMinimo(Integer.valueOf((String) data.get(2)));
        
        context = new Contexto(new Categoria_es());
        p.setCategoriaid((Categoria) context.id((String) data.get(3)));
        
        context = new Contexto(new Presentacion_es());
        p.setPresentacionid((Presentacion) context.id((String) data.get(4)));
        
        context = new Contexto(new Unidad_es());
        p.setUnidadid((Unidad) context.id((String) data.get(5)));
        
        context = new Contexto( new Marca_es());
        p.setMarcaid((Marca) context.id((String) data.get(6)));
        
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");        
        EntityManager em = emf.createEntityManager();//
        ProductoJpaController productocontroller=new ProductoJpaController(em);
        try {
             productocontroller.create(p);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductoBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          //em.close();
        }
//         
        
        
        
        
        
        
        emf.close();
    }
}
