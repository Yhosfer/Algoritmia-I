package Sesion01.Actividad01;

public class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada  c2) {
        this.esquina1 = c1;
        this.esquina2 = c2;
    }
    // Setters y Getters
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
    // Metodo toString
    @Override
    public String toString() {
        return "esquina1 = " + esquina1 + ", esquina2 = " + esquina2;
    }
}