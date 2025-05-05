package Sesion06.Ejercicio03;
import Sesion06.Actividad02.ExceptionIsEmpty;

public interface PriorityQueue <E,N extends Number> {

    void enqueue(E x, N priority);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
    void printQueues();

}
