
package Logica;
public class Direccion {
    private String codigo;
    private String calle1;
    private String calle2;
    private String referencia;
    private int actual;

    public Direccion() {
    }

    public Direccion(String codigo, String calle1, String calle2, String referencia, int actual) {
        this.codigo = codigo;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.referencia = referencia;
        this.actual = actual;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    @Override
    public String toString() {
        return "Direccion{" + "codigo=" + codigo + ", calle1=" + calle1 + ", calle2=" + calle2 + ", referencia=" + referencia + ", actual=" + actual + '}';
    }
    
}
