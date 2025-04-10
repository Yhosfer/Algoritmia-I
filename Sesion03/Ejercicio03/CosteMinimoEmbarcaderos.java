package Sesion03.Ejercicio03;

import java.util.*;

public class CosteMinimoEmbarcaderos {

    public static int[][] calcularCosteMinimo(int[][] T, int[][] P) {
        int n = T.length;
        int[][] C = new int[n][n];

        // Inicializar matrices C y P
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else {
                    C[i][j] = T[i][j];
                    P[i][j] = -1; // -1 indica que no hay escala
                }
            }
        }

        // Programación dinámica
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i + 1; k < j; k++) {
                    int nuevoCosto = T[i][k] + C[k][j];
                    if (nuevoCosto < C[i][j]) {
                        C[i][j] = nuevoCosto;
                        P[i][j] = k;
                    }
                }
            }
        }

        return C;
    }

    // Reconstruir la ruta de i ExplicaciónActi02 j usando la matriz P
    public static void imprimirRuta(int i, int j, int[][] P) {
        if (P[i][j] == -1) {
            System.out.print(i + " -> " + j);
        } else {
            int k = P[i][j];
            imprimirRuta(i, k, P);
            System.out.print(" -> ");
            imprimirRuta(k, j, P);
        }
    }

    // Imprimir la matriz
    public static void imprimirMatriz(int[][] M) {
        for (int[] fila : M) {
            for (int val : fila) {
                if (val == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(val + "\t");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] T = {
                {0, 3, 4, 7},
                {0, 0, 1, 6},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        int n = T.length;
        int[][] P = new int[n][n]; // para rutas
        int[][] C = calcularCosteMinimo(T, P);

        System.out.println("Matriz de Costes Mínimos C:");
        imprimirMatriz(C);

        // Mostrar ruta mínima de 0 ExplicaciónActi02 3
        System.out.print("\nRuta mínima de 0 ExplicaciónActi02 3: ");
        imprimirRuta(0, 3, P);
        System.out.println("\nCosto total: " + C[0][3]);
    }
}
