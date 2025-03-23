package Sesion01.Ejercicio01;

public class Verificador {
    public static double menorX = 0;
    public static double mayorX = 0;
    public static double menorY = 0;
    public static double mayorY = 0;

    //Se ordenan las coordenadas para evitar cualquier tipo de error
    public static void calcularVars(Rectangulo r1) {

        if (r1.getEsquina1().getX() < r1.getEsquina2().getX()) {
            menorX = r1.getEsquina1().getX();
            mayorX = r1.getEsquina2().getX();
        } else {
            menorX = r1.getEsquina2().getX();
            mayorX = r1.getEsquina1().getX();
        }


        if (r1.getEsquina1().getY() < r1.getEsquina2().getY()) {
            menorY = r1.getEsquina1().getY();
            mayorY = r1.getEsquina2().getY();
        } else {
            menorY = r1.getEsquina2().getY();
            mayorY = r1.getEsquina1().getY();

        }
    }

    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);
        double r1minX = menorX, r1maxX = mayorX;
        double r1minY = menorY, r1maxY = mayorY;

        calcularVars(r2);
        double r2minX = menorX, r2maxX = mayorX;
        double r2minY = menorY, r2maxY = mayorY;

        return (Math.max(r1minX, r2minX) < Math.min(r1maxX, r2maxX)) &&
                (Math.max(r1minY, r2minY) < Math.min(r1maxY, r2maxY));
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);
        if (esSobrePos(r1, r2)) {
            return (menorX == r2.getEsquina1().getX() || menorX == r2.getEsquina2().getX()) ||
                    (mayorX == r2.getEsquina1().getX() || mayorX == r2.getEsquina2().getX()) ||
                    (menorY == r2.getEsquina1().getY() || menorY == r2.getEsquina2().getY()) ||
                    (mayorY == r2.getEsquina1().getY() || mayorY == r2.getEsquina2().getY());
        }
        return false;

    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
    public static void verificar(Rectangulo r1, Rectangulo r2) {
        if (esSobrePos(r1, r2)) {
            System.out.println("Rectángulos sobrepuestos");
        } else if (esJunto(r1, r2)) {
            System.out.println("Rectángulos juntos");
        } else {
            System.out.println("Rectángulos disjuntos");
        }
    }
}
