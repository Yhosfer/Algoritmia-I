package Sesion09.TDA;

public class PriorityQueue<E> {
    private ListLinked<E> elements;
    private ListLinked<Integer> priorities;

    public PriorityQueue() {
        elements = new ListLinked<>();
        priorities = new ListLinked<>();
    }

    public void add(E element, int priority) {
        // Si el elemento ya existe, actualizamos su prioridad
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                // Actualizar prioridad existente
                removeAt(i);
                break;
            }
        }
        // Agregar el elemento y su prioridad
        elements.addLast(element);
        priorities.addLast(priority);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public E poll() {
        if (isEmpty()) return null;

        // Buscar el índice del elemento con menor prioridad
        int minIndex = 0;
        int minPriority = priorities.get(0);

        for (int i = 1; i < priorities.size(); i++) {
            int currentPriority = priorities.get(i);
            if (currentPriority < minPriority) {
                minPriority = currentPriority;
                minIndex = i;
            }
        }

        // Obtener el elemento y removerlo junto con su prioridad
        E result = elements.get(minIndex);
        removeAt(minIndex);
        return result;
    }

    public void updatePriority(E element, int priority) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                // Remover y volver a agregar con nueva prioridad
                removeAt(i);
                elements.addLast(element);
                priorities.addLast(priority);
                return;
            }
        }
    }

    public boolean contains(E element) {
        return elements.contains(element);
    }

    public int size() {
        return elements.size();
    }

    // Método auxiliar para remover por índice
    private void removeAt(int index) {
        if (index == 0) {
            elements.removeFirst();
            priorities.removeFirst();
        } else {
            // Crear nuevas listas sin el elemento en el índice dado
            ListLinked<E> newElements = new ListLinked<>();
            ListLinked<Integer> newPriorities = new ListLinked<>();

            for (int i = 0; i < elements.size(); i++) {
                if (i != index) {
                    newElements.addLast(elements.get(i));
                    newPriorities.addLast(priorities.get(i));
                }
            }

            elements = newElements;
            priorities = newPriorities;
        }
    }
}