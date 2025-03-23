package Ejercicio01;

public class MetodoGenericoBusqueda {
    static <T extends Comparable<T>> boolean exist(T[] x, T y){
        for (int i = 0; i < x.length; i++){
            if (x[i].equals(y)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String[] v = {"Perez","Sanchez","Rodriguez"};
        Integer[] w = {12,34,56};

        System.out.println("Existe? " + exist(v,"Rodriguez"));
        System.out.println("Existe? " + exist(v,"Sanchet"));
        System.out.println("Existe? "+ exist(w, 32));
        System.out.println("Existe? "+ exist(w, 34));
        // Error el metodo solo acepta valores del mismo tipo
        //System.out.println("Existe? "+ exist(v, 56));

    }
}
