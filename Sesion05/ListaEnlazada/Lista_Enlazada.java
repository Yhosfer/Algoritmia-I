package Sesion05.ListaEnlazada;

public class Lista_Enlazada <T extends Comparable<T>> {

    Nodo<T> head;

    public Lista_Enlazada() {

        head = null;

    }

    boolean isEmptyList(){
        return head == null;
    }

    int length(){

        if(isEmptyList()){
            return 0;
        }

        Nodo<T> pivote = head;
        int cont = 1;

        while (pivote.next != null){
            cont++;
            pivote = pivote.next;
        }
        return cont;

    }

    void destroyList(){
        head = null;
    }

    int search(T x) {
    Nodo<T> pivote = head;
    int cont = 0;

    while (pivote != null) {
        if (pivote.valor.compareTo(x) == 0) {
            return cont; 
        }
        pivote = pivote.next;
        cont++;
    }
    return -1; // No encontrado
}


    void insertarEnPosicion(int posicion, T valor) {
        if (posicion < 0 ) {
            System.out.println("Posición inválida");
            return;
        }
        if (length() < posicion){
            System.out.println("Fuera de posición");
            return;
        }

        if (posicion == 0) {
            Nodo<T> nuevo = new Nodo<>(valor);
            nuevo.next = head;
            head = nuevo;
            return;
        }

        Nodo<T> pivote = head;
        int contador = 0;

        while (contador < posicion - 1) {
            pivote = pivote.next;
            contador++;
        }

        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.next = pivote.next;
        pivote.next = nuevo;
    }

    void insertFirst(T x){

        if(isEmptyList()){
            head = new Nodo<>(x);
        }else{

            Nodo<T> newNode = new Nodo<>(x);
            Nodo<T> auxiliar = head;
            head = newNode;
            newNode.next = auxiliar;

        }
    }

    void insertLast(T x) {

        if(isEmptyList()){
            head = new Nodo<>(x);
        } else {

            Nodo<T> pivote = head;

            while (pivote.next != null){

                pivote = pivote.next;

            }

            pivote.next = new Nodo<>(x);
        }
    }

    void removeNode(T x){

        if(!seencuentra(x) || isEmptyList()){

            System.out.println("Lista vacia");
            return;

        }


        Nodo<T> pivote = head;

        if(search(x) == 0){

            Nodo<T> auxiliar = pivote.next;
            head = auxiliar;
            return;

        }

        while (pivote.next != null && pivote.next.valor.compareTo(x) != 0) {
            pivote = pivote.next;
        }


        Nodo<T> auxiliar = pivote.next;
        pivote.next = auxiliar.next;
    }

    boolean seencuentra(T x){

        if (isEmptyList()){
            return false;
        }

        Nodo<T> pivote = head;

        if (pivote.valor.compareTo(x) == 0){
            return true;
        }

        while (pivote.next != null){

            pivote = pivote.next;

            if(pivote.valor == x){
                return true;
            }
        }
        return false;

    }

    Lista_Enlazada<T> invertirLista() {

        Lista_Enlazada<T> listaInvertida = new Lista_Enlazada<>();
        Nodo<T> pivote = head;
        while (pivote != null) {

            listaInvertida.insertFirst(pivote.valor);
            pivote = pivote.next;

        }
        return listaInvertida;

    }

    // Imprime la lista
    public void listar(){
        if (isEmptyList()) {
            System.out.println("Lista vacia");
        }
        Nodo<T> actual = head;
        while (actual.next != null){
            System.out.println(actual.valor + " miku ");
            actual = actual.next;
        }
        System.out.println(actual.valor + " miku ");
    }

    public void ordenarLista() {
        if (isEmptyList()) {
            return;
        }

        Nodo<T> pivote = head;

        while (pivote != null) {
            Nodo<T> siguiente = pivote.next;

            while (siguiente != null) {

                if (pivote.valor.compareTo(siguiente.valor) > 0) {

                    T temp = pivote.valor;
                    pivote.valor = siguiente.valor;
                    siguiente.valor = temp;

                }

                siguiente = siguiente.next;

            }

            pivote = pivote.next;

        }
    }
    public void concatenarListas(Lista_Enlazada<T> otraLista) {
        if (head == null) {
            head = otraLista.head;
            return;
        }
        Nodo<T> pivote1 = head;
        while (pivote1.next != null) {
            pivote1 = pivote1.next;
        }

        pivote1.next=otraLista.head;

    }

}
