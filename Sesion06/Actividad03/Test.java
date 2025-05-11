package Sesion06.Actividad03;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> cola = new PriorityQueueLinkSort<>();

        cola.enqueue("Tarea B", 2);
        cola.enqueue("Tarea A", 3);
        cola.enqueue("Tarea C", 1);
        cola.enqueue("Tarea D", 3); 

        System.out.println(cola);

        try {
            System.out.println("Front: " + cola.front()); // A
            System.out.println("Back: " + cola.back());   // C
            System.out.println("Dequeue: " + cola.dequeue()); // A
            System.out.println(cola);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
