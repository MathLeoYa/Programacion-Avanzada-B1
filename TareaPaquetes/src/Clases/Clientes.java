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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
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
