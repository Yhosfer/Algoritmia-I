public class Aplicacion {

    public static boolean symbolBalancing(String s) {
        StackLink<Character> stack = new StackLink<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                try {
                    char top = stack.pop();
                    if (!esPar(top, c)) return false;
                } catch (ExceptionIsEmpty e) {
                    return false; // hay más cierres que aperturas
                }
            }
        }

        return stack.isEmpty(); // al final no deben quedar aperturas sin cerrar
    }

    private static boolean esPar(char abierto, char cerrado) {
        return (abierto == '(' && cerrado == ')') ||
               (abierto == '[' && cerrado == ']') ||
               (abierto == '{' && cerrado == '}');
    }
}
