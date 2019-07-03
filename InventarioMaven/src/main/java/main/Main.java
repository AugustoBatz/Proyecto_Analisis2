/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mycompany.inventariomaven.Categoria;
import com.mycompany.inventariomaven.CategoriaJpaController;
import com.mycompany.inventariomaven.Producto;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import vistas.Almacen;

/**
 *
 * @author sys515
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("proyecto");//la unidad de persostencia decide que base de datos usar
        ///EntityMagegerFactory fabrica de adminsitradores de entidades,
        EntityManager em = emf.createEntityManager();//
        //crear consultas
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.findAll", Categoria.class);
       // TypedQuery<Complemento> query = em.createNamedQuery("Complemento.findByCostoandNombre", Complemento.class);
        //query.setParameter("nombre", "Ron");
       // query.setParameter("costo", 1.5f);
        List<Categoria> listaCategoria = query.getResultList();//List es una interfaz
        /*for (int i = 0; i < listaComplementos.size(); i++) {
            System.out.println(listaComplementos.get(i));
        }*/
        for (Categoria categoria : listaCategoria) {
            
            System.out.println(categoria);
        }
        Almacen view=new Almacen(listaCategoria);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        Categoria c1 = new Categoria();
      
        c1.setCategoria("Alimentos");
        //c1.setCosto(1.5f);
        CategoriaJpaController categoriaController = new CategoriaJpaController(em);
        try {
            categoriaController.create(c1);
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            em.close();
        }
        
    }
    
}
