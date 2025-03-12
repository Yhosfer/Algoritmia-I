package Ejercicicios.Ejercicio01;


import java.util.ArrayList;

public class ContainerRect {
    private ArrayList<Rectangulo> rects;
    private ArrayList<Double> distanciaEuclidiana;
    private ArrayList<Double> areasRec;
    static int tamanioArrayRects = 4;
    static int numRec;

    public ContainerRect(){
        rects = new ArrayList<>();
        distanciaEuclidiana = new ArrayList<>();
        areasRec = new ArrayList<>();
        this.numRec = 0;
    }
    public void agregarRectangulo(Rectangulo rect){
        if (numRec <= tamanioArrayRects ){
            rects.add(rect);
            numRec++;

            distanciaEuclidiana.add(rect.calcularEuclidiana());
            areasRec.add(rect.area(rect));
        } else {
            System.out.println("Array lleno ");
        }
    }

//    static private double distanciaEu(Rectangulo rect1){
//
//    }
//    static private void areaRec(Rectangulo rect1){
//
//    }
    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder(String.format("%-11s %-25s %-15s %-15s", "Rectángulo", "Coordenadas", "Distancia", "Área"));
        Rectangulo rec;
        for (int x = 0; x < rects.size(); x++){
            rec = rects.get(x);
            cadena.append(String.format("\n%-11d %-25s %-15f %-15f", x, rec.info(), rec.calcularEuclidiana(), rec.area(rec)));
        }
        return cadena.toString();
    }
}
