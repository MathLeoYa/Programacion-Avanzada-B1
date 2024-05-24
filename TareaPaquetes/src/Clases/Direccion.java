
package Clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigo;
    private String calle1;
    private String calle2;
    private String referenda;
    private boolean actual;

    @ManyToOne
    private Clientes cliente;

    public Direccion() {
    }

    public Direccion(int id, String codigo, String calle1, String calle2, String referenda, boolean actual, Clientes cliente) {
        this.id = id;
        this.codigo = codigo;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.referenda = referenda;
        this.actual = actual;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getReferenda() {
        return referenda;
    }

    public void setReferenda(String referenda) {
        this.referenda = referenda;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
}
