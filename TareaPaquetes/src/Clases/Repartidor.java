package Clases;
public class Repartidor extends Empleados{
    private int zona;

    public Repartidor() {
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
