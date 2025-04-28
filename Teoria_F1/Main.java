package Teoria_F1;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String validar;
        ArrayList<String> coleccion = new ArrayList<>();
        String letra;

        // Leer elementos del arreglo
        System.out.println("Ingrese las letras para ordenar:");
        do {
            System.out.print("Ingrese una letra: ");
            letra = scanner.next();
            coleccion.add(letra);

            System.out.print("¿Desea continuar? s/n: ");
            validar = scanner.next().toLowerCase();
        } while (validar.equals("s"));

        System.out.println("Arreglo original:");
        imprimir(coleccion);
        System.out.println();

        selectionSort(coleccion); // Llamada al método genérico

        System.out.println("Arreglo ordenado:");
        imprimir(coleccion);
        System.out.println();

        scanner.close();
    }

    // Método genérico de Selection Sort

    public static <T extends Comparable<T>> void selectionSort(ArrayList<T> lista) {
        int n = lista.size();

        for (int i = 0; i < n; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).compareTo(lista.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                T temp = lista.get(i);
                lista.set(i, lista.get(minIndex));
                lista.set(minIndex, temp);
            }
        }
    }

    // Método para imprimir cualquier lista genérica
    public static <T> void imprimir(ArrayList<T> lista) {
        for (T elem : lista) {
            System.out.print(elem + " ");
        }
    }
}
