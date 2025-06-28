package Sesion11.Hash;
import java.util.LinkedList;

public class HashO<T> {
    private final LinkedList<Register<T>>[] tabla;
    private final int tamaño;

    public HashO(int tamaño) {
        this.tamaño = tamaño;
        tabla = new LinkedList[tamaño];

        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int funcionHash(int clave) {
        return clave % tamaño;
    }

    public int insertar(Register<T> reg) {
        int pos = funcionHash(reg.getClave());
        LinkedList<Register<T>> lista = tabla[pos];

        for (Register<T> r : lista) {
            if (r.getClave() == reg.getClave()) {
                r.setValor(reg.getValor());
                return pos;
            }
        }

        lista.add(reg);
        return pos;
    }

    public Register<T> buscar(int clave) {
        int pos = funcionHash(clave);
        for (Register<T> r : tabla[pos]) {
            if (r.getClave() == clave) return r;
        }
        return null;
    }

    public boolean eliminar(int clave) {
        int pos = funcionHash(clave);
        LinkedList<Register<T>> lista = tabla[pos];

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getClave() == clave) {
                lista.remove(i);
                return true;
            }
        }

        return false;
    }

    public void mostrar() {
        System.out.println("TABLA HASH ABIERTA:");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(i + " → ");
            if (tabla[i].isEmpty()) {
                System.out.println("VACÍO");
            } else {
                for (Register<T> r : tabla[i]) {
                    System.out.print(r + " ");
                }
                System.out.println();
            }
        }
    }
}
