/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorybd;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author chr
 */
public class FactoryBaseDeDatos {
        
    
    public FactoryBaseDeDatos(){
        //nombre base de datos
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurante");
        EntityManager em = emf.createEntityManager();
    }
    public     
        
        //TypedQuery<Cafe> query = em.createNamedQuery("Cafe.findAll", Cafe.class);
       // List<Cafe> listaCafes = query.getResultList();
    
}
