package Clases;

import java.util.ArrayList;

public class Clientes extends Personas {
    private String celular;
    public ArrayList<Direccion> direcciones;

    public Clientes() {
    }

    public Clientes(String celular, ArrayList<Direccion> direcciones, String cedula, String apellido, String nombre, String email) {
        super(cedula, apellido, nombre, email);
        this.celular = celular;
        this.direcciones = direcciones;
    }
    
}
