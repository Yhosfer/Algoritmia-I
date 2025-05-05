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
            System.out.println("Last: "+cola.backElemento());
            //cola.print();
//
            cola.enqueue(6);
            cola.enqueue(7);
            cola.print();
//            System.out.println("\nPrueba despues: ");
//            cola.enqueue(6);
            System.out.println("Atendiendo a First: " + cola.dequeue());
            cola.print();
            System.out.println("First: "+cola.front());
            System.out.println("Last: "+cola.backElemento());



        } catch (ExceptionIsEmpty e){
            System.out.println(e);
        }
    }
}

