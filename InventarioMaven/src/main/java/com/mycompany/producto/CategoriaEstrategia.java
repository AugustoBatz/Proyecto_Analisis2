/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.producto;

import com.mycompany.factorybd.FactoryBaseDeDatos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author sys515
 */
public class CategoriaEstrategia implements InterfazEstrategia{
    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();
     public CategoriaEstrategia() {
    }
    @Override
    public Object realizarOperacion(String descripcion) {
      
        System.out.println(descripcion);
        TypedQuery<com.mycompany.inventariomaven.Categoria> query_cat = conexion.getEntityManager().createNamedQuery("Categoria.findByCategoria", com.mycompany.inventariomaven.Categoria.class);
        query_cat.setParameter("categoria", descripcion);
        List<com.mycompany.inventariomaven.Categoria> listaCategoria = query_cat.getResultList();//List es una interfaz
        System.out.println("Lista");
        System.out.println(listaCategoria);
        System.out.println(listaCategoria.get(0).getId());

        return listaCategoria.get(0);
    }

}
