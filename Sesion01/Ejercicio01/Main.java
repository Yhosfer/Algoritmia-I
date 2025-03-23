package Sesion01.Ejercicio01;

public class Main {
    public static void main(String[] args) {


        Coordenada Coord1 = new Coordenada(1, 1);
        Coordenada Coord2 = new Coordenada(3, 3);
        Rectangulo r1 = new Rectangulo(Coord1, Coord2);

        Coordenada Coord3 = new Coordenada(2, 2);
        Coordenada Coord4 = new Coordenada(6, 6);
        Rectangulo r2 = new Rectangulo(Coord3, Coord4);


        // Se llama al metodo static para verificar si esta sobre o juntos o disjuntos
        Verificador.verificar(r1, r2);

        ContainerRect contenedor = new ContainerRect(4);
        contenedor.agregarRectangulo(r1);
        contenedor.agregarRectangulo(r2);

        System.out.println("\n" + contenedor);
        System.out.println("\nárea de intersección: " + Rectangulo.areainter(r1, r2));


        new Visualizador(r1, r2);
    }

}
