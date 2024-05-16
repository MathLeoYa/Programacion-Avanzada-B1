
package Logica;

public class Personas {
    String cedula;
    String apellido;
    String nombre;
    String email;

    public Personas() {
    }

    public Personas(String cedula, String apellido, String nombre, String email) {
        this.cedula = cedula;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
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

    @Override
    public String toString() {
        return "Personas{" + "cedula=" + cedula + ", apellido=" + apellido + ", nombre=" + nombre + ", email=" + email + '}';
    }
    
}
