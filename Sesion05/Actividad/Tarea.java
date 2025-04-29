package Sesion05.Actividad;

public class Tarea implements Comparable<Tarea> {

    private String titulo;
    private int prioridad;

    public Tarea(String titulo, int prioridad) {

        this.titulo = titulo;
        this.prioridad = prioridad;

    }

    public String getTitulo() {

        return titulo;

    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;

    }

    public int getPrioridad() {

        return prioridad;

    }

    public void setPrioridad(int prioridad) {

        this.prioridad = prioridad;

    }

    @Override
    public int compareTo(Tarea otra) {
        if (this.prioridad < otra.prioridad) {
            return -1;
        } else if (this.prioridad > otra.prioridad) {
            return 1;
        } else {
            if (this.titulo.equals(otra.titulo)){
                return 0;
            }
            return -2;
        }
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }

}
