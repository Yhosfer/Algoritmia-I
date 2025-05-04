package Sesion06.Ejercicio02;

public class TestEjer02 {
    public static void main(String[] args) {
        try {
            QueueArray cola = new QueueArray(4);

            cola.enqueue(1);
            cola.enqueue(2);
            cola.enqueue(3);
            cola.enqueue(4);
            cola.enqueue(5);

            System.out.println("Atendiendo a First: " + cola.dequeue());
            System.out.println("Last: "+cola.back());
            cola.print();


        } catch (ExceptionIsEmpty e){
            System.out.println(e);
        }
    }
}

