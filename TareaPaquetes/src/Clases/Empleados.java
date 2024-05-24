package Clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Empleados extends Personas implements Serializable{
    private String cuidad;

    public Empleados() {
    }

    public Empleados(String cuidad) {
        this.cuidad = cuidad;
    }

    public Empleados(String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.cuidad = cuidad;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }
    
}
