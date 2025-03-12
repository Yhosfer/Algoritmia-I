package Actividades.Actividad01;

public class Verificador {
    public static int var1 = 0;
    public static int var2 = 0;
    public static int var3 = 0;
    public static int var4 = 0;

    private static void calcularVars(Rectangulo r1) {
        if (r1.getEsquina1().getX() < r1.getEsquina2().getX()) {
            var1 = (int) r1.getEsquina1().getX();
            var2 = (int) r1.getEsquina2().getX();
        } else {
            var2 = (int) r1.getEsquina1().getX();
            var1 = (int) r1.getEsquina2().getX();
        }

        if (r1.getEsquina1().getY() < r1.getEsquina2().getY()) {
            var3 = (int) r1.getEsquina1().getY();
            var4 = (int) r1.getEsquina2().getY();
        } else {
            var4 = (int) r1.getEsquina1().getY();
            var3 = (int) r1.getEsquina2().getY();
        }
    }

    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);

        if (r2.getEsquina1().getX() > var1 && r2.getEsquina1().getX() < var2) {
            return true;
        } else if (r2.getEsquina2().getX() > var1 && r2.getEsquina2().getX() < var2) {
            return true;
        } else if (r2.getEsquina1().getY() > var3 && r2.getEsquina1().getY() < var4) {
            return true;
        } else if (r2.getEsquina2().getY() > var3 && r2.getEsquina2().getY() < var4) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);

        if (var1 == (int) r2.getEsquina1().getX() || var1 == (int) r2.getEsquina2().getX()) {
            return true;
        } else if (var2 == (int) r2.getEsquina1().getX() || var2 == (int) r2.getEsquina2().getX()) {
            return true;
        } else {
            if (var3 == (int) r2.getEsquina1().getY() || var3 == (int) r2.getEsquina2().getY()) {
                return true;
            } else if (var4 == (int) r2.getEsquina1().getY() || var4 == (int) r2.getEsquina2().getY()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1,r2);
    }
}
