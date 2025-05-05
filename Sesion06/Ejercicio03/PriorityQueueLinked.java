package Sesion06.Ejercicio03;

import Sesion06.Actividad02.QueueLink;
import Sesion06.Actividad02.ExceptionIsEmpty;

public class PriorityQueueLinked<E, N extends Number> implements PriorityQueue<E, N> {

    private QueueLink<E>[] colas;
    private int n_prioridades;

    public PriorityQueueLinked(int n_prioridades) {

        this.n_prioridades = n_prioridades;
        colas = new QueueLink[n_prioridades];

        for (int i = 0; i < n_prioridades; i++) {
            colas[i] = new QueueLink<>();
        }
    }

    @Override
    public void enqueue(E x, N priority) {

        int p = Math.round(priority.floatValue());

        if (p < 0 || p >= n_prioridades) {
            throw new IllegalArgumentException("La prioridad especificada está fuera de rango");
        }

        colas[p].enqueue(x);
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {

        for (int i = 0; i < n_prioridades; i++) {

            if (!colas[i].isEmpty()) {
                return colas[i].dequeue();
            }
        }

        throw new ExceptionIsEmpty("No hay elementos para sacar de la cola de prioridad.");
    }

    @Override
    public E front() throws ExceptionIsEmpty {

        for (int i = 0; i < n_prioridades; i++) {

            if (!colas[i].isEmpty()) {
                return colas[i].front();
            }
        }

        throw new ExceptionIsEmpty("La cola de prioridad está пустой."); //пустой = vacía xd
    }

    @Override
    public E back() throws ExceptionIsEmpty {

        for (int i = n_prioridades - 1; i >= 0; i--) {

            if (!colas[i].isEmpty()) {
                return colas[i].back();
            }
        }

        throw new ExceptionIsEmpty("La cola de prioridad está пустой."); //пустой = vacía xd
    }

    @Override
    public boolean isEmpty() {

        for (int i = 0; i < n_prioridades; i++) {

            if (!colas[i].isEmpty()) {
                return false;
            }
        }

        return true;
    }


    @Override
    public void printQueues() {

        if (isEmpty()) {
            return;
        }

        System.out.println("\n=== Estado actual de las colas de prioridad ===");

        for (int i = 0; i < n_prioridades; i++) {

            System.out.print("Prioridad " + i + ": ");
            colas[i].printQueue();

        }

    }

}
