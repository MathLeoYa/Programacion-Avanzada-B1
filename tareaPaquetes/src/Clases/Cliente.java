/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
/**
 *
 * @author Usuario iTC
 */
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Persona implements Serializable {
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

    public Cliente() {
    }

    public Cliente(String celular, String cedula, String apellidos, String nombres, String mail) {
        super(cedula, apellidos, nombres, mail);
        this.celular = celular;
    }
    
    public Cliente(String celular, List<Direccion> direcciones, List<Entrega> entregas, String cedula, String apellidos, String nombres, String mail) {
        super(cedula, apellidos, nombres, mail);
        this.celular = celular;
        this.direcciones = direcciones;
        this.entregas = entregas;
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
        return "Cliente{" + "celular=" + celular + ", direcciones=" + direcciones + ", entregas=" + entregas + ", paquetes=" + paquetes + '}';
    }  
}
