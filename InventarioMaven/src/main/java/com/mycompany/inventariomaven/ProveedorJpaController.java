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
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getFacturaCompraCollection() == null) {
            proveedor.setFacturaCompraCollection(new ArrayList<FacturaCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<FacturaCompra> attachedFacturaCompraCollection = new ArrayList<FacturaCompra>();
            for (FacturaCompra facturaCompraCollectionFacturaCompraToAttach : proveedor.getFacturaCompraCollection()) {
                facturaCompraCollectionFacturaCompraToAttach = em.getReference(facturaCompraCollectionFacturaCompraToAttach.getClass(), facturaCompraCollectionFacturaCompraToAttach.getId());
                attachedFacturaCompraCollection.add(facturaCompraCollectionFacturaCompraToAttach);
            }
            proveedor.setFacturaCompraCollection(attachedFacturaCompraCollection);
            em.persist(proveedor);
            for (FacturaCompra facturaCompraCollectionFacturaCompra : proveedor.getFacturaCompraCollection()) {
                Proveedor oldProveedoridOfFacturaCompraCollectionFacturaCompra = facturaCompraCollectionFacturaCompra.getProveedorid();
                facturaCompraCollectionFacturaCompra.setProveedorid(proveedor);
                facturaCompraCollectionFacturaCompra = em.merge(facturaCompraCollectionFacturaCompra);
                if (oldProveedoridOfFacturaCompraCollectionFacturaCompra != null) {
                    oldProveedoridOfFacturaCompraCollectionFacturaCompra.getFacturaCompraCollection().remove(facturaCompraCollectionFacturaCompra);
                    oldProveedoridOfFacturaCompraCollectionFacturaCompra = em.merge(oldProveedoridOfFacturaCompraCollectionFacturaCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getId());
            Collection<FacturaCompra> facturaCompraCollectionOld = persistentProveedor.getFacturaCompraCollection();
            Collection<FacturaCompra> facturaCompraCollectionNew = proveedor.getFacturaCompraCollection();
            List<String> illegalOrphanMessages = null;
            for (FacturaCompra facturaCompraCollectionOldFacturaCompra : facturaCompraCollectionOld) {
                if (!facturaCompraCollectionNew.contains(facturaCompraCollectionOldFacturaCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FacturaCompra " + facturaCompraCollectionOldFacturaCompra + " since its proveedorid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<FacturaCompra> attachedFacturaCompraCollectionNew = new ArrayList<FacturaCompra>();
            for (FacturaCompra facturaCompraCollectionNewFacturaCompraToAttach : facturaCompraCollectionNew) {
                facturaCompraCollectionNewFacturaCompraToAttach = em.getReference(facturaCompraCollectionNewFacturaCompraToAttach.getClass(), facturaCompraCollectionNewFacturaCompraToAttach.getId());
                attachedFacturaCompraCollectionNew.add(facturaCompraCollectionNewFacturaCompraToAttach);
            }
            facturaCompraCollectionNew = attachedFacturaCompraCollectionNew;
            proveedor.setFacturaCompraCollection(facturaCompraCollectionNew);
            proveedor = em.merge(proveedor);
            for (FacturaCompra facturaCompraCollectionNewFacturaCompra : facturaCompraCollectionNew) {
                if (!facturaCompraCollectionOld.contains(facturaCompraCollectionNewFacturaCompra)) {
                    Proveedor oldProveedoridOfFacturaCompraCollectionNewFacturaCompra = facturaCompraCollectionNewFacturaCompra.getProveedorid();
                    facturaCompraCollectionNewFacturaCompra.setProveedorid(proveedor);
                    facturaCompraCollectionNewFacturaCompra = em.merge(facturaCompraCollectionNewFacturaCompra);
                    if (oldProveedoridOfFacturaCompraCollectionNewFacturaCompra != null && !oldProveedoridOfFacturaCompraCollectionNewFacturaCompra.equals(proveedor)) {
                        oldProveedoridOfFacturaCompraCollectionNewFacturaCompra.getFacturaCompraCollection().remove(facturaCompraCollectionNewFacturaCompra);
                        oldProveedoridOfFacturaCompraCollectionNewFacturaCompra = em.merge(oldProveedoridOfFacturaCompraCollectionNewFacturaCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getId();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<FacturaCompra> facturaCompraCollectionOrphanCheck = proveedor.getFacturaCompraCollection();
            for (FacturaCompra facturaCompraCollectionOrphanCheckFacturaCompra : facturaCompraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the FacturaCompra " + facturaCompraCollectionOrphanCheckFacturaCompra + " in its facturaCompraCollection field has a non-nullable proveedorid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
