package Ejercicio05_06;


public class TestGolosina {
    public static void main(String[] args) {
        // Instacia de Cajoneria
        Cajoneria<Golosina> cajas = new Cajoneria<>();
        // Instacias de Caja
        Caja<Golosina> caja1 = new Caja<>("Rosita");
        Caja<Golosina> caja2 = new Caja<>("Marron");
        // Instacias de Golosina
        Golosina golosa = new Golosina("Golosi",30);
        Golosina gasolina = new Golosina("Gasolina",10);
        Golosina celosa = new Golosina("Celosa",20);
        Golosina golosina1 = new Golosina("Dulce",50);

        caja1.agregarObjeto(golosa);
        caja1.agregarObjeto(gasolina);
        caja2.agregarObjeto(celosa);
        caja2.agregarObjeto(golosina1);

        cajas.agregarCaja(caja1);
        cajas.agregarCaja(caja2);

        System.out.println(cajas);

        // Busqueda de Golosina
        System.out.println("Buscando:"+ gasolina);
        cajas.search(gasolina);
        // Borrado de Golosina

        cajas.delete(gasolina);
        System.out.println();
        System.out.println(cajas);

    }
}
