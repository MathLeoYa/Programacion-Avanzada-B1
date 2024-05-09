package Clases;
import java.util.Date;
public class Estado {
    private int tipo;
    private String estado;
    private Date fecha;
    private String observacion;

    public Estado() {
    }

    public Estado(int tipo, String estado, Date fecha, String observacion) {
        this.tipo = tipo;
        this.estado = estado;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        return "Estado{" + "tipo=" + tipo + ", estado=" + estado + ", fecha=" + fecha + ", observacion=" + observacion + '}';
    }
}
