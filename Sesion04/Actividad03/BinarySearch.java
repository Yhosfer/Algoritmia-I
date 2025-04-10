package Sesion04.Actividad03;

public class BinarySearch {
    public static int binarySearch(int[] arr, int lo, int hi, int x) {
        if (hi >= lo && lo < arr.length - 1) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] > x) {
                return binarySearch(arr, lo, mid - 1, x);
            }
            return binarySearch(arr, mid + 1, hi, x);
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int n = arr.length;
        int x = 4; // elemento que se vas a buscar
        int position = binarySearch(arr, 0, n - 1, x);
        if (position == -1) {
            System.out.println("Elemento no encontrado");
        } else {
            System.out.println("Elemento encontrado en la posición: " + position);
        }

    }
}
