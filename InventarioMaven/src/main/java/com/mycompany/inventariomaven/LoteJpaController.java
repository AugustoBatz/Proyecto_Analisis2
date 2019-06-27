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
public class LoteJpaController implements Serializable {

    public LoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lote lote) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaCompra facturaCompraid = lote.getFacturaCompraid();
            if (facturaCompraid != null) {
                facturaCompraid = em.getReference(facturaCompraid.getClass(), facturaCompraid.getId());
                lote.setFacturaCompraid(facturaCompraid);
            }
            Producto productoid = lote.getProductoid();
            if (productoid != null) {
                productoid = em.getReference(productoid.getClass(), productoid.getId());
                lote.setProductoid(productoid);
            }
            em.persist(lote);
            if (facturaCompraid != null) {
                facturaCompraid.getLoteCollection().add(lote);
                facturaCompraid = em.merge(facturaCompraid);
            }
            if (productoid != null) {
                productoid.getLoteCollection().add(lote);
                productoid = em.merge(productoid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lote lote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote persistentLote = em.find(Lote.class, lote.getId());
            FacturaCompra facturaCompraidOld = persistentLote.getFacturaCompraid();
            FacturaCompra facturaCompraidNew = lote.getFacturaCompraid();
            Producto productoidOld = persistentLote.getProductoid();
            Producto productoidNew = lote.getProductoid();
            if (facturaCompraidNew != null) {
                facturaCompraidNew = em.getReference(facturaCompraidNew.getClass(), facturaCompraidNew.getId());
                lote.setFacturaCompraid(facturaCompraidNew);
            }
            if (productoidNew != null) {
                productoidNew = em.getReference(productoidNew.getClass(), productoidNew.getId());
                lote.setProductoid(productoidNew);
            }
            lote = em.merge(lote);
            if (facturaCompraidOld != null && !facturaCompraidOld.equals(facturaCompraidNew)) {
                facturaCompraidOld.getLoteCollection().remove(lote);
                facturaCompraidOld = em.merge(facturaCompraidOld);
            }
            if (facturaCompraidNew != null && !facturaCompraidNew.equals(facturaCompraidOld)) {
                facturaCompraidNew.getLoteCollection().add(lote);
                facturaCompraidNew = em.merge(facturaCompraidNew);
            }
            if (productoidOld != null && !productoidOld.equals(productoidNew)) {
                productoidOld.getLoteCollection().remove(lote);
                productoidOld = em.merge(productoidOld);
            }
            if (productoidNew != null && !productoidNew.equals(productoidOld)) {
                productoidNew.getLoteCollection().add(lote);
                productoidNew = em.merge(productoidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lote.getId();
                if (findLote(id) == null) {
                    throw new NonexistentEntityException("The lote with id " + id + " no longer exists.");
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
            Lote lote;
            try {
                lote = em.getReference(Lote.class, id);
                lote.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lote with id " + id + " no longer exists.", enfe);
            }
            FacturaCompra facturaCompraid = lote.getFacturaCompraid();
            if (facturaCompraid != null) {
                facturaCompraid.getLoteCollection().remove(lote);
                facturaCompraid = em.merge(facturaCompraid);
            }
            Producto productoid = lote.getProductoid();
            if (productoid != null) {
                productoid.getLoteCollection().remove(lote);
                productoid = em.merge(productoid);
            }
            em.remove(lote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lote> findLoteEntities() {
        return findLoteEntities(true, -1, -1);
    }

    public List<Lote> findLoteEntities(int maxResults, int firstResult) {
        return findLoteEntities(false, maxResults, firstResult);
    }

    private List<Lote> findLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lote.class));
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

    public Lote findLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lote> rt = cq.from(Lote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
