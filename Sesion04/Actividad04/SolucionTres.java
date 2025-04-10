package Sesion04.Actividad04;

import java.util.ArrayList;
import java.util.List;

public class SolucionTres {

    static class Limits {
        int[] a;
        int prim, ult;

        Limits(int[] a, int prim, int ult) {
            this.a = a;
            this.prim = prim;
            this.ult = ult;
        }

        int length() {
            return ult - prim + 1;
        }
    }

    // Simulamos los conjuntos con listas
    private static List<Limits> homogeneo = new ArrayList<>();
    private static List<Limits> heterogeneo = new ArrayList<>();

    public static int moda3(int[] a, int prim, int ult) {
        heterogeneo.add(new Limits(a, prim, ult));

        while (longitudMayor(heterogeneo) > longitudMayor(homogeneo)) {
            Limits p = extraerMayor(heterogeneo);

            int mediana = a[(p.prim + p.ult) / 2];

            int[] izqDer = new int[2];
            pivote2(p.a, mediana, p.prim, p.ult, izqDer);

            Limits p1 = new Limits(p.a, p.prim, izqDer[0] - 1);
            Limits p2 = new Limits(p.a, izqDer[0], izqDer[1] - 1);
            Limits p3 = new Limits(p.a, izqDer[1], p.ult);

            if (p1.prim <= p1.ult) heterogeneo.add(p1);
            if (p3.prim <= p3.ult) heterogeneo.add(p3);
            if (p2.prim <= p2.ult) homogeneo.add(p2);
        }

        if (homogeneo.isEmpty()) return a[prim];

        Limits moda = extraerMayor(homogeneo);
        return moda.a[moda.prim];
    }

    private static int longitudMayor(List<Limits> lista) {
        int max = 0;
        for (Limits l : lista) {
            max = Math.max(max, l.length());
        }
        return max;
    }

    private static Limits extraerMayor(List<Limits> lista) {
        int maxIndex = 0;
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i).length() > lista.get(maxIndex).length()) {
                maxIndex = i;
            }
        }
        return lista.remove(maxIndex);
    }

    private static void pivote2(int[] a, int pivote, int prim, int ult, int[] izqDer) {
        int izq = prim;
        int der = ult;
        int i = prim;

        while (i <= der) {
            if (a[i] < pivote) {
                int temp = a[izq]; a[izq] = a[i]; a[i] = temp;
                izq++; i++;
            } else if (a[i] > pivote) {
                int temp = a[der]; a[der] = a[i]; a[i] = temp;
                der--;
            } else {
                i++;
            }
        }

        izqDer[0] = izq;
        izqDer[1] = der + 1;
    }


    public static void main(String[] args) {
        int[] datos = {4, 3, 4, 2, 4, 5, 5, 5, 5, 6, 6};

        int moda = moda3(datos, 0, datos.length - 1);
        System.out.println("Moda3: " + moda);
    }
}
