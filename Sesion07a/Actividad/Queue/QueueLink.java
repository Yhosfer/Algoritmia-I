package Sesion07a.Actividad.Queue;



public class QueueLink <E> implements Queue<E>{

    private Node<E> first;
    private Node<E> last;

    public QueueLink() {
        first = last = null;
    }

    @Override
    public void enqueue(E x) {

        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
        }

        last = newNode;

    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {

        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacia, no es posible obtener first");
        }

        E returnFirst = first.getData();
        first = first.getNext();
        return returnFirst;

    }

    @Override
    public E front() throws ExceptionIsEmpty {

        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacia");
        }

        return first.getData();

    }

    @Override
    public E back() throws ExceptionIsEmpty {

        if (isEmpty()||last==null) {
            throw new ExceptionIsEmpty("Cola vacia");
        }

        return last.getData();

    }

    @Override
    public boolean isEmpty() {

        return first == null;

    }

    public void print() {

        if (isEmpty()) {
            System.out.println("Cola vacia");
        }

        Node<E> temp = first;
        while (temp != null) {

            System.out.println(temp + " --");
            temp = temp.getNext();

        }

    }

    public void printQueue() {

        if (isEmpty()) {
            System.out.println("Vacía");
        } else {
            Node<E> current = first;
            while (current != null) {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            }
            System.out.println();
        }

    }

}