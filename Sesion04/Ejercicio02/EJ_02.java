package Sesion04.Ejercicio02;

public class EJ_02 {

    public static void main(String[] args) {

        int[] arreglo = {7, 2, 1, 6, 8, 5, 3, 4};

        int k = 3;

        int resultado = seleccionarElemento(arreglo, k);

        System.out.println("El menor elemento en la posicion -> " + k + " es: " + resultado);

    }

    public static int seleccionarElemento(int[] arreglo, int k) {

        return quickSelect(arreglo, 0, arreglo.length - 1, k - 1);

    }

    private static int quickSelect(int[] arreglo, int izquierda, int derecha, int indiceBuscado) {

        if (izquierda == derecha) {

            return arreglo[izquierda];

        }

        int indicePivote = particionar(arreglo, izquierda, derecha);

        if (indiceBuscado == indicePivote) {

            return arreglo[indiceBuscado];

        } else if (indiceBuscado < indicePivote) {

            return quickSelect(arreglo, izquierda, indicePivote - 1, indiceBuscado);

        } else {

            return quickSelect(arreglo, indicePivote + 1, derecha, indiceBuscado);

        }

    }

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

    private static void intercambiar(int[] arreglo, int i, int j) {

        int temp = arreglo[i];

        arreglo[i] = arreglo[j];

        arreglo[j] = temp;

    }
}