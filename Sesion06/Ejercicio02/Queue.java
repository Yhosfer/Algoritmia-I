package Sesion06.Ejercicio02;

public interface Queue <E>{

    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E backElemento() throws ExceptionIsEmpty;
    boolean isEmpty();

}
