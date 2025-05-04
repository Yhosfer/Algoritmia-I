package Sesion06.Ejercicio02;

public class QueueArray <E> implements Queue<E> {
    private E[] arrayCola;
    private int currentSize;
    private int front;
    private int back;
    public QueueArray(int tamanio) {
        this.currentSize = -1;
        arrayCola = (E[]) new Object[tamanio];
        front = 0;
        back = -1;
    }
    @Override
    public boolean isEmpty() {
        return currentSize == -1;
    }
    @Override
    public void enqueue(E element) {
        if (currentSize == (arrayCola.length - 1)) {
            System.out.println("Cola Llena. ");
            return;
        }
        arrayCola[++back] = element;
        currentSize++;

    }
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        E firstElement = arrayCola[front];
        front++;
        return firstElement;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return arrayCola[front];
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return arrayCola[back];
    }
    public void print() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        for (int i = front; i <= back; i++) {
            System.out.println(arrayCola[i] + " --");
        }
    }
}
