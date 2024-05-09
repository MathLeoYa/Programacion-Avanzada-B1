package Clases;
public class Empleados {
    private String cuidad;

    public Empleados() {
    }

    public Empleados(String cuidad) {
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
