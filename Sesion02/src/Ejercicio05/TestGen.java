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

        //System.out.println(cajota);

        Golosina gasolina2 = new Golosina("Gasolina",10);
        cajota.search(gasolina2);

        // Test Chocolatinas
        Cajoneria<Chocolatina> andamio = new Cajoneria<Chocolatina>();
        Caja<Chocolatina> cajaChoco1 = new Caja<>("Gris");
        Caja<Chocolatina> cajaChoco2 = new Caja<>("Rojo");

        Chocolatina choco1 = new Chocolatina("Nestle");
        Chocolatina choco2 = new Chocolatina("CocoaSuyo");
        Chocolatina choco3 = new Chocolatina("aaaaa");
        Chocolatina choco4 = new Chocolatina("Suyo");

        cajaChoco1.agregarObjeto(choco1);
        cajaChoco1.agregarObjeto(choco2);
        cajaChoco2.agregarObjeto(choco3);
        cajaChoco2.agregarObjeto(choco4);

        andamio.agregarCaja(cajaChoco1);
        andamio.agregarCaja(cajaChoco2);


        andamio.search(choco3);
        System.out.println(andamio);

        andamio.delete(choco4);
        System.out.println(andamio);
    }
}
