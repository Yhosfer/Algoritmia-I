package Sesion11.Main;

import Sesion11.Hash.HashC;
import Sesion11.Hash.HashO;
import Sesion11.Hash.Register;

public class TestHash {
        public static void main(String[] args) {
            System.out.println("1. INSERTAR SIN COLISIONES (Hash Cerrado, tamaño 7)");
            HashC<Integer> hash1 = new HashC<>(7);
            hash1.insertar(new Register<>(3, 3));
            hash1.insertar(new Register<>(10, 10));
            hash1.insertar(new Register<>(17, 17));
            hash1.insertar(new Register<>(24, 24));
            hash1.mostrar();


            System.out.println("\n------------------------------------------------------\n");

            System.out.println("2. RESOLVER COLISIONES (Hash Cerrado, tamaño 6)");
            HashC<Integer> hash2 = new HashC<>(6);
            int[] valores = {12, 18, 24, 30};

            for (int v : valores) {
                System.out.println("\nInsertando " + v + "...");
                hash2.insertar(new Register<>(v, v));
                hash2.mostrar();
            }

            System.out.println("\nNota: Todos los elementos tienen clave múltiplo de 6 → colisión en índice 0.");
            System.out.println("Se resuelven con SONDEO LINEAL (buscando el siguiente espacio libre).");

            System.out.println("\n------------------------------------------------------\n");

            System.out.println("3. BUSCAR EN HASH ABIERTO (encadenamiento)");
            HashO<String> hash3 = new HashO<>(5);
            hash3.insertar(new Register<>(10, "Juan"));
            hash3.insertar(new Register<>(15, "Ana"));
            hash3.insertar(new Register<>(20, "Luis"));
            hash3.insertar(new Register<>(25, "Rosa"));

            hash3.mostrar();

            System.out.println("\nBuscar clave 20 → " + hash3.buscar(20));
            System.out.println("Buscar clave 30 → " + hash3.buscar(30));
            System.out.println("Nota: Si la clave no está en la lista de su posición hash, retorna null.");


            System.out.println("\n------------------------------------------------------\n");

            System.out.println("4. ELIMINAR EN HASH CERRADO (tamaño 7)");
            HashC<Integer> hash4 = new HashC<>(7);
            hash4.insertar(new Register<>(5, 5));
            hash4.insertar(new Register<>(12, 12));
            hash4.insertar(new Register<>(19, 19));
            System.out.println("Estado inicial:");
            hash4.mostrar();

            System.out.println("\nEliminando clave 12...");
            hash4.eliminar(12);
            hash4.mostrar();

            System.out.println("\nBuscando clave 19 → " + hash4.buscar(19));
            System.out.println("""
                NOTA:
                - El 19 colisionó con 12, se insertó más adelante.
                - Aunque 12 fue eliminado, su espacio queda como 'BORRADO' (-1).
                - Eso permite seguir buscando hacia adelante y encontrar 19.
                - Esto es una ELIMINACIÓN LÓGICA, no física.
                """);

            System.out.println("DIFERENCIA:");
            System.out.println("- Eliminación LÓGICA: marca como BORRADO, útil para sondeo.");
            System.out.println("- Eliminación FÍSICA: elimina completamente, puede romper búsquedas.");
        }
}
