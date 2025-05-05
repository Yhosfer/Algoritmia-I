package Sesion06.Ejercicio01;

public class StackLink<E> implements Stack<E> {
    private Nodo<E> top;

    public StackLink() {
        top = null;
    }

    public void push(E x) {
        top = new Nodo<>(x, top);
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Stack vacío.");
        E val = top.valor;
        top = top.next;
        return val;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Stack vacío.");
        return top.valor;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Stack: ");
        Nodo<E> actual = top;
        while (actual != null) {
            sb.append(actual.valor).append(" ");
            actual = actual.next;
        }
        return sb.toString();
    }
}
