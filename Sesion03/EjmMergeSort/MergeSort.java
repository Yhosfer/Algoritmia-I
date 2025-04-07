package Sesion03.EjmMergeSort;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length <=1) {
            return;
        }

        int mitad = arr.length/2;
        int[] izquierdaArr = new int[mitad];
        int[] derechaArr = new int[arr.length -mitad];

        System.arraycopy(arr, 0, izquierdaArr, 0, mitad);

        if (arr.length - mitad >= 0) {
            System.arraycopy(arr, mitad, derechaArr, 0,
                    arr.length - mitad);

        }
        mergeSort(izquierdaArr);
        mergeSort(derechaArr);
        merge(izquierdaArr, derechaArr, arr);

    }
    public static void merge(int[] izquierArr, int[] derechaArr, int[] arr) {
        int i = 0, j = 0, k = 0;

        while (i < izquierArr.length && j < derechaArr.length) {
            if (izquierArr[i] <= derechaArr[j]) {
                arr[k++] = izquierArr[i++];
            }else {
                arr[k++] = derechaArr[j++];
            }
        }
        while (i < izquierArr.length) {
            arr[k++] = izquierArr[i++];
        }
        while (j < derechaArr.length) {
            arr[k++] = derechaArr[j++];
        }
    }
    public static void main(String[] args) {
        int[] array  = new int[]{3,4,1,7,8,19,13,2,10,17};

        mergeSort(array);
        System.out.println("Ordenamiento Merge Sort: ");
        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}
