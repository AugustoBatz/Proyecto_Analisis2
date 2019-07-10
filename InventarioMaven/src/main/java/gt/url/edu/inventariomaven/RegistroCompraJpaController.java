/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.url.edu.inventariomaven;

import gt.url.edu.inventariomaven.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sys515
 */
public class RegistroCompraJpaController implements Serializable {

    public RegistroCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroCompra registroCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroCompra registroCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroCompra = em.merge(registroCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroCompra.getId();
                if (findRegistroCompra(id) == null) {
                    throw new NonexistentEntityException("The registroCompra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroCompra registroCompra;
            try {
                registroCompra = em.getReference(RegistroCompra.class, id);
                registroCompra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroCompra with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroCompra> findRegistroCompraEntities() {
        return findRegistroCompraEntities(true, -1, -1);
    }

    public List<RegistroCompra> findRegistroCompraEntities(int maxResults, int firstResult) {
        return findRegistroCompraEntities(false, maxResults, firstResult);
    }

    private List<RegistroCompra> findRegistroCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroCompra.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RegistroCompra findRegistroCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroCompra> rt = cq.from(RegistroCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
