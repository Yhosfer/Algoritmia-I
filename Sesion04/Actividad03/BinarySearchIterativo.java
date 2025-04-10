package Sesion04.Actividad03;

public class BinarySearchIterativo {
    public static int binarySearch(int arr[], int x){
        int lo = 0, hi = arr.length - 1;

        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == x){
                return mid;
            }
            if (arr[mid] < x){
                lo = mid + 1;
            } else
                hi = mid - 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5};
        int n = arr.length;
        int x = 5;
        int pos = binarySearch(arr, x);
        if (pos == -1){
            System.out.println("Elemento no encontrado");
        }else
            System.out.println("Elemento encontrado en la posición: " + pos);
    }
}
