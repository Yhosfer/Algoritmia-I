package Participación;

public class Test {
    public static void main(String[] args) {
        BolsasNumeros<Integer> bol = new BolsasNumeros<>();
        bol.agregar(6);
        bol.agregar(2);
        bol.agregar(3);

        BolsasNumeros<Integer> bol2 = new BolsasNumeros<>();
        bol2.agregar(4);
        bol2.agregar(5);
        bol2.agregar(6);

        BolsasNumeros<String> bol3 = new BolsasNumeros<>();
        bol3.agregar("uno");
        bol3.agregar("dos");
        bol3.agregar("tres");

        SBolsa sBolsa = new SBolsa();
        sBolsa.agregar(bol);
        sBolsa.agregar(bol2);
        sBolsa.agregar(bol3);

        sBolsa.mostrar();

        bol.ordenamiento();
        bol.mostrar();
    }
}
