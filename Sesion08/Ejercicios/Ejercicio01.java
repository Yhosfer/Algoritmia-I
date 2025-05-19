package Sesion08.Ejercicios;

// import Sesion08.Actividad.AVLTree;

import Sesion08.Actividad.AVLTree;

public class Ejercicio01 {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(50);
        avlTree.insert(30);
        avlTree.insert(31);
        avlTree.insert(32);
        avlTree.insert(33);
        avlTree.insert(34);
        avlTree.insert(70);
        avlTree.insert(60);
        avlTree.insert(80);
        avlTree.drawBST();
        System.out.println("-----------");
        avlTree.insert(20);
        avlTree.drawBST();
        System.out.println("-----------");
        avlTree.insert(25);
        avlTree.drawBST();
        System.out.println("-----------");
        avlTree.insert(27);
//        avlTree.insert(40);
        System.out.println("-----------");
        avlTree.drawBST();



    }
}
