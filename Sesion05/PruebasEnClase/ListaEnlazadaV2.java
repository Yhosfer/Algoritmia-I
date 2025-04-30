package Sesion05.PruebasEnClase;

public class ListaEnlazadaV2<T extends Comparable<T>>{
    private Node<T> first;

    public ListaEnlazadaV2(){
        first = null;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int length(){
        int length = 0;
        Node actual = first;
        if(actual == null){
            return length;
        } else {
            length++;
            while(actual.getSiguiente() != null){
                actual = actual.getSiguiente();
                length++;
            }
        }
        return length;
    }
    public void destroyListaEnlazada(){
        first = null;
    }
    public void insertFirst(T elemento){
        Node newNode = new Node(elemento);
        first = newNode;
    }
    public void insertLast(T elemento){
        Node<T> nuevo = new Node(elemento);
        if(first == null){
            first = nuevo;
        } else{
            Node actual = first;
            while (actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
    public void listar(){
        Node actual = first;
        while(actual.getSiguiente() != null){
            System.out.println(actual.getElemento() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println(actual.getElemento()+ " ");
    }
    public int search(T elemento){
        Node actual = first;
        int index = 0;
        if( actual == null ){
            return -1;
        }else {
            while(actual.getSiguiente() != null){
                if(actual.getElemento().compareTo(elemento) == 0){
                    return index;
                }
                actual = actual.getSiguiente();
                index++;
            }
        }
        return -1;
    }
    public boolean eliminar(T elemento){
        Node actual = first;
        if( actual == null ){
            return false;
        } else {
            while(actual.getSiguiente() != null){
                if(actual.getSiguiente().getElemento().equals(elemento)){
                    actual.setSiguiente(actual.getSiguiente().getSiguiente());
                    return true;
                }
                actual = actual.getSiguiente();
            }
        }
        return false;
    }
}
