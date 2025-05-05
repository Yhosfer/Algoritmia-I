package Sesion06.Ejercicio02;

public class QueueArray <E> implements Queue<E> {
    private E[] arrayCola;
    private int currentSize;
    private int front;
    private int back;
    public QueueArray(int tamanio) {
        this.currentSize = 0;
        this.arrayCola = (E[]) new Object[tamanio];
        this.front = 0;
        this.back = -1;
    }
    @Override
    public boolean isEmpty() {
        return currentSize == -1;
    }
    @Override
    public void enqueue(E element) {
        if (currentSize == arrayCola.length ) {
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
        desplazarElementos();
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
    public E backElemento() throws ExceptionIsEmpty {
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
        System.out.println();
    }
    public void desplazarElementos() throws ExceptionIsEmpty {
        E[] auxArray;
        auxArray = (E[]) new Object[arrayCola.length];
        int indice = 0;
        System.out.println("cola: "+ backElemento());
        // copiar elementos de la cola
        for (int i = front; i <= back; i++) {
            auxArray[indice] = arrayCola[i];
            indice++;
        }
        arrayCola = auxArray;
        currentSize--;
        front = 0;
        back = currentSize -1;
    }
}
