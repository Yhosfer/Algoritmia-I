package Ejercicio04;

public class Golosina implements Comparable<Golosina> {
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    @Override
    public String toString() {
        return (nombre+","+ peso);
    }

    @Override
    public int compareTo(Golosina o) {
        if (this.getPeso() == o.getPeso() && this.getNombre().equals(o.getNombre())) {
            return 0;
        }
        return 1;
    }
}
