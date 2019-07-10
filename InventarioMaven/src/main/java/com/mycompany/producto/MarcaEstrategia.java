/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.producto;

import com.mycompany.inventariomaven.Marca;
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
public class MarcaEstrategia implements InterfazEstrategia {
    
    private FactoryBaseDeDatos conexion=FactoryBaseDeDatos.getInstancia();
    @Override
    public Object realizarOperacion(String descripcion) {
      
        System.out.println(descripcion);
        TypedQuery<Marca> query_cat = conexion.getEntityManager().createNamedQuery("Marca.findByMarca",Marca.class);
        query_cat.setParameter("marca", descripcion);
        List<Marca> listamarca = query_cat.getResultList();//List es una interfaz
        System.out.println("Lista");
        System.out.println(listamarca);
        System.out.println(listamarca.get(0).getId());

       
        return listamarca.get(0);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
