import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EJ_01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n_el = scanner.nextInt();

        int[] arr = new int[n_el];

        for (int i = 0; i < n_el; i++) {

            arr[i] = scanner.nextInt();

        }

        int meta = scanner.nextInt();

        if (suma_sub_arr(n_el, arr, meta)) {

            System.out.println("Posible");

        } else {

            System.out.println("Imposible");

        }

        scanner.close();

    }


    public static boolean isPotencia(int n) {

        if (n <= 0) {

            return false;

        }

        while (n % 2 == 0) {

            n = n / 2;

        }

        return n == 1;

    }

    public static boolean suma_sub_arr(int n_el, int[] arr, int meta) {

        int suma_obligatoria = 0;
        List<Integer> numeros_sobrantes = new ArrayList<>();

        for (int i = 0; i < n_el; i++) {

            int num = arr[i];

            if (isPotencia(num)) {

                suma_obligatoria += num;

            } else {

                if (num % 5 == 0) {

                    if (i < n_el - 1 && arr[i + 1] % 2 != 0) {

                        continue;

                    }

                }

                numeros_sobrantes.add(num);

            }

        }

        if (suma_obligatoria > meta) {

            return false;

        }

        int sobrante_meta = meta - suma_obligatoria;

        return suma_sub_sub_arr(numeros_sobrantes, sobrante_meta);
    }

    public static boolean suma_sub_sub_arr(List<Integer> numeros_sobrantes, int sobrante_meta) {
        boolean[] sumas = new boolean[sobrante_meta + 1];
        sumas[0] = true;

        for (int num : numeros_sobrantes) {

            for (int j = sobrante_meta; j >= num; j--) {

                if (sumas[j - num]) {

                    sumas[j] = true;

                }

            }

        }

        return sumas[sobrante_meta];

    }

}
