package Ejercicio03_04__v2;

public class Test03_04 {
    public static void main(String[] args) {
        Cajoneria<Integer> cajoneria = new Cajoneria<>();
        Caja<Integer> caja1 = new Caja<>("Rojo");
        Caja<Integer> caja2 = new Caja<>("Rosa");
        Caja<Integer> caja3 = new Caja<>("Verde");

        caja1.agregarObjeto(1);
        caja1.agregarObjeto(2);
        caja1.agregarObjeto(3);

        caja2.agregarObjeto(4);
        caja2.agregarObjeto(5);
        caja2.agregarObjeto(6);

        caja3.agregarObjeto(7);
        caja3.agregarObjeto(8);
        caja3.agregarObjeto(9);

        cajoneria.agregarCaja(caja1);
        cajoneria.agregarCaja(caja2);
        cajoneria.agregarCaja(caja3);

        cajoneria.search(7);
        //error si

        Integer obj = cajoneria.delete(6);
        System.out.println("Se elimino el objeto " + obj);

        cajoneria.search(8);


        System.out.println(cajoneria);
    }
}

