package Logica;
public class Empleados extends Personas{
    private int idEmpl;
    private String cuidad;  

    public Empleados() {
    }

    public Empleados(int idEmpl, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.idEmpl = idEmpl;
        this.cuidad = cuidad;
    }

    public int getIdEmpl() {
        return idEmpl;
    }

    public void setIdEmpl(int idEmpl) {
        this.idEmpl = idEmpl;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
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
        return "Empleados{" + "idEmpl=" + idEmpl + ", cuidad=" + cuidad + '}';
    }
}
