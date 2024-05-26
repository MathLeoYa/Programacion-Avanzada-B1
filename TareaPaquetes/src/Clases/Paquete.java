package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaq;
    String codigo;
    String descripcion;
    Double peso;
    Double largo;
    Double ancho;
        
    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estado> estados;

    @OneToOne
    private Entrega entrega;
    @ManyToOne
    private Clientes cliente;
    @ManyToOne
    private Bodeguero bodegueros;

    public Paquete() {
    }

    public Paquete(int idPaq, String codigo, String descripcion, Double peso, Double largo, Double ancho, List<Estado> estados, Bodeguero bodegueros) {
        this.idPaq = idPaq;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.estados = estados;
        this.bodegueros = bodegueros;
    }
    
    public Paquete(int idPaq, String codigo, String descripcion, Double peso, Double largo, Double ancho, List<Estado> estados, Entrega entrega, Clientes cliente, Bodeguero bodegueros) {
        this.idPaq = idPaq;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.estados = estados;
        this.entrega = entrega;
        this.cliente = cliente;
        this.bodegueros = bodegueros;
    }
    
/*
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
*/

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

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Bodeguero getBodegueros() {
        return bodegueros;
    }

    public void setBodegueros(Bodeguero bodegueros) {
        this.bodegueros = bodegueros;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaq=" + idPaq + ", codigo=" + codigo + ", descripcion=" + descripcion + ", peso=" + peso + ", largo=" + largo + ", ancho=" + ancho + ", estados=" + estados + ", entrega=" + entrega + ", cliente=" + cliente + ", bodegueros=" + bodegueros + '}';
    }
}
