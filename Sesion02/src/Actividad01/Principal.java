package Actividad01;

public class Principal {
    public static void main(String[] args){
        Bolsa <Chocolatina> bolsaChoco = new Bolsa<>(4);
        Chocolatina c = new Chocolatina("Milka");
        Chocolatina c2 = new Chocolatina("Milka");
        Chocolatina c3 = new Chocolatina("Ferrero");
        bolsaChoco.add(c);
        bolsaChoco.add(c2);
        bolsaChoco.add(c3);

        for (Chocolatina chocolatina : bolsaChoco) {
            System.out.println(chocolatina);
        }
    }
}
