/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventario;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.Marca;
import com.mycompany.inventariomaven.Presentacion;
import com.mycompany.inventariomaven.Producto;
import com.mycompany.inventariomaven.Unidad;
import factorybd.FactoryBaseDeDatos;
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

   
    public List listaProdcutos(){
        
        TypedQuery<Producto> queryprod = conexion.getEntityManager().createNamedQuery("Producto.findAll", Producto.class);
        List<Producto> listaProductos = queryprod.getResultList();//List es una interfaz
        for (Object obj:listaProductos) {
            System.out.println(obj);
        }
        return listaProductos;
      
    }
    
}
