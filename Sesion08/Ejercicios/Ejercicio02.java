package Sesion08.Ejercicios;

import Sesion08.Actividad.AVLTree;

public class Ejercicio02 {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        avlTree.insert(5);

        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.insert(4);
        avlTree.insert(6);


//        avlTree.drawBST();
//        System.out.println("-----------");
//        avlTree.insert(20);
//        avlTree.drawBST();
//        System.out.println("-----------");
//        avlTree.insert(25);
//        avlTree.drawBST();
//        System.out.println("-----------");
//        avlTree.insert(27);
////        avlTree.insert(40);
        System.out.println("-----------");
        avlTree.drawBST();

        System.out.println("-----------");
        avlTree.delete(3);
        avlTree.drawBST();

        // Recorrido por amplitud
//        avlTree.printLevelOrder();


        // PREORDER
//        avlTree.printPreOrder();

    }
}
