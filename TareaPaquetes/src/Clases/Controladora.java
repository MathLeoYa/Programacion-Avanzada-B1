package Clases;

import Persistencia.ControladorPersistence;
import java.util.List;

public class Controladora {
    
    ControladorPersistence controlPersistencia = new ControladorPersistence();
   
// Direccion 
    public void agregarDireccion(Direccion direccion) {
        controlPersistencia.agregarDireccion(direccion);
    }
    public List<Direccion> listarDireccion() {
        return controlPersistencia.listarDireccion();
    }

    
// Cliente  
    public void crearCliente(Clientes cliente) {
        controlPersistencia.crearCliente(cliente);
    }
    
    public Clientes buscarCliente(String cedula) {
        return controlPersistencia.buscarCliente(cedula);
    }
    
    public void actualizarCliente(Clientes cliente) throws Exception {
        controlPersistencia.actualizarCliente(cliente);
    }
    
   public List<Clientes> listarClientes() {
        return controlPersistencia.listarClientes();
    }

//Bodeguero
    public void crearBodeguero(Bodeguero bodeguero) {
        controlPersistencia.crearBodeguero(bodeguero);
    }
    
    public Bodeguero buscarBodeguero(String cedula) {
        return controlPersistencia.buscarBodeguero(cedula);
    }
    
    public void actualizarBodeguero(Bodeguero bodeguero) throws Exception {
        controlPersistencia.actualizarBodeguero(bodeguero);
    }
    
   public List<Bodeguero> listarBodeguero() {
        return controlPersistencia.listarBodeguero();
    }
   
// Estado 
    public void agregarEstado(Estado estado) {
        controlPersistencia.crearEstado(estado);
    }
    public List<Estado> listarEstado() {
        return controlPersistencia.listarEstado();
    }
    
//Paquete
    public void crearPaquete(Paquete paquete) {
        controlPersistencia.crearPaquete(paquete);
    }
    
    public Paquete buscarPaquete(int id) {
        return controlPersistencia.buscarPaquete(id);
    }
    
    public void actualizarPaquete(Paquete paquete) throws Exception {
        controlPersistencia.actualizarPaquete(paquete);
    }
    
   public List<Paquete> listarPaquete() {
        return controlPersistencia.listarPaquete();
    }
   
//Repartidor
    public void crearRepartidor(Repartidor repartidor) {
        controlPersistencia.crearRepartidor(repartidor);
    }
    
    public Repartidor buscarRepartidor(String cedula) {
        return controlPersistencia.buscarRepartidor(cedula);
    }
    
    public void actualizarRepartidor(Repartidor repartidor) throws Exception {
        controlPersistencia.actualizarRepartidor(repartidor);
    }
    
   public List<Repartidor> listarRepartidor() {
        return controlPersistencia.listarRepartidor();
    }
   
//Entrega
    public void crearEntrega(Entrega entrega) {
        controlPersistencia.crearEntrega(entrega);
    }
    
    public Entrega buscarEntrega(int id) {
        return controlPersistencia.buscarEntrega(id);
    }
    
    public void actualizarRepartidor(Entrega entrega) throws Exception {
        controlPersistencia.actualizarEntrega(entrega);
    }
    
   public List<Entrega> listarEntrega() {
        return controlPersistencia.listarEntrega();
    }
}
