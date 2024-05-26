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
 * @author V I C T U S
 */
public class RepartidorJpaController implements Serializable {
    public RepartidorJpaController() {
        emf = Persistence.createEntityManagerFactory("TareaPaquetesPU");
    }
    public RepartidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Repartidor repartidor) throws PreexistingEntityException, Exception {
        if (repartidor.getEntregas() == null) {
            repartidor.setEntregas(new ArrayList<Entrega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Entrega> attachedEntregas = new ArrayList<Entrega>();
            for (Entrega entregasEntregaToAttach : repartidor.getEntregas()) {
                entregasEntregaToAttach = em.getReference(entregasEntregaToAttach.getClass(), entregasEntregaToAttach.getId());
                attachedEntregas.add(entregasEntregaToAttach);
            }
            repartidor.setEntregas(attachedEntregas);
            em.persist(repartidor);
            for (Entrega entregasEntrega : repartidor.getEntregas()) {
                Repartidor oldRepartidorOfEntregasEntrega = entregasEntrega.getRepartidor();
                entregasEntrega.setRepartidor(repartidor);
                entregasEntrega = em.merge(entregasEntrega);
                if (oldRepartidorOfEntregasEntrega != null) {
                    oldRepartidorOfEntregasEntrega.getEntregas().remove(entregasEntrega);
                    oldRepartidorOfEntregasEntrega = em.merge(oldRepartidorOfEntregasEntrega);
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
            List<Entrega> entregasOld = persistentRepartidor.getEntregas();
            List<Entrega> entregasNew = repartidor.getEntregas();
            List<Entrega> attachedEntregasNew = new ArrayList<Entrega>();
            for (Entrega entregasNewEntregaToAttach : entregasNew) {
                entregasNewEntregaToAttach = em.getReference(entregasNewEntregaToAttach.getClass(), entregasNewEntregaToAttach.getId());
                attachedEntregasNew.add(entregasNewEntregaToAttach);
            }
            entregasNew = attachedEntregasNew;
            repartidor.setEntregas(entregasNew);
            repartidor = em.merge(repartidor);
            for (Entrega entregasOldEntrega : entregasOld) {
                if (!entregasNew.contains(entregasOldEntrega)) {
                    entregasOldEntrega.setRepartidor(null);
                    entregasOldEntrega = em.merge(entregasOldEntrega);
                }
            }
            for (Entrega entregasNewEntrega : entregasNew) {
                if (!entregasOld.contains(entregasNewEntrega)) {
                    Repartidor oldRepartidorOfEntregasNewEntrega = entregasNewEntrega.getRepartidor();
                    entregasNewEntrega.setRepartidor(repartidor);
                    entregasNewEntrega = em.merge(entregasNewEntrega);
                    if (oldRepartidorOfEntregasNewEntrega != null && !oldRepartidorOfEntregasNewEntrega.equals(repartidor)) {
                        oldRepartidorOfEntregasNewEntrega.getEntregas().remove(entregasNewEntrega);
                        oldRepartidorOfEntregasNewEntrega = em.merge(oldRepartidorOfEntregasNewEntrega);
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
            List<Entrega> entregas = repartidor.getEntregas();
            for (Entrega entregasEntrega : entregas) {
                entregasEntrega.setRepartidor(null);
                entregasEntrega = em.merge(entregasEntrega);
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
