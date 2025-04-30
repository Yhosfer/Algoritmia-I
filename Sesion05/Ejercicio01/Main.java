package Sesion05.Ejercicio01;

public class Main {
    public static void main(String[] args) {
        Lista_Enlazada <Integer> listaNumeros = new Lista_Enlazada<>();
        listaNumeros.insertFirst(10);
        listaNumeros.insertFirst(12);
        listaNumeros.insertLast(5);
        listaNumeros.insertLast(7);

        // Invertir lista
        //listaNumeros.invertirLista();
        listaNumeros.listar();

        // Contar nodos
        System.out.println("Total Nodos: " + listaNumeros.length());

        // Comparar dos listas

        System.out.println("Son iguales Lista : "+ listaNumeros.sonIguales(listaNumeros));

        // Concatenar dos listas
        Lista_Enlazada <Integer> listaNumeros1 = new Lista_Enlazada<>();
        listaNumeros.insertFirst(1);
        listaNumeros.insertFirst(11);
        listaNumeros.insertLast(8);

        listaNumeros.concatenarListas(listaNumeros1);
        System.out.println("Total Nodos: " + listaNumeros1.length());
        listaNumeros.listar();

        System.out.println("");
        // Ordenamiento
        listaNumeros.ordenarLista();
        listaNumeros.listar();

    }
}
