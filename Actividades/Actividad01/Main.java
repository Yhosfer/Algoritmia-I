package Actividades.Actividad01;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Coordenada Coord1 = new Coordenada(1, 1);
        Coordenada Coord2 = new Coordenada(3, 3);
        System.out.println("Coordenada 1: " + Coord1);

        System.out.println("Distancia Euclidania MI: " + Coord2.distancia(Coord1));
        System.out.println("Distacian Euclidania MC: " + Coordenada.distancia(Coord1, Coord2));
    }
}
