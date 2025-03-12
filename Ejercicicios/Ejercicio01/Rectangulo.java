package Ejercicicios.Ejercicio01;

public class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;
    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }
    public void setEsquina1 (Coordenada coo) {
        this.esquina1 = coo;
    }
    public void setEsquina2 (Coordenada coo) {
        this.esquina2 = coo;
    }
    public Coordenada getEsquina1 () {
        return this.esquina1;
    }
    public Coordenada getEsquina2 () {
        return this.esquina2;
    }

    public double calcularEuclidiana(){
        return Coordenada.distancia(esquina1, esquina2);
    }

    double area(Rectangulo r1){
        Verificador.calcularVars(r1);
        double areaCalc = (Verificador.mayorX - Verificador.menorX)*(Verificador.mayorY - Verificador.menorY);
        return areaCalc;
    }

    @Override
    public String toString() {
        return "(" + esquina1 + ", " + esquina2 + ")";
    }
    public String info(){
        return "(" + esquina1 + ", " + esquina2 + ")";
    }
}