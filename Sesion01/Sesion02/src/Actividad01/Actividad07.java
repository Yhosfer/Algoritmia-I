package Actividad01;

import Ejercicio05_06.Chocolatina;

public class Actividad07 {
    public static void main(String[] args) {
        Bolsa<Golosina> bolsaGolosi = new Bolsa<>(5);
        Golosina golosiA = new Golosina("OleOle",5);
        Golosina golosiB = new Golosina("Chinchin",12);
        Golosina golosiC = new Golosina("MiniChips",14);

        bolsaGolosi.add(golosiA);
        bolsaGolosi.add(golosiB);
        bolsaGolosi.add(golosiC);

        bolsaGolosi.mostrarBolsa();

    }
}
