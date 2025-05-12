package Sesion06.Ejercicio04;

import Sesion06.Ejercicio01.StackLink;
import Sesion06.Ejercicio01.ExceptionIsEmpty;

public class Ejercicio_04 {

    public static boolean symbolbalancing(String s) {

        StackLink<Character> stack = new StackLink<>();

        try {

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);

                } else if (c == ')' || c == ']' || c == '}') {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    char top = stack.pop();

                    if (!matches(top, c)){
                        return false;
                    }

                }

            }

            return stack.isEmpty();

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



}
