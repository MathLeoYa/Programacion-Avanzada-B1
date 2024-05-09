package Clases;
public class Empleados extends Personas{
    private String cuidad;
    public Empleados() {
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

    @Override
    public String toString() {
        return "Empleados{" + "cuidad=" + cuidad + '}';
    }
    
}
