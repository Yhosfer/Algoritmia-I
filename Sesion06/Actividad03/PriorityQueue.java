public interface PriorityQueue<E, N extends Comparable<N>> {
    void enqueue(E x, N pr);               // Insertar elemento con prioridad
    E dequeue() throws ExceptionIsEmpty;   // Eliminar el de mayor prioridad
    E front() throws ExceptionIsEmpty;     // Ver el primero
    E back() throws ExceptionIsEmpty;      // Ver el último
    boolean isEmpty();                     // ¿Está vacía?
}
