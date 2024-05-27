/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Entrega;
import Clases.Repartidor;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USER
 */
public class RepartidorJpaController implements Serializable {

    public RepartidorJpaController() {
         emf = Persistence.createEntityManagerFactory("sistemaPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Repartidor repartidor) throws PreexistingEntityException, Exception {
        if (repartidor.getEntrega() == null) {
            repartidor.setEntrega(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Entrega> attachedEntrega = new ArrayList<Entrega>();
            for (Entrega entregaEntregaToAttach : repartidor.getEntrega()) {
                entregaEntregaToAttach = em.getReference(entregaEntregaToAttach.getClass(), entregaEntregaToAttach.getId());
                attachedEntrega.add(entregaEntregaToAttach);
            }
            repartidor.setEntrega(attachedEntrega);
            em.persist(repartidor);
            for (Entrega entregaEntrega : repartidor.getEntrega()) {
                Repartidor oldRepartidorOfEntregaEntrega = entregaEntrega.getRepartidor();
                entregaEntrega.setRepartidor(repartidor);
                entregaEntrega = em.merge(entregaEntrega);
                if (oldRepartidorOfEntregaEntrega != null) {
                    oldRepartidorOfEntregaEntrega.getEntrega().remove(entregaEntrega);
                    oldRepartidorOfEntregaEntrega = em.merge(oldRepartidorOfEntregaEntrega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRepartidor(repartidor.getCedula()) != null) {
                throw new PreexistingEntityException("Repartidor " + repartidor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Repartidor repartidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Repartidor persistentRepartidor = em.find(Repartidor.class, repartidor.getCedula());
            List<Entrega> entregaOld = persistentRepartidor.getEntrega();
            List<Entrega> entregaNew = repartidor.getEntrega();
            List<Entrega> attachedEntregaNew = new ArrayList<Entrega>();
            for (Entrega entregaNewEntregaToAttach : entregaNew) {
                entregaNewEntregaToAttach = em.getReference(entregaNewEntregaToAttach.getClass(), entregaNewEntregaToAttach.getId());
                attachedEntregaNew.add(entregaNewEntregaToAttach);
            }
            entregaNew = attachedEntregaNew;
            repartidor.setEntrega(entregaNew);
            repartidor = em.merge(repartidor);
            for (Entrega entregaOldEntrega : entregaOld) {
                if (!entregaNew.contains(entregaOldEntrega)) {
                    entregaOldEntrega.setRepartidor(null);
                    entregaOldEntrega = em.merge(entregaOldEntrega);
                }
            }
            for (Entrega entregaNewEntrega : entregaNew) {
                if (!entregaOld.contains(entregaNewEntrega)) {
                    Repartidor oldRepartidorOfEntregaNewEntrega = entregaNewEntrega.getRepartidor();
                    entregaNewEntrega.setRepartidor(repartidor);
                    entregaNewEntrega = em.merge(entregaNewEntrega);
                    if (oldRepartidorOfEntregaNewEntrega != null && !oldRepartidorOfEntregaNewEntrega.equals(repartidor)) {
                        oldRepartidorOfEntregaNewEntrega.getEntrega().remove(entregaNewEntrega);
                        oldRepartidorOfEntregaNewEntrega = em.merge(oldRepartidorOfEntregaNewEntrega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = repartidor.getCedula();
                if (findRepartidor(id) == null) {
                    throw new NonexistentEntityException("The repartidor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Repartidor repartidor;
            try {
                repartidor = em.getReference(Repartidor.class, id);
                repartidor.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The repartidor with id " + id + " no longer exists.", enfe);
            }
            List<Entrega> entrega = repartidor.getEntrega();
            for (Entrega entregaEntrega : entrega) {
                entregaEntrega.setRepartidor(null);
                entregaEntrega = em.merge(entregaEntrega);
            }
            em.remove(repartidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Repartidor> findRepartidorEntities() {
        return findRepartidorEntities(true, -1, -1);
    }

    public List<Repartidor> findRepartidorEntities(int maxResults, int firstResult) {
        return findRepartidorEntities(false, maxResults, firstResult);
    }

    private List<Repartidor> findRepartidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Repartidor.class));
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

    public Repartidor findRepartidor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Repartidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getRepartidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Repartidor> rt = cq.from(Repartidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
