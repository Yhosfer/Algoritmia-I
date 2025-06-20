package Sesion10.ToString_IdNode;

public class MainToString {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Btree<Integer> arbolbb = new Btree<>(5);
        arbolbb.insert(100);
        arbolbb.insert(50);
        arbolbb.insert(70);
        arbolbb.insert(10);
        arbolbb.insert(30);
        arbolbb.insert(80);
        arbolbb.insert(200);
        arbolbb.insert(25);
        arbolbb.insert(15);
        arbolbb.insert(5);
        arbolbb.insert(65);
        arbolbb.insert(35);
        arbolbb.insert(60);
        arbolbb.insert(18);
        arbolbb.insert(93);
        arbolbb.insert(94);

        System.out.println(arbolbb);
//        arbolbb.printPretty();

        // Search
        arbolbb.search(94);
    }

}
