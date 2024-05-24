package Clases;

import javax.persistence.Entity;

@Entity
public class Repartidor extends Empleados{
    private int zona;

    public Repartidor() {
    }

    public Repartidor(int zona) {
        this.zona = zona;
    }
    
    public Repartidor(int zona, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cuidad, cedula, apellido, nombre, email);
        this.zona = zona;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }
    
}
