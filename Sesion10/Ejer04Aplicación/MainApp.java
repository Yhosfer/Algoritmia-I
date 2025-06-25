package Sesion10.Ejer04Aplicación;

public class MainApp {
    public static void main(String[] args) {

        RegistroEstudiante est3 = new RegistroEstudiante(101, "Carlos");

        Btree arbolEst = new Btree(4);

        arbolEst.insert(new RegistroEstudiante(103, "Ana"));
        arbolEst.insert(new RegistroEstudiante(110, "Luis"));
        arbolEst.insert(est3);
        arbolEst.insert(new RegistroEstudiante(120, "Lucia"));
//        arbolEst.insert(new RegistroEstudiante(115, "David"));
        arbolEst.insert(new RegistroEstudiante(115, "David"));
        arbolEst.insert(new RegistroEstudiante(125, "Jorge"));
        arbolEst.insert(new RegistroEstudiante(140, "Camila"));
        arbolEst.insert(new RegistroEstudiante(108, "Rosa"));
        arbolEst.insert(new RegistroEstudiante(132, "Ernesto"));
        arbolEst.insert(new RegistroEstudiante(128, "Denis"));
        arbolEst.insert(new RegistroEstudiante(145, "Enrique"));
        arbolEst.insert(new RegistroEstudiante(122, "Karina"));
        arbolEst.insert(new RegistroEstudiante(108, "Juan"));

        // Busqueda con codigo

        System.out.println("David? "+arbolEst.buscarNombre(115));

        System.out.println("Ernesto? "+arbolEst.buscarNombre(132));

        System.out.println("? "+arbolEst.buscarNombre(999));

        // Eliminar estudiante de código 101

        // eliminamos a carlos
        arbolEst.delete(est3);
        // insertamos a sara
        arbolEst.insert(new RegistroEstudiante(106, "Sara"));
        arbolEst.insert(new RegistroEstudiante(115, "David"));
        // busqueda
        System.out.println("David? "+arbolEst.buscarNombre(115));
        System.out.println(arbolEst.buscarNombre(106));
        System.out.println(arbolEst);
    }
}
