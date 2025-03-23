package Sesion01.Actividad01;


public class Coordenada{
    private double x;
    private double y;

    //Constructor por defecto que inicializa por en cero (x, y)
    public Coordenada( ){
        this.x = 0.0;
        this.y = 0.0;
    }

    //Constructor
    public Coordenada(double x, double y ){
        this.x = x;
        this.y = y;
    }
    //Constructor
    public Coordenada(Coordenada c ){
        this.x = c.getX();
        this.y = c.getY();
    }

    //métodos setter
    void setX(double  x) {
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    //métodos getter
    double getX(){
        return x;
    }

    double getY(){
        return y;
    }

    //método de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
        double distEuclidi = Math.sqrt(Math.pow(c.getX() - this.x, 2) + Math.pow(c.getY() - this.y, 2));
        return distEuclidi;
    }

    //método de clase que calcula la distancia euclideana
    static double distancia(Coordenada c1, Coordenada c2){
        double distEuclidi = Math.sqrt(Math.pow(c1.getX() - c2.getX(), 2) + Math.pow(c1.getY() - c2.getY(), 2));
        return distEuclidi;
    }
    //método que devuelve los valores de una coordenada en determinado formato
    @Override
    public String toString(){
        return "    x: "+ this.x +
                "\n    y: "+ this.y;
    }
}