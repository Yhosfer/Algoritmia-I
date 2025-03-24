//package Ejercicio03;
//
//import java.util.ArrayList;
//
//public class Cajoneria {
//    private ArrayList<Caja> caja = new ArrayList();
//
//    public void agregarCaja(Caja<T> caja){
//        this.caja.add(caja);
//    }
//
//    public void search(T objeto){
//        int pos = 0;
//        for (Caja<T> c : caja) {
//            for (T x : c){
//                if(x.compareTo(objeto) == 0){
//                    System.out.println("Se encontro el objeto en la posicion " + pos + " y la caja es color " + c.getColor());
//                    return;
//                }
//            }
//            pos++;
//        }
//        System.out.println("No se encontró el objeto.");
//    }
//
//}
