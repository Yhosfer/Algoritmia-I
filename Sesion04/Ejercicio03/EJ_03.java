public class EJ_03 {

    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n]; // Matriz donde vamos a guardar los costos mínimos

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0; // Costo de ir a uno mismo es cero
                } else {
                    C[i][j] = T[i][j]; // Inicializamos con los valores directos de la matriz T
                }
            }
        }

        // Vamos calculando caminos mínimos para trayectos de distancia 2 o más
        for (int distancia = 2; distancia < n; distancia++) {
            for (int i = 0; i < n - distancia; i++) {
                int j = i + distancia;

                for (int k = i + 1; k < j; k++) {
                    // Si encontramos un camino más barato pasando por k, lo actualizamos
                    if (C[i][k] + C[k][j] < C[i][j]) {
                        C[i][j] = C[i][k] + C[k][j];
                    }
                }
            }
        }

        return C; // Retornamos la matriz con los costos mínimos

    }

    public static void main(String[] args) {

        int[][] T = {
                {0, 5, 6, 10},  // Costos directos desde el nodo 0
                {0, 0, 3, 4},   // Costos directos desde el nodo 1
                {0, 0, 0, 2},   // Costos directos desde el nodo 2
                {0, 0, 0, 0}    // Nodo 3 no tiene salidas
        };

        int[][] costosMinimos = calcularCostosMinimos(T);

        // Mostramos el costo mínimo de ir del nodo 0 al nodo 3
        System.out.println("\nCosto mínimo de 0 a 3: " + costosMinimos[0][3]);

    }

}