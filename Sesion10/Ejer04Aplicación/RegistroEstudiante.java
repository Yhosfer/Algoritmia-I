package Sesion10.Ejer04Aplicación;

public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
    private String nombre;
    private int codigo;

    public RegistroEstudiante(int codigo, String nombre) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    @Override
    public int compareTo(RegistroEstudiante otro) {
        return Integer.compare(this.getCodigo(), otro.getCodigo());
    }
    @Override
    public String toString() {
        return codigo+" - "+nombre;
    }
}
