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
    private static FactoryBaseDeDatos conexionDb;
    private final EntityManagerFactory emf;
    private final EntityManager em;    
    
    private FactoryBaseDeDatos(){
        //nombre base de datos
        emf = Persistence.createEntityManagerFactory("proyecto");
        em = emf.createEntityManager();
    }
    public static FactoryBaseDeDatos getInstancia() {
        if (conexionDb == null) {
            conexionDb = new FactoryBaseDeDatos();
        }
        return conexionDb;
    }

    public boolean EstadoConexionDb() {
        return em.isOpen();
    }

    public String cerrarConexionDb() {
        this.em.close();
        return "Desconectado";
    }

    public EntityManager getEntityManager() {
        return this.em;
    }
        

    
}
