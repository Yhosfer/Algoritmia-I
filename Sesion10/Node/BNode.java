package Sesion10.Node;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    public ArrayList<E> keys; //keys = claves = espacios dentro del array
    public ArrayList<BNode<E>> childs;
    public int count; //El contador de claves

    public BNode(int n) {

        this.keys   = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count  = 0;

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
