package Clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Clientes extends Personas implements Serializable{
    private String celular;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrega> entregas;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Paquete> paquetes;
    
    public void hacerActual(Direccion direccion) {
        for (Direccion dir : direcciones) {
            dir.setActual(false);
        }
        direccion.setActual(true);
    }

    public Clientes() {
    }

    public Clientes(String celular, List<Direccion> direcciones, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.celular = celular;
        this.direcciones = direcciones;
    }

    public Clientes(String celular, List<Direccion> direcciones, List<Paquete> paquetes, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.celular = celular;
        this.direcciones = direcciones;
        this.paquetes = paquetes;
    }

    public Clientes(String celular, List<Direccion> direcciones, List<Entrega> entregas, List<Paquete> paquetes, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.celular = celular;
        this.direcciones = direcciones;
        this.entregas = entregas;
        this.paquetes = paquetes;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
  
    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    @Override
    public String toString() {
        return "Clientes{" + "direcciones=" + direcciones + ", entregas=" + entregas + ", paquetes=" + paquetes + '}';
    }
    
}
