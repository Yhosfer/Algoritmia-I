package Ejercicio05_06;

public class TestChocolatina {
    public static void main(String[] args) {
        // Instacia de Cajoneria
        Cajoneria<Chocolatina> cajoneria = new Cajoneria<>();
        // Instacias de Caja
        Caja<Chocolatina> caja1 = new Caja<>("Verde");
        Caja<Chocolatina> caja2 = new Caja<>("Azul");
        // Instacias de Chocolatina
        Chocolatina chocolatina1 = new Chocolatina("Sublime");
        Chocolatina chocolatina2 = new Chocolatina("Princesa");
        Chocolatina chocolatina3 = new Chocolatina("Melon");
        Chocolatina chocolatina4 = new Chocolatina("Choco");

        caja1.agregarObjeto(chocolatina1);
        caja1.agregarObjeto(chocolatina2);
        caja2.agregarObjeto(chocolatina3);
        caja2.agregarObjeto(chocolatina4);

        cajoneria.agregarCaja(caja1);
        cajoneria.agregarCaja(caja2);

        System.out.println(cajoneria);

        // Busqueda de Golosina
        Chocolatina chocolatina5 = new Chocolatina("Sublime");
        System.out.println("Busqueda de :"+ chocolatina5);
        cajoneria.search(chocolatina5);

    }
}
