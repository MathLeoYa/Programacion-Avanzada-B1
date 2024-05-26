package Clases;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Bodeguero extends Empleados{
    @Basic
    private String local;
    @OneToMany(mappedBy = "bodeguero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paquete> paquetes;
    public Bodeguero() {
    }

    public Bodeguero(String local) {
        this.local = local;
    }

    public Bodeguero(String local, List<Paquete> paquetes, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cuidad, cedula, apellido, nombre, email);
        this.local = local;
        this.paquetes = paquetes;
    }
    
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
    
    @Override
    public String toString() {
        return "Bodeguero{" + "local=" + local + ", paquetes=" + paquetes + '}';
    }
}
