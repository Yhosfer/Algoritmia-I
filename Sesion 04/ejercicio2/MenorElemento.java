public class MenorElemento {

    // Método principal que ordena y luego selecciona el k-ésimo elemento
    public static int seleccionarElemento(int[] arreglo, int k) {
        quicksort(arreglo, 0, arreglo.length - 1);
        
        // Luego, simplemente devolvemos el elemento en la posición k-1
        return arreglo[k - 1];
    }

    // Método Quicksort para ordenar el arreglo
    private static void quicksort(int[] arreglo, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int indicePivote = particionar(arreglo, izquierda, derecha);
            
            // Ordenamos recursivamente las dos mitades
            quicksort(arreglo, izquierda, indicePivote - 1);
            quicksort(arreglo, indicePivote + 1, derecha);
        }
    }

    // Partición (igual que en el código original)
    private static int particionar(int[] arreglo, int izquierda, int derecha) {
        int valorPivote = arreglo[derecha];
        int indiceAlmacenamiento = izquierda;

        for (int i = izquierda; i < derecha; i++) {
            if (arreglo[i] < valorPivote) {
                intercambiar(arreglo, indiceAlmacenamiento, i);
                indiceAlmacenamiento++;
            }
        }

        intercambiar(arreglo, indiceAlmacenamiento, derecha);
        return indiceAlmacenamiento;
    }

    // Método auxiliar para intercambiar elementos (igual que antes)
    private static void intercambiar(int[] arreglo, int i, int j) {
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}
