package Sesion09.TDA;

public class ListLinked<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public ListLinked() {
        cabeza = null;
        tamaño = 0;
    }

    public void addLast(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
        tamaño++;
    }
    public boolean remove(T dato) {
        if (cabeza == null) return false;

        // Si el nodo a eliminar está al inicio
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.next;
            tamaño--;
            return true;
        }

        Nodo<T> anterior = cabeza;
        Nodo<T> actual = cabeza.next;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                anterior.next = actual.next;
                tamaño--;
                return true;
            }
            anterior = actual;
            actual = actual.next;
        }

        return false; // No se encontró el dato
    }

    public T get(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.next;
        }
        return actual.dato;
    }

    public boolean contains(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    public int size() {
        return tamaño;
    }

    public String toString() {
        StringBuilder resultado = new StringBuilder();
        Nodo<T> actual = cabeza;
        while (actual != null) {
            resultado.append(actual.dato.toString()).append(" ");
            actual = actual.next;
        }
        return resultado.toString().trim();
    }

    public T removeFirst() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
        }
        T dato = cabeza.dato;
        cabeza = cabeza.next;
        tamaño--;
        return dato;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

}
