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

@Entity
public class Estado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int tipo;
    private String estado;
    private String fecha;
    private String observacion;

    @ManyToOne
    private Paquete paquete;

    public Estado() {
    }

    public Estado(Long id, int tipo, String estado, String fecha, String observacion, Paquete paquete) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.fecha = fecha;
        this.observacion = observacion;
        this.paquete = paquete;
    }

    public Long getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "Estado{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", fecha=" + fecha + ", observacion=" + observacion + ", paquete=" + paquete + '}';
    } 
}
