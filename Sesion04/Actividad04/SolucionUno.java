package Sesion04.Actividad04;

public class SolucionUno {
    public static int moda1(int array[]){
        int first = 0;
        int end = array.length-1;

        if (first == end)
            return array[first];

        int moda = array[first];
        int maxfre = frecuencia(array, first, end, moda);

        for (int i = first +1; i < end; i++){
            int fre = frecuencia(array, i, end, array[i]);
            if (fre > maxfre){
                maxfre = fre;
                moda = array[i];
            }
        }
        return moda;
    }

    private static int frecuencia(int []array, int first, int end, int ele){
        if (first == end) return 0;
        int suma = 0;
        for (int i = first; i <= end; i++){
            if (array[i] == ele){
                suma ++;
            }
        }
        return suma;
    }
    public static void main(String[] args) {
        int[] datos = {3, 5, 2, 3, 6, 3, 5, 5, 5};

        int resultado = moda1(datos);

        System.out.println("La moda del arreglo es: " + resultado);
    }
}
