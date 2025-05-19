package Sesion08.Actividad;

class BSTree<E extends Comparable<E>> {
    protected Node<E> root;

    public BSTree() {
        root = null;
    }

    public void insert(E data) {
        root = insertRec(root, data);
    }

    protected Node<E> insertRec(Node<E> node, E data) {
        if (node == null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, data);
        }
        return node;
    }

    public boolean search(E data) {
        return searchRec(root, data);
    }

    protected boolean searchRec(Node<E> node, E data) {
        if (node == null) {
            return false;
        }
        int cmp = data.compareTo(node.data);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return searchRec(node.left, data);
        } else {
            return searchRec(node.right, data);
        }
    }

    public int height() {
        return heightRec(root);
    }

    protected int heightRec(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }
}
