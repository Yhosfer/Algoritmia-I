package Ejercicio05;


public class TestGen {
    public static void main(String[] args) {
        Cajoneria<Golosina> cajota = new Cajoneria<>();
        Caja<Golosina> caja1 = new Caja<>("Rosita");
        Caja<Golosina> caja2 = new Caja<>("Marron");

        Golosina golosa = new Golosina("Golosa",30);
        Golosina gasolina = new Golosina("Gasolina",10);
        Golosina celosa = new Golosina("Celosa",20);

        caja1.agregarObjeto(golosa);
        caja1.agregarObjeto(gasolina);
        caja2.agregarObjeto(celosa);

        cajota.agregarCaja(caja1);
        cajota.agregarCaja(caja2);

        System.out.println(cajota);

        Golosina gasolina2 = new Golosina("Gasolina",10);
        cajota.search(gasolina2);

        // Test Chocolatinas

    }
}

