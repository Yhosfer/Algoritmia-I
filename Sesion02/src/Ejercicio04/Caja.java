package Ejercicio04;

import java.util.ArrayList;
import java.util.Iterator;

public class Caja <T> implements Iterable<T>{

    private String color;
    private ArrayList<T> lista = new ArrayList<>();

    public Caja(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void agregarObjeto(T objeto){
        lista.add(objeto);
    }

    public Iterator<T> iterator(){
        return lista.iterator();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T objeto : lista) {
            sb.append("[").append(objeto.toString()).append("]");
        }
        return sb.toString();
    }
}