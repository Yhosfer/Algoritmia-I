package Sesion05.ListaEnlazada;

public class Pruebas {
    public static void main(String[] args) {
        // Instancia de la clase ListaEnlazada
        ListaEnlazada lista = new ListaEnlazada();

        // Se agregan elemento
        lista.insertFirst(1);
        lista.insertFirst(2);
        lista.insertLast(4);
        lista.insertLast(5);
        lista.insertLast(6);
        // Se imprime la lista
        lista.listar();

        // Busqueda de elemento generico
        System.out.println(" Se encuentra en la lista: "+lista.search(1));
        // Total de nodos
        System.out.println("Total de Nodos: "+lista.length());

        System.out.println("Eliminar: "+lista.eliminar(6));
        lista.listar();
    }
}
