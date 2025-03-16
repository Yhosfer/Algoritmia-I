package Ejercicicios.Ejercicio01;

public class ContainerRect {

    private int tamanioArrayRects;
    // Declaración de Arrays
    private Rectangulo[] rects;
    private Double[] distanciaEu;
    private Double[] areaRec;
    private int numRec;


    public ContainerRect(int tamanio){
        // Inicializa los arreglos
        rects = new Rectangulo[tamanio];
        distanciaEu = new Double[tamanio];
        areaRec = new Double[tamanio];
        this.numRec = 0;
        tamanioArrayRects = tamanio;
    }
    // El siguiente método agrega los datos del rectangulo
    // A los arreglos correspondientes
    public void agregarRectangulo(Rectangulo rect){
        if (numRec <= tamanioArrayRects ){
            // Accedemos al elemnto "numRec" array y asiganamos "rect"
            rects[numRec] = rect;
            numRec++;
            distanciaEu[numRec] = rect.calcularEuclidiana();
            areaRec[numRec] = rect.area(rect);

        } else {
            //
            System.out.println("Array lleno ");
        }
    }

    @Override
    public String toString() {
        // Se crean cadenas formateadas
        StringBuilder cadena = new StringBuilder(String.format("%-11s %-25s %-15s %-15s", "Rectángulo", "Coordenadas", "Distancia", "Área"));
        Rectangulo rec;
        for (int x = 0; x < numRec; x++){
            rec = rects[x];
            cadena.append(String.format("\n%-11d %-25s %-15f %-15f", x+1, rec.info(), rec.calcularEuclidiana(), rec.area(rec)));
        }
        return cadena.toString();
    }
}
