package Sesion03.EjmMergeSort;


import java.util.ArrayList;
import java.util.Scanner;

public class Algo_InsectionSort {
    public static void main(String[] args) {

        ArrayList<String> coleccion = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String validar;
        String letra;



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

        selectionSort(coleccion);

        System.out.println("Arreglo ordenado:");
        imprimir(coleccion);
        System.out.println();

        scanner.close();

    }


    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> lista) {

        int n = lista.size();   //1

        for (int i = 0; i < n; i++) {   //n

            int minIndex = i;   //1

            for (int j = i + 1; j < n; j++) {   //n

                if (lista.get(j).compareTo(lista.get(minIndex)) < 0) {  //1

                    minIndex = j;   //1

                }

            }

            if (minIndex != i) {    //1

                E temp = lista.get(i);      //1
                lista.set(i, lista.get(minIndex));      //1
                lista.set(minIndex, temp);      //1

            }


        }


    }

    public static <E> void imprimir(ArrayList<E> lista) {

        for (E elem : lista) {

            System.out.print(elem + " ");


        }

    }

}