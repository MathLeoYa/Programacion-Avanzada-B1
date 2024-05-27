/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
/**
 *
 * @author Usuario iTC
 */
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
public class Paquete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpaq;
    private String codigo;
    private String descripcion;
    private int peso;
    private int alto;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estado> estados;

    @OneToOne(mappedBy = "paquete", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Entrega entrega;
    
    @ManyToOne
    private Cliente cliente;

    public Paquete() {
    }

    public Paquete(int idpaq, String codigo, String descripcion, int peso, int alto, List<Estado> estados, Entrega entrega, Cliente cliente) {
        this.idpaq = idpaq;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.estados = estados;
        this.entrega = entrega;
        this.cliente = cliente;
    }

    public int getIdpaq() {
        return idpaq;
    }

    public void setIdpaq(int idpaq) {
        this.idpaq = idpaq;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idpaq=" + idpaq + ", codigo=" + codigo + ", descripcion=" + descripcion + ", peso=" + peso + ", alto=" + alto + ", estados=" + estados + ", entrega=" + entrega + ", cliente=" + cliente + '}';
    }
}

