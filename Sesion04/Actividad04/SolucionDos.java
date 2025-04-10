package Sesion04.Actividad04;

import java.util.Arrays;

public class SolucionDos {
    public static int moda2(int array[]) {
        int first = 1;
        int p = 0;
        int end = array.length - 1;
        int moda = array[0];
        int frec = 1;
        int maxfrec = 0;
        while (first <= end) {
            if (array[p] == array[first]) {
                frec++;
                first++;
            }
            else {
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[p];
                }
                p = first;
                first = p+1;
                frec = 1;
            }
        }
        return moda;
    }
    public static void main(String[] args) {
        int[] datos = {3, 5, 2, 3, 6, 3, 5, 5, 5};

        // ¡Es necesario ordenar el arreglo antes de buscar la moda
        Arrays.sort(datos);

        int resultado = SolucionDos.moda2(datos);

        System.out.println("La moda del arreglo ordenado es: " + resultado);
    }
}
