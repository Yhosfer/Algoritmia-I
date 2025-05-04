//package Sesion06.Ejercicio02;
//
//public class QueueArray <E> implements Queue<E> {
//    private E[] ArrayCola;
//    private int currentSize;
//    private int front;
//    private int back;
//    public QueueArray(int tamanio) {
//        this.currentSize = -1;
//        ArrayCola = (E[]) new Object[tamanio];
//        front = 0;
//        back = -1;
//    }
//
//    public boolean isEmpty() {
//        return currentSize == -1;
//    }
//
//    public void enqueue(E element) {
//        if (currentSize == (ArrayCola.length - 1)) {
//            System.out.println("Cola Llena. ");
//            return;
//        }
//
//        ArrayCola[back] = element;
//
//    }
//
//}
