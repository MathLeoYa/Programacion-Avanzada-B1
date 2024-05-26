package Clases;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Repartidor extends Empleados{
    private int zona;
    
    @OneToMany(mappedBy = "repartidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrega> entregas;
    
    public Repartidor() {
    }
    public Repartidor(int zona) {
        this.zona = zona;
    }
    public Repartidor(int zona, List<Entrega> entregas, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cuidad, cedula, apellido, nombre, email);
        this.zona = zona;
        this.entregas = entregas;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    @Override
    public String toString() {
        return "Repartidor{" + "zona=" + zona + ", entregas=" + entregas + '}';
    }
}
