package Sesion06.Actividad02;

public class Test {
    public static void main(String[] args) {
        try {
            QueueLink<Integer> a = new QueueLink<>();
            a.enqueue(1);
            a.enqueue(2);
            a.enqueue(3);
            a.enqueue(4);
            a.dequeue();

            a.print();
            System.out.println(a.front());
            System.out.println(a.back());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e);
        }
    }
}
