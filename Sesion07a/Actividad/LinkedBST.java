package Sesion07a.Actividad;

import Sesion06.Actividad02.ExceptionIsEmpty;
import Sesion07a.Exceptions.*;
import Sesion07a.bstreeInterface.BinarySearchTree;
import Sesion06.Actividad02.QueueLink;

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
    public void insert(E data) throws ItemDuplicated {
        root = insertRecursivo(root, data);
    }

    private Node<E> insertRecursivo(Node<E> node, E data) throws ItemDuplicated {
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
    public E search(E data) throws ItemNoFound {
        return searchRecursivo(root, data);
    }

    private E searchRecursivo(Node<E> node, E data) throws ItemNoFound {
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
    public void delete(E data) throws ExceptionIsEmptyBST{
        root = deleteRecursivo(root, data);
    }

    private Node<E> deleteRecursivo(Node<E> node, E data) throws ExceptionIsEmptyBST {
        if (node == null)
            throw new ExceptionIsEmptyBST("Elemento no encontrado: " + data);

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

//    Ejercicio 01: Se agregaron los siguientes metodos:
//    destroyNodes(),  countAllNodes(), countNodes(), height(x), amplitude(Nivel)

    public void destroyNodes() throws ExceptionIsEmptyBST{
        if (isEmpty()){
            throw new ExceptionIsEmptyBST("Error: El arbol ya esta vacio. ");
        }
        root = null;
    }
    // Retorna el total de nodos papa(no incluye nodos hoja)
    public int countNodes() {
        if (isEmpty()){
            return 0;
        }

        QueueLink<Node<E>> queue = new QueueLink<Node<E>>();
        queue.enqueue(root);

        int count = 0;

        while (!queue.isEmpty()) {
            Node<E> current = null;

            try {
                current = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                System.out.println(e);
                break;
            }

            boolean hasLeft = false;
            boolean hasRight = false;

            if(current.left != null){
                hasLeft = true;
            }
            if(current.right != null){
                hasRight = true;
            }
            if (hasLeft || hasRight) {
                count++;
            }
            if (hasLeft) {
                queue.enqueue(current.left);
            }
            if (hasRight) {
                queue.enqueue(current.right);

            }
        }

        return count;
    }
    // Retorna el total de nodos (incluye nodos hoja)
    public int countAllNodes() {
        if (isEmpty()) return 0;

        QueueLink<Node<E>> queue = new QueueLink<Node<E>>();
        queue.enqueue(root);

        int count = 0;

        while (!queue.isEmpty()) {
            Node<E> current = null;
            try {
                current = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                System.out.println(e);
                break;
            }
            count++;
            if (current.left != null){
                queue.enqueue(current.left);
            }
            if (current.right != null){
                queue.enqueue(current.right);
            }
        }
        return count;
    }

    public int countQueueElements(QueueLink<Node<E>> queue) throws ExceptionIsEmpty {
        QueueLink<Node<E>> temp = new QueueLink<Node<E>>();
        int count = 0;

        while (!queue.isEmpty()) {
            Node<E> current = queue.dequeue();
            temp.enqueue(current);
            count++;
        }

        while (!temp.isEmpty()) {
            queue.enqueue(temp.dequeue());
        }

        return count;
    }
    public int height(E x) throws ItemNoFound, ExceptionIsEmpty {

        Node<E> node = search_Node(x);

        if (node == null){

            return -1;

        }

        QueueLink<Node<E>> queue = new QueueLink<Node<E>>();
        queue.enqueue(node);
        int height = -1;

        while (!queue.isEmpty()) {

            int nivel = 0;

            try {

                nivel = countQueueElements(queue); // Contamos nodos en este nivel

            } catch (ExceptionIsEmpty e) {

                return height;

            }

            for (int i = 0; i < nivel; i++) {

                Node<E> actual = queue.dequeue();

                if (actual.left != null) {

                    queue.enqueue(actual.left);

                }

                if (actual.right != null) {

                    queue.enqueue(actual.right);

                }
            }
            height++;

        }
        return height;
    }

    public int amplitude(int nivel) throws ExceptionIsEmpty {

        if(isEmpty()){ return 0;}

        QueueLink<Node<E>> queue = new QueueLink<Node<E>>();
        queue.enqueue(root);
        int nivelactual = 0;

        while (!queue.isEmpty()) {
            int tam_nivel = countQueueElements(queue);

            if (nivelactual == nivel) {
                return tam_nivel;

            }

            for (int i = 0; i < tam_nivel; i++) {

                Node<E> actual = queue.dequeue();

                if (actual.left != null) queue.enqueue(actual.left);
                if (actual.right != null) queue.enqueue(actual.right);

            }

            nivelactual++;
        }

        return 0;
    }

    // Search que retorna el Nodo donde se encuentra el dato recibido
    public Node<E> search_Node(E el) throws ItemNoFound, ExceptionIsEmpty {

        if (isEmpty()) {

            throw new ExceptionIsEmpty("Árbol vacío.");

        }

        return RecSearch_Node(root, el);
    }

    public Node<E> RecSearch_Node(Node<E> actual, E el) throws ItemNoFound {

        if (actual == null) {

            throw new ItemNoFound("El elemento -> " + el + " no está en el arbol :v");
        }

        int compared = el.compareTo(actual.data);

        if (compared == 0) {

            return actual;

        } else if (compared < 0) {

            return RecSearch_Node(actual.left, el);

        } else {

            return RecSearch_Node(actual.right, el);

        }
    }

    public int areaBST() throws ItemNoFound, ExceptionIsEmpty {

        if (isEmpty()){

            return 0;

        }

        QueueLink<Node<E>> queue = new QueueLink<Node<E>>();
        queue.enqueue(root);

        int hojas = 0;

        while (!queue.isEmpty()) {

            Node<E> current = null;

            try {

                current = queue.dequeue();

            } catch (ExceptionIsEmpty e) {

                break;

            }

            if (current.left == null && current.right == null) {

                hojas++;

            }

            if (current.left != null){

                queue.enqueue(current.left);

            }

            if (current.right != null){

                queue.enqueue(current.right);

            }

        }

        return hojas * height(root.data);

    }

    public void drawBST() {
        drawBST(this.root, 0);
    }

    private void drawBST(Node<E> node, int depth) {
        if (node == null) {
            return;
        }

        drawBST(node.right, depth + 1);

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        System.out.println(node.data);

        drawBST(node.left, depth + 1);
    }


    public static <E extends Comparable<E>> boolean sameArea(LinkedBST<E> t1, LinkedBST<E> t2) throws ItemNoFound, ExceptionIsEmpty {
        return t1.areaBST() == t2.areaBST();
    }


    public String parenthesize() {
        StringBuilder sb = new StringBuilder();
        RecParenthesize(root, sb, 0);
        return sb.toString();
    }

    private void RecParenthesize(Node<E> node, StringBuilder sb, int depth) {
        if (node != null) {
            // Sangría para este nodo
            for (int i = 0; i < depth; i++) {
                sb.append("  ");
            }

            // Valor del nodo y apertura
            sb.append(node.data).append(" ( \n");

            // Hijo izquierdo
            RecParenthesize(node.left, sb, depth + 1);

            // Hijo derecho
            RecParenthesize(node.right, sb, depth + 1);

            for (int i = 0; i < depth; i++) {
                sb.append("  ");
            }
            //cierre
            sb.append(" ) \n");
        }
    }


    public static void main (String[] args) {
        try {
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
            System.out.println("Uwu:  "+aaa.search(55));

            aaa.inOrder();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
