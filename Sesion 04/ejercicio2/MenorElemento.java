import java.util.Random;

public class MenorElemento {

    public static int seleccionarElemento(int[] arreglo, int k) {
        return quickSelect(arreglo, 0, arreglo.length - 1, k - 1);
    }

    private static int quickSelect(int[] arreglo, int izquierda, int derecha, int indiceBuscado) {
        if (izquierda == derecha) return arreglo[izquierda];

        int indicePivote = particionar(arreglo, izquierda, derecha);

        if (indiceBuscado == indicePivote)
            return arreglo[indiceBuscado];
        else if (indiceBuscado < indicePivote)
            return quickSelect(arreglo, izquierda, indicePivote - 1, indiceBuscado);
        else
            return quickSelect(arreglo, indicePivote + 1, derecha, indiceBuscado);
    }

    private static int particionar(int[] arreglo, int izquierda, int derecha) {
        Random aleatorio = new Random();
        int indicePivote = izquierda + aleatorio.nextInt(derecha - izquierda + 1);
        int valorPivote = arreglo[indicePivote];

        intercambiar(arreglo, indicePivote, derecha);
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

    // Pruebas
    public static void main(String[] args) {
        System.out.println(seleccionarElemento(new int[]{4, 2, 7, 10, 4, 17}, 3)); // 4
        System.out.println(seleccionarElemento(new int[]{4, 2, 7, 10, 4, 1, 6}, 5)); // 6
        System.out.println(seleccionarElemento(new int[]{4, 2, 7, 1, 4, 6}, 1)); // 1
        System.out.println(seleccionarElemento(new int[]{9, 2, 7, 1, 7}, 4)); // 7
    }
}
