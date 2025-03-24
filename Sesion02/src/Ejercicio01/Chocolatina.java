package Ejercicio01;

public class Chocolatina implements Comparable<Chocolatina> {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Chocolatina (" + "marca: " + marca + ')';
    }

    @Override
    public int compareTo(Chocolatina choco) {
        return marca.compareTo(choco.getMarca());
    }
}
