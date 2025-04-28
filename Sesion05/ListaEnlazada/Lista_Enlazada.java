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
    if (posicion < 0) {
        System.out.println("Posición inválida");
        return;
    }

    Nodo<T> nuevo = new Nodo<>(valor);

    if (posicion == 0) {
        nuevo.next = head;
        head = nuevo;
        return;
    }

    Nodo<T> pivote = head;
    int contador = 0;

    while (pivote != null && contador < posicion - 1) {
        pivote = pivote.next;
        contador++;
    }

    if (pivote == null) {
        System.out.println("Fuera de posición");
        return;
    }

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

   void removeNode(T x) {
    if (isEmptyList()) {
        System.out.println("Lista vacía");
        return;
    }

    Nodo<T> pivote = head;
    Nodo<T> auxiliar = null;

    while (pivote != null) {
        if (pivote.datos.compareTo(x) == 0) {
            if (auxiliar == null) {
                head = pivote.next;
            } else {
                auxiliar.next = pivote.next;
            }
            return; 
        }
        auxiliar = pivote;
        pivote = pivote.next;
    }

    System.out.println("Elemento no encontrado");
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
    boolean sonIguales(Lista_Enlazada<T> otraLista) {
        Nodo<T> pivote1 = this.head;
        Nodo<T> pivote2 = otraLista.head;

        while (pivote1 != null && pivote2 != null) {
            if (!pivote1.valor.equals(pivote2.valor)) {
                return false;
            }
            pivote1 = pivote1.next;
            pivote2 = pivote2.next;
        }

        return pivote1 == null && pivote2 == null;
    }



}
