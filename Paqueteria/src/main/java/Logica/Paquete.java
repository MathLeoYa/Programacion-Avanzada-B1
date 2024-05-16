package Logica;

import java.util.ArrayList;
import java.util.Date;

public class Paquete {
    int idPaq;
    String codigo;
    String descripcion;
    Double peso;
    Double largo;
    Double ancho;
    ArrayList<Estado> estado;
    
    public Paquete() {
        Date date = new Date();
        this.estado = new ArrayList<>();
        Estado esta1 = new Estado(0,"Creado", date, "");
        Estado esta2 = new Estado(1,"Despachado", null, "");
        Estado esta3 = new Estado(2,"Entregado", null, "");
        Estado esta4 = new Estado(3,"Pendiente", null, "");
        estado.add(esta1);
        estado.add(esta2);
        estado.add(esta3);
        estado.add(esta4);
    }

    public Paquete(int idPaq, String codigo, String descripcion, Double peso, Double largo, Double ancho,
            ArrayList<Estado> estado) {
        this.idPaq = idPaq;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.estado = estado;
    }

    public int getIdPaq() {
        return idPaq;
    }

    public void setIdPaq(int idPaq) {
        this.idPaq = idPaq;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public ArrayList<Estado> getEstado() {
        return estado;
    }

    public void setEstado(ArrayList<Estado> estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaq=" + idPaq + ", codigo=" + codigo + ", descripcion=" + descripcion + ", peso=" + peso + ", largo=" + largo + ", ancho=" + ancho + ", estado=" + estado + '}';
    }
    
    
}
