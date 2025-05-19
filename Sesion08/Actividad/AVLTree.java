package Sesion08.Actividad;

import Sesion07a.Actividad.LinkedBST;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    @Override
    public void insert(E data) {
        root = insertAVL((NodeAVL<E>) root, data);
    }

    private NodeAVL<E> insertAVL(NodeAVL<E> node, E data) {
        if (node == null) {
            return new NodeAVL<>(data);
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertAVL((NodeAVL<E>) node.left, data);
            node = updateBalanceAfterInsert(node);
        } else if (cmp > 0) {
            node.right = insertAVL((NodeAVL<E>) node.right, data);
            node = updateBalanceAfterInsert(node);
        }
        return node;
    }

    private NodeAVL<E> updateBalanceAfterInsert(NodeAVL<E> node) {
        int lh = heightRec(node.left);
        int rh = heightRec(node.right);
        node.bf = rh - lh;

        if (node.bf == 2) {
            return balanceToLeft(node);
        }
        if (node.bf == -2) {
            return balanceToRight(node);
        }

        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> child = (NodeAVL<E>) node.right;
        if (child.bf >= 0) {
            return rotateSL(node);
        } else {
            node.right = rotateSR(child);
            return rotateSL(node);
        }
    }

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> child = (NodeAVL<E>) node.left;
        if (child.bf <= 0) {
            return rotateSR(node);
        } else {
            node.left = rotateSL(child);
            return rotateSR(node);
        }
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateBF(node);
        updateBF(newRoot);
        return newRoot;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> newRoot = (NodeAVL<E>) node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateBF(node);
        updateBF(newRoot);
        return newRoot;
    }

    private void updateBF(NodeAVL<E> node) {
        int lh = heightRec(node.left);
        int rh = heightRec(node.right);
        node.bf = rh - lh;
    }

    @Override
    public int height() {
        return heightRec(root);
    }

    @Override
    public boolean search(E data) {
        return searchRec(root, data);
    }

    public void printLevelOrder() {
        int h = height();
        for (int i = 0; i < h; i++) {
            printLevel((NodeAVL<E>) root, i);
        }
        System.out.println();
    }

    private void printLevel(NodeAVL<E> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 0) {
            System.out.print(node.data + " ");
        } else {
            printLevel((NodeAVL<E>) node.left, level - 1);
            printLevel((NodeAVL<E>) node.right, level - 1);
        }
    }

    public void printPreOrder() {
        printPreOrder((NodeAVL<E>) root);
        System.out.println();
    }

    private void printPreOrder(NodeAVL<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            printPreOrder((NodeAVL<E>) node.left);
            printPreOrder((NodeAVL<E>) node.right);
        }
    }

    public void delete(E data) {
        root = deleteAVL((NodeAVL<E>) root, data);
    }

    private NodeAVL<E> deleteAVL(NodeAVL<E> node, E data) {
        if (node == null) {
            return null;
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = deleteAVL((NodeAVL<E>) node.left, data);
        } else if (cmp > 0) {
            node.right = deleteAVL((NodeAVL<E>) node.right, data);
        } else {
            if (node.left == null || node.right == null) {
                if (node.left != null) {
                    node = (NodeAVL<E>) node.left;
                } else {
                    node = (NodeAVL<E>) node.right;
                }
            } else {
                NodeAVL<E> successor = findMin((NodeAVL<E>) node.right);
                node.data = successor.data;
                node.right = deleteAVL((NodeAVL<E>) node.right, successor.data);
            }
        }

        if (node == null) {
            return null;
        }

        updateBF(node);
        if (node.bf == 2) {
            return balanceToLeft(node);
        }
        if (node.bf == -2) {
            return balanceToRight(node);
        }

        return node;
    }

    private NodeAVL<E> findMin(NodeAVL<E> node) {
        while (node.left != null) {
            node = (NodeAVL<E>) node.left;
        }
        return node;
    }

    // imprime graficamente el arbol izquiera root -->
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

}
