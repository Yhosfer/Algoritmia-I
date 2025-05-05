package Sesion06.Ejercicio04;

import Sesion06.Ejercicio01.StackLink;
import Sesion06.Ejercicio01.ExceptionIsEmpty;

public class App {
    public static boolean symbol_balancing(String s) {
        StackLink<Character> stack = new StackLink<>();

        try {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (c == ')' || c == ']' || c == '}') {
                    if (stack.isEmpty()) return false;

                    char top = stack.pop();  // puede lanzar ExceptionIsEmpty
                    if (!matches(top, c)) return false;
                }
            }

            return stack.isEmpty(); // si queda algo, está desbalanceado
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    // Método main de prueba
    public static void main(String[] args) {
        String prueba = "{()[()]()}[()]()";
        boolean resultado = symbol_balancing(prueba);
        System.out.println("¿Balanceado? " + resultado);
    }
}
