/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.Presentacion;
import com.mycompany.inventariomaven.Unidad;
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
public class read implements strategy {

    private List querylist=new ArrayList();
    @Override
    public void operacionbd() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto");
        EntityManager em = emf.createEntityManager();//
        TypedQuery<Categoria> query_cat = em.createNamedQuery("Categoria.findAll", Categoria.class);
        List<Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        TypedQuery<Presentacion> query_pres = em.createNamedQuery("Presentacion.findAll", Presentacion.class);
        List<Presentacion> listaPresentacion = query_pres.getResultList();//List es una interfaz
        TypedQuery<Unidad> query_unidad = em.createNamedQuery("Unidad.findAll", Unidad.class);
        List<Unidad> listaUnidad = query_unidad.getResultList();//List es una interfaz
        querylist.add(listaCategoria);
        querylist.add(listaPresentacion);
        querylist.add(listaUnidad);
        em.close();
        emf.close();
    }

    public List getQuerylist() {
        return querylist;
    }
    

}
