    package Sesion05.ActivdadV2;

    public class Lista_Enlazada<T extends Comparable<T>> {

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

        public boolean removeNodev2(T x) {
            if (isEmptyList()) {
                System.out.println("Lista vacía");
                return false;
            }

            if (!seEncuentraV2(x)) {
                System.out.println("Elemento no encontrado");
                return false;
            }

            Nodo<T> pivote = head;

            if (pivote.valor.compareTo(x) == 0) {
                head = head.next;
                return true;
            }

            while (pivote.next != null && pivote.next.valor.compareTo(x) != 0) {
                pivote = pivote.next;
            }

            if (pivote.next != null) {
                pivote.next = pivote.next.next;
                return true;
            }

            return false; // En caso de que no se encuentre al final
        }

        public boolean seEncuentraV2(T x){
            if (isEmptyList()){
                return false;
            }

            Nodo<T> pivote = head;

            while (pivote != null){
                if (pivote.valor != null && pivote.valor.compareTo(x) == 0){
                    return true;
                }
                pivote = pivote.next;
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
                System.out.println("Lista vacía");
                return;
            }
            Nodo<T> actual = head;
            while (actual.next != null){
                System.out.println(actual.valor + " miku ");
                actual = actual.next;
            }
            System.out.println(actual.valor + " miku ");
        }

        // contatenar 2 Listas
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

        public T obtenerTareaPrioridad(){
            if(isEmptyList()) {
                return null;
            }

            Nodo<T> pivote = head;
            Nodo<T> mayorPri = head;

            while (pivote.next !=  null){
                if (mayorPri.valor.compareTo(pivote.next.valor)>0){
                    mayorPri = pivote;
                }else {
                    mayorPri = mayorPri.next;
                }
                pivote = pivote.next;
            }

            return mayorPri.valor;

        }
    }
