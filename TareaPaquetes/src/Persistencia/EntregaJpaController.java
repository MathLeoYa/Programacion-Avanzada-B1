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
import Clases.Clientes;
import Clases.Entrega;
import Clases.Paquete;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mateo
 */
public class EntregaJpaController implements Serializable {

    public EntregaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entrega entrega) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes cliente = entrega.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getCedula());
                entrega.setCliente(cliente);
            }
            Paquete paquete = entrega.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getIdPaq());
                entrega.setPaquete(paquete);
            }
            em.persist(entrega);
            if (cliente != null) {
                cliente.getEntregas().add(entrega);
                cliente = em.merge(cliente);
            }
            if (paquete != null) {
                Entrega oldEntregaOfPaquete = paquete.getEntrega();
                if (oldEntregaOfPaquete != null) {
                    oldEntregaOfPaquete.setPaquete(null);
                    oldEntregaOfPaquete = em.merge(oldEntregaOfPaquete);
                }
                paquete.setEntrega(entrega);
                paquete = em.merge(paquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entrega entrega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega persistentEntrega = em.find(Entrega.class, entrega.getId());
            Clientes clienteOld = persistentEntrega.getCliente();
            Clientes clienteNew = entrega.getCliente();
            Paquete paqueteOld = persistentEntrega.getPaquete();
            Paquete paqueteNew = entrega.getPaquete();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getCedula());
                entrega.setCliente(clienteNew);
            }
            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getIdPaq());
                entrega.setPaquete(paqueteNew);
            }
            entrega = em.merge(entrega);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getEntregas().remove(entrega);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getEntregas().add(entrega);
                clienteNew = em.merge(clienteNew);
            }
            if (paqueteOld != null && !paqueteOld.equals(paqueteNew)) {
                paqueteOld.setEntrega(null);
                paqueteOld = em.merge(paqueteOld);
            }
            if (paqueteNew != null && !paqueteNew.equals(paqueteOld)) {
                Entrega oldEntregaOfPaquete = paqueteNew.getEntrega();
                if (oldEntregaOfPaquete != null) {
                    oldEntregaOfPaquete.setPaquete(null);
                    oldEntregaOfPaquete = em.merge(oldEntregaOfPaquete);
                }
                paqueteNew.setEntrega(entrega);
                paqueteNew = em.merge(paqueteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = entrega.getId();
                if (findEntrega(id) == null) {
                    throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entrega entrega;
            try {
                entrega = em.getReference(Entrega.class, id);
                entrega.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entrega with id " + id + " no longer exists.", enfe);
            }
            Clientes cliente = entrega.getCliente();
            if (cliente != null) {
                cliente.getEntregas().remove(entrega);
                cliente = em.merge(cliente);
            }
            Paquete paquete = entrega.getPaquete();
            if (paquete != null) {
                paquete.setEntrega(null);
                paquete = em.merge(paquete);
            }
            em.remove(entrega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entrega> findEntregaEntities() {
        return findEntregaEntities(true, -1, -1);
    }

    public List<Entrega> findEntregaEntities(int maxResults, int firstResult) {
        return findEntregaEntities(false, maxResults, firstResult);
    }

    private List<Entrega> findEntregaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entrega.class));
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

    public Entrega findEntrega(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entrega.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entrega> rt = cq.from(Entrega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
