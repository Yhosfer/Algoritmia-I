package Sesion05.ActivdadV2;

public class MainVerFinal {
    public static void main(String[] args){
        GestorDeTarea<Tarea> listaTareas = new GestorDeTarea<>();
        listaTareas.agregarTarea(new Tarea("Tarea Academia", 1));
        listaTareas.agregarTarea(new Tarea("Tarea Casa", 2));
        listaTareas.agregarTarea(new Tarea("Tarea Univ", 3));

        //listaTareas.mostrarTareas();
        //listaTareas.eliminarTarea(new Tarea("Tarea Academia", 1));
        listaTareas.mostrarTareas();
        System.out.println("Tareas: "+listaTareas.contarTareas());
        System.out.println("Tarea mas prioritaria: "+listaTareas.obtenerTareaMasPrioritaria());
    }
}
