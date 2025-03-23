package Sesion01.Ejercicio01;

public class Coordenada {
    private double x;
    private double y;

    // Constructor por defecto
    public Coordenada() {
        this.x = 0.0;
        this.y = 0.0;
    }

    // Constructor con parámetros
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Constructor copia
    public Coordenada(Coordenada c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    // Método de instancia que calcula la distancia euclidiana
    public double distancia(Coordenada c) {
        return Math.sqrt(Math.pow(c.getX() - this.x, 2) + Math.pow(c.getY() - this.y, 2));
    }

    // Método de clase que calcula la distancia euclidiana entre dos coordenadas
    public static double distancia(Coordenada c1, Coordenada c2) {
        return Math.sqrt(Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2));
    }

    // Representación en cadena de la coordenada
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
