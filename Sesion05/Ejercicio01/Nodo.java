package Sesion05.Ejercicio01;

public class Nodo<T extends Comparable<T>> {
    public T valor;
    public Nodo<T> next;

    public Nodo(T valor) {

        this.valor = valor;
        this.next = null;

    }
}
