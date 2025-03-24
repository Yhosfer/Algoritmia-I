package Ejer03__v2;

import java.util.ArrayList;
import java.util.Iterator;



public class Cajoneria <T extends Comparable<T>> implements Iterable<Caja<T>> {

    private ArrayList<Caja<T>> caja = new ArrayList<>();


    public void agregarCaja(Caja<T> caja){

        this.caja.add(caja);

    }

    public void search(T objeto){
        int pos = 0;
        for (Caja<T> c : caja) {
            for (T x : c){
                if(x.compareTo(objeto) == 0){
                    System.out.println("Se encontro el objeto en la posicion " + pos + " y la caja es color " + c.getColor());
                    return;

                }

            }

            pos++;
        }

        System.out.println("No se encontró el objeto.");

    }
    public T delete(T objeto){
        for (Caja<T> c : caja) {
            for (T x : c){
                if(x.compareTo(objeto) == 0){
                    caja.remove(x);
                    System.out.println("Se elimino el objeto " + x);
                    return x;
                }
            }
        }
        return null;
    }

    public Iterator<Caja<T>> iterator(){
        return caja.iterator();
    }
    @Override
    public String toString(){
        StringBuilder cadena = new StringBuilder(String.format("%-8s %-11s %-30s","Posición","Color Caja","Objeto"));
        int pos = 0;
        for (Caja<T> c : caja){
            cadena.append(String.format("\n%-8d %-11s %-30s",pos,c.getColor(),c));
            pos++;
        }
        return cadena.toString();
    }
}
