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
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String codigo;
    private String fecha;
    private String observacion;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Repartidor repartidor;

    @OneToOne
    private Paquete paquete;

    public Entrega() {
    }

    public Entrega(Long id, String codigo, String fecha, String observacion, Repartidor repartidor, Paquete paquete) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.observacion = observacion;
        this.repartidor = repartidor;
        this.paquete = paquete;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "Entrega{" + "id=" + id + ", codigo=" + codigo + ", fecha=" + fecha + ", observacion=" + observacion + ", cliente=" + cliente + ", repartidor=" + repartidor + ", paquete=" + paquete + '}';
    }
}

