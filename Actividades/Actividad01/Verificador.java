package Actividades.Actividad01;

public class Verificador {
    public static double menorX = 0;
    public static double mayorX = 0;
    public static double menorY = 0;
    public static double mayorY = 0;

    // Comentarios
    public static void calcularVars(Rectangulo r1) {
        // Condicional se cumple si x1 es menos que x2 ???
        if (r1.getEsquina1().getX() < r1.getEsquina2().getX()) {
            menorX = (double) r1.getEsquina1().getX();
            mayorX = (double) r1.getEsquina2().getX();
        } else {
            mayorX = (double) r1.getEsquina1().getX();
            menorX = (double) r1.getEsquina2().getX();
        }

        if (r1.getEsquina1().getY() < r1.getEsquina2().getY()) {
            menorY = (double) r1.getEsquina1().getY();
            mayorY = (double) r1.getEsquina2().getY();
        } else {
            mayorY = (double) r1.getEsquina1().getY();
            menorY = (double) r1.getEsquina2().getY();
        }
    }



    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);

        if (r2.getEsquina1().getX() > menorX && r2.getEsquina1().getX() < mayorX) {
            return true;
        } else if (r2.getEsquina2().getX() > menorX && r2.getEsquina2().getX() < mayorX) {
            return true;
        } else if (r2.getEsquina1().getY() > menorY && r2.getEsquina1().getY() < mayorY) {
            return true;
        } else if (r2.getEsquina2().getY() > menorY && r2.getEsquina2().getY() < mayorY) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);

        if (menorX == (double) r2.getEsquina1().getX() || menorX == (double) r2.getEsquina2().getX()) {
            return true;
        } else if (mayorX == (double) r2.getEsquina1().getX() || mayorX == (double) r2.getEsquina2().getX()) {
            return true;
        } else {
            if (menorY == (double) r2.getEsquina1().getY() || menorY == (double) r2.getEsquina2().getY()) {
                return true;
            } else if (mayorY == (double) r2.getEsquina1().getY() || mayorY == (double) r2.getEsquina2().getY()) {
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
