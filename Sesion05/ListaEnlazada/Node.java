package Sesion05.ListaEnlazada;

public class Node <T extends Comparable<T>>{
    private T elemento;
    private Node<T> siguiente;
    public Node(T elemento){
        this.elemento = elemento;
        this.siguiente = null;
    }
    public T getElemento(){
        return elemento;
    }
    public void setElemento(T elemento){
        this.elemento = elemento;
    }
    public Node<T> getSiguiente(){
        return siguiente;
    }
    public void setSiguiente(Node<T> siguiente){
        this.siguiente = siguiente;
    }
    public boolean isEmpty(){
        return elemento == null;
    }
}
