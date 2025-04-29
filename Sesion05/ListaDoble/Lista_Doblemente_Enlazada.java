package Sesion05.ListaDoble;

public class Lista_Doblemente_Enlazada<T extends Comparable<T>> {

    NodoDos<T> head;

    public Lista_Doblemente_Enlazada(){

        head = null;

    }

    boolean isEmptyList(){

        return head == null;

    }

    void insertFirst(T x) {

        if (isEmptyList()) {

            head = new NodoDos<>(x);

        } else {

            NodoDos<T> newNode = new NodoDos<>(x);
            NodoDos<T> auxiliar = head;
            head = newNode;
            newNode.next = auxiliar;
            auxiliar.prev = newNode;

        }
    }

    void insertLast(T x) {

        if (isEmptyList()) {
            head = new NodoDos<>(x);
        } else {

            NodoDos<T> pivote = head;

            while (pivote.next != null) {
                pivote = pivote.next;
            }

            NodoDos<T> newNode = new NodoDos<>(x);
            pivote.next = newNode;
            newNode.prev = pivote;

        }

    }

    boolean seencuentra(T x){

        if (isEmptyList()){
            return false;
        }

        NodoDos<T> pivote = head;

        if (pivote.valor == x){
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

    void removeNode(T x){

        if(!seencuentra(x) || isEmptyList()){

            System.out.println("Lista vacia");
            return;

        }

        //Separación

        NodoDos<T> pivote = head;

        if(search(x) == 0){

            NodoDos<T> auxiliar = pivote.next;
            head = auxiliar;
            return;

        }

        while (pivote.next.valor != x){

            pivote = pivote.next;

        }
        NodoDos<T> auxiliar = pivote.next;

        pivote.next = auxiliar.next;
        auxiliar.next.prev = pivote;
    }

    int search(T x){

        NodoDos<T> pivote = head;
        int cont = 0;

        if (pivote.valor == x){
            return 0;
        }

        while (pivote.next != null && pivote.next.valor!= x){
            pivote = pivote.next;
            cont++;
        }

        if (cont == 0){
            return -1;
        }

        return cont+1;

    }

    void invertirLista() {
        NodoDos<T> actual = head;

        NodoDos<T> temp = null;

        while (actual != null) {

            temp = actual.prev;

            actual.prev = actual.next;

            actual.next = temp;

            actual = actual.prev;
        }

        if (temp != null) {

            head = temp.prev;
        }
    }

}
