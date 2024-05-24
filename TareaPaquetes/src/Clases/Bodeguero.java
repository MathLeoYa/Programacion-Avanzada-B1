package Clases;

import javax.persistence.Entity;

@Entity
public class Bodeguero extends Empleados{
    private String local;

    public Bodeguero() {
    }

    public Bodeguero(String local) {
        this.local = local;
    }

    public Bodeguero(String local, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cuidad, cedula, apellido, nombre, email);
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
    
}
