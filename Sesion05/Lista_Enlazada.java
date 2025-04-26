package Sesion05;

public class Lista_Enlazada <T> {

    Nodo<T> head;

    public Lista_Enlazada() {

        head = null;

    }

    boolean isEmptyList(){
        return head == null;
    }

    int lenght(){

        Nodo<T> pivote = head;
        int cont = 0;

        while (pivote.next != null){
            cont++;
            pivote = pivote.next;
        }

        return cont;

    }

    void destroyList(){
        head = null;
    }

    int search(T x){

        Nodo<T> pivote = head;
        int cont = 0;

        if (pivote.valor == x){
            return 0;
        }

        while (pivote.next != null && pivote.next.valor!= x){
            cont++;
        }

        if (cont == 0){
            return -1;
        }

        return cont+1;

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

        //Separación

        Nodo<T> pivote = head;

        if(search(x) == 0){
            Nodo<T> auxiliar = pivote.next;
            head = auxiliar;
            return;
        }

        while (pivote.next.valor != x){

            pivote = pivote.next;

        }

        Nodo<T> auxiliar = pivote.next;
        pivote.next = auxiliar.next;



    }

    boolean seencuentra(T x){

        Nodo<T> pivote = head;

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

}