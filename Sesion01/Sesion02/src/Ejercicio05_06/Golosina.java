package Ejercicio05_06;

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
        return ("("+nombre+","+ peso+")");
    }

    @Override
    public int compareTo(Golosina o) {
        // Comparar primero por peso
        int resultado = Double.compare(this.peso, o.peso);
        if (resultado == 0) {
            // Si el peso es el mismo, comparar por nombre (alfabéticamente)
            return this.nombre.compareTo(o.nombre);
        }
        return resultado;
    }
}
