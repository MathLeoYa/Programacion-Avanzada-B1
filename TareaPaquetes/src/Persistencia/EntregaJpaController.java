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
import Clases.Cliente;
import Clases.Entrega;
import Clases.Repartidor;
import Clases.Paquete;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USER
 */
public class EntregaJpaController implements Serializable {

    public EntregaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaPU");
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
            Cliente cliente = entrega.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getCedula());
                entrega.setCliente(cliente);
            }
            Repartidor repartidor = entrega.getRepartidor();
            if (repartidor != null) {
                repartidor = em.getReference(repartidor.getClass(), repartidor.getCedula());
                entrega.setRepartidor(repartidor);
            }
            Paquete paquete = entrega.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getIdpaq());
                entrega.setPaquete(paquete);
            }
            em.persist(entrega);
            if (cliente != null) {
                cliente.getEntregas().add(entrega);
                cliente = em.merge(cliente);
            }
            if (repartidor != null) {
                repartidor.getEntrega().add(entrega);
                repartidor = em.merge(repartidor);
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
            Cliente clienteOld = persistentEntrega.getCliente();
            Cliente clienteNew = entrega.getCliente();
            Repartidor repartidorOld = persistentEntrega.getRepartidor();
            Repartidor repartidorNew = entrega.getRepartidor();
            Paquete paqueteOld = persistentEntrega.getPaquete();
            Paquete paqueteNew = entrega.getPaquete();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getCedula());
                entrega.setCliente(clienteNew);
            }
            if (repartidorNew != null) {
                repartidorNew = em.getReference(repartidorNew.getClass(), repartidorNew.getCedula());
                entrega.setRepartidor(repartidorNew);
            }
            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getIdpaq());
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
            if (repartidorOld != null && !repartidorOld.equals(repartidorNew)) {
                repartidorOld.getEntrega().remove(entrega);
                repartidorOld = em.merge(repartidorOld);
            }
            if (repartidorNew != null && !repartidorNew.equals(repartidorOld)) {
                repartidorNew.getEntrega().add(entrega);
                repartidorNew = em.merge(repartidorNew);
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
                Long id = entrega.getId();
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Cliente cliente = entrega.getCliente();
            if (cliente != null) {
                cliente.getEntregas().remove(entrega);
                cliente = em.merge(cliente);
            }
            Repartidor repartidor = entrega.getRepartidor();
            if (repartidor != null) {
                repartidor.getEntrega().remove(entrega);
                repartidor = em.merge(repartidor);
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

    public Entrega findEntrega(Long id) {
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
