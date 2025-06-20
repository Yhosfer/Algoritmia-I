package Sesion10.LecturaTxt;

public class MainTxt {
    public static void main(String[] args) {
        try {
            Btree<Integer> b = Btree.building_Btree("Sesion10/LecturaTxt/arbolB.txt");
            System.out.println(b);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
