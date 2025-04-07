package Sesion04;

public class DiscosHanoui {
    public static void hanio(int n,int origen,int destino, int aux){
        if (n == 1){
            System.out.println("Mueva el disco "+ n + " desde la torre " + origen+ " hasta la torre "+ destino);
        } else {
            DiscosHanoui.hanio(n-1, origen, aux, destino);
            System.out.println("Mueva el disco "+ n + " desde la torre " + origen+ " hasta la torre "+ destino);
        }

    }
}
