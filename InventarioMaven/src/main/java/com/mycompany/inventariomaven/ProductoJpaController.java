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
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getLoteCollection() == null) {
            producto.setLoteCollection(new ArrayList<Lote>());
        }
        if (producto.getRegistroCompraCollection() == null) {
            producto.setRegistroCompraCollection(new ArrayList<RegistroCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaid = producto.getCategoriaid();
            if (categoriaid != null) {
                categoriaid = em.getReference(categoriaid.getClass(), categoriaid.getId());
                producto.setCategoriaid(categoriaid);
            }
            Presentacion presentacionid = producto.getPresentacionid();
            if (presentacionid != null) {
                presentacionid = em.getReference(presentacionid.getClass(), presentacionid.getId());
                producto.setPresentacionid(presentacionid);
            }
            Unidad unidadid = producto.getUnidadid();
            if (unidadid != null) {
                unidadid = em.getReference(unidadid.getClass(), unidadid.getId());
                producto.setUnidadid(unidadid);
            }
            Marca marcaid = producto.getMarcaid();
            if (marcaid != null) {
                marcaid = em.getReference(marcaid.getClass(), marcaid.getId());
                producto.setMarcaid(marcaid);
            }
            Collection<Lote> attachedLoteCollection = new ArrayList<Lote>();
            for (Lote loteCollectionLoteToAttach : producto.getLoteCollection()) {
                loteCollectionLoteToAttach = em.getReference(loteCollectionLoteToAttach.getClass(), loteCollectionLoteToAttach.getId());
                attachedLoteCollection.add(loteCollectionLoteToAttach);
            }
            producto.setLoteCollection(attachedLoteCollection);
            Collection<RegistroCompra> attachedRegistroCompraCollection = new ArrayList<RegistroCompra>();
            for (RegistroCompra registroCompraCollectionRegistroCompraToAttach : producto.getRegistroCompraCollection()) {
                registroCompraCollectionRegistroCompraToAttach = em.getReference(registroCompraCollectionRegistroCompraToAttach.getClass(), registroCompraCollectionRegistroCompraToAttach.getId());
                attachedRegistroCompraCollection.add(registroCompraCollectionRegistroCompraToAttach);
            }
            producto.setRegistroCompraCollection(attachedRegistroCompraCollection);
            em.persist(producto);
            if (categoriaid != null) {
                categoriaid.getProductoCollection().add(producto);
                categoriaid = em.merge(categoriaid);
            }
            if (presentacionid != null) {
                presentacionid.getProductoCollection().add(producto);
                presentacionid = em.merge(presentacionid);
            }
            if (unidadid != null) {
                unidadid.getProductoCollection().add(producto);
                unidadid = em.merge(unidadid);
            }
            if (marcaid != null) {
                marcaid.getProductoCollection().add(producto);
                marcaid = em.merge(marcaid);
            }
            for (Lote loteCollectionLote : producto.getLoteCollection()) {
                Producto oldProductoidOfLoteCollectionLote = loteCollectionLote.getProductoid();
                loteCollectionLote.setProductoid(producto);
                loteCollectionLote = em.merge(loteCollectionLote);
                if (oldProductoidOfLoteCollectionLote != null) {
                    oldProductoidOfLoteCollectionLote.getLoteCollection().remove(loteCollectionLote);
                    oldProductoidOfLoteCollectionLote = em.merge(oldProductoidOfLoteCollectionLote);
                }
            }
            for (RegistroCompra registroCompraCollectionRegistroCompra : producto.getRegistroCompraCollection()) {
                Producto oldProductoidOfRegistroCompraCollectionRegistroCompra = registroCompraCollectionRegistroCompra.getProductoid();
                registroCompraCollectionRegistroCompra.setProductoid(producto);
                registroCompraCollectionRegistroCompra = em.merge(registroCompraCollectionRegistroCompra);
                if (oldProductoidOfRegistroCompraCollectionRegistroCompra != null) {
                    oldProductoidOfRegistroCompraCollectionRegistroCompra.getRegistroCompraCollection().remove(registroCompraCollectionRegistroCompra);
                    oldProductoidOfRegistroCompraCollectionRegistroCompra = em.merge(oldProductoidOfRegistroCompraCollectionRegistroCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Categoria categoriaidOld = persistentProducto.getCategoriaid();
            Categoria categoriaidNew = producto.getCategoriaid();
            Presentacion presentacionidOld = persistentProducto.getPresentacionid();
            Presentacion presentacionidNew = producto.getPresentacionid();
            Unidad unidadidOld = persistentProducto.getUnidadid();
            Unidad unidadidNew = producto.getUnidadid();
            Marca marcaidOld = persistentProducto.getMarcaid();
            Marca marcaidNew = producto.getMarcaid();
            Collection<Lote> loteCollectionOld = persistentProducto.getLoteCollection();
            Collection<Lote> loteCollectionNew = producto.getLoteCollection();
            Collection<RegistroCompra> registroCompraCollectionOld = persistentProducto.getRegistroCompraCollection();
            Collection<RegistroCompra> registroCompraCollectionNew = producto.getRegistroCompraCollection();
            List<String> illegalOrphanMessages = null;
            for (Lote loteCollectionOldLote : loteCollectionOld) {
                if (!loteCollectionNew.contains(loteCollectionOldLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Lote " + loteCollectionOldLote + " since its productoid field is not nullable.");
                }
            }
            for (RegistroCompra registroCompraCollectionOldRegistroCompra : registroCompraCollectionOld) {
                if (!registroCompraCollectionNew.contains(registroCompraCollectionOldRegistroCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroCompra " + registroCompraCollectionOldRegistroCompra + " since its productoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaidNew != null) {
                categoriaidNew = em.getReference(categoriaidNew.getClass(), categoriaidNew.getId());
                producto.setCategoriaid(categoriaidNew);
            }
            if (presentacionidNew != null) {
                presentacionidNew = em.getReference(presentacionidNew.getClass(), presentacionidNew.getId());
                producto.setPresentacionid(presentacionidNew);
            }
            if (unidadidNew != null) {
                unidadidNew = em.getReference(unidadidNew.getClass(), unidadidNew.getId());
                producto.setUnidadid(unidadidNew);
            }
            if (marcaidNew != null) {
                marcaidNew = em.getReference(marcaidNew.getClass(), marcaidNew.getId());
                producto.setMarcaid(marcaidNew);
            }
            Collection<Lote> attachedLoteCollectionNew = new ArrayList<Lote>();
            for (Lote loteCollectionNewLoteToAttach : loteCollectionNew) {
                loteCollectionNewLoteToAttach = em.getReference(loteCollectionNewLoteToAttach.getClass(), loteCollectionNewLoteToAttach.getId());
                attachedLoteCollectionNew.add(loteCollectionNewLoteToAttach);
            }
            loteCollectionNew = attachedLoteCollectionNew;
            producto.setLoteCollection(loteCollectionNew);
            Collection<RegistroCompra> attachedRegistroCompraCollectionNew = new ArrayList<RegistroCompra>();
            for (RegistroCompra registroCompraCollectionNewRegistroCompraToAttach : registroCompraCollectionNew) {
                registroCompraCollectionNewRegistroCompraToAttach = em.getReference(registroCompraCollectionNewRegistroCompraToAttach.getClass(), registroCompraCollectionNewRegistroCompraToAttach.getId());
                attachedRegistroCompraCollectionNew.add(registroCompraCollectionNewRegistroCompraToAttach);
            }
            registroCompraCollectionNew = attachedRegistroCompraCollectionNew;
            producto.setRegistroCompraCollection(registroCompraCollectionNew);
            producto = em.merge(producto);
            if (categoriaidOld != null && !categoriaidOld.equals(categoriaidNew)) {
                categoriaidOld.getProductoCollection().remove(producto);
                categoriaidOld = em.merge(categoriaidOld);
            }
            if (categoriaidNew != null && !categoriaidNew.equals(categoriaidOld)) {
                categoriaidNew.getProductoCollection().add(producto);
                categoriaidNew = em.merge(categoriaidNew);
            }
            if (presentacionidOld != null && !presentacionidOld.equals(presentacionidNew)) {
                presentacionidOld.getProductoCollection().remove(producto);
                presentacionidOld = em.merge(presentacionidOld);
            }
            if (presentacionidNew != null && !presentacionidNew.equals(presentacionidOld)) {
                presentacionidNew.getProductoCollection().add(producto);
                presentacionidNew = em.merge(presentacionidNew);
            }
            if (unidadidOld != null && !unidadidOld.equals(unidadidNew)) {
                unidadidOld.getProductoCollection().remove(producto);
                unidadidOld = em.merge(unidadidOld);
            }
            if (unidadidNew != null && !unidadidNew.equals(unidadidOld)) {
                unidadidNew.getProductoCollection().add(producto);
                unidadidNew = em.merge(unidadidNew);
            }
            if (marcaidOld != null && !marcaidOld.equals(marcaidNew)) {
                marcaidOld.getProductoCollection().remove(producto);
                marcaidOld = em.merge(marcaidOld);
            }
            if (marcaidNew != null && !marcaidNew.equals(marcaidOld)) {
                marcaidNew.getProductoCollection().add(producto);
                marcaidNew = em.merge(marcaidNew);
            }
            for (Lote loteCollectionNewLote : loteCollectionNew) {
                if (!loteCollectionOld.contains(loteCollectionNewLote)) {
                    Producto oldProductoidOfLoteCollectionNewLote = loteCollectionNewLote.getProductoid();
                    loteCollectionNewLote.setProductoid(producto);
                    loteCollectionNewLote = em.merge(loteCollectionNewLote);
                    if (oldProductoidOfLoteCollectionNewLote != null && !oldProductoidOfLoteCollectionNewLote.equals(producto)) {
                        oldProductoidOfLoteCollectionNewLote.getLoteCollection().remove(loteCollectionNewLote);
                        oldProductoidOfLoteCollectionNewLote = em.merge(oldProductoidOfLoteCollectionNewLote);
                    }
                }
            }
            for (RegistroCompra registroCompraCollectionNewRegistroCompra : registroCompraCollectionNew) {
                if (!registroCompraCollectionOld.contains(registroCompraCollectionNewRegistroCompra)) {
                    Producto oldProductoidOfRegistroCompraCollectionNewRegistroCompra = registroCompraCollectionNewRegistroCompra.getProductoid();
                    registroCompraCollectionNewRegistroCompra.setProductoid(producto);
                    registroCompraCollectionNewRegistroCompra = em.merge(registroCompraCollectionNewRegistroCompra);
                    if (oldProductoidOfRegistroCompraCollectionNewRegistroCompra != null && !oldProductoidOfRegistroCompraCollectionNewRegistroCompra.equals(producto)) {
                        oldProductoidOfRegistroCompraCollectionNewRegistroCompra.getRegistroCompraCollection().remove(registroCompraCollectionNewRegistroCompra);
                        oldProductoidOfRegistroCompraCollectionNewRegistroCompra = em.merge(oldProductoidOfRegistroCompraCollectionNewRegistroCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Lote> loteCollectionOrphanCheck = producto.getLoteCollection();
            for (Lote loteCollectionOrphanCheckLote : loteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Lote " + loteCollectionOrphanCheckLote + " in its loteCollection field has a non-nullable productoid field.");
            }
            Collection<RegistroCompra> registroCompraCollectionOrphanCheck = producto.getRegistroCompraCollection();
            for (RegistroCompra registroCompraCollectionOrphanCheckRegistroCompra : registroCompraCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the RegistroCompra " + registroCompraCollectionOrphanCheckRegistroCompra + " in its registroCompraCollection field has a non-nullable productoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria categoriaid = producto.getCategoriaid();
            if (categoriaid != null) {
                categoriaid.getProductoCollection().remove(producto);
                categoriaid = em.merge(categoriaid);
            }
            Presentacion presentacionid = producto.getPresentacionid();
            if (presentacionid != null) {
                presentacionid.getProductoCollection().remove(producto);
                presentacionid = em.merge(presentacionid);
            }
            Unidad unidadid = producto.getUnidadid();
            if (unidadid != null) {
                unidadid.getProductoCollection().remove(producto);
                unidadid = em.merge(unidadid);
            }
            Marca marcaid = producto.getMarcaid();
            if (marcaid != null) {
                marcaid.getProductoCollection().remove(producto);
                marcaid = em.merge(marcaid);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
