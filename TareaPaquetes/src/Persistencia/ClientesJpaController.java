/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Clases.Clientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Direccion;
import java.util.ArrayList;
import java.util.List;
import Clases.Entrega;
import Clases.Paquete;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mateo
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) throws PreexistingEntityException, Exception {
        if (clientes.getDirecciones() == null) {
            clientes.setDirecciones(new ArrayList<Direccion>());
        }
        if (clientes.getEntregas() == null) {
            clientes.setEntregas(new ArrayList<Entrega>());
        }
        if (clientes.getPaquetes() == null) {
            clientes.setPaquetes(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Direccion> attachedDirecciones = new ArrayList<Direccion>();
            for (Direccion direccionesDireccionToAttach : clientes.getDirecciones()) {
                direccionesDireccionToAttach = em.getReference(direccionesDireccionToAttach.getClass(), direccionesDireccionToAttach.getId());
                attachedDirecciones.add(direccionesDireccionToAttach);
            }
            clientes.setDirecciones(attachedDirecciones);
            List<Entrega> attachedEntregas = new ArrayList<Entrega>();
            for (Entrega entregasEntregaToAttach : clientes.getEntregas()) {
                entregasEntregaToAttach = em.getReference(entregasEntregaToAttach.getClass(), entregasEntregaToAttach.getId());
                attachedEntregas.add(entregasEntregaToAttach);
            }
            clientes.setEntregas(attachedEntregas);
            List<Paquete> attachedPaquetes = new ArrayList<Paquete>();
            for (Paquete paquetesPaqueteToAttach : clientes.getPaquetes()) {
                paquetesPaqueteToAttach = em.getReference(paquetesPaqueteToAttach.getClass(), paquetesPaqueteToAttach.getIdPaq());
                attachedPaquetes.add(paquetesPaqueteToAttach);
            }
            clientes.setPaquetes(attachedPaquetes);
            em.persist(clientes);
            for (Direccion direccionesDireccion : clientes.getDirecciones()) {
                Clientes oldClienteOfDireccionesDireccion = direccionesDireccion.getCliente();
                direccionesDireccion.setCliente(clientes);
                direccionesDireccion = em.merge(direccionesDireccion);
                if (oldClienteOfDireccionesDireccion != null) {
                    oldClienteOfDireccionesDireccion.getDirecciones().remove(direccionesDireccion);
                    oldClienteOfDireccionesDireccion = em.merge(oldClienteOfDireccionesDireccion);
                }
            }
            for (Entrega entregasEntrega : clientes.getEntregas()) {
                Clientes oldClienteOfEntregasEntrega = entregasEntrega.getCliente();
                entregasEntrega.setCliente(clientes);
                entregasEntrega = em.merge(entregasEntrega);
                if (oldClienteOfEntregasEntrega != null) {
                    oldClienteOfEntregasEntrega.getEntregas().remove(entregasEntrega);
                    oldClienteOfEntregasEntrega = em.merge(oldClienteOfEntregasEntrega);
                }
            }
            for (Paquete paquetesPaquete : clientes.getPaquetes()) {
                Clientes oldClienteOfPaquetesPaquete = paquetesPaquete.getCliente();
                paquetesPaquete.setCliente(clientes);
                paquetesPaquete = em.merge(paquetesPaquete);
                if (oldClienteOfPaquetesPaquete != null) {
                    oldClienteOfPaquetesPaquete.getPaquetes().remove(paquetesPaquete);
                    oldClienteOfPaquetesPaquete = em.merge(oldClienteOfPaquetesPaquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientes(clientes.getCedula()) != null) {
                throw new PreexistingEntityException("Clientes " + clientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getCedula());
            List<Direccion> direccionesOld = persistentClientes.getDirecciones();
            List<Direccion> direccionesNew = clientes.getDirecciones();
            List<Entrega> entregasOld = persistentClientes.getEntregas();
            List<Entrega> entregasNew = clientes.getEntregas();
            List<Paquete> paquetesOld = persistentClientes.getPaquetes();
            List<Paquete> paquetesNew = clientes.getPaquetes();
            List<Direccion> attachedDireccionesNew = new ArrayList<Direccion>();
            for (Direccion direccionesNewDireccionToAttach : direccionesNew) {
                direccionesNewDireccionToAttach = em.getReference(direccionesNewDireccionToAttach.getClass(), direccionesNewDireccionToAttach.getId());
                attachedDireccionesNew.add(direccionesNewDireccionToAttach);
            }
            direccionesNew = attachedDireccionesNew;
            clientes.setDirecciones(direccionesNew);
            List<Entrega> attachedEntregasNew = new ArrayList<Entrega>();
            for (Entrega entregasNewEntregaToAttach : entregasNew) {
                entregasNewEntregaToAttach = em.getReference(entregasNewEntregaToAttach.getClass(), entregasNewEntregaToAttach.getId());
                attachedEntregasNew.add(entregasNewEntregaToAttach);
            }
            entregasNew = attachedEntregasNew;
            clientes.setEntregas(entregasNew);
            List<Paquete> attachedPaquetesNew = new ArrayList<Paquete>();
            for (Paquete paquetesNewPaqueteToAttach : paquetesNew) {
                paquetesNewPaqueteToAttach = em.getReference(paquetesNewPaqueteToAttach.getClass(), paquetesNewPaqueteToAttach.getIdPaq());
                attachedPaquetesNew.add(paquetesNewPaqueteToAttach);
            }
            paquetesNew = attachedPaquetesNew;
            clientes.setPaquetes(paquetesNew);
            clientes = em.merge(clientes);
            for (Direccion direccionesOldDireccion : direccionesOld) {
                if (!direccionesNew.contains(direccionesOldDireccion)) {
                    direccionesOldDireccion.setCliente(null);
                    direccionesOldDireccion = em.merge(direccionesOldDireccion);
                }
            }
            for (Direccion direccionesNewDireccion : direccionesNew) {
                if (!direccionesOld.contains(direccionesNewDireccion)) {
                    Clientes oldClienteOfDireccionesNewDireccion = direccionesNewDireccion.getCliente();
                    direccionesNewDireccion.setCliente(clientes);
                    direccionesNewDireccion = em.merge(direccionesNewDireccion);
                    if (oldClienteOfDireccionesNewDireccion != null && !oldClienteOfDireccionesNewDireccion.equals(clientes)) {
                        oldClienteOfDireccionesNewDireccion.getDirecciones().remove(direccionesNewDireccion);
                        oldClienteOfDireccionesNewDireccion = em.merge(oldClienteOfDireccionesNewDireccion);
                    }
                }
            }
            for (Entrega entregasOldEntrega : entregasOld) {
                if (!entregasNew.contains(entregasOldEntrega)) {
                    entregasOldEntrega.setCliente(null);
                    entregasOldEntrega = em.merge(entregasOldEntrega);
                }
            }
            for (Entrega entregasNewEntrega : entregasNew) {
                if (!entregasOld.contains(entregasNewEntrega)) {
                    Clientes oldClienteOfEntregasNewEntrega = entregasNewEntrega.getCliente();
                    entregasNewEntrega.setCliente(clientes);
                    entregasNewEntrega = em.merge(entregasNewEntrega);
                    if (oldClienteOfEntregasNewEntrega != null && !oldClienteOfEntregasNewEntrega.equals(clientes)) {
                        oldClienteOfEntregasNewEntrega.getEntregas().remove(entregasNewEntrega);
                        oldClienteOfEntregasNewEntrega = em.merge(oldClienteOfEntregasNewEntrega);
                    }
                }
            }
            for (Paquete paquetesOldPaquete : paquetesOld) {
                if (!paquetesNew.contains(paquetesOldPaquete)) {
                    paquetesOldPaquete.setCliente(null);
                    paquetesOldPaquete = em.merge(paquetesOldPaquete);
                }
            }
            for (Paquete paquetesNewPaquete : paquetesNew) {
                if (!paquetesOld.contains(paquetesNewPaquete)) {
                    Clientes oldClienteOfPaquetesNewPaquete = paquetesNewPaquete.getCliente();
                    paquetesNewPaquete.setCliente(clientes);
                    paquetesNewPaquete = em.merge(paquetesNewPaquete);
                    if (oldClienteOfPaquetesNewPaquete != null && !oldClienteOfPaquetesNewPaquete.equals(clientes)) {
                        oldClienteOfPaquetesNewPaquete.getPaquetes().remove(paquetesNewPaquete);
                        oldClienteOfPaquetesNewPaquete = em.merge(oldClienteOfPaquetesNewPaquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = clientes.getCedula();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
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
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<Direccion> direcciones = clientes.getDirecciones();
            for (Direccion direccionesDireccion : direcciones) {
                direccionesDireccion.setCliente(null);
                direccionesDireccion = em.merge(direccionesDireccion);
            }
            List<Entrega> entregas = clientes.getEntregas();
            for (Entrega entregasEntrega : entregas) {
                entregasEntrega.setCliente(null);
                entregasEntrega = em.merge(entregasEntrega);
            }
            List<Paquete> paquetes = clientes.getPaquetes();
            for (Paquete paquetesPaquete : paquetes) {
                paquetesPaquete.setCliente(null);
                paquetesPaquete = em.merge(paquetesPaquete);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
