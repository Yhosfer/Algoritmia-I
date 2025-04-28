package Sesion05.ListaEnlazada;

public class Main {
    public static void main(String[] args) {
        Lista_Enlazada lista = new Lista_Enlazada();
        lista.insertFirst(10);
        lista.insertFirst(1);
        lista.insertLast(5);
        lista.insertLast(8);
        lista.insertLast(7);

        lista.listar();
        System.out.println("d" + "");
        Lista_Enlazada listaInvertida = lista.invertirLista();
        listaInvertida.listar();
        lista.ordenarLista();
        lista.listar();
    }
}
