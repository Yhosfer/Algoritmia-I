package Sesion06.Ejercicio01;

public class Nodo<E> {

    E valor;
    Nodo<E> next;

    public Nodo(E valor, Nodo<E> next) {

        this.valor = valor;
        this.next = next;

    }

}
