package Ejercicios.Ejercicio01;

public class ContainerRect {

    private int tamanioArrayRects;
    private Rectangulo[] rects;
    private Double[] distanciaEu;
    private Double[] areaRec;
    private int numRec;  // Empieza en 1

    public ContainerRect(int tamanio) {
        //El 1 en numrec se coloca con el fin de que el contador sea de acuerdo
        //a lo que uno busca de un contador real sin considerar el 0.
        rects = new Rectangulo[tamanio + 1];
        distanciaEu = new Double[tamanio + 1];
        areaRec = new Double[tamanio + 1];
        this.numRec = 1;
        this.tamanioArrayRects = tamanio;

    }

    public void agregarRectangulo(Rectangulo rect) {
        if (numRec <= tamanioArrayRects) {
            rects[numRec] = rect;
            distanciaEu[numRec] = rect.calcularEuclidiana();
            areaRec[numRec] = rect.area();
            numRec++;
        } else {
            System.out.println("Array lleno, no se puede agregar más rectángulos.");
        }
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder(
                String.format("%-11s %-25s %-15s %-15s", "Rectángulo", "Coordenadas", "Distancia", "Área")
        );

        for (int x = 1; x < numRec; x++) {
            if (rects[x] != null) {
                cadena.append(String.format("\n%-11d %-25s %-15f %-15f",
                        x, rects[x].info(), distanciaEu[x], areaRec[x]));
            }
        }

        return cadena.toString();
    }
}
