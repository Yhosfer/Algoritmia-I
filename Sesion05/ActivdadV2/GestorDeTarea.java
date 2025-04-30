package Sesion05.ActivdadV2;

public class GestorDeTarea <T extends Comparable<T>> {
    Lista_Enlazada<T> lista ;
    public GestorDeTarea(){
        lista = new Lista_Enlazada<>();
    }
     public void agregarTarea(T tarea){
        lista.insertLast(tarea);
     }

     public void concatenar_lista(Lista_Enlazada t){
        lista.concatenarListas(t);
     }

     public boolean eliminarTarea(T tarea){
        return lista.removeNodev2(tarea);
     }

     public boolean contieneTarea(T terea){
        return lista.seEncuentraV2(terea);
     }
     public void mostrarTareas(){
        lista.listar();
     }
     public int contarTareas(){
        return lista.length();
     }
     //
     public T obtenerTareaMasPrioritaria(){
        T tarea= lista.obtenerTareaPrioridad();
        return tarea;
     }
     // Invierte la lista
     public void invertirTarea(){
        lista = lista.invertirLista();
     }

     public void transferir_tarea_completa(T tarea, GestorDeTarea t){
        if(contieneTarea(tarea)){

            t.agregarTarea(tarea);
            System.out.println("Tarea transferida");
        } else {
            System.out.println("Tarea no transferida, no la encontramos");
        }

     }

}
