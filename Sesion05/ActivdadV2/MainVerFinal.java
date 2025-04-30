package Sesion05.ActivdadV2;

import java.util.List;

public class MainVerFinal {
    public static void main(String[] args){
        GestorDeTarea<Tarea> listaTareas = new GestorDeTarea<>();
        listaTareas.agregarTarea(new Tarea("Tarea Domestica", 1));
        listaTareas.agregarTarea(new Tarea("Tarea Academia", 2));
        listaTareas.agregarTarea(new Tarea("Tarea Universidad", 3));
        listaTareas.agregarTarea(new Tarea("Tarea Univiversidad Urgente", 4));

        //listaTareas.mostrarTareas();
        //listaTareas.eliminarTarea(new Tarea("Tarea Academia", 1));
        listaTareas.mostrarTareas();

        // Imprime el total de Tareas
        System.out.println("Total de Tareas: "+listaTareas.contarTareas());

        // Tarea mas prioritaria
        System.out.println("Tarea mas prioritaria: "+listaTareas.obtenerTareaMasPrioritaria());

        // Verificar existencias de x Tarea
        Tarea tarea = new Tarea("Tarea A", 1);
        System.out.println("Existe la tarea: "+listaTareas.contieneTarea(tarea));
        System.out.println("Existe la tarea: "+listaTareas.contieneTarea(new Tarea("Tarea Domestica", 1)));

        // Invertir Lista
        listaTareas.invertirTarea();
        //listaTareas.mostrarTareas();

        // Tareas completas
        GestorDeTarea<Tarea> listaTareasCompletas = new GestorDeTarea<>();
        listaTareas.transferir_tarea_completa(new Tarea("Tarea Universidad Urgente", 4), listaTareasCompletas);
        System.out.println("");
        // Mostrar ambas Listas
        System.out.println("Tareas: ");
        listaTareas.mostrarTareas();
        System.out.println("Tareas completas: ");
        //listaTareasCompletas.mostrarTareas();


    }
}
