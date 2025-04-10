package Sesion04.Ejercicio03;

public class EJ_03 {

    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                if (i == j) {

                    C[i][j] = 0;

                } else {

                    C[i][j] = T[i][j];

                }

            }

        }

        for (int distancia = 2; distancia < n; distancia++) {

            for (int i = 0; i < n - distancia; i++) {

                int j = i + distancia;

                for (int k = i + 1; k < j; k++) {

                    if (C[i][k] + C[k][j] < C[i][j]) {

                        C[i][j] = C[i][k] + C[k][j];

                    }

                }

            }

        }

        return C;

    }

    public static void main(String[] args) {

        int[][] T = {

                {0, 5, 6, 10},
                {0, 0, 3, 4},
                {0, 0, 0, 2},
                {0, 0, 0, 0}

        };

        int[][] costosMinimos = calcularCostosMinimos(T);

        System.out.println("\nCosto mínimo de 0 a 3: " + costosMinimos[0][3]);

    }

}
