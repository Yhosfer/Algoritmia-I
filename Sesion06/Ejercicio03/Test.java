package Sesion06.Ejercicio03;

import Sesion06.Actividad02.ExceptionIsEmpty;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PriorityQueue<String, Integer> colaPrioridad = new PriorityQueueLinked<>(3);

        int opcion;

        do {

            System.out.println("\n===== MENÚ COLA DE PRIORIDAD =====");
            System.out.println("1. Encolar elemento");
            System.out.println("2. Desencolar elemento");
            System.out.println("3. Ver frente");
            System.out.println("4. Ver final");
            System.out.println("5. Imprimir colas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Ingrese el elemento: ");
                    String dato = sc.nextLine();

                    System.out.print("Ingrese la prioridad (0-2): ");
                    int prioridad = sc.nextInt();

                    colaPrioridad.enqueue(dato, prioridad);

                    System.out.println("Elemento encolado con prioridad " + prioridad);

                    break;

                case 2:

                    try {

                        String eliminado = colaPrioridad.dequeue();
                        System.out.println("Elemento desencolado: " + eliminado);

                    } catch (ExceptionIsEmpty e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    try {

                        System.out.println("Elemento al frente: " + colaPrioridad.front());

                    } catch (ExceptionIsEmpty e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    try {

                        System.out.println("Elemento al final: " + colaPrioridad.back());

                    } catch (ExceptionIsEmpty e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:

                    colaPrioridad.printQueues();

                    break;

                case 0:

                    System.out.println("Saliendo del programa...");

                    break;

                default:

                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }
}
