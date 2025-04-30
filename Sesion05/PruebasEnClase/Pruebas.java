package Sesion05.PruebasEnClase;

public class Pruebas {
    public static void main(String[] args) {
        ListaEnlazadaV2 lista = new ListaEnlazadaV2();
        lista.insertFirst(1);
        lista.insertFirst(2);
        lista.insertLast(4);
        lista.insertLast(5);
        lista.insertLast(6);

        lista.listar();
        System.out.println("dd: "+lista.length());
        System.out.println("dd index: "+lista.search(1));
        System.out.println("Eliminar: "+lista.eliminar(6));
        lista.listar();
    }
}
