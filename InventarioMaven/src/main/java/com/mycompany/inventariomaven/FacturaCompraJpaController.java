/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventariomaven;

import com.mycompany.inventariomaven.exceptions.NonexistentEntityException;
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
public class FacturaCompraJpaController implements Serializable {

    public FacturaCompraJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(FacturaCompra facturaCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(facturaCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                //em.close();
            }
        }
    }

    public void edit(FacturaCompra facturaCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            facturaCompra = em.merge(facturaCompra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturaCompra.getId();
                if (findFacturaCompra(id) == null) {
                    throw new NonexistentEntityException("The facturaCompra with id " + id + " no longer exists.");
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
            FacturaCompra facturaCompra;
            try {
                facturaCompra = em.getReference(FacturaCompra.class, id);
                facturaCompra.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaCompra with id " + id + " no longer exists.", enfe);
            }
            em.remove(facturaCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacturaCompra> findFacturaCompraEntities() {
        return findFacturaCompraEntities(true, -1, -1);
    }

    public List<FacturaCompra> findFacturaCompraEntities(int maxResults, int firstResult) {
        return findFacturaCompraEntities(false, maxResults, firstResult);
    }

    private List<FacturaCompra> findFacturaCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaCompra.class));
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

    public FacturaCompra findFacturaCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturaCompra> rt = cq.from(FacturaCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
