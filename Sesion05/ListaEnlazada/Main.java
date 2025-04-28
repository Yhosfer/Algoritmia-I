package Sesion05.ListaEnlazada;

public class Main {
    public static void main(String[] args) {
        // Instancia de la clase ListaEnlazada
        Lista_Enlazada lista = new Lista_Enlazada();
        lista.insertFirst(10);
        lista.insertFirst(1);
        lista.insertLast(5);



        // Busqueda de elemento generico
        System.out.println(" Se encuentra en la lista: "+lista.search(1));

        // Invertir la lista
        Lista_Enlazada listaInvertida = lista.invertirLista();

        // inserción al final de la lista
        lista.insertLast(22);

        // Se imprime la lista
        //lista.listar();

        // Conteo de nodos
        System.out.println("Total de Nodos: "+lista.length());

        // ordenar lista
        lista.ordenarLista();

        // concatenar dos listas
        lista.concatenarListas(listaInvertida);

        // comparar dos listas
        System.out.println("Son iguales? "+lista.sonIguales(listaInvertida));

        System.out.println("Total de Nodos: "+lista.length());
        System.out.println("Ordenamiento de lista");
        lista.ordenarLista();
        lista.listar();
    }

}
