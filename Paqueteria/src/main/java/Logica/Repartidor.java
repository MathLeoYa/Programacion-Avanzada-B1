package Logica;
public class Repartidor extends Empleados {
    private int idRepar;
    private int zona;
    private 

    public Repartidor() {
    }

    public Repartidor(int idRepar, int zona, int idEmpl, String cuidad, String cedula, String apellido, String nombre, String email) {
        super(idEmpl, cuidad, cedula, apellido, nombre, email);
        this.idRepar = idRepar;
        this.zona = zona;
    }
    

}
