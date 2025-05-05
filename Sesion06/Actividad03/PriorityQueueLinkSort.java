package Sesion06.Actividad03;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    private class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }

        public String toString() {
            return "(" + data + ", prioridad: " + priority + ")";
        }
    }

    private Nodo<EntryNode> first;
    private Nodo<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        EntryNode nuevo = new EntryNode(x, pr);
        Nodo<EntryNode> nuevoNodo = new Nodo<>(nuevo, null);

        if (isEmpty()) {
            first = last = nuevoNodo;
            return;
        }

        // Insertar en orden de mayor a menor prioridad
        if (pr.compareTo(first.getData().priority) > 0) {
            nuevoNodo.setNext(first);
            first = nuevoNodo;
            return;
        }

        Nodo<EntryNode> actual = first;
        while (actual.getNext() != null &&
               pr.compareTo(actual.getNext().getData().priority) <= 0) {
            actual = actual.getNext();
        }

        nuevoNodo.setNext(actual.getNext());
        actual.setNext(nuevoNodo);

        if (nuevoNodo.getNext() == null) last = nuevoNodo;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía.");
        E val = first.getData().data;
        first = first.getNext();
        if (first == null) last = null;
        return val;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía.");
        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola vacía.");
        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cola de prioridad: ");
        Nodo<EntryNode> actual = first;
        while (actual != null) {
            sb.append(actual.getData().toString()).append(" -> ");
            actual = actual.getNext();
        }
        return sb.toString();
    }
}
