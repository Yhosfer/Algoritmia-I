package Sesion08.Actividad;

class Node<E extends Comparable<E>> {
    protected E data;
    protected Node<E> left, right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
