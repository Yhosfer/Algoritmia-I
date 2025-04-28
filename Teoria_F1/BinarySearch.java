package Teoria_F1;

public class BinarySearch {
    public static int binarySearch(int[] lista, int key) {
        int incio = 0, fim = lista.length - 1;
        while (incio <= fim) {
            int medio = incio + (fim - incio) / 2;
            if (lista[medio] == key) {
                return medio;
            } else if (lista[medio] < key) {
                incio = medio + 1;
            } else{
                fim = medio - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] num = {1,3,6,7,12,16,26,34,55,58,66,98};
        int objetivo = 66;

        int resultado = binarySearch(num, objetivo);
        if (resultado == -1) {
            System.out.println("Objetivo no encontrado");
        }
        else{
            System.out.println("Objetivo encontrado");
        }
    }
}
