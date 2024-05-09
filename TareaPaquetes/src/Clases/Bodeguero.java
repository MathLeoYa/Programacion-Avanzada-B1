package Clases;
public class Bodeguero extends Empleados{
    private String local;
    
    public Bodeguero() {
    }

    public Bodeguero(String local) {
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Bodeguero{" + "local=" + local + '}';
    }
    
}
