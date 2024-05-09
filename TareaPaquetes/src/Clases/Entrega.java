package Clases;

import java.util.Date;

public class Entrega {
    private String codigo;
    private Date fecha;
    private String observacion;

    public Entrega() {
    }

    public Entrega(String codigo, Date fecha, String observacion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Entrega{" + "codigo=" + codigo + ", fecha=" + fecha + ", observacion=" + observacion + '}';
    }
    
}
