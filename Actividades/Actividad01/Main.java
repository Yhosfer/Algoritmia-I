package Actividades.Actividad01;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");


        Coordenada Coord1 = new Coordenada(1, 1);
        Coordenada Coord2 = new Coordenada(3, 3);
        System.out.println("Coordenada 1: " + Coord1);

        System.out.println("Distancia Euclidania MI: " + Coord2.distancia(Coord1));
        System.out.println("Distacian Euclidania MC: " + Coordenada.distancia(Coord1, Coord2));

        Rectangulo r1 = new Rectangulo(Coord1, Coord2);

//        System.out.println("Rectangulo 1: " + r1);

        Coordenada Coord3 = new Coordenada(2, 2);
        Coordenada Coord4 = new Coordenada(6, 6);
        Rectangulo r2 = new Rectangulo(Coord3, Coord4);

        // Verificador verificador = new Verificador();
        if(Verificador.esSobrePos(r1, r2)){
            System.out.println("Rectángulos sobrepuestos: " + Verificador.esSobrePos(r1, r2));
        } else if (Verificador.esJunto(r1, r2)){
            System.out.println("Rectángulos juntos: " + Verificador.esJunto(r1, r2));

        } else {System.out.println("Rectángulos disjuntos: " + Verificador.esDisjunto(r1,r2));}


    }
}
