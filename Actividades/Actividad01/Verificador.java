package Actividades.Actividad01;

public class Verificador {
    public static double menorX = 0;
    public static double mayorX = 0;
    public static double menorY = 0;
    public static double mayorY = 0;

    //
    public Verificador() {}
    
    public static void calcularVars(Rectangulo r1) {
        // Este metodo esta hecho para ordenar el menor y mayor valor de cada esquina X y Y para facilitar el uso de los demas metodos
        if (r1.getEsquina1().getX() < r1.getEsquina2().getX()) {
            menorX = r1.getEsquina1().getX();
            mayorX = r1.getEsquina2().getX();
        } else {
            mayorX = r1.getEsquina1().getX();
            menorX = r1.getEsquina2().getX();
        }

        if (r1.getEsquina1().getY() < r1.getEsquina2().getY()) {
            menorY = r1.getEsquina1().getY();
            mayorY = r1.getEsquina2().getY();
        } else {
            mayorY = r1.getEsquina1().getY();
            menorY = r1.getEsquina2().getY();
        }
    }



    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        //Este metodo ve si el valor de una de las esquinas del segundo rectangulo
        // estan entre los apartados menor y mayor del primero y saber si esta sobre este
        calcularVars(r1);
        double r1minX = menorX;
        double r1maxX = mayorX;
        double r1minY = menorY;
        double r1maxY = mayorY;
        calcularVars(r2);
        double r2minX = menorX;
        double r2maxX = mayorX;
        double r2minY = menorY;
        double r2maxY = mayorY;

        return (Math.max(r1minX, r2minX) < Math.min(r1maxX, r2maxX)) &&
                (Math.max(r1minY, r2minY) < Math.min(r1maxY, r2maxY));
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        calcularVars(r1);
    //este metodo lo que hace es comparar que la X o Y de un rectangulo este en la misma posición que el otro
        if (menorX == r2.getEsquina1().getX() || menorX == r2.getEsquina2().getX()) {
            return true;
        } else if (mayorX == r2.getEsquina1().getX() || mayorX == r2.getEsquina2().getX()) {
            return true;
        } else {
            if (menorY == r2.getEsquina1().getY() || menorY == r2.getEsquina2().getY()) {
                return true;
            } else if (mayorY == r2.getEsquina1().getY() || mayorY == r2.getEsquina2().getY()) {
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
