package Sesion10.Ejer04Aplicación;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected int idNodo;
    protected int idPadre;
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    public BNode(int n) {
        this.idNodo = -1;   // se asignará desde Btree
        this.idPadre = -1;
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count = 0;

        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.childs.add(null);
    }

    public boolean nodeFull() {
        return count == keys.size();
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(E key, int[] pos) {
        int i = 0;

        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        //Retornamos por referencia la posición de donde se posiciona el nodo
        pos[0] = i;

        return (i < count && key.compareTo(keys.get(i)) == 0);
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
