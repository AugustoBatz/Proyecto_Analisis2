/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.inventariomaven;

import com.mycompany.inventariomaven.exceptions.IllegalOrphanException;
import com.mycompany.inventariomaven.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sys515
 */
public class FacturaCompraJpaController implements Serializable {

    public FacturaCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturaCompra facturaCompra) {
        if (facturaCompra.getLoteCollection() == null) {
            facturaCompra.setLoteCollection(new ArrayList<Lote>());
        }
        if (facturaCompra.getRegistroCompraCollection() == null) {
            facturaCompra.setRegistroCompraCollection(new ArrayList<RegistroCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorid = facturaCompra.getProveedorid();
            if (proveedorid != null) {
                proveedorid = em.getReference(proveedorid.getClass(), proveedorid.getId());
                facturaCompra.setProveedorid(proveedorid);
            }
            Collection<Lote> attachedLoteCollection = new ArrayList<Lote>();
            for (Lote loteCollectionLoteToAttach : facturaCompra.getLoteCollection()) {
                loteCollectionLoteToAttach = em.getReference(loteCollectionLoteToAttach.getClass(), loteCollectionLoteToAttach.getId());
                attachedLoteCollection.add(loteCollectionLoteToAttach);
            }
            facturaCompra.setLoteCollection(attachedLoteCollection);
            Collection<RegistroCompra> attachedRegistroCompraCollection = new ArrayList<RegistroCompra>();
            for (RegistroCompra registroCompraCollectionRegistroCompraToAttach : facturaCompra.getRegistroCompraCollection()) {
                registroCompraCollectionRegistroCompraToAttach = em.getReference(registroCompraCollectionRegistroCompraToAttach.getClass(), registroCompraCollectionRegistroCompraToAttach.getId());
                attachedRegistroCompraCollection.add(registroCompraCollectionRegistroCompraToAttach);
            }
            facturaCompra.setRegistroCompraCollection(attachedRegistroCompraCollection);
            em.persist(facturaCompra);
            if (proveedorid != null) {
                proveedorid.getFacturaCompraCollection().add(facturaCompra);
                proveedorid = em.merge(proveedorid);
            }
            for (Lote loteCollectionLote : facturaCompra.getLoteCollection()) {
                FacturaCompra oldFacturaCompraidOfLoteCollectionLote = loteCollectionLote.getFacturaCompraid();
                loteCollectionLote.setFacturaCompraid(facturaCompra);
                loteCollectionLote = em.merge(loteCollectionLote);
                if (oldFacturaCompraidOfLoteCollectionLote != null) {
                    oldFacturaCompraidOfLoteCollectionLote.getLoteCollection().remove(loteCollectionLote);
                    oldFacturaCompraidOfLoteCollectionLote = em.merge(oldFacturaCompraidOfLoteCollectionLote);
                }
            }
            for (RegistroCompra registroCompraCollectionRegistroCompra : facturaCompra.getRegistroCompraCollection()) {
                FacturaCompra oldFacturaCompraidOfRegistroCompraCollectionRegistroCompra = registroCompraCollectionRegistroCompra.getFacturaCompraid();
                registroCompraCollectionRegistroCompra.setFacturaCompraid(facturaCompra);
                registroCompraCollectionRegistroCompra = em.merge(registroCompraCollectionRegistroCompra);
                if (oldFacturaCompraidOfRegistroCompraCollectionRegistroCompra != null) {
                    oldFacturaCompraidOfRegistroCompraCollectionRegistroCompra.getRegistroCompraCollection().remove(registroCompraCollectionRegistroCompra);
                    oldFacturaCompraidOfRegistroCompraCollectionRegistroCompra = em.merge(oldFacturaCompraidOfRegistroCompraCollectionRegistroCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturaCompra facturaCompra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaCompra persistentFacturaCompra = em.find(FacturaCompra.class, facturaCompra.getId());
            Proveedor proveedoridOld = persistentFacturaCompra.getProveedorid();
            Proveedor proveedoridNew = facturaCompra.getProveedorid();
            Collection<Lote> loteCollectionOld = persistentFacturaCompra.getLoteCollection();
            Collection<Lote> loteCollectionNew = facturaCompra.getLoteCollection();
            Collection<RegistroCompra> registroCompraCollectionOld = persistentFacturaCompra.getRegistroCompraCollection();
            Collection<RegistroCompra> registroCompraCollectionNew = facturaCompra.getRegistroCompraCollection();
            List<String> illegalOrphanMessages = null;
            for (Lote loteCollectionOldLote : loteCollectionOld) {
                if (!loteCollectionNew.contains(loteCollectionOldLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lote " + loteCollectionOldLote + " since its facturaCompraid field is not nullable.");
                }
            }
            for (RegistroCompra registroCompraCollectionOldRegistroCompra : registroCompraCollectionOld) {
                if (!registroCompraCollectionNew.contains(registroCompraCollectionOldRegistroCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroCompra " + registroCompraCollectionOldRegistroCompra + " since its facturaCompraid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedoridNew != null) {
                proveedoridNew = em.getReference(proveedoridNew.getClass(), proveedoridNew.getId());
                facturaCompra.setProveedorid(proveedoridNew);
            }
            Collection<Lote> attachedLoteCollectionNew = new ArrayList<Lote>();
            for (Lote loteCollectionNewLoteToAttach : loteCollectionNew) {
                loteCollectionNewLoteToAttach = em.getReference(loteCollectionNewLoteToAttach.getClass(), loteCollectionNewLoteToAttach.getId());
                attachedLoteCollectionNew.add(loteCollectionNewLoteToAttach);
            }
            loteCollectionNew = attachedLoteCollectionNew;
            facturaCompra.setLoteCollection(loteCollectionNew);
            Collection<RegistroCompra> attachedRegistroCompraCollectionNew = new ArrayList<RegistroCompra>();
            for (RegistroCompra registroCompraCollectionNewRegistroCompraToAttach : registroCompraCollectionNew) {
                registroCompraCollectionNewRegistroCompraToAttach = em.getReference(registroCompraCollectionNewRegistroCompraToAttach.getClass(), registroCompraCollectionNewRegistroCompraToAttach.getId());
                attachedRegistroCompraCollectionNew.add(registroCompraCollectionNewRegistroCompraToAttach);
            }
            registroCompraCollectionNew = attachedRegistroCompraCollectionNew;
            facturaCompra.setRegistroCompraCollection(registroCompraCollectionNew);
            facturaCompra = em.merge(facturaCompra);
            if (proveedoridOld != null && !proveedoridOld.equals(proveedoridNew)) {
                proveedoridOld.getFacturaCompraCollection().remove(facturaCompra);
                proveedoridOld = em.merge(proveedoridOld);
            }
            if (proveedoridNew != null && !proveedoridNew.equals(proveedoridOld)) {
                proveedoridNew.getFacturaCompraCollection().add(facturaCompra);
                proveedoridNew = em.merge(proveedoridNew);
            }
            for (Lote loteCollectionNewLote : loteCollectionNew) {
                if (!loteCollectionOld.contains(loteCollectionNewLote)) {
                    FacturaCompra oldFacturaCompraidOfLoteCollectionNewLote = loteCollectionNewLote.getFacturaCompraid();
                    loteCollectionNewLote.setFacturaCompraid(facturaCompra);
                    loteCollectionNewLote = em.merge(loteCollectionNewLote);
                    if (oldFacturaCompraidOfLoteCollectionNewLote != null && !oldFacturaCompraidOfLoteCollectionNewLote.equals(facturaCompra)) {
                        oldFacturaCompraidOfLoteCollectionNewLote.getLoteCollection().remove(loteCollectionNewLote);
                        oldFacturaCompraidOfLoteCollectionNewLote = em.merge(oldFacturaCompraidOfLoteCollectionNewLote);
                    }
                }
            }
            for (RegistroCompra registroCompraCollectionNewRegistroCompra : registroCompraCollectionNew) {
                if (!registroCompraCollectionOld.contains(registroCompraCollectionNewRegistroCompra)) {
                    FacturaCompra oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra = registroCompraCollectionNewRegistroCompra.getFacturaCompraid();
                    registroCompraCollectionNewRegistroCompra.setFacturaCompraid(facturaCompra);
                    registroCompraCollectionNewRegistroCompra = em.merge(registroCompraCollectionNewRegistroCompra);
                    if (oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra != null && !oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra.equals(facturaCompra)) {
                        oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra.getRegistroCompraCollection().remove(registroCompraCollectionNewRegistroCompra);
                        oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra = em.merge(oldFacturaCompraidOfRegistroCompraCollectionNewRegistroCompra);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Lote> loteCollectionOrphanCheck = facturaCompra.getLoteCollection();
            for (Lote loteCollectionOrphanCheckLote : loteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacturaCompra (" + facturaCompra + ") cannot be destroyed since the Lote " + loteCollectionOrphanCheckLote + " in its loteCollection field has a non-nullable facturaCompraid field.");
            }
            Collection<RegistroCompra> registroCompraCollectionOrphanCheck = facturaCompra.getRegistroCompraCollection();
            for (RegistroCompra registroCompraCollectionOrphanCheckRegistroCompra : registroCompraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FacturaCompra (" + facturaCompra + ") cannot be destroyed since the RegistroCompra " + registroCompraCollectionOrphanCheckRegistroCompra + " in its registroCompraCollection field has a non-nullable facturaCompraid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorid = facturaCompra.getProveedorid();
            if (proveedorid != null) {
                proveedorid.getFacturaCompraCollection().remove(facturaCompra);
                proveedorid = em.merge(proveedorid);
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
