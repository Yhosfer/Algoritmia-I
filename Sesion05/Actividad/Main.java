package Sesion05.Actividad;

public class Main {
    public static void main(String[] args) {

        // Instancia de la clase ListaEnlazada
        Gestor_Tarea lista = new Gestor_Tarea();
        Tarea tare1 = new Tarea("Casa",2);
        Tarea tare2 = new Tarea("Academia",3);
        Tarea tare3 = new Tarea("Universidad",1);

        lista.insertLast(tare1);
        lista.insertLast(tare2);
        lista.insertLast(tare3);

        System.out.println("Tareas: "+lista.obtenerTareaPrioridad());
        //System.out.println("Tareas: "+lista.obtenerTareaMasPrioridad());









    }

}
