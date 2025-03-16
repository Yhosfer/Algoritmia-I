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

    public double area(Rectangulo r1){
        Verificador.calcularVars(r1);
        double areaCalc = (Verificador.mayorX - Verificador.menorX)*(Verificador.mayorY - Verificador.menorY);
        return areaCalc;
    }
    static double areainter(Rectangulo r1, Rectangulo r2){
        Verificador.calcularVars(r1);
        double r1minX = Verificador.menorX, r1maxX = Verificador.mayorX, r1minY = Verificador.menorY, r1maxY = Verificador.mayorY;

        Verificador.calcularVars(r2);
        double r2minX = Verificador.menorX, r2maxX = Verificador.mayorX, r2minY = Verificador.menorY, r2maxY = Verificador.mayorY;

        double base = Math.min(r1maxX, r2maxX) - Math.max(r1minX, r2minX);
        double altura = Math.min(r1maxY, r2maxY) - Math.max(r1minY, r2minY);
        return base * altura;
        }

    @Override
    public String toString() {
        return "(" + esquina1 + ", " + esquina2 + ")";
    }
    public String info(){
        return "(" + esquina1 + ", " + esquina2 + ")";
    }
}
