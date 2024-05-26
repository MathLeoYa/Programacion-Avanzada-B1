/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Clases.Bodeguero;
import Clases.Clientes;
import Clases.Direccion;
import Clases.Entrega;
import Clases.Estado;
import Clases.Paquete;
import Clases.Repartidor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author V I C T U S
 */
public class ControladorPersistence {
    DireccionJpaController dirJPA = new DireccionJpaController();
    ClientesJpaController cliJPA = new ClientesJpaController();
    
    BodegueroJpaController bodJPA = new BodegueroJpaController();
    
    EstadoJpaController estJPA = new EstadoJpaController();
    PaqueteJpaController paqJPA = new PaqueteJpaController();
    
    RepartidorJpaController repJPA = new RepartidorJpaController();
    
    EntregaJpaController entJPA = new EntregaJpaController();
    
    //apartado
    EmpleadosJpaController empJPA = new EmpleadosJpaController();
    PersonasJpaController perJPA = new PersonasJpaController();
    
// Direccion dirJPA
    public void agregarDireccion(Direccion direccion) {
        dirJPA.create(direccion);
    }
    public  List<Direccion> listarDireccion() {
        return dirJPA.findDireccionEntities();
    }
    
// Cliente cliJPA
    public void crearCliente(Clientes cliente) {
        try {
            cliJPA.create(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Clientes buscarCliente(String cedula) {
        return cliJPA.findClientes(cedula);
    }

    public void actualizarCliente(Clientes cliente) throws Exception {
        cliJPA.edit(cliente);
    }

    public  List<Clientes> listarClientes() {
        return cliJPA.findClientesEntities();
    }
    
//Bodeguero bodJPA
    public void crearBodeguero (Bodeguero bodeguero) {
        try {
            bodJPA.create(bodeguero);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bodeguero buscarBodeguero(String cedula) {
        return bodJPA.findBodeguero(cedula);
    }

    public void actualizarBodeguero(Bodeguero bodeguero) throws Exception {
        bodJPA.edit(bodeguero);
    }

    public  List<Bodeguero> listarBodeguero() {
        return bodJPA.findBodegueroEntities();
    }
    
//Estado estJPA
    public void crearEstado (Estado estado) {
        try {
            estJPA.create(estado);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  List<Estado> listarEstado() {
        return estJPA.findEstadoEntities();
    }
    
//Paquete paqJPA
    public void crearPaquete(Paquete paquete) {
        try {
            paqJPA.create(paquete);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Paquete buscarPaquete(int id) {
        return paqJPA.findPaquete(id);
    }

    public void actualizarPaquete(Paquete paquete) throws Exception {
        paqJPA.edit(paquete);
    }

    public  List<Paquete> listarPaquete() {
        return paqJPA.findPaqueteEntities();
    }
    
//Repartidor repJPA
    public void crearRepartidor(Repartidor repartidor) {
        try {
            repJPA.create(repartidor);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Repartidor buscarRepartidor(String cedula) {
        return repJPA.findRepartidor(cedula);
    }

    public void actualizarRepartidor(Repartidor repartidor) throws Exception {
        repJPA.edit(repartidor);
    }

    public  List<Repartidor> listarRepartidor() {
        return repJPA.findRepartidorEntities();
    }
//Entrega entJPA
    public void crearEntrega(Entrega entrega) {
        try {
            entJPA.create(entrega);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Entrega buscarEntrega(int id) {
        return entJPA.findEntrega(id);
    }

    public void actualizarEntrega(Entrega entrega) throws Exception {
        entJPA.edit(entrega);
    }

    public  List<Entrega> listarEntrega() {
        return entJPA.findEntregaEntities();
    }
}
