/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario iTC
 */
import java.util.List;
import javax.persistence.*;

@Entity
public class Repartidor extends Empleado {

    private int zona;

    @OneToMany(mappedBy = "repartidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrega> entrega;

    public Repartidor() {
    }

    public Repartidor(int zona, List<Entrega> entrega) {
        this.zona = zona;
        this.entrega = entrega;
    }

    public Repartidor(int zona, String ciudad, String cedula, String apellidos, String nombres, String mail) {
        super(ciudad, cedula, apellidos, nombres, mail);
        this.zona = zona;
    }

    public Repartidor(int zona, List<Entrega> entrega, String ciudad, String cedula, String apellidos, String nombres, String mail) {
        super(ciudad, cedula, apellidos, nombres, mail);
        this.zona = zona;
        this.entrega = entrega;
    }

    public int getZona() {
        return zona;
    }

    public List<Entrega> getEntrega() {
        return entrega;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setEntrega(List<Entrega> entrega) {
        this.entrega = entrega;
    }

    @Override
    public String toString() {
        return "Repartidor{" + "zona=" + zona + ", entrega=" + entrega + '}';
    }

}
