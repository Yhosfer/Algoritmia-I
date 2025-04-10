package Sesion04.Actividad01;

public class Hanoi {
    public static void main(String[] args) {
        torresHanoi(3,1,2,3);
    }
    // creando el metodo recursivo
    // para la solución de las torres Hanoi
    public static void torresHanoi(int discos, int trr1, int trr2, int trr3) {
        // caso base
        if (discos == 1) {
            System.out.println("Mover disco de torre " + trr1 + " ExplicaciónActi02 torre "+ trr3);
        } else {
            // Dominio (problema -1)
            torresHanoi(discos - 1, trr1, trr3, trr2);
            System.out.println("Mover disco de torre "+ trr1 + " ExplicaciónActi02 torre "+ trr3);
            torresHanoi(discos - 1, trr2, trr1, trr3);
        }
    }
}
