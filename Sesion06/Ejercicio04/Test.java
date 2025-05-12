package Sesion06.Ejercicio04;

public class Test {

    public static void main(String[] args) {

        Ejercicio_04 e = new Ejercicio_04();
        String prueba = "{()[()]()}[()]()";
        boolean resultado = e.symbolbalancing(prueba);
        System.out.println("¿Anidación Correcta? " + resultado);

    }
}
