package Sesion04.Ejercicio02;

public class EJ_02 {

    public static void main(String[] args) {

        int[] arreglo = {7, 2, 1, 6, 8, 5, 3, 4}; // Nuestro arreglo de prueba

        int k = 3; // Queremos encontrar el 3er menor elemento

        int resultado = seleccionarElemento(arreglo, k);

        System.out.println("El menor elemento en la posicion -> " + k + " es: " + resultado);

    }

    public static int seleccionarElemento(int[] arreglo, int k) {

        // Llamamos al quickSelect con índice ajustado (k-1 porque empezamos desde 0)
        return quickSelect(arreglo, 0, arreglo.length - 1, k - 1);

    }

    private static int quickSelect(int[] arreglo, int izquierda, int derecha, int indiceBuscado) {

        // Si ya solo queda un elemento, ese es el que buscamos
        if (izquierda == derecha) {
            return arreglo[izquierda];
        }

        // Se escoge un pivote y se posiciona en su lugar correcto
        int indicePivote = particionar(arreglo, izquierda, derecha);

        // Si el pivote está en la posición que queremos, lo devolvemos
        if (indiceBuscado == indicePivote) {
            return arreglo[indiceBuscado];

            // Si el índice que buscamos está a la izquierda del pivote
        } else if (indiceBuscado < indicePivote) {
            return quickSelect(arreglo, izquierda, indicePivote - 1, indiceBuscado);

            // Si está a la derecha
        } else {
            return quickSelect(arreglo, indicePivote + 1, derecha, indiceBuscado);
        }

    }

    private static int particionar(int[] arreglo, int izquierda, int derecha) {

        int valorPivote = arreglo[derecha]; // Tomamos el último valor como pivote
        int indiceAlmacenamiento = izquierda; // Aquí vamos guardando los menores al pivote

        for (int i = izquierda; i < derecha; i++) {

            if (arreglo[i] < valorPivote) {
                // Intercambiamos para mover el menor al frente
                intercambiar(arreglo, indiceAlmacenamiento, i);
                indiceAlmacenamiento++;
            }

        }

        // Al final, colocamos el pivote en su posición correcta
        intercambiar(arreglo, indiceAlmacenamiento, derecha);

        return indiceAlmacenamiento;
    }

    private static void intercambiar(int[] arreglo, int i, int j) {

        // Clásico swap
        int temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;

    }
}

