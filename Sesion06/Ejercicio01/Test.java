public class Test {
    public static void main(String[] args) {

        Stack<Integer> pila = new StackLink<>();

        pila.push(100);
        pila.push(200);
        pila.push(300);

        System.out.println(pila); // Stack: 300 200 100

        try {
            System.out.println("Top: " + pila.top()); // 300
            System.out.println("Pop: " + pila.pop()); // 300
            System.out.println("Top después de pop: " + pila.top()); // 200
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        System.out.println("¿Está vacía?: " + pila.isEmpty());
        try {
        pila.pop();
        pila.pop();
        pila.pop();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción esperada: " + e.getMessage());
        }
    }
}
