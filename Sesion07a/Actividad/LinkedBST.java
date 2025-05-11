package Sesion07a.Actividad;

import Sesion07a.Exceptions.ItemDuplicated;
import Sesion07a.Exceptions.ItemNoFound;
import Sesion07a.bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    private class Node<E> {
        E data;
        Node<E> left, right;

        Node(E data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node<E> root;

    public LinkedBST() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E data) {
        root = insertRecursivo(root, data);
    }

    private Node<E> insertRecursivo(Node<E> node, E data) {
        if (node == null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = insertRecursivo(node.left, data);
        else if (cmp > 0)
            node.right = insertRecursivo(node.right, data);
        else
            throw new ItemDuplicated("Elemento duplicado: " + data);
        return node;
    }

    @Override
    public E search(E data) {
        return searchRecursivo(root, data);
    }

    private E searchRecursivo(Node<E> node, E data) {
        if (node == null)
            throw new ItemNoFound("Error: Elemento no encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            return searchRecursivo(node.left, data);
        else if (cmp > 0)
            return searchRecursivo(node.right, data);
        else
            return node.data;
    }

    @Override
    public void delete(E data) {
        root = deleteRecursivo(root, data);
    }

    private Node<E> deleteRecursivo(Node<E> node, E data) {
        if (node == null)
            throw new ItemNoFound("Elemento no encontrado: " + data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0)
            node.left = deleteRecursivo(node.left, data);
        else if (cmp > 0)
            node.right = deleteRecursivo(node.right, data);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            //
            Node<E> minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = deleteRecursivo(node.right, minNode.data);
        }
        return node;
    }

    // Actvidad 07 inOrder
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRecursivo(root, sb);
        return sb.toString();
    }
    // recorrido (izquierda, cabecera, derecha)
    private void inOrderRecursivo(Node<E> node, StringBuilder sb) {
        if (node != null) {
            inOrderRecursivo(node.left, sb);         // Rama izquierda
            sb.append(node.data).append(" ");   // Se guarda del dato de "node"
            inOrderRecursivo(node.right, sb);        // Rama derecha
        }
    }
    // Actvidad 08 preOrder
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRecursivo(root, sb);
        return sb.toString();
    }
    // recorrido (cabecera, izquierda, derecha)
    private void preOrderRecursivo(Node<E> node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");  // Se guarda el dato de ""node"
            preOrderRecursivo(node.left, sb);  // Rama izquierda
            preOrderRecursivo(node.right, sb); // Rama derecha
        }
    }
    // Actvidad 09 postOrder
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRecursivo(root, sb);
        return sb.toString();
    }
    // recorrido (izquierda, derecha, cabecera)
    private void postOrderRecursivo(Node<E> node, StringBuilder sb) {
        if (node != null) {
            postOrderRecursivo(node.left, sb);  // Rama <--
            postOrderRecursivo(node.right, sb); // Rama -->
            sb.append(node.data).append(" ");
        }
    }
    // Actividad 10
    // Retorna el nodo de menor valor en la rama izquierda
    private Node<E> findMin(Node<E> node) {
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
    // Actividad 10
    // Retorna el nodo de menor valor en la rama derecha
    private Node<E> findMax(Node<E> node) {
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> node, StringBuilder sb) {
        if (node != null) {
            toStringRec(node.left, sb);
            sb.append(node.data).append(" ");
            toStringRec(node.right, sb);
        }
    }




    public static void main (String[] args) {
        LinkedBST aaa = new LinkedBST();
        aaa.insert(55);
        aaa.insert(66);
        aaa.insert(77);
        aaa.insert(33);
        aaa.insert(63);
        aaa.insert(75);
        aaa.delete(66);

        System.out.println("Uwu:  "+aaa.search(63));


        System.out.println(aaa);
    }
}
