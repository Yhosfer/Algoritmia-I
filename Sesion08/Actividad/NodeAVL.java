package Sesion08.Actividad;

class NodeAVL<E extends Comparable<E>> extends Node<E> {
    protected int bf;

    public NodeAVL(E data) {
        super(data);
        this.bf = 0;
    }

    @Override
    public String toString() {
        return data + "(bf=" + bf + ")";
    }
}
