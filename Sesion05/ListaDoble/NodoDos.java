package Sesion05.ListaDoble;

public class NodoDos<T> {

    public T valor;
    public NodoDos<T> next;
    public NodoDos<T> prev;

    public NodoDos(T valor){

        this.valor = valor;
        this.next = null;
        this.prev = null;

    }

}
