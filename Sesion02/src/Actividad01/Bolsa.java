package Actividad01;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Iterator;

public class Bolsa <T> implements Iterable<T> {
    private ArrayList<T> lista = new ArrayList<>();
    private int tope;

    public Bolsa(int tope) {
        super();
        this.tope = tope;
    }
    public void add(T objeto){
        if (lista.size() < tope){
            lista.add(objeto);
        } else {
            throw new RuntimeException("No caben mas");
        }
    }
    public void mostrarBolsa(){
        for (T objeto : lista){
            System.out.println(objeto);
        }
    }

    public Iterator<T> iterator(){
        return lista.iterator();
    }

}
