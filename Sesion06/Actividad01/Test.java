package Sesion06.Actividad01;

public class Test {
    public static void main(String[] args) {

        Stack<String> pila = new StackArray<>(4);

        pila.push("uno");
        pila.push("dos");
        pila.push("tres");

        System.out.println(pila);

        try {
            System.out.println("Top: " + pila.top()); // tres
            System.out.println("Pop: " + pila.pop()); // tres
            System.out.println("Top después de pop: " + pila.top()); // dos
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        System.out.println("¿Está vacía?: " + pila.isEmpty());
        pila.push("cuatro");
        pila.push("cinco");
        System.out.println(pila); 
    }
}
