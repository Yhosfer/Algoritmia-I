package Ejercicio01;


public class Principal {
    public static void main(String[] args){
        Bolsa<Chocolatina> bolsaChoco = new Bolsa<>(4);

        Chocolatina c = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ambrosoli");
        Chocolatina c3 = new Chocolatina("ferrero");
        bolsaChoco.add(c);
        bolsaChoco.add(c2);
        bolsaChoco.add(c3);

        Chocolatina c4 = new Chocolatina("ferrero");

    
        System.out.println(exist(bolsaChoco,c4));


    }
    public static <T extends Comparable<T>> boolean exist(Bolsa<T> x, T y){
        for (T t : x){
            if (t.compareTo(y) == 0){
                return true;
            }
        }
        return false;
    }
}
