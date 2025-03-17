package Ejercicios.Ejercicio01;

public class Main {
    public static void main(String[] args) {


        Coordenada Coord1 = new Coordenada(1, 1);
        Coordenada Coord2 = new Coordenada(3, 3);
        Rectangulo r1 = new Rectangulo(Coord1, Coord2);

        Coordenada Coord3 = new Coordenada(2, 2);
        Coordenada Coord4 = new Coordenada(6, 6);
        Rectangulo r2 = new Rectangulo(Coord3, Coord4);

        //Con el fin de que no sea tan engorroso el code lo que hicimos aquí
        //fue asignar las validaciones a variables y directamente con un
        //nombre mejor preparado colocarlas en los bloques if
        boolean sobrepuestos = Verificador.esSobrePos(r1, r2);
        boolean juntos = Verificador.esJunto(r1, r2);

        // Verificaciones para saber si están juntos, sobrepuestos o disjuntos
        if (sobrepuestos) {
            System.out.println("Rectángulos sobrepuestos");
        } else if (juntos) {
            System.out.println("Rectángulos juntos");
        } else {
            System.out.println("Rectángulos disjuntos");
        }

        ContainerRect contenedor = new ContainerRect(4);
        contenedor.agregarRectangulo(r1);
        contenedor.agregarRectangulo(r2);

        System.out.println("\n" + contenedor);
        System.out.println("\nárea de intersección: " + Rectangulo.areainter(r1, r2));


        new Visualizador(r1, r2);
    }
}
